package PingballTests;

import static org.junit.Assert.*; 

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import ADT.Ball;
import ADT.Board;
import ADT.Absorber;
import ADT.CircleBumper;
import ADT.LeftFlipper;
import ADT.RightFlipper;
import ADT.SquareBumper;
import ADT.TriangularBumper;
import physics.Circle;
import physics.Geometry;
import physics.Vect;
import ADT.Gadget;

/**
 * Testing Strategy:
 * 
 * 1. Written Tests:
 * - Test Board's toString method:
 *   Check that all the gadgets in different orientations and states should be printed correctly.
 *   Ball should be represented as *
 *   Square bumper should be #
 *   Circle bumper should be O
 *   Triangle bumper should be / for orientation 0 or 180, \ for orientation 90 or 270
 *   Flipper | when vertical, - when horizontal /has 4 different orientations and 2 different states for each
 *   orientation/
 *   Absorber =
 *   Outer wall . 
 *
 * 
 * - Test when there are multiple gadgets, the board is calculating the correct collision time
 *      until the ball hits the nearest gadget in the board. /check board getCollisonTime() method/
 *    
 * - Tests for trigger relationships:
 *   
 *      A gadget can be triggered by a single gadget /Triggering gadget could be either
 *      the gadget itself /self-triggering/, or a different gadget./
 *   
 *      A gadget can be triggered by multiple gadgets. /It could include the gadget itself or not./
 *   
 *      A gadget can trigger a single gadget or multiple gadgets. 
 *   
 *      When a gadget is an absorber:
 *         When the previously ejected ball has not yet left the absorber, the absorber should 
 *         take no action when it is triggered. 
 * - Test if the board reflects the name of the neighbors correctly:
 *      always start the name at the beginning of the wall
 *      if name of the neighbor is too long, truncate it to 20 characters
 * - Test ball-ball collisions
 *      two, three, four balls
 *      
 * 2. Manual Tests:
 * - Manually checked and saw that when a ball hits a gadget from different angles /all edge cases/, 
 *      the ball's velocity should be updated correctly and it should be reflected to a correct direction.
 *      
 */

public class BoardTests {
    @Test public void boardToString(){
        Board board = new Board("Board A", 25, 0.01, 0.01);
        board.addBall(new Ball("BallA", 0.5, 0.5, 1, 1, 0.25));
        board.addGadget(new CircleBumper("CircleA", 1, 0));
        board.addGadget(new SquareBumper("SquareA", 1, 1));
        board.addGadget(new TriangularBumper("TriangleA", 2, 0, 180));
        board.addGadget(new TriangularBumper("TriangleB", 3, 0, 90));
        board.addGadget(new LeftFlipper("LeftA", 4, 0, 0));
        board.addGadget(new LeftFlipper("LeftB", 6, 0, 90));
        board.addGadget(new LeftFlipper("LeftC", 8, 0, 180));
        board.addGadget(new LeftFlipper("LeftD", 10, 0, 270));
        board.addGadget(new RightFlipper("RightA", 12, 0, 0));
        board.addGadget(new RightFlipper("RightB", 14, 0, 90));
        board.addGadget(new RightFlipper("RightC", 16, 0, 180));
        board.addGadget(new RightFlipper("RightD", 18, 0, 270));
        board.addGadget(new Absorber("Abs", 0, 2, 20, 1));
        String boardToString = board.toString();
        String[] rows = boardToString.split("\n");
        
        assertEquals(rows[0], "......................");
        assertEquals(rows[1], ".*O/\\| -- |   |  | --.");
        assertEquals(rows[2], ". #  |    |-- |--|   .");
        assertEquals(rows[3], ".====================.");
        for (int row = 4; row < 21; row++){
            assertEquals(rows[row], ".                    .");
        }
        assertEquals(rows[21], "......................");
    }
    
