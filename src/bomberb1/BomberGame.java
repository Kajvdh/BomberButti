/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberb1;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
/**
 *
 * @author Kaj
 */
public class BomberGame extends JPanel implements ActionListener {
    BomberMap map;
    BomberPlayer player;
    int totalPlayers;
    int playersLeft;
    boolean gameOver;
    Timer timer;
    
    public BomberGame() {;
        this.map = new BomberMap();
        this.player = new BomberPlayer(this,map,1,1,1);
        timer = new Timer(150, this);
        timer.start(); 
    }
    
    
    /**
     * Wordt uitgevoerd op elke puls van de Timer
     * @param e 
     */
    public void actionPerformed(ActionEvent e) {
        map.act();
        player.act();
        repaint(); //Opnieuw naar het scherm schrijven
    }
    
    /**
     * Wordt uitgevoerd wanneer een toets wordt ingedrukt
     * @param evt 
     */
    public void keyPressed(KeyEvent evt) {
        if (player != null)
            player.keyPressed(evt);
    }
    
    /**
     * Wordt uitgevoerd wanneer een toets wordt losgelaten
     * @param evt 
     */
    public void keyReleased(KeyEvent evt) {
        if (player != null)
            player.keyReleased(evt);
    }
    
    /**
     * Kan worden aangeroepen om het spel te beëindigen
     */
    public void endGame() {
        this.gameOver = true;
    }
    
    /**
     * Hier gebeurt het wegschrijven naar het scherm
     * @param g 
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (!gameOver) {
            map.draw(g);
            player.draw(g);
        }
        else
            g.drawString("Game Over!", 150, 150);
    }
}