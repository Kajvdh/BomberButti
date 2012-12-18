/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package BomberButti;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;
import javax.swing.SwingUtilities;
/**
* De klasse BomberPlayer bevat alle gegevens van de speler
* Elke speler heeft een eigen object van deze klasse
* @author Kaj
*/
public class BomberPlayer {
    BomberMap map; //Bevat het object van de klasse BomberMap
    BomberGame game; //Bevat het object van de klasse BomberGame
    int id; //Elke speler heeft een uniek ID
    int x; //x-locatie
    int y; //y-locatie
    boolean isDead; //Is true wanneer speler dood is
    int speed; //Indicatie van snelheid waarmee het character over het speelveld kan verplaatsen
    int totalBombs; //Totaal aantal bommen dat de speler tegelijkertijd kan leggen
    int bombStrike; //Rijkweidte van de strikes van de bommen die deze speler legt
    int usedBombs; //Aantal bommen dat de speler 'tot nu toe' heeft gebruikt
    int direction; //Richting waarin de speler beweegt op het veld (wordt uitgevoerd bij this.act()
    boolean dropBomb; //True als speler een bom wil legggen (wordt uigevoerd bij this.act())
    ArrayList<Bomb> bombs = new ArrayList<Bomb>(); //bijhouden van de gelegde bommen door deze speler
    String name; //The username of this player
    
    private static final int DIR_UP = 1;
    private static final int DIR_DOWN = 2;
    private static final int DIR_LEFT = 3;
    private static final int DIR_RIGHT = 4;
    
    /**
* Default constructor
*/
    public BomberPlayer() {
        id = 0;
        x = 0;
        y = 0;
        isDead = false;
        speed = 1;
        totalBombs = 1;
        bombStrike = 3;
        usedBombs = 0;
        direction = 0;
        dropBomb = false;
        name = "Unknown player";
    }
    
    /**
* Constructor
* @param id: Uniek ID van de speler
* @param x: X-locatie van de speler
* @param y: Y-locatie van de speler
*/
    public BomberPlayer(int id, int x, int y) {
        this();
        this.id = id;
        this.x = x;
        this.y = y;
        name = "Player " + id;
    }
    
    /**
* Constructor
* @param map: Object van de BomberMap klasse
* @param id: Uniek ID van de speler
* @param x: X-locatie van de speler
* @param y: Y-locatie van de speler
*/
    public BomberPlayer(BomberMap map, int id, int x, int y) {
        this(id,x,y);
        this.map = map;
    }
    
    /**
* Constructor
* @param game: Object van de BomberGame klasse
* @param map: Object van de BomberMap klasse
* @param id: Uniek ID van deze speler
* @param x: X-locatie van deze speler
* @param y: Y-locatie van deze speler
*/
    public BomberPlayer(BomberGame game, BomberMap map, int id, int x, int y) {
        this(map, id, x, y);
        this.game = game;
    }
    
