package PingballTests;

import static org.junit.Assert.*;


import org.junit.Test;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import ADT.Gadget;
import ADT.Absorber;
import ADT.Ball;
import ADT.Board;
import ADT.CircleBumper;
import ADT.LeftFlipper;
import ADT.RightFlipper;
/**
 * Flippers : A ball can hit each of its wall and both ends which are circles.
 *                   It has 4 orientations and 2 state.
 *
 */
public class FlippersTest {
    /**
     * Testing construction of all kinds of leftFlipper and rightFlipper
     * - once hit by the ball, must change orientation:
     *   vertical to horizontal and vice versa
     *   
     * 
     */
    @Test public void leftFlipperToString(){
        LeftFlipper leftFlipperOrientation0 = new LeftFlipper("LeftA", 5, 5, 0);
        LeftFlipper leftFlipperOrientation90 = new LeftFlipper("LeftB", 5, 5, 90);
        LeftFlipper leftFlipperOrientation180 = new LeftFlipper("LeftC", 5, 5, 180);
        LeftFlipper leftFlipperOrientation270 = new LeftFlipper("LeftD", 5, 5, 270);
        
        assertEquals(leftFlipperOrientation0.toString(), "| | ");
        assertEquals(leftFlipperOrientation90.toString(), "--  ");
        assertEquals(leftFlipperOrientation180.toString(), " | |");
        assertEquals(leftFlipperOrientation270.toString(), "  --");
        
        Ball ball = new Ball("ballA", 5, 4, 0, 1, 0.25);
        leftFlipperOrientation0.isTriggered(leftFlipperOrientation0);
        leftFlipperOrientation90.isTriggered(leftFlipperOrientation0);
        leftFlipperOrientation180.isTriggered(leftFlipperOrientation0);
        leftFlipperOrientation270.isTriggered(leftFlipperOrientation0);
        
        leftFlipperOrientation0.reflect(ball);
        
        assertEquals(leftFlipperOrientation0.toString(), "--  ");
        assertEquals(leftFlipperOrientation90.toString(), " | |");
        assertEquals(leftFlipperOrientation180.toString(), "  --");
        assertEquals(leftFlipperOrientation270.toString(), "| | ");
        
        leftFlipperOrientation0.reflect(ball);
        
        assertEquals(leftFlipperOrientation0.toString(), "| | ");
        assertEquals(leftFlipperOrientation90.toString(), "--  ");
        assertEquals(leftFlipperOrientation180.toString(), " | |");
        assertEquals(leftFlipperOrientation270.toString(), "  --");
    }
    
    @Test public void rightFlipperToString(){
        RightFlipper rightFlipperOrientation0 = new RightFlipper("RightA", 5, 5, 0);
        RightFlipper rightFlipperOrientation90 = new RightFlipper("RightB", 5, 5, 90);
        RightFlipper rightFlipperOrientation180 = new RightFlipper("RightC", 5, 5, 180);
        RightFlipper rightFlipperOrientation270 = new RightFlipper("RightD", 5, 5, 270);
        
        assertEquals(rightFlipperOrientation0.toString(), " | |");
        assertEquals(rightFlipperOrientation90.toString(), "  --");
        assertEquals(rightFlipperOrientation180.toString(), "| | ");
        assertEquals(rightFlipperOrientation270.toString(), "--  ");

        Ball ball = new Ball("ballA", 1, 4, 0, 1, 0.25);
        CircleBumper circleBumper = new CircleBumper("A", 10, 10);
        rightFlipperOrientation0.isTriggered(circleBumper);
        rightFlipperOrientation90.isTriggered(circleBumper);
        rightFlipperOrientation180.isTriggered(circleBumper);
        rightFlipperOrientation270.isTriggered(circleBumper);
        
        circleBumper.reflect(ball);
        
        assertEquals(rightFlipperOrientation0.toString(), "--  ");
        assertEquals(rightFlipperOrientation90.toString(), " | |");
        assertEquals(rightFlipperOrientation180.toString(), "  --");
        assertEquals(rightFlipperOrientation270.toString(), "| | ");
        
        circleBumper.reflect(ball);

        assertEquals(rightFlipperOrientation0.toString(), " | |");
        assertEquals(rightFlipperOrientation90.toString(), "  --");
        assertEquals(rightFlipperOrientation180.toString(), "| | ");
        assertEquals(rightFlipperOrientation270.toString(), "--  ");
    }

   


    /**
     * Testing that the ball hitting the right Line Segment and appropriately changes
     * the velocity
     */
    @Test public void flipperCollision(){
        Ball ballA = new Ball("BallA", 9, 11, 1, 0, 0.25);
        Ball ballB = new Ball("BallB", 9, 10, 0, 1, 0.25);
        Ball ballC = new Ball("BallC", 9, 12, 1, 0, 0.25);
        LeftFlipper leftFlipper = new LeftFlipper("LeftA", 10, 10, 0);
        leftFlipper.isTriggered(leftFlipper);
        
        Vect newVelocityA = Geometry.reflectRotatingWall(new LineSegment(10, 10, 10, 12), new Vect(10, 10), Math.PI*6, ballA.getBallCircle(), ballA.getVelocity());
        Vect newVelocityB = Geometry.reflectCircle(new Circle(10, 10, 0).getCenter(), ballB.getBallCircle().getCenter(), ballB.getVelocity());
        Vect newVelocityC = Geometry.reflectWall(new LineSegment(10, 10, 10, 11), ballC.getVelocity());
        
        leftFlipper.reflect(ballA);
        leftFlipper.reflect(ballB);
        leftFlipper.reflect(ballC);

        assertEquals(newVelocityA.times(0.95), ballA.getVelocity());
        assertEquals(newVelocityB.times(0.95), ballB.getVelocity());
        assertEquals(newVelocityC.times(0.95), ballC.getVelocity());
        
    }
    
    @Test public void flipperNonSelfTrigger() {
        Board board = new Board("Board1", 25.0, 0.025, 0.025);

        RightFlipper rightFlipper = new RightFlipper("FlipR", 4, 5, 0);
        LeftFlipper leftFlipper = new LeftFlipper("FlipL", 10, 10, 0);
        board.addGadget(rightFlipper);
        board.addGadget(leftFlipper);
        
        Ball ball1 = new Ball("Ball1", 3, 3, 1, 2, 0.25);
        Ball ball2 = new Ball("Ball2", 2, 2, 0, 1,0.25);
        board.addBall(ball1);
        board.addBall(ball2);
        
        rightFlipper.isTriggered(leftFlipper);
        rightFlipper.reflect(ball1);
        assertEquals(" | |", rightFlipper.toString());
        
        leftFlipper.reflect(ball2);
        assertEquals("--  ", rightFlipper.toString());
        assertEquals("| | ", leftFlipper.toString());
    }
    
    @Test public void flipperSelfTrigger() {
        Board board = new Board("Board1", 25.0, 0.025, 0.025);
        Absorber absorber = new Absorber("Abs", 0, 19, 20, 1);
        board.addGadget(absorber);
        
        RightFlipper rightFlipper = new RightFlipper("FlipR", 4, 5, 0);
        board.addGadget(rightFlipper);
        
        Ball ball1 = new Ball("Ball1", 3, 3, 1, 2, 0.25);
        Ball ball2 = new Ball("Ball2", 2, 2, 0, 1, 0.25);
        board.addBall(ball1);
        board.addBall(ball2);
        
        rightFlipper.isTriggered(rightFlipper);
        rightFlipper.reflect(ball1);
        assertEquals("--  ", rightFlipper.toString());
    }

    
   
}