    @Test public void twoGadgetsTriggeredBySameGadget() {
        Board board = new Board("Board1", 25.0, 0.025, 0.025);
        
        TriangularBumper triangularBumper = new TriangularBumper("Tri1", 9, 11, 0);
        board.addGadget(triangularBumper);
        
        RightFlipper rightFlipper = new RightFlipper("FlipR", 4, 5, 0);
        board.addGadget(rightFlipper);
        rightFlipper.isTriggered(triangularBumper);
        
        Absorber absorber = new Absorber("Abs", 0, 19, 20, 1);
        board.addGadget(absorber);
        absorber.isTriggered(triangularBumper);
        
        
        //Absorber stores ball2
        Ball ball2 = new Ball("Ball2", 2, 2, 0, 1, 0.25);
        board.addBall(ball2);
        absorber.reflect(ball2);
        
        
        Ball ball1 = new Ball("Ball1", 12, 9, -2, 3, 0.25);
        triangularBumper.reflect(ball1);

        assertTrue(rightFlipper.isRotated());
        assertEquals(ball2.getVelocity().equals(new Vect(0, 0)), false);
   
    }
    
    @Test public void OneGadgetTriggeredByTwoGadgets() {
        Board board = new Board("Board1", 25.0, 0.025, 0.025);
        
        TriangularBumper triangularBumper = new TriangularBumper("Tri1", 9, 11, 0);
        board.addGadget(triangularBumper);
        
        RightFlipper rightFlipper = new RightFlipper("FlipR", 4, 5, 0);
        board.addGadget(rightFlipper);
        
        Absorber absorber = new Absorber("Abs", 0, 19, 20, 1);
        board.addGadget(absorber);
        absorber.isTriggered(triangularBumper);
        absorber.isTriggered(rightFlipper);
        
        //---FIRST TRIGGER---
        
        //Absorber stores ball2
        Ball ball2 = new Ball("Ball2", 2, 2, 0, 1, 0.25);
        board.addBall(ball2);
        absorber.reflect(ball2);
        
        Ball ball1 = new Ball("Ball1", 12, 9, -2, 3, 0.25);
        triangularBumper.reflect(ball1);
        
        assertEquals(ball2.getVelocity().equals(new Vect(0, 0)), false);
        
        //Remove the stored ball so that when ball3 (below) hits the absorber and 
        //gets stored, and the absorber gets triggered by the right flipper.
        //it can shoot ball3 upwards.
        //The reason we need this is that if the previously ejected ball of this absorber has not yet left
        //the absorber, the absorber takes no action when it receives a trigger signal.
        //Therefore, since the ball 1 has not yet left the absorber, the absorber
        //should not shoot the ball3.
        absorber.getStoredBalls().remove(ball2);
        
        //---SECOND TRIGGER---
        
        //Absorber stores ball3
        Ball ball3 = new Ball("Ball3", 3, 3, 0, 1, 0.25);
        board.addBall(ball3);
        absorber.reflect(ball3);
        
        
        Ball ball4 = new Ball("Ball4", 8, 10, -1, -1, 0.25);
        rightFlipper.reflect(ball4);
        
        assertFalse(ball3.getVelocity().equals(new Vect(0, 0)));
    }
    
    @Test public void neighborNamesAllTogether(){
        Board board = new Board("Board A", 25, 0.01, 0.01);
        board.connectWall(0, "Mercury");
        board.connectWall(3,"Some new star that has never been see before");
        board.connectWall(2,"Zulsar Lena George  ");
        board.connectWall(1,"Mars");
        board.update();
        String boardToString = board.toString();
        String[] rows = boardToString.split("\n");
        //System.out.println(boardToString);
        assertEquals(rows[0], ".......Mercury........");
        assertEquals(rows[1], ".                    .");
        assertEquals(rows[2], "Z                    S");
        assertEquals(rows[3], "u                    o");
        assertEquals(rows[4], "l                    m");
        assertEquals(rows[5], "s                    e");
        assertEquals(rows[6], "a                     ");
        assertEquals(rows[7], "r                    n");
        assertEquals(rows[8], "                     e");
        assertEquals(rows[9], "L                    w");
        assertEquals(rows[10], "e                     ");
        assertEquals(rows[11], "n                    s");
        assertEquals(rows[12], "a                    t");
        assertEquals(rows[13], "                     a");
        assertEquals(rows[14], "G                    r");
        assertEquals(rows[15], "e                     ");
        assertEquals(rows[16], "o                    t");
        assertEquals(rows[17], "r                    h");
        assertEquals(rows[18], "g                    a");
        assertEquals(rows[19], "e                    t");
        assertEquals(rows[20], "                      ");
        assertEquals(rows[21], ".........Mars.........");
    }
    public void testHandleBallBallCollision() {
        Ball ball1 = new Ball("ball1",new Circle(1,1,0.25), new Vect(0,-1));
        Ball ball2 = new Ball("ball2",new Circle(1,1.05,0.25), new Vect(0,1));
        Board board = new Board("test",25, .025, .025);
        board.addBall(ball1);
        board.addBall(ball2);
        board.handleBallBallCollision(ball1, ball2);

        assertEquals(new Vect(0, 1), ball1.getVelocity());
        assertEquals(new Vect(0, -1), ball2.getVelocity());
        
    }
    
