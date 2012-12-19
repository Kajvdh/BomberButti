/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BomberButti;
import java.awt.event.KeyEvent;
import java.io.*;
/**
 *
 * @author Kaj
 */
public class KeyConfig {
    public int keys[][];
    int UP = 0;
    int DOWN = 1;
    int LEFT = 2;
    int RIGHT = 3;
    int BOMB = 4;
    
    public KeyConfig() {
        initKeys();
    }
    
    public void initKeys() {
        keys = new int[2][5];
        
        /**
         * Player 1
         */
        keys[0][UP] = KeyEvent.VK_Z; //Omhoog
        keys[0][DOWN] = KeyEvent.VK_S; //Omlaag
        keys[0][LEFT] = KeyEvent.VK_Q; //Links
        keys[0][RIGHT] = KeyEvent.VK_D; //Rechts
        keys[0][BOMB] = KeyEvent.VK_SHIFT; //Bom leggen
        
        
        /**
         * Player 2
         */
        keys[1][UP] = KeyEvent.VK_I; //Omhoog
        keys[1][DOWN] = KeyEvent.VK_K; //Omlaag
        keys[1][LEFT] = KeyEvent.VK_J; //Links
        keys[1][RIGHT] = KeyEvent.VK_L; //Rechts
        keys[1][BOMB] = KeyEvent.VK_M; //Bom leggen
    }
    
    
    public int[] getKeys(int id) {
        if (--id == 0) 
            return keys[0];
        else
            return keys[1];
    }
    
}
