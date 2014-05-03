package ADT;

import ADT.Ball;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import physics.Geometry.DoublePair;
import physics.Geometry.VectPair;

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
    
    
    
    private Circle ballCircle;
    private Geometry.DoublePair position;
    private boolean absorbed = false;
    private final double x;
    private final double y;
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
        this.x = xCoord;
        this.y = yCoord;
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
     * Returns the x velocity passed in. Used for debugging antlr.
     * @return the x velocity passed in
     */
    public double getXVelocity(){
        return velocity.x();
    }
    
    /**
     * Returns the y velocity passed in. Used for debugging antlr.
     * @return the y velocity passed in
     */
    public double getYVelocity(){
        return velocity.y();
    }
    
    /**
     * Returns the name of this ball.
     * @return the name
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * @return the String representation of this Ball
     */
    @Override
    public String toString() {
        return "*";
    }
    
    /**
     * @return the Velocity of this ball
     */
    public Vect getVelocity() {
        return this.velocity;
    }
    
    /**
     * Finds the circle of this ball
     * @return the circle of this ball
     */
    public Circle getBallCircle() {
        return this.ballCircle;
    }
    
    /**
     * Checks whether the ball is absorbed or not
     * @return boolean representing whether the ball is absorbed or not
     */
    public boolean isAbsorbed(){
        return this.absorbed;
    }
    
    /**
     * Gets this ball absorbed in an absorber, sets this ball's 
     * absorbed parameter equal to true
     */
    public void getsAbsorbed(){
        this.absorbed = true;
    }
    
    /**
     * Gets this ball released from an absorber, sets this ball's
     * absorbed parameter equals to false
     */
    public void getsReleased(){
        this.absorbed = false;
    }
    
    /**
     * Finds the position of this ball
     * @return Geometry DoublePair representation of this ball's position
     */
    public Geometry.DoublePair getBallPosition() {
        return this.position;
    }
    
    /**
     * Updates this ball's position to a new position
     * @param position the new position of this ball
     */
    public void updateBallPosition(Geometry.DoublePair position) {
        this.position = position;
        this.ballCircle = new Circle(position.d1, position.d2, 0.25);
    }

    /**
     * Updates this ball's velocity to a new velocity
     * @param velocity the new velocity of this ball
     */
    public void updateBallVelocity(Vect velocity) {
        this.velocity = velocity;
    }
    
    /**
     * Computes the time until this ball collides with a line segment. 
     * @param wall the line segment to compute the collision time with this ball
     * @return time until collision
     */
    public double getWallCollisionTime(LineSegment wall){
        return Geometry.timeUntilWallCollision(wall, getBallCircle(), getVelocity());
    }
    
    /**
     * Computes the time until this ball collides with a given circle. 
     * @param circle the Circle to compute the collision time with this ball
     * @return time until collision
     */
    public double getCircleCollisionTime(Circle circle){
        return Geometry.timeUntilCircleCollision(circle, getBallCircle(), getVelocity());
    }
    
    /**
     * Computes the time until this ball collides with another ball
     * @param ball the ball to compute the collision time with this ball
     * @return the time until collision
     */
    public double getBallCollisionTime(Ball ball){
        return Geometry.timeUntilBallBallCollision(this.getBallCircle(), this.velocity, ball.getBallCircle(), ball.velocity);
    }
    
    /**
     * Computes the time until this ball collides with another ball
     * @param ball another ball with which to compute the collision time with this ball
     * @return the velocity after collision
     */
    public Vect reflectBall(Ball ball){
        Vect center1 = new Vect(this.position.d1, this.position.d2);
        Vect center2 = new Vect(ball.getBallPosition().d1, ball.getBallPosition().d2);
        VectPair afterVelocities = Geometry.reflectBalls(center1, 1,  this.velocity, center2, 1, ball.velocity);
        return afterVelocities.v1;
    }
}
