package BomberButti;
import java.io.*; 
import java.net.*; 
import java.util.*; //ArrayList, Iterator

/**
 * BomberServer
 * Hier wordt de Server opgezet waarop Clients kunnen connecteren om vervolgens tegen elkaar te spelen
 * @author Kaj
 */
public class BomberServer {
    BomberGame game;
    BomberMap map;
    ArrayList<BomberPlayer> players = new ArrayList<BomberPlayer>();
    
    private int id_counter;
    public BomberServer() throws Exception {
        id_counter = 0;
        game = new BomberGame();
        
        waitForPlayers();
    }
    
    public void waitForPlayers() throws Exception {
        try {
            DatagramSocket serverSocket = new DatagramSocket(9876); //Socket openen
  
            byte[] receiveData = new byte[1024]; //Ontvangen gegevens
            byte[] sendData  = new byte[1024];  //Te verzenden gegevens
            
            int assignedId;
            String assignedIdString;
            
            while(players.size() < 2) {
                receiveData = new byte[1024]; 

                //Ontvangen data (als die er is) ophalen
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length); 

                System.out.println ("Waiting for players to connect");

                serverSocket.receive(receivePacket); 

                String sentence = new String(receivePacket.getData()); 
                
                InetAddress IPAddress = receivePacket.getAddress(); 

                int port = receivePacket.getPort(); 
                
                System.out.println ("From: " + IPAddress + ":" + port);
                System.out.println ("Username: " + sentence);
                
                assignedId = game.createPlayer(sentence);
                
                
                assignedIdString = Integer.toString(assignedId);
                
                sendData = assignedIdString.getBytes();
                
                DatagramPacket sendPacket = 
                   new DatagramPacket(sendData, sendData.length, IPAddress, 
                                     port); 

                serverSocket.send(sendPacket); 

              } 
        }
        catch(SocketException ex) {
            System.out.println("UDP Port 9876 is occupied.");
            System.exit(1);
        }
    }
    public static void main(String args[]) throws Exception  {
        BomberServer server = new BomberServer();
    }
}