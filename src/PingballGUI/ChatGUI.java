package PingballGUI;

import static javax.swing.GroupLayout.Alignment.BASELINE; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import PingballClientServer.Communicator;


/**
 * A window that pops up when two boards agree to a chat
 * Concurrency argument for this GUI is made on top of Communicator class
 * 
 * @author jonathan
 *
 */
public class ChatGUI extends JFrame implements Runnable{

	private static final long serialVersionUID = 1L; // required by Serializable

	// components to use in the GUI
	private final JLabel chatInstruction;
	private final JTextField chatSend;
	private final JTable converseTable;
	private final DefaultTableModel tmodel;

	private String chatSendContent;
	
	private final int wallNum;
	private final Communicator backgroundC;


	private HashMap <String, Integer> chatReceiveToRow = new HashMap<String, Integer>();
	private int rowChat = 0;
	
	private final String me;
	private final String chatNeighbor;

	
	/**
	 * GUI window has an introductory statement of who you are talking to,
	 * a chat box to type into, and a conversation table
	 * @param wallNum
	 * @param c
	 * @param me
	 * @param chatNeighbor
	 */
	public ChatGUI(int wallNum, Communicator c, String me, String chatNeighbor) {

		this.me = me;
		this.chatNeighbor = chatNeighbor;

		this.wallNum = wallNum;
		this.backgroundC = c;

		chatInstruction = new JLabel(me + " talking to " + chatNeighbor);
		chatInstruction.setName("guessInstruction");

		chatSend = new JTextField();
		chatSend.setName("guess");
		TypeChatListener t = new TypeChatListener();
		chatSend.addActionListener(t);		

		final String[] colNames = {"Your Neighbor", "You"}; 
		tmodel = new DefaultTableModel(colNames, 0);
		tmodel.setColumnIdentifiers(colNames);
		converseTable = new JTable(tmodel);
		converseTable.setName("guessTable");
		
		Object[] row = {"Your Neighbor", "You"};
		tmodel.addRow(row);



		// Problem 2
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()					
						.addGroup(layout.createSequentialGroup()
								.addComponent(chatInstruction)
								.addComponent(chatSend)
								))
								.addComponent(converseTable)
						);

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(BASELINE)
						.addGroup(layout.createParallelGroup(BASELINE)
								.addComponent(chatInstruction)
								.addComponent(chatSend)
								))
								.addComponent(converseTable)
						);


	}

	/**
	 * pack and show GUI on the screen
	 */
	public void showGUI() {


		this.setTitle("ChatGUI");
		this.setSize(500,500);
		//chat.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setVisible(true);
	}






	/**
	 * Listens for user input for chat box
	 *
	 */
	private class TypeChatListener implements ActionListener {

		/**
		 * Listen for user input and put the input into the request 
		 * queue to be sent to the server
		 */
		@Override
		public void actionPerformed(ActionEvent e) {

			chatSendContent = chatSend.getText();
			chatSend.setText(""); // reset the field

			chatReceiveToRow.put(chatSendContent, rowChat);
			//System.out.println(chatReceiveToRow.containsKey(chatSendContent));
			
			backgroundC.chatSend(chatSendContent, wallNum);
			
			
			rowChat++;

			Object[] row = {null, chatSendContent};
			tmodel.addRow(row);

		}	

	}

	/**
	 * take chat responses off the queue and store on the table
	 */
	@Override
	public void run() {
		while (true) {
			String chatReceived = backgroundC.chatReceive(wallNum);
			System.out.println("chatReceived: " + chatReceived);
			Object[] row = {chatReceived, null};
			tmodel.addRow(row);
			rowChat++;
		}
		
	}
	
	

}




