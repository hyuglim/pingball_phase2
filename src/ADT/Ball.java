package ADT;

import physics.Circle;
import physics.Vect;
import physics.Geometry.DoublePair;

public class Ball {
    /**
     * The ball class with be implemented similarly to the ball implemented in the warm-up
     * some methods to be retained (and improved on are as follows)
     */
    public String name;
    public Circle circle;
    public Vect velocity;
    private final double radius;
    public double speed;
    
    /**
     * make a new ball instance
     * @param circle from the physics package
     * @param velocity a vector (also from the physics package)
     */
    
    public Ball(String name, double xCoord, double yCoord, double xVel, double yVel) {
        super();
        this.circle = new Circle(xCoord, yCoord, .25);
        this.velocity = new Vect(xVel, yVel);
        this.radius = .25;
        this.speed = 0;
        this.updateSpeed();
        this.name = name;
    }
    
    public void updatePosition(double x,double y){
        this.circle = new Circle(x, y, this.radius);
    }
    public void updateVelocity(Vect newVelocity){
        this.velocity = newVelocity;
    }

    public void setSpeed(double nextSpeed){
        this.speed = nextSpeed;
    }
        
    public void updateSpeed(){
        this.speed = Math.sqrt(Math.pow(this.velocity.x(),2) + Math.pow(this.velocity.y(),2));
    }

    public double getOriginX(){
        double originX = this.circle.getCenter().x();
        return originX;
    }

    public double getOriginY(){
        double originY = this.circle.getCenter().y();
        return originY;
    }

    public DoublePair getCurrentPosition(){
        return new DoublePair(getOriginX(), getOriginY());
    }

    public void setVelocity(Vect newvel) {
        this.velocity = newvel;
        
    }
}
