package BomberButti;

/**
 * De Coord klasse beschrijft een coördinaat van een x,y vlak
 * @author Kaj
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
     * Getfuncties
     * @return 
     */
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
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
    
    /**
     * Semi-setfuncties
     */
    public void incX() {
        this.x++;
    }
    public void incX(int a) {
        x = x+a;
    }
    public void decX() {
        this.x--;
    }
    public void decX(int a) {
        x = x-a;
    }
    public void incY() {
        this.y++;
    }
    public void incY(int a) {
        y = y+a;
    }
    public void decY() {
        this.y--;
    }
    public void decY(int a) {
        y = y-a;
    }
}
