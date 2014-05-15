package PingballClientServer;

import java.io.BufferedReader;  
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import ADT.Board;
import ADT.Wall;
import Parser.BoardFileFactory;


// each client has two threads within it
// The first thread communicates with the server
// The second thread plays the game.
// The client will have a shared memory that gives information about
// which ball hit which wall.


public class PingballClient{
	private boolean isSinglePlayerMode = true;
	// key = ball, value = "invisible"|"visible". store whether the ball hit an invisible wall.
	private GamePlayer player;
	private Communicator messenger;
	private static Socket socket;
	// -1: no wall hit, 0: top wall hit,
	// 1: bottom wall hit, 2: right wall hit
	private Board myBoard;


	/**
	 * Constructor for a multiplayer PingballClient with only a board
	 * when a user connects with the server without a board file.
	 * @param socket the socket of the PingballClient to initialize.
	 */
	
	public PingballClient(Socket socket){
	    PingballClient.socket = socket;
	}
	
	/**
	 * Constructor for a PingballClient in a multiplayer game 
	 * that takes in input: socket and board. 
	 * @param socket the socket to initialize the PingballClient
	 * @param board the board of the PingballClient
	 */
	public PingballClient(Socket socket, Board board) {
	    this.myBoard = board;
	    PingballClient.socket = socket;
		//this.player = new GamePlayer(board);
		this.messenger = new Communicator(socket, board);
		
		new Thread(player).start();
		new Thread(messenger).start();
	}

	/**
	 * singleplayer constructor
	 * @param board the board to initialize the singleplayer game.
	 */
	public PingballClient(Board board) {
		this.player = new GamePlayer(board);		
		new Thread(player).start();
	}	
	   
	/**
	 * Return a boolean indicating whether the game is in a single player mode. 
	 * @return true if the game is in a single player mode, else false. 
	 */
	public boolean isSinglePlayerMode() {
		return isSinglePlayerMode;
	}

	/**
	 * Set the game to single player mode.
	 * @param isSinglePlayerMode
	 */
	public void setSinglePlayerMode(boolean isSinglePlayerMode) {
		this.isSinglePlayerMode = isSinglePlayerMode;
	}
	
	/**
	 * read command line arguments
	 * @param args
	 */
	public static void main(String[] args) {
		int port = 10987; // default port
		String host = ""; // if no host provided, go to single player mode
		File file = null;
	
		Queue<String> arguments = new LinkedList<String>(Arrays.asList(args));
		System.out.println(arguments);
		try {
			//command line parsing
			while ( ! arguments.isEmpty()) {
				String flag = arguments.remove();
				try {
					if (flag.equals("--host")) {
						host = arguments.remove();
					}
					if (flag.equals("--port")) {
						port = Integer.parseInt(arguments.remove());
					}
					if (arguments.size()==0) { //the last argument must be file
						//String filePath = arguments.remove();
						file = new File(flag);
						
						if ( ! file.isFile()) {
							throw new IllegalArgumentException("file not found: \"" + file + "\"");
						}
					} 
				} catch (NoSuchElementException nsee) {
					throw new IllegalArgumentException("missing argument for " + flag);
				} catch (NumberFormatException nfe) {
					throw new IllegalArgumentException("unable to parse number for " + flag);
				}
			}

		} catch (IllegalArgumentException iae) {
			iae.printStackTrace();
			System.err.println(iae.getMessage());
			System.err.println("usage: PingballClient [--host HOST] [--port PORT] FILE");
			return;
		}

		//starting threads and shit
		Board board =null;
		//single player mode
		if (host.equals("")) {
			try {				
				System.out.println("single player");
				StringBuilder boardText = new StringBuilder("");
		        BufferedReader br = new BufferedReader(new FileReader(file));
		        for(String line = br.readLine(); line != null; line = br.readLine()){
		            boardText.append('\n'+line);
		        }
		        String boardTextString = boardText.toString().substring(1);
		        board = BoardFileFactory.parse(boardTextString);		
		        br.close();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		} else { //multiplayer mode
			try {
				System.out.println("multiplayer");
				StringBuilder boardText = new StringBuilder("");
                BufferedReader br = new BufferedReader(new FileReader(file));
                for(String line = br.readLine(); line != null; line = br.readLine()){
                    boardText.append('\n'+line);
                }
                String boardTextString = boardText.toString().substring(1);
                board = BoardFileFactory.parse(boardTextString);
				socket = new Socket(host, port);
				br.close();
			} catch (Exception e) {
				e.printStackTrace();			
			}
		}						
        while(true){
            try{
                Thread.sleep(50);
            } catch(Exception e){
                e.printStackTrace();
            }
        }
	}
	
	/**
	 * Sets the player to a new player with given board
	 * @param board the board to start game player.
	 */
	public void setBoard(Board board){
	    this.player = new GamePlayer(board);
	}
	
	/**
	 * Closes the socket of this client and removes this board's 
	 * all walls' connection.
	 * @throws IOException if the socket cannot be closed
	 */
	public void close() throws IOException{
	    socket.close();
	    for(Wall wall: this.myBoard.getWalls()){
	        wall.removeConnection();
	    }
	}
}
