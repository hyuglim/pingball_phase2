package ADT;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;  

import ADT.Ball;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * RightFlipper represents a right flipper in the pingball board.
 */
public class RightFlipper implements Gadget{
    private final int x;
    private final int y;
    private final int orientation;
    private final String name;
    private boolean rotated = false;
    private LineSegment segment;
    private final ArrayList<Gadget> triggeredBy = new ArrayList<Gadget>();
    private final ArrayList<Gadget> triggers = new ArrayList<Gadget>();
    private final Vect center;
    private final double reflection = 0.95;
    private Circle end1;
    private Circle end2;
    
    private ArrayList<String> upTriggers = new ArrayList<String>();
    private ArrayList<String> downTriggers = new ArrayList<String>();
    
    /*
     * REP INVARIANT:
     * x, y, name and orientation must be non-null.
     * 0 <= x <= 18
     * 0 <= y <= 18
     * orientation should be either 0, 90, 180 or 270
     */
    
    /**
     * Constructor for the RightFlipper
     * @param x the x coordinate of this flipper in the board.
     * @param y the y coordinate of this flipper in the board
     * @param name the name of this rightflipper
     * @param orientation of this rightflipper.
     */

    public RightFlipper(String name, int x, int y, int orientation){
        this.name = name;
        this.x = x;
        this.y = y;
        this.orientation = orientation;
        if(orientation == 0){
            this.center = new Vect(x+2, y);
            this.segment = new LineSegment(x+2, y, x+2, y+2);
            this.end1 = new Circle(x+2, y, 0);
            this.end2 = new Circle(x+2, y+2, 0);
        }else if(orientation == 90){
            this.center = new Vect(x+2, y+2);
            this.segment = new LineSegment(x, y+2, x+2, y+2);
            this.end1 = new Circle(x, y+2, 0);
            this.end2 = new Circle(x+2, y+2, 0);
        }else if(orientation == 180){
            this.center = new Vect(x, y+2);
            this.segment = new LineSegment(x, y, x, y+2);
            this.end1 = new Circle(x, y, 0);
            this.end2 = new Circle(x, y+2, 0);
        }else{
            this.center = new Vect(x, y);
            this.segment = new LineSegment(x, y, x+2, y);
            this.end1 = new Circle(x, y, 0);
            this.end2 = new Circle(x+2, y, 0);
        }
        checkRep();
    }
    
    /**
     * Maintains the ball's rep invariant.
     * @throws RuntimeException if rep invariant is violated.
     */
    
    private void checkRep() throws RuntimeException{
        if (this.x<0 || this.x>19 || this.y<0 || this.y>19){
            throw new RuntimeException("RightFlipper's position cannot be outside of the board's bounds!!!");
        }
        if (!(this.orientation ==0 || this.orientation ==90 || this.orientation ==180 ||this.orientation ==270)){
            throw new RuntimeException("RightFlipper's orientation should be 0|90|180|270!!!");
        }
    }
    
    /**
     * @see Gadget#getName()
     */
    public String getName() {
        return name;
    }
    
    /**
     * Returns the orientation of the gadget. Used for debugging antlr.
     * @return the the orientation
     */
    public int getOrientation() {
         return orientation;
     }
    
    /**
     * @see Gadget#getX()
     */
    public int getX(){
        int boardOffset = 1;
        return this.x + boardOffset;
    }
    
    /**
     * @see Gadget#getY()
     */
    public int getY(){
        int boardOffset = 1;
        return this.y + boardOffset;
    }
    
    /**
     * @see Gadget#getChar()
     */
    public String getChar(){
        return " ";
    }
    
    /**
     * Returns the String representation of this rightflipper
     * @return the String representation
     */
    @Override
    public String toString(){
        if(!this.isRotated()){
            if(this.orientation == 0){
                return " | |";
            }else if(this.orientation == 90){
                return "  --";
            }else if(this.orientation == 180){
                return "| | ";
            }
            return "--  ";
            
        }else{
            if(this.orientation == 0){
                return "--  ";
            }else if(this.orientation == 90){
                return " | |";
            }else if(this.orientation == 180){
                return "  --";
            }
            return "| | ";
            
        }
    }
 
