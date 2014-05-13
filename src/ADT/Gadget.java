package ADT;

import java.awt.Graphics2D;
import java.util.ArrayList; 

import ADT.Ball;

/**
 *
 * The standard gadget could either be a Wall, SquareBumper, CircleBumper, TriangularBumper, 
 * RightFlipper, LeftFlipper or Absorber. Each gadget may have a trigger and an action. 
 * A trigger is an event that happens at the gadget, such as a ball colliding with it. An action 
 * is a response that a gadget can make to a trigger happening somewhere on the board.
 * A gadget also has a coefficient of reflection, which is a multiplier applied to the 
 * magnitude of the ball’s velocity after it bounces off the gadget.
 * 
 * The gadget consists of Circles and LineSegments
 */

public interface Gadget {
    
    
    /**
     * Returns the name of this gadget.
     * @return the String name
     */
    public String getName();
    
    
    /**
     * Returns the orientation of this gadget. Could be either 0, 90, 180 or 270.
     * @return the orientation.
     */
    public int getOrientation();
    /**
     * Returns the gadget's column position from the board's origin (0,0).
     * @return integer representing the column.
     */
    public int getX();
    
    /**
     * Returns the gadget's row position from the board's origin (0,0).
     * @return integer representing the row.
     */  
    public int getY();
    
    /**
     * Returns the character representation of the gadget.
     * @return character representation.
     */
    public String getChar();
    
    /**
     * Returns the width of this gadget.
     * @return the width
     */
    public int getWidth();
    
    /**
     * Returns the height of this gadget.
     * @return the height
     */
    public int getHeight();
    
    /**
     * Checks whether the gadget can absorb a ball or not.
     * @return true if the gadget absorbs a ball, false otherwise.
     */
    public boolean doesAbsorb();
    
    /**
     * Checks whether the gadget flips or not.
     * @return true if the gadget flips whenever a ball hits it.
     */
    public boolean doesFlip();
    
    
    /**
     * Checks whether this gadget has been rotated from its original position.
     * @return boolean true if this gadget is rotated, otherwise false.
     */
    public boolean isRotated();
    
    
    /**
     * Makes this gadget a consumer of the trigger events by the gadget passed in.
     * @param gadget the gadget whose events trigger this gadget.
     */
    public void isTriggered(Gadget gadget);
    
    /**
     * Calculates the time until the given ball collides with this gadget.
     * @param ball the ball whose collision time is to be calculated.
     * @return the time until the ball collides with this gadget.
     */
    public double getCollisionTime(Ball ball);
    
    /**
     * Updates the velocity and the position of the ball right when the ball hits this gadget.
     * @param ball the ball that triggered this gadget by hitting it. 
     */
    public void reflect(Ball ball);
    
    /**
     * Triggers this gadget. If this gadget is a flipper, it rotates 90 degrees, or if this 
     * gadget is an absorber shoots out an already stored ball, otherwise does nothing.
     */
    public void action();    
    
    /**
     * Releases the ball from this gadget. 
     * @param ball that is stored in this gadget, and is about to get released from it.
     */
    public void release(Ball ball);
    
    
    /**
     * Sets this gadget's configuration such that it triggers the other gadget. 
     * @param gadget
     */
    public void triggers(Gadget gadget);
    
    /**
     * Returns the list of gadgets that trigger this gadget
     * @return the triggers of this gadget
     */
    public ArrayList<Gadget> getTriggers();
    
    /**
     * Adds the key to the list of this gadget's key triggers.
     * @param key the key that triggers this gadget when released
     */
    public void addKeyUp(String key);
    
    /**
     * Adds the key to the list of this gadget's key triggers.
     * @param key the key that triggers this gadget when pressed.
     */
    public void addKeyDown(String key);
    
    /**
     * Returns the list of keys that triggers this gadget when released.
     * @return this gadgets' up trigger keys.
     */
    public ArrayList<String> getUpKeyTriggers();
    
    /**
     * Returns the list of keys that triggers this gadget when pressed.
     * @return this gadgets' down trigger keys.
     */
    public ArrayList<String> getDownKeyTriggers();
    
    /**
     * Checks whether the gadget ports or not.
     * @return true if the gadget ports whenever a ball hits it.
     */
    public boolean doesPort();
    
    /**
     * Returns the otherboard's name that this gadget is connected to
     * @return the board name that this gadget is connected to
     */
    public String getOtherBoard();
    
    
    /**
     * Returns the other portal name that this gadget is connected to
     * @return the portal name that this gadget is connected to
     */
    public String getOtherPortal();
    


    /**
     * Draws a graphical representation of the object in BoardJPanel
     * @param g2
     */
    public void draw(Graphics2D g2);
    public void drawAnother(Graphics2D g2);
    public boolean isHit();
    public void makeNoise();
    public void setNotHit();      

}
