package PingballClientServer;

import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;


/**
 * ##############Server/Client Overview and Concurrency Arguments for Pingball Game###############
 * 
 *             Overview:
 * The Server has n+1 threads, given n clients.
 * The first thread continuously listens for the user to input join commands.
 * The last n threads communicate with the client through the socket.
 * 
 * Each client has 4 threads running.
 * (1) The first thread GamePlayer plays the game.
 * (2) The main thread inside PingballClient displays the game.
 * (3) The third thread Communicator communicates with the server through the socket.
 * (4) The fourth thread inside Communicator continuously checks the state of the board at regular intervals
 *     to see if a ball collided with a wall.
 * 
 *             Strategy:
 * When a client first connects to the server, it sends to the server the name of its board. 
 * The server stores the name and other info inside the hashmap which is explained below in the comment.
 * When a user types in a join command, the thread inside the server listens and updates the hashmap for 
 * both boards mentioned in the command.
 * 
 * When (4) determines that a ball did collide with a wall, the thread sends to the server
 * the name of the ball, its location, and its velocity to the server. The server looks up 
 * the wall in the hashmap to determine if it's invisible. If the ball hit a visible wall, 
 * the server sends message back to the client saying play the game as usual. If the ball hit an 
 * invisible wall, the server sends a message back saying the client should delete the ball.
 * The server also sends a message to the client's neighbor to create a new ball at an appropriate location.
 * 
 *            Concurrency Argument:
 * The strategy uses two shared memory structures. On the server side, the n threads access the hash map, and 
 * the last thread modify the hash map. We are using ConcurrentHashMap which provides thread-safe operations.
 * Since the dictionary key comprises unique board names, when a single ball hits a wall, each client thread will
 * be accessing different keys, so the methods do not block. When two balls hit a wall from a single client, 
 * the server calls a get method on the hashmap on the same key which is a thread safe method for ConcurrentHashMap
 * according to Oracle.com. 
 * 
 * On the client side, we have four threads running.
 * (1) calls moveAllballs which accesses concurrentHashMap balls
 * (2) calls display() which accesses 2d String array and ConcurrentHashMap of positionofGadgets
 * (3) calls either insertBall or deleteBall which also accesses balls
 * (4) calls whichWallGotHit which accesses List of Walls and balls
 * 
 * (2) does not block because none of the other threads need to access 2d String array and positionofGadgets
 * (1),(3), and (4) access balls by calling a get method. As mentioned above, ConcurrentHashmap provides 
 * thread-safe get method. Therefore, there is no risk of interleaving here. (4) accesses List of Walls which
 * is not accessed by other threads after the board is initialized. We can apply the same argument for
 * the ConcurrentHashMap of positionofGadgets as well. PositionofGadgets does not mutate after initialization. 
 * There is no deadlock because none of the threads are waiting for each other to finish access 
 * to any of the data strcutures mentioned above.
 * 
 * Therefore, the access to the shared memory within Server and Client and between them is thread-safe.
 * 
 * @author jonathan
 *
 */
public class PingballServer {

	private final ServerSocket serverSocket;
	//ConcurrentHashMap<String, Tuple> created when joining the boards is as follows:
	//
	//                 top      bot  left right   top   bot   left right
	//{ "board1" : ([neighbor1, n2, n3,   n4] , [true, true, true, true], board1's socket, [true, false, false, true])
	//  "neighbor1": ([null, client1, null, null],[false, true, false, false], neighbor1's socket, [false, true, true, false])
	//  "board3" : ([n1,  null,   null, n4]   , [true, false, true, false], board3's socket, ...)
	//   ... }
	// Thus, each board name has a list of all the neighboring board names which are initialized as null.
	// Each board name also has a list of booleans indicating if the top, bottom, left, or right wall is 
	// invisible or not. Each board name has a socket that corresponds to that particular client thread.
	// The final list is a list of booleans that indicate if each neighbor has agreed to chat with that client
	// Quadruple has a list of strings and a list of booleans. 
	private ConcurrentHashMap<String, Quadruple<List<String>,List<Boolean>,Socket,List<Boolean>>> neighbors 
	= new ConcurrentHashMap<String, Quadruple<List<String>,List<Boolean>,Socket, List<Boolean>>>();
	


