/*package Parser;
package phase1;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.Test;

import ADT.Ball;
import ADT.Board;

*//**
 * Testing strategy: 
 *  parse board with:
 *      no gadgets or balls.
 *      one ball
 *      one square bumper
 *      one circle bumper
 *      one triangular bumper
 *      one left flipper
 *      one right flipper
 *      one absorber
 *      a self triggering absorber
 *      an absorber triggered by a right flipper   
 *      comments on the board file (with #)
 *//*
public class ParserTest {
    
    *//**
     * Tests the parsing of a board file representing an empty board.
     *//*
    @Test
    public void testEmptyBoard() {
        String boardDeclaration = "board name=sampleBoard1 gravity=20.0 friction1=0.020 friction2=0.020\n";
        Board board = BoardFileFactory.parse(boardDeclaration);
        assertEquals(board.getName(), "sampleBoard1");
        assertTrue(board.getGravity() == 20.0);
        assertTrue(board.getFriction1() == 0.020);
        assertTrue(board.getFriction2() == 0.020);  
    }
    
    *//**
     * Tests the parsing of a board file representing a board containing one ball.
     *//*
    @Test
    public void testOneBall() {
        String boardDeclaration = "board name=sampleBoard1 gravity=20.0 friction1=0.020 friction2=0.020\n "
                + "ball name=Ball x=0.5 y=0.5 xVelocity=2.5 yVelocity=2.5";
        Board board = BoardFileFactory.parse(boardDeclaration);
        assertEquals(board.getName(), "sampleBoard1");
        assertTrue(board.getGravity() == 20.0);
        assertTrue(board.getFriction1() == 0.020);
        assertTrue(board.getFriction2() == 0.020);
        
        Ball onlyBall = board.getBalls().get(0);
        assertEquals(onlyBall.getName(), "Ball");
        assertTrue(onlyBall.getOriginalX() == 0.5);
        assertTrue(onlyBall.getOriginalY() == 0.5);
        assertTrue(onlyBall.getXVelocity() == 2.5);
        assertTrue(onlyBall.getYVelocity() == 2.5);
        
    }
    
    *//**
     * Tests the parsing of a board file representing a board containing one square bumper. 
     *//*
    @Test
    public void testOneSquareBumper() {
        String boardDeclaration = "board name=sampleBoard1 gravity=20.0 friction1=0.020 friction2=0.020\n "
                + "squareBumper name=Square0 x=0 y=2";
        Board board = BoardFileFactory.parse(boardDeclaration);
        assertEquals(board.getName(), "sampleBoard1");
        assertTrue(board.getGravity() == 20.0);
        assertTrue(board.getFriction1() == 0.020);
        assertTrue(board.getFriction2() == 0.020);
        
        //The gadgets list includes the four walls so we start from index 4
        //to get the first gadget added, i.e. the squareBumper.
        assertTrue(board.getGadgets().size() == 5);
        Gadget squareBumper = board.getGadgets().get(4);
        assertEquals(squareBumper.getName(), "Square0");
        assertTrue(squareBumper.getOriginalX() == 0);
        assertTrue(squareBumper.getOriginalY() == 2); 
    }
    
    *//**
     * Tests the parsing of a board file representing a board containing one circle bumper. 
     *//*
    @Test
    public void testOneCircleBumper() {
        String boardDeclaration = "board name=sampleBoard1 gravity=20.0 friction1=0.020 friction2=0.020\n "
                + "circleBumper name=Circle4 x=4 y=3";
        Board board = BoardFileFactory.parse(boardDeclaration);
        assertEquals(board.getName(), "sampleBoard1");
        assertTrue(board.getGravity() == 20.0);
        assertTrue(board.getFriction1() == 0.020);
        assertTrue(board.getFriction2() == 0.020);
        
        //The gadgets list includes the four walls so we start from index 4
        //to get the first gadget added, i.e. the circleBumper
        assertTrue(board.getGadgets().size() == 5);
        Gadget circleBumper = board.getGadgets().get(4);
        assertEquals(circleBumper.getName(), "Circle4");
        assertTrue(circleBumper.getOriginalX() == 4);
        assertTrue(circleBumper.getOriginalY() == 3); 
    }
    
    *//**
     * Tests the parsing of a board file representing a board containing one triangular bumper. 
     *//*
    @Test
    public void testOneTriangularBumper() {
        String boardDeclaration = "board name=sampleBoard1 gravity=20.0 friction1=0.020 friction2=0.020\n "
                + "triangleBumper name=Tri1 x=8 y=8 orientation=270";
        Board board = BoardFileFactory.parse(boardDeclaration);
        assertEquals(board.getName(), "sampleBoard1");
        assertTrue(board.getGravity() == 20.0);
        assertTrue(board.getFriction1() == 0.020);
        assertTrue(board.getFriction2() == 0.020); 
        
        //The gadgets list includes the four walls so we start from index 4
        //to get the first gadget added, i.e. the triangleBumper
        assertTrue(board.getGadgets().size() == 5);
        Gadget triangleBumper = board.getGadgets().get(4);
        assertEquals(triangleBumper.getName(), "Tri1");
        assertTrue(triangleBumper.getOriginalX() == 8);
        assertTrue(triangleBumper.getOriginalY() == 8); 
        assertTrue(triangleBumper.getOrientation() == 270);
    }
    
    *//**
     * Tests the parsing of a board file representing a board containing one left flipper. 
     *//*
    @Test
    public void testOneLeftFlipper() {
        String boardDeclaration = "board name=sampleBoard1 gravity=20.0 friction1=0.020 friction2=0.020\n "
                + "leftFlipper name=FlipL1 x=8 y=2 orientation=0";
        Board board = BoardFileFactory.parse(boardDeclaration);
        assertEquals(board.getName(), "sampleBoard1");
        assertTrue(board.getGravity() == 20.0);
        assertTrue(board.getFriction1() == 0.020);
        assertTrue(board.getFriction2() == 0.020); 
        
        //The gadgets list includes the four walls so we start from index 4
        //to get the first gadget added, i.e. the leftFlipper
        assertTrue(board.getGadgets().size() == 5);
        Gadget leftFlipper = board.getGadgets().get(4);
        assertEquals(leftFlipper.getName(), "FlipL1");
        assertTrue(leftFlipper.getOriginalX() == 8);
        assertTrue(leftFlipper.getOriginalY() == 2); 
        assertTrue(leftFlipper.getOrientation() == 0);
    }
    
    *//**
     * Tests the parsing of a board file representing a board containing one right flipper. 
     *//*
    @Test
    public void testOneRightFlipper() {
        String boardDeclaration = "board name=sampleBoard1 gravity=20.0 friction1=0.020 friction2=0.020\n "
                + "rightFlipper name=FlipR1 x=11 y=2 orientation=0";
        Board board = BoardFileFactory.parse(boardDeclaration);
        assertEquals(board.getName(), "sampleBoard1");
        assertTrue(board.getGravity() == 20.0);
        assertTrue(board.getFriction1() == 0.020);
        assertTrue(board.getFriction2() == 0.020);
        
        //The gadgets list includes the four walls so we start from index 4
        //to get the first gadget added, i.e. the rightFlipper
        assertTrue(board.getGadgets().size() == 5);
        Gadget rightFlipper = board.getGadgets().get(4);
        assertEquals(rightFlipper.getName(), "FlipR1");
        assertTrue(rightFlipper.getOriginalX() == 11);
        assertTrue(rightFlipper.getOriginalY() == 2); 
        assertTrue(rightFlipper.getOrientation() == 0);
    }
    
    *//**
     * Tests the parsing of a board file representing a board containing one absorber. 
     *//*
    @Test
    public void testOneAbsorber() {
        String boardDeclaration = "board name=sampleBoard1 gravity=20.0 friction1=0.020 friction2=0.020\n "
                + "absorber name=Abs x=0 y=19 width=20 height=1";
        Board board = BoardFileFactory.parse(boardDeclaration);
        assertEquals(board.getName(), "sampleBoard1");
        assertTrue(board.getGravity() == 20.0);
        assertTrue(board.getFriction1() == 0.020);
        assertTrue(board.getFriction2() == 0.020);
        
      //The gadgets list includes the four walls so we start from index 4
        //to get the first gadget added, i.e. the absorber.
        assertTrue(board.getGadgets().size() == 5);
        Gadget absorber = board.getGadgets().get(4);
        assertEquals(absorber.getName(), "Abs");
        assertTrue(absorber.getOriginalX() == 0);
        assertTrue(absorber.getOriginalY() == 19); 
        assertTrue(absorber.getWidth() == 20);
        assertTrue(absorber.getHeight() == 1);
    }
    
    *//**
     * Tests the parsing of a board file representing a board containing a self triggering absorber. 
     *//*
    @Test
    public void testGadgetSelfTrigger() {
        String boardDeclaration = "board name=sampleBoard1 gravity=20.0 friction1=0.020 friction2=0.020\n "
                + "absorber name=Abs x=0 y=19 width=20 height=1\n fire trigger=Abs action=Abs";
        Board board = BoardFileFactory.parse(boardDeclaration);
        assertEquals(board.getName(), "sampleBoard1");
        assertTrue(board.getGravity() == 20.0);
        assertTrue(board.getFriction1() == 0.020);
        assertTrue(board.getFriction2() == 0.020);
        
        //The gadgets list includes the four walls so we start from index 4
        //to get the first gadget added, i.e. the absorber.
        assertTrue(board.getGadgets().size() == 5);
        Gadget absorber = board.getGadgets().get(4);
        
        assertEquals(absorber.getTriggers().size(), 1);
        assertTrue(absorber.getTriggers().contains(absorber));
    }
    
    *//**
     * Tests the parsing of a board file representing a board containing a right flipper and absorber. 
     * The absorber is triggered by the events of the right flipper.
     *//*
    @Test
    public void testGadgetNotSelfTrigger() {
        String boardDeclaration = "board name=sampleBoard1 gravity=20.0 friction1=0.020 friction2=0.020\n "
                + "rightFlipper name=FlipR1 x=11 y=2 orientation=0\n absorber name=Abs x=0 y=19 width=20 height=1\n "
                + "fire trigger=FlipR1 action=Abs";
        Board board = BoardFileFactory.parse(boardDeclaration);
        assertEquals(board.getName(), "sampleBoard1");
        assertTrue(board.getGravity() == 20.0);
        assertTrue(board.getFriction1() == 0.020);
        assertTrue(board.getFriction2() == 0.020);
        
        //The gadgets list includes the four walls so we start from index 4
        //to get the first gadget added, i.e. the rightFlipper, and the second 
        //gadget added, i.e. the absorber.
        //The right flipper is added to the list before the absorber
        //since it is defined in the string (board file) before it.
        assertTrue(board.getGadgets().size() == 6);
        Gadget rightFlipper = board.getGadgets().get(4);
        Gadget absorber = board.getGadgets().get(5);
        
        
        assertEquals(absorber.getTriggers().size(), 1);
        assertTrue(absorber.getTriggers().contains(rightFlipper));
        
    }
    
    *//**
     * Tests that comments (beginning with a #) are ignored while parsing.
     *//*
    @Test
    public void testCommentsIgnored() {
        String boardDeclaration = "board name=sampleBoard1 gravity=20.0 friction1=0.020 friction2=0.020\n "
                + "# This board is meant for stand-alone play. It is a loose\n "
                + "# representation of the screenshot in the project handout.\n"
                + "ball name=Ball x=0.5 y=0.5 xVelocity=2.5 yVelocity=2.5";
        
        Board board = BoardFileFactory.parse(boardDeclaration);
        assertEquals(board.getName(), "sampleBoard1");
        assertTrue(board.getGravity() == 20.0);
        assertTrue(board.getFriction1() == 0.020);
        assertTrue(board.getFriction2() == 0.020);
        
        //The board will only contain the ball.
        assertTrue(board.getBalls().size() == 1);
        Ball onlyBall = board.getBalls().get(0);
        assertEquals(onlyBall.getName(), "Ball");
        assertTrue(onlyBall.getOriginalX() == 0.5);
        assertTrue(onlyBall.getOriginalY() == 0.5);
        assertTrue(onlyBall.getXVelocity() == 2.5);
        assertTrue(onlyBall.getYVelocity() == 2.5);
    }
    
    @Test
    public void testParseSampleBoardWithManyGadgets() throws Exception {
        try {
            String boardTextString = fileToString("sampleBoard.pb");
        
            
            Board board = BoardFileFactory.parse(boardTextString);
            assertEquals(board.getName(), "sampleBoard1");
            assertTrue(board.getGravity() == 20.0);
            //friction1 and friction2 are not defined in the sample board file
            //so we check they are set to the default values.
            assertTrue(board.getFriction1() == 0.025);
            assertTrue(board.getFriction2() == 0.025);
            assertTrue(board.getGadgets().size() == 10);
            
            assertEquals(board.getBalls().size(), 2);
            Ball firstBall = board.getBalls().get(0);
            assertEquals(firstBall.getName(), "Ball");
            assertTrue(firstBall.getOriginalX() == 0.5);
            assertTrue(firstBall.getOriginalY() == 0.5);
            assertTrue(firstBall.getXVelocity() == 2.5);
            assertTrue(firstBall.getYVelocity() == 2.5);
            
            Ball secondBall = board.getBalls().get(1);
            assertEquals(secondBall.getName(), "Ball2");
            assertTrue(secondBall.getOriginalX() == 6.0);
            assertTrue(secondBall.getOriginalY() == 5.0);
            assertTrue(secondBall.getXVelocity() == 3.5);
            assertTrue(secondBall.getYVelocity() == -1.4);
            
            
            
            //The order of the gadgets in the list board.getGadgets()
            //is the order they were parsed in, so the order they
            //appeared in the board file.
            //The gadgets list includes the walls so we start from index 4
            //to get the first gadget added.
            Gadget circleBumper = board.getGadgets().get(4);
            assertEquals(circleBumper.getName(), "Circle5");
            assertTrue(circleBumper.getOriginalX() == 5);
            assertTrue(circleBumper.getOriginalY() == 4);
            
            Gadget squareBumper = board.getGadgets().get(5);
            assertEquals(squareBumper.getName(), "Square1");
            assertTrue(squareBumper.getOriginalX() == 1);
            assertTrue(squareBumper.getOriginalY() == 2);
            
            Gadget triangleBumper = board.getGadgets().get(6);
            assertEquals(triangleBumper.getName(), "Tri2");
            assertTrue(triangleBumper.getOriginalX() == 11);
            assertTrue(triangleBumper.getOriginalY() == 9); 
            assertTrue(triangleBumper.getOrientation() == 180);
            
            Gadget leftFlipper = board.getGadgets().get(7);
            assertEquals(leftFlipper.getName(), "FlipL2");
            assertTrue(leftFlipper.getOriginalX() == 8);
            assertTrue(leftFlipper.getOriginalY() == 7); 
            assertTrue(leftFlipper.getOrientation() == 0);
            
            Gadget rightFlipper = board.getGadgets().get(8);
            assertEquals(rightFlipper.getName(), "FlipR2");
            assertTrue(rightFlipper.getOriginalX() == 10);
            assertTrue(rightFlipper.getOriginalY() == 7); 
            assertTrue(rightFlipper.getOrientation() == 0);
            
            Gadget absorber = board.getGadgets().get(9);
            assertEquals(absorber.getName(), "Abs");
            assertTrue(absorber.getOriginalX() == 0);
            assertTrue(absorber.getOriginalY() == 19); 
            assertTrue(absorber.getWidth() == 20);
            assertTrue(absorber.getHeight() == 1);
            assertEquals(absorber.getTriggers().size(), 1);
            assertTrue(absorber.getTriggers().contains(absorber));
            
        } catch (Exception e) {
            throw new FileNotFoundException("Not a valid name");
        }
            
    }
    
    *//**
     * Helper method which reads the file contents and outputs them in String format. Returns the string format of file.
     * @param filename the name of the file to be converted to a string.
     * @return the string equivalent of the file contents.
     * @throws FileNotFoundException if the named file does not exist, is a directory rather than a regular file, 
     * or for some other reason cannot be opened for reading.
     *//*
    private String fileToString(String filename) throws Exception {
        StringBuilder boardText = new StringBuilder("");
        
        BufferedReader fr = new BufferedReader(new FileReader("src/phase1/" + filename));
        for (String line = fr.readLine(); line != null; line = fr.readLine()) {
            boardText.append('\n' + line);
        }
        String boardTextString = boardText.toString().substring(1);
        return boardTextString;
    }
}
*/