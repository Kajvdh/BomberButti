package BomberButti;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
/**
 *
 * @author Kaj
 */
public class BomberMap{
    public BomberGame game; //Object van BomberGame
    int mapWidth; //Breedte van de map
    int mapHeight; //Hoogte van de map
    
    /**
     *
     */
    public boolean[][] strikeGrid; //Grid van de bombstrikes
    public Block[][] bGrid; //Grid van de blokjes
    public Bonus[][] bonusGrid; //Grid van de bonussen
    
    ArrayList<Bomb> bombGrid = new ArrayList<>(); //'Grid' (ArrayList) van de bommen
    
    /**
     * Default constructor
     */
    public BomberMap() {
        this.mapWidth = 29;
        this.mapHeight = 29;
        strikeGrid = new boolean[30][30];
        bonusGrid = new Bonus[30][30];
        initBlocks(); //Blokjes initialiseren
        initStrikes(); //Strike grid initialiseren
        initBonuses(); //Bonusgrid initialiseren
    }
    
    /**
     * Constructor
     * @param game
     */
    public BomberMap(BomberGame game) {
        this();
        this.game = game;
    }
    
    /**
     * Hier worden de blokjes geïnitialiseerd
     */
    private void initBlocks() {
        bGrid =  new Block[30][30];
        
        //Grid met 'null' waarden opvullen
        for (int i=0;i<30;i++) {
            for(int j=0;j<30;j++) {
                bGrid[i][j] = null;
            }
        }
        
        //bovenste rij vullen met vaste blokjes
        for (int i = 0; i <= mapWidth-1; i++) {
            bGrid[i][0] = new Block(this, i,0, false);
        }
       
        //onderste rij vullen met vaste blokjes
        for (int i = 0; i <= mapWidth-1; i++) {
            bGrid[i][mapHeight-1] = new Block(this, i,mapHeight-1, false);
        }
                
        //linkse kolom vullen met vaste blokjes
        for (int i = 1; i <= mapHeight-1; i++) {
            bGrid[0][i] = new Block(this, 0,i, false);
        }
                
        //rechtse kolom vullen met vaste blokjes
        for (int i = 0; i <= mapHeight-2; i++) {
            bGrid[mapWidth-1][i] = new Block(this, mapWidth-1,i, false);
        }
        
        
        //'tussen' blocks
        for (int i = 2; i <= mapWidth-3; i=i+2) { 
            for (int j = 2; j <= mapHeight-3; j=j+2) {
                bGrid[i][j] = new Block(this, i,j, false);
            }
        }
        
        //Opblaasbare blocks die bonussen kunnen bevatten
        for (int i = 0; i <= mapWidth-1; i++) {
            for (int j = 0; j <= mapHeight-1; j++) {
                if (((!((i<10) && (j<10)))) && (!((i>18) && (j>18)))) { //spawn hoeken linksboven & rechtsonder vrijhouden
                    if (bGrid[i][j] == null) {
                        bGrid[i][j] = new Block(this, i,j,true);
                    }
                }
            }
        }
    }
    
    /**
     * Strikes initialiseren
     */
    public void initStrikes() {
        //Grid opvullen met 'null' waarden
        for (int i = 0; i < 30; i++) { 
            for (int j = 0; j < 30; j++) {
                this.strikeGrid[i][j] = false;
            }
        }
    }
    
    /**
     * Bonussen initialiseren
     */
    public void initBonuses() {
        //Grid opvullen met 'null' waarden
        for (int i = 0; i < 30; i++) { 
            for (int j = 0; j < 30; j++) {
                this.bonusGrid[i][j] = null;
            }
        }
    }
    
    /**
     * Deze functie dient om te controleren of er op een bepaalde coördinaat een strike is
     * @param c: Coördinaat van de te controleren locatie
     * @return true als er op de opgegeven locatie een strike is
     */
    public boolean isStrike(Coord c) {
        return strikeGrid[c.getX()][c.getY()];
    }
    
