package ADT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import physics.*;
/**
 * This represents a wall, it can be of four types depending on the orientation w.r.t the board.
 * Each wall can also be visible or not, if invisible: it has a board that it is connected to and
 * the toString method takes care of returning the name of the board it is connected to.
 * @author saadiyah
 *
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
            //printing the name of the other board to the wall
            String name = connectedTo;
            for (int i=0; i<name.length(); i++){
                rep.remove(i);
                rep.add(i, (""+name.charAt(i))); 
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
    
    public boolean getVisibility(){
        return this.visible; 
    }
    
    public void setVisibility(boolean b){
        this.visible = b;
        
    }
    
    public void removeConnection(){
        this.connectedTo = ".";
    }
    
    public void addConnection(String name){
        this.connectedTo = name;
        this.visible = false;
    }
    
    public void disconnectWall(){
        removeConnection();
        this.visible = true;
    }
    
    public String getConnectedTo(){
        return this.connectedTo;
    }
    public String getLocation(){
        return this.location;
    }
    

}
