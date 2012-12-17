/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BomberButti;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Integer;
import java.io.*;
/**
 *
 * @author Kaj
 */
public class BomberB1 extends JFrame {
    private BomberGame game;
    public BomberB1() {
        game = new BomberGame();
        add(game);
        setSize(400, 400);
        setTitle("Bomber");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.show();
        
        
        
        addKeyListener(new KeyAdapter() {
            /**
             * Wordt aangeroepen bij het indrukken van een toets
             * @param evt keyboard event
             */
            public void keyPressed(KeyEvent evt) {
                if (game != null)
                    game.keyPressed(evt);
            }
            /**
             * Wordt aangeroepen bij het loslaten van een toets
             * @param evt keyboard event
             */
            public void keyReleased(KeyEvent evt) {
                if (game != null)
                    game.keyReleased(evt);
            }
        });
        
    }
    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        BomberB1 main = new BomberB1();
        
        
    }
}
