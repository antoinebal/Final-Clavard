package clavard;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.awt.Cursor;

public class Connecte implements ActionListener, MouseListener {
	private JFrame frmConnecte;
	int nbCo;
	private String login;
	private ArrayList<String> ListCo;
	private JList list;
	//private ArrayList<JLabel> tabLabel = new ArrayList<JLabel>();
	//private int j=0;
	
	
	public Connecte(String log) {
		login = log;
		Controller ctrl = new Controller();
		ListCo = ctrl.getListeCo();
		//System.out.println(ListCo);
		nbCo = ListCo.size();
		initialize();
	}
	
	
	public void initialize() {
		frmConnecte = new JFrame();
		frmConnecte.getContentPane().setBackground(new Color(0, 0, 51));
		frmConnecte.setForeground(new Color(0, 0, 0));
		frmConnecte.setBackground(new Color(0, 0, 0));
		frmConnecte.setBounds(100, 100, 1080, 720);
		frmConnecte.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmConnecte.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(new Color(0, 0, 128));
		panel.setBounds(833, 0, 229, 673);
		frmConnecte.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Connecté(s) : "+nbCo);
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
		list = new JList(listModel);
		list.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		list.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		list.setBorder(null);
		list.setBackground(new Color(0, 0, 128));
		list.setForeground(Color.WHITE);
		
		/*for(int k=0;k<25;k++) {
			listModel.addElement("test");

		}*/
		
		
		for(String i : ListCo) {
			
			//tabLabel.set(j,new JLabel(i)) ;
			//tabLabel.get(j).setForeground(Color.WHITE);
			//listModel.addElement(tabLabel.get(j));
			listModel.addElement(i);
			
			//j++;
			// EST-CE QUE JE DEVRAIS GARDER LE TABLEAU DE VARIABLE ?
			
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
		frmConnecte.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Bonjour "+login+" !");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(22, 13, 301, 23);
		panel_1.add(lblNewLabel_1);
		frmConnecte.setTitle("Connecté");
		frmConnecte.setVisible(true);
	}

	

   
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
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
		String s = (String) list.getSelectedValue();
		 if (e.getClickCount() == 2) {
	        	
	        	try {
					new Chat(login,s);
					frmConnecte.setVisible(false);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        }
	}
}
