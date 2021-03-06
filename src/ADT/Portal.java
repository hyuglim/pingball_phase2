package ADT;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import physics.Circle;
import physics.Geometry;
import physics.Vect;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Portal implements Gadget {
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
     * 
     * @param x
     *            the x coordinate of this circle bumper in the board.
     * @param y
     *            the y coordinate of this circle bumper in the board
     * @param name
     *            the name of this circle bumper
     */
    public Portal(String name, int x, int y, String otherBoard,
            String otherPortal) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.otherBoard = otherBoard;
        this.otherPortal = otherPortal;
        this.circle = new Circle(x + 0.5, y + 0.5, 0.5);
    }

    /**
     * @see Gadget#getX()
     */
    public int getX() {
        int boardOffset = 1;
        return x + boardOffset;
    }

    /**
     * @see Gadget#getY()
     */
    public int getY() {
        int boardOffset = 1;
        return y + boardOffset;
    }

    /**
     * @see Gadget#getChar()
     */
    public String getChar() {
        return "Z";
    }

    /**
     * @see Gadget#getName()
     */
    public String getName() {
        return name;
    }

    /**
     * @see Gadget#getWidth()
     */
    public int getWidth() {
        int WIDTH_OF_PORTAL = 1;
        return WIDTH_OF_PORTAL;
    }

    /**
     * @see Gadget#getHeight()
     */
    public int getHeight() {
        int HEIGHT_OF_PORTAL = 1;
        return HEIGHT_OF_PORTAL;
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
     * 
     * @param ball
     *            the ball that collided with this circle bumper
     */
    public void reflect(Ball ball) {
        makeNoise();
        if (otherBoard.equals("")) {

        }
        Vect newVelocity = Geometry.reflectCircle((this.circle).getCenter(),
                ball.getBallCircle().getCenter(), ball.getVelocity());
        ball.updateBallVelocity(newVelocity);
        for (Gadget gadget : this.triggers) {
            gadget.action();
        }

    }

    /**
     * Action: None
     * 
     * @param ball
     *            the ball to be released
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
     * 
     * @return returns Circle representation.
     */
    public Circle getCircle() {
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

    /**
     * @see Gadget#addKeyUp(key)
     */
    public void addKeyUp(String key) {
    }

    /**
     * @see Gadget#addKeyUp(key)
     */
    public void addKeyDown(String key) {
    }

    /**
     * @see Gadget#getUpKeyTriggers()
     */
    public ArrayList<String> getUpKeyTriggers() {
        return new ArrayList<String>();
    }

    /**
     * @see Gadget#getDownKeyTriggers()
     */
    public ArrayList<String> getDownKeyTriggers() {
        return new ArrayList<String>();
    }

    /**
     * @see Gadget#doesPort()
     */
    public boolean doesPort() {
        return true;
    }

    /**
     * @see Gadget#getOtherBoard()
     */
    public String getOtherBoard() {
        return otherBoard;
    }

    /**
     * @see Gadget#getOtherPortal()
     */
    public String getOtherPortal() {
        return otherPortal;
    }

    @Override
    public void draw(Graphics2D g2) {
        BufferedImage img = null;
        // swirl picture
        try {
            img = ImageIO.read(new File("src/ADT/portal.jpg"));
        } catch (IOException e) {
            System.err.println("No image");
        }
        g2.drawImage(img, x * 20 + 20, y * 20 + 20, 20, 20, null);
    }

    /**
     * @see ADT.Gadget#drawAnother(java.awt.Graphics2D)
     */
    // dummy method, portal doesn't glow
    @Override
    public void drawAnother(Graphics2D g2) {

    }

    /**
     * @see ADT.Gadget#isHit()
     */
    // dummy method, portal doesn't glow
    @Override
    public boolean isHit() {
        return false;
    }

    /**
     * @see ADT.Gadget#makeNoise()
     */
    @Override
    public void makeNoise() {
        String fileName = "src/ADT/Portal.wav";
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

    /**
     * @see ADT.Gadget#setNotHit()
     */
    // dummy method, portal doesn't glow
    @Override
    public void setNotHit() {

    }
}
