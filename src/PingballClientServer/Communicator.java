package PingballClientServer;

import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import ADT.Board;
import ADT.Gadget;
import PingballGUI.ChatGUI;


/**
 * Overview and Concurrency Arguments for Chat Feature
 * 
 * Communicator is a thread owned by each client that communicates with the server.
 * Communicator is responsible for receiving messages from the server and giving messages to the server,
 * depending what happens inside the pingball game.
 * The argument for the actual pingball game proper can be found at the top of PingballServer.java
 * 
 * 
 * ###############################Chat Functionality ############################################# 
 * If both boards agree to come into the chat, then the Communicator thread receives a message from 
 * the server to create a ChatGUI. Each Communicator thread has four ChatGUIs at maximum for talking
 * to four of its joined neighbors. When a ChatGUI is started, two queues are initiated for putting 
 * chats to send to the neighbor and receiving chats from the neighbor. ChatGUI is initiated by SwingUtilities
 * invokeLater method. ChatGUI has a separate thread running that checks if there is something to 
 * take from the chatSends and Receives queues. 
 * 
 * 
 * ########################Concurrency argument#####################
 * Each Communicator thread can have at maximum eight queues for chat functionality. A send queue and a receive queue
 * for each of the four neighbors. Those queues are the only shared data between the Communicator thread 
 * and ChatGUI, and those queues are BlockingQueues, a threadsafe data type provided by Java. 
 * Each of those queues store an immutable data String. Thus, the shared data is safe from multithreading.
 *  
 * 
 * 
 * @author jonathan
 *
 */
public class Communicator implements Runnable{
	private Socket clientSocket = null;
	private Board board = null;
	
	private final ChatGUI[] chatGUIs = new ChatGUI[4];
	
	private final String[] chatNeighbors = new String[4];
	private final BlockingQueue<String>[] chatSends = new BlockingQueue[4];
	private final BlockingQueue<String>[] chatReceives = new BlockingQueue[4];

	/**
	 * Each communicator thread has a socket and a board
	 * @param socket
	 * @param board
	 */
	public Communicator(Socket socket, Board board) {
		this.clientSocket = socket;
		this.board = board;
	}

	/**
	 * handleConnection and then close the socket
	 */
	public void run() {
		try{
			//if connection successful
			handleConnection(clientSocket);
			clientSocket.close();

		} catch (IOException e) {
			System.err.println("in or out failed");
		}
	}

	/**
	 * getting message from the server
	 * @param socket
	 * @throws IOException
	 */
	private void handleConnection(Socket socket) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		final PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

