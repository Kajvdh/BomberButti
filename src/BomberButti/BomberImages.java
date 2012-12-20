package BomberButti;
import java.awt.*;
import javax.swing.*;
/**
 * De klasse BomberImages zorgt dat alle benodigde afbeeldingen voor het spel juist en volledig ingeladen worden voor aanvang van het spel
 * @author Kaj Van der Hallen
 */
public class BomberImages extends JPanel {
    Image block_dyn, block_stat, strike_mid, strike_hor, strike_ver, player, player2, bomb, bonus_bomb, bonus_strike, bonus_speed;
    
    MediaTracker tracker; //Tracker om de informatie over het laden van de afbeeldingen bij te houden
    
    
    /**
     * Default constructor
     */
    public BomberImages() {
        block_dyn = Toolkit.getDefaultToolkit().getImage(getClass().getResource("dynblock.gif"));
        block_stat = Toolkit.getDefaultToolkit().getImage(getClass().getResource("staticblock.gif"));
        strike_mid = Toolkit.getDefaultToolkit().getImage(getClass().getResource("strike_mid.gif"));
        strike_hor = Toolkit.getDefaultToolkit().getImage(getClass().getResource("strike_horizontal.gif"));
        strike_ver = Toolkit.getDefaultToolkit().getImage(getClass().getResource("strike_vertical.gif"));
        player = Toolkit.getDefaultToolkit().getImage(getClass().getResource("char.gif"));
        player2 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("char2.gif"));
        bomb = Toolkit.getDefaultToolkit().getImage(getClass().getResource("bomb.gif"));
        bonus_bomb = Toolkit.getDefaultToolkit().getImage(getClass().getResource("bonus_bomb.gif"));
        bonus_strike = Toolkit.getDefaultToolkit().getImage(getClass().getResource("bonus_expandstrike.gif"));
        bonus_speed = Toolkit.getDefaultToolkit().getImage(getClass().getResource("bonus_speed.gif"));
        loadImages(); //Afbeeldingen laden
    }
    
    /**
     * Functie die zorgt dat de afbeeldingen, na uitvoer van deze functie, ook daadwerkelijk zijn ingeladen
     */
    private void loadImages() {
        tracker = new MediaTracker(this);
        int i = 1;
        
        /**
         * Alle afbeeldingen aan de tracker toevoegen met een uniek ID
         */
        tracker.addImage(block_dyn,i);
        tracker.addImage(block_stat,++i);
        tracker.addImage(strike_mid,++i);
        tracker.addImage(strike_hor,++i);
        tracker.addImage(strike_ver,++i);
        tracker.addImage(player,++i);
        tracker.addImage(player2,++i);
        tracker.addImage(bomb,++i);
        tracker.addImage(bonus_bomb,++i);
        tracker.addImage(bonus_strike,++i);
        tracker.addImage(bonus_speed,++i);
        try {
            tracker.waitForAll(); //Wachten tot alle afbeeldingen geladen zijn
        }
        catch(InterruptedException e) {
            
        }
    }
    
    /**
     * Getfuncctie voor de afbeelding van opblaasbare blokjes
     * @return 
     */
    public Image getBlockDyn() {
        return block_dyn;
    }
    /**
     * Getfunctie voor de afbeelding van vaste blokjes
     * @return 
     */
    public Image getBlockStat() {
        return block_stat;
    }
    /**
     * Getfunctie voor de afbeelding van strikes in het centrum
     * @return 
     */
    public Image getStrikeMid() {
        return strike_mid;
    }
    /**
     * Getfunctie voor de afbeeldinge van horizontal strikes
     * @return 
     */
    public Image getStrikeHor() {
        return strike_hor;
    }
    /**
     * Getfunctie voor de afbeelding van verticale strikes
     * @return 
     */
    public Image getStrikeVer() {
        return strike_ver;
    }
    /**
     * Getfunctie voor de afbeelding van player 1
     * @return 
     */
    public Image getPlayer() {
        return player;
    }
    /**
     * Getfunctie voor de afbeelding van player 2
     * @return 
     */
    public Image getPlayer2() {
        return player2;
    }
    /**
     * Getfunctie voor de afbeelding van de bommen
     * @return 
     */
    public Image getBomb() {
        return bomb;
    }
    /**
     * Getfunctie voor de afbeelding van de bom-bonus
     * @return 
     */
    public Image getBonusBomb() {
        return bonus_bomb;
    }
    /**
     * Getfunctie voor de afbeelding van de strike-bonus
     * @return 
     */
    public Image getBonusStrike() {
        return bonus_strike;
    }
    /**
     * Getfunctie voor de afbeelding van de speed-bonus
     * @return 
     */
    public Image getBonusSpeed() {
        return bonus_speed;
    }
}