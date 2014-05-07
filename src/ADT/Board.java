package ADT;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList; 
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.rmi.CORBA.Tie;

import org.antlr.v4.codegen.model.chunk.ThisRulePropertyRef_ctx;

import ADT.Gadget;
import physics.*;
import physics.Geometry.DoublePair;
import ADT.Ball;
import Parser.BoardFileFactory;

/**
 * Concurrency/Thread Safety Strategy: The only aspect of board accessible by the server is its calls.
 * Thus we made the list of calls synchronized to avoid concurrency issues (especially when it comes to baordrep)
 * Furthermore, server contains a synchronized list of boards to concurrent modifications byt he server
 * onto boards.
 * 
 * Note: Board is merely a framework for containing gadgets and balls. It can be visualized as a list 
 * of lists but in our implementation It is but a list of gadgets defined by the position of their origins.
 *
 */

public class Board extends TimerTask {
    public String boardname;
    public List<Ball> balls;
    private double gravity;
    private int dimension;
    private double mu;
    private double mu2;
    public List<List<String>> boardRep;
    private List<Wall> walls;
    private List<Gadget> gadgets;
    private final ArrayList<Gadget> myFlippers = new ArrayList<Gadget>();
    private String wallHit;
    private String portalHit;
    private String[] neighbours = new String[4];
    
    /**
     * The constructor for a board
     * 
     * @param name a string representing the name of the board
     * @param gravity gravity initialized to 0.025 by default
     * @param mu friction initialized to 0.025 by default
     * @param mu2 friction initialized to 0.025 by default
     * @param balls all the balls that are to be included in the board at creation
     * @param gadgets all the gadgets that are to be included in the board
     */
    public Board(String name, double gravity, double mu, double mu2){
        super();
        this.balls = Collections.synchronizedList(new ArrayList<Ball>());
        this.gravity = gravity;
        this.dimension = 20;
        this.mu = mu;
        this.mu2 = mu2;
        this.gadgets = new ArrayList<Gadget>();
        // wall has a field visible (if visible, it is a solid wall)
        this.walls = Collections.synchronizedList(Arrays.asList(new Wall("top",true, null, boardname), new Wall("bottom",true, null, boardname), new Wall("left",true, null, boardname),new Wall("right",true, null, boardname)));
        this.boardname = name;
        this.wallHit = "";
        this.portalHit = "";
        
        /**
         * Makes an empty board with named walls
         */
        this.boardRep = new ArrayList<List<String>>();

        List<String> topWall = walls.get(0).toList();
        List<String> bottomWall = walls.get(1).toList();
        List<String> rightWall = walls.get(3).toList();
        List<String> leftWall = walls.get(2).toList();

        boardRep.add(topWall);
        for (int height=0; height<dimension; height++){
            List<String> temp = new ArrayList<String>();
            temp.add(leftWall.get(height));
            for (int width =0; width<dimension; width++){
                temp.add(" ");
            }
            temp.add(rightWall.get(height));
            boardRep.add(temp);
        }
        boardRep.add(bottomWall);
    }
    
    
    /**
     * Checks if some position is within the board. If not, it concludes that the position is 
     * outside the board and that the ball is projected to collide with a wall
     * @param doublePair representing location to be checked
     * @return boolean if that location is outside the wall (true), (false if within)
     * 
     * Note: this method is called on balls to see if they are overshooting the bounding walls of the board
     */
    private boolean isWall(Geometry.DoublePair doublePair){
        double x = doublePair.d1;
        double y = doublePair.d2;
        return ((x) < 0.25 || (x)>19.75 ||
                (y) < 0.25 || (y)>19.75 );   
    }
    
    
    /**
     * Find the wall the ball is going to collide with first
     * @param ball whose time till collision we are investigating
     * @return the wall (out of the board's four walls) which the ball will collide 
     * with in the least amount of time
     */
    private Wall getMinWall(Ball ball){
        Wall minWall = walls.get(0);
        double minTime = Geometry.timeUntilWallCollision(minWall.getWall(), ball.getBallCircle(), ball.getVelocity());
        for (Wall wall:walls){
            if (Geometry.timeUntilWallCollision(wall.getWall(), ball.getBallCircle(), ball.getVelocity())<minTime){
                minTime = Geometry.timeUntilWallCollision(minWall.getWall(), ball.getBallCircle(), ball.getVelocity());
                minWall = wall;
            }
        }
        return minWall;
    }
    
    
    /**
     * Calculates the minimum time until impact with one of the boards four walls
     * @param ball whose time till collision we want to find
     * @param wall the wall which we want to find time till collision with
     * @return
     */
    private double getMinTimeWall(Ball ball, Wall wall){
        return Geometry.timeUntilWallCollision(wall.getWall(), ball.getBallCircle(), ball.getVelocity());
    }
    
    
    /**
     * Finds the closest gadget a ball can hit
     * @param ball the ball whose time till collision is being investigated
     * @return the closest gadget the ball can hit
     */
    private Gadget getMinTimeGadget(Ball ball){
        Gadget minGadget = gadgets.get(0);
        double timetillcollision = gadgets.get(0).getCollisionTime(ball);
        for (Gadget g: gadgets){
            if (g.getCollisionTime(ball) < timetillcollision){
                minGadget = g;
                timetillcollision = g.getCollisionTime(ball);
            }
        }
        return minGadget;
    }
    
