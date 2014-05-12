package ADT;

import java.awt.Graphics2D;
import java.util.ArrayList;

import physics.Circle;

public class Spawner implements Gadget{

    private final int x;
    private final int y;
    private final String name;
    private final Circle circle;
    private final ArrayList<Gadget> triggeredBy = new ArrayList<Gadget>();
    private final ArrayList<Gadget> triggers = new ArrayList<Gadget>();
    /**
     * Constructor for a CircleBumper
     * @param x the x coordinate of this circle bumper in the board.
     * @param y the y coordinate of this circle bumper in the board
     * @param name the name of this circle bumper
     */
    public Spawner(String name, int x, int y){
        this.name = name;
        this.x = x;
        this.y = y;
        this.circle = new Circle(x+0.5, y+0.5, 0.5);
        checkRep();
    }

    /**
     * Maintains the ball's rep invariant.
     * @throws RuntimeException if rep invariant is violated.
     */
    
    private void checkRep() throws RuntimeException{
        if (this.x<0 || this.x>19 || this.y<0 || this.y>19){
            throw new RuntimeException("CircleBumper's position cannot be outside of the board's bounds!!!");
        }
    }
    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getOrientation() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getX() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getY() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getChar() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getWidth() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getHeight() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean doesAbsorb() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean doesFlip() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isRotated() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void isTriggered(Gadget gadget) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public double getCollisionTime(Ball ball) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void reflect(Ball ball) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void action() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void release(Ball ball) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void triggers(Gadget gadget) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public ArrayList<Gadget> getTriggers() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addKeyUp(String key) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void addKeyDown(String key) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public ArrayList<String> getUpKeyTriggers() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
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

    @Override
    public void draw(Graphics2D g2) {
        // TODO Auto-generated method stub
        
    }
    

}
