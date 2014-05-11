package test;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class draw extends JPanel implements ActionListener, KeyListener{
    Timer t = new Timer(5, this);
    double x=0, y=0, velx = 0, vely = 0;
    
    public draw(){
        t.start();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
       // g2.fille(new Ellipse2D.Double(x, y, 40, 40));
    }

    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    public void actionPerformed(ActionEvent e) {
        repaint();
        x += velx;
        y += vely;
        
        
    }
    
    
}
