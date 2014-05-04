package PingballTests;

import static org.junit.Assert.*;

import org.junit.Test;

import physics.Circle;
import physics.Geometry;
import physics.Vect;
import ADT.Ball;
import ADT.CircleBumper;

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
        
        assertEquals(new Vect(.71, 0.5), ball.circle.getCenter());
        assertEquals(new Vect(-1,0), ball.velocity);
    }
    
    /**
     * Ball bouncing off at an angle
     */
    @Test
    public void angleBounceTest(){
        Ball ball = new Ball("ball1",new Circle(0.71,0.5,0.25), new Vect(1,1));
        CircleBumper circlarBumper = new CircleBumper("cb", 1,0);
        
        circlarBumper.reflect(ball);
        
       
        assertEquals(-1.0, ball.velocity.x(),.0001);
        assertEquals(1.0, ball.velocity.y(), .0001);
        
    }
    
    /**
     * Ball hitting from farther away
     */
    @Test
    public void farBallTest(){
        Ball ball = new Ball("ball1",new Circle(5.71,0.5,0.25), new Vect(-50,0));
        CircleBumper circlarBumper = new CircleBumper("cb", 0,0);
        
        circlarBumper.reflect(ball);
        
        assertEquals(new Vect(5.71, .5), ball.circle.getCenter());
        assertEquals(50, ball.velocity.x(), 1);
        assertEquals(0, ball.velocity.y(), 1);
        
        
    }

}
