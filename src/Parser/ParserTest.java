package Parser;


import static org.junit.Assert.*; 
import org.junit.Test;

import ADT.Ball;
import ADT.Board;
import ADT.Gadget;

/**
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
 */
public class ParserTest {
    
    /**
     * Tests the parsing of a board file representing an empty board.
     */
    @Test
    public void testEmptyBoard() {
        String boardDeclaration = "board name=sampleBoard1 gravity=20.0 friction1=0.020 friction2=0.020\n";
        Board board = BoardFileFactory.parse(boardDeclaration);
        assertEquals(board.getName(), "sampleBoard1");
        assertTrue(board.getGravity() == 20.0);
        assertTrue(board.getFriction1() == 0.020);
        assertTrue(board.getFriction2() == 0.020);  
    }

    /**
     * Tests the parsing of a board file representing a board containing one ball.
     */
    @Test
    public void testOneBall() {
        String boardDeclaration = "board name=sampleBoard1 gravity=20.0 friction1=0.020 friction2=0.020\n "
                + "ball name=Ball x=0.5 y=0.5 xVelocity=2.5 yVelocity=2.5 radius=0.25";
        Board board = BoardFileFactory.parse(boardDeclaration);
        assertEquals(board.getName(), "sampleBoard1");
        assertTrue(board.getGravity() == 20.0);
        assertTrue(board.getFriction1() == 0.020);
        assertTrue(board.getFriction2() == 0.020);
        
        Ball onlyBall = board.getBalls().get(0);
        assertEquals(onlyBall.getName(), "Ball");
        assertTrue(onlyBall.getOriginX() == 0.5);
        assertTrue(onlyBall.getOriginY() == 0.5);
        assertTrue(onlyBall.getXVelocity() == 2.5);
        assertTrue(onlyBall.getYVelocity() == 2.5);
        
    }

    /**
     * Tests the parsing of a board file representing a board containing one square bumper. 
     */
    @Test
    public void testOneSquareBumper() {
        String boardDeclaration = "board name=sampleBoard1 gravity=20.0 friction1=0.020 friction2=0.020\n "
                + "squareBumper name=Square0 x=0 y=2";
        Board board = BoardFileFactory.parse(boardDeclaration);
        assertEquals(board.getName(), "sampleBoard1");
        assertTrue(board.getGravity() == 20.0);
        assertTrue(board.getFriction1() == 0.020);
        assertTrue(board.getFriction2() == 0.020);
        assertTrue(board.getGadgets().size() == 1);
        Gadget squareBumper = board.getGadgets().get(0);
        assertEquals(squareBumper.getName(), "Square0");
        assertTrue(squareBumper.getX() == 1);
        assertTrue(squareBumper.getY() == 3); 
    }

    /**
     * Tests the parsing of a board file representing a board containing one circle bumper. 
     */
    @Test
    public void testOneCircleBumper() {
        String boardDeclaration = "board name=sampleBoard1 gravity=20.0 friction1=0.020 friction2=0.020\n "
                + "circleBumper name=Circle4 x=4 y=3";
        Board board = BoardFileFactory.parse(boardDeclaration);
        assertEquals(board.getName(), "sampleBoard1");
        assertTrue(board.getGravity() == 20.0);
        assertTrue(board.getFriction1() == 0.020);
        assertTrue(board.getFriction2() == 0.020);
        
        assertTrue(board.getGadgets().size() == 1);
        Gadget circleBumper = board.getGadgets().get(0);
        assertEquals(circleBumper.getName(), "Circle4");
        assertTrue(circleBumper.getX() == 5);
        assertTrue(circleBumper.getY() == 4); 
    }
    
/**
     * Tests the parsing of a board file representing a board containing one triangular bumper. 
     */
    @Test
    public void testOneTriangularBumper() {
        String boardDeclaration = "board name=sampleBoard1 gravity=20.0 friction1=0.020 friction2=0.020\n "
                + "triangleBumper name=Tri1 x=8 y=8 orientation=270";
        Board board = BoardFileFactory.parse(boardDeclaration);
        assertEquals(board.getName(), "sampleBoard1");
        assertTrue(board.getGravity() == 20.0);
        assertTrue(board.getFriction1() == 0.020);
        assertTrue(board.getFriction2() == 0.020); 
       
        assertTrue(board.getGadgets().size() == 1);
        Gadget triangleBumper = board.getGadgets().get(0);
        assertEquals(triangleBumper.getName(), "Tri1");
        assertTrue(triangleBumper.getX() == 9);
        assertTrue(triangleBumper.getY() == 9); 
        assertTrue(triangleBumper.getOrientation() == 270);
    }
    
/**
     * Tests the parsing of a board file representing a board containing one left flipper. 
     */
    @Test
    public void testOneLeftFlipper() {
        String boardDeclaration = "board name=sampleBoard1 gravity=20.0 friction1=0.020 friction2=0.020\n "
                + "leftFlipper name=FlipL1 x=8 y=2 orientation=0";
        Board board = BoardFileFactory.parse(boardDeclaration);
        assertEquals(board.getName(), "sampleBoard1");
        assertTrue(board.getGravity() == 20.0);
        assertTrue(board.getFriction1() == 0.020);
        assertTrue(board.getFriction2() == 0.020); 
        
        
        assertTrue(board.getGadgets().size() == 1);
        Gadget leftFlipper = board.getGadgets().get(0);
        assertEquals(leftFlipper.getName(), "FlipL1");
        assertTrue(leftFlipper.getX() == 9);
        assertTrue(leftFlipper.getY() == 3); 
        assertTrue(leftFlipper.getOrientation() == 0);
    }
    
/**
     * Tests the parsing of a board file representing a board containing one right flipper. 
     */
    @Test
    public void testOneRightFlipper() {
        String boardDeclaration = "board name=sampleBoard1 gravity=20.0 friction1=0.020 friction2=0.020\n "
                + "rightFlipper name=FlipR1 x=11 y=2 orientation=0";
        Board board = BoardFileFactory.parse(boardDeclaration);
        assertEquals(board.getName(), "sampleBoard1");
        assertTrue(board.getGravity() == 20.0);
        assertTrue(board.getFriction1() == 0.020);
        assertTrue(board.getFriction2() == 0.020);
        
        
        assertTrue(board.getGadgets().size() == 1);
        Gadget rightFlipper = board.getGadgets().get(0);
        assertEquals(rightFlipper.getName(), "FlipR1");
        assertTrue(rightFlipper.getX() == 12);
        assertTrue(rightFlipper.getY() == 3); 
        assertTrue(rightFlipper.getOrientation() == 0);
    }

