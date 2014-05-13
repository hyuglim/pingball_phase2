package PingballGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import ADT.Board;
import Parser.*;
import PingballClientServer.PingballClient;

public class PingballGUI extends JFrame implements ActionListener{

    private static final long serialVersionUID = 1L; // required by Serializable

    private final JButton loadFileButton ;
    private final JButton pauseButton;
    private final JButton resumeButton;
    private final JButton restartButton;
    private final JButton exitButton;
    

    private final JMenuBar bar;
    private final JRadioButtonMenuItem red;
    private final JRadioButtonMenuItem blue;
    private final JRadioButtonMenuItem green;
    private BoardGUI boardGui;

    private JPanel boardPanel;
    private final JMenu myMenu;
    private PingballClient myClient;
    
    private Board board = new Board("Sample", 0, 0, 0);
    /**
     * Constructor for the Jotto playing GUI. Creates a JottoGui() with
     * JButton() newPuzzleButton, JTextField() newPuzzleNumber that let the user
     * choose a new puzzle ID number,
     * JLabel() puzzleNumber that displays the puzzle number that the user put in,
     * JTextField() guess with a JLabel() guessLabel that let the user type the guess,
     * JTable() guessTable that displays the guesses that the user made for a
     * particular puzzleNumber,
     */
    public PingballGUI() {
        // components must be named with "setName" as specified in the problem set
        // remember to use these objects in your GUI!

        loadFileButton = new JButton();
        loadFileButton.setName("loadFileButton");
        loadFileButton.setText("load File:");

        pauseButton = new JButton();
        pauseButton.setName("pauseButton");
        pauseButton.setText("pause");

        resumeButton = new JButton();
        resumeButton.setName("resumeButton");
        resumeButton.setText("resume");

        restartButton = new JButton();
        restartButton.setName("restartButton");
        restartButton.setText("restart");

        exitButton = new JButton();
        exitButton.setName("exitButton");
        exitButton.setText("exit");

        myMenu = new JMenu("Background Color");
        bar = new JMenuBar();

        boardPanel = new JPanel();
        boardPanel.setPreferredSize(new Dimension(440, 440));
        
        JMenuBar menubar = new JMenuBar();     
        setJMenuBar(menubar);      
        JMenu file = new JMenu("Menu");

        JMenuItem loadFile = new JMenuItem("Load a file");
        file.add(loadFile);
        
        JMenuItem connectToServer = new JMenuItem("Connect To Server");
        file.add(connectToServer);
        
        JMenuItem disconnectFromServer = new JMenuItem("Disconnect From Server");
        file.add(disconnectFromServer);
        
        JMenuItem exit = new JMenuItem("Exit");
        file.add(exit);
        
        menubar.add(file);
        
        JMenu help = new JMenu("help");
        menubar.add(help);
        JMenuItem about = new JMenuItem("About");
        help.add(about);

        class exitAction implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }                  
        }
        exit.addActionListener(new exitAction());
        
        red = new JRadioButtonMenuItem("Red");
        blue = new JRadioButtonMenuItem("Blue");
        green= new JRadioButtonMenuItem("Green");

        bar.add(myMenu);
        myMenu.add(red); 
        myMenu.add(blue);
        myMenu.add(green);

        ButtonGroup group = new ButtonGroup();
        group.add(red);
        group.add(blue);
        group.add(green);

        add(bar);

        red.addActionListener(this);
        blue.addActionListener(this);
        green.addActionListener(this);
        
        this.pack();

        setLayout(new FlowLayout());
        
        /**
         * Registers an action listener for the connectToServer button, so that
         * when the button is clicked, opens up a pop-up JOption window asking for user input for
         * the Server name and port number and then, connects the user interface with the 
         * given Server.
         */

        connectToServer.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                
                String serverName = JOptionPane.showInputDialog("Enter Server name: ");
                String serverId = JOptionPane.showInputDialog("Enter Server port: ");
                
                try {
                    if(board!=null){
                        myClient = new PingballClient(new Socket(serverName, Integer.parseInt(serverId)), board);
                    }
                    
                } catch (UnknownHostException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
            }
        });
        
        /**
         * Registers an action listener for the disconnectFromServer button, so that
         * when the button is clicked, closes the user's connection with the Server.
         */

        disconnectFromServer.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                try {
                    myClient.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        
        /**
         * Registers an action listener for the pauseButton, so that
         * when the button is clicked, pauses the currently running board.
         */
        
        pauseButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                boardGui.stop();
            }
        });

        /**
         * Registers an action listener for the pauseButton, so that
         * when the button is clicked, resumes the currently running board.
         */
        resumeButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                boardGui.start();
            }
        });


        /**
         * Registers an action listener for the restartButton, so that
         * when the button is clicked, restarts the boardGui JPanel and 
         * initializes the currently running board from
         * its initial state.
         */
        restartButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                boardGui.restart();
            }
        });


        /**
         * Registers an action listener for the exitButton, so that
         * when the button is clicked, exits the user interface. 
         */
        exitButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                System.exit(0);
            }
        });
        
        /**
         * Registers an action listener for the loadFile JMenuItem, so that
         * when this item is selected from the menu, opens a file chooser
         * and lets the user to choose a board file.
         */
        loadFile.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                JFileChooser fc = new JFileChooser();
                fc.showOpenDialog(null);
                File file = fc.getSelectedFile();
                String filePath = file.getPath();
                StringBuilder boardText = new StringBuilder("");
                BufferedReader br;
                try {

                    br = new BufferedReader(new FileReader("src/Parser/sampleBoard.pb"));

                    br = new BufferedReader(new FileReader(filePath));
                    
                    for(String line = br.readLine(); line != null; line = br.readLine()){
                        boardText.append('\n'+line);
                    }
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                String boardTextString = boardText.toString().substring(1);
                board = BoardFileFactory.parse(boardTextString);  
                
                if(boardGui==null){
                    boardGui = new BoardGUI(board, boardTextString);             
                    add(pauseButton);
                    add(resumeButton);
                    add(restartButton);
                    add(exitButton);
                    getContentPane().add(boardGui);
                }else{
                    boardGui = new BoardGUI(board, boardTextString);
                }
                
                revalidate();
            }
        });
        
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new MyDispatcher());

    }

    /**
     * Class for key listener:
     * Listens for the key events: typed, pressed or released and checks
     * if the key triggers gadgets in the board and triggers those gadgets.
     * @author zulsarbatmunkh
     *
     */
    private class MyDispatcher implements KeyEventDispatcher{
        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                String key = KeyEvent.getKeyText(e.getKeyCode());
                String keyString = key.replaceAll(" ", "").toLowerCase();
                board.triggerDownKey(keyString);
                System.out.println("Key Pressed: " + keyString);
            } else if (e.getID() == KeyEvent.KEY_RELEASED) {
                String key = KeyEvent.getKeyText(e.getKeyCode());
                String keyString = key.replaceAll(" ", "").toLowerCase();
                board.triggerUpKey(keyString);
                System.out.println("Key Released: " + keyString);
            } else if (e.getID() == KeyEvent.KEY_TYPED) {
                System.out.println("Key Typed: " + KeyEvent.getKeyText(e.getKeyCode()));
            }
            return false;
        }
   }


    /**
     * Lets the user to change the background color of the user interface.
     */
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == red){
            this.getContentPane().setBackground(Color.RED);
        }

        if(e.getSource() == blue){
            this.getContentPane().setBackground(Color.BLUE);
        }

        if(e.getSource() == green){
            this.getContentPane().setBackground(Color.GREEN);
        }   
    } 
    


    /**
     * Start the GUI Pingball client.
     * @param args unused
     */
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                PingballGUI main = new PingballGUI();
                main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                main.setVisible(true);
                main.setSize(600, 600);
            }
        });

    }
}
