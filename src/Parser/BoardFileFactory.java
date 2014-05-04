package Parser;  

import java.util.ArrayList;   
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.TokenStream; 
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

import ADT.Absorber;
import ADT.Ball;
import ADT.Board;
import ADT.CircleBumper;
import ADT.Gadget;
import ADT.LeftFlipper;
import ADT.Portal;
import ADT.RightFlipper;
import ADT.SquareBumper;
import ADT.TriangularBumper;

public class BoardFileFactory {
    /**
     * @param input string representing a board
     * @return Board corresponding to the input
     */
    public static ADT.Board parse(String input) {
        // Create a stream of tokens using the lexer.
        CharStream stream = new ANTLRInputStream(input);
        BoardFileLexer lexer = new BoardFileLexer(stream);
        lexer.reportErrorsAsExceptions();
        TokenStream tokens = new CommonTokenStream(lexer);
        
        // Feed the tokens into the parser.
        BoardFileParser parser = new BoardFileParser(tokens);
        parser.reportErrorsAsExceptions();
        
        // Generate the parse tree using the starter rule.
        ParseTree tree = parser.file(); // "file" is the starter rule
        
        // debugging option #1: print the tree to the console
//        System.err.println(tree.toStringTree(parser));

        // debugging option #2: show the tree in a window
//        ((RuleContext)tree).inspect(parser);

        // debugging option #3: walk the tree with a listener
//        new ParseTreeWalker().walk(new PrintEverythingListener(), tree);
        
        // Finally, construct a Board instance by walking over the parse tree.
        ParseTreeWalker walker = new ParseTreeWalker();
        BoardFileCreatorListener listener = new BoardFileCreatorListener();
        walker.walk(listener, tree);
        
        // return the Board instance that the listener created
        return listener.getBoard();
    }
    
  private static class BoardFileCreatorListener extends BoardFileBaseListener {
      //this stack can contain the attribute names gravity, friction1, friction2
      private Stack<String> boardAttributes = new Stack<>();
      
      //this map will map "gravity" to the gravity value it refers to. The same thing
      //applies to friction1, and friction2. (e.g. map["gravity"] = 20.0)
      private Map<String, String> boardAttributeValues = new HashMap<String, String>();
      
      //The attributes queue contains the values of the attributes such as [20.0, 0.020, ...]
      //corresponding to attributes "gravity", "friction1", ...
      private Queue<Object> attributes = new LinkedList<Object>();
      private ArrayList<Gadget> gadgets = new ArrayList<Gadget>();
      private ArrayList<Ball> balls = new ArrayList<Ball>();
      private Board desiredBoard;
      
      /*
       * Printing the parser tree will help. (in parse method)
       * 
       * Assumes that the attributes queue is in FIFO order 
       * and for every node, the concrete syntax tree has the attributes 
       * in a certain order from left to right. 
       * 
       * For example, for the ball node, the attributes queue will be:
       * [name, x-coordinte, y-coordinate, x-velocity, y-velocity] 
       * with name added the earliest and y-velocity added the latest.
       * Removing from this queue means that name is the first thing 
       * removed, x-coordinate is the second, etc.
       * 
       * Integer.parseInt(String s) expects the string passed in to contain an integer. 
       * If a float is passed in, it raises a NumberFormatException. This is 
       * what we want: for example, square needs its x and y coordinates to be 
       * integers only and should raise an exception if a float is passed in.  
       */
      
  @Override
  public void exitValue(BoardFileParser.ValueContext ctx) {
      //In the parse tree, the value node points to the actual value of the attribute.
      //e.g. attribute is name, value is sampleBoard1. So the value node points to sampleBoard1.
      //the variable "attribute" is the value of the attribute, so in this case, it is attribute = "sampleBoard1".
      Object attribute = ctx.getChild(0);
      
      //If there are board attributes left to pop, so the stack contains either a gravity, friction1, or friction2,
      //then we want to map the board attribute (e.g. gravity) to its value (e.g. 20.0)
      if (!boardAttributes.isEmpty()) {
          String boardAttribute = boardAttributes.pop();
          boardAttributeValues.put(boardAttribute, attribute.toString());
      } else {
          attributes.add(attribute);
      }
      
  }
  
  @Override
  public void visitTerminal(TerminalNode node) {
      //Only push "gravity", "friction1", or "friction2" to the boardAttributes stack
      if ((node.toString().equals("gravity")) ||(node.toString().equals("friction1")) || (node.toString().equals("friction2"))) {
          boardAttributes.push(node.toString());
      }
  }
  
  @Override
  public void exitBall(BoardFileParser.BallContext ctx) {
      String name = attributes.remove().toString();
      double xCoord = Double.parseDouble(attributes.remove().toString());
      double yCoord = Double.parseDouble(attributes.remove().toString());
      double xVelocity = Double.parseDouble(attributes.remove().toString());
      double yVelocity = Double.parseDouble(attributes.remove().toString());
      double radius = Double.parseDouble(attributes.remove().toString());
      Ball ball = new Ball(name, xCoord, yCoord, xVelocity, yVelocity, radius);
      desiredBoard.addBall(ball);
  }
      