    /**
     * Tests the parsing of a board file representing a board containing one absorber. 
     */
    @Test
    public void testOneAbsorber() {
        String boardDeclaration = "board name=sampleBoard1 gravity=20.0 friction1=0.020 friction2=0.020\n "
                + "absorber name=Abs x=0 y=19 width=20 height=1";
        Board board = BoardFileFactory.parse(boardDeclaration);
        assertEquals(board.getName(), "sampleBoard1");
        assertTrue(board.getGravity() == 20.0);
        assertTrue(board.getFriction1() == 0.020);
        assertTrue(board.getFriction2() == 0.020);
        
        assertTrue(board.getGadgets().size() == 1);
        Gadget absorber = board.getGadgets().get(0);
        assertEquals(absorber.getName(), "Abs");
        assertTrue(absorber.getX() == 1);
        assertTrue(absorber.getY() == 20); 
        assertTrue(absorber.getWidth() == 20);
        assertTrue(absorber.getHeight() == 1);
    }
    
    /**
     * Tests the parsing of a board file representing a board containing a self triggering absorber. 
     */
    @Test
    public void testGadgetSelfTrigger() {
        String boardDeclaration = "board name=sampleBoard1 gravity=20.0 friction1=0.020 friction2=0.020\n "
                + "absorber name=Abs x=0 y=19 width=20 height=1\n fire trigger=Abs action=Abs";
        Board board = BoardFileFactory.parse(boardDeclaration);
        assertEquals(board.getName(), "sampleBoard1");
        assertTrue(board.getGravity() == 20.0);
        assertTrue(board.getFriction1() == 0.020);
        assertTrue(board.getFriction2() == 0.020);
        
        
        assertTrue(board.getGadgets().size() == 1);
        Gadget absorber = board.getGadgets().get(0);
        
        assertEquals(absorber.getTriggers().size(), 1);
        assertTrue(absorber.getTriggers().contains(absorber));
    }

    /**
     * Tests the parsing of a board file representing a board containing a right flipper and absorber. 
     * The absorber is triggered by the events of the right flipper.
     */
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
        
        
        assertTrue(board.getGadgets().size() == 2);
        Gadget rightFlipper = board.getGadgets().get(0);
        Gadget absorber = board.getGadgets().get(1);
        
        
        assertEquals(absorber.getTriggers().size(), 1);
        assertTrue(absorber.getTriggers().contains(rightFlipper));
        
    }
    
/**
     * Tests that comments (beginning with a #) are ignored while parsing.
     */
    @Test
    public void testCommentsIgnored() {
        String boardDeclaration = "board name=sampleBoard1 gravity=20.0 friction1=0.020 friction2=0.020\n "
                + "# This board is meant for stand-alone play. It is a loose\n "
                + "# representation of the screenshot in the project handout.\n"
                + "ball name=Ball x=0.5 y=0.5 xVelocity=2.5 yVelocity=2.5 radius=0.25";
        
        Board board = BoardFileFactory.parse(boardDeclaration);
        assertEquals(board.getName(), "sampleBoard1");
        assertTrue(board.getGravity() == 20.0);
        assertTrue(board.getFriction1() == 0.020);
        assertTrue(board.getFriction2() == 0.020);
        
        //The board will only contain the ball.
        assertTrue(board.getBalls().size() == 1);
        Ball onlyBall = board.getBalls().get(0);
        assertEquals(onlyBall.getName(), "Ball");
        assertTrue(onlyBall.getOriginX() == 0.5);
        assertTrue(onlyBall.getOriginY() == 0.5);
        assertTrue(onlyBall.getXVelocity() == 2.5);
        assertTrue(onlyBall.getYVelocity() == 2.5);
    }
}
