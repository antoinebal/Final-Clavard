package bdd;

//import java.net.InetAddress;
//import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BDD {
	private String DBPath = "Chemin aux base de donnée SQLite";
    private Connection connection = null;
    private Statement statement = null;
 
    public BDD(String dBPath) {
        DBPath = dBPath;
    }
    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + DBPath);
            statement = connection.createStatement();
            System.out.println("Connexion à " + DBPath + " avec succès");
        } catch (ClassNotFoundException notFoundException) {
            notFoundException.printStackTrace();
            System.out.println("Erreur de connexion");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println("Erreur de connexion");
        }
    }
    public ArrayList<Message> lire_mess(String login_emet,String login_recept) throws ParseException {
    	String emereplaced = login_emet.replace("'", "\\\\");
    	String receplaced = login_recept.replace("'", "\\\\");
    	ResultSet resultSet = query("SELECT * FROM messages WHERE (loginemetteur = '"+emereplaced+"' AND loginrecepteur = '"+receplaced+"') OR (loginemetteur = '"+receplaced+"' AND loginrecepteur = '"+emereplaced+"')");
    	
    	ArrayList<Message> liste = new ArrayList<Message>();
    	
    	try {
            while (resultSet.next()) {
            	//Créer une liste (map ?), mettre tous les messages dedans et envoyer la liste
            	String strdate = resultSet.getString("date");
            	String contenu = resultSet.getString("mess");
            	String loem=resultSet.getString("loginemetteur");
               	String lore=resultSet.getString("loginrecepteur");
               	String loemreplaced=loem.replace("\\\\", "'");
               	String lorereplaced=lore.replace("\\\\", "'");
            	String creplaced=contenu.replace("\\\\", "'");
            	Date date=new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").parse(strdate);
            	Message mess=new Message(loemreplaced,lorereplaced,date,creplaced);
            	liste.add(mess);
                //System.out.println("Le "+resultSet.getString("date")+" : "+resultSet.getString("mess"));
            }
        } catch (SQLException e) {
            //e.printStackTrace();
        } catch (NullPointerException e) {
        	return liste;
        }
    	
    	return liste;
    }
    public void ecrire(String login_emet,String login_recept,Date date,String mess) {
    	System.out.println("BDD : JE SUIS DANS ECRIRE");
    	DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");  
    	String strDate = dateFormat.format(date); 
    	String emetrplcd = login_emet.replace("'", "\\\\");
    	String receptrplcd = login_recept.replace("'", "\\\\");
    	
    	String messrplcd = mess.replace("'", "\\\\");
    		
    	query("INSERT INTO messages(loginemetteur,loginrecepteur, date, mess) VALUES ( '" +emetrplcd+"','"+receptrplcd+"','"+strDate+"','"+messrplcd+"')");
    	  	
    	System.out.println("BDD : JE VIENS DE QUERY, voici la liste des messages dans la bdd");
    	try {
			ArrayList<Message> testPrint = this.lire_mess(login_emet, login_recept);
			for (Message msg : testPrint) {
				//System.out.println("Emetteur : "+msg.getEmetteur()+"  Recepteur : "+msg.getRecepteur()+"  Date : "+msg.getDate()+"  Contenu  :  "+msg.getContenu()); 
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }
    
    
    public ArrayList<String> loginbdd() {
    	ResultSet resultSet = query("Select loginemetteur from messages union select loginrecepteur from messages");
    	ArrayList<String> listepseudos = new ArrayList<String>();
    	try {
            while (resultSet.next()) {
            	String loem=resultSet.getString("loginemetteur");
               	//String lore=resultSet.getString("loginrecepteur");
               	String loemreplaced=loem.replace("\\\\", "'");
               	//String lorereplaced=lore.replace("\\\\", "'");
            	listepseudos.add(loemreplaced);
                //System.out.println("Le "+resultSet.getString("date")+" : "+resultSet.getString("mess"));
            }
        } catch (SQLException e) {
            //e.printStackTrace();
        } catch (NullPointerException e) {
        	return listepseudos;
        }
    	
    	return listepseudos;
    	
    }
    
    /*public InetAddress getIP(String pseudo) {
    	ResultSet resultSet = query("SELECT IP FROM users WHERE pseudo = '" +pseudo+"'");
    	String ip=null;
    	InetAddress inetAddress=null;
    	
		try {
			System.out.println("IP : "+resultSet.getString("IP"));
                
            ip = resultSet.getString("IP");
			inetAddress = InetAddress.getByName(ip);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Mauvais pseudo ou pas d'IP associé");
			
		}catch (UnknownHostException e) {
			e.printStackTrace();
			
		}
			return inetAddress;
	}*/
    
    public void close() {
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }
    public ResultSet query(String requet) {
        ResultSet resultat = null;
        try {
            resultat = statement.executeQuery(requet);
        } catch (SQLException e) {
            //e.printStackTrace();
            
        	//System.out.println("Erreur dans la requête : " + requet);
        }
        return resultat;
  
    }
    
    public void changementPseudo(String previousPseudo, String newPseudo) {
    	String prevrplcd = previousPseudo.replace("'", "\\\\");
    	String newrplcd = newPseudo.replace("'", "\\\\");
    	query("UPDATE messages SET loginemetteur='"+newrplcd+"' WHERE loginemetteur = '"+prevrplcd+"'");
    	query("UPDATE messages SET loginrecepteur='"+newrplcd+"' WHERE loginrecepteur = '"+prevrplcd+"'");
    }
}
