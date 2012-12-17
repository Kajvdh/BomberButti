/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberb1;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.SwingUtilities;
import java.util.Iterator;
/**
 *
 * @author Kaj
 */
public class BomberMap{
    private BomberB1 main;
    private boolean gameOver;
    private Color backgroundColor;
    
    int mapWidth;
    int mapHeight;
    
    public int[][] grid;
    public boolean[][] fireGrid;
    //public BomberBomb[][] bombGrid;
    public boolean[][] strikeGrid;
    public Block[][] bGrid;
    public Bonus[][] bonusGrid;
    //public BomberBonus[][] bonusGrid;
    //public Block[][] blockGrid;
    
    ArrayList<Block> blockGrid = new ArrayList<Block>();
    ArrayList<Bomb> bombGrid = new ArrayList<Bomb>();
    
    public BomberMap() {
        this.backgroundColor = new Color(200,200,200);
        this.mapWidth = 29;
        this.mapHeight = 29;
        strikeGrid = new boolean[30][30];
        bonusGrid = new Bonus[30][30];
        initBlocks();
        initStrikes();
        initBonuses();
    }
    
    private void initBlocks() {
       bGrid =  new Block[30][30];
        for (int i=0;i<30;i++)
            for(int j=0;j<30;j++)
                bGrid[i][j] = null;
        
        
        //bovenste rij (x = [0,49] , y=0)
        for (int i = 0; i <= mapWidth-1; i++) {
            blockGrid.add(new Block(i,0));
            bGrid[i][0] = new Block(i,0);
        }
       
        //onderste rij (x = [0,49] , y=49)
        for (int i = 0; i <= mapWidth-1; i++) {
            blockGrid.add(new Block(i,mapHeight-1));
            bGrid[i][mapHeight-1] = new Block(i,mapHeight-1);
        }
                
        //linkse kolom (x = 0, y = [1,49])
        for (int i = 1; i <= mapHeight-1; i++) {
            blockGrid.add(new Block(0,i));
            bGrid[0][i] = new Block(0,i);
        }
                
        //rechtse kolom (x = 49, y = [0,48])
        for (int i = 0; i <= mapHeight-2; i++) {
            blockGrid.add(new Block(mapWidth-1,i));
            bGrid[mapWidth-1][i] = new Block(mapWidth-1,i);
        }
        
        
        //'tussen' blocks
        for (int i = 2; i <= mapWidth-3; i=i+2) {
            for (int j = 2; j <= mapHeight-3; j=j+2) {
                blockGrid.add(new Block(i,j));
                bGrid[i][j] = new Block(i,j);
            }
        }
        
        //Opblaasbare blocks die bonussen kunnen bevatten
        for (int i = 0; i <= mapWidth-1; i++) {
            for (int j = 0; j <= mapHeight-1; j++) {
                if (!((i<10) && (j<10))) { //spawn hoek vrijhouden
                    if (bGrid[i][j] == null) {
                        bGrid[i][j] = new Block(i,j,true);
                    }
                }
            }
        }
        
        
        
    }
    