		try{      	
			//tell the server name of the board
			out.println("name " + board.getName());

			/**
			 * spin off another thread to constantly check on the board 
			 * to see if a ball hit a wall.
			 */
			new Thread(){
				@SuppressWarnings("static-access")
				public void run() {
					while(true) {
						try {
							this.sleep(50);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						ArrayList<String> hitwall = board.whichWallGotHit();
						
						if(hitwall.size()!=0){//if ball hits a wall
							synchronized(out){
								// sample output: hit NAMEofBoard wallNum  NAMEofBall x y xVel yVel
								
							    for(String hitMessage: hitwall){
                                    out.println(hitMessage);
                                }
								board.updateWallHit();
							}	
						}
						
						ArrayList<String> portalHit = board.whichPortalGotHit();
                        if(!portalHit.isEmpty()){//if ball hits a portal
                            synchronized(out){
                                // sample output: hit NAMEofBoard wallNum  NAMEofBall x y xVel yVel
                                //System.out.println(portalHit);
                                for(String hitMessage: portalHit){
                                   
                                    out.println(hitMessage);
                                }
                                board.updatePortalHit();
                            }   
                        }                       
                        // take chats off the queue and send to the server
                        for (int i = 0; i < chatSends.length; i++) {
                        	try {
                        		
                        		if (chatSends[i] != null && chatSends[i].size() > 0) { // because we don't want this thread to block because of take() method
                        			System.out.println("chatSends[i] size " + i + " " + chatSends[i].size());
                        			String delim = "123456789";
                        			String msgToSend = "chatSend" + delim + board.boardname + delim + 
                        		                       chatNeighbors[i] + delim + i + delim + chatSends[i].take();
                        			//TODO: change the delimiter from space to something else
                        			// assume for now that the messages sent are one word long
                        			out.println(msgToSend);
                        		}						   	
								
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
                        }
					}			
				}
			}.start();
			//while server inputstream isn't closed
			for (String line = in.readLine(); line != null; line = in.readLine()) { 
			    String output = handleRequest(line); 
			    if (output != null) {
			        // when client disconnects, clear all balls
			        if (output.equals("kill")) {
			            //System.out.println("I am killed");
			            board.die();
			            board.clearAllBalls();
			            return;
			        }
			        if (output.contains("create")){
			        	synchronized(out) {
			        		out.println(output);
			        	}
			        }			        
			        if (output.contains("chatWant") || output.contains("chatNo")) {
			        	synchronized(out) {
			        		out.println(output);
			        	}	
			        	//System.out.println(output);
			        }
			    }   		
			}
		}catch (Exception e) {	
		}finally {
			out.close();
			in.close();
		}
	}

	/**
	 * Modify the board according to what the server says
	 * @param input
	 * @return a String telling the Client what to do
	 */
	private String handleRequest(String input) {
		System.out.println("input: " + input);
		String[] tokens = input.split(" ");
	
		// receiving messages from the chat
		String delim = "123456789";
		String[] strangeTokens = input.split(delim);
		if(strangeTokens[0].equals("chatReceive")) {
			//System.out.println("inside chatReceive");
			String chatNeighbor = strangeTokens[1];
			int wallNum = Integer.parseInt(strangeTokens[2]);
			String msgReceived = strangeTokens[3];
			
			System.out.println("wallnum checked: " + wallNum);
			
			try {
				chatReceives[wallNum].put(msgReceived);
				
			} catch (InterruptedException e) {
				//System.out.println("inside chatReceive");
				e.printStackTrace();
			}	
			return null;
		}
		
		//CONFIRMING IF BALL HIT AN INVISIBLE WALL
		if(tokens[0].equals("delete")) {
			// sample input: invisible NAMEofBALL x y xVel yVel
			String nameOfBall = tokens[1];
			// get rid of the ball with that name     
			board.deleteBall(nameOfBall);
			return null;
		}
		//CONFIRMING IF BALL HIT a portal
        if(tokens[0].equals("port")) {
            // sample input: invisible NAMEofBALL x y xVel yVel
            String originalBoardName = tokens[1];
            String portalName = tokens[2];
            String nameOfBall = tokens[3];
            float x = Float.parseFloat(tokens[4]);
            float y = Float.parseFloat(tokens[5]);
            float xVel = Float.parseFloat(tokens[6]);
            float yVel = Float.parseFloat(tokens[7]);
            float radius = Float.parseFloat(tokens[8]);
            
            if(board.getPortal(portalName) != null){
                Gadget portal = board.getPortal(portalName);
                board.insertBall(nameOfBall, portal.getX()-0.5, portal.getY()-0.5, xVel, yVel, radius);
                return null;
            }
           //put the ball a little outside the portal
            if (x<19){
                x = x+1;
            }
            else if(y<19){
                y = y+1;
            }
            else{
                x = x-1;
            }
            String messageToSend = "create " + originalBoardName + " " + nameOfBall + " " + x + " " + y + " " + xVel + " " + yVel + " " + radius;            
            return messageToSend;
        }      
		if(tokens[0].equals("delete")) {
            // sample input: invisible NAMEofBALL x y xVel yVel
            String nameOfBall = tokens[1];
            // get rid of the ball with that name     
            board.deleteBall(nameOfBall);
            return null;
        }
		if(tokens[0].equals("create")) {
		    //System.out.println(tokens);
			// sample input: invisible NAMEofBALL x y xVel yVel
			String nameOfBall = tokens[1];
			float x = Float.parseFloat(tokens[2]);
			float y = Float.parseFloat(tokens[3]);
			float xVel = Float.parseFloat(tokens[4]);
			float yVel = Float.parseFloat(tokens[5]);
			float radius = Float.parseFloat(tokens[6]);

			// ADD A NEW BALL AT X,Y LOC IN THE CLIENT 	
			board.insertBall(nameOfBall, x, y, xVel, yVel, radius);
			return null;
		}   
		if(tokens[0].equals("visible")) {
			// DON'T DO ANYTHING. BUSINESS AS USUAL
			return null;
		}
		if (tokens[0].equals("chatReject")) {
			//System.out.println("inside chatReject");
			String chatNeighbor = tokens[1];
			String msg = chatNeighbor + " does not want to talk to you.";
			Object[] options = {"Cancel"};
			
			JOptionPane.showOptionDialog(new JFrame(),
				    msg,
				    "Chat Invitation Response",
				    JOptionPane.CANCEL_OPTION,
				    JOptionPane.QUESTION_MESSAGE,
				    null,     //do not use a custom Icon
				    options,  //the titles of buttons
				    options[0]); //default button title
			
			return null;
		}
		if (tokens[0].equals("chatCreated")) {
			//System.out.println("inside chatCreated");
			final int wallNum = Integer.parseInt(tokens[1]);
			final String chatNeighbor = tokens[2];
			final Communicator c = this;
			
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					ChatGUI cg = new ChatGUI(wallNum, c, board.boardname, chatNeighbor);
					chatGUIs[wallNum] = cg;
					chatNeighbors[wallNum] = chatNeighbor;
					chatSends[wallNum] = new LinkedBlockingQueue<String>();
					chatReceives[wallNum] = new LinkedBlockingQueue<String>();					
					new Thread(cg).start(); 				
					cg.showGUI();
				}				
			});			
			return null;
		}		
		if(tokens[0].equals("mark")) {		
			int wallNum = Integer.parseInt(tokens[1]);
			String neighbor = tokens[2];		
			board.giveNeighborsName(wallNum, neighbor);
			board.connectWall(wallNum, neighbor);			
			String answer = null;
			boolean chatWant = askForChat(neighbor, wallNum);
			if (chatWant)
				answer = "chatWant " + wallNum + " " + this.board.boardname + " " + neighbor;
			else
				answer = "chatNo " + chatWant + " " + wallNum + " " + this.board.boardname + " " + neighbor;			
			return answer;
		}
		if(tokens[0].equals("unmark")) {			
			int wallNum = Integer.parseInt(tokens[1]);
			String neighbor = tokens[2];
			board.removeNeighborsName(wallNum, neighbor);
			return null;
		}
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Send the client a chat invitation.
	 * @param chatNeighbor the String representing the neighbor
	 * @param wallNum the integer representing the wall
	 * @return the yes no option pane answer
	 */
	
	private boolean askForChat(String chatNeighbor, int wallNum) {
		int chatRequested = JOptionPane.showConfirmDialog(new JFrame(), 
				             "Do you want to talk to " + chatNeighbor + " ?" +
				             		"\n If so, we'll let you know if both of you want to talk to each other", 
				             		"Chat Invitation", JOptionPane.YES_NO_OPTION);
		return chatRequested == JOptionPane.YES_NO_OPTION;
	}
	
	/**
	 * Receives a chat from the clients
	 * @param wallNum the integer representing the wall number
	 * @return the response message received from the chat.
	 */
	public String chatReceive(int wallNum) {
		String response = null;
		try {
			response = chatReceives[wallNum].take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	/**
	 * Sends the messages from the clients
	 * @param msg the message to send
	 * @param wallNum the integer representing the wall.
	 */
	public void chatSend(String msg, int wallNum) {
		try {
			chatSends[wallNum].put(msg);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
