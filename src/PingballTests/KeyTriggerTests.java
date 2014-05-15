package PingballTests;

import static org.junit.Assert.*; 

import org.junit.Test;
import ADT.Board;
import ADT.LeftFlipper;
import ADT.RightFlipper;
/**
 * Testing strategy:
 * 
 * - Test whether the up and down key triggers all trigger the gadgets they trigger.
 * - Test whether all the key Strings t
 * 
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
        assertEquals(rightFlipper.toString(), "--  ");
        myBoard.triggerUpKey("meta");
        assertEquals(rightFlipper.toString(), " | |");
        myBoard.triggerUpKey("space");
        assertEquals(rightFlipper.toString(), "--  ");
        myBoard.triggerUpKey("left");
        assertEquals(rightFlipper.toString(), " | |");
        myBoard.triggerUpKey("right");
        assertEquals(rightFlipper.toString(), "--  ");
        myBoard.triggerUpKey("up");
        assertEquals(rightFlipper.toString(), " | |");
        myBoard.triggerUpKey("down");
        assertEquals(rightFlipper.toString(), "--  ");
        myBoard.triggerUpKey("minus");
        assertEquals(rightFlipper.toString(), " | |");       
        myBoard.triggerUpKey("equals");
        assertEquals(rightFlipper.toString(), "--  ");
        myBoard.triggerUpKey("backslash"); 
        assertEquals(rightFlipper.toString(), " | |");
        myBoard.triggerUpKey("semicolon");
        assertEquals(rightFlipper.toString(), "--  ");
        myBoard.triggerUpKey("quote");
        assertEquals(rightFlipper.toString(), " | |");
        myBoard.triggerUpKey("enter");
        assertEquals(rightFlipper.toString(), "--  ");
        myBoard.triggerUpKey("comma");
        assertEquals(rightFlipper.toString(), " | |");
        myBoard.triggerUpKey("period");
        assertEquals(rightFlipper.toString(), "--  ");
        myBoard.triggerUpKey("slash");   
        assertEquals(rightFlipper.toString(), " | |");
        myBoard.triggerUpKey("z");
        assertEquals(rightFlipper.toString(), "--  ");
        myBoard.triggerUpKey("9");     
        assertEquals(rightFlipper.toString(), " | |");
    }
    /**
     * If the portal is connected to the portal on the other board,
     * the board should prepare a message for the server/communicator
     */
    @Test
    public void keyDownTriggers() {
        Board myBoard = new Board("Board", 20, 0.25, 0.25);
        LeftFlipper leftFlipper = new  LeftFlipper("LFlip1", 5, 5, 270);
        
        assertEquals(leftFlipper.toString(), "  --");
        leftFlipper.addKeyDown("ctrl");
        leftFlipper.addKeyDown("shift");
        leftFlipper.addKeyDown("alt");
        leftFlipper.addKeyDown("meta");
        leftFlipper.addKeyDown("space");
        leftFlipper.addKeyDown("left");
        leftFlipper.addKeyDown("right");
        leftFlipper.addKeyDown("up");
        leftFlipper.addKeyDown("down");
        leftFlipper.addKeyDown("minus");
        leftFlipper.addKeyDown("equals");
        leftFlipper.addKeyDown("backslash");        
        leftFlipper.addKeyDown("semicolon");
        leftFlipper.addKeyDown("quote");
        leftFlipper.addKeyDown("enter");
        leftFlipper.addKeyDown("comma");
        leftFlipper.addKeyDown("period");
        leftFlipper.addKeyDown("slash");        
        leftFlipper.addKeyDown("z");
        leftFlipper.addKeyDown("9");
        
        myBoard.addGadget(leftFlipper);
        myBoard.triggerDownKey("ctrl");
        assertEquals(leftFlipper.toString(), "| | ");
        myBoard.triggerDownKey("shift");
        assertEquals(leftFlipper.toString(), "  --");
        myBoard.triggerDownKey("alt");
        assertEquals(leftFlipper.toString(), "| | ");
        myBoard.triggerDownKey("meta");
        assertEquals(leftFlipper.toString(), "  --");
        myBoard.triggerDownKey("space");
        assertEquals(leftFlipper.toString(), "| | ");
        myBoard.triggerDownKey("left");
        assertEquals(leftFlipper.toString(), "  --");
        myBoard.triggerDownKey("right");
        assertEquals(leftFlipper.toString(), "| | ");
        myBoard.triggerDownKey("up");
        assertEquals(leftFlipper.toString(), "  --");
        myBoard.triggerDownKey("down");
        assertEquals(leftFlipper.toString(), "| | ");
        myBoard.triggerDownKey("minus");
        assertEquals(leftFlipper.toString(), "  --");       
        myBoard.triggerDownKey("equals");
        assertEquals(leftFlipper.toString(), "| | ");
        myBoard.triggerDownKey("backslash"); 
        assertEquals(leftFlipper.toString(), "  --");
        myBoard.triggerDownKey("semicolon");
        assertEquals(leftFlipper.toString(), "| | ");
        myBoard.triggerDownKey("quote");
        assertEquals(leftFlipper.toString(), "  --");
        myBoard.triggerDownKey("enter");
        assertEquals(leftFlipper.toString(), "| | ");
        myBoard.triggerDownKey("comma");
        assertEquals(leftFlipper.toString(), "  --");
        myBoard.triggerDownKey("period");
        assertEquals(leftFlipper.toString(), "| | ");
        myBoard.triggerDownKey("slash");   
        assertEquals(leftFlipper.toString(), "  --");
        myBoard.triggerDownKey("z");
        assertEquals(leftFlipper.toString(), "| | ");
        myBoard.triggerDownKey("9");     
        assertEquals(leftFlipper.toString(), "  --");
        
    }
    
    /**
     * If the portal is connected to the non-existent portal on the same board,
     * the ball would behave as it was an empty space
     */
    @Test
    public void keyUpTriggerDigits() {
        Board myBoard = new Board("Board", 20, 0.25, 0.25);
        RightFlipper rightFlipper = new  RightFlipper("RFlip1", 5, 5, 180);
        
        assertEquals(rightFlipper.toString(), "| | ");
        rightFlipper.addKeyUp("0");
        rightFlipper.addKeyUp("1");
        rightFlipper.addKeyUp("2");
        rightFlipper.addKeyUp("3");
        rightFlipper.addKeyUp("4");
        rightFlipper.addKeyDown("5");
        rightFlipper.addKeyDown("6");
        rightFlipper.addKeyDown("7");
        rightFlipper.addKeyDown("8");
        rightFlipper.addKeyDown("9");
        
        myBoard.triggerUpKey("0");
        assertEquals(rightFlipper.toString(), "| | ");
        myBoard.triggerUpKey("1");
        assertEquals(rightFlipper.toString(), "| | ");
        myBoard.triggerUpKey("2");
        myBoard.triggerUpKey("3");
        myBoard.triggerUpKey("4");
        myBoard.triggerUpKey("5");
        myBoard.triggerDownKey("6");
        myBoard.triggerDownKey("7");
        myBoard.triggerDownKey("8");
        myBoard.triggerDownKey("9");
        
    }
   

}
