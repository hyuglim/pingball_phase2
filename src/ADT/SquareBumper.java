package ADT;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;   

import ADT.Ball;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

/**
 * SquareBumper represents a square bumper in the pingball board.
 */
public class SquareBumper implements Gadget{
    
    /*
     * REP INVARIANT:
     * x, y, name, segments and corners must be non-null.
     * 0 <= x <= 19
     * 0 <= y <= 19
     * 
     */
    
    private final int x;
    private final int y;
    private final ArrayList<LineSegment> segments = new ArrayList<LineSegment>();
    private final ArrayList<Circle> corners = new ArrayList<Circle>();
    private final String name;
    private final ArrayList<Gadget> triggeredBy = new ArrayList<Gadget>();
    private final ArrayList<Gadget> triggers = new ArrayList<Gadget>();
    /**
     * Constructor for a SquareBumper
     * @param x the x coordinate of this square bumper in the board.
     * @param y the y coordinate of this square bumper in the board
     * @param name the name of this square bumper
     */
    public SquareBumper(String name, int x, int y){
        this.x = x;
        this.y = y;
        this.name = name;
        
        segments.add(new LineSegment(x, y, x, y+1));
        segments.add(new LineSegment(x+1, y, x+1, y+1));
        segments.add(new LineSegment(x, y, x+1, y));
        segments.add(new LineSegment(x, y+1, x+1, y+1));
        corners.add(new Circle(x, y, 0));
        corners.add(new Circle(x, y+1, 0));
        corners.add(new Circle(x+1, y, 0));
        corners.add(new Circle(x+1, y+1, 0));
        checkRep();
        
    }
      
    /**
     * Maintains the ball's rep invariant.
     * @throws RuntimeException if rep invariant is violated.
     */
    
    private void checkRep() throws RuntimeException{
        if (this.x<0 || this.x>19 || this.y<0 || this.y>19){
            throw new RuntimeException("SquareBumper's position cannot be outside of the board's bounds!!!");
        } 
    }
    
    /**
     * @see Gadget#getOrientation()
     */
    public int getOrientation() {
        return 0;
    }
    
   /**
    * @see Gadget#getX()
    */
    public int getX(){
        int boardOffset = 1;
        return x + boardOffset;
    }
    
   /**
    * @see Gadget#getY()
    */
    public int getY(){
        int boardOffset = 1;
        return y + boardOffset;
    }
    
    /**
     * @see Gadget#getName()
     */
    public String getName(){
        return name;
    }
    
    /**
     * @see Gadget#getChar()
     */
    public String getChar(){
        return "#";
    }

    /**
     * @see Gadget#getCollisionTime(Ball)
     */
    public double getCollisionTime(Ball ball) {
        LineSegment minSegment = this.segments.get(0);
        double minTimeToCollision = Geometry.timeUntilWallCollision(minSegment, ball.getBallCircle(), ball.getVelocity());
        
        for(int i=1; i<segments.size(); i++){
            double timeToSegment = Geometry.timeUntilWallCollision(segments.get(i), ball.getBallCircle(), ball.getVelocity());
            if(minTimeToCollision>timeToSegment){
                minTimeToCollision = timeToSegment;
            }
        }
        
        for(int i=0; i<corners.size(); i++){
            double timeToCircle = Geometry.timeUntilCircleCollision(this.corners.get(i), ball.getBallCircle(), ball.getVelocity());
            if(minTimeToCollision>timeToCircle){
                minTimeToCollision= timeToCircle;
            }
        }
        
        return minTimeToCollision;
    }
    
    /**
     * @see Gadget#getWidth()
     */
    public int getWidth(){
        int WIDTH_OF_SQUARE_BUMPER = 1;
        return WIDTH_OF_SQUARE_BUMPER;
    }
    
    /**
     * @see Gadget#getHeight()
     */
    public int getHeight(){
        int HEIGHT_OF_SQUARE_BUMPER = 1;
        return HEIGHT_OF_SQUARE_BUMPER;
    }
    
    /**
     * @see Gadget#doesAbsorb()
     */
    public boolean doesAbsorb() {
        return false;
    }
    
    /**
     * @see Gadget#doesFlip()
     */
    public boolean doesFlip() {
        return false;
    }
    
    /**
     * Finds the nearest component of this square bumper, either a corner or a wall, to the ball colliding and 
     * updates the velocity and position of the ball after collision
     * @param ball the ball that collided with this square bumper
     */
    public void reflect(Ball ball) { 
        double minTimeToCollision = this.getCollisionTime(ball);
        Vect newVelocity = new Vect(0, 0);
        
        for(LineSegment segment : this.segments){
            double time = ball.getWallCollisionTime(segment);
            if(minTimeToCollision==time){
                newVelocity = Geometry.reflectWall(segment, ball.getVelocity());          
            }
        }
        
        for(Circle corner : this.corners){
            double time = ball.getCircleCollisionTime(corner);
            if(minTimeToCollision==time){
                newVelocity = Geometry.reflectCircle(corner.getCenter(), ball.getBallCircle().getCenter(), ball.getVelocity());  
                
            }
        }
        ball.updateBallVelocity(newVelocity);
        for(Gadget gadget: this.triggers){
            gadget.action();
        }
    }

    /**
     * Action: None 
     * @param ball the ball to be released
     */
    public void release(Ball ball) {    
    }
    
    /**
     * @see Gadget#actionr()
     */
    public void action(){    
    }
    
    /**
     * @see Gadget#isTriggered(Gadget)
     */
    public void isTriggered(Gadget gadget) {
        this.triggeredBy.add(gadget);       
    }
    
    /**
     * @see Gadget#triggers()
     */
    public void triggers(Gadget gadget) {
        this.triggers.add(gadget);
    }
    
    /**
     * @see Gadget#isRotated()
     */
    public boolean isRotated() {
        return false;
    }
    
    /**
     * @see Gadget#getTriggers()
     */
    public ArrayList<Gadget> getTriggers() {
        return this.triggers;
    }


    /**
     * @see Gadget#addKeyUp(key)
     */
    public void addKeyUp(String key) {        
    }

    /**
     * @see Gadget#addKeyUp(key)
     */
    public void addKeyDown(String key) {
    }

    /**
     * @see Gadget#getUpKeyTriggers()
     */
    public ArrayList<String> getUpKeyTriggers() {
        return null;
    }

    /**
     * @see Gadget#getDownKeyTriggers()
     */
    public ArrayList<String> getDownKeyTriggers() {
        return null;
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
        g2.setColor(Color.RED);
        g2.fillRect(x*20+20, y*20+20, getWidth()*20, getHeight()*20);
        
    }
    
    
}
