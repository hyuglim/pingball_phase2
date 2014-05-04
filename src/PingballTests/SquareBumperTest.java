package PingballTests;

import static org.junit.Assert.*;

import org.junit.Test;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

import ADT.*;


public class SquareBumperTest {

    
  @Test public void squareBumperCollision(){
      Ball ballA = new Ball("BallA", 10.5, 9.75, 0, 1, 0.25);
      Ball ballB = new Ball("BallB", 10.5, 11.25, 0, -1, 0.25);
      Ball ballC = new Ball("BallC", 9.75, 10.5, 1, 0, 0.25);
      Ball ballD = new Ball("BallD", 11.25, 10.5, -1, 0, 0.25);
      
      Vect newVelocityA = Geometry.reflectWall(new LineSegment(10, 10, 11, 10), ballA.getVelocity());
      Vect newVelocityB = Geometry.reflectWall(new LineSegment(10, 11, 11, 11), ballB.getVelocity());
      Vect newVelocityC = Geometry.reflectWall(new LineSegment(10, 10, 10, 11), ballC.getVelocity());
      Vect newVelocityD = Geometry.reflectWall(new LineSegment(11, 10, 11, 11), ballD.getVelocity());
      
      SquareBumper square = new SquareBumper("SquareA", 10, 10);
      square.reflect(ballA);
      square.reflect(ballB);
      square.reflect(ballC);
      square.reflect(ballD);
      
      assertEquals(newVelocityA, ballA.getVelocity());
      assertEquals(newVelocityB, ballB.getVelocity());
      assertEquals(newVelocityC, ballC.getVelocity());
      assertEquals(newVelocityD, ballD.getVelocity());
      
  }

  @Test public void squareBumperCollisionTime(){
      Ball ballA = new Ball("BallA", 10.5, 8.75, 0, 1, 0.25);
      Ball ballB = new Ball("BallB", 10.5, 12, 0, -1, 0.25);
      Ball ballC = new Ball("BallC", 9, 10.5, 1, 0, 0.25);
      Ball ballD = new Ball("BallD", 14, 10.5, -1, 0, 0.25);
      Ball ballE = new Ball("BallE", 8.5, 8.5, 1, 1, 0.25);
      Ball ballF = new Ball("BallF", 9.5, 11.5, 1, -1, 0.25);
      Ball ballG = new Ball("BallG", 11.5, 9.5, -1, 1, 0.25);
      Ball ballH = new Ball("BallH", 12.5, 12.5, -1, -1, 0.25);
      
      SquareBumper square = new SquareBumper("SquareA", 10, 10);
      
      double collisionTimeA = Geometry.timeUntilWallCollision(new LineSegment(10, 10, 11, 10), ballA.getBallCircle(), ballA.getVelocity());
      double collisionTimeB = Geometry.timeUntilWallCollision(new LineSegment(10, 11, 11, 11), ballB.getBallCircle(), ballB.getVelocity());
      double collisionTimeC = Geometry.timeUntilWallCollision(new LineSegment(10, 11, 11, 11), ballC.getBallCircle(), ballC.getVelocity());
      double collisionTimeD = Geometry.timeUntilWallCollision(new LineSegment(10, 11, 11, 11), ballD.getBallCircle(), ballD.getVelocity());
      double collisionTimeE = Geometry.timeUntilCircleCollision(new Circle(10, 10, 0), ballE.getBallCircle(), ballE.getVelocity());
      double collisionTimeF = Geometry.timeUntilCircleCollision(new Circle(10, 11, 0), ballF.getBallCircle(), ballF.getVelocity());
      double collisionTimeG = Geometry.timeUntilCircleCollision(new Circle(11, 10, 0), ballG.getBallCircle(), ballG.getVelocity());
      double collisionTimeH = Geometry.timeUntilCircleCollision(new Circle(11, 11, 0), ballH.getBallCircle(), ballH.getVelocity());
      
      assert(collisionTimeA == square.getCollisionTime(ballA));
      assert(collisionTimeB == square.getCollisionTime(ballB));
      assert(collisionTimeC == square.getCollisionTime(ballC));
      assert(collisionTimeD == square.getCollisionTime(ballD));
      assert(collisionTimeE == square.getCollisionTime(ballE));
      assert(collisionTimeF == square.getCollisionTime(ballF));
      assert(collisionTimeG == square.getCollisionTime(ballG));
      assert(collisionTimeH == square.getCollisionTime(ballH));
      
  }
  @Test
  public void ballRightStraightOnTest() {
      Ball ball = new Ball("ball1",.65,.5, 10,0, .25);
      SquareBumper bumper = new SquareBumper("sqb",1,0);
      
      bumper.reflect(ball);
      assertEquals(new Vect(-10,0), ball.velocity);
      
  }
  
  /**
   * ball coming in at an angle towards the right wall
   */
  @Test
  public void ballRightAngleTest(){
      Ball ball = new Ball("ball1",0.73, 0.0, 1,1, 0.25);
      SquareBumper bumper = new SquareBumper("sqb",1,0);
      
      bumper.reflect(ball);
      assertEquals(-1.0, ball.velocity.x(),.0001);
      assertEquals(1.0, ball.velocity.y(), .0001);
  }
  
