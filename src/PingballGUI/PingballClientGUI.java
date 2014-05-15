package PingballGUI;


/**
 * The Pingball playing GUI user interface that displays the running game as a   
 * graphical user interface that pops up in a new window. 
 * 
 * The commands for the operations: loading a board from a file, connecting to 
 * a server by specifying a host and port, disconnecting from a server 
 * and exiting from the user interface are respectively supported
 * through the menu items: loadFile, connectToServer, disconnectFromServer and exit.
 * 
 * Once a valid board file is loaded, adds BoardGUI to the interface, starts displaying the 
 * board game corresponding to the chosen board file and adds new JButtons to the user interface: pauseButton, 
 * resumeButton, restartButton and exitButtion that support the commands for the operations: 
 * pausing the game, resuming the game, restarting the game from the initial state of the board
 * and exiting from the user interface. 
 * 
 * Furthermore, has additional about section in the help section of the menu that
 * gives the users directions on how to play the game and a JMenuBar on the user interface
 * that lets the user change the background color of the user interface.
 * 
 * Thread safety/Concurrency Argument:
 * 
 * Board instance is confined to a single thread which is responsible for its graphics representation.
 * All the modification of the board happen in a single thread, and all its fields that get mutated 
 * (balls, walls and gadgets) are wrapped into synchronized lists and can be accessed only by acquiring a
 *  lock on this object. Also, all the Swing related changes happen in a separate thread. Thus,
 *  we can claim that PingballClientGUI object is threadsafe.
 * 
 */
import java.awt.Color;  
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingUtilities;

import ADT.Board;
import Parser.*;
import PingballClientServer.PingballClient;

public class PingballClientGUI extends JFrame implements ActionListener{

    private static final long serialVersionUID = 1L; // required by Serializable
    
    private final JButton pauseButton;
    private final JButton resumeButton;
    private final JButton restartButton;
    private final JButton exitButton;

    private final JMenuBar bar;
    private final JMenuItem loadFile;
    private final JMenuItem connectToServer;
    private final JMenuItem disconnectFromServer;
    private final JMenuItem exit;
    
    private final JMenu myMenu;
    private final JRadioButtonMenuItem red;
    private final JRadioButtonMenuItem blue;
    private final JRadioButtonMenuItem green;
    
    private BoardGUI boardGui;
    private PingballClient myClient; 
    private Board board;
    
