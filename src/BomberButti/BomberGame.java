package BomberButti;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;
/**
* De BomberGame klasse bevat alles van het spel zelf. Vanaf hier worden de map en de players geladen
* @author Kaj Van der Hallen
*/
public class BomberGame extends JPanel implements ActionListener {
    BomberMap map; //Object van de map
    ArrayList<BomberPlayer> players = new ArrayList<>(); //Verzameling van de verschillende spelers
    BomberImages images; //Een object van de BomberImages klasses, die alle benodigde afbeeldingen voor het spel bevat
    BomberPlayer winner; //Een Object van BomberPlayer om de speler die het spel gewonnen heeft in in te laden
    Timer timer; //De timer die zorgt voor de pulsen voor de map en de spelers
    int totalPlayers; //Totaal aantal spelers
    boolean gameOver; //Boolean variabele voor de status van het spel in te stellen
    private int idCounter; //Een teller om elke speler een uniek ID toe te kunnen wijzen
    
    /**
     * Default constructor
     */
    public BomberGame() {
        idCounter = 0;
        images = new BomberImages();
        timer = new Timer(150, this);
    }
    
    /**
     * Functie om het spel te laden
     */
    public void loadGame() {
        this.map = new BomberMap(this); //Nieuw map object aanmaken
        this.gameOver = false; //Spel is niet gameOver
        initPlayers(); //Spelers laden
    }
    
    /**
     * Functie om het spel mee te starten
     */
    public void startGame() {
        timer.stop(); //De timer die voor de pulsen zorgt stoppen als die al actief was
        loadGame(); //Het spel (opnieuw) laden
        timer.start(); //De timer starten
        
    }
    
    /**
     * Functie om de spelers mee te laden
     */
    public void initPlayers() {
        players.removeAll(players); //Eventueel al-geladen spelers terug verwijderen
        players.add(new BomberPlayer(this,map,1,1,1,"Player 1")); //Speler 1 tpevoegen op coördinaat (1,1)
        players.add(new BomberPlayer(this,map,2,27,27,"Player 2")); //Speler 2 toevoegen op coördinaat (27,27)
        //Voor alle spelers de bijbehorende besturingstoetsen inladen
        for (BomberPlayer i : players) {
            i.loadKeys();
        }
    }
    
    /**
     * Functie om een speler aan te maken
     * @param name: Spelernaam
     * @return het id dat de speler heeft toegewezen gekregen
     */
    public int createPlayer(String name) {
        players.add(new BomberPlayer(this,map,++idCounter,1,1,name));
        return idCounter;
    }
    
    /**
     * Getfunctie om het aantal spelers mee op te vragen
     * @return players.size()
     */
    public int getAmountOfPlayers() {
        return players.size();
    }
    
    /**
     * Getfunctie om het object van de klasse BomberImages (dat de afbeeldingen bevat) op te vragen
     * @return images
     */
    public BomberImages getImages() {
        return images;
    }
    
    /**
    * Wordt uitgevoerd op elke puls van de Timer
    * @param e
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            map.act(); //Map een puls geven
            for (BomberPlayer i : players) {
                i.act(); //Spelers een puks geven
            }
            repaint(); //Opnieuw naar het scherm tekenen
        }
    }
    
    /**
    * Wordt uitgevoerd wanneer een toets wordt ingedrukt
    * @param evt
    */
    public void keyPressed(KeyEvent evt) {
        for (BomberPlayer i : players) {
            i.keyPressed(evt); //Toetsinformatie naar elke speler doorsturen
        }
    }
    
    /**
    * Wordt uitgevoerd wanneer een toets wordt losgelaten
    * @param evt
    */
    public void keyReleased(KeyEvent evt) {
        for (BomberPlayer i : players) {
            i.keyReleased(evt); //Toetsinformatie naar elke speler doorsturen
        }
    }
    
    /**
    * Kan worden aangeroepen om het spel te beëindigen
    */
    public void endGame() {
        this.gameOver = true;
        for (Iterator i = players.listIterator(); i.hasNext();) {
            BomberPlayer player = (BomberPlayer) i.next(); 
                if (!player.getIsDead()) {
                this.winner = player;
            }
        }
    }
    
    /**
    * Hier gebeurt het wegschrijven naar het scherm
    * @param g
    */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        map.draw(g);
        for (BomberPlayer i : players) {
            i.draw(g);
        }
        
        if (gameOver) {
            g.drawString("Game Over! De winnaar is: " +winner.getName(), 20, 305);
        }
    }
}