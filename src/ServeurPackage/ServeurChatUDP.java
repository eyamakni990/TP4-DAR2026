
package ServeurPackage;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.util.HashSet;
import java.util.Set;

public class ServeurChatUDP {

    public static void main(String[] args) throws Exception {

        // Création de la socket UDP
        DatagramSocket socket = new DatagramSocket(1234);

        // Liste des clients connectés
        Set<SocketAddress> clients = new HashSet<>();

        System.out.println("Lancement du serveur Chat UDP");

        byte[] buffer = new byte[1024];

        while (true) {

            // Réception d'un message
            DatagramPacket paquet =new DatagramPacket(buffer, buffer.length);

            socket.receive(paquet);

            // Récupération du message
            String message = new String(paquet.getData(),0,paquet.getLength());

            // Récupération de l'adresse du client
            SocketAddress adresseClient = paquet.getSocketAddress();

            // Ajouter le client s'il n'existe pas déjà
            clients.add(adresseClient);

            System.out.println("Message reçu : " + message);

            // Diffusion aux autres clients
            for (SocketAddress client : clients) {

                if (!client.equals(adresseClient)) {

                    byte[] donnees = message.getBytes();

                    DatagramPacket paquetEnvoye =new DatagramPacket(donnees,donnees.length,client);

                    socket.send(paquetEnvoye);
                }
            }
        }
    }
}
