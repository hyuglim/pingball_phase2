package ADT;

import java.util.ArrayList;

import physics.Circle;
import physics.Geometry;
import physics.Vect;

public class Portal implements Gadget{
    private final int x;
    private final int y;
    private final String name;
    private final Circle circle;
    private final ArrayList<Gadget> triggeredBy = new ArrayList<Gadget>();
    private final ArrayList<Gadget> triggers = new ArrayList<Gadget>();
    private final String otherPortal;
    private final String otherBoard;
    /**
     * Constructor for a CircleBumper
     * @param x the x coordinate of this circle bumper in the board.
     * @param y the y coordinate of this circle bumper in the board
     * @param name the name of this circle bumper
     */
    public Portal(String name, int x, int y, String otherBoard, String otherPortal){
        this.name = name;
        this.x = x;
        this.y = y;
        this.otherBoard = otherBoard;
        this.otherPortal = otherPortal;
        this.circle = new Circle(x+0.5, y+0.5, 0.5);
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
    public String getChar(){
        return "Z";
    }

    /**
     * @see Gadget#getName()
     */
    public String getName(){
        return name;
    }

    /**
     * @see Gadget#getWidth()
     */
    public int getWidth(){
        int WIDTH_OF_CIRCLE_BUMPER = 1;
        return WIDTH_OF_CIRCLE_BUMPER;
    }

    /**
     * @see Gadget#getHeight()
     */
    public int getHeight(){
        int HEIGHT_OF_CIRCLE_BUMPER = 1;
        return HEIGHT_OF_CIRCLE_BUMPER;
    }

    /**
     * @see Gadget#doesAbsorb()
     */
    public boolean doesAbsorb() {
        return false;
    }

    /**
     * @see Gadget#getCollisionTime(Ball)
     */
    public double getCollisionTime(Ball ball) {    
        return ball.getCircleCollisionTime(this.circle);
    }

    /**
     * Updates the velocity and position of the ball after collision
     * @param ball the ball that collided with this circle bumper
     */
    public void reflect(Ball ball) {
        if(otherBoard.equals("")){
            
        }
        Vect newVelocity = Geometry.reflectCircle((this.circle).getCenter(), ball.getBallCircle().getCenter(), ball.getVelocity()); 
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
        ball.getsReleased();
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
     * @see Gadget#getOrientation()
     */
    public int getOrientation() {
        return 0;
    }

    /**
     * @see Gadget#triggers()
     */
    public void triggers(Gadget gadget) {
        this.triggers.add(gadget);       
    }

    /**
     * @see Gadget#trigger()
     */
    public void action() {
    }

    /**
     * Returns the Circle representation of this CircleBumper
     * @return returns Circle representation.
     */
    public Circle getCircle(){
        return this.circle;
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
        return this.triggeredBy;
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
        return true;
    }

    @Override
    public String getOtherBoard() {
        // TODO Auto-generated method stub
        return otherBoard;
    }

    @Override
    public String getOtherPortal() {
        // TODO Auto-generated method stub
        return otherPortal;
    }

}