	/**
	 * 
	 * @param port
	 * @throws IOException
	 */
	public PingballServer(int port) throws IOException {
		this.serverSocket = new ServerSocket(port);

	}

	/**
	 * Method used for telling clients that their wall has been joined
	 * Gets all valid neighbors, get their sockets, and send messages.
	 * 
	 * @param name
	 * @throws IOException
	 */
	private void notifyBoardJoin(String name) throws IOException {
		Socket socket = neighbors.get(name).getThree();
		List<String> adj = neighbors.get(name).getOne();
		List<Boolean> invis = neighbors.get(name).getTwo();
		List<Boolean> chats = neighbors.get(name).getFour();
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

		for (int i = 0; i < adj.size(); i++) {
			if (invis.get(i) && chats.get(i)==null) {
				String neigh = adj.get(i);
				System.out.println("mark " + i + " " + neigh);

				out.println("mark " + i + " " + neigh);

			}
		}		
	}
	
	/**
	 * get the socket corresponding to chatName and send the message 
	 * to that board
	 * @param chatName
	 * @param msg
	 * @throws IOException
	 */
	private void notifyChat(String chatName, String msg) throws IOException{
			System.out.println("notifyChat: " + msg);
		
			Socket socket = neighbors.get(chatName).getThree();
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			out.println(msg);					
		
	}

	/**
	 * join boards horizontally. 
	 * Left board's right wall is joined with Right board's left wall
	 * @param left board
	 * @param right board
	 * @throws IllegalArgumentException
	 * @throws IOException 
	 */
	private void makeHorizNeighbors(String []words) throws IllegalArgumentException, IOException{
		if(!words[1].contains("_left") || !words[2].contains("_right")) {
			throw new IllegalArgumentException("Enter: h board1_left board2_right");
		}

		String left = words[1].split("_left")[0];
		String right = words[2].split("_right")[0];

		if (!neighbors.containsKey(left) || !neighbors.containsKey(right)) {
			throw new IllegalArgumentException("cannot join uncreated board");
		}

		//if left is in the dictionary, and the existing right neighbor does not 
		//match the current bottom neighbor
		String prevRight = neighbors.get(left).getOne().get(3);
		if (neighbors.get(left).getTwo().get(3)
				&& !prevRight.equals(right)) {
			setNullifyBoards(prevRight, 2);
		}

		//if right is in the dictionary, and the existing left neighbor does not
		//match the current left neighbor
		String prevLeft = neighbors.get(right).getOne().get(2);
		if (neighbors.get(right).getTwo().get(2)
				&& !prevLeft.equals(right)) {
			setNullifyBoards(prevLeft, 3);
		} 

		// send message to the left board
		setNeighborBoards(left, right, 3);
		notifyBoardJoin(left);

		// send message to the right board
		setNeighborBoards(right, left, 2);
		notifyBoardJoin(right);

		return;
	}

	/**
	 * join boards vertically
	 * Top board's bottom wall is joined with Bottom board's top wall
	 * @param top
	 * @param bottom
	 * @throws IOException 
	 */
	private void makeVerticNeighbors(String []words) throws IllegalArgumentException, IOException{
		if(!words[1].contains("_top") || !words[2].contains("_bottom")) {
			throw new IllegalArgumentException("Enter: v board1_top board2_bottom");
		}

		String top = words[1].split("_top")[0];
		String bottom = words[2].split("_bottom")[0];
		if (!neighbors.containsKey(top) || !neighbors.containsKey(bottom)) {
			//printNeighbors();
			System.out.println("cannot join uncreated board");
		}

		//if top is in the dictionary, and the existing bottom neighbor does not 
		//match the current bottom neighbor
		String prevBottom = neighbors.get(top).getOne().get(1);
		if (neighbors.get(top).getTwo().get(1)
				&& !prevBottom.equals(bottom)) {
			
			setNullifyBoards(prevBottom, 0);
		}

		//if bottom is in the dictionary, and the existing top neighbor does not
		//match the current top neighbor
		String prevTop = neighbors.get(bottom).getOne().get(0);
		if (neighbors.get(bottom).getTwo().get(0)
				&& !prevTop.equals(top)) {
			setNullifyBoards(prevTop, 1);
		}

		// send message to the top board
		setNeighborBoards(top, bottom, 1);
		notifyBoardJoin(top);

		// send message to the right board
		setNeighborBoards(bottom, top, 0);
		notifyBoardJoin(bottom);
		return;

	}

