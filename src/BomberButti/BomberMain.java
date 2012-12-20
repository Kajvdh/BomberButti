package BomberButti;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

/**
* Main klasse
* @author Kaj Van der Hallen
*/
public class BomberMain extends JFrame {
    private BomberGame game; //Object van de BomberGame klasse
    
    /**
     * Default constructor
     */
    public BomberMain() {
        initUI(); //Het laden van de User Interface
        
        
        /**
         * Aanmaken van de startbutton
         */
        JButton b = new JButton("Start het spel!");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                BomberMain.this.startGame();
            } 
        });
        add(b);
        
        
        setSize(400, 400);
        setResizable(false);
        setTitle("BomberButti");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        show(); //Alles zichtbaar maken
    }
    
    /**
     * Functie om het spel te starten
     */
    public void startGame() {
        hide(); //Alles verbergen
        getContentPane().removeAll();
        game = new BomberGame();
        add(game);
        addKeyListener(new KeyAdapter() {
            /**
            * Wordt aangeroepen bij het indrukken van een toets
            * @param evt keyboard event
            */
            @Override
            public void keyPressed(KeyEvent evt) {
                if (game != null) {
                    game.keyPressed(evt);
                }
            }
            /**
            * Wordt aangeroepen bij het loslaten van een toets
            * @param evt keyboard event
            */
            @Override
            public void keyReleased(KeyEvent evt) {
                if (game != null) {
                    game.keyReleased(evt);
                }
            }
        });
        game.startGame();
        show();
    }
    
    /**
     * Functie om de User Interface te initialiseren
     */
    private void initUI() {
        initMenubar(); //Menubar laden
    }
    
    /**
     * Functie om de menubar te initialiseren
     */
    private void initMenubar() {
        /* Menu's declareren & initialiseren */
        JMenuBar menubar = new JMenuBar(); //Menubar aanmaken
        JMenu bestand = new JMenu("Bestand"); //Nieuw menu toevoegen
        JMenu help = new JMenu("Help"); //Menu help toevoegen
        
        /* MenuItem's declareren & initialiseren */
        JMenuItem starten = new JMenuItem("Spel starten");
        JMenuItem afsluiten = new JMenuItem("Afsluiten");
        JMenuItem handleiding = new JMenuItem("Handleiding");
        
        /* Tooltips */
        starten.setToolTipText("Het spel starten");
        afsluiten.setToolTipText("Het spel afsluiten");
        handleiding.setToolTipText("De handleiding van het spel openen");
        
        /* Event handling */
        starten.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                game.startGame();
            }
        });
        afsluiten.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.exit(1);
            }
        });
        handleiding.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //handleiding openen
                Desktop dt = Desktop.getDesktop();
                File f = new File("BomberButti_Manual.chm");
                try {
                    dt.open(f);
                }
                catch(IOException e) {
                    
                }
            }
        });
        
        bestand.add(starten);
        bestand.add(afsluiten);
        help.add(handleiding);
        
        menubar.add(bestand);
        menubar.add(help);
        
        setJMenuBar(menubar);
    }
    
    
    
    /**
    * @param args the command line arguments
    */
    public static void main(String[] args) {
        // TODO code application logic here
        BomberMain main = new BomberMain();
    }
}