   /**
    * Finds the part of this flipper that the ball hit and
    * updates the ball's velocity correspondingly taking into account the standard
    * coefficient of reflection of 0.95 and also the linear velocity of the part of the flipper that
    * contacts the ball. Therefore, the ball may leave the flipper with a
    * higher energy than it had when it reached it. 
    * @param ball the ball that hit this flipper
    */
    public void reflect(Ball ball){
        
        double angularVelocity = 0;
        
        //If its self-triggering rotate it
        if(this.triggers.contains(this)){
            int velocityInRadian = 6;
            angularVelocity = Math.PI * velocityInRadian;
            if(!this.rotated){
                angularVelocity = - angularVelocity;
            }
        }
        
        double minTime = this.getCollisionTime(ball);
        
        //Finds the part of this flipper that the ball hit and
        //updates the ball's velocity correspondingly.
        
        Vect newVelocity = Geometry.reflectRotatingWall(segment, center, angularVelocity, ball.getBallCircle(), ball.getVelocity());
        if(minTime != ball.getWallCollisionTime(segment)){  
            if(minTime == ball.getCircleCollisionTime(end1)){
                newVelocity = Geometry.reflectCircle(end1.getCenter(), ball.getBallCircle().getCenter(), ball.getVelocity());     
            }else{
                newVelocity = Geometry.reflectCircle(end2.getCenter(), ball.getBallCircle().getCenter(), ball.getVelocity());     
            }
        }
        ball.updateBallVelocity(newVelocity.times(this.reflection));
        
        //triggers all the gadgets that this rightflipper triggers
        for(Gadget gadget: this.triggers){
            gadget.action();
        }
    }
    
    /**
     * @see Gadget#doesAbsorb()
     */
    public boolean doesAbsorb() {
        return false;
    }
    
    /**
     * @see Gadget#getCollisionTime()
     */

    public double getCollisionTime(Ball ball) {
        double minTimeToCollision = Geometry.timeUntilWallCollision(segment, ball.getBallCircle(), ball.getVelocity());
        double timeToEnd1 = Geometry.timeUntilCircleCollision(end1, ball.getBallCircle(), ball.getVelocity());
        minTimeToCollision = Math.min(minTimeToCollision, timeToEnd1);
        double timeToEnd2 = Geometry.timeUntilCircleCollision(end2, ball.getBallCircle(), ball.getVelocity());
        minTimeToCollision = Math.min(minTimeToCollision, timeToEnd2);
        return minTimeToCollision;
    }
    
    /**
     * @see Gadget#getWidth()
     */
    public int getWidth() {
        int WIDTH_OF_FLIPPER = 2;
        return WIDTH_OF_FLIPPER;
    }

    /**
     * @see Gadget#getHeight()
     */
    public int getHeight() {
        int HEIGHT_OF_FLIPPER = 2;
        return HEIGHT_OF_FLIPPER;
    }
    
    /**
     * Action: None 
     * @param ball the ball to be released
     */
    public void release(Ball ball) {   
        
    }

    /**
     * @see Gadget#doesFlip()
     */
    public boolean doesFlip(){
        return true;
    }
       
    /**
     * @see Gadget#isTriggered(Gadget)
     */
    public void isTriggered(Gadget gadget) {
        this.triggeredBy.add(gadget);
        gadget.triggers(this);
    }
    
    /**
     * @see Gadget#triggers(Gadget)
     */
    public void triggers(Gadget gadget) {
        this.triggers.add(gadget);
    }
    
    /**
     * When a rightflipper's action is triggered, the rightflipper rotates at
     * a constant angular velocity of 1080 degrees per second to a position 
     * 90 degrees away from its starting position. When its triggered a second time, it rotates back
     * to its original position at a same angular velocity. Since the time it takes for the flipper
     * to complete 90 degree rotation is less than our time frame, we just rotate the flipper
     * to its final state. 
     */
    public void action() {
        makeNoise();
        if(this.rotated){
            this.rotated = false;
            if(orientation == 0){
                this.segment = new LineSegment(x+2, y, x+2, y+2);
                this.end1 = new Circle(x+2, y, 0);
                this.end2 = new Circle(x+2, y+2, 0);
            }else if(orientation == 90){
                this.segment = new LineSegment(x, y+2, x+2, y+2);
                this.end1 = new Circle(x, y+2, 0);
                this.end2 = new Circle(x+2, y+2, 0);
            }else if(orientation == 180){
                this.segment = new LineSegment(x, y, x, y+2);
                this.end1 = new Circle(x, y, 0);
                this.end2 = new Circle(x, y+2, 0);
            }else{
                this.segment = new LineSegment(x, y, x+2, y);
                this.end1 = new Circle(x, y, 0);
                this.end2 = new Circle(x+2, y, 0);
            }
        }else{
            this.rotated = true;
            if(orientation == 0){
                this.segment = new LineSegment(x, y, x+2, y);
                this.end1 = new Circle(x, y+2, 0);
                this.end2 = new Circle(x+2, y, 0);
            }else if(orientation == 90){
                this.segment = new LineSegment(x+2, y, x+2, y+2);
                this.end1 = new Circle(x+2, y, 0);
                this.end2 = new Circle(x+2, y+2, 0);
            }else if(orientation == 180){
                this.segment = new LineSegment(x, y+2, x+2, y+2);
                this.end1 = new Circle(x, y+2, 0);
                this.end2 = new Circle(x+2, y+2, 0);
            }else{
                this.segment = new LineSegment(x, y, x, y+2);
                this.end1 = new Circle(x, y, 0);
                this.end2 = new Circle(x, y+2, 0);
            }
        }
        
    }
    
