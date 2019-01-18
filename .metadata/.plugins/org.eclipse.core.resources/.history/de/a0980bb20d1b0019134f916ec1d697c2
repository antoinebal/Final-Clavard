package clavard;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;

public class Chat implements ActionListener, MouseListener, KeyListener{

	private JFrame frmChat;
	int nbCo;
	private String login;
	private ArrayList<String> ListCo;
	private JTextField textField;
	private String recepteur;
	private BDD connexion;
	private ArrayList<JTextArea> tabTextArea = new ArrayList<JTextArea>();
	private int j=0;
	ArrayList<String> l_messages; 
	JTextArea TextArea;
	JPanel panel_4;
	
	
	
	public Chat(String log, String recept) throws ParseException {
		login = log;
		Controller ctrl = new Controller();
		ListCo = ctrl.getListeCo();
		System.out.println(ListCo);
		nbCo = ListCo.size();
		recepteur = recept;
		connexion = new BDD("Clavard.db");
        connexion.connect();
		initialize();
	}
	
	
	public void initialize() throws ParseException {
		frmChat = new JFrame();
		frmChat.getContentPane().setBackground(new Color(0, 0, 51));
		frmChat.setForeground(new Color(0, 0, 0));
		frmChat.setBackground(new Color(0, 0, 0));
		frmChat.setBounds(100, 100, 1080, 720);
		frmChat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmChat.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(new Color(0, 0, 128));
		panel.setBounds(833, 0, 229, 673);
		frmChat.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Connect�(s) : "+nbCo);
		lblNewLabel.setBounds(56, 103, 130, 23);
		lblNewLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(new Color(0, 0, 0));
		panel.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 0, 128));
		panel_2.setBounds(0, 215, 228, 458);
		panel.add(panel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 228, 458);
		scrollPane.setBorder(null);
		DefaultListModel listModel = new DefaultListModel();
		JList list = new JList(listModel);
		list.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		list.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		list.setBorder(null);
		list.setBackground(new Color(0, 0, 128));
		list.setForeground(Color.WHITE);
		
		
		for(String i : ListCo) {
						
			listModel.addElement(i);
			
		}
		panel_2.setLayout(null);
		list.setModel(listModel);
		list.setBounds(0, 0, 228, 347);
		DefaultListCellRenderer renderer = (DefaultListCellRenderer) list.getCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setViewportView(list);
		list.addMouseListener(this);
		panel_2.add(scrollPane);
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		panel_1.setBackground(new Color(0, 0, 205));
		panel_1.setBounds(0, 0, 835, 673);
		frmChat.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Bonjour "+login+" !");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(22, 13, 301, 23);
		panel_1.add(lblNewLabel_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 0, 205));
		panel_3.setBounds(62, 124, 773, 517);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		JButton btnEnvoyer = new JButton("Envoyer");
		btnEnvoyer.setBounds(637, 400, 97, 25);
		btnEnvoyer.addActionListener(this);
		panel_3.add(btnEnvoyer);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 400, 597, 38);
		panel_3.add(scrollPane_1);
		
		textField = new JTextField();
		scrollPane_1.setViewportView(textField);
		textField.addKeyListener(this);
		textField.setColumns(10);
		
		panel_4 = new JPanel();
		panel_4.setBounds(12, 0, 720, 383);
		panel_3.add(panel_4);
		panel_4.setLayout(null);
		
				
		
		TextArea = new JTextArea();
		TextArea.setEditable(false);
		JScrollPane scroll = new JScrollPane(TextArea);
		
		
		l_messages = new ArrayList<String>();
		ArrayList<Message> liste_bdd = connexion.lire_mess(login, recepteur);
		for (Message obj : liste_bdd) {
			if(obj.emetteur.equals(login)) {
				l_messages.add("Moi : "+obj.contenu);
			}
			else {
				l_messages.add(recepteur+" : "+obj.contenu);
			}
			//listModel.addElement(tabLabel.get(j));
			
		}
		System.out.println("l_messages "+l_messages);
		System.out.println("ArrayList<Messages> : "+liste_bdd);
		
		
		for (String str : l_messages) {
			System.out.println(str);
			/*tabTextArea.add(new JTextArea(str)) ;
			tabTextArea.get(j).setEditable(false);*/
			
			//tabTextArea.get(j).setForeground(Color.WHITE);
			//tabTextArea.get(j).setText(str);
			
			    TextArea.append(str+"\n");
			//scrollPane_2.add(tabTextArea.get(j), j);
			     TextArea.setBounds(12, 13, 696, 357);
			     panel_4.add(TextArea);
			

			j++;
		}
		
		
		panel_4.add(scroll);
		
		
		JLabel lblNewLabel_3 = new JLabel("Discussion avec "+recepteur);
		lblNewLabel_3.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Book Antiqua", Font.PLAIN, 17));
		lblNewLabel_3.setBounds(282, 65, 306, 33);
		panel_1.add(lblNewLabel_3);
		frmChat.setTitle("Chat");
		frmChat.setVisible(true);
		
		
		
	}
	

	public void afficherChat() throws ParseException {
		/* ArrayList<Message> liste_envoyes;
		ArrayList<Message> liste_recus;
		l_messages = new ArrayList<String>();
		liste_envoyes = connexion.lire_mess(login, recepteur);
		for (Message obj : liste_envoyes) {
			
			//listModel.addElement(tabLabel.get(j));
			l_messages.add("Moi : "+obj.contenu);
		}
		
		liste_recus = connexion.lire_mess(recepteur, login);
		System.out.println(liste_recus);
		for (Message mess : liste_recus) {
			l_messages.add(recepteur+" : "+mess.contenu);
		}
		
		for (String str : l_messages) {
			System.out.println(str);
			/*tabTextArea.add(new JTextArea(str)) ;
			tabTextArea.get(j).setEditable(false);*/
			
			//tabTextArea.get(j).setForeground(Color.WHITE);
			//tabTextArea.get(j).setText(str);
			
		//	    TextArea.append(str+"\n");
			//scrollPane_2.add(tabTextArea.get(j), j);
		//	     TextArea.setBounds(12, 13, 696, 357);
		//	panel_4.add(TextArea);
			

		//	j++;
	//	}
		// QUAND IL Y A // BIEN AVANT LE TEXTE C'EST QUE CA FAIT PARTI DU CODE (ECLIPSE A PAS G�R� LES COMMENTAIRES)
	}


	
	public void envoi(String mess) {
		/*�criture dans la base de donn�es et refresh ?
		 �crire BDD */
		if(mess.isEmpty()) {
		}
		else {
		Date date=new Date();
		connexion.ecrire(login, recepteur, date, mess);
		TextArea.append("Moi : "+mess+"\n");
		textField.setText("");
		
		//connexion.close();
		}
	}
   
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		envoi(this.textField.getText());
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		 if (e.getClickCount() == 2) {
	        	//frmChat.setVisible(false);
	        }
	}


	@Override
	public void keyPressed(KeyEvent ke) {
		// TODO Auto-generated method stub
		if (ke.getKeyCode() == 10) {
			envoi(this.textField.getText());
		}
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