	/**
	 * updates "invisible wall" info in the hashmap
	 * @param s1 the board that needs to be modified.
	 * @param s2 the neighboring board
	 * @param i2 indicates which wall needs to be marked as invisible
	 * @throws IOException
	 */
	private void setNeighborBoards(String s1, String s2, int i2) {
		List<String> adjBoardNames = neighbors.get(s1).getOne();
		List<Boolean> isInvisible = neighbors.get(s1).getTwo();
		adjBoardNames.set(i2, s2);
		isInvisible.set(i2, true);
		//printNeighbors();
	}

	/**
	 * set a certain neighbor to be solid wall
	 * @param board
	 * @param loc
	 * @throws IOException 
	 */
	private void setNullifyBoards(String board, int loc) {
		System.out.println("setNullifyBoards called: " + board);
		List<String> adjBoardNames = neighbors.get(board).getOne();
		List<Boolean> isInvisible = neighbors.get(board).getTwo();
		String neighbor = adjBoardNames.get(loc);
		System.out.println("jod neighbor: " + neighbor);


		Socket socket = neighbors.get(board).getThree();
		PrintWriter out;
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			
			System.out.println("unmark " + loc + " " + neighbor);
			out.println("unmark " + loc + " " + neighbor);
		} catch (IOException e) {
			System.out.println("CATCH 21");
			e.printStackTrace();
		}
		adjBoardNames.set(loc, null);
		isInvisible.set(loc, false);
		

	}

	/**
	 * prints out the contents of the map
	 */
	private void printNeighbors() {
		System.out.println("*********printNeighbors*********");
		for (String client : neighbors.keySet()) {
			System.out.println("client: " + client);
			for (String neighbor : neighbors.get(client).getOne()) {
				System.out.print(neighbor + " ");
			}
			System.out.println();
			for (boolean bool : neighbors.get(client).getTwo()) {
				System.out.print(bool + " ");
			}
			System.out.println();
		}
		System.out.println("******************************");
	}

	/**
	 * the main joinBoards function
	 * parses the join command
	 * @param command
	 * @throws IOException 
	 * @throws IllegalArgumentException 
	 */
	public void joinBoards(String command) throws IllegalArgumentException, IOException {
		// sample input: h NAME_left NAME_right
		//               v NAME_top NAME_bottom
		String []words = command.split(" ");
		System.out.println(command);

		if (words[0].equals("h")) {
			makeHorizNeighbors(words);
		} else if (words[0].equals("v")) {
			makeVerticNeighbors(words);
		}
	}
	
	/**
	 * 
	 * @param name: board that wants to chat
	 * @param nameWantsChat: if that board wants to chat
	 * @return if both name and neighbor want to chat
	 */
	public boolean checkChatAgreement(String name, boolean nameWantsChat, String neighbor) {
		int nameIndex = neighbors.get(neighbor).getOne().indexOf(name);
		boolean neighborWantsChat = neighbors.get(neighbor).getFour().get(nameIndex) ;
		return nameWantsChat && neighborWantsChat; 
	}
	
	/**
	 * 
	 * @param name
	 * @param neighbor
	 * @return if both name and neighbor have expressed yes or no on 
	 *         whether to join the chat
	 */
	public boolean checkBothFinished (String name, String neighbor) {
		int nameIndex = neighbors.get(neighbor).getOne().indexOf(name);
		int neighborIndex = neighbors.get(name).getOne().indexOf(neighbor);
		System.out.println("neighbor -> name " + neighbors.get(neighbor).getFour().get(nameIndex));
		System.out.println("name -> neighbor " + neighbors.get(name).getFour().get(neighborIndex));
		
		return neighbors.get(neighbor).getFour().get(nameIndex) != null
		    && neighbors.get(name).getFour().get(neighborIndex) != null;		
	}
	
	/**
	 * update if the board wants to talk to neighbor of given index
	 *
	 * @param name
	 * @param nameWantsChat
	 * @param neighborIndex
	 */
	public void updateChatWant(String name, boolean nameWantsChat, int neighborIndex) {
		neighbors.get(name).getFour().set(neighborIndex, nameWantsChat);
	}

	/**
	 * Run the server, listening for client connections and handling them.
	 * Never returns unless an exception is thrown.
	 * 
	 * @throws IOException if the main server socket is broken
	 *                     (IOExceptions from individual clients do *not* terminate serve())
	 */
	public void serve() throws IOException {
		new Thread() {
			/**
			 * listen for server join commands
			 */
			public void run() {

	
				Scanner sc = new Scanner(System.in);
				System.out.println("Enter a join command:");
				while(sc.hasNextLine()) {

					String joinCommand = sc.nextLine();
					try {
						joinBoards(joinCommand);
					} catch (Exception e) {
						e.printStackTrace();
					}					

				}
				sc.close();

			}
		}.start();

		while (true) {
			final Socket socket;
			try {
				// block until a client connects
				socket = serverSocket.accept();
				new Thread(){
					/**
					 * talk to the client thread
					 */
					public void run(){
						try {
							handleConnection(socket);
						} catch (IOException e) {
							System.out.println("handleConnection failed inside Server");
							e.printStackTrace();
						} 
					}
				}.start();

			} catch (Exception e) {
				System.out.println("connection failed");
			} 
		}		
	}


	/**
	 * listen for messages coming from the client
	 * @param socket
	 * @throws IOException
	 */
	private void handleConnection(Socket socket) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		String name = null;

		try{      
			for (String line = in.readLine(); line != null; line = in.readLine()) {

				if(!line.equals("")){
					//System.out.println(line);

					String[] tokens = line.split(" ");
					
					//When the client first connects, it passes in the name of the boar
					if (tokens[0].equals("name")) {
						name = tokens[1];
						System.out.println(name);
						if (!neighbors.contains(name)) {
							//top, bottom, left, right
							List<String> adjacents = Arrays.asList(null, null, null, null);
							List<Boolean> invisibles = Arrays.asList(false, false, false, false);
							List<Boolean> chatNeighbors = Arrays.asList(null, null, null, null);
							Quadruple<List<String>, List<Boolean>, Socket, List<Boolean>> triple 
							= new Quadruple<List<String>, List<Boolean>,Socket, List<Boolean>>(adjacents, invisibles, socket, chatNeighbors);

							neighbors.put(name, triple);
						}
						//printNeighbors();
					}               

					String output = handleRequest(line); 
					if (output != null) {
						
					}
				}
				  
			}//end for
			System.out.println("got out of for loop");
			out.println("kill");

			//when a client disconnects, revert to solid walls
			revertToSolidWalls(name);
			System.out.println("revert walls: inside try " + name);
			
			neighbors.remove(name);
		} catch(Exception e) {
			//when the server thread specific to a client disconnects, revert the neighboring boards to solid walls
		    
			e.printStackTrace(); 
		}   finally {
			out.close();
			in.close();
			socket.close();



			//when the client disconnects, all balls inside its board are lost


		}
	}

	/**
	 * When a client disconnects, find all boards joined to it, and revert to solid walls
	 * also clear all the neighbors who are in chat
	 * @param name
	 */
	private void revertToSolidWalls(String name) {
		System.out.println();
		System.out.println("***************revertToSolidWalls method****************");
		printNeighbors();

		List<String> adjacents = neighbors.get(name).getOne();		
		List<Boolean> invisibles = neighbors.get(name).getTwo();
		List<Boolean> chatNeighbors = neighbors.get(name).getFour();

		System.out.println("who called this method: " + name);

		//go through the neighbors and reset
		for (int i = 0; i < adjacents.size(); i++) {
			String neighbor = adjacents.get(i);

			if (neighbor != null) {
				List<String> nOfns = neighbors.get(neighbor).getOne();
				List<Boolean> bOfns = neighbors.get(neighbor).getTwo();
				List<Boolean> cOfns = neighbors.get(neighbor).getFour();
				
				int index = nOfns.indexOf(name);
				System.out.println("neighbor's name: " + neighbor);
				Socket socket = neighbors.get(neighbor).getThree();
				PrintWriter out;
				try {
					out = new PrintWriter(socket.getOutputStream(), true);
					System.out.println("unmark " + index + " " + neighbor);
					out.println("unmark " + index + " " + neighbor);
				} catch (IOException e) {
					System.out.println("catch2");
					e.printStackTrace();
				}

				System.out.println("name when closed: " + name);
				System.out.println("index of neighbor: " + index);
				nOfns.set(index, null);
				bOfns.set(index, false);		

				cOfns.set(index, null);


			}
		}	

		// reset the board 
		adjacents = Arrays.asList(null, null, null, null);
		invisibles = Arrays.asList(false, false, false, false);
		chatNeighbors = Arrays.asList(null, null, null, null);
		Quadruple<List<String>, List<Boolean>, Socket, List<Boolean>> quad 
		= new Quadruple<List<String>, List<Boolean>,Socket, List<Boolean>>(adjacents, invisibles, null, chatNeighbors);
		neighbors.put(name, quad);

		printNeighbors();
		System.out.println("**********");
		System.out.println();
	}
	

	/**
	 * do something with the client messages
	 * such as choosing the neighbor clients to pass the ball to.
	 * @param input
	 * @return an output to send to the server
	 * @throws IOException 
	 */
	private String handleRequest(String input) throws IOException {


		//System.out.println("input message: " + input);
		//System.out.println("input from the client: " + input);
		String[] tokens = input.split(" ");

		//sample input: name 
		if(tokens[0].equals("name")) {
			//System.out.println("name: " + input);
			return input;
		}
		
		// assume for now that the messages sent are one word long
		// change the delimiter to something else later
		String[] strangeTokens = input.split("123456789");
		if (strangeTokens[0].equals("chatSend")) {
			//System.out.println("inside chatSend: " + input);
			String chatName = strangeTokens[1];
			String chatNeighbor = strangeTokens[2];
			int wallNum = neighbors.get(chatNeighbor).getOne().indexOf(chatName);
			String msg = strangeTokens[4];
			
			String delim = "123456789";
			String msgToReceive = "chatReceive" + delim + chatNeighbor + delim + wallNum + delim + msg;
			notifyChat(chatNeighbor, msgToReceive);
			return null;
		}
		
		if (tokens[0].equals("chatWant")) {
			//System.out.println("input: " + input);
			int wallNum = Integer.parseInt(tokens[1]);
			String chatName = tokens[2];
			String chatNeighbor = tokens[3];
			updateChatWant(chatName, true, wallNum);
			
			boolean ended = checkBothFinished(chatName, chatNeighbor);
			if (!ended) {
				//System.out.println("chatName: " + chatName + " chatNeibor: " + chatNeighbor);
				return null;
			}
			
			boolean chatAgreed = checkChatAgreement(chatName, true, chatNeighbor);
			//System.out.println("chatAgreed: " + chatAgreed + " chatName: " + chatName + " chatNeigh: " + chatNeighbor);
			
			if (chatAgreed && ended){
				String msgToChatName = "chatCreated " + wallNum + " " + chatNeighbor;
				String msgToChatNeighbor = "chatCreated " + neighbors.get(chatNeighbor).getOne().indexOf(chatName)
						               + " " + chatName;
				
				try {
//					Socket socket = neighbors.get(chatName).getThree();
//					PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
//					out.println(msgToChatNeighbor);		
					notifyChat(chatName, msgToChatName);
					notifyChat(chatNeighbor, msgToChatNeighbor);
					
					
				} catch (IOException e) {
					System.out.println("inside chatWant");
					e.printStackTrace();
				}
			}
			return null;
		}
		
		if (tokens[0].equals("chatNo")) {
			//System.out.println("input " + input);
			boolean send = !Boolean.parseBoolean(tokens[1]);
			int wallNum = Integer.parseInt(tokens[2]);
			String chatName = tokens[3];
			String chatNeighbor = tokens[4];
			
			boolean ended = checkBothFinished(chatName, chatNeighbor);
			updateChatWant(chatName, false, wallNum);		
						
			
			//String msgToChatName = "chatReject " + chatNeighbor + " " + wallNum;
			String msgToChatNeighbor = "chatReject " + chatName + " " 
					               + neighbors.get(chatNeighbor).getOne().indexOf(chatName);
			
			if (send && !ended) {
				try {
					//notifyChat(chatName, msgToChatName);
					notifyChat(chatNeighbor, msgToChatNeighbor);
					
				} catch (IOException e) {
					System.out.println("inside chatNo");
					e.printStackTrace();
				}
			}
			
			return null;
		}

		if (tokens[0].equals("create")){

			String otherBoardName = tokens[1];
			String nameOfBall = tokens[2];

			double x = Double.parseDouble(tokens[3]);
			double y = Double.parseDouble(tokens[4]);
			double xVel = Double.parseDouble(tokens[5]);
			double yVel = Double.parseDouble(tokens[6]);    

			double radius = Double.parseDouble(tokens[7]);  

			PrintWriter outReceiver;
			try {

				Socket socketReceiver = neighbors.get(otherBoardName).getThree();

				outReceiver = new PrintWriter(socketReceiver.getOutputStream(), true);

				//String msgToSender = "delete " + nameOfBall + " " + x + " " + y + " " + xVel + " " + yVel;
				String msgToReceiver = "create " + nameOfBall + " " + x + " " + y + " " + xVel + " " + yVel + " " + radius;

				System.out.println(msgToReceiver);
				//outSender.println(msgToSender);
				outReceiver.println(msgToReceiver);

				return null;

			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("custom print");
				e.printStackTrace();
			}

		}

		//port hit:
		if (tokens[0].equals("port")){

			String nameOfBoard = tokens[1];
			String otherBoardName = tokens[2];
			String otherPortalName = tokens[3];
			String nameOfBall = tokens[4];

			double x = Double.parseDouble(tokens[5]);
			double y = Double.parseDouble(tokens[6]);
			double xVel = Double.parseDouble(tokens[7]);
			double yVel = Double.parseDouble(tokens[8]);    
			double radius = Double.parseDouble(tokens[9]); 

			PrintWriter outReceiver;
			try {
				//System.out.println("Portal Transfer");
				//System.out.println("ffffffffffffffffffffffffffffffffffffffffff");
				if(neighbors.containsKey(otherBoardName)){
					Socket socketReceiver = neighbors.get(otherBoardName).getThree();

					outReceiver = new PrintWriter(socketReceiver.getOutputStream(), true);

					//String msgToSender = "delete " + nameOfBall + " " + x + " " + y + " " + xVel + " " + yVel;
					String msgToReceiver = "port "+ nameOfBoard +" " + otherPortalName+" "+nameOfBall+" "+x+" "+y+" "+xVel+" "+yVel+ " " + radius;
					//outSender.println(msgToSender);
					outReceiver.println(msgToReceiver);

					return null;
				}

				Socket socketReceiver = neighbors.get(nameOfBoard).getThree();

				outReceiver = new PrintWriter(socketReceiver.getOutputStream(), true);
				String msgToReceiver = "create " + nameOfBall + " " + x + " " + y + " " + xVel + " " + yVel + " " + radius;

				//outSender.println(msgToSender);
				outReceiver.println(msgToReceiver);
				return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("custom print");
				e.printStackTrace();
			}

		}
		// sample input: hit NAMEofBoard wallNum  NAMEofBall x y xVel yVel
		// wallNum is either 0,1,2,3 -> top, bottom, left, right
		if (tokens[0].equals("hit")) {

		    System.out.println("INPUT AGAIN: "+input);

		    System.out.println("Input message: " + input);

			String nameOfBoard = tokens[1];
			int wallNum = Integer.parseInt(tokens[2]);
			String nameOfBall = tokens[3];

			double x = Double.parseDouble(tokens[4]);
			double y = Double.parseDouble(tokens[5]);
			double xVel = Double.parseDouble(tokens[6]);
			double yVel = Double.parseDouble(tokens[7]); 	
			double radius = Double.parseDouble(tokens[8]); 

			// TODO: 
			// first, find the adjacent neighbor
			// second, see if the wall that is hit is invisible
			// third, if so, change the coordinate and send message to the adjacent neighbor
			// sample output: "invisible NAMEofBall x y xVel yVel" or "visible"


			boolean hitInvisible= neighbors.get(nameOfBoard).getTwo().get(wallNum);
			Socket socketSender = neighbors.get(nameOfBoard).getThree();

			PrintWriter outSender;
			PrintWriter outReceiver;
			try {
				outSender = new PrintWriter(socketSender.getOutputStream(), true);

				System.out.println("wall number is "+wallNum);

				String neighbor = neighbors.get(nameOfBoard).getOne().get(wallNum);

				Socket socketReceiver = neighbors.get(neighbor).getThree();

				outReceiver = new PrintWriter(socketReceiver.getOutputStream(), true);
				
				if(wallNum==0){

				    System.out.println("Y0 value is "+y);
				    y+=19;
				}
				else if (wallNum==1){
				    System.out.println("Y1 value is "+y);
				    y-=19;
				}
				else if(wallNum==2){
				    x+=19;
				}
				else{
				    x-=19;
				}
				
				System.out.println(wallNum);

				//String msgToSender = "delete " + nameOfBall + " " + x + " " + y + " " + xVel + " " + yVel;
				String msgToReceiver = "create " + nameOfBall + " " + x + " " + y + " " + xVel + " " + yVel + " " + radius;
				System.out.println(msgToReceiver);
				//outSender.println(msgToSender);
				outReceiver.println(msgToReceiver);
				return null;
			}  catch (NullPointerException e) {
				outSender = new PrintWriter(socketSender.getOutputStream(), true);
				String msgToReceiver = "create " + nameOfBall + " " + x + " " + y + " " + xVel + " " + yVel + " " + radius;
				outSender.println(msgToReceiver); 
			}catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("custom print");
				e.printStackTrace();
			}


			return null;
		} else {
			return null;
		}

		// Should never get here--make sure to return in each of the valid cases above.
		//throw new UnsupportedOperationException();
	}



	/**
	 * Main method checks for port argument and handles connection
	 * @param args
	 */
	public static void main(String[] args) {
		int port = 10987; // default port

		Queue<String> arguments = new LinkedList<String>(Arrays.asList(args));

		try {
			//command line parsing
			while ( ! arguments.isEmpty()) {
				String flag = arguments.remove();
				try {
					if (flag.equals("--port")) {
						port = Integer.parseInt(arguments.remove());
						//System.out.println("port: " + port);
					}

				} catch (Exception e) {
					e.printStackTrace();
					throw new IllegalArgumentException();
				}
			}

		} catch (IllegalArgumentException iae) {
			iae.printStackTrace();
			System.err.println(iae.getMessage());
			System.err.println("usage: PingballServer [--port PORT]");
			return;
		}

		try {
			runPingballServer(port);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * starts the server
	 * @param port
	 * @throws IOException
	 */
	public static void runPingballServer(int port) throws IOException{
		PingballServer s = new PingballServer(port);
		s.serve();
	}
	//in serve, listen for a connection 

}
