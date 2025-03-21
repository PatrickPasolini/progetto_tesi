package it.unibs.view.fruitore;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JSeparator;

import it.unibs.domain.Proposta;
import it.unibs.view.atomicElements.BaseView;
import it.unibs.view.atomicElements.RoundedButton;

public class ViewRitiraProposte extends BaseView {

	private JLabel lblRitira;
    private ArrayList<Proposta> scambiAperti;
    private RoundedButton btnRitira;
    private RoundedButton btnHome;
    
    private DefaultListModel<String> listModel;
    private JList<String> scambiList;
	
	public ViewRitiraProposte(JFrame frame,ArrayList<Proposta> scambiAperti) {
		super(frame,frame.getWidth()-200,650);
		this.scambiAperti=scambiAperti;
	}

	@Override
	protected void inizializzaComponenti() {
		lblRitira = new JLabel("Scegli che proposte ritirare:");
		listModel = new DefaultListModel<>();
		scambiList = new JList<>(listModel);
		btnRitira = new RoundedButton("Ritira scambio", new Color(8, 102, 255));
    	btnHome = new RoundedButton("Home", new Color(54, 164, 32));
	}

	@Override
	protected void aggiornaComponenti(int w, int h) {
		contentPanel.removeAll();
        int contentWidth = contentPanel.getWidth();
        int contentHeight = contentPanel.getHeight();
        
        lblRitira.setFont(new Font("Tahoma", Font.PLAIN, 40));
        Dimension size = lblRitira.getPreferredSize();
        lblRitira.setBounds((contentWidth - size.width) / 2, 20, size.width, 70);
        contentPanel.add(lblRitira);
        
        
        btnRitira.setBorder(null);
        btnRitira.setMargin(new Insets(0, 10, 0, 0));
        btnRitira.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnRitira.setBounds(contentWidth / 2 - 170, 350, 340, 60);
        btnRitira.setForeground(Color.WHITE);
        contentPanel.add(btnRitira);
      
        JSeparator line = new JSeparator();
        line.setBounds(contentWidth / 2 - 170, 430, 340, 10);
        line.setForeground(Color.DARK_GRAY);
        contentPanel.add(line);
        
        btnHome.setBorder(null);
        btnHome.setMargin(new Insets(0, 10, 0, 0));
        btnHome.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnHome.setBounds(contentWidth / 2 - 150, 450, 300, 60);
        btnHome.setForeground(Color.white);
        contentPanel.add(btnHome);
        
        
        
        revalidate();
        repaint();
		
	}
	public void aggiornaListaScambi(List<String> scambi) {
	    listModel.clear();
	    for (String s : scambi) {
	        listModel.addElement(estraiRichiestaEOfferta(s));
	    }
	}
	
	public static String estraiRichiestaEOfferta(String input) {
	    input = input.replace("[", "");
	    
	    int richiestaIndex = input.indexOf("richiesta:");
	    int offertaIndex = input.indexOf("offerta:");
	    String richiesta = input.substring(richiestaIndex + 11, input.indexOf("]", richiestaIndex)).trim();
	    String offerta = input.substring(offertaIndex + 8, input.indexOf("]", offertaIndex)).trim();
	    
	    return "<html>- richiesta: " + richiesta + "<br>"+"&nbsp;&nbsp;offerta: " + offerta + "</html>";
	}
	
	public void setBtnRitiraListener(ActionListener listener) {
		btnRitira.addActionListener(listener); // Riaggiungiamo il listener
	}
	public void setBtnHomeListener(ActionListener listener) {
		btnHome.addActionListener(listener); // Riaggiungiamo il listener
	}
}



