package PingballTests;

import static org.junit.Assert.*;

import org.junit.Test;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import ADT.Ball;
import ADT.TriangularBumper;
/**
 * Testing strategy:
 * With current specifications we can have four types of triangular bumpers, each
 * differ from other by its orientation. Bumpers are represented by 3 line segments and
 * three circles at corners. When a ball hits the bumper, only its velocity should
 * change, the position is changed within the Board update method.
 * 
 * Tests:
 * - check when ball approaches the bumper, it must hit it
 *   and properly change the velocity
 * - check if the ball hits the right line segment
 * - check when ball hits all 4 types of bumpers (must change the velocity)
 * - check when ball hits the corners (must change the velocity, nothing weird should happen)
 *
 */
public class TriangularBumperTest {

    @Test public void triangularBumperCollision(){
        Ball ballA = new Ball("BallA", 10.5, 9.75, 0, 1, 0.25);
        Ball ballB = new Ball("BallB", 9.75, 10.5, 1, 0, 0.25);
        
        Vect newVelocityA = Geometry.reflectWall(new LineSegment(10, 10, 11, 10), ballA.getVelocity());
        Vect newVelocityB = Geometry.reflectWall(new LineSegment(10, 10, 10, 11), ballB.getVelocity());
        
        TriangularBumper triangle = new TriangularBumper("triangleA", 10, 10, 0);
        triangle.reflect(ballA);
        triangle.reflect(ballB);
                
        assertEquals(newVelocityA, ballA.getVelocity());
        assertEquals(newVelocityB, ballB.getVelocity());
        
    }
    
    @Test public void triangularBumperCollisionTime(){
        Ball ballA = new Ball("BallA", 10.5, 9.75, 0, 1, 0.25);
        Ball ballB = new Ball("BallB", 9.75, 10.5, 1, 0, 0.25);
        
        double collisionTimeA = Geometry.timeUntilWallCollision(new LineSegment(10, 10, 11, 10), ballA.getBallCircle(), ballA.getVelocity());
        double collisionTimeB = Geometry.timeUntilWallCollision(new LineSegment(10, 11, 11, 11), ballB.getBallCircle(), ballB.getVelocity());
        
        TriangularBumper triangle = new TriangularBumper("triangleA", 10, 10, 0);
        
        assert(collisionTimeA == triangle.getCollisionTime(ballA));
        assert(collisionTimeB == triangle.getCollisionTime(ballB));
        
    }
    @Test
    public void firstOrientationTest() {
        Ball ball = new Ball("ball1",new Circle(2.75,2.5,.25), new Vect(-17,0));
        TriangularBumper bumper = new TriangularBumper("tr", 0,2,0);
        
        bumper.reflect(ball);
        assertEquals(new Vect(0, 17), ball.getVelocity() );
    }
    
    /**
     * Ball hits 90 oriented triangle
     */
    @Test
    public void secondOrientationTest() {
        Ball ball = new Ball("ball1",new Circle(.9,2.5,.25), new Vect(16,0));
        TriangularBumper bumper = new TriangularBumper("tr",1,2,90);
        
        bumper.reflect(ball);
        
        assertEquals(new Vect(0, 16), ball.getVelocity() );
    }
    
    /**
     * Ball hits 180 oriented triangle
     */
    @Test
    public void thirdOrientationTest() {
        Ball ball = new Ball("ball1",new Circle(.25,1.5,.25), new Vect(17,0));
        TriangularBumper bumper = new TriangularBumper("tr", 1,1,180);
        
        bumper.reflect(ball);
        
        
        assertEquals(new Vect(0,17), ball.getVelocity() );
    }
    
    /**
     * ball hits 270 oriented triangle
     */
    @Test
    public void fourthOrientationTest() {
        Ball ball = new Ball("ball1",new Circle(1.05,2.5,.25), new Vect(-17,0));
        TriangularBumper bumper = new TriangularBumper("tr", 0,2,270);
        
        bumper.reflect(ball);
        
        
        assertEquals(new Vect(0, -17), ball.getVelocity() );
    }
    
    /**
     * Test hitting from the upper right corner
     */
    @Test
    public void upperRightCornerTest(){
        Ball ball = new Ball("ball1",new Circle(3.,2.,.25), new Vect(-17,0));
        TriangularBumper bumper = new TriangularBumper("tr", 0,2,0);
        
        bumper.reflect(ball);
        
        assertEquals(new Vect(3.,2), ball.getBallCircle().getCenter());
        assertEquals(new Vect(17,0), ball.getVelocity());
    }
    
    /**
     * Test hitting the lower right corner
     */
    @Test
    public void lowerRightCornerTest(){
        Ball ball = new Ball("ball1",new Circle(5.,3.,.25), new Vect(-17,0));
        TriangularBumper bumper = new TriangularBumper("tr",0,2,180);
        
        bumper.reflect(ball);
        
        assertEquals(new Vect(5.,3), ball.getBallCircle().getCenter());
        assertEquals(new Vect(17,0), ball.getVelocity());
    }
    
    /**
     * Test hitting the lower left corner
     */
    @Test
    public void lowerLeftCornerTest(){
        Ball ball = new Ball("ball1",new Circle(0.,5.,.25), new Vect(0,-17));
        TriangularBumper bumper = new TriangularBumper("tr", 0,2,0);
        
        bumper.reflect(ball);
        
        assertEquals(new Vect(0.,5), ball.getBallCircle().getCenter());
        assertEquals(new Vect(0,17), ball.getVelocity());
    }
    
    /**
     * Test hitting the upper left corner
     */
    @Test
    public void upperLeftCornerTest(){
        Ball ball = new Ball("ball1",new Circle(0.,0.,.25), new Vect(0,17));
        TriangularBumper bumper = new TriangularBumper("tr", 0,1,0);
        
        bumper.reflect(ball);
        
        assertEquals(new Vect(0.,0), ball.getBallCircle().getCenter());
        assertEquals(new Vect(0,-17), ball.getVelocity());
    }



}
