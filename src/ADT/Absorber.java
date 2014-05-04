package ADT;

import java.util.ArrayList; 
import java.util.List;

import ADT.Ball;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

/**
 * Absorber represents an absorber in the pingball board.
 */

public class Absorber implements Gadget{
    
    /*
     * REP INVARIANT:
     * x, y, name, orientation, segments and corners must be non-null.
     * 0 <= x <= 19
     * 0 <= y <= 19
     * 
     */
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final String name;
    private final ArrayList<Gadget> triggeredBy = new ArrayList<Gadget>();
    private final ArrayList<Gadget> triggers = new ArrayList<Gadget>();
    private final ArrayList<Ball> myBalls = new ArrayList<Ball>();
    private final ArrayList<LineSegment> segments = new ArrayList<LineSegment>();
    private final ArrayList<Circle> corners = new ArrayList<Circle>();
    
    private ArrayList<String> upTriggers = new ArrayList<String>();
    private ArrayList<String> downTriggers = new ArrayList<String>();
    
    /**
     * Constructor for an Absorber
     * @param name the name of this triangular bumper
     * @param x the x coordinate of this absorber in the board
     * @param y the y coordinate of this absorber in the board
     * @param width the width of this absorber
     * @param height the height of this absorber
     * @param selftrigger the boolean representing whether this absorber is selftriggering or not.
     */
    public Absorber(String name, int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.name = name;
        this.width = width;
        this.height = height;
        this.segments.add(new LineSegment(x, y, x, y + height));
        this.segments.add(new LineSegment(x, y, x+width, y));
        this.segments.add(new LineSegment(x+width, y, x+width, y+ height));
        this.segments.add(new LineSegment(x,  y+ height, x+width, y+ height));
        this.corners.add(new Circle(x, y, 0));
        this.corners.add(new Circle(x, y+ height, 0));
        this.corners.add(new Circle(x+width, y, 0));
        this.corners.add(new Circle(x+width,y+ height, 0)); 
        checkRep();
    }
    
    /**
     * Maintains the ball's rep invariant.
     * @throws RuntimeException if rep invariant is violated.
     */
    
    private void checkRep() throws RuntimeException{
        if (this.x<0 || this.x>19 || this.y<0 || this.y>19){
            throw new RuntimeException("Absorber's position cannot be outside of the board's bounds!!!");
        }
        if (this.x + this.width>20 || this.y + this.height>20){
            throw new RuntimeException("Absorber's sides should not be greater than the sides of the board!!!");
        }
    }
    
    /**
     * Returns the original x without adding the board offset. Used for debugging antlr.
     * @return the x coordinate passed in
     */
    public double getOriginalX(){
        return x;
    }
    
    /**
     * Returns the original y without adding the board offset. Used for debugging antlr.
     * @return the y coordinate passed in
     */
    public double getOriginalY(){
        return y;
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
        return "=";
    }
    
    /**
     * @see Gadget#getName()
     */
    public String getName(){
        return name;
    }
    
    /**
     * @return returns the width of this absorber
     */
    public int getWidth(){
        return this.width;
    }
    
    /**
     * @return returns the height of this absorber
     */
    public int getHeight(){
        return this.height;
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
     * @see Gadget#doesAbsorb()
     */
    public boolean doesAbsorb() {
        return true;
    }
    
    /**
     * When a ball hits this absorber, it sends a trigger signal to the absorber.
     * If the absorber is not holding any ball, the absorber stops the ball and holds it 
     * in the bottom right-hand corner. The absorber can hold at most 1 ball at any given time.
     * If the absorber is holding another ball, then the absorber shoots the another ball straight upwards 
     * in the direction of the top of the playing area and holds the ball that hit it.
     * If the previously ejected ball has not yet left the absorber, then the absorber takes no action 
     * and the hitting ball just bounces back. 
     * @param ball the ball that hit this absorber
     */
    public void reflect(Ball ball){
        myBalls.add(ball);
        ball.getsAbsorbed();
        double xPosition = x + getWidth() - 0.25;
        double yPosition = y + getHeight() - 0.25;
        Geometry.DoublePair position = new Geometry.DoublePair(xPosition, yPosition);
        ball.updateBallVelocity(new Vect(0, 0));
        ball.updatePosition(position.d1, position.d2);
        if(this.triggeredBy.contains(this)){
            ball.updateBallVelocity(new Vect(0, -50));
        }else{
            for(Gadget gadget : this.triggers){
                gadget.action();         
            }
        }
    }
    
    /**
     * When the ejected ball comes extremely close to the top wall
     * of the absorber, absorber releases the ball, allows the ball to get
     * past the top ball and removes it from its myBalls list. Now the ball is 
     * no longer absorbed.
     * @param ball the ball that's about to leave this absorber.
     */
    public void release(Ball ball) {
        myBalls.remove(ball);
        ball.getsReleased();
        Vect velocity = ball.getVelocity();
        velocity = velocity.times(1-0.025*0.01 - 0.025*velocity.length()*0.01);
        double xPosition = ball.getBallPosition().d1 + 0.1*ball.getVelocity().x();
        double yPosition = ball.getBallPosition().d2 + 0.1*ball.getVelocity().y();
        Geometry.DoublePair position = new Geometry.DoublePair(xPosition, yPosition);
        ball.updatePosition(position.d1, position.d2);
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
        gadget.triggers(this);  
    }

    /**
     * @see Gadget#getOrientation()
     */
    public int getOrientation() {
        return 0;
    }
    
    /**
     * Sets this gadget's configuration such that it triggers the other gadget. 
     * @param gadget
     */
    public void triggers(Gadget gadget) {
        this.triggers.add(gadget);
    }
    
    /**
     * Gadget#action()
     */
    public void action() {
        if(this.myBalls.size() >= 1 && this.myBalls.get(0).getVelocity().length() == 0){
            this.myBalls.get(0).updateBallVelocity(new Vect(0, -50));
        }
    }
    
    /**
     * @see Gadget#isRotated()
     */
    public boolean isRotated() {
        return false;
    }
    
    /**
     * Returns the stored balls in this absorber
     * @return the stored balls
     */
    public List<Ball> getStoredBalls() {
        return this.myBalls;
    }

    /**
     * @see Gadget#getTriggers()
     */
    public ArrayList<Gadget> getTriggers() {
        return this.triggeredBy;
    }


    public void addKeyUp(String key) {
        upTriggers.add(key);
        
    }

    public void addKeyDown(String key) {
        downTriggers.add(key);
        
    }

    public ArrayList<String> getUpKeyTriggers() {
        return upTriggers;
    }

    public ArrayList<String> getDownKeyTriggers() {
        return downTriggers;
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
