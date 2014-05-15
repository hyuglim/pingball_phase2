package ADT;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import physics.Geometry.DoublePair;
import physics.Geometry.VectPair;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Ball {
    /**
     * The ball class with be implemented similarly to the ball implemented in
     * the warm-up some methods to be retained (and improved on are as follows)
     */
    public String name;
    private Circle circle;
    private Vect velocity;
    private double radius;
    private Geometry.DoublePair position;
    private boolean absorbed = false;

    /**
     * Constructor for making a new ball
     * 
     * @param name
     *            the name of the ball
     * @param xCoord
     *            xCoordinate of the ball's center
     * @param yCoord
     *            yCoordinate of the ball's center
     * @param xVel
     *            xVelocity of the ball's velocity
     * @param yVel
     *            yVelocity of the ball's velocity
     * @param radius
     *            the radius of the ball
     */
    public Ball(String name, double xCoord, double yCoord, double xVel,
            double yVel, double radius) {
        this.radius = radius;
        this.circle = new Circle(xCoord, yCoord, this.radius);
        this.position = new Geometry.DoublePair(xCoord, yCoord);
        this.velocity = new Vect(xVel, yVel);
        this.name = name;

    }

    /**
     * Maintains the ball's rep invariant.
     * @throws RuntimeException if rep invariant is violated.
     */
    
    private void checkRep() throws RuntimeException{
        double x = position.d1;
        double y = position.d2;
        if (this.velocity.length() >= 200){
            throw new RuntimeException("Ball Is Moving Too Fast!!!");
        }else if(this.radius<0.25 || this.radius>.5){
            throw new RuntimeException("Ball's radius should be in the range between 0.25 and 0.5, inclusive!");
        }else if (x < this.radius || x > 20-this.radius || y < this.radius || y > 20-this.radius) {
            throw new RuntimeException(
                    "Ball's position cannot be outside of the board's bounds!!!");
        }
    }

    /**
     * make a new ball instance
     * 
     * @param circle
     *            from the physics package
     * @param velocity
     *            a vector (also from the physics package)
     */
    public Ball(String name, Circle circle, Vect vel) {
        this.radius = circle.getRadius();
        this.circle = circle;
        this.position = new Geometry.DoublePair(circle.getCenter().x(), circle
                .getCenter().y());
        this.velocity = vel;
        this.name = name;
    }

    /**
     * Updates this ball's position
     * 
     * @param x
     *            the x coordinate of the new position
     * @param y
     *            the y coordinate of the new position
     */

    public void updatePosition(double newX, double newY) {
        if (newX > 20-getRadius()){
            newX = 20-getRadius();
        }
        if (newY > 20-getRadius()){
            newY = 20-getRadius();
        }
        if (newX < getRadius() ){
            newX = getRadius();
        }
        if (newY < getRadius() ){
            newY = getRadius();
        }
        this.circle = new Circle(newX, newY, this.radius);
        this.position = new Geometry.DoublePair(newX, newY);

    }
    /**
     * Updates this ball's velocity to the given new velocity newvel
     * 
     * @param newvel
     *            the new velocity
     */
    public void updateVelocity(Vect newVelocity) {
        this.velocity = newVelocity;
        checkRep();
    }

    /**
     * Returns the x axis location of this ball's center
     * 
     * @return x coordinate of this ball's center
     */
    public double getOriginX() {
        double originX = this.circle.getCenter().x();
        return originX;
    }

    /**
     * Returns the y axis location of this ball's center
     * 
     * @return y coordinate of this ball's center
     */
    public double getOriginY() {
        double originY = this.circle.getCenter().y();
        return originY;
    }

    /**
     * Returns DoublePair representation of this ball's current position
     * 
     * @return the current position
     */
    public DoublePair getCurrentPosition() {
        return new DoublePair(getOriginX(), getOriginY());
    }

    /**
     * Returns the x velocity passed in. Used for debugging antlr.
     * 
     * @return the x velocity passed in
     */
    public double getXVelocity() {
        return velocity.x();
    }

    /**
     * Returns the y velocity passed in. Used for debugging antlr.
     * 
     * @return the y velocity passed in
     */
    public double getYVelocity() {
        return velocity.y();
    }

    /**
     * Returns the name of this ball.
     * 
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
     * 
     * @return the circle of this ball
     */
    public Circle getBallCircle() {
        return this.circle;
    }

    /**
     * Checks whether the ball is absorbed or not
     * 
     * @return boolean representing whether the ball is absorbed or not
     */
    public boolean isAbsorbed() {
        return this.absorbed;
    }

    /**
     * Gets this ball absorbed in an absorber, sets this ball's absorbed
     * parameter equal to true
     */
    public void getsAbsorbed() {
        this.absorbed = true;
    }

    /**
     * Gets this ball released from an absorber, sets this ball's absorbed
     * parameter equals to false
     */
    public void getsReleased() {
        this.absorbed = false;
    }

    /**
     * Finds the position of this ball
     * 
     * @return Geometry DoublePair representation of this ball's position
     */
    public Geometry.DoublePair getBallPosition() {
        return this.position;
    }

    /**
     * Updates this ball's velocity to a new velocity
     * 
     * @param velocity
     *            the new velocity of this ball
     */
    public void updateBallVelocity(Vect velocity) {
        this.velocity = velocity;
    }

    /**
     * Computes the time until this ball collides with a line segment.
     * 
     * @param wall
     *            the line segment to compute the collision time with this ball
     * @return time until collision
     */
    public double getWallCollisionTime(LineSegment wall) {
        return Geometry.timeUntilWallCollision(wall, getBallCircle(),
                getVelocity());
    }

    /**
     * Computes the time until this ball collides with a given circle.
     * 
     * @param circle
     *            the Circle to compute the collision time with this ball
     * @return time until collision
     */
    public double getCircleCollisionTime(Circle circle) {
        return Geometry.timeUntilCircleCollision(circle, getBallCircle(),
                getVelocity());
    }

    /**
     * Computes the time until this ball collides with another ball
     * 
     * @param ball
     *            the ball to compute the collision time with this ball
     * @return the time until collision
     */
    public double getBallCollisionTime(Ball ball) {
        return Geometry.timeUntilBallBallCollision(this.getBallCircle(),
                this.velocity, ball.getBallCircle(), ball.velocity);
    }

    /**
     * Computes the time until this ball collides with another ball
     * 
     * @param ball
     *            another ball with which to compute the collision time with
     *            this ball
     * @return the velocity after collision
     */
    public Vect reflectBall(Ball ball) {
        Vect center1 = new Vect(this.position.d1, this.position.d2);
        Vect center2 = new Vect(ball.getBallPosition().d1,
                ball.getBallPosition().d2);
        VectPair afterVelocities = Geometry.reflectBalls(center1, 1,
                this.velocity, center2, 1, ball.velocity);
        return afterVelocities.v1;
    }

    /**
     * Returns the radius of this ball
     * 
     * @return the radius
     */
    public double getRadius() {
        return this.radius;
    }

    /**
     * Draws the graphics representations of the ball (solid yellow circle)
     * 
     * @param g
     *            instance of Graphics2D, drawing tool
     */
    public void draw(Graphics2D g) {
        g.setColor(Color.YELLOW);
        g.fillOval((int) (Math.round(getOriginX() * 20 + (20-radius*20))),
                (int) (Math.round(getOriginY() * 20 + (20-radius*20))),
                (int) (getRadius() * 40), (int) (getRadius() * 40));

    }

    /**
     * If the ball's radius is 0.25, then doubles the ball, otherwise shrinks
     * the ball size to the half of its original radius. The new radius must be at least 0.25, if
     * its less than 0.25, we give it a new radius 0.25
     */
    public void changeRadius() {
        if (this.radius <= 0.25) {
            this.radius = 0.5;
        } else if (this.radius <=0.5){
            this.radius = .25;
        }else{
            this.radius = this.radius/2;
       }
    }
    

    /**
     * Plays the sound file when ball hits another ball
     */
    public void makeNoise() {
        String fileName = "src/ADT/Wall.wav";
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
