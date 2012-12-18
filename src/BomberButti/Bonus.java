package BomberButti;

import java.awt.Color;
import java.awt.Graphics;

/**
 * De Bonus klasse beschrijft de mogelijke bonussen die kunnen voorkomen tijdens het spel
 * @author Kaj
 */
public class Bonus {
    int x; //X-coördinaat
    int y; //Y-coördinaat
    int type; //Type van de bonus
    int width; //Breedte
    int height; //Hoogte
    boolean rendeemed; //True als de bonus is opgebruikt
    private static final int BONUS_AMOUNT = 10;
    /**
     * Default constructor
     */
    public Bonus() {
        x = 0;
        y = 0;
        type = 0;
        width = 10;
        height = 10;
        rendeemed = false;
    }
    
    /**
     * Constructor
     * @param x: X-coördinaat
     * @param y: Y-coördinaat
     * @param randomType: Bij true wordt er random een type bepaald, bij false wordt type 0 gebruikt 
     */
    public Bonus(int x, int y, boolean randomType) {
        this();
        this.x = x;
        this.y = y;
        if (randomType)
            this.type = setRandomBonusType();
    }
    
    
    /**
     * Deze functie kiest een random type
     * @return: Random type integer
     */
    public int setRandomBonusType() {
        int minVal = 0; //Minimumwaarde
        int maxVal = BONUS_AMOUNT; //Maximumwaarde
        int randomVal;
        randomVal = minVal + (int)(Math.random() * ((maxVal - minVal) +1)); //Random getal tussen minimumwaarde en maximumwaarde bepalen
        return randomVal;
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
    public int getType() {
        return this.type;
    }
    public int getWidth() {
        return this.width;
    }
    public int getHeight() {
        return this.height;
    }
    public boolean getRendeemed() {
        return this.rendeemed;
    }
    public boolean isAt(int x, int y) {
        if ((this.x == x) && (this.y == y))
            return true;
        else
            return false;
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
    public void setType(int type) {
        this.type = type;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public void setRendeemed(boolean rendeemed) {
        this.rendeemed = rendeemed;
    }
    
    
    /**
     * Deze functie wordt aangeroepen wanneer deze bonus wordt 'gebruikt'/'opgenomen' door een speler
     * @param player: Object van de klasse BomberPlayer van de speler die de bonus gebruikt
     */
    public void rewardTo(BomberPlayer player) {
        switch(type) { //Switch van het type bonus
            case 1: //extra bomb
                player.incTotalBombs(1); //totalBombs variabele van de speler met 1 verhogen
                break;
            case 2:
                player.incBombStrike(1); //bombStrike variabele van de speler met 1 verhogen
                break;
            case 3:
                player.incSpeed(1); //speed variabele van de speler met 1 verhogen
                break;
            default:
        }
        this.rendeemed = true; //bonus 'waardeloos' maken
    }
    
    /**
     * Hier wordt de bonus getekend
     * @param g: Bevat het Graphics object naar waar er getekend moet worde
     */
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        
        g.drawRect(this.x*10,this.y*10,this.width,this.height);
        g.fillRect(this.x*10,this.y*10,this.width,this.height);
    }
    
}
