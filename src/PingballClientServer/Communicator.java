package PingballClientServer;

import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import ADT.Board;
import ADT.Gadget;

public class Communicator implements Runnable{
	private Socket clientSocket = null;
	private Board board = null;
	
	//private final ChatGUI[] chatGUIs = new ChatGUI[4];
	
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
			System.out.println("in or out failed");
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
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						ArrayList<String> hitwall = board.whichWallGotHit();
						
						if(hitwall.size()!=0){//if ball hits a wall
							synchronized(out){
								// sample output: hit NAMEofBoard wallNum  NAMEofBall x y xVel yVel
								
							    for(String hitMessage: hitwall){
                                    System.out.println(hitwall.size());
                                    System.out.println(hitMessage);
                                    out.println(hitMessage);
                                }
								board.updateWallHit();
							}	
						}
						
						ArrayList<String> portalHit = board.whichPortalGotHit();
                        if(!portalHit.isEmpty()){//if ball hits a wall
                            synchronized(out){
                                // sample output: hit NAMEofBoard wallNum  NAMEofBall x y xVel yVel
                                //System.out.println(portalHit);
                                for(String hitMessage: portalHit){
                                    System.out.println(portalHit.size());
                                    System.out.println(hitMessage);
                                    out.println(hitMessage);
                                }
                                board.updatePortalHit();
                            }   
                        }                       
                        // take chats off the queue and send to the server
                        /*for (int i = 0; i < chatSends.length; i++) {
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
                        }*/
                        
                        
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
	/*		        if (output.contains("chatWant") || output.contains("chatNo")) {
			        	synchronized(out) {
			        		out.println(output);
			        	}
			        	
			        	//System.out.println(output);
			        }*/
			    }   		
			}
		}catch (Exception e) {
			//e.printStackTrace();
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
		/*if(strangeTokens[0].equals("chatReceive")) {
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
		}*/
		
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
<<<<<<< HEAD
			System.out.println(input);
=======
			
>>>>>>> 19b72b87e75b2b73783a1db95faf60cd84ec8e43
			return null;
		}   

		if(tokens[0].equals("visible")) {
			// DON'T DO ANYTHING. BUSINESS AS USUAL
			return null;
		}
		
		/*if (tokens[0].equals("chatReject")) {
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
			System.out.println("inside chatCreated");
			final int wallNum = Integer.parseInt(tokens[1]);
			final String chatNeighbor = tokens[2];
			final Communicator c = this;
			
			// TODO: do GUI stuff
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
			System.out.println("wallnum Created: " + wallNum);
			System.out.println("is chatReceieves[wallnum] null: " + chatReceives[wallNum] == null);
			System.out.println("is chatSends[wallnum] null: " + chatSends[wallNum] == null);
			*/
//			SwingUtilities.invokeLater(new Runnable() {
//				public void run() {
/*//					cg.update
//				}
//			});
			
			
			//System.out.println("endingnnnn");
			return null;
		}*/
		
		if(tokens[0].equals("mark")) {
		
			int wallNum = Integer.parseInt(tokens[1]);
			String neighbor = tokens[2];
			board.giveNeighborsName(wallNum, neighbor);
			board.connectWall(wallNum, neighbor);
			
			String answer = null;
			/*boolean chatWant = askForChat(neighbor, wallNum);
			if (chatWant)
				answer = "chatWant " + wallNum + " " + this.board.boardname + " " + neighbor;
			else
				answer = "chatNo " + chatWant + " " + wallNum + " " + this.board.boardname + " " + neighbor;
			*/
			return answer;
		}
		
		if(tokens[0].equals("unmark")) {
			//System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			int wallNum = Integer.parseInt(tokens[1]);
			String neighbor = tokens[2];
			board.removeNeighborsName(wallNum, neighbor);
			return null;
		}

		//System.out.println(input+"Boooooooooooooooooooo");

		// Should never get here--make sure to return in each of the valid cases above.
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Send the client a chat invitation.
	 * @param chatNeighbor
	 * @param wallNum
	 * @return 
	 */
	/*private boolean askForChat(String chatNeighbor, int wallNum) {
		int chatRequested = JOptionPane.showConfirmDialog(new JFrame(), 
				             "Do you want to talk to " + chatNeighbor + " ?" +
				             		"\n If so, we'll let you know if both of you want to talk to each other", 
				             		"Chat Invitation", JOptionPane.YES_NO_OPTION);
//		//System.out.println("chatRequested" + chatRequested);
//		//System.out.println("Yes_no_option: " + JOptionPane.YES_NO_OPTION);
//		//System.out.println("Yes_no cancel option: " + JOptionPane.YES_NO_CANCEL_OPTION);
//		//System.out.println("ok cancel option: " + JOptionPane.OK_CANCEL_OPTION);
		return chatRequested == JOptionPane.YES_NO_OPTION;
	}
	
	public String chatReceive(int wallNum) {
		String response = null;
		try {
			response = chatReceives[wallNum].take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	public void chatSend(String msg, int wallNum) {
		try {
			chatSends[wallNum].put(msg);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	*/
}
