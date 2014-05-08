package ADT;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList; 
import java.util.List;

import physics.*;
/**
 * This represents a wall, it can be of four types depending on the orientation w.r.t the board.
 * Each wall can also be visible or not, if invisible: it has a board that it is connected to and
 * the toString method takes care of returning the name of the board it is connected to.
 */

public class Wall {
    private String boardname;
    private String location; // string representing "top", "bottom", "right", "left"
    private boolean visible; // if visible it means wall is solid
    private String connectedTo; // the name of the board the wall is connected to
    
    /**
     * 
     * @param location the type of the wall "top" etc
     * @param visible if it is a solid wall then true
     * @param connectedTo a wall that it is connected to
     * @param boardname the name of the board it currently belongs to
     */
    
    
    public Wall(String location,boolean visible, String connectedTo, String boardname){
        this.location = location;
        this.boardname = boardname;
        this.visible = visible;
        this.connectedTo = connectedTo;
    }

    
    /**
     * returns the appropriate line segment to be used in a board for reflections
     * @return
     */
    public LineSegment getWall(){
        if (location.equals("top")){
            return new LineSegment(0, 0, 20, 0);
        }
        if (location.equals("bottom")){
            return new LineSegment(0, 20, 20, 20);
        }
        if (location.equals("left")){
            return new LineSegment(0, 0, 0, 20);
        }
        //right
        else{
            return new LineSegment(20, 0, 20, 20);
        }
    }
    
    
    /**
     * a list representation to be used in printing the boardrep more easily since walls can be changed
     * @return
     */
    
    public List<String> toList(){
        List<String> rep = new ArrayList<String>();
        if (location.equals("top")|| location.equals("bottom")){
            //22 dots
            rep = new ArrayList<String>();
            for (int i=0; i<22; i++){
                rep.add(".");
            }
        }
        else{
            //only 20 dots
            rep = new ArrayList<String>();
            for (int i=0; i<20; i++){
                rep.add(".");
            }
            
        }
        
        if (!visible){
            String name = connectedTo;
            int nameLength = connectedTo.length();
            if(nameLength >= 20){
                for (int i=1; i<20; i++){
                    rep.remove(i);
                    rep.add(i, (""+name.charAt(i-1))); 
                }
            }else{
                for (int i=0; i<name.length(); i++){
                    rep.remove(i+(22-nameLength)/2);
                    rep.add(i+(22-nameLength)/2, (""+name.charAt(i))); 
                }
           
            }
        }
        return rep;
    }
    
    /**
     * takes care of ball to wall collisions
     * @param ball
     */
    public void update(Ball ball){
        LineSegment wall = getWall();
        //System.out.println("Hit wall "+this.location);
        double timeToWall = Geometry.timeUntilWallCollision(wall, ball.getBallCircle(), ball.getVelocity());
        Vect oldVel = ball.getVelocity();
        double newXPos = ball.getOriginX() + oldVel.x()*timeToWall;
        double newYPos = ball.getOriginY() + oldVel.y()*timeToWall;
        
        Vect newVelocity = Geometry.reflectWall(wall, ball.getVelocity());
        
        ball.updatePosition(newXPos, newYPos);
        ball.updateVelocity(newVelocity);
        
    }
    
    /**
     * Returns the visibility of this wall
     * @return true if this wall is visible, otherwise false
     */
    public boolean getVisibility(){
        return this.visible; 
    }
    
    
    /**
     * Sets the visibility of this wall to be
     * @param b true if to make this wall visible, otherwise false
     */
    public void setVisibility(boolean b){
        this.visible = b;
        
    }
       
    /**
     * If this wall is connected to a neighbor, then
     * disconnects the connection
     */
    public void removeConnection(){
        this.connectedTo = ".";
    }
    
    /**
     * Adds a connection to this wall, so that this wall is
     * connected to a wall of a given board with a given String name
     * @param name the name of the board to connect to
     */
    public void addConnection(String name){
        this.connectedTo = name;
        this.visible = false;
    }
    
    /**
     * If this wall is connected to a neighbor, then
     * disconnects the connection and makes this wall visible
     */
    public void disconnectWall(){
        removeConnection();
        this.visible = true;
    }
    
    /**
     * Returns the neighbor name of the board that this wall's connected tp
     * @return the connected neighbor name
     */
    public String getConnectedTo(){
        return this.connectedTo;
    }
    
    /**
     * Returns the location of this wall
     * @return the location
     */
    public String getLocation(){
        return this.location;
    }
    
    public void draw(Graphics2D g){
        Color c = new Color(252, 48, 48); //red
        g.setColor(c);
        
        if (this.location.equals("top")){
            g.fillRect(0, 0, 440, 20);
        }
        else if (this.location.equals("bottom")){
            g.fillRect(0, 420, 440, 20);
        }
        else if (this.location.equals("left")){
            g.fillRect(0, 20, 20, 400);
        }
        else{
            g.fillRect(420, 20, 20, 400);
            
        }
    }
}