  /**
   * straight towards left wall
   */
  @Test
  public void ballLeftStraightOnTest() {
      Ball ball = new Ball("ball1",2.65,.5, -10,0, 0.25);
      SquareBumper bumper = new SquareBumper("sqb",1,0);
      
      bumper.reflect(ball);
      assertEquals(new Vect(10,0), ball.velocity);
      
  }
  
  /**
   * ball coming in at an angle towards left wall
   */
  @Test
  public void ballLeftAngleTest(){
      Ball ball = new Ball("ball1",2.27, 0.0, -1,1, 0.25);
      SquareBumper bumper = new SquareBumper("sqb",1,0);
      
      bumper.reflect(ball);
      assertEquals(1.0, ball.velocity.x(),.0001);
      assertEquals(1.0, ball.velocity.y(), .0001);
  }
  
  /**
   * straight towards bottom wall
   */
  @Test
  public void ballBottomStraightOnTest() {
      Ball ball = new Ball("ball1",1.5,0.55, 0,10, 0.25);
      SquareBumper bumper = new SquareBumper("sqb",1,1);
      
      bumper.reflect(ball);
      assertEquals(new Vect(0,-10), ball.velocity);
      
  }
  
  /**
   * ball coming in at an angle towards bottom wall
   */
  @Test
  public void ballBottomAngleTest(){
      Ball ball = new Ball("ball1",1.0, 0.73, 1,1, 0.25);
      SquareBumper bumper = new SquareBumper("sqb",1,1);
      
      bumper.reflect(ball);
      
      assertEquals(1.0, ball.velocity.x(),.0001);
      assertEquals(-1.0, ball.velocity.y(), .0001);
  }
  
  /**
   * straight towards top wall
   */
  @Test
  public void ballTopStraightOnTest() {
      Ball ball = new Ball("ball1",1.5,2.67, 0,-10, 0.25);
      SquareBumper bumper = new SquareBumper("sqb",1,2);
      
      bumper.reflect(ball);
      
    
      assertEquals(new Vect(0,10), ball.velocity);
      
  }
  
  /**
   * ball coming in at an angle towards top wall
   */
  @Test
  public void ballTopAngleTest(){
      Ball ball = new Ball("ball1",1.00, 1.27, 1,-1, 0.25);
      SquareBumper bumper = new SquareBumper("sqb",1,1);
      
      bumper.reflect(ball);

      assertEquals(1.0, ball.velocity.x(),.0001);
      assertEquals(1.0, ball.velocity.y(), .0001);
  }
  
  /**
   * ball coming very fast. Should hit closer wall
   */
  @Test
  public void fastBall1Test(){
      Ball ball = new Ball("ball1",2.75, 0.5, -100,0, 0.25);
      SquareBumper bumper = new SquareBumper("sqb",1,0);
      
      bumper.reflect(ball);
      
      assertEquals(0.5, ball.circle.getCenter().y(), .0001);
      assertEquals(100.0, ball.velocity.x(),.0001);
      assertEquals(0.0, ball.velocity.y(), .0001);
  }
  /*
   * Testing when a ball hits squareBumper at corners:
   * - nothing weird should happen
   * - the position shouldn't change (it's done in Board)
   * - velocity MUST change
   */
  /**
   * Test hitting from the upper right corner
   */
  @Test
  public void upperRightCornerTest(){
      Ball ball = new Ball("ball1",3.,2., -17,0, 0.25);
      SquareBumper bumper = new SquareBumper("sqb", 0,2);
      
      bumper.reflect(ball);
      
      assertEquals(3., ball.circle.getCenter().x(), .01);
      assertEquals(2., ball.circle.getCenter().y(), .01);
      assertFalse(ball.getVelocity().equals(new Vect(0,-17)));
  }
  
  /**
   * Test hitting the lower right corner
   */
  @Test
  public void lowerRightCornerTest(){
      Ball ball = new Ball("ball1",5.,3., -17,0, 0.25);
      SquareBumper bumper = new SquareBumper("sqb",0,2);
      
      bumper.reflect(ball);
      
      assertEquals(5, ball.circle.getCenter().x(),.01);
      assertEquals(3., ball.circle.getCenter().y(), .01);
      assertFalse(ball.getVelocity().equals(new Vect(0,-17)));
  }
  
  /**
   * Test hitting the lower left corner
   */
  @Test
  public void lowerLeftCornerTest(){
      Ball ball = new Ball("ball1",new Circle(0.,5.,.25), new Vect(0,-17));
      Gadget bumper = new SquareBumper("sqb",0,2);
      
      bumper.reflect(ball);
      
      assertEquals(0., ball.circle.getCenter().x(),.01);
      assertEquals(5., ball.circle.getCenter().y(), .01);
      assertFalse(ball.getVelocity().equals(new Vect(0,-17)));
  }
  
  /**
   * Test hitting the upper left corner
   */
  @Test
  public void upperLeftCornerTest(){
      Ball ball = new Ball("ball1",new Circle(0.,0.,.25), new Vect(0,17));
      Gadget bumper = new SquareBumper("sqb", 0,2);

      bumper.reflect(ball);
      
      assertEquals(0., ball.circle.getCenter().x(),.01);
      assertEquals(0, ball.circle.getCenter().y(), .01);
      assertFalse(ball.getVelocity().equals(new Vect(0,17)));
  }

  
  

}
