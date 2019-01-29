package reseau;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import clavard.Controller;

public class HttpTalker {
	
	private AgentWAN aw_;
	
	HttpTalker(AgentWAN aw) {
		aw_=aw;
	}
	
    /*envoie requête http au serveur, retourne la liste des connectés
     * envoyée par le serveur
     */
    public String seConnecter() {
        URL url = construitURL(true, false, false);
        try {
            HttpURLConnection connexion = (HttpURLConnection) url.openConnection();
            connexion.setRequestMethod("GET");
            
            //on n'utilise pas le cache
            connexion.setUseCaches(false);
            
            //on règle la connexion en output
            connexion.setDoOutput(true);
            
            //envoie requête
            //DataOutputStream out = new DataOutputStream(connexion_.getOutputStream());
            //out.writeBytes("");
            
            //on attend une réponse
            InputStream in = connexion.getInputStream();
            BufferedReader buffReader = new BufferedReader(new InputStreamReader(in));
            StringBuilder response = new StringBuilder();
            String line;
            while (((line=buffReader.readLine())!=null)) {
                response.append(line);
                response.append('\r');
            }
            System.out.println("Message reçu : "+response.toString());
            buffReader.close();
            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }    
    }
    
    /* construit l'url en fonction des paramètres */
    public URL construitURL(boolean co, boolean deco, boolean newpseudo) {
        String stringUrl = "http://localhost:8080/clavard-serveur/ClavardServlet?pseudo="+aw_.getPseudo();
        if (co) {
            stringUrl+="&connexion=1&ip="+aw_.getIP()+"&ptcp="+aw_.getPort()+"&pudp="+aw_.getPortUDP();
        }
        if (deco) {
            stringUrl+="&deconnexion=1";
        }
        if (newpseudo) {
            stringUrl+="&newpseudo=1";
        }

        URL url=null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
    
	public static void main(String[] args) {		
		Scanner scan = new Scanner(System.in);
		String inputDest=null;
		String inputMsg=null;
		String inputNewPseudo=null;
		try {
		   Controller cont = new Controller(true);
		   String pseudo = "drassius";
		   InterfaceReseau ir = new AgentWAN(pseudo, 8001, 6001, cont);		
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
