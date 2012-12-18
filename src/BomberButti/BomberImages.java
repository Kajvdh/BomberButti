/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BomberButti;
import java.awt.Toolkit;
import java.awt.Image;
/**
 *
 * @author Kaj
 */
public class BomberImages {
    Image block_dyn, block_stat, strike_mid, strike_hor, strike_ver, player, bomb;
    public BomberImages() {
        block_dyn = Toolkit.getDefaultToolkit().getImage(getClass().getResource("dynblock.gif"));
        block_stat = Toolkit.getDefaultToolkit().getImage(getClass().getResource("staticblock.gif"));
        strike_mid = Toolkit.getDefaultToolkit().getImage(getClass().getResource("strike_mid.gif"));
        strike_hor = Toolkit.getDefaultToolkit().getImage(getClass().getResource("strike_horizontal.gif"));
        strike_ver = Toolkit.getDefaultToolkit().getImage(getClass().getResource("strike_vertical.gif"));
        player = Toolkit.getDefaultToolkit().getImage(getClass().getResource("char.gif"));
        bomb = Toolkit.getDefaultToolkit().getImage(getClass().getResource("bomb.gif"));
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
    public Image getBomb() {
        return bomb;
    }
}
