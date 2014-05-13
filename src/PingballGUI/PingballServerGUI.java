package PingballGUI;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import PingballClientServer.PingballServer;

public class PingballServerGUI extends JFrame{
    private static final long serialVersionUID = 1L;
    private PingballServer myServer;
    private final JTextField serverName;
    private final JTextField serverPort;
    private final JLabel serverNameLabel;
    private final JLabel serverPortLabel;
    private final JButton connectButton;
    private final JTextField leftBoard;
    private final JLabel leftLabel = new JLabel();
    private final JTextField rightBoard;
    private final JLabel rightLabel = new JLabel();
    
    private final JTextField topBoard;
    private final JLabel topLabel = new JLabel();
    private final JTextField bottomBoard;
    private final JLabel bottomLabel = new JLabel();
    private final JButton joinHButton;
    private final JButton joinVButton;
    
    public PingballServerGUI(){
        serverName = new JTextField(15);
        
        serverPort = new JTextField(15);
        
        serverNameLabel = new JLabel();
        serverNameLabel.setText("Server: ");
        serverPortLabel = new JLabel();
        serverPortLabel.setText("Port: ");
        connectButton = new JButton();
        connectButton.setText("Connect To Server");
        
        topBoard = new JTextField(20);
        topLabel.setText("Top Board: ");
        leftBoard = new JTextField(20);
        leftLabel.setText("Left Board: ");
        rightBoard = new JTextField(20);
        rightLabel.setText("Right Board: ");
        bottomBoard = new JTextField(20);
        bottomLabel.setText("Bottom Board: ");
        joinHButton = new JButton("Join Horizontally");
        joinVButton = new JButton("Join Vertically");

        serverName.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                serverNameLabel.setText("Server: " + serverName.getText());
                serverName.setText("");
            }
        });
        
        serverPort.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                serverPortLabel.setText("Port: " + serverPort.getText());
                serverPort.setText("");
            }
        });
      
        connectButton.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){ 
               try {
                myServer = new PingballServer(Integer.parseInt(serverPortLabel.getText().substring(6)));
                JOptionPane.showMessageDialog(null, "Successfully connected to Server: " + serverNameLabel.getText().substring(8));
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(null, "Invalid Port Number, Connect again!");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
           }
        });
        
        
        joinHButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ 
                if(myServer!=null){
                    try {
                        System.out.println("h " + leftBoard.getText() + "_left " + 
                                rightBoard.getText() + "_right");
                        myServer.joinBoards("h " + leftBoard.getText() + "_left " + 
                                rightBoard.getText() + "_right");
                        leftBoard.setText("");
                        rightBoard.setText("");
                    } catch (IllegalArgumentException e1) {
                        JOptionPane.showMessageDialog(null, e1);
                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(null, e1);
                    }
                }
            }
         });
        
        joinVButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ 
                if(myServer!=null){
                    try {
                        System.out.println("v " + topBoard.getText() + "_top " + 
                                bottomBoard.getText() + "_bottom");
                        myServer.joinBoards("v " + topBoard.getText() + "_top " + 
                                bottomBoard.getText() + "_bottom");
                        topBoard.setText("");
                        bottomBoard.setText("");
                    } catch (IllegalArgumentException e1) {
                        JOptionPane.showMessageDialog(null, "Illegal Command!");
                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(null, "Illegal Command!");
                    }
                }
            }
         });
        
        
        
        //define layout
        setLayout(new FlowLayout());
        serverNameLabel.setBounds(0, 0, 5, 100);
        add(serverNameLabel);
        add(serverName);
        add(serverPortLabel);
        add(serverPort);
        add(connectButton, BorderLayout.CENTER); 
        add(serverPort);
        add(topLabel);
        add(topBoard);
        add(bottomLabel);
        add(bottomBoard);
        add(joinVButton);
        
        add(leftLabel);
        add(leftBoard);
        add(rightLabel);
        add(rightBoard);
        add(joinHButton);
    }
    
    /**
     * Starts the GUI Pingball Server
     * @param args unused.
     */
    public static void main(final String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                PingballServerGUI main = new PingballServerGUI();
                main.setTitle("PingballGUI Client");
                main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                main.setVisible(true);
                main.setSize(1000, 200);
            }
        });
    }
}