    public void initStrikes() {
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                this.strikeGrid[i][j] = false;
            }
        }
    }
    
    public void initBonuses() {
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                this.bonusGrid[i][j] = null;
            }
        }
    }
    
    
    public boolean isStrike(Coord c) {
        return strikeGrid[c.getX()][c.getY()];
    }
    
    public boolean isBlock(Coord c) {
        if (bGrid[c.getX()][c.getY()] != null)
            return true;
        else
            return false;
    }
    public boolean isBomb(Coord c) {
        Coord c2;
        int x1,x2,y1,y2,j;
        j = 0;
        x1 = c.getX();
        y1 = c.getY();
        for (Bomb i : bombGrid) {
            c2 = i.getCoords();
            x2 = c2.getX();
            y2 = c2.getY();
            if ((x1 == x2) && (y1 == y2))
                j++;
        }
        if (j > 0)
            return true;
        else
            return false;
    }
    
    public boolean isStrikable(Coord c) {
        if (bGrid[c.getX()][c.getY()] != null)
        {
            if (bGrid[c.getX()][c.getY()].isDestructable()) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return true;
        }
    }
    
    public boolean isObstacle(Coord c) {
        if ((!isBomb(c)) && (!isBlock(c)))
            return false;
        else
            return true;
    }
    
    public void createBomb(BomberPlayer player, int x, int y) {
        bombGrid.add(new Bomb(player,x,y));
    }
    public void createBomb(Bomb b) {
        bombGrid.add(b);
    }
    
    
    public void act() {
        //Ontplofte bommen van het grid verwijderen
        for (Iterator i = bombGrid.listIterator(); i.hasNext();) {
            Bomb bomb = (Bomb) i.next(); 
                if (bomb.isExploded())
                    i.remove();
        }
        
        //Strikes clearen
        initStrikes();
        
        int bombsDetonatedByStrike = 0;
    
        do {
            for (Iterator i = bombGrid.listIterator(); i.hasNext();) {
                Bomb b = (Bomb) i.next();
                if (bombsDetonatedByStrike == 0) //first run
                    b.act();
                bombsDetonatedByStrike = 0;
                Coord c = b.getCoords(); //Coördinaat van de bom ophalen
                int cx = c.getX();  //Bom x-coördinaat
                int cy = c.getY(); //Bom y-coördinaat
                
                if (strikeGrid[cx][cy]) //Bom wordt geraakt door een strike
                    b.explode(); //Bom laten ontplofen
                
                if ((b.isExploded()) && (!b.getMadeStrike())) { //Bom ontploft en strike nog niet uitgewerkt? -> Strike maken
                    bombsDetonatedByStrike++;
                    int cstrike = b.getStrike(); //Lengte van de strike ophalen
                    int cx_min = cx-cstrike;
                    int cx_max = cx+cstrike;
                    int cy_min = cy-cstrike;
                    int cy_max = cy+cstrike;
                    
                    
                    strikeGrid[cx][cy] = true;
                    boolean strikeBlocked;
                    int k;
                    //Vanaf centrum naar boven toe
                    strikeBlocked = false;
                    k = cy;
                    do {
                        k--;
                        if (isStrikable(new Coord(cx,k))) {
                            strikeGrid[cx][k] = true;
                        }
                        else {
                            strikeBlocked = true;
                        }
                    } while ((k >= cy_min) && (!strikeBlocked));
                    
                    
                    
                    //Vanaf centrum naar onder toe
                    strikeBlocked = false;
                    k = cy;
                    do {
                        k++;
                        if (isStrikable(new Coord(cx,k))) {
                            strikeGrid[cx][k] = true;
                        }
                        else {
                            strikeBlocked = true;
                        }
                    } while ((k <= cy_max) && (!strikeBlocked));
                    
                    //Vanaf centrum naar links toe
                    strikeBlocked = false;
                    k = cx;
                    do {
                        k--;
                        if (isStrikable(new Coord(k,cy))) {
                            strikeGrid[k][cy] = true;
                        }
                        else {
                            strikeBlocked = true;
                        }
                    } while ((k >= cx_min) && (!strikeBlocked));
                    
                    //Vanaf centrum naar rechts toe
                    strikeBlocked = false;
                    k = cx;
                    do {
                        k++;
                        if (isStrikable(new Coord(k,cy))) {
                            strikeGrid[k][cy] = true;
                        }
                        else {
                            strikeBlocked = true;
                        }
                    } while ((k <= cx_max) && (!strikeBlocked));
                    
                    
                    b.striked();
                }
            }
        } while(bombsDetonatedByStrike > 0);
        
        
        
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                if (strikeGrid[i][j]) {
                    //controleren of er bonussen zijn geraakt door een strike
                    if (bonusGrid[i][j] != null) { //bonus geraakt door een strike
                        bonusGrid[i][j] = null; //bonus verwijderen
                    }
                    
                    //controleren of er blocks zijn geraakt door een strike
                    if (bGrid[i][j] != null) { //strike raakt een block
                        if (bGrid[i][j].isDestructable()) { //is normaalgezien altijd
                            bGrid[i][j] = null; //block verwijderen
                            bonusGrid[i][j] = new Bonus(i,j,true); //bonus element (random)
                        }
                    }
                }
            }
        }
        
        
        
        
    }
    
    public void draw(Graphics g) {
        //g.setColor(backgroundColor);
        //g.drawRect(0, 0, 300, 300);
        //g.fillRect(0,0,300,300);
        
        
        for (int i=0;i<30;i++) {
            for (int j = 0; j < 30; j++) {
                if (bonusGrid[i][j] != null)
                    bonusGrid[i][j].draw(g);
            }
        }
        
        
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                if (bGrid[i][j] != null)
                    bGrid[i][j].draw(g);
            }
        }
        
        for (Bomb i : bombGrid) {
            i.draw(g);
        }
        
        g.setColor(Color.magenta);
        for (int i=0;i<30;i++) {
            for (int j=0;j<30;j++) {
                if (strikeGrid[i][j]) {
                    g.drawRect(i*10, j*10, 10, 10);
                    g.fillRect(i*10, j*10, 10, 10);
                }
            }
        }
        
        
        
    }
}