package reseau;



import java.util.Scanner;
import java.net.InetAddress;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.io.IOException;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.InterruptedException;

public class ClientTCP {
    private Socket socket_;

    //port d'écoute du srv auquel le client doit se connecter
    private int port_;
    //port d'écoute du srv auquel le client doit se connecter
    private InetAddress address_;
    private TCPListener listener_;
    private InterfaceReseau ir_;
    private BlablaTCP bbTCP_;
    private String pseudoServeur_;

    ClientTCP(int port, InetAddress address, InterfaceReseau ir, String pseudoServeur) {
	address_=address;
	port_=port;
	ir_=ir;
	pseudoServeur_=pseudoServeur;
       	System.out.println("Client : crée pour contacter "+pseudoServeur_);
    }
    
    public void startClient() {
	try{
	    System.out.println("Client démarré.");
	    socket_ = new Socket(address_, port_);
	    //socket_.setSoTimeout(50000);

	    bbTCP_ = new BlablaTCP(pseudoServeur_, ir_, socket_, listener_);
	    
	    //on construit et démarre le listener
	    listener_ = new TCPListener(bbTCP_, socket_);
	    bbTCP_.setListener(listener_);
	    Thread threadListener = new Thread(listener_);
	    threadListener.start();
	     
	    /*on envoie notre pseudo pour être reconnu
	      on fait attendre un peu ce thread
	      pour laisser le temps au serveur d'être en mesure de lire le message*/
	    Thread.sleep(2000);
	    //on envoie notre pseudo pour s'identifier
	    bbTCP_.envoyerPseudoLocal();
	    ir_.nouveauCorrespondant(pseudoServeur_, bbTCP_);
	} catch (UnknownHostException e) {
	    System.out.println("PB starClient : hôte inconnu.");
	    e.printStackTrace();
	} catch (IOException e) {
	    System.out.println("PB starClient : créaion socket.");
	    e.printStackTrace();
	}catch (InterruptedException e) {
	    System.out.println("PB starClient : Thread.sleep().");
	    e.printStackTrace();
	}
				
    }


    public BlablaTCP getBBTCP() {return bbTCP_;}



    public void detruireClient() {
	System.out.println("Destruction Client.");
    }


   

  
    
    /*
      public static void main(String[] args) {
      try {
      Clt client = new Clt(5000, InetAddress.getLocalHost());
      client.startClient();

      Scanner scan = new Scanner(System.in);
      String inputMsg=null;
      while(true) {
      System.out.println("Attente entrée message.");
      if(scan.hasNext()) {
      inputMsg = scan.nextLine();
      }
      client.envoyerMessage(inputMsg);
      }
      } catch (UnknownHostException e) {
      System.out.println("PB getLocalHost.");
      }

      }
    */


}
