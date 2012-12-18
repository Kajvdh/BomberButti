/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BomberButti;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.lang.Math;
/**
 * De klasse Block beschrijft de blokjes op het scherm, zowel de vaste als de opblaasbare blokjes
 * @author Kaj
 */
public class Block {
    int x; //X-coördinaat
    int y; //Y-coördinaat
    private int width; //Breedte van het blokje
    private int height; //Hoogte van het blokje
    private boolean visible; //True als het blokje zichtbaar moet zijn op de kaart
    private boolean destructable; //True als het blokje kan worden opgeblazen
    
    /**
     * Default constructor
     */
    public Block() {
        x = 0;
        y = 0;
        width = 10;
        height = 10;
        visible = false;
        destructable = false;
    }
    
    /**
     * Constructor
     * @param x: X-coördinaat
     * @param y: Y-coördinaat
     */
    public Block(int x, int y) {
        this();
        this.x = x;
        this.y = y;        
    }
    
    /**
     * Constructor
     * @param x: X-coördinaat
     * @param y: Y-coördinaat
     * @param width: Breedte van het blokje
     * @param height: Hoogte van het blokje
     */
    public Block(int x, int y, int width, int height) {
        this(x,y);
        this.width = width;
        this.height = height;
    }
    
    /**
     * Constructor
     * @param x: X-coördinaat
     * @param y: Y-coördinaat
     * @param destructable: True als het blokje opgeblazen kan worden
     */
    public Block(int x, int y, boolean destructable) {
        this(x,y);
        this.destructable = destructable;
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
    public int getWidth() {
        return this.width;
    }
    public int getHeight() {
        return this.height;
    }
    public boolean getVisible() {
        return this.visible;
    }
    public boolean getDestructable() {
        return this.destructable;
    }
    public boolean isVisible() {
        return this.getVisible();
    }
    public boolean isDestructable() {
        return this.getDestructable();
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
    public void setWidth(int width) {
        this.width = width;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    public void setDestructable(boolean destructable) {
        this.destructable = destructable;
    }
    public void setBlock(int x, int y, int width, int height, boolean visible, boolean destructable) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.visible = visible;
        this.destructable = destructable;
    }
    
    
    /**
     * Controle of het block zich op deze locatie bevind
     * @param x: X-coördinaat
     * @param y: Y-coördinaat
     * @return: True als het block zich op de coördinaat van de opgegeven x,y parameters bevind
     */
    public boolean isAt(int x, int y) {
        if ((this.x == x) && (this.y == y))
            return true;
        else
            return false;
    }
    
    /**
     * Hier wordt het blokje getekend
     * @param g: Bevat het Graphics object naar waar er getekend moet worden
     */
    public void draw(Graphics g) {
        Image blockImage; 
        if (this.destructable)
            blockImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("dynblock.gif")); //Afbeelding inlezen
        else
            blockImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("staticblock.gif")); //Afbeelding inlezen
        
        g.drawImage(blockImage, x*10, y*10, null);
        
        //if (this.destructable)
        //    g.setColor(Color.gray);
        //else
        //    g.setColor(Color.black);
        //
        //g.drawRect(this.x*10,this.y*10,this.width,this.height);
        //g.fillRect(this.x*10,this.y*10,this.width,this.height);
    }
}