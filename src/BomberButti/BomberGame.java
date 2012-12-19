/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package BomberButti;
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

import java.util.*;
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
    ArrayList<BomberPlayer> players = new ArrayList<BomberPlayer>();
    private int idCounter;
    BomberImages images;
    public BomberGame() {
        idCounter = 0;
        images = new BomberImages();
        this.map = new BomberMap(this);
        //this.player = new BomberPlayer(this,map,1,1,1,"Kaj");
        initPlayers();
        //this.player.loadKeys();
        timer = new Timer(150, this);
    }
    
    public void startGame() {
        timer.start();
    }
    public void pauseGame() {
        timer.stop();
    }
    
    public void initPlayers() {
        players.add(new BomberPlayer(this,map,1,1,1,"Player 1"));
        players.add(new BomberPlayer(this,map,2,27,27,"Player 2"));
        for (BomberPlayer i : players) {
            i.loadKeys();
        }
    }
    
    public int createPlayer(String name) {
        players.add(new BomberPlayer(this,map,++idCounter,1,1,name));
        return idCounter;
    }
    
    public int getAmountOfPlayers() {
        return players.size();
    }
    
    public BomberImages getImages() {
        return images;
    }
    
    /**
    * Wordt uitgevoerd op elke puls van de Timer
    * @param e
    */
    public void actionPerformed(ActionEvent e) {
        map.act();
        for (BomberPlayer i : players) {
            i.act();
        }
        //player.act();
        repaint(); //Opnieuw naar het scherm schrijven
    }
    
    /**
    * Wordt uitgevoerd wanneer een toets wordt ingedrukt
    * @param evt
    */
    public void keyPressed(KeyEvent evt) {
        //if (player != null)
        //    player.keyPressed(evt);
        for (BomberPlayer i : players) {
            i.keyPressed(evt);
        }
    }
    
    /**
    * Wordt uitgevoerd wanneer een toets wordt losgelaten
    * @param evt
    */
    public void keyReleased(KeyEvent evt) {
        //if (player != null)
        //    player.keyReleased(evt);
        for (BomberPlayer i : players) {
            i.keyReleased(evt);
        }
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
            //player.draw(g);
            for (BomberPlayer i : players) {
                i.draw(g);
            }
        }
        else
            g.drawString("Game Over!", 150, 150);
    }
}