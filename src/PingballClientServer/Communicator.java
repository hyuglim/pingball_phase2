package PingballClientServer;

import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import ADT.Board;
import ADT.Gadget;

public class Communicator implements Runnable{
	private Socket clientSocket = null;
	private Board board = null;

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
			System.exit(-1);
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
						String hitwall = board.whichWallGotHit();
						
						if(! hitwall.equals("")){//if ball hits a wall
							synchronized(out){
								// sample output: hit NAMEofBoard wallNum  NAMEofBall x y xVel yVel
								out.println(hitwall);
								board.updateWallHit();
							}	
						}
						
						String portalHit = board.whichPortalGotHit();
                        if(! portalHit.equals("")){//if ball hits a wall
                            synchronized(out){
                                // sample output: hit NAMEofBoard wallNum  NAMEofBall x y xVel yVel
                                System.out.println(portalHit);
                                
                                out.println(portalHit);
                                board.updatePortalHit();
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
			            System.out.println("I am killed");
			            board.clearAllBalls();
			            return;
			        }

			        if (output.contains("create")){
			            out.println(output);
			            System.out.println(output);
			        }
			    }   		
			}
		} finally {
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

		String[] tokens = input.split(" ");
	
		
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
		    System.out.println(tokens);
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
		
		if(tokens[0].equals("mark")) {
		
			int wallNum = Integer.parseInt(tokens[1]);
			String neighbor = tokens[2];
			board.giveNeighborsName(wallNum, neighbor);
			board.connectWall(wallNum, neighbor);
			return null;
		}

		System.out.println(input+"Boooooooooooooooooooo");

		// Should never get here--make sure to return in each of the valid cases above.
		throw new UnsupportedOperationException();
	}
}
