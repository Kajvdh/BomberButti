package BomberButti;
import java.awt.*;

/**
 * De Bonus klasse beschrijft de mogelijke bonussen die kunnen voorkomen tijdens het spel
 * @author Kaj Van der Hallen
 */
public class Bonus {
    BomberMap map; //Object van de BomberMap
    int x; //X-coördinaat
    int y; //Y-coördinaat
    int type; //Type van de bonus
    boolean rendeemed; //True als de bonus is opgebruikt
    
    /**
     * Default constructor
     */
    public Bonus() {
        x = 0;
        y = 0;
        type = 0;
        rendeemed = false;
    }
    
    /**
     * Constructor
     * @param map: Object van de map
     * @param x: X-coördinaat
     * @param y: Y-coördinaat
     * @param randomType: Bij true wordt er random een type bepaald, bij false wordt type 0 gebruikt 
     */
    public Bonus(BomberMap map, int x, int y, boolean randomType) {
        this();
        this.x = x;
        this.y = y;
        this.map = map;
        if (randomType) {
            this.type = setRandomBonusType();
        }
    }
    
    
    /**
     * Deze functie kiest een random bonustype
     * @return: Random type integer
     */
    private int setRandomBonusType() {
        int minVal = 0; //Minimumwaarde
        int maxVal = 10; //Maximumwaarde
        int randomVal;
        randomVal = minVal + (int)(Math.random() * ((maxVal - minVal) +1)); //Random getal tussen minimumwaarde en maximumwaarde bepalen
        return randomVal;
    }
    
    
    /**
     * Getfunctie voor de X-coördinaat
     * @return x
     */
    public int getX() {
        return this.x;
    }
    /**
     * Getfunctie voor de Y-coördinaat
     * @return y
     */
    public int getY() {
        return this.y;
    }
    /**
     * Getfunctie voor het type bonus
     * @return type
     */
    public int getType() {
        return this.type;
    }
    /**
     * Getfunctie voor de rendeemed boolean
     * @return rendeemed
     */
    public boolean getRendeemed() {
        return this.rendeemed;
    }
    
    /**
     * Controle of de bonus zich op een bepaalde locatie bevind
     * @param x: X-coördinaat
     * @param y: Y-coördinaat
     * @return true als de bonus zich bevindt op coördinaat (x,y)
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
     * Setfunctie voor de X-coördinaat
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
     * Setfunctie voor het type bonus
     * @param type 
     */
    public void setType(int type) {
        this.type = type;
    }
    /**
     * Setfunctie voor de rendeemed boolean
     * @param rendeemed 
     */
    public void setRendeemed(boolean rendeemed) {
        this.rendeemed = rendeemed;
    }
    
    
    /**
     * Deze functie wordt aangeroepen wanneer deze bonus wordt 'gebruikt'/'opgenomen' door een speler
     * @param player: Object van de klasse BomberPlayer van de speler die de bonus gebruikt
     */
    public void rewardTo(BomberPlayer player) {
        switch(type) { //Switch van het type bonus
            case 1: //extra bomb bonus
                player.incTotalBombs(1); //totalBombs variabele van de speler met 1 verhogen
                break;
            case 2: //strike bonus
                player.incBombStrike(1); //bombStrike variabele van de speler met 1 verhogen
                break;
            case 3: //snelheidsbonus
                player.incSpeed(1); //speed variabele van de speler met 1 verhogen
                break;
            default:
        }
        this.rendeemed = true; //bonus 'waardeloos' maken zodat deze niet nogmaals opgenomen kan worden
    }
    
    /**
     * Hier wordt de bonus getekend
     * @param g: Bevat het Graphics object naar waar er getekend moet worde
     */
    public void draw(Graphics g) {
        Image I;
        boolean noBonus = false;
        I = map.game.getImages().getBonusBomb();
        switch(type) {
            case 1:
                I = map.game.getImages().getBonusBomb();
                break;
            case 2:
                I = map.game.getImages().getBonusStrike();
                break;
            case 3:
                I = map.game.getImages().getBonusSpeed();
                break;
            default:
                noBonus = true;
        }
        
        if (!noBonus) {
            g.drawImage(I, x*10, y*10, null);
        }
    }
}