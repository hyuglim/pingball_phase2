package PingballClientServer;

import java.io.BufferedReader;  
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

import ADT.Ball;
import ADT.Board;
import Parser.BoardFileFactory;


// each client has two threads within it
// The first thread communicates with the server
// The second thread plays the game.
// The client will have a shared memory that gives information about
// which ball hit which wall.


public class PingballClient{

	private boolean isSinglePlayerMode = true;
	// key = ball, value = "invisible"|"visible". store whether the ball hit an invisible wall.
	private ConcurrentHashMap<Ball, String> hitInvisibles = new ConcurrentHashMap<>();
	private GamePlayer player;
	private Communicator messenger;

	private static Socket socket;
	// -1: no wall hit, 0: top wall hit,
	// 1: bottom wall hit, 2: right wall hit
	private int whichWallHit = -1;


	/**
	 * multiplayer constructor
	 * @param socket
	 * @param board
	 */
	
	public PingballClient(Socket socket){
	}
	
	public PingballClient(Socket socket, Board board) {
	    this.socket = socket;
		this.player = new GamePlayer(board);
		this.messenger = new Communicator(socket, board);
		
		new Thread(player).start();
		new Thread(messenger).start();
	}

	/**
	 * singleplayer constructor
	 * @param board
	 */
	public PingballClient(Board board) {
		this.player = new GamePlayer(board);		
		new Thread(player).start();
	}	
	   
	/**
	 * 
	 * @return single player
	 */
	public boolean isSinglePlayerMode() {
		return isSinglePlayerMode;
	}

	/**
	 * 
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
				System.out.println("flag: "+ flag);
				try {
					if (flag.equals("--host")) {
						host = arguments.remove();
						System.out.println("host: " + host);
					}
					if (flag.equals("--port")) {
						port = Integer.parseInt(arguments.remove());
						System.out.println("port: " + flag);
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

		System.out.println("port: " + port);
		System.out.println("host: " + host);
		System.out.println("file: " + file);

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
				PingballClient player = new PingballClient(board);
				
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
				PingballClient player = new PingballClient(socket, board);
				
				
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
	
	public void setBoard(Board board){
	    this.player = new GamePlayer(board);
	}
	
	public void close() throws IOException{
	    socket.close();
	}
}
