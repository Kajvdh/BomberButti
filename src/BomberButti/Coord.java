package BomberButti;

/**
 * De Coord klasse beschrijft een coördinaat van een x,y vlak
 * @author Kaj Van der Hallen
 */
public class Coord {
    int x; //X-coördinaat
    int y; //Y-coördinaat
    
    /**
     * Default constructor
     */
    public Coord() {
        this.x = 0;
        this.y = 0;
    }
    
    /**
     * Constructor
     * @param x: X-coördinaat
     * @param y: Y-coördinaat
     */
    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    
    
    /**
     * Getfunctie van de X-coördinaat
     * @return 
     */
    public int getX() {
        return this.x;
    }
    /**
     * Getfunctie van de Y-coördinaat
     * @return 
     */
    public int getY() {
        return this.y;
    }
    
    /**
     * Setfunctie om de X-coördinaat in te stellen
     * @param x 
     */
    public void setX(int x) {
        this.x = x;
    }
    /**
     * Setfunctie om de Y-coördinaat in te stellen
     * @param y 
     */
    public void setY(int y) {
        this.y = y;
    }
    
    
    /**
     * Functie om de X-coördinaat met 1 te verhogen
     */
    public void incX() {
        this.x++;
    }
    /**
     * Functie om de X-coördinaat met een bepaalde waarde te verhogen
     * @param a: Waarde waarmee de X-coördinaat wordt verhoogd
     */
    public void incX(int a) {
        x = x+a;
    }
    /**
     * Functie op de X-coördinaat met 1 te verlagen
     */
    public void decX() {
        this.x--;
    }
    /**
     * Functie om de X-coördinaat met een bepaalde waarde te verlagen
     * @param a: Waarde waarmee de X-coördinaat wordt verlaagd
     */
    public void decX(int a) {
        x = x-a;
    }
    /**
     * Functie om de Y-coördinaat met 1 te verhogen
     */
    public void incY() {
        this.y++;
    }
    /**
     * Functie om de Y-coördinaat met een bepaalde waarde te verhogen
     * @param a: Waarde waarmee de Y-coördinaat wordt verhoogd
     */
    public void incY(int a) {
        y = y+a;
    }
    /**
     * Functie om de Y-coördinaat met 1 te verlagen
     */
    public void decY() {
        this.y--;
    }
    /**
     * Functie om de Y-coördinaat met een bepaalde waarde te verlagen
     * @param a: Waarde waarmee de Y-coördinaat wordt verlaagd 
     */
    public void decY(int a) {
        y = y-a;
    }
}