package PingballTests;

import static org.junit.Assert.*; 

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
import physics.LineSegment;
import physics.Vect;

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
 * - Test to check if the board prints its neighbor name correctly. 
 * 
 * - Test ball collision:
 *   A ball can hit either a gadget or another ball. 
 *   
 * - Tests to make sure Each Gadget is reflecting properly when a ball
 *   hits it in its different orientations and states, or when it is triggered:
 *      Check that the collision time until the ball hits the gadget is correct
 *      /In other words, check that each gadget has the right line segments and circles and
 *      make sure that the ball's collision time until the nearest line segment or 
 *      circle is calculated correctly./
 *      
 *      SquareBumper :  A ball can hit each of its 4 walls and 4 corner circles. 
 *                      It has only one orientation and one state.
 *      CircleBumper :  A ball can hit its circle. It has only one orientation and one state.
 *      TriangularBumper : A ball can hit each of its 3 walls and 3 corner circles. 
 *                         It has 4 orientations and 1 state.
 *      Absorber : A ball can hit each of its 4 walls and 4 corner circles. 
 *                   It has only one orientation and one state.
 *      Both flippers : A ball can hit each of its wall and both ends which are circles.
 *                   It has 4 orientations and 2 state.
 *      Wall : A ball can hit its line segment and line segment's both end circles.
 *                   It has only one orientation and one state.
 *      
 * - Test when there are multiple gadgets, the board is calculating the correct collision time
 *      until the ball hits the nearest gadget in the board. /check board getCollisonTime() method/
 *    
 * - Tests for trigger relationships:
 *   
 *   A gadget can be triggered by a single gadget /Triggering gadget could be either
 *   the gadget itself /self-triggering/, or a different gadget./
 *   
 *   A gadget can be triggered by multiple gadgets. /It could include the gadget itself or not./
 *   
 *   A gadget can trigger a single gadget or multiple gadgets. 
 *   
 *   When a gadget is an absorber:
 *      When the previously ejected ball has not yet left the absorber, the absorber should 
 *      take no action when it is triggered. 
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
    
    @Test(expected = RuntimeException.class)
    public void testImproperFilesWithNonMatchingContent() throws Exception{
        new Ball("IllegalBall",0 , 0, 200, 100, 0.25);
    }
    
}