package clavard;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

public class Accueil implements ActionListener, KeyListener{
	private JFrame frmAccueil;
	private JTextField txtfield;
	private JTextField txtLogin;
	private JTextField txtBienvenueSurVotre;
	private JTextField txtVeuillezVousIdentifier;
	private String log;
	private JLabel lblLePseudoNe;
	
	
	
	public String getLog() {
		return log;
	}

	public Accueil() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frmAccueil = new JFrame();
		frmAccueil.getContentPane().setBackground(new Color(0, 0, 51));
		frmAccueil.setForeground(new Color(0, 0, 0));
		frmAccueil.setBackground(new Color(0, 0, 0));
		frmAccueil.setBounds(100, 100, 1080, 720);
		frmAccueil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAccueil.getContentPane().setLayout(null);
		frmAccueil.setTitle("Accueil");
		
		txtfield = new JTextField();
		txtfield.setFont(new Font("Open Sans", Font.PLAIN, 15));
		txtfield.setMargin(new Insets(2, 10, 2, 2));
		txtfield.setForeground(Color.WHITE);
		txtfield.setBackground(new Color(0, 0, 153));
		txtfield.setBounds(389, 407, 261, 28);
		txtfield.addKeyListener(this);
		frmAccueil.getContentPane().add(txtfield);
		txtfield.setColumns(10);
		//txtfield.addActionListener(l);
		
		txtLogin = new JTextField();
		txtLogin.setBorder(null);
		txtLogin.setEditable(false);
		txtLogin.setForeground(Color.WHITE);
		txtLogin.setBackground(new Color(0, 0, 51));
		txtLogin.setFont(new Font("Source Code Pro", Font.ITALIC, 18));
		txtLogin.setText("login :");
		txtLogin.setBounds(238, 400, 91, 40);
		frmAccueil.getContentPane().add(txtLogin);
		txtLogin.setColumns(10);
		
		txtBienvenueSurVotre = new JTextField();
		txtBienvenueSurVotre.setBorder(null);
		txtBienvenueSurVotre.setHorizontalAlignment(SwingConstants.CENTER);
		txtBienvenueSurVotre.setEditable(false);
		txtBienvenueSurVotre.setForeground(new Color(255, 255, 0));
		txtBienvenueSurVotre.setBackground(new Color(0, 0, 51));
		txtBienvenueSurVotre.setFont(new Font("Source Sans Pro", Font.BOLD, 36));
		txtBienvenueSurVotre.setText("Bienvenue sur votre plateforme de clavardage");
		txtBienvenueSurVotre.setBounds(141, 58, 755, 133);
		frmAccueil.getContentPane().add(txtBienvenueSurVotre);
		txtBienvenueSurVotre.setColumns(10);
		
		txtVeuillezVousIdentifier = new JTextField();
		txtVeuillezVousIdentifier.setBorder(null);
		txtVeuillezVousIdentifier.setEditable(false);
		txtVeuillezVousIdentifier.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txtVeuillezVousIdentifier.setForeground(Color.WHITE);
		txtVeuillezVousIdentifier.setBackground(new Color(0, 0, 51));
		txtVeuillezVousIdentifier.setText("Veuillez vous identifier");
		txtVeuillezVousIdentifier.setBounds(403, 228, 228, 28);
		frmAccueil.getContentPane().add(txtVeuillezVousIdentifier);
		txtVeuillezVousIdentifier.setColumns(10);
		
		JButton btnConnexion = new JButton("Connexion");
		btnConnexion.setBounds(701, 409, 97, 25);
		btnConnexion.addActionListener(this);
		//btnConnexion.getInputMap().pu
		//btnConnexion.getInputMap().put(java.awt.event.KeyEvent.VK_ENTER, arg1);
		frmAccueil.getContentPane().add(btnConnexion);
		
		lblLePseudoNe = new JLabel("Le pseudo ne doit ni \u00EAtre vide ni contenir de \":\" ou de \";\" !");
		lblLePseudoNe.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLePseudoNe.setForeground(Color.RED);
		lblLePseudoNe.setBounds(301, 481, 471, 48);
		lblLePseudoNe.setVisible(false);
		frmAccueil.getContentPane().add(lblLePseudoNe);
		
		frmAccueil.setVisible(true);
		

		
	}


	
	public void action() {
		log = this.txtfield.getText();
		if (log.contains(":") || log.contains(";") || log.isEmpty()){
			lblLePseudoNe.setVisible(true);
		}
		else {
			frmAccueil.setVisible(false);	
		}
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		action();
	}
	
	@Override
	public void keyPressed(KeyEvent ke) {
		if (ke.getKeyCode() == 10) {
			action();
		}
	}
	
	@Override
	public void keyTyped(KeyEvent ke) {
	}
	
	@Override
	public void keyReleased(KeyEvent ke) {}
}