  @Override
  public void exitSquare(BoardFileParser.SquareContext ctx) {
      String name = attributes.remove().toString();
      int xCoord = Integer.parseInt(attributes.remove().toString());
      int yCoord = Integer.parseInt(attributes.remove().toString());
      SquareBumper squareBumper = new SquareBumper(name, xCoord, yCoord);
      desiredBoard.addGadget(squareBumper);
      gadgets.add(squareBumper);
  }
  
  @Override
  public void exitCircle(BoardFileParser.CircleContext ctx) {
      String name = attributes.remove().toString();
      int xCoord = Integer.parseInt(attributes.remove().toString());
      int yCoord = Integer.parseInt(attributes.remove().toString());
      CircleBumper circleBumper = new CircleBumper(name, xCoord, yCoord);
      desiredBoard.addGadget(circleBumper);
      gadgets.add(circleBumper);
  }
  
  @Override
  public void exitTriangle(BoardFileParser.TriangleContext ctx) {
      String name = attributes.remove().toString();
      int xCoord = Integer.parseInt(attributes.remove().toString());
      int yCoord = Integer.parseInt(attributes.remove().toString());
      int orientation = Integer.parseInt(attributes.remove().toString());
      TriangularBumper triangularBumper = new TriangularBumper(name, xCoord, yCoord, orientation);
      desiredBoard.addGadget(triangularBumper);
      gadgets.add(triangularBumper);
  }
  
  @Override
  public void exitLeftFlipper(BoardFileParser.LeftFlipperContext ctx) {
      String name = attributes.remove().toString();
      int xCoord = Integer.parseInt(attributes.remove().toString());
      int yCoord = Integer.parseInt(attributes.remove().toString());
      int orientation = Integer.parseInt(attributes.remove().toString());
      LeftFlipper leftFlipper = new LeftFlipper(name, xCoord, yCoord, orientation);
      desiredBoard.addGadget(leftFlipper);
      gadgets.add(leftFlipper);
  }
  
  @Override
  public void exitRightFlipper(BoardFileParser.RightFlipperContext ctx) {
      String name = attributes.remove().toString();
      int xCoord = Integer.parseInt(attributes.remove().toString());
      int yCoord = Integer.parseInt(attributes.remove().toString());
      int orientation = Integer.parseInt(attributes.remove().toString());
      RightFlipper rightFlipper = new RightFlipper(name, xCoord, yCoord, orientation);
      desiredBoard.addGadget(rightFlipper);
      gadgets.add(rightFlipper);
  }
  
  @Override
  public void exitAbsorber(BoardFileParser.AbsorberContext ctx) {
      String name = attributes.remove().toString();
      int xCoord = Integer.parseInt(attributes.remove().toString());
      int yCoord = Integer.parseInt(attributes.remove().toString());
      int width = Integer.parseInt(attributes.remove().toString());
      int height = Integer.parseInt(attributes.remove().toString());
      
      Absorber absorber = new Absorber(name, xCoord, yCoord, width, height);
      desiredBoard.addGadget(absorber);
      gadgets.add(absorber);
  }
  
  @Override
  public void exitPortal(BoardFileParser.PortalContext ctx) {
      String name = attributes.remove().toString();
      int xCoord = Integer.parseInt(attributes.remove().toString());
      int yCoord = Integer.parseInt(attributes.remove().toString());
      String otherBoard = "";
      String otherPortal = attributes.remove().toString();
      if(otherPortal.equals("otherBoard")){
          otherBoard = otherPortal;
          otherPortal = attributes.remove().toString();
      }
      
      Portal portal = new Portal(name, xCoord, yCoord, otherBoard, otherPortal);
      desiredBoard.addGadget(portal);
      gadgets.add(portal);
  }
  
  @Override
  public void exitFire(BoardFileParser.FireContext ctx) {
      //the fire node points to terminal nodes trigger and action
      //(among other terminal nodes).
      Object trigger = ctx.getChild(3);
      Object action = ctx.getChild(6);
      Gadget triggerGadget = gadgets.get(0);
      Gadget actionGadget = gadgets.get(0);
      for (Gadget gadget : gadgets) {
          if (gadget.getName().equals(trigger.toString())) {
              triggerGadget = gadget;
          } if (gadget.getName().equals(action.toString())) {
              actionGadget = gadget;
          }
      }
      actionGadget.isTriggered(triggerGadget);
  }
  
  @Override
  public void exitKeytrigger(BoardFileParser.KeytriggerContext ctx) {
      //the fire node points to terminal nodes trigger and action
      //(among other terminal nodes).
      Object command = ctx.getChild(0);
      Object key = ctx.getChild(3);
      Object action = ctx.getChild(6);
   
      if(command.toString().equals("keyup")){
          for (Gadget gadget : gadgets) {
              if (gadget.getName().equals(action.toString())) {
                  gadget.addKeyUp(key.toString());
              }
          }
      }else{
          for (Gadget gadget : gadgets) {
              if (gadget.getName().equals(action.toString())) {
                  gadget.addKeyDown(key.toString());
              }
          }
      }
  }
  
