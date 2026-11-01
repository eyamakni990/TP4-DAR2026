
package ClientPackage;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClientChatUDP {

    public static void main(String[] args) throws Exception {

        // Création de la socket UDP
        DatagramSocket socket = new DatagramSocket();

        InetAddress adresse = InetAddress.getByName("localhost");
        int port = 1234;

        Scanner sc = new Scanner(System.in);

        // Saisie du nom d'utilisateur
        System.out.print("Entrez votre nom : ");
        String nom = sc.nextLine();

        // Thread pour recevoir les messages
        Thread reception = new Thread(() -> {

            try {
                byte[] buffer = new byte[1024];

                while (true) {

                    DatagramPacket paquet =new DatagramPacket(buffer, buffer.length);

                    socket.receive(paquet);

                    String message = new String(paquet.getData(),0,paquet.getLength());

                    System.out.println("\n" + message);
                    System.out.print("Message : ");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        reception.start();

        // Envoi des messages
        while (true) {

            System.out.print("Message : ");
            String texte = sc.nextLine();

            String message = "[" + nom + "] : " + texte;

            byte[] donnees = message.getBytes();

            DatagramPacket paquet = new DatagramPacket(donnees,donnees.length,adresse,port);

            socket.send(paquet);
        }
    }
}
