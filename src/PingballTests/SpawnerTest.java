package PingballTests;

import static org.junit.Assert.*;

import org.junit.Test;

import physics.Circle;
import physics.Geometry;
import physics.Vect;
import ADT.Ball;
import ADT.Spawner;
/**
* Testing strategy: 
* With current specifications we can have one type of
* spawner. Spawners are represented by a circle. When a ball hits the
* spawner, it either gets larger or smaller, which is related to its current radius,
* it's velocity changes as if it hit circular object.
* Board update method.
* 
* Tests: 
* - check if the ball changes the velocity
* - check if the ball gets larger (r = .5) when its radius <=.25
* - check if the ball gets smaller (r = 2.5) when its radius is <=.5
* - check if the ball halves in size when its radius is >.5
* - check if the ball calculates the time of collision with spawner
*   as  with collision with a circular object
*/
public class SpawnerTest {
    
    
    @Test
    public void testVelocityChanged() {
        Vect oldVel  = new Vect(5,5);
        Ball ball = new Ball("b", new Circle(3.9,3.9,.25), oldVel);
        Vect vel = Geometry.reflectCircle(new Circle(4.5, 4.5,.5).getCenter(), ball.getBallCircle().getCenter(), ball.getVelocity());
        Spawner spawner = new Spawner("s", 4,4);
        spawner.reflect(ball);
        Vect newVel = ball.getVelocity();
        
        assertEquals(vel, newVel);
        
        
    }
    @Test
    public void testSmallBallGetsLarger(){
        Vect oldVel  = new Vect(5,5);
        Ball ball = new Ball("b", new Circle(3.9,3.9,.1), oldVel);
        Spawner spawner = new Spawner("s", 4,4);
        spawner.reflect(ball);
        assertTrue(ball.getRadius()== .5);
    }
    @Test
    public void testLargerBallGetsSmallerer(){
        Vect oldVel  = new Vect(5,5);
        Ball ball = new Ball("b", new Circle(3.9,3.9,.4), oldVel);
        Spawner spawner = new Spawner("s", 4,4);
        spawner.reflect(ball);
        assertTrue(ball.getRadius()== .25);
    }
    @Test
    public void testLargeBallHalves(){
        Vect oldVel  = new Vect(5,5);
        double r = 1;
        Ball ball = new Ball("b", new Circle(3.9,3.9,r), oldVel);
        Spawner spawner = new Spawner("s", 4,4);
        spawner.reflect(ball);
        assertTrue(ball.getRadius()== r/2);
    }
    @Test
    public void testTimeToCollision() {
        Vect oldVel  = new Vect(5,5);
        Ball ball = new Ball("b", new Circle(3.0,3.0,.25), oldVel);
        Spawner spawner = new Spawner("s", 4,4);
        double perfectTime = Geometry.timeUntilCircleCollision(new Circle(4.5, 4.5,.5), ball.getBallCircle(), ball.getVelocity());
        double actualTime = spawner.getCollisionTime(ball);
      
        assertTrue(perfectTime==actualTime);
        
        
    }
    

}