    /**
     * Test two balls colliding
     */
    @Test 
    public void testPerformBallBallCollisionTwoBalls(){
        //System.out.println("TEST2");
        Ball ball1 = new Ball("ball1",new Circle(1.05,1,.25), new Vect(-1,0));
        Ball ball2 = new Ball("ball2",new Circle(1,1.0,0.25), new Vect(1,0));
        Board board = new Board("test",25, .025, .025);
        board.addBall(ball1);
        board.addBall(ball2);
        List<Ball> balls = Arrays.asList(ball1, ball2);
        List<Ball> uninvolved = board.performBallBallCollision(balls);
        
        assertEquals(ball2.getVelocity(), new Vect(-1,0));
        assertEquals(ball1.getVelocity(), new Vect(1,0));
        assertTrue(uninvolved.isEmpty());
        List<Ball> uninvolved2 = board.performBallBallCollision(balls);
        assertTrue(uninvolved2.size()==2);
        
        
        
    }
    
    /**
     * Test three Balls Colliding
     */
    @Test
    public void testPerformBallBallCollisionThreeBalls(){
        //1 and 3 collide, 2 keeps moving
        //System.out.println("TEST3");
        Ball ball1 = new Ball("ball1",new Circle(1,1,0.25), new Vect(0,-1));
        Ball ball2 = new Ball("ball2",new Circle(1,1.05,0.25), new Vect(0,1));
        Vect vel2Old = ball2.getVelocity();
        Ball ball3 = new Ball("ball3",new Circle(1.04,1,0.25), new Vect(-1,0));
        List<Ball> balls = Arrays.asList(ball1, ball2, ball3);
        Board board = new Board("test",25, .025, .025);
        board.addBall(ball1);
        
        board.performBallBallCollision(balls);
        
        assertEquals(ball2.getVelocity(), vel2Old);
        assertEquals(ball1.getVelocity(), new Vect(-1,-1));
        
    }
    
    /**
     * Test four balls colliding
     */
    @Test
    public void testPerformBallBallCollisionFourBalls(){
        //1 and 3 collide, 2 and 4 collide
        //System.out.println("TEST4");
        Ball ball1 = new Ball("ball1",new Circle(1,1,0.25), new Vect(0,1));
        Ball ball3 = new Ball("ball2",new Circle(1,1.05,0.25), new Vect(0,-1));
        
        Ball ball2 = new Ball("ball3",new Circle(5,5.0,0.25), new Vect(1,0));
        Ball ball4 = new Ball("ball4",new Circle(5.04,5.0,0.25), new Vect(-1,0));
        
        List<Ball> balls = Arrays.asList(ball1, ball2, ball3,ball4);
        Board board = new Board("test",25, .025, .025);

        board.performBallBallCollision(balls);
        
        assertEquals(new Vect(0,-1), ball1.getVelocity());
        assertEquals(new Vect(0,1), ball3.getVelocity());
        
        assertEquals(new Vect(-1,0), ball2.getVelocity());
        assertEquals(new Vect(1,0), ball4.getVelocity());
       
        
    }
    
    /**
     * test removing a ball from the board
     */
    @Test
    public void testRemoveBall(){
        Ball ball1 = new Ball("ball1", 1, 1, 0, 1, 0.25);
        Ball ball3 = new Ball("ball2",1, 1.05, 0 ,- 1, 0.25);
        
        Ball ball2 = new Ball("ball3",new Circle(5,5.0,0.25), new Vect(1,0));
        Ball ball4 = new Ball("ball4",new Circle(5.04,5.0,0.25), new Vect(-1,0));
        Board board = new Board("test",25, .025, .025);
        board.addBall(ball1);
        board.addBall(ball2);
        board.addBall(ball3);
        board.addBall(ball4);
        
        board.removeBall(ball1);
        assertTrue(board.getBalls().size()==3);
        assertFalse(board.getBalls().contains(ball1));
    }
    
    
    
    
    
}