    /**
     * Return the time the ball will take to hit the closest gadget
     * @param ball whose time till collision we want to find
     * @param gadget with respect to which ball's time till collision is calculated
     * @return
     */
    private double timeMinGadget(Ball ball, Gadget gadget){
        return gadget.getCollisionTime(ball);
    }
    
    
    /**
     * Calculates the next position of the ball given there are no obstacles in its path within
     * this time step (set to 0.05 seconds)
     * @param ball whose next position is to be calculated
     * @param time during which ball moves (here, 0.05 seconds)
     * @return the expected position of the ball
     * 
     * note: this method tackles calculations keeping friction and gravity in mind
     */
    private void updateEmpty(Ball ball, double time){
        double oldX = ball.getOriginX();
        double oldY = ball.getOriginY();
      
        double newX = oldX + ball.getVelocity().times(time).x();
        double newY = oldY + ball.getVelocity().times(time).y();
        
        System.out.println(newX);
        System.out.println(newY);
        
        ball.updatePosition(newX, newY);
        
        Vect vel = ball.getVelocity();
        Vect newVel = vel.times(1-mu*time-mu2*ball.getVelocity().length()*time);
        newVel = newVel.plus(new Vect(0, gravity*time));
        
        ball.updateVelocity(newVel);
    
    }
    
    
    /**
     * Updates positions of balls within the board and also tackle removal of balls from it
     * @returns: A String encoding information about the ball(s) leaving the board
     */
    public synchronized void update(){
        List<Ball> freeBalls = performBallBallCollision(balls);
        List<Ball>ballsToRemove = new ArrayList<Ball>();
        boolean ballOut = false;
        synchronized(this.balls){
        for (Ball each:freeBalls){
            boardRepRemoveBall(each);
            double timeMinGadget = Double.POSITIVE_INFINITY;
            Gadget g = null;
            if (!gadgets.isEmpty()){
            g = getMinTimeGadget(each);
            timeMinGadget = timeMinGadget(each, g);

            }           
            Wall w = getMinWall(each);
            double timeMinWall = getMinTimeWall(each, w);
            if(timeMinGadget >0.05 && timeMinWall > 0.05){
                updateEmpty(each, 0.05);

            }
            else if(timeMinGadget<timeMinWall){
                if(each.isAbsorbed()){
                    updateEmpty(each, 0.05);
                    g.release(each);                
                }else if(g.doesPort()){
                    if(g.getOtherBoard().equals("")){
                        boolean otherPortalExists = false;
                        for(Gadget gadget: this.gadgets){
                            if(gadget.getName().equals(g.getOtherPortal())){
                                otherPortalExists = true;
                                each.updatePosition(gadget.getX()-0.5, gadget.getY()-0.5);
                            }
                            
                        }
                        if(otherPortalExists == false){
                            updateEmpty(each, 0.05);
                        }
                    }else{

                        this.portalHit = "port "+this.boardname+" " +g.getOtherBoard()+" "+g.getOtherPortal()+" "+each.name+" "+each.getOriginX()+" "+each.getOriginY()+" "+each.getVelocity().x()+" "+each.getVelocity().y()+"\n";
                        ballOut = true;
                        ballsToRemove.add(each);
                  
                    }
                }else{
                    g.reflect(each);
    /*                if(!g.doesAbsorb() && !g.doesPort()){
                        updateEmpty(each, timeMinGadget);
                    } */                  
                }
            }
            else{
                // CASE 1: if the wall is solid, then merely bounce of it
                if (w.getVisibility()){
                    w.update(each);
                }
                // CASE 2: If the wall is connected to another board, remove the ball for current
                //board and send encode the outgoing wall in a string (to be tackled by server)
                else{
                    ballOut = true;
                    //XXXX is used for splitting purposes to indicate the beginning of information about a new ball
                    if (w.getLocation().equals("top")){
                        
                
                        this.wallHit = "hit "+this.boardname+" 0 " +each.name+" "+each.getOriginX()+" "+each.getOriginY()+" "+each.getVelocity().x()+" "+each.getVelocity().y()+"\n";
                    }
                    else if (w.getLocation().equals("bottom")){
                        
                        
                        this.wallHit = "hit "+this.boardname+" 1 " +each.name+" "+each.getOriginX()+" "+each.getOriginY()+" "+each.getVelocity().x()+" "+each.getVelocity().y()+"\n";
                    }
                    else if (w.getLocation().equals("right")){
                        
                        
                        this.wallHit = "hit "+this.boardname+" 3 " +each.name+" "+each.getOriginX()+" "+each.getOriginY()+" "+each.getVelocity().x()+" "+each.getVelocity().y()+"\n";
                    }
                    else{//left
                        
                        
                        this.wallHit = "hit "+this.boardname+" 2 " +each.name+" "+each.getOriginX()+" "+each.getOriginY()+" "+each.getVelocity().x()+" "+each.getVelocity().y()+"\n";
                    }
                    ballsToRemove.add(each);
                }
                    
            }  
        }
        UpdateBoardRep();
        //Removing the balls that are leaving the board from the board's list of balls
        if (ballOut){
            for (Ball b:ballsToRemove){
                removeBall(b);
            }
            
        }
        }
        
    }
    
    
    /**
     * remove a ball(*) from boardRep so we do not have "ghost" balls
     * @param ball which needs to disappear from the baordRep
     */
    private void boardRepRemoveBall(Ball ball){
        int x = (int) Math.floor(ball.getOriginX());
        int y = (int) Math.floor(ball.getOriginY());
        if (boardRep.get(y + 1).get(x + 1).equals("*")){
            boardRep.get(y+1).remove(x+1);
            boardRep.get(y+1).add(x+1, " ");
        }
    }
    
    
    /**
     * Add the ball(*) at the balls' location in the boardRep
     * @param ball that needs to be shown on the boardRep
     */
    private void boardRepAddBall(Ball ball){
        int x = (int) Math.floor(ball.getOriginX());
        int y = (int) Math.floor(ball.getOriginY());
        
        if (boardRep.get(y + 1).get(x + 1).equals(" ") && !ball.isAbsorbed()){
            boardRep.get(y+1).remove(x+1);
            boardRep.get(y+1).add(x+1, "*");
        }
    }
    
    
    /**
     *Updates the baordRep to represent the current state of the walls 
     *in the string representation of the board
     */
    private void boardRepUpdateWalls(){
        for (Wall wall: walls){
            if (wall.getLocation().equals("top")){
                boardRep.remove(0);
                boardRep.add(0, wall.toList());
            }
            else if (wall.getLocation().equals("bottom")){
                boardRep.remove(21);
                boardRep.add(21, wall.toList());
            }
            
            else if (wall.getLocation().equals("right")){
                List<String> wallList = wall.toList();
                for (int i=0; i<20; i++){
                    boardRep.get(i+1).remove(21);
                    boardRep.get(i+1).add(21, wallList.get(i));
                }
            }
            else{//left wall
                List<String> wallList = wall.toList();
                for (int i=0; i<20; i++){
                    boardRep.get(i+1).remove(0);
                    boardRep.get(i+1).add(0, wallList.get(i));
                }
                
            }
        }
        
    }
    
    
    /**
     * This method updates the boardRep (created in the constructor) so that it represents the
     * internal state of the board.
     * @return: The board representation (which can then be converted to a string for printing)
     */
    private List<List<String>> UpdateBoardRep(){
        // Update the walls (in case of joining walls etc)
        boardRepUpdateWalls();
        
        //ensures that each ball is represented on the board 
        //(unless if it is in a square that already contains another gadget
        for(Ball ball:balls){
            DoublePair pos = ball.getCurrentPosition();
            if(!isWall(pos))
                boardRepAddBall(ball);
        }
        // Update the boardRep to represent the flippers in their right states
        updateFlipperStringPosition();
        
        return boardRep;
    }
    
    
    /**
     * The run method used for single-play. This method is not used for server-client play
     * This prints a string representation of the board to the console.
     */
    @Override
    public void run() {
        this.update();
        System.out.println(this.toString());
        
    }
    
    
    /**
     * Makes all the calculations in calculating the new velocity of balls involved in 
     * ball-ball collisions
     * @requires two balls that will collide for sure
     * @param ball1 one of the balls that will collide
     * @param ball2 the other ball that will collide
     */
    public void handleBallBallCollision(Ball ball1, Ball ball2){
        //mass of the ball?
        Geometry.VectPair velocities = Geometry.reflectBalls(
                ball1.getBallCircle().getCenter(), 1, ball1.getVelocity(),
                ball2.getBallCircle().getCenter(), 1, ball2.getVelocity());
        double time = Geometry.timeUntilBallBallCollision(ball1.getBallCircle(),
                ball1.getVelocity(), ball2.getBallCircle(), ball2.getVelocity());
        boardRepRemoveBall(ball1);
        boardRepRemoveBall(ball2);
        updateEmpty(ball1, time);
        updateEmpty(ball2, time);
        ball1.updateVelocity(velocities.v1);

        ball2.updateVelocity(velocities.v2);

        
        
    }
    
    
    /**
     * Checks all pairs of balls if any pair is going to collide in the next time step. 
     * When a ball is going to collide with more than two balls,it picks the one it's going to hit 
     * soonest. 
     * Then these two balls are not considered for collision with any other balls in the list
     * 
     * @return list of balls which were not involved in any collisions so they can be checked for 
     * collisions with gadgets and walls
     * 
     */
    public List<Ball> performBallBallCollision(List<Ball> balls){
        synchronized(this.balls){
            //Case1: only one ball
            if (balls.size()==1){
                return balls;
            }
            //Multiple balls
            List<Ball> uninvolvedBalls = new ArrayList<Ball>();
            int numBalls = balls.size();
            //List of balls involved in ball-ball collisions
            List<Ball> hasCollided = new ArrayList<Ball>();
            //All balls not involved in collisions
            Set<Ball> uninvolved = new HashSet<Ball>();
            
            for (int i=0; i<numBalls-1; i++){
                Ball ball1 = balls.get(i);
                double time = Double.POSITIVE_INFINITY;
                Ball ballToCollideWith = null;
                for (int j=i+1; j<numBalls; j++){
                    Ball ball2 = balls.get(j);
                    double timeToColl = Geometry.timeUntilBallBallCollision(ball1.getBallCircle(), ball1.getVelocity(), ball2.getBallCircle(), ball2.getVelocity());
                    if (timeToColl<0.05 && timeToColl<time && (!hasCollided.contains(ball2))){
                        ballToCollideWith = ball2;
                        time = timeToColl;
                    }
                }
                if (time==Double.POSITIVE_INFINITY || ballToCollideWith==null){
                    uninvolved.add(ball1);
                }
                else{
                    handleBallBallCollision(ball1, ballToCollideWith);
                    hasCollided.add(ball1);
                    hasCollided.add(ballToCollideWith);
                }
            }
            for (int k=0; k<numBalls;k++){
                if (!(hasCollided.contains(balls.get(k)))){
                    uninvolved.add(balls.get(k));
                }
            }
            // all balls that did not collide with another ball
            uninvolvedBalls.addAll(uninvolved);  
            return uninvolvedBalls;
            }
    }    
    
