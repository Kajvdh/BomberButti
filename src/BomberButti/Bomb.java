package BomberButti;
import java.awt.*;

/**
 * De klasse Bomb beschrijft de bommen die gelegd kunnen worden door spelers
 * @author Kaj Van der Hallen
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
     * Getfunctie voor de X-coördinaat van de bom in het speelveld
     * @return x
     */
    public int getX() {
        return this.x;
    }
    /**
     * Getfunctie voor de Y-coördinaat van de bom in het speelveld
     * @return y
     */
    public int getY() {
        return this.y;
    }
    /**
     * Getfunctie voor de countdown teller van de bom
     * @return countdown
     */
    public int getCountdown() {
        return this.countdown;
    }
    /**
     * Getfunctie voor de exploded boolean
     * @return exploded
     */
    public boolean getExploded() {
        return this.exploded;
    }
    /**
     * Getfunctie voor de madestrike boolean
     * @return madestrike
     */
    public boolean getMadeStrike() {
        return this.madestrike;
    }
    /**
     * Getfunctie voor het object van de speler die de bom heeft gelegd
     * @return player
     */
    public BomberPlayer getPlayer() {
        return this.player;
    }
    /**
     * Getfunctie voor de coördinaten van de bom in het speelveld
     * @return 
     */
    public Coord getCoords() {
        return new Coord(x,y);
    }
    /**
     * Deze functie returnt true als de bom is ontploft
     * @return 
     */
    public boolean isExploded() {
        return this.getExploded();
    }
    /**
     * Deze functie returnt de lengte van de strike die deze bom gaat maken bij explosie
     * @return 
     */
    public int getStrike() {
        return player.getBombStrike();
    }
    
    
    /**
     * Setfuncctie voor de X-coördinaat
     * @param x 
     */
    public void setX(int x) {
        this.x = x;
    }
    /**
     * Setfunctie voor de Y-coördinaat
     * @param y 
     */
    public void setY(int y) {
        this.y = y;
    }
    /**
     * Setfunctie voor de countdown teller
     * @param countdown 
     */
    public void setCountdown(int countdown) {
        this.countdown = countdown;
    }
    /**
     * Setfunctie voor de exploded boolean
     * @param exploded 
     */
    public void setExploded(boolean exploded) {
        this.exploded = exploded;
    }
    /**
     * Setfunctie voor de madestrike boolean
     * @param madestrike 
     */
    public void setMadestrike(boolean madestrike) {
        this.madestrike = madestrike;
    }
    /**
     * Setfunctie voor het object van de speler die de bom heeft gelegd
     * @param player 
     */
    public void setPlayer(BomberPlayer player) {
        this.player = player;
    }
    
    /**
     * Functie om de bom mee te doen ontploffen
     */
    public void explode() {
        this.setExploded(true);
    }
    /**
     * Functie om de madestrike boolean op true te zetten
     */
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
        g.drawImage(player.game.getImages().getBomb(), x*10, y*10, null);
    }
}