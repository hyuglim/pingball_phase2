package PingballGUI;

import static javax.swing.GroupLayout.Alignment.BASELINE;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingWorker;


/**
 * Jotto GUI has a SwingWorker running in the background thread
 * each listener gets a separate jottomodel, so they're modifying their own data.
 * JTable is modified by a event dispatching thread, so the data is safe.
 * 
 */
public class ChatGUI extends JFrame {

	private static final long serialVersionUID = 1L; // required by Serializable

	// components to use in the GUI
	private final JButton newPuzzleButton;
	private final JTextField newPuzzleNumber;
	private final JLabel puzzleNumber;
	private final JLabel guessInstruction;
	private final JTextField guess;
	private final JTable guessTable;
	private final DefaultTableModel tmodel;

	private int puzzleInt;
	private String puzzleGuess;

	
	private HashMap <String, Integer> guessToRow = new HashMap<String, Integer>();
	private int rowGuess = 0;

	/**
	 * No input given
	 * Create all the GUI components here
	 * newPuzzle button, textfield for the user to enter new number, 
	 * textfield for user input, and table showing how the user is doing in the game
	 *  
	 * instantiate a thread for the model to keep running in the background
	 */
	public ChatGUI() {
		// components must be named with "setName" as specified in the problem set
		// remember to use these objects in your GUI!

		newPuzzleButton = new JButton("newPuzzle");
		newPuzzleButton.setName("newPuzzleButton");
		newPuzzleButton.addActionListener(new ButtonClickListener());

		newPuzzleNumber = new JTextField(10);
		newPuzzleNumber.setName("newPuzzleNumber");
		newPuzzleNumber.addActionListener(new TypeNumListener());

		//calculate randomly and initialize
		int random = (int) (Math.random() * 10000) + 1;
		puzzleNumber = new JLabel("puzzle #" + random);
		puzzleNumber.setName("puzzleNumber");
		puzzleInt = random;


		guessInstruction = new JLabel("Type a guess here: ");
		guessInstruction.setName("guessInstruction");

		guess = new JTextField();
		guess.setName("guess");
		TypeGuessListener t = new TypeGuessListener();
		guess.addActionListener(t);		

		final String[] colNames = {"guess", "numInCommon", "numCorrectPos"}; 
		tmodel = new DefaultTableModel(colNames, 0);
		tmodel.setColumnIdentifiers(colNames);
		guessTable = new JTable(tmodel);
		guessTable.setName("guessTable");



		// Problem 2
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
						.addComponent(puzzleNumber)
						.addComponent(newPuzzleButton)
						.addComponent(newPuzzleNumber)
						)
						.addGroup(layout.createSequentialGroup()
								.addComponent(guessInstruction)
								.addComponent(guess)
								)
								.addComponent(guessTable)
				);

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(BASELINE)
						.addComponent(puzzleNumber)
						.addComponent(newPuzzleButton)
						.addComponent(newPuzzleNumber))
						.addGroup(layout.createParallelGroup(BASELINE)
								.addComponent(guessInstruction)
								.addComponent(guess)
								)
								.addComponent(guessTable)
				);


	}

	/**
	 * pack and show GUI on the screen
	 */
	public static void createAndShowGUI() {
//		JottoGUI jotto = new JottoGUI();
//		jotto.setTitle("Jotto Game");
//		jotto.setSize(500, 300);
//		jotto.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//		jotto.setVisible(true);
		ChatGUI chat = new ChatGUI();
		chat.setTitle("ChatGUI");
		chat.setSize(500,500);
		chat.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		chat.setVisible(true);
	}


	/**
	 * Start the GUI Jotto client.
	 * @param args unused
	 */
	public static void main(final String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	/**
	 * 
	 * @param String puzzleNum
	 * @return a parsed Integer 
	 * 		or a randomly generated quizz number if invalid input given
	 */
	private int parsePuzzleNum(String puzzleNum) {
		int randomNum = (int) (Math.random() * 10000) + 1;
		int parsedInput;
		try {
			parsedInput = Integer.parseInt(puzzleNum);
			if (parsedInput > 0)
				return parsedInput;	
			else
				return randomNum;
		} catch (Exception e) {
			return randomNum;
		}

	}

	/**
	 * Listens for mouse click on the newPuzzle button
	 * and retrieve the user input inside the textfield
	 */
	private class ButtonClickListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			puzzleInt = parsePuzzleNum(newPuzzleNumber.getText());	

			puzzleNumber.setText("puzzle #"+puzzleInt); // reset the field
			newPuzzleNumber.setText(""); 			
			tmodel.setRowCount(0); // reset the table
			rowGuess = 0;
		}		
	}

	/**
	 * Listens for keyboard Enter from the guess textfield
	 * and retrieve the user input inside the textfield
	 */
	private class TypeNumListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			puzzleInt = parsePuzzleNum(newPuzzleNumber.getText());	

			puzzleNumber.setText("puzzle #"+puzzleInt);
			newPuzzleNumber.setText(""); 
			tmodel.setRowCount(0);
			rowGuess = 0;
			
		}	
	}

	/**
	 * Listens for user input for guesses
	 *
	 */
	private class TypeGuessListener implements ActionListener {

		/**
		 * Listen for user input and put the input into the request 
		 * queue to be sent to the server
		 */
		@Override
		public void actionPerformed(ActionEvent e) {

			puzzleGuess = guess.getText();
			guess.setText(""); // reset the field
			
			System.out.println("guessToRow");
			guessToRow.put(puzzleGuess, rowGuess);
			System.out.println(guessToRow.containsKey(puzzleGuess));
			
			System.out.println("rowGuess " + rowGuess);
			new PutInAnswer(puzzleGuess, rowGuess, puzzleInt).execute();
			
			rowGuess++;
			
			
			
			Object[] row = {puzzleGuess, null, null};
			tmodel.addRow(row);

		}	


	}

	private class PutInAnswer extends SwingWorker<String, Object> {
		

		public PutInAnswer(String guess, int row, int puzzleNum) {
		}
		/**
		 * constantly check for responses coming from the server
		 * process them appropriately and store them into the JTable
		 */
		@Override
		public String doInBackground() {
			return "";
		}
		
		@Override
		protected void done() {
			try {

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
	}	
	
	
}




