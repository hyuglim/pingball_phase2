package PingballTests;

import static org.junit.Assert.*;

import org.junit.Test;

import physics.Vect;
import ADT.Ball;
import ADT.Board;
import ADT.Portal;
/**
 * Testing strategy:
 * With current specifications the portal connects to the other portal 
 * (in the same or a different board). Teleported ball must retain its velocity. but
 * its position is changed to be the center of the portal.
 * If the other portal or board is non-existent, ball should just pass the portal as
 * it was an empty space.
 * Tests:
 * - test when a portal is connected to the portal within the same board.
 * - test when a portal is connected to the portal within the other board
 * - test when a portal is connected to the nonexistent portal within the same board
 * 
 * Manual tests (as the server must check whether there is a second board):  
 * - test when a portal is connected to the nonexistent portal within the other board
 * - test when a portal is connected to the nonexistent board
 
 *
 */
public class PortalTest {

    @Test
    public void portalTestSameBoardExists() {
        Board b = new Board("A", 25,.25,.25);
        Portal p1 = new Portal("P1", 15, 15, "", "P2");
        Portal p2 = new Portal("P2", 4, 4, "", "ignore");
        b.addGadget(p1);
        b.addGadget(p2);
        Ball ball = new Ball("b", 15,14.9, 0, 10, .25);
        b.addBall(ball);
        b.update();
        assertEquals(ball.getBallCircle().getCenter(), new Vect(4.5,4.5));
        assertEquals(ball.getVelocity(), new Vect(0,10));
        
    }
    /**
     * If the portal is connected to the portal on the other board,
     * the board should prepare a message for the server/communicator
     */
    @Test
    public void portalTestOtherBoardExists() {
        Board b1 = new Board("A", 25,.25,.25);
        Board b2 = new Board("B", 25,.25,.25);
        Portal p1 = new Portal("P1", 15, 15, "B", "P2");
        Portal p2 = new Portal("P2", 4, 4, "", "ignore");
        b1.addGadget(p1);
        b2.addGadget(p2);
        Ball ball = new Ball("b", 15,14.9, 0, 10, .25);
        b1.addBall(ball);
        b1.update();
        System.out.println(b1.getBalls().size());
        assertTrue(b1.whichPortalGotHit().length()!=0);
        
    }
    /**
     * If the portal is connected to the non-existent portal on the same board,
     * the ball would behave as it was an empty space
     */
    @Test
    public void portalTestSameBoardDoesNotExist() {
        Board b = new Board("A", 25,.25,.25);
        Portal p1 = new Portal("P1", 15, 15, "", "P2");
        b.addGadget(p1);

        Ball ball = new Ball("b", 15,14.9, 0, 10, .25);
        b.addBall(ball);
        b.update();
        
        Board b1 = new Board("A'", 25,.25,.25);
      
        Ball ball1 = new Ball("b'", 15,14.9, 0, 10, .25);
        b1.addBall(ball1);
        b1.update();
        
        assertEquals(ball.getBallCircle().getCenter(), ball1.getBallCircle().getCenter());
        assertEquals(ball.getVelocity(), ball1.getVelocity());
    }
   

}