  @Override
  public void exitBoard(BoardFileParser.BoardContext ctx) {
      //We have enough information to construct the desired board once 
      //we exit the board node. 
      //Assumes the attributes queue is implemented in FIFO order and
      //the concrete syntax tree has the attributes in this order from left to right.
      String name = attributes.remove().toString();
      
      //Default values of gravity, friction1, friction2
      double gravity = 25.0;
      double friction1 = 0.025;
      double friction2 = 0.025;
      
      //If the board file specifies a gravity, then it must be in the boardAttributeValues map. 
      //Same applies to friction1 and friction2. Otherwise, the default values are used.
      if (boardAttributeValues.get("gravity") != null) {
          gravity = Double.parseDouble(boardAttributeValues.get("gravity"));
      }
      if (boardAttributeValues.get("friction1") != null) {
          friction1 = Double.parseDouble(boardAttributeValues.get("friction1"));
      }
      if (boardAttributeValues.get("friction2") != null) {
          friction2 = Double.parseDouble(boardAttributeValues.get("friction2"));
      }
      
      desiredBoard = new Board(name, gravity, friction1, friction2);
      
  }
  
  public Board getBoard() {
      return desiredBoard;
  }
}


static class PrintEverythingListener extends BoardFileBaseListener {
  public void enterFile(BoardFileParser.FileContext ctx) { System.err.println("entering file " + ctx.getText()); }
  public void exitFile(BoardFileParser.FileContext ctx) { System.err.println("exiting file " + ctx.getText()); }

  public void enterDeclaration(BoardFileParser.DeclarationContext ctx) { System.err.println("entering declaration " + ctx.getText()); }
  public void exitDeclaration(BoardFileParser.DeclarationContext ctx) { System.err.println("exiting declaration " + ctx.getText()); }

  public void enterBoard(BoardFileParser.BoardContext ctx) { System.err.println("entering board " + ctx.getText()); }
  public void exitBoard(BoardFileParser.BoardContext ctx) { System.err.println("exiting board " + ctx.getText()); }
  
  public void enterValue(BoardFileParser.ValueContext ctx) { System.err.println("entering value " + ctx.getText()); }
  public void exitValue(BoardFileParser.ValueContext ctx) { System.err.println("exiting value " + ctx.getText()); }
  
  public void enterBall(BoardFileParser.BallContext ctx) { System.err.println("entering ball " + ctx.getText()); }
  public void exitBall(BoardFileParser.BallContext ctx) { System.err.println("exiting ball " + ctx.getText()); }
  
  public void enterSquare(BoardFileParser.SquareContext ctx) { System.err.println("entering square " + ctx.getText()); }
  public void exitSquare(BoardFileParser.SquareContext ctx) { System.err.println("exiting square " + ctx.getText()); }
  
  public void enterCircle(BoardFileParser.CircleContext ctx) { System.err.println("entering circle " + ctx.getText()); }
  public void exitCircle(BoardFileParser.CircleContext ctx) { System.err.println("exiting circle " + ctx.getText()); }
  
  public void enterTriangle(BoardFileParser.TriangleContext ctx) { System.err.println("entering triangle " + ctx.getText()); }
  public void exitTriangle(BoardFileParser.TriangleContext ctx) { System.err.println("exiting triangle " + ctx.getText()); }
  
  public void enterLeftFlipper(BoardFileParser.LeftFlipperContext ctx) { System.err.println("entering leftFlipper " + ctx.getText()); }
  public void exitLeftFlipper(BoardFileParser.LeftFlipperContext ctx) { System.err.println("exiting leftFlipper " + ctx.getText()); }
  
  public void enterRightFlipper(BoardFileParser.RightFlipperContext ctx) { System.err.println("entering rightFlipper " + ctx.getText()); }
  public void exitRightFlipper(BoardFileParser.RightFlipperContext ctx) { System.err.println("exiting rightFlipper " + ctx.getText()); }
  
  public void enterAbsorber(BoardFileParser.AbsorberContext ctx) { System.err.println("entering absorber " + ctx.getText()); }
  public void exitAbsorber(BoardFileParser.AbsorberContext ctx) { System.err.println("exiting absorber " + ctx.getText()); }
  
  public void enterPortal(BoardFileParser.PortalContext ctx) { System.err.println("entering portal " + ctx.getText()); }
  public void exitPortal(BoardFileParser.PortalContext ctx) { System.err.println("exiting portal " + ctx.getText()); }
  
  public void enterFire(BoardFileParser.FireContext ctx) { System.err.println("entering fire " + ctx.getText()); }
  public void exitFire(BoardFileParser.FireContext ctx) { System.err.println("exiting fire " + ctx.getText()); }
  
  public void enterKey(BoardFileParser.KeytriggerContext ctx) { System.err.println("entering key " + ctx.getText()); }
  public void exitKey(BoardFileParser.KeytriggerContext ctx) { System.err.println("exiting key " + ctx.getText()); }
}

}