    /**
     * @see Gadget#isRotated()
     */
    public boolean isRotated() {
        return this.rotated;
    }
    
    /**
     * @see Gadget#getTriggers()
     */
    public ArrayList<Gadget> getTriggers() {
        return this.triggeredBy;
    }
    /**
     * @see Gadget#addKeyUp(key)
     */
    public void addKeyUp(String key) {
        upTriggers.add(key);
        
    }

    /**
     * @see Gadget#addKeyUp(key)
     */
    public void addKeyDown(String key) {
        downTriggers.add(key);
        
    }

    /**
     * @see Gadget#getUpKeyTriggers()
     */
    public ArrayList<String> getUpKeyTriggers() {
        return upTriggers;
    }

    /**
     * @see Gadget#getDownKeyTriggers()
     */
    public ArrayList<String> getDownKeyTriggers() {
        return downTriggers;
    }
    
    /**
     * @see Gadget#doesPort()
     */
    public boolean doesPort() {
        return false;
    }

    /**
     * @see Gadget#getOtherBoard()
     */
    public String getOtherBoard() {
        return null;
    }

    /**
     * @see Gadget#getOtherPortal()
     */
    public String getOtherPortal() {
        return null;
    }

    @Override
    public void draw(Graphics2D g2) {
        Color c = new Color(53, 199, 104); //light green
        g2.setColor(c);
        final int OFFSET = 20;
        int x = (int) (center.x() * 20);
        int y = (int) (center.y() * 20);
        if (!(this.rotated)) {
            if (orientation == 0) {
                g2.fillRoundRect(x + OFFSET - 10, y + OFFSET, 10, 40, 10, 10);
            }
            if (orientation == 90) {

                g2.fillRoundRect(x + OFFSET - 40, y + OFFSET - 10, 40, 10, 10, 10);
            }
            if (orientation == 180) {
                g2.fillRoundRect(x + OFFSET, y + OFFSET - 40, 10, 40, 10,
                        10);
            }
            if (orientation == 270) {
                g2.fillRoundRect(x + OFFSET, y + OFFSET, 40, 10, 10, 10);
            }
        } else {
            if (orientation == 0) {
                g2.fillRoundRect(x + OFFSET - 40, y + OFFSET, 40, 10, 10, 10);
            }
            if (orientation == 90) {

                g2.fillRoundRect(x + OFFSET - 10, y + OFFSET - 40, 10, 40, 10, 10);
            }
            if (orientation == 180) {
                g2.fillRoundRect(x + OFFSET, y + OFFSET - 10, 40, 10, 10,
                        10);
            }
            if (orientation == 270) {
                g2.fillRoundRect(x + OFFSET, y + OFFSET, 10, 40, 10, 10);
            }

        }
        
    }

    @Override
    public void drawAnother(Graphics2D g2) {
        
    }

    @Override
    public boolean isHit() {
        return false;
    }

    @Override
    public void makeNoise() {
        String fileName = "src/ADT/Flipper.wav";
        InputStream in = null;
        try {
            in = new FileInputStream(fileName);
  

       } catch (FileNotFoundException e) {
            System.err.println("Can't find wav file");
            
            e.printStackTrace();
       }

       AudioStream as = null;
       try {
            as = new AudioStream(in);
       } catch (IOException e) {
            e.printStackTrace();
       }

       AudioPlayer.player.start(as);
        
        
    }

    @Override
    public void setNotHit() {
        
    }
}
