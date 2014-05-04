package PingballGUI;

import java.awt.event.KeyEvent;

import javax.swing.JTextField;

import ADT.Board;

public class PingballKeyListener {
    
    private JTextField commandTypingArea;
    private Board board;

    public PingballKeyListener(JTextField commandTypingArea, Board board){
        this.commandTypingArea = commandTypingArea;
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
        int id = e.getID();
        String keyString;
        if (id == KeyEvent.KEY_TYPED) {
            char c = e.getKeyChar();
            keyString = "key character = '" + c + "'";
        } else {
            int keyCode = e.getKeyCode();
            keyString = "key code = " + keyCode
                    + " ("
                    + KeyEvent.getKeyText(keyCode)
                    + ")";
        }
    }

}
