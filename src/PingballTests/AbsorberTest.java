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
        
        //Since the absorber is triggered by the rightflipper when a ball hits rightflipper,
        //the absorber should shoot the ball it stores upwards.
        //So that ball's velocity is not (0, 0).
        assertFalse(ball2.getVelocity().equals(new Vect(0, 0)));
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
        
        //Since the absorber is self-triggering it should shoot any ball that hits it immediately upwards.
        //So that ball's velocity is never (0, 0).
        //The ball that hit the absorber gets stored in the absorber until
        //it leaves the absorber's top wall.
        assertFalse(ball2.getVelocity().equals(new Vect(0, 0)));
        assertFalse(ball1.getVelocity().equals(new Vect(0, 0)));     
    }
    
    @Test
    public void oneBallTest() {
        Absorber abs = new Absorber("abs", 3,3,6,1);
        Ball ball = new Ball("ball",5,1, .25, 0,10);
        abs.reflect(ball);
        
        assertEquals(new Vect(8.75,3.75), ball.circle.getCenter());
        assertEquals(new Vect(0.0,0.0), ball.velocity);
        
        abs.action();
        
        assertEquals(new Vect(0, -50.0), ball.velocity);
        
    }
    
    /**
     * test absorber with two balls to make sure the second one 
     * bounces off
     */
    
    @Test
    public void twoBallTest() {
        Absorber abs = new Absorber("abs", 3,3,6,1);
        
        Ball ball = new Ball("ball", 5, 1, .25, 0,10);
        Ball ball2 = new Ball("ball2", 5, 0, .25, 0, 5);
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
    
    /**
     * test absorber with multiple balls\
     */
    @Test
    public void multipleBallTest() {
        Gadget abs = new Absorber("abs", 0,3,20,1);
        Ball ball = new Ball("ball", 5,1, .25, 0,100);
        Ball ball2 = new Ball("ball2", 5, 0, .25, 0, 50);
        Ball ball3 = new Ball("ball3",  6, 0, .25, 0, 50);
        abs.reflect(ball);
        abs.reflect(ball2);
        abs.reflect(ball3);
        
        assertEquals(new Vect(19.75,3.75), ball.circle.getCenter());
        assertEquals(new Vect(0.0,0.0), ball.velocity);
        assertEquals(new Vect(19.75,3.75), ball2.circle.getCenter());
        assertEquals(new Vect(19.75,3.75), ball3.circle.getCenter());
        
        abs.action();
        
        assertEquals(new Vect(0, -50.0), ball.velocity);
        
    }
    
}