    public PingballClientGUI() {
        
        /* The following buttons are added to the user interface screen only 
         * when a valid board file is loaded.
         */
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
        
        /*
         * add MenuBar that changes the background color of the GUI
         */
        bar = new JMenuBar();
        myMenu = new JMenu("Background Color");
        myMenu.setName("Background color chooser");
        bar.setName("menu bar");
        bar.add(myMenu);
        red = new JRadioButtonMenuItem("Red");
        blue = new JRadioButtonMenuItem("Blue");
        green= new JRadioButtonMenuItem("Green");
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
        
        /*
         * Add MenuBar for the loadFile, connectToServer, disconnectFromServer
         * and exit commands.
         */
        JMenuBar menubar = new JMenuBar();     
        setJMenuBar(menubar);      
        JMenu file = new JMenu("Menu");
        loadFile = new JMenuItem("Load a file");
        connectToServer = new JMenuItem("Connect To Server");  
        disconnectFromServer = new JMenuItem("Disconnect From Server"); 
        exit = new JMenuItem("Exit");
        file.add(loadFile);
        file.add(connectToServer);
        file.add(disconnectFromServer);
        file.add(exit);
        menubar.add(file);
        
        /* 
         * Add help JMenu that has a section about that gives directions on how to play.
         */
        JMenu help = new JMenu("help");
        menubar.add(help);
        JMenuItem about = new JMenuItem("About");
        help.add(about);

        this.pack();
        setLayout(new FlowLayout());
        
        /**
         * Registers an action listener for the connectToServer button, so that
         * when the button is clicked, opens up a pop-up JOption window asking for user input for
         * the Server name and port number and then, connects the user interface with the 
         * given Server by creating an instance of PingBallClient and connecting it
         * to the server specified by user input and a boardGui that displays 
         * the board of the PingBallClient.
         * If the user input for the Server name and port are invalid, or the connection
         * is refused opens up a pop-up windows showing the corresponding error messages.
         */

        connectToServer.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                if(boardGui!=null){
                    boardGui.stop();
                }
                int serverId = 0;
                String serverName = JOptionPane.showInputDialog("Enter Server name: ");
                if(serverName!=null){
                    JOptionPane.showMessageDialog(null, "Connecting to Server: " + serverName.replaceAll(" ", ""));
                    String serverIdString = JOptionPane.showInputDialog("Enter Server port: ");
                    if(serverIdString!=null){
                        try{
                            serverId = Integer.parseInt(serverIdString);
                            if(serverId<0 || serverId>65535){
                                JOptionPane.showMessageDialog(null, "Invalid Port Number: " + serverIdString + "\nPort number should be an integer in the "
                                        + "range 0 to 65535 inclusive, Connect again!", "Warning!", JOptionPane.WARNING_MESSAGE);
                            }
                            try {
                                if(board!=null){
                                    JOptionPane.showMessageDialog(null, "Succesfully connected to the Server: " +
                                            serverName + " with port: " + serverId);
                                
                                    myClient = new PingballClient(new Socket(serverName, serverId), board);
                                }    
                            } catch (UnknownHostException e) {
                                JOptionPane.showMessageDialog(null, "Connecting to invalid Server: " + serverName 
                                        + " with port: " + serverId + " connect again!");
                            } catch (IOException e) {
                                JOptionPane.showMessageDialog(null, "Connection refused! Connect again!");
                            }
                        }catch(Exception e){
                            JOptionPane.showMessageDialog(null, "Invalid Port Number: " + serverIdString + "\nPort number should be an integer in the "
                                    + "range 0 to 65535 inclusive, Connect again!", "Warning!", JOptionPane.WARNING_MESSAGE);
                        }
                    }                   
                }
                if(boardGui!=null){
                    boardGui.start();
                }
                requestFocusInWindow();
            }
        });
        
        /**
         * Registers an action listener for the disconnectFromServer button, so that
         * when the button is clicked, closes the user's connection with the Server.
         */

        disconnectFromServer.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                if(boardGui!=null){
                    boardGui.stop();
                }
                try {
                    if(myClient!=null){
                        myClient.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }               
                boardGui.start();
                requestFocusInWindow();
            }
            
        });
        
        /**
         * Registers an action listener for the pauseButton, so that
         * when the button is clicked, pauses the currently running boardGui.
         */
        
        pauseButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                boardGui.stop();
                requestFocusInWindow();
            }
        });

        /**
         * Registers an action listener for the pauseButton, so that
         * when the button is clicked, resumes the currently running boardGui.
         */
        resumeButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                boardGui.start();
                requestFocusInWindow();
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
                try {
                    if(myClient!=null){
                        myClient.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } 
                requestFocusInWindow();
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
         * Registers an action listener for the exit JMenuItem in the JMenubar,
         * so that when this item is selected, exits the user interface.
         */
        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }                  
        });
        
        
        /**
         * Registers an action listener for the about option in the help JMenu,
         * detailing the directions on how to play
         */
        about.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "To start a single machine play: Choose"
                        + " load file option from the Menu and choose a valid\n"
                        + "       board file once you load a file you'll have more buttons available for your "
                        + "board game\n"
                        + "To connect with the Server: Choose the connectToServer option from the Menu\n"
                        + "       and provide a valid host name and port, if you want to play multiplayer game\n"
                        + "       and connect your board with other boards, you must first load a board file and\n"
                        + "       then connect to the server\n"
                        + "To disconnect from Server: Choose the disconnectFromServer option from the Menu\n"
                        + "To exit: Choose the exit option from the Menu");
                requestFocusInWindow();
            
            }
            
        });
        
        /**
         * Registers an action listener for the loadFile JMenuItem, so that
         * when this item is selected from the menu, opens a file chooser
         * and lets the user to choose a board file. If the chosen file is invalid, then
         * opens up a message saying that the board file is invalid. 
         */  
        
        loadFile.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                if(boardGui!=null){
                    boardGui.stop();
                }
                JFileChooser fc = new JFileChooser();
                fc.showOpenDialog(null);
                File file = fc.getSelectedFile();
                if(file!=null){
                    String filePath = file.getPath();
                    StringBuilder boardText = new StringBuilder("");
                    BufferedReader br;
                    try {
                        br = new BufferedReader(new FileReader(filePath));
                        for(String line = br.readLine(); line != null; line = br.readLine()){
                            boardText.append('\n'+line);
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    
                    String boardTextString = boardText.toString().substring(1);
                    try{
                        Board newBoard = BoardFileFactory.parse(boardTextString); 
                        if(boardGui==null){                
                            boardGui = new BoardGUI(newBoard, boardTextString);     
                            board = boardGui.getBoard();
                            add(pauseButton);
                            add(resumeButton);
                            add(restartButton);
                            add(exitButton);
                            getContentPane().add(boardGui);
                        }else{
                            boardGui.updateBoard(newBoard);
                            boardGui.updateBoardString(boardTextString);
                        }
                        revalidate();
                    } catch (Exception e){
                        JOptionPane.showMessageDialog(null, "Invalid board file! Load a valid board file!",
                                "Warning!", JOptionPane.WARNING_MESSAGE);
                    }                    
                }
                if(boardGui!=null){
                    boardGui.start();
                }
                requestFocusInWindow();
            }
            
        });  

        KeyListener listener = new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                String key = KeyEvent.getKeyText(e.getKeyCode());
                String keyString = key.replaceAll(" ", "").toLowerCase();
                if(boardGui!=null){
                    if(boardGui.isRunning()){
                        boardGui.getBoard().triggerDownKey(keyString);
                    }
                }
            }
            public void keyReleased(KeyEvent e) {
                String key = KeyEvent.getKeyText(e.getKeyCode());
                String keyString = key.replaceAll(" ", "").toLowerCase();
                if(boardGui!=null){
                    if(boardGui.isRunning()){
                        boardGui.getBoard().triggerUpKey(keyString);
                    }
                }
            }
        };

        KeyListener magical = new MagicKeyListener(listener);
        addKeyListener(magical);
    }

    /**
     * Lets the user to change the background color of the user interface.
     */
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == red){
            this.getContentPane().setBackground(Color.RED);
        }else if(e.getSource() == blue){
            this.getContentPane().setBackground(Color.BLUE);
        }else if(e.getSource() == green){
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
                PingballClientGUI main = new PingballClientGUI();
                main.setTitle("PingballGUI Client");
                main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                
                main.setVisible(true);
                main.setSize(600, 600);
            }
        });
    }
}
