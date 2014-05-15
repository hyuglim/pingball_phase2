package PingballTests;

import static org.junit.Assert.*;

import org.junit.Test;

import physics.Circle;
import physics.Vect;
import ADT.Gadget;
import ADT.Absorber;
import ADT.Ball;
import ADT.Board;
import ADT.RightFlipper;

/**
 * Testing strategy: 
 * With current specifications an absorber captures all balls
 * which hit it and when triggered, releases the ball with the upward directed
 * velocity (0,-50). Absorber can be self triggering and non-self triggering.
 * 
 * Tests: 
 * - non-self triggering absorber (when triggered releases only first
 * ball which got in) 
 * - self triggering absorber (releases a ball once it got
 * in) 
 * - absorber must keep the ball in the upper right corner with zero
 * velocity 
 * - if two or more balls got in and he absorber is triggered, only the
 * first ball gets shot out
 */
public class AbsorberTest {
    
    @Test public void absorberNonSelfTrigger() {
        Board board = new Board("Board1", 25.0, 0.025, 0.025);
        Absorber absorber = new Absorber("Abs", 0, 19, 20, 1);
        board.addGadget(absorber);
        
        RightFlipper rightFlipper = new RightFlipper("FlipR", 4, 5, 0);
        board.addGadget(rightFlipper);
        
        Ball ball1 = new Ball("Ball1", 3, 3, 1, 2, 0.25);
        Ball ball2 = new Ball("Ball2", 2, 2, 0, 1, 0.25);
        board.addBall(ball1);
        board.addBall(ball2);
        
        absorber.isTriggered(rightFlipper);
        absorber.reflect(ball2);
        rightFlipper.reflect(ball1);
        assertTrue(ball2.getVelocity().equals(new Vect(0, -50)));
    }
    
    @Test public void absorberSelfTrigger() {
        Board board = new Board("Board1", 25.0, 0.025, 0.025);
        Absorber absorber = new Absorber("Abs", 0, 19, 20, 1);
        board.addGadget(absorber);
        
        Ball ball2 = new Ball("Ball2", 2, 2, 0, 1, 0.25);
        board.addBall(ball2);
        
        absorber.isTriggered(absorber);
        absorber.reflect(ball2);
        
        Ball ball1 = new Ball("Ball1", 3, 3, 0, 1, 0.25);
        board.addBall(ball1);
        absorber.reflect(ball1);
       
        assertTrue(ball2.getVelocity().equals(new Vect(0, -50)));
        assertTrue(ball1.getVelocity().equals(new Vect(0, -50)));     
    }
    
    @Test
    public void oneBallTest() {
        Absorber abs = new Absorber("abs", 3,3,6,1);
        Ball ball = new Ball("ball",5,1,0,10,  .25);
        abs.reflect(ball);
        
        assertEquals(new Vect(8.75,3.75), ball.getBallCircle().getCenter());
        assertEquals(new Vect(0.0,0.0), ball.getVelocity());
        
    }
    

    @Test
    public void twoBallTest() {
        Absorber abs = new Absorber("abs", 3,3,6,1);
        
        Ball ball = new Ball("ball", 5, 1, 0,10, .25);
        Ball ball2 = new Ball("ball2", 5, 0, 0, 5, .25);
        abs.reflect(ball);
        abs.reflect(ball2);
        
        assertEquals(new Vect(8.75,3.75), ball.getBallCircle().getCenter());
        //second ball must be caught
        assertEquals(new Vect(8.75,3.75), ball2.getBallCircle().getCenter());
        
        abs.action();
        //only first ball gets released
        assertEquals(new Vect(0, -50.0), ball.getVelocity());
        assertEquals(new Vect(0, 0.0), ball2.getVelocity());
        
    }
    
    @Test
    public void multipleBallTest() {
        Gadget abs = new Absorber("abs", 0,3,20,1);
        Ball ball = new Ball("ball", 5,1, 0,100, .25);
        Ball ball2 = new Ball("ball2", 5, 0, 0, 50, .25);
        Ball ball3 = new Ball("ball3",  6, 0, 0, 50, .25);
        abs.reflect(ball);
        abs.reflect(ball2);
        abs.reflect(ball3);
        
        assertEquals(new Vect(19.75,3.75), ball.getBallCircle().getCenter());
        assertEquals(new Vect(0.0,0.0), ball.getVelocity());
        assertEquals(new Vect(19.75,3.75), ball2.getBallCircle().getCenter());
        assertEquals(new Vect(19.75,3.75), ball3.getBallCircle().getCenter());
        
        abs.action();
        assertEquals(new Vect(0, -50.0), ball.getVelocity());
        
    }
    
}
