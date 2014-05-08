package PingballGUI;


/**
 * Takes a board instance, uses Swing timer
 * 
 * Ratio: one board cell = 20 pixels!!!
 */
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import ADT.Absorber;
import ADT.Ball;
import ADT.Board;
import ADT.CircleBumper;
import ADT.Gadget;
import ADT.LeftFlipper;
import ADT.Portal;
import ADT.RightFlipper;
import ADT.SquareBumper;
import ADT.TriangularBumper;
import ADT.Wall;

public class BoardGUI extends JPanel {
    Color backgroundColor = Color.BLACK;
    private final Board board;
    //double buffer variables
    Image dbImage;
    Graphics dbGraphics;
    
    public BoardGUI(Board board) {
        this.board = board;
        this.setPreferredSize(new Dimension(440, 440));
        setBackground(backgroundColor);
       
        setFocusable(true);
        requestFocusInWindow();
        repaint();
        new Timer(50, paintTimer).start();


    }
    @Override
    public void paintComponent(Graphics g) {
        
        Graphics2D g2 = (Graphics2D) g;
        fillWindow(g2);
        for (Gadget gadget: board.getGadgets()){
            gadget.draw(g2);
        }
      
        for (Ball ball:board.getBalls()){
            ball.draw(g2);
        }
        for (Wall wall:board.getWalls()){
            wall.draw(g2);
        }
        
        
    }
    private void fillWindow(final Graphics2D g) {
        g.setColor(backgroundColor);
        g.fillRect(0,  0,  getWidth(), getHeight());
    }
    
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame window = new JFrame("Pingball");
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.setLayout(new BorderLayout());
                final Board board = new Board("b", 25, .025, .025);
                Ball ball = new Ball("b", 3.5,13.5,10,0,.25);
                board.addGadget(new SquareBumper("sq", 4, 17));
                board.addGadget(new CircleBumper("c", 16, 8));
                board.addBall(ball);
                board.addGadget(new TriangularBumper("tr", 11, 11, 0));
                board.addGadget(new Portal("p", 10, 10, "", ""));
                Gadget abs = new Absorber("a", 0, 19, 20, 1);
                Gadget lf = new LeftFlipper("lf", 2, 2, 270);
                Gadget rf = new RightFlipper("rf", 4, 13, 180);
                lf.triggers(lf);
                rf.triggers(rf);
                abs.triggers(abs);
                //board.addGadget(abs);
                board.addGadget(lf);
                board.addGadget(rf);
                BoardGUI canvas = new BoardGUI(board);
                
                window.add(canvas, BorderLayout.CENTER);
                window.pack();
                window.setVisible(true);
         
            }
        });
        
    
    }   
    
    Action paintTimer = new AbstractAction(){
       public void actionPerformed(ActionEvent e){
           board.update();
           repaint();
       }
   };
   /**
    * Method for double buffering, so the image doesn't flicker
    */
   public void update(Graphics g){
        
       if (dbImage==null){
           dbImage = createImage(this.getSize().width, this.getSize().height);
           dbGraphics = dbImage.getGraphics();
       }
       dbGraphics.setColor(this.getBackground());
       dbGraphics.fillRect(0, 0, this.getSize().width, this.getSize().height);
       dbGraphics.setColor(getForeground());
       paint(dbGraphics);
       g.drawImage(dbImage,0,0,this);
       
   }
   
}
