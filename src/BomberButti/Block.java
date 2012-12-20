package BomberButti;
import java.awt.*;

/**
 * De klasse Block beschrijft de blokjes op het scherm, zowel de vaste als de opblaasbare blokjes
 * @author Kaj Van der Hallen
 */
public class Block {
    BomberMap map; //Map object
    int x; //X-coördinaat
    int y; //Y-coördinaat
    private boolean visible; //True als het blokje zichtbaar moet zijn op de kaart
    private boolean destructable; //True als het blokje kan worden opgeblazen
    
    /**
     * Default constructor
     */
    public Block() {
        map = null;
        x = 0;
        y = 0;
        visible = false;
        destructable = false;
    }
    
    /**
     * Constructor
     * @param map: Map object
     * @param x: X-coördinaat
     * @param y: Y-coördinaat
     * @param destructable: True als het blokje kan worden opgeblazen
     */
    public Block(BomberMap map, int x, int y, boolean destructable) {
        this();
        this.x = x;
        this.y = y;
        this.destructable = destructable;
        this.map = map;
    }
    
    /**
     * Getfunctie voor de X-locatie van het blokje op de map
     * @return x
     */
    public int getX() {
        return this.x;
    }
    /**
     * Getfunctie voor de Y-locatie van het blokje op de map
     * @return y
     */
    public int getY() {
        return this.y;
    }
    /**
     * Getfunctie voor de visible boolean
     * @return visible
     */
    public boolean getVisible() {
        return this.visible;
    }
    /**
     * Getfunctie voor de destructable boolean
     * @return destructable
     */
    public boolean getDestructable() {
        return this.destructable;
    }
    /**
     * Returnt true als het blokje zichtbaar is op de map
     * @return visible
     */
    public boolean isVisible() {
        return this.getVisible();
    }
    /**
     * Returnt true als het blokje kan worden opgeblazen
     * @return 
     */
    public boolean isDestructable() {
        return this.getDestructable();
    }
    
    
    /**
     * Setfunctie voor de X-locatie van het blokje op de map
     * @param x 
     */
    public void setX(int x) {
        this.x = x;
    }
    /**
     * Setfunctie voor de Y-locatie van het blokje op de map
     * @param y 
     */
    public void setY(int y) {
        this.y = y;
    }
    /**
     * Setfunctie voor de visible boolean
     * @param visible 
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    /**
     * Setfunctie voor de destructable boolean
     * @param destructable 
     */
    public void setDestructable(boolean destructable) {
        this.destructable = destructable;
    }
    /**
     * Algemene setfunctie waarmee alle variabelen opnieuw ingesteld kunnen worden
     * @param x
     * @param y
     * @param visible
     * @param destructable 
     */
    public void setBlock(int x, int y, boolean visible, boolean destructable) {
        this.x = x;
        this.y = y;
        this.visible = visible;
        this.destructable = destructable;
    }
    
    
    /**
     * Controle of het blokje zich op deze locatie bevind
     * @param x: X-coördinaat
     * @param y: Y-coördinaat
     * @return: True als het block zich op de coördinaat van de opgegeven x,y parameters bevind
     */
    public boolean isAt(int x, int y) {
        if ((this.x == x) && (this.y == y)) {
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * Hier wordt het blokje getekend
     * @param g: Bevat het Graphics object naar waar er getekend moet worden
     */
    public void draw(Graphics g) {
        if (this.destructable) {
            g.drawImage(map.game.getImages().getBlockDyn(), x*10, y*10, null);
        }
        else {
            g.drawImage(map.game.getImages().getBlockStat(), x*10, y*10, null);
        }
    }
}