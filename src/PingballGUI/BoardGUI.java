package PingballGUI;

/**
 * BoardGUI: JPanel inside the JFrame and displays board game. 
 * Takes a board instance and a String representing the initial state of the board
 * and uses Swing timer to 
 * 
 * Ratio: one board cell = 20 pixels!!!
 */
import java.awt.Color; 
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
<<<<<<< HEAD
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
=======
>>>>>>> 0c365f86e7b4d00580466d3b98ae81f0549b733b

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import ADT.Ball;
import ADT.Board;
import ADT.Gadget;
import ADT.Wall;
import Parser.BoardFileFactory;

public class BoardGUI extends JPanel {   
    private static final long serialVersionUID = 1L; 
    private final Timer myTimer;
    //private final Timer myChatTimer;

    private Board board;
    private String boardText;
    private final Color backgroundColor = Color.BLACK;
    //double buffer variables
    Image dbImage;
    Graphics dbGraphics;
    private boolean firstTime;

    /**
     * Constructor for the BoardGUI:
     * @param board the board to be simulated 
     * @param boardText the String representing
     * the initial state of the board
     */
    public BoardGUI(Board board, String boardText) {
        this.board =  board;
        this.boardText = boardText;
        this.setPreferredSize(new Dimension(440, 440));
        this.firstTime = true;
        setBackground(backgroundColor);     
        setFocusable(true);
        requestFocusInWindow();
        repaint();
        this.myTimer = new Timer(50, paintTimer);
        this.myTimer.start();      
    }
    
    /**
     * 
     */
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        fillWindow(g2);
        for (Gadget gadget: board.getGadgets()){
            if (gadget.isHit()){
                gadget.drawAnother(g2);
                gadget.setNotHit();
            }
            else{
                if (firstTime){
                    gadget.draw(g2);
                    
                }
                else if (gadget.doesFlip()){
                    gadget.draw(g2);
                    }
            }
        }
        for (Ball ball:board.getBalls()){
            ball.draw(g2);
        }
        for (Wall wall:board.getWalls()){
                wall.draw(g2);          
        }
    }
    
    /**
     * Sets the 
     * @param g 
     */
    private void fillWindow(final Graphics2D g) {
        g.setColor(backgroundColor);
        g.fillRect(0,  0,  getWidth(), getHeight());
    }
    
    /**
     * 
     */
    Action paintTimer = new AbstractAction(){
       public void actionPerformed(ActionEvent e){
           Thread t = new Thread(new Runnable() {
            
            @Override
            public void run() {
                board.update();
                SwingUtilities.invokeLater(new Runnable() {
                    
                    @Override
                    public void run() {
                        repaint();
                        
                    }
                });
                
            }
        });
           t.start();
               
                    
           
       }
   };
   
   /**
    * Method for double buffering, so when the ball moves and the
    * subsequent image is displayed on the screen, the image doesn't flicker
    */
   
   public void update(Graphics g){      
       if (dbImage == null){
           dbImage = createImage(this.getSize().width, this.getSize().height);
           dbGraphics = dbImage.getGraphics();
       }
       dbGraphics.setColor(this.getBackground());
       dbGraphics.fillRect(0, 0, this.getSize().width, this.getSize().height);
       dbGraphics.setColor(getForeground());
       paint(dbGraphics);
       g.drawImage(dbImage, 0, 0, this);   
   }

   /**
    * Stops the running board game by stopping the timer.
    */
   public void stop(){
       myTimer.stop();
   }
   
   /**
    * Starts or resumes the board game by starting the
    * timer.
    */
   public void start(){
       myTimer.start();
   }

   /**
    * Restarts the board game by stopping the timer and 
    * updating this boardGUI's board to its initial state and then 
    * starts the timer.
    */
   public void restart(){
       myTimer.stop();
       board = BoardFileFactory.parse(boardText);   
       myTimer.start();
   }

   /**
    * Returns the board of this boardGui
    * @return the board
    */
   public Board getBoard(){
       return board;
   }
   
   /**
    * Returns a boolean indicating whether the board game on this boardGUI
    * is running or not by checking if the timer is running. If the timer is running
    * then the board game is running.
    * @return true if the timer is running, else false.
    */
   public boolean isRunning(){
       return myTimer.isRunning();
   }
   
   /**
    * Updates the board of this boradGUI by the given newBoard
    * @param newBoard the new board to update this boardGUI's board
    */
   public void updateBoard(Board newBoard){
       this.board = newBoard;
   }
  
   /**
    * Updates the boardText of this boardGUI. When a different board file is
    * loaded, this method is called so that from then on, when a
    * board game is restarted, the new loaded board file is always updated to its
    * own initial state.
    * @param newBoardText the new Board text String denoting the new initial state
    * of the board.
    */
   public void updateBoardString(String newBoardText){
       this.boardText = newBoardText;
   }
   
   
   public static void main(String[] args){
       SwingUtilities.invokeLater(new Runnable() {
           public void run() {

               StringBuilder boardText = new StringBuilder("");
               BufferedReader br;
               try {
                   br = new BufferedReader(new FileReader("src/Parser/sampleBoard.pb"));
                   for(String line = br.readLine(); line != null; line = br.readLine()){
                       boardText.append('\n'+line);
                   }
               } catch (FileNotFoundException e) {
                   e.printStackTrace();
               } catch (IOException e) {
                   e.printStackTrace();
               }
               
               String boardTextString = boardText.toString().substring(1);
               Board newBoard = BoardFileFactory.parse(boardTextString);                
               BoardGUI boardGui = new BoardGUI(newBoard, boardTextString);               
               boardGui.setVisible(true); 
               boardGui.setSize(440, 440);
                            
           }
       });
   }
   
}