    /**
     * Deze functie dient om te controleren of er op een bepaalde coördinaat een blokje staat
     * @param c: Coördinaat van de te controleren locatie
     * @return true als er op de opgegeven locatie een blokje staat
     */
    public boolean isBlock(Coord c) {
        if (bGrid[c.getX()][c.getY()] != null) {
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * Deze functie dient om te controleren of er op een bepaalde coördinaat een bom ligt
     * @param c: Coördinaat van de te controleren locatie
     * @return true als er op de opgegeven locatie een bom ligt
     */
    public boolean isBomb(Coord c) {
        Coord c2;
        int x1,x2,y1,y2,j;
        j = 0;
        x1 = c.getX(); //X-coördinaat van ophalen
        y1 = c.getY(); //Y-coördinaat ophalen
        for (Bomb i : bombGrid) { //Loop door alle bommen
            c2 = i.getCoords();
            x2 = c2.getX();
            y2 = c2.getY();
            if ((x1 == x2) && (y1 == y2)) {
                j++;
            }
        }
        if (j > 0) {
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * Deze functie dient om te controleren of de opgegeven locatie geraakt kan worden door een strike
     * @param c: Coördinaat van de te controleren locatie
     * @return true als de opgegeven locatie door een strike geraakt kan worden
     */
    public boolean isStrikable(Coord c) {
        if (bGrid[c.getX()][c.getY()] != null) {
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
    
    /**
     * Deze functie dient om te controleren of op de opgegeven locatie een obstakel is waar de speler niet kan doorlopen
     * @param c: Coördinaat van de te controleren locatie
     * @return true als op de opgegeven locatie een obstakel is
     */
    public boolean isObstacle(Coord c) {
        if ((!isBomb(c)) && (!isBlock(c))) {
            return false;
        }
        else {
            return true;
        }
    }
    
    /**
     * Deze functie wordt door BomberPlayer aangeroepen om een bom toe te voegen aan de map
     * @param b: Object van de bom, aangemaakt door de speler
     */
    public void createBomb(Bomb b) {
        bombGrid.add(b);
    }
    
    /**
     * Functie om te controleren of een speler over een bonus loopt, indien ja -> deze bonus overdragen aan speler
     * @param player: Object van de speler
     */
    public void checkBonus(BomberPlayer player) {
        int x = player.getX();
        int y = player.getY();
        if (bonusGrid[x][y] != null) { //Er is een bonus op deze locatie -> aan speler geven
            bonusGrid[x][y].rewardTo(player);
            bonusGrid[x][y] = null;
        }
    }
    
    /**
     * Deze functie wordt aangeroepen bij elke timerpuls van BomberGame
     * Hierin zit alle logica van de map
     */
    public void act() {
        //Ontplofte bommen van het grid verwijderen
        for (Iterator i = bombGrid.listIterator(); i.hasNext();) {
            Bomb bomb = (Bomb) i.next(); 
                if (bomb.isExploded()) {
                i.remove();
            }
        }
        
        //Alle strikes verwijderen (strikes blijven maar 1 puls)
        initStrikes();
        
        //Strike maken voor bommen die ontploft zijn & bommen die geraakt worden door een strike doen ontploffen
        int bombsDetonatedByStrike = 0; //teller voor het aantal bommen dat ontploft is doordat ze door een strike zijn geraakt
        do {
            for (Iterator i = bombGrid.listIterator(); i.hasNext();) { //Loop door het bombGrid
                Bomb b = (Bomb) i.next(); //Huidige bomb
                if (bombsDetonatedByStrike == 0) {
                    b.act();
                } //Bom 'acten' (puls geven naar bom om countdown-1,..
                bombsDetonatedByStrike = 0;
                Coord c = b.getCoords(); //Coördinaat van de bom ophalen
                int cx = c.getX();  //Bom x-coördinaat
                int cy = c.getY(); //Bom y-coördinaat
                
                if (strikeGrid[cx][cy]) {
                    b.explode();
                } //Bom laten ontplofen
                
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
                            if (bGrid[cx][k] != null) {
                                strikeBlocked = true;
                            }
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
                            if (bGrid[cx][k] != null) {
                                strikeBlocked = true;
                            }
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
                            if (bGrid[k][cy] != null) {
                                strikeBlocked = true;
                            }
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
                            if (bGrid[k][cy] != null) {
                                strikeBlocked = true;
                            }
                        }
                        else {
                            strikeBlocked = true;
                        }
                    } while ((k <= cx_max) && (!strikeBlocked));
                    b.striked(); //Bom heeft een strike gemaakt
                }
            }
        } while(bombsDetonatedByStrike > 0);
        
        
        /**
         * Schade door strikes (buiten bommen)
         */
        for (int i = 0; i < 30;  i++) {
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
                            bonusGrid[i][j] = new Bonus(this, i,j,true); //bonus element (random)
                        }
                    }
                }
            }
        }
    }
    
    public void draw(Graphics g) {
        /**
         * @todo: Zorgen dat er onderscheid kan gemaakt worden bij strikes:
         * Strike in het centrum moet afbeelding strike_mid krijgen
         * Horizontale strikes: strike_hor
         * Verticale strikes: strike_ver
         */
        
        /**
         * Bonussen tekenen
         */
        for (int i=0;i<30;i++) { 
            for (int j = 0; j < 30; j++) { 
                if (bonusGrid[i][j] != null) {
                    bonusGrid[i][j].draw(g);
                }
            }
        }
        
        
        /**
         * Blokjes tekenen
         */
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) { 
                if (bGrid[i][j] != null) {
                    bGrid[i][j].draw(g);
                }
            }
        }
        
        /**
         * Bommen tekenen
         */
        for (Bomb i : bombGrid) {
            i.draw(g);
        }
        
        /**
         * Strikes tekenen
         */
        for (int i=0;i<30;i++) {
            for (int j=0;j<30;j++) {
                if (strikeGrid[i][j]) {
                    g.drawImage(game.getImages().getStrikeMid(), i*10, j*10, null);
                }
            }
            }
    }
}