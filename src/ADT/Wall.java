package ADT;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList; 
import java.util.List;

import physics.*;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
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
     * @return correcponding Line Segment instance
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
     * @return List of Strings used by boardRep
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
     * @param ball to be collided with a wall
     */
    public void update(Ball ball){
        LineSegment wall = getWall();
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
        this.connectedTo = null;
        this.visible = true;
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
    
    /**
     * Draws a graphical representation of the wall.
     * Whenever a Wall is visible, it's represented as a solid
     * red rectangle. When it's visible, it reflects the
     * name of the connected board.
     * @param g drawing tool
     */
    public void draw(Graphics2D g){
        Color c = new Color(252, 48, 48); //red
        g.setColor(c);
        
        if (this.location.equals("top")){
            if(this.visible){
                g.fillRect(0, 0, 440, 20);
            }else{
                g.setColor(Color.ORANGE);
                g.setFont(new Font("Verdana", 1, 20));
                
                int initialX = (440 - this.getConnectedTo().length()*20)/2;
                g.drawString(this.getConnectedTo(), initialX, 16);
            }
        }
        else if (this.location.equals("bottom")){
            if(this.visible){
                g.fillRect(0, 420, 440, 20);
            }else{
                g.setColor(Color.ORANGE);
                g.setFont(new Font("Verdana", 1, 20));
                
                int initialX = (440 - this.getConnectedTo().length()*20)/2;
                g.drawString(this.getConnectedTo(), initialX, 438);
            }
        }
        else if (this.location.equals("left")){
            if(this.visible){
                g.fillRect(0, 20, 20, 400);
            }else{
                g.setColor(Color.ORANGE);
                g.setFont(new Font("Verdana", 1, 20));
                int initialY = (440 - this.getConnectedTo().length()*20)/2;
                
                String[] charArray = this.getConnectedTo().split("");
                for(String character: charArray){
                    g.drawString(character, 4, initialY);
                    initialY+=20;
                }
            }
        }
        else{
            if(this.visible){
                g.fillRect(420, 20, 20, 400); 
            }else{
                g.setColor(Color.ORANGE);
                g.setFont(new Font("Verdana", 1, 20));
                
                int initialY = (440 - this.getConnectedTo().length()*20)/2;
                String[] charArray = this.getConnectedTo().split("");
                for(String character: charArray){
                    g.drawString(character, 423, initialY);
                    initialY+=20;
                }
            }
        }
    }
    /**
     * Plays the sound file whenever a ball hits the wall
     */
    public void makeNoise() {
        String fileName ="src/ADT/Wall.wav";
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
            System.err.println("Can't play wav file");
            e.printStackTrace();
       }

       AudioPlayer.player.start(as);
      
    }
}
