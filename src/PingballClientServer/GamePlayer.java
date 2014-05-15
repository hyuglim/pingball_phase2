package PingballClientServer;


import java.io.*;
import java.util.Timer;

import ADT.Board;
import Parser.BoardFileFactory;


public class GamePlayer implements Runnable{

	private Board myBoard;
	
	/**
	 * takes in 
	 * @param Board b
	 */
	public GamePlayer(Board b) {
		this.myBoard = b;
	}
	
	/**
	 * plays the game on the board
	 */
	public void run() {		
	    Timer timer = new Timer();             
        timer.schedule(myBoard, 0, 50);
	}
	
	public static void main(String[ ] arsg) throws IOException{
	    StringBuilder boardText = new StringBuilder("");
	    String filePath = "src/Parser/" + "sampleBoard.pb";
        
        BufferedReader fr = new BufferedReader(new FileReader(filePath));
        for (String line = fr.readLine(); line != null; line = fr.readLine()) {
            boardText.append('\n' + line);
        }
        String boardTextString = boardText.toString().substring(1);
        Board myBoard = BoardFileFactory.parse(boardTextString);
	    GamePlayer game = new GamePlayer(myBoard);
	    new Thread(game).start();
	}
}
