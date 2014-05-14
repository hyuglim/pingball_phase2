package PingballTests;

import static org.junit.Assert.*;

import org.junit.Test;

import physics.Vect;
import ADT.Ball;
import ADT.Board;
import ADT.LeftFlipper;
import ADT.Portal;
import ADT.RightFlipper;
/**
 * Testing strategy:
 * Manual tests:
 * - Manually run the board game and check if the gadgets are correctly triggered by their
 *  key up and down triggers. /Check to see if the gadget is actually triggered when the 
 *  key is released or pressed./
 *
 */
public class KeyTriggerTests {

    @Test
    public void keyUpTriggers(){
        Board myBoard = new Board("Board", 20, 0.25, 0.25);
        RightFlipper rightFlipper = new  RightFlipper("RFlip1", 5, 5, 0);
        
        assertEquals(rightFlipper.toString(), " | |");
        rightFlipper.addKeyUp("ctrl");
        rightFlipper.addKeyUp("shift");
        rightFlipper.addKeyUp("alt");
        rightFlipper.addKeyUp("meta");
        rightFlipper.addKeyUp("space");
        rightFlipper.addKeyUp("left");
        rightFlipper.addKeyUp("right");
        rightFlipper.addKeyUp("up");
        rightFlipper.addKeyUp("down");
        rightFlipper.addKeyUp("minus");
        rightFlipper.addKeyUp("equals");
        rightFlipper.addKeyUp("backslash");        
        rightFlipper.addKeyUp("semicolon");
        rightFlipper.addKeyUp("quote");
        rightFlipper.addKeyUp("enter");
        rightFlipper.addKeyUp("comma");
        rightFlipper.addKeyUp("period");
        rightFlipper.addKeyUp("slash");        
        rightFlipper.addKeyUp("z");
        rightFlipper.addKeyUp("9");
        myBoard.addGadget(rightFlipper);
        myBoard.triggerUpKey("ctrl");
        assertEquals(rightFlipper.toString(), "--  ");
        myBoard.triggerUpKey("shift");
        assertEquals(rightFlipper.toString(), " | |");
        
        myBoard.triggerUpKey("alt");
        myBoard.triggerUpKey("meta");
        myBoard.triggerUpKey("space");
        myBoard.triggerUpKey("left");
        myBoard.triggerUpKey("right");
        myBoard.triggerUpKey("up");
        rightFlipper.addKeyUp("down");
        rightFlipper.addKeyUp("minus");
        rightFlipper.addKeyUp("equals");
        rightFlipper.addKeyUp("backslash");        
        rightFlipper.addKeyUp("semicolon");
        rightFlipper.addKeyUp("quote");
        rightFlipper.addKeyUp("enter");
        rightFlipper.addKeyUp("comma");
        rightFlipper.addKeyUp("period");
        rightFlipper.addKeyUp("slash");        
        rightFlipper.addKeyUp("z");
        rightFlipper.addKeyUp("9");     
    }
    /**
     * If the portal is connected to the portal on the other board,
     * the board should prepare a message for the server/communicator
     */
    @Test
    public void keyUpTriggerAlphabets() {
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
    public void keyUpTriggerDigits() {
        Board myBoard = new Board("Board", 20, 0.25, 0.25);
        RightFlipper rightFlipper = new  RightFlipper("RFlip1", 5, 5, 0);
        
        assertEquals(rightFlipper.toString(), " | |");
        rightFlipper.addKeyUp("0");
        rightFlipper.addKeyUp("1");
        rightFlipper.addKeyUp("2");
        rightFlipper.addKeyUp("3");
        rightFlipper.addKeyUp("4");
        rightFlipper.addKeyUp("5");
        rightFlipper.addKeyUp("6");
        rightFlipper.addKeyUp("7");
        rightFlipper.addKeyUp("8");
        rightFlipper.addKeyUp("9");
        
        
    }
   

}
