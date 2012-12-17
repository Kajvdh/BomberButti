package BomberButti;

import java.io.*; 
import java.net.*; 
/**
 * BomberServer
 * Hier wordt de Server opgezet waarop Clients kunnen connecteren om vervolgens tegen elkaar te spelen
 * @author Kaj
 */
public class BomberServer {
    public static void main(String args[]) throws Exception  {
        try {
            DatagramSocket serverSocket = new DatagramSocket(9876); //Socket openen
  
            byte[] receiveData = new byte[1024]; //Ontvangen gegevens
            byte[] sendData  = new byte[1024];  //Te verzenden gegevens

            while(true) {
                receiveData = new byte[1024]; 

                //Ontvangen data (als die er is) ophalen
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length); 

                System.out.println ("Waiting for datagram packet");

                serverSocket.receive(receivePacket); 

                String sentence = new String(receivePacket.getData()); 

                InetAddress IPAddress = receivePacket.getAddress(); 

                int port = receivePacket.getPort(); 

                System.out.println ("From: " + IPAddress + ":" + port);
                System.out.println ("Message: " + sentence);

                String capitalizedSentence = sentence.toUpperCase(); 

                sendData = capitalizedSentence.getBytes(); 

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
}