    public BomberPlayer(BomberGame game, BomberMap map, int id, int x, int y, String name) {
        this(game,map,id,x,y);
        this.name = name;
    }
    
    
    /**
* Get functies; Zodat externe klasses de variabelen van deze klasse kunnen ophalen
* @return
*/
    public BomberMap getMap() {
        return this.map;
    }
    public BomberGame getGame() {
        return this.game;
    }
    public int getId() {
        return this.id;
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public Coord getCoords() {
        return new Coord(this.x,this.y);
    }
    public boolean getIsDead() {
        return this.isDead;
    }
    public int getSpeed() {
        return this.speed;
    }
    public int getTotalBombs() {
        return this.totalBombs;
    }
    public int getBombStrike() {
        return this.bombStrike;
    }
    public int getUsedBombs() {
        return this.usedBombs;
    }
    public int getDirection() {
        return this.direction;
    }
    public boolean getDropBomb() {
        return this.dropBomb;
    }
    public ArrayList<Bomb> getBombs() {
        return this.bombs;
    }
    
    /**
* Set functies; Zodat externe klasses de variabelen van deze klasses kunnen instellen
*/
    public void setMap(BomberMap map) {
        this.map = map;
    }
    public void setGame(BomberGame game) {
        this.game = game;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setIsDead(boolean isDead) {
        this.isDead = isDead;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public void setTotalBombs(int totalBombs) {
        this.totalBombs = totalBombs;
    }
    public void setBombStrike(int bombStrike) {
        this.bombStrike = bombStrike;
    }
    public void setUsedBombs(int usedBombs) {
        this.usedBombs = usedBombs;
    }
    public void setDirection(int direction) {
        this.direction = direction;
    }
    public void setDropBomb(boolean dropBomb) {
        this.dropBomb = dropBomb;
    }
    public void setBombs(ArrayList<Bomb> bombs) {
        this.bombs = bombs;
    }
    
    /**
* Semi-set functions
*/
    public void incSpeed(int a) {
        speed = speed+a;
    }
    public void decSpeed(int a) {
        speed = speed-a;
    }
    public void incTotalBombs(int a) {
        totalBombs = totalBombs+a;
    }
    public void decTotalBombs(int a) {
        totalBombs = totalBombs-a;
    }
    public void incBombStrike(int a) {
        bombStrike = bombStrike+a;
    }
    public void decBombStrike(int a) {
        bombStrike = bombStrike-a;
    }
    
    
    /**
* Wordt uitgevoerd bij het indrukken van een toets
* @param evt: Bevat informatie ingedrukte toets
*/
    public void keyPressed(KeyEvent evt) {
        switch (evt.getKeyCode()) { //Switch op basis van ingedrukte toets
            case KeyEvent.VK_UP: //Pijltje omhoog
                this.direction = DIR_UP;
                break;
            case KeyEvent.VK_DOWN:
                this.direction = DIR_DOWN;
                break;
            case KeyEvent.VK_LEFT:
                this.direction = DIR_LEFT;
                break;
            case KeyEvent.VK_RIGHT:
                this.direction = DIR_RIGHT;
                break;
            case KeyEvent.VK_SPACE:
                dropBomb = true;
                break;
            default:
        }
    }
    
    /**
* Wordt uitgevoerd bij het loslaten van een toets
* @param evt: Bevat informatie over de losgelaten toets
*/
    public void keyReleased(KeyEvent evt) {
        if ((evt.getKeyCode() == KeyEvent.VK_UP) && (this.direction == 1))
            this.direction = 0;
        if ((evt.getKeyCode() == KeyEvent.VK_DOWN) && (this.direction == 2))
            this.direction = 0;
        if ((evt.getKeyCode() == KeyEvent.VK_LEFT) && (this.direction == 3))
            this.direction = 0;
        if ((evt.getKeyCode() == KeyEvent.VK_RIGHT) && (this.direction == 4))
            this.direction = 0;
        //if ((evt.getKeyCode() == KeyEvent.VK_SPACE) && (this.dropBomb == true))
        // this.dropBomb = false;
    }
    
    
    
    /**
* Speler wordt 'doodgemaakt'
*/
    public void kill() {
        this.isDead = true;
    }
    
    /**
* Wordt bij elke 'timerpulse' uitgevoerd, vanaf hier wordt telkens alles uitgevoerd
*/
    public void act() {
        //Loop door alle bommen die door deze speler zijn gelegd om de ontplofte bommen te verwijderen uit de lijst
        for (Iterator i = bombs.listIterator(); i.hasNext();) {
            Bomb b = (Bomb) i.next(); //Huidige 'bom' in de loop
            if (b.isExploded()) //bom ontploft?
                i.remove(); //y->bom verwijderen
        }
        
        
        //Controleren of speler wordt geraakt door een strike
        Coord c = new Coord(x,y); //Coördinaat object maken van de huidige locataie
        if (map.isStrike(c)) { //true als er een bombStrike op deze locatie is
            this.kill(); //Speler killen
            game.endGame(); //Game eindigen
        }
        else {
            //Controle of de speler over een bonus loopt
            map.checkBonus(this);
            
            
            //Controle of speler bom wil leggen
            if (dropBomb) { //Speler heeft spatiebalk ingedrukt om bom te leggen
                if (bombs.size() < totalBombs) { //Speler heeft nog bommen beschikbaar
                    Bomb b = new Bomb(this,x,y); //Bom object aanmaken
                    bombs.add(b); //Bom aan de lisjt toevoegen
                    map.createBomb(b); //Bom aan de map toevoegen
                    usedBombs++; //Gebruikte bommen +1
                }
            }
            dropBomb = false;
            
            //Controle of speler zich wil verplaatsen
            switch(direction) {
                case DIR_UP: //Speler wil zich naar omhoog verplaatsen
                    c.decY(); //Huidige coordinaat 1 vakje omhoog
                    if (!map.isObstacle(c)) //Is er een obstakel op deze nieuwe locatie?
                        this.y--; //n->naar omhoog verplaatsen
                    break;
                case DIR_DOWN: //Speler wil zich naar omlaag verplaatsen
                    c.incY(); //Huidige coördinaat 1 vakje omlaag
                    if (!map.isObstacle(c)) //Is er een obstakel op deze nieuwe locatie?
                        this.y++; //n->naar omlaag verplaatsen
                    break;
                case DIR_LEFT: //Speler wil zich naar links verplaatsen
                    c.decX(); //Huidige coördinaat 1 vakje naar links
                    if (!map.isObstacle(c)) //Is er een obstakel op deze nieuwe locatie?
                        this.x--; //n->naar links verplaatsen
                    break;
                case DIR_RIGHT: //Speler wil zich naar rechts verplaatsen
                    c.incX(); //Huidige coördinaat 1 vakje naar rechts
                    if (!map.isObstacle(c)) //Is er een obstakel op deze nieuwe locatie?
                        this.x++; //n->naar rechts verlaatsen
                    break;
                default: //Speler wil zich niet verplaatsen -> niets doen
            }
        }
    }
    
    /**
* Hier wordt de speler naar het programma getekend
* @param g: Bevat het Graphics object naar waar er getekend moet worden
*/
    public void draw(Graphics g) {
        g.drawImage(game.getImages().getPlayer(), x*10, y*10, null);
    }
    
}