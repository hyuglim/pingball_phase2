package PingballTests;

import static org.junit.Assert.*;

import org.junit.Test;

import physics.Circle;
import physics.Vect;
import ADT.Ball;
import ADT.Wall;

/**
 * Wall : A ball can hit its line segment and line segment's both end circles.
 *                   It has only one orientation and one state.
 *
 */
public class WallTest {
    
    /**
     * bouncing one ball off the top wall
     */
    @Test
    public void topWallTest() {
        Ball ball = new Ball("ball1",new Circle(0.71,0.5,0.25), new Vect(0,-10));
        
        Wall wall = new Wall("top", false, "", "test");
        
        wall.update(ball);
        
        assertEquals(new Vect(.71,.25), ball.circle.getCenter());
        
    }
    
    /**
     * bouncing one ball off the right wall
     */
    @Test
    public void rightWallTest() {
        Ball ball = new Ball("ball1",new Circle(16.71,5.5,0.25), new Vect(100,0));
        
        Wall wall = new Wall("right", false, "", "test");
        
        wall.update(ball);
        
        assertEquals(new Vect(19.75,5.5), ball.circle.getCenter());
        
    }
    
    /**
     * bouncing one ball off the left wall
     */
    @Test
    public void leftWallTest() {
        Ball ball = new Ball("ball1",new Circle(3.71,6.5,0.25), new Vect(-100,0));
        
        Wall wall = new Wall("left", false, "", "test");
        
        wall.update(ball);
        
        assertEquals(new Vect(0.25,6.5), ball.circle.getCenter());
        
    }
    
    /**
     * bouncing one ball off the bottom wall
     */
    @Test
    public void bottomWallTest() {
        Ball ball = new Ball("ball1",new Circle(5.,17.5,0.25), new Vect(0,100));
        
        Wall wall = new Wall("bottom", false, "", "test");
        
        wall.update(ball);
        
        assertEquals(new Vect(5.,19.75), ball.circle.getCenter());
        
    }

}
