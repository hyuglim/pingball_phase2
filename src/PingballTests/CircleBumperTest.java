package PingballTests;

import static org.junit.Assert.*;

import org.junit.Test;

import physics.Circle;
import physics.Geometry;
import physics.Vect;
import ADT.Ball;
import ADT.CircleBumper;

/**
 * Testing strategy: 
 * With current specifications we can have one type of
 * circular bumpers. Bumpers are represented by a circle. When a ball hits the
 * bumper, only its velocity should change, the position is changed within the
 * Board update method.
 * 
 * Tests: 
 * - check when ball approaches the bumper, it must hit it and properly
 * change the velocity 
 * - check when ball hits a bumper straight on and at an angle
 * 
 */
public class CircleBumperTest {

    @Test public void circleBumperCollision(){
        Ball ball = new Ball("BallA", 0.5, 2.75, 0, 1, 0.25);
        CircleBumper circleBumper = new CircleBumper("CircleA", 0, 3);
        Vect newVelocity = Geometry.reflectCircle(circleBumper.getCircle().getCenter(), ball.getBallCircle().getCenter(), ball.getVelocity());
        circleBumper.reflect(ball);
        assertEquals(newVelocity, ball.getVelocity());
        
    }
    
    @Test public void circleBumperCollisionTime(){
        Ball ball = new Ball("BallA", 0.5, 0.5, 0, 1, 0.25);
        CircleBumper circleBumper = new CircleBumper("CircleA", 0, 3);
        double collisionTime = Geometry.timeUntilCircleCollision(circleBumper.getCircle(), ball.getBallCircle(), ball.getVelocity());
        assert(collisionTime == circleBumper.getCollisionTime(ball));     
    }
    
    @Test
    public void straightBounceTest() {
        Ball ball = new Ball("ball1",new Circle(0.71,0.5,0.25), new Vect(1,0));
        CircleBumper circlarBumper = new CircleBumper("cb", 1,0);
        
        circlarBumper.reflect(ball);
        
        assertEquals(new Vect(.71, 0.5), ball.getBallCircle().getCenter());
        assertEquals(new Vect(-1,0), ball.getVelocity());
    }
    
    /**
     * Ball bouncing off at an angle
     */
    @Test
    public void angleBounceTest(){
        Ball ball = new Ball("ball1",new Circle(0.71,0.5,0.25), new Vect(1,1));
        CircleBumper circlarBumper = new CircleBumper("cb", 1,0);
        
        circlarBumper.reflect(ball);
        
        assertEquals(new Vect(0.71, .5), ball.getBallCircle().getCenter());
        assertEquals(-1.0, ball.getVelocity().x(),.0001);
        assertEquals(1.0, ball.getVelocity().y(), .0001);
        
    }
    
    /**
     * Ball hitting from farther away
     */
    @Test
    public void farBallTest(){
        Ball ball = new Ball("ball1",new Circle(5.71,0.5,0.25), new Vect(-50,0));
        CircleBumper circlarBumper = new CircleBumper("cb", 0,0);
        
        circlarBumper.reflect(ball);
        
        assertEquals(new Vect(5.71, .5), ball.getBallCircle().getCenter());
        assertEquals(50, ball.getVelocity().x(), 1);
        assertEquals(0, ball.getVelocity().y(), 1);
        
        
    }

}
