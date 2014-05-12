package PingballGUI;

import java.awt.event.KeyEvent; 
import java.awt.event.KeyListener;

import javax.swing.JTextField;

import ADT.Board;

public class PingballKeyListener implements KeyListener{
    private Board board;

    public PingballKeyListener(Board board){
        this.board = board;
    }
  
    public void keyTyped(KeyEvent keyEvent) {
        String key = KeyEvent.getKeyText(keyEvent.getKeyCode());
        String keyString = key.replaceAll(" ", "").toLowerCase();
        
        displayInfo(keyEvent, "KEY TYPED: ");
    }

    /** Handle the key-pressed event from the text field. */
    public void keyPressed(KeyEvent keyEvent) {
        String key = KeyEvent.getKeyText(keyEvent.getKeyCode());
        String keyString = key.replaceAll(" ", "").toLowerCase();
        board.triggerDownKey(keyString);
        
        displayInfo(keyEvent, "KEY PRESSED: ");
    }

    /** Handle the key-released event from the text field. */
    public void keyReleased(KeyEvent keyEvent) {
        String key = KeyEvent.getKeyText(keyEvent.getKeyCode());
        String keyString = key.replaceAll(" ", "").toLowerCase();
        board.triggerUpKey(keyString);
        
        displayInfo(keyEvent, "KEY PRESSED: ");
    }

    private void displayInfo(KeyEvent e, String keyStatus){

        //You should only rely on the key char if the event
        //is a key typed event.
        System.out.println(keyStatus + KeyEvent.getKeyText(e.getKeyCode()));

    }

}
