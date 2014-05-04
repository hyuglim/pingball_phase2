package ADT;

import java.util.ArrayList;   

import ADT.Ball;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

/**
 * TriangularBumper represents a triangle bumper in the pingball board.
 */

public class TriangularBumper implements Gadget{
    
    /*
     * REP INVARIANT:
     * x, y, name, orientation, segments and corners must be non-null.
     * 0 <= x <= 19
     * 0 <= y <= 19
     * orientation should be either 0, 90, 180 or 270
     */
    private final int x;
    private final int y;
    private final int orientation;
    private final String name;
    private final ArrayList<LineSegment> segments = new ArrayList<LineSegment>();
    private final ArrayList<Circle> corners = new ArrayList<Circle>();
    private final ArrayList<Gadget> triggeredBy = new ArrayList<Gadget>();
    private final ArrayList<Gadget> triggers = new ArrayList<Gadget>();
    
    /**
     * Constructor for a TriangularBumper
     * @param x the x coordinate of this triangular bumper in the board.
     * @param y the y coordinate of this triangular bumper in the board
     * @param orientation the orientation of this triangular bumper
     * @param name the name of this triangular bumper
     */
    public TriangularBumper(String name, int x, int y, int orientation){
        this.x = x;
        this.y = y;
        this.name = name;
        this.orientation = orientation;
        if(this.orientation == 0 || orientation == 90){
            segments.add(new LineSegment(x, y, x+1, y));
            corners.add(new Circle(x, y, 0));
            corners.add(new Circle(x+1, y, 0));
            if(orientation == 0){
                segments.add(new LineSegment(x, y, x, y+1));
                segments.add(new LineSegment(x, y+1, x+1, y));
                corners.add(new Circle(x, y+1, 0));
            }else{
                segments.add(new LineSegment(x+1, y, x+1, y+1));
                segments.add(new LineSegment(x, y, x+1, y+1));
                corners.add(new Circle(x+1, y+1, 0));
            }
        }else{
            corners.add(new Circle(x, y+1, 0));
            corners.add(new Circle(x+1, y+1, 0));
            segments.add(new LineSegment(x, y+1, x+1, y+1));
            if(orientation == 180){
                corners.add(new Circle(x+1, y, 0));
                segments.add(new LineSegment(x, y+1, x+1, y));
                segments.add(new LineSegment(x, y, x+1, y+1));
            }else{
                corners.add(new Circle(x, y, 0));
                segments.add(new LineSegment(x, y, x+1, y+1));
                segments.add(new LineSegment(x, y, x, y+1));
            }
        }
        checkRep();
    }
    
    /**
     * Maintains the ball's rep invariant.
     * @throws RuntimeException if rep invariant is violated.
     */
    
    private void checkRep() throws RuntimeException{
        if (this.x<0 || this.x>19 || this.y<0 || this.y>19){
            throw new RuntimeException("TriangularBumper's position cannot be outside of the board's bounds!!!");
        }
        if (!(this.orientation ==0 || this.orientation ==90 || this.orientation ==180 ||this.orientation ==270)){
            throw new RuntimeException("TriangularBumper's orientation should be 0|90|180|270!!!");
        }
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
     * @see Gadget#getChar()
     */
    public String getChar() {
        if(this.orientation==180 || this.orientation ==0){
            return "/";
        }
        return "\\";
    }
    
    /**
     * @see Gadget#getName()
     */
    public String getName(){
        return name;
    }
    
    /**
     * @see Gadget#getCollisionTime(Ball)
     */
    public double getCollisionTime(Ball ball) {

        LineSegment minSegment = this.segments.get(0);
        double minTime =  ball.getWallCollisionTime(minSegment);
        for(LineSegment segment : segments){
            double time = ball.getWallCollisionTime(segment);
            if(minTime>time){
                minTime = time;
            }
        }
        
        for(Circle corner: corners){
            double time = ball.getCircleCollisionTime(corner);
            if(minTime>time){
                minTime = time;
            }
        } 
        return minTime;
    }
    
    /**
     * @see Gadget#getWidth()
     */
    public int getWidth(){
        int WIDTH_OF_TRIANGLE_BUMPER = 1;
        return WIDTH_OF_TRIANGLE_BUMPER;
    }
    
    /**
     * @see Gadget#getHeight()
     */
    public int getHeight(){
        int HEIGHT_OF_TRIANGLE_BUMPER = 1;
        return HEIGHT_OF_TRIANGLE_BUMPER;
    }
    
    /**
     * @see Gadget#doesAbsorb()
     */
    public boolean doesAbsorb() {
        return false;
    }

    /**
     * Finds the nearest component of this triangular bumper, either a corner or a wall, to the ball colliding and 
     * updates the velocity and position of the ball after collision
     * @param ball the ball that collided with this triangular bumper
     */
    public void reflect(Ball ball) {
        double minTime = this.getCollisionTime(ball);
        Vect newVelocity = new Vect(0, 0);
        for(LineSegment segment : this.segments){
            double time = ball.getWallCollisionTime(segment);;
            if(minTime==time){
                newVelocity = Geometry.reflectWall(segment, ball.getVelocity());          
            }
        }
        
        for(Circle corner : this.corners){
            double time = ball.getCircleCollisionTime(corner);
            if(minTime==time){
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
     * @see Gadget#doesFlip()
     */
    public boolean doesFlip() {
        return false;
    }
    
    /**
     * @see Gadget#isTriggered(Gadget)
     */
    public void isTriggered(Gadget gadget) {
        this.triggeredBy.add(gadget);
        
    }
    
    /**
     * @see Gadget#action()
     */
    public void action() { 
    }
    
    /**
     * @see Gadget#triggers(Gadget)
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
        return null;
    }


    public void addKeyUp(String key) {
        // TODO Auto-generated method stub
        
    }

    public void addKeyDown(String key) {
        // TODO Auto-generated method stub
        
    }


    public ArrayList<String> getUpKeyTriggers() {
        // TODO Auto-generated method stub
        return null;
    }


    public ArrayList<String> getDownKeyTriggers() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean doesPort() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String getOtherBoard() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getOtherPortal() {
        // TODO Auto-generated method stub
        return null;
    }

}
