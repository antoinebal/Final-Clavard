package reseau;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;

import clavard.Controller;
import reseau.InterfaceReseau.Correspondant;

public class AgentWAN extends InterfaceReseau {
	private HttpTalker httpTalker_;
	
	public AgentWAN(String pseudo, int portServeur, int portUDP, Controller controller) {
		super(pseudo, portServeur, portUDP, controller);
		httpTalker_ = new HttpTalker(this);
		
		/* on demande au secrétaire de remplir l'annuaire avec la liste
		 * renvoyée par le serveur
		 */
		secretaire_.traiteWelcomeMessage(httpTalker_.subscribe());
		
		/* on se passe connecté, ce qui est le signal
		 * pour que le controller puisse récupérer la liste des
		 * connectés
		 */
		connected_=true;
			
		
	}

	@Override
	public void informerNewPseudo(String newPseudo) {
		//on envoie la requête au serveur
		httpTalker_.notifyNewPseudo(newPseudo);
		
		//on change le pseudo enregistré localement
		pseudo_=newPseudo;
	}

	@Override
	public void extinction() {
		
		//on termine toutes les connexions TCP en cours
		for(Map.Entry<String, Correspondant> entry : annuaire_.entrySet()) {
		    String pseudo = entry.getKey();
		    Correspondant corr = entry.getValue();
		    if (corr.coEtablie()) { //on termine les connexions BlablaTCP
			corr.getBBTCP().envoyerTchao(false);
			//argument faux car appelé depuis agent wan
			corr.getBBTCP().terminerConnexion(false);
			System.out.println("AgentWAN : (ext) connexion terminée avec "+pseudo);
		    }
		}
		
		annuaire_.clear();
		System.out.println("AW : ANNUAIRE VIDÉ, CONNEXIONS FERMÉES");
		printAnnuaire();
		
		//on envoie une requête GET au serveur avec l'attribut deconnexion à 1
		httpTalker_.notifyDeconnexion();
		
		termine_=true;	
	}
	
	
		public static void main(String[] args) {		
					Scanner scan = new Scanner(System.in);
					String inputDest=null;
					String inputMsg=null;
					String inputNewPseudo=null;
					try {
					   Controller cont = new Controller(true);
					   String pseudo = "maure";
					   InterfaceReseau ir = new AgentWAN(pseudo, 8002, 6002, cont);		
					   while(true) {
					System.out.println("Qui contacter?");
					if (scan.hasNext()) {
					    inputDest=scan.nextLine();
					}
					if (inputDest.equals("down")) {
					    ir.extinction();
					    break;
					}
					if (inputDest.equals("switch")) {
					    System.out.println("Quel nouveau pseudo choisir?");
					    if (scan.hasNext()) {
						inputNewPseudo=scan.nextLine();
					    }
					    ir.informerNewPseudo(inputNewPseudo);
					    pseudo=inputNewPseudo;
					} else {
					    System.out.println("Quel message envoyer à "+inputDest);
					    if (scan.hasNext()) {
						inputMsg=scan.nextLine();
					   }
					   ir.envoyerMessage(inputDest, inputMsg);					
					   }
				    }
				} catch (CorrespondantException e) {
				    e.printStackTrace();
				}		
		}
	
	

}
