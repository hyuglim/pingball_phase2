package PingballGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import ADT.Board;
import Parser.BoardFileFactory;

public class PingballGUI extends JFrame {

    private static final long serialVersionUID = 1L; // required by Serializable

    // components to use in the GUI
    private final JButton newPuzzleButton;
    private final JTextField newPuzzleNumber;
    private final JLabel puzzleNumber;
    private final JTextField guess;
    private final JTable guessTable;
    private final JLabel guessLabel;
    
    
    private final JButton loadFileButton ;
    private final JButton pauseButton;
    private final JButton resumeButton;
    private final JButton restartButton;
    private final JButton exitButton;
    
    
    private ArrayList<SwingWorker<String, Void>> myWorkers = new ArrayList<SwingWorker<String, Void>>();

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
        newPuzzleButton = new JButton();
        
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
        
        
        
        
        newPuzzleNumber = new JTextField();
        newPuzzleNumber.setName("newPuzzleNumber");

        puzzleNumber = new JLabel();
        puzzleNumber.setName("puzzleNumber");
        guess = new JTextField();
        guess.setName("guess");
        guessTable = new JTable();
        guessTable.setName("guessTable");

        guessLabel = new JLabel();
        guessLabel.setName("guessLabel");
        
        guessLabel.setText("Type a guess here: ");
        newPuzzleButton.setText("New Puzzle");
        //starts GUI with an already selected puzzle number 118.
        int puzzleId = 118;
        puzzleNumber.setText("Puzzle#" + puzzleId);       
        
        //Sets a data model for the guess Table with three columns.
        final DefaultTableModel model = new DefaultTableModel();
        guessTable.setModel(model);

        model.addColumn("Guess");
        model.addColumn("In Common");
        model.addColumn("Correct Position");
        
        //define layout
        GroupLayout layout = new GroupLayout(getContentPane());
        setLayout(layout);

        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addComponent(loadFileButton)
                        .addComponent(pauseButton)
                        .addComponent(resumeButton)
                        .addComponent(restartButton)
                        .addComponent(exitButton))
                );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()

                        .addComponent(loadFileButton)
                        .addComponent(pauseButton)
                        .addComponent(resumeButton)
                        .addComponent(restartButton)
                        .addComponent(exitButton))
                  );
        
        
        /**
         * Registers an action listener for the new puzzle button, so that
         * when the button is clicked, displays the number the user put in
         * the new puzzle JLabel. 
         * 
         * When clicked, calls the selectNewPuzzleId() 
         * method below, and if a user starts a new puzzle while results from 
         * guesses on a previous puzzle have not yet appeared in the table, discards those 
         * results and does not display them. Thus, walks through the SwingWorker list 
         * and cancels the running workers and refreshes the guess Table and clears the new Puzzle
         * Number text field. 
         */
       
        newPuzzleButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                
            }
        });
        
        
        /**
         * Registers an action listener for the newPuzzleNumber text field, so that
         * when the user hits return in the text box, displays the number the user put in
         * the new puzzle JLabel. 
         * 
         * When generated, calls the selectNewPuzzleId() 
         * method below, and if a user starts a new puzzle while results from 
         * guesses on a previous puzzle have not yet appeared in the table, discards those 
         * results and does not display them. Thus, walks through the SwingWorker list 
         * and cancels the running workers and refreshes the guess Table and clears the new Puzzle
         * Number text field. 
         */
        
        newPuzzleNumber.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                                      
            }
        });
        
        /**
         * Registers an action listener for the guess text field, so that
         * JTable guessTable displays the guesses with the appropriate 
         * information. Starts a new thread for each guess and handles each guess
         * separately. Furthermore, calls the sendGuess() method below and clears the guess
         * text field.
         */      
        guess.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                Thread listenerThread = new Thread(){
                    public void run(){                    
                           
                        guess.setText("");
                    }
                };
                listenerThread.start();
            }
        });

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
                main.setSize(500, 400);
            
                
                JMenuBar menubar = new JMenuBar();
                main.setJMenuBar(menubar);
                StringBuilder boardText = new StringBuilder("");
                BufferedReader br;
                try {
                    br = new BufferedReader(new FileReader("/Users/zulsarbatmunkh/pingball-phase2/src/Parser/sampleBoard.pb"));
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
                Board board = BoardFileFactory.parse(boardTextString);           
                BoardGUI boardGUI = new BoardGUI(board);
                main.add(boardGUI);
                main.setVisible(true);
                   
                JMenu file = new JMenu("Menu");
                menubar.add(file);
                JMenuItem exit = new JMenuItem("Exit");
                file.add(exit);
                                
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
            }
        });

    }
}
