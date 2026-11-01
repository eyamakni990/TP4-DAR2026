package ClientPackage;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClientUDP {
    public static void main(String[] args) throws Exception {
// Le client crée une socket UDP
        DatagramSocket socket = new DatagramSocket();
// Prépare un paquet UDP contenant le message,
// l'adresse du serveur et le port
        Scanner sc = new Scanner(System.in);
        System.out.print("Entrez votre nom : ");
        String nom = sc.nextLine();
        InetAddress adresse = InetAddress.getByName("localhost");
        int port = 1234;

        while (true) {

            System.out.print("Message : ");
            String ch = sc.nextLine();
            // Préfixer le message par le nom
            String message = "[" + nom + "] : " + ch;
            byte buffer[] = message.getBytes();
            int length = buffer.length;
            DatagramPacket donneesEmises =new DatagramPacket(buffer,length,adresse,port);
            // Le client envoie le paquet au serveur
            socket.send(donneesEmises);
        }
    }
}