    /**
     * Remove a ball when it hits an invisible wall
     * @requires: that ball be in the particular board
     * @param ball the ball to be removed
     * @return
     */
    public void removeBall(Ball ball){
        synchronized (this.balls) {
        int x = (int) Math.floor(ball.getOriginX());
        int y = (int) Math.floor(ball.getOriginY());  
        if(x<22 & y<22){
        boardRepRemoveBall(ball);
        }
        balls.remove(ball);
        }
    }    
    
    /**
     * Add a ball to the board
     * @param ball the ball to be added to this particular board
     */
    public void addBall(Ball ball){
        synchronized(this.balls){
        this.balls.add(ball);
        }
    }
    
    
    /**
     * Connects a particular wall of the current board to the board whose name is passed in
     * @param walllocation the current location of our wall
     * @param boardname the new board it will be connected to
     */
    
    public void connectWall(int walllocation, String boardname){
        //truncate long names
        
        if (walllocation == 0){
            walls.get(0).addConnection(boardname);
        }
        else if (walllocation == 1){
            walls.get(1).addConnection(boardname);
        }
        else if (walllocation == 3){
            walls.get(3).addConnection(boardname);
        }
        else{
            walls.get(2).addConnection(boardname);
        }
    }
    
    
    /**
     * This method is used to disconnect all walls in a particular board whose walls
     * were connected to the board  whose name is passed in the method
     * (to be used only when a board dies)
     * @param boardname of board that needs to be removed from all walls
     */
    public void disconnectBoardWalls(String boardname){
        for (Wall w: walls){
            if (w.getConnectedTo().equals(boardname)){
                w.disconnectWall();
            } 
        }
    }
    
    
    /**
     * Changes the state of a wall, irrespective of its visibility to make it visible again
     * @param location a string representing a location
     */
    public void disconnectParticularWall(String location){
        if (location.equals("top")){
            walls.get(0).disconnectWall();
        }
        else if(location.equals("bottom")){
            walls.get(1).disconnectWall();
        }
        else if(location.equals("right")){
            walls.get(3).disconnectWall();
        }
        else{//left one
            walls.get(2).disconnectWall();
        }
    }
    
    
    /**
     * Update the orientation (and hence the representation) of the flippers in the boardRep.
     * 
     * Note: this method is called during each update to ensure that all flippers are up-to-date.
     */
    public void updateFlipperStringPosition(){

        for(Gadget gadget : myFlippers){
            int x = gadget.getX();
            int y= gadget.getY();
            boardRep.get(y).remove(x);
            boardRep.get(y).add(x, gadget.toString().substring(0, 1));
            boardRep.get(y).remove(x+1);
            boardRep.get(y).add(x+1, gadget.toString().substring(1, 2));
            boardRep.get(y+1).remove(x);
            boardRep.get(y+1).add(x, gadget.toString().substring(2, 3));
            boardRep.get(y+1).remove(x+1);
            boardRep.get(y+1).add(x+1, gadget.toString().substring(3, 4));
        }
    }
    
    
    /**
     * This method prints a string representation of the board to the console.
     * Each character represents an LxL section of the wall
     */
    @Override
    public String toString(){
        String output = "";
        for (int i = 0; i<22; i++){
            for (int j = 0; j<22; j++){
                output = output+boardRep.get(i).get(j);
            }
            output =  output + "\n";
        }
        
        return output;
        
    }
    

