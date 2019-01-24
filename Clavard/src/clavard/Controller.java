package clavard;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import reseau.CorrespondantException;
import reseau.InterfaceReseau;


public class Controller {
	private ArrayList<String> ListeCo = new ArrayList<String>();
	private InterfaceReseau ir_;
	private Connecte fenetreCo_;
	private BDD connexion;
	private String pseudo;
	private boolean ready_=false;
	
	
	/*
	public Controller() {
		ListeCo = new ArrayList<String>();
		ListeCo.add("Le Balayssac FR");
		ListeCo.add("Sauveur Gascou");
		ListeCo.add("th�o");
		ListeCo.add("khalil");
		ListeCo.add("Slim le S");
		ListeCo.add("Mehdi");
	}*/
	
	public Controller() {
		
		//fen�tre d'authentification
		Accueil accueil = new Accueil();
		
		//on r�cup�re le pseudo
		while ((pseudo=accueil.getLog())==null || pseudo=="") {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		//on cr�e l'ir
		ir_ = new InterfaceReseau(pseudo, 6000, 5000, this);
		
		while (!ir_.isCo()) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//on r�cup�re la liste des connect�s
		ListeCo = ir_.annuaireToPseudoList();
		
		connexion = new BDD("C:/Users/Mehdi/Desktop/INSA/4IR/POO/Final-Clavard/Clavard/src/clavard/Clavard.db");
        connexion.connect();
		
		//on a la liste donc on peut cr�er la fenetre connecte
		fenetreCo_ = new Connecte(pseudo, this);
		ready_=true;
	}
	
	/*fonction appel�e depuis le Chat. Appelle envoyerMessage de
	 * InterfaceReseau
	 */
	public void envoyerMessage(String pseudoDest, String message) {
		try {
			ir_.envoyerMessage(pseudoDest, message);
		} catch (CorrespondantException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getListeCo() {
		return ListeCo;
	}

	public void setListeCo(ArrayList<String> listeCo) {
		ListeCo = listeCo;
	}
	
	public void recevoirMessage(String pseudoEmetteur, String message) {
		ecrireBDD(pseudoEmetteur, pseudo, new Date(), message);
		fenetreCo_.ajoutMessageRecu(pseudoEmetteur, message);
	}
	
	public void ecrireBDD(String login_emet,String login_recept,Date date,String mess) {
		connexion.ecrire(login_emet, login_recept, date, mess);
	}
	
	public ArrayList<Message> lireBDD(String login_emet,String login_recept) {
		try {
			return connexion.lire_mess(login_emet, login_recept);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<Message>();
	}
	
	/*appel�e quand l'utilisateur ferme la fen�tre : 
	 * il faut close la bdd et �teindre l'IR
	 */
	public void fermer() {
		connexion.close();
		ir_.extinction();
	}
	
	/* m�thode appel�e par l'IR quand un contact se
	 * d�connecte
	 */
	
	
	public static void main(String[] args) {
		new Controller();
	}
	
	/* fonction � appeler depuis l'ir quand il y a
	 * un nouveau connect�. Le controller va notifier 
	 * la fen�tre Connecte de cela
	 */
	public void nouveauConnecte(String pseudo) {
		if (ready_) {
		ListeCo.add(pseudo);
		fenetreCo_.majListeCo();
		}
	}
	
	/* m�thode appel�e par l'IR quand un contact se
	 * d�connecte
	 */
	public void decoContact(String pseudo) {
		if (ready_) {
		ListeCo.remove(pseudo);
		if (pseudo.equals(fenetreCo_.getRecepteur())) {
			fenetreCo_.cacherChat();
		}
		fenetreCo_.majListeCo();
		}
	}
	
	/*fonction appel�e depuis l'IR, on : 
	 * > m�j la BDD
	 * > m�j l'interface graphique
	 */
	public void traiterNewPseudo(String previousPseudo, String newPseudo) {
		System.out.println("Controller : "+previousPseudo+" s'appelle d�sormais "+newPseudo);
		
		//on m�j la bdd
		connexion.changementPseudo(previousPseudo, newPseudo);
		
		//on m�j l'ig
		try {
			fenetreCo_.afficheChat();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
}
