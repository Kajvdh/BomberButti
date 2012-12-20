package BomberButti;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

/**
* De klasse BomberPlayer bevat alle gegevens van de speler
* Elke speler heeft een eigen object van deze klasse
* @author Kaj Van der Hallen
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
    ArrayList<Bomb> bombs = new ArrayList<>(); //bijhouden van de gelegde bommen door deze speler
    String name; //The username of this player
    int[] keys;
    private static final int DIR_UP = 1;
    private static final int DIR_DOWN = 2;
    private static final int DIR_LEFT = 3;
    private static final int DIR_RIGHT = 4;
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private static final int LEFT = 2;
    private static final int RIGHT = 3;
    private static final int BOMB = 4;
    
    /**
    * Default constructor
    */
    public BomberPlayer() {
        keys = new int[5];
        id = 0;
        x = 0;
        y = 0;
        isDead = false;
        speed = 1;
        totalBombs = 1;
        bombStrike = 0;
        usedBombs = 0;
        direction = 0;
        dropBomb = false;
        name = "Unknown player";
    }
    
    
    /**
    * Constructor
    * @param game: Object van de BomberGame klasse
    * @param map: Object van de BomberMap klasse
    * @param id: Uniek ID van deze speler
    * @param x: X-locatie van deze speler
    * @param y: Y-locatie van deze speler
    */
    public BomberPlayer(BomberGame game, BomberMap map, int id, int x, int y, String name) {
        this();
        this.game = game;
        this.map = map;
        this.id = id;
        this.x = x;
        this.y = y;
        this.name = name;
    }
    
    
    /**
     * Besturingskeys voor deze speler ophalen uit de KeyConfig classe
     */
    public void loadKeys() {
        KeyConfig kC = new KeyConfig();
        keys[UP] = kC.keys[id-1][UP];
        keys[DOWN] = kC.keys[id-1][DOWN];
        keys[LEFT] = kC.keys[id-1][LEFT];
        keys[RIGHT] = kC.keys[id-1][RIGHT];
        keys[BOMB] = kC.keys[id-1][BOMB];
    }
    
    
    /**
     * Getfunctie voor het object van de map
     * @return 
     */
    public BomberMap getMap() {
        return this.map;
    }
    /**
     * Getfunctie voor het object van de game
     * @return 
     */
    public BomberGame getGame() {
        return this.game;
    }
    /**
     * Getfunctie voor het id
     * @return 
     */
    public int getId() {
        return this.id;
    }
    /**
     * Getfunctie voor de X-coördinaat
     * @return 
     */
    public int getX() {
        return this.x;
    }
    /**
     * Getfunctie voor de Y-coördinaat
     * @return 
     */
    public int getY() {
        return this.y;
    }
    /**
     * Getfunctie voor de (x,y) coördinaat
     * @return 
     */
    public Coord getCoords() {
        return new Coord(this.x,this.y);
    }
    /**
     * Getfunctie voor de isDead boolean
     * @return 
     */
    public boolean getIsDead() {
        return this.isDead;
    }
    /**
     * Getfunctie voor de speed
     * @return 
     */
    public int getSpeed() {
        return this.speed;
    }
    /**
     * Getfunctie voor de totalBombs variabele
     * @return 
     */
    public int getTotalBombs() {
        return this.totalBombs;
    }
    /**
     * Getfunctie voor de bombStrike
     * @return 
     */
    public int getBombStrike() {
        return this.bombStrike;
    }
    /**
     * Getfunctie voor de usedBombs
     * @return 
     */
    public int getUsedBombs() {
        return this.usedBombs;
    }
    /**
     * Getfunctie voor de ArrayList van de bombs die door deze speler zijn gelegd
     * @return 
     */
    public ArrayList<Bomb> getBombs() {
        return this.bombs;
    }
    /**
     * Getfunctie voor de naam van deze speler
     * @return 
     */
    public String getName() {
        return this.name;
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
        
        if (evt.getKeyCode() == keys[UP]) {
            this.direction = DIR_UP;
        }
        else if (evt.getKeyCode() == keys[DOWN]) {
            this.direction = DIR_DOWN;
        }
        else if (evt.getKeyCode() == keys[LEFT]) {
            this.direction = DIR_LEFT;
        }
        else if (evt.getKeyCode() == keys[RIGHT]) {
            this.direction = DIR_RIGHT;
        }
        else if (evt.getKeyCode() == keys[BOMB]) {
            dropBomb = true;
        }
        
    }
    
    /**
    * Wordt uitgevoerd bij het loslaten van een toets
    * @param evt: Bevat informatie over de losgelaten toets
    */
    public void keyReleased(KeyEvent evt) {
        if ((evt.getKeyCode() == keys[UP]) && (direction == DIR_UP)) {
            direction = 0;
        }
        if ((evt.getKeyCode() == keys[DOWN]) && (direction == DIR_DOWN)) {
            direction = 0;
        }
        if ((evt.getKeyCode() == keys[LEFT]) && (direction == DIR_LEFT)) {
            direction = 0;
        }
        if ((evt.getKeyCode() == keys[RIGHT]) && (direction == DIR_RIGHT)) {
            direction = 0;
        }
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
            if (b.isExploded()) {
                i.remove();
            } //y->bom verwijderen
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
                    if (!map.isObstacle(c)) {
                        this.y--;
                    } //n->naar omhoog verplaatsen
                    break;
                case DIR_DOWN: //Speler wil zich naar omlaag verplaatsen
                    c.incY(); //Huidige coördinaat 1 vakje omlaag
                    if (!map.isObstacle(c)) {
                        this.y++;
                    } //n->naar omlaag verplaatsen
                    break;
                case DIR_LEFT: //Speler wil zich naar links verplaatsen
                    c.decX(); //Huidige coördinaat 1 vakje naar links
                    if (!map.isObstacle(c)) {
                        this.x--;
                    } //n->naar links verplaatsen
                    break;
                case DIR_RIGHT: //Speler wil zich naar rechts verplaatsen
                    c.incX(); //Huidige coördinaat 1 vakje naar rechts
                    if (!map.isObstacle(c)) {
                        this.x++;
                    } //n->naar rechts verlaatsen
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
        if (id == 1) {
            g.drawImage(game.getImages().getPlayer(), x*10, y*10, null);
        }
        else {
            g.drawImage(game.getImages().getPlayer2(), x*10, y*10, null);
        }
    }
    
}