    /**
     * Adds the specified gadget to the board's list of gadgets.
     * @param gadget the gadget to be added to the board.
     */
    public void addGadget(Gadget gadget) {
        gadgets.add(gadget);
        if(!gadget.doesFlip()){
            for(int x = gadget.getX(); x < gadget.getX() + gadget.getWidth(); x++){
                for(int y=gadget.getY(); y < gadget.getY() + gadget.getHeight(); y++){
                    boardRep.get(y).remove(x);
                    boardRep.get(y).add(x, gadget.getChar());
                }
            }
        }else{
            myFlippers.add(gadget);
            int x = gadget.getX();
            int y= gadget.getY();
            boardRep.get(y).remove(x);
            boardRep.get(y).add(x, gadget.toString().substring(0, 1));
            boardRep.get(y).remove(x+1);
            boardRep.get(y).add(x+1, gadget.toString().substring(1, 2));
            boardRep.get(y+1).remove(x);
            boardRep.get(y+1).add(x, gadget.toString().substring(2, 3));
            boardRep.get(y+1).remove(x+1);
            boardRep.get(y+1).add(x+1, gadget.toString().substring(3, 4));
        }
    }
    
    public String whichWallGotHit(){
        return this.wallHit;
        
    }
    public String whichPortalGotHit(){
        return this.portalHit;
    }
    
