/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BomberButti;
import java.awt.*;
import java.applet.Applet;
import javax.swing.*;
/**
 *
 * @author Kaj
 */
public class BomberImages extends JPanel {
    Image block_dyn, block_stat, strike_mid, strike_hor, strike_ver, player, player2, bomb, bonus_bomb, bonus_strike, bonus_speed;
    MediaTracker tracker;
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
        loadImages();
    }
    
    public void loadImages() {
        tracker = new MediaTracker(this);
        int i = 1;
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
            tracker.waitForAll();
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    
    public Image getBlockDyn() {
        return block_dyn;
    }
    public Image getBlockStat() {
        return block_stat;
    }
    public Image getStrikeMid() {
        return strike_mid;
    }
    public Image getStrikeHor() {
        return strike_hor;
    }
    public Image getStrikeVer() {
        return strike_ver;
    }
    public Image getPlayer() {
        return player;
    }
    public Image getPlayer2() {
        return player2;
    }
    public Image getBomb() {
        return bomb;
    }
    public Image getBonusBomb() {
        return bonus_bomb;
    }
    public Image getBonusStrike() {
        return bonus_strike;
    }
    public Image getBonusSpeed() {
        return bonus_speed;
    }
}
