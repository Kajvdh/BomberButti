/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BomberButti;
import java.awt.Graphics;
import java.awt.Color;
/**
 * De klasse Bomb beschrijft de bommen die gelegd kunnen worden door spelers
 * @author Kaj
 */
public class Bomb {
    int x; //X-coördinaat van de bom
    int y; //Y-coördinaat van de bom
    int countdown; //Afteller; wanneer deze op 0 komt te staan ontploft te bom
    boolean exploded; //Is true wanneer de bom ontploft is
    boolean madestrike; //Is true wanneer de bom zijn strike gemaakt heeft (bij explosie)
    BomberPlayer player; //Object van BomberPlayer van de speler die de bom heeft gelegd
    
    /**
     * Default constructor
     */
    public Bomb() {
        x = 0;
        y = 0;
        countdown = 30;
        exploded = false;
        madestrike = false;
        player = null;
    }
    
    /**
     * Constructor
     * @param x: X-coördinaat van de bom
     * @param y: Y-coördinaat van de bom
     */
    public Bomb(int x, int y) {
        this();
        this.x = x;
        this.y = y;
    }
    
    /**
     * Constructor
     * @param player: Object van de speler die deze bom heeft gelegd
     * @param x: X-coördinaat van de bom
     * @param y: Y-coördinaat van de bom
     */
    public Bomb(BomberPlayer player, int x, int y) {
        this();
        this.x = x;
        this.y = y;
        this.player = player;
    }
    
    /**
     * Getfuncties
     * @return 
     */
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public int getCountdown() {
        return this.countdown;
    }
    public boolean getExploded() {
        return this.exploded;
    }
    public boolean getMadeStrike() {
        return this.madestrike;
    }
    public BomberPlayer getPlayer() {
        return this.player;
    }
    public Coord getCoords() {
        return new Coord(x,y);
    }
    public boolean isExploded() {
        return this.getExploded();
    }
    public int getStrike() {
        return player.getBombStrike();
    }
    
    /**
     * Setfuncties
     */
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setCountdown(int countdown) {
        this.countdown = countdown;
    }
    public void setExploded(boolean exploded) {
        this.exploded = exploded;
    }
    public void setMadestrike(boolean madestrike) {
        this.madestrike = madestrike;
    }
    public void setPlayer(BomberPlayer player) {
        this.player = player;
    }
    public void explode() {
        this.setExploded(true);
    }
    public void striked() {
        this.setMadestrike(true);
    }
    
    /**
     * Hier gebeurt alle 'logica' van de bom
     * Wordt bij elke 'timerpuls' aangeroepen
     */
    public void act() {
        countdown--; //Teller -1
        if (countdown < 1) { //Teller staat op 0 -> Bom ontploft
            exploded = true; //Bom is ontploft
        }
    }
    
    /**
     * Hier wordt de bom getekend
     * @param g: Bevat het Graphics object naar waar er getekend moet worden
     */
    void draw(Graphics g) {
        g.setColor(Color.red);
        g.drawOval(x*10, y*10, 10, 10);
        g.fillOval(x*10, y*10, 10, 10);
    }
}