    public void giveNeighborsName(int wallNum, String neighbor){
        this.neighbours [wallNum] = neighbor;
        this.walls.get(wallNum).addConnection(neighbor);
        
    }
    
    public void triggerUpKey(String key){
        for(Gadget gadget:gadgets){
            if(gadget.getUpKeyTriggers().contains(key)){
                gadget.action();
            }
        }
    }
    
    public void triggerDownKey(String key){
        for(Gadget gadget:gadgets){
            if(gadget.getDownKeyTriggers().contains(key)){
                gadget.action();
            }
        }
    }
    
    
    public static void main(String[] args) throws IOException{
/*        Board myBoard = new Board("B", 0, 0, 0);
        CircleBumper circle = new CircleBumper("Cicrle", 9, 9);
        myBoard.addGadget(circle);
        TriangularBumper triangle = new TriangularBumper("T", 4, 4, 0);
        myBoard.addGadget(triangle);
        
        myBoard.addBall(new Ball("Ball", 1, 10, 0.0, -4.0, 0.25));
        myBoard.addBall(new Ball("BallA", 1, 1, 1, 1, 0.25 ));*/
        
        //RightFlipper rf = new RightFlipper("Rf", 9, 9, 0);
        //rf.isTriggered(rf);
        
        //LeftFlipper lf = new LeftFlipper("lf", 4, 4, 0);
        //lf.isTriggered(lf);
        
        //myBoard.addGadget(rf);
        //myBoard.addGadget(lf);
        
        StringBuilder boardText = new StringBuilder("");
        
        BufferedReader fr = new BufferedReader(new FileReader("src/Parser/" + "sampleBoard.pb"));
        for (String line = fr.readLine(); line != null; line = fr.readLine()) {
            boardText.append('\n' + line);
        }
        String boardTextString = boardText.toString().substring(1);
        Board myBoard = BoardFileFactory.parse(boardTextString);

        //Ball myBall = new  Ball("Zulaa", 7, 7, 0, 10);
        //myBoard.addBall(myBall);
/*        Timer timer = new Timer();
       
        timer.schedule(myBoard, 0, 50);
        
        CircleBumper circle = new CircleBumper("C", 0, 0);
        Ball newBall = new Ball("A", 0.5, 0.5, 0, 1, 0.25);*/
        
/*        for(Gadget gadget : myBoard.getGadgets()){
            if(gadget.doesPort()){
                System.out.println(gadget.getOtherBoard());
            }
        }*/
        
    }


    public void insertBall(String nameOfBall, double x, double y, double xVel, double yVel){
        Ball ball = new Ball(nameOfBall, x, x, xVel, yVel, .25);
        this.addBall(ball);

    }
    
    public void deleteBall(String nameFfBall){
        for (Ball b:this.balls){
            if (b.getName().equals(nameFfBall)){
                this.removeBall(b);
            }
        }

    }
   
    public String getName(){
        return this.boardname;
    }

    public void clearAllBalls(){
        this.balls = new ArrayList<Ball>();
    }
    
    public int getBallsSize(){
        return this.balls.size();
    }
    
    public void updateWallHit(){
        this.wallHit = "";
    }

    public Gadget getPortal(String portalName){
        for (Gadget gadget: this.gadgets){
            if(gadget.getName().equals(portalName)){
                return gadget;
            }
        }
        return null;
    }

    public List<Ball> getBalls(){
        return this.balls;
    }


    public double getGravity() {
        return this.gravity;
    }


    public double getFriction1() {
        return this.mu;
    }


    public double getFriction2() {
        return this.mu2;
    }


    public List<Gadget> getGadgets() {
        ArrayList<Gadget> myGadgets = new ArrayList<Gadget>();
        for(Gadget gadget: myGadgets){
            myGadgets.add(gadget);
        }
        return myGadgets;
    }
    
    
    public void updatePortalHit(){
        this.portalHit = "";
    }

}
