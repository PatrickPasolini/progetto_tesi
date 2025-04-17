package it.unibs.view.configuratore;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import it.unibs.domain.Gerarchia;
import it.unibs.domain.NonFoglia;
import it.unibs.view.atomicElements.RoundedButton;
import it.unibs.view.atomicElements.TextFieldWithPlaceholder;

public class ViewAddGerarchiaNonFoglia extends ViewAddGerarchia{
	private static final long serialVersionUID = 1L;
	private JLabel lblRadice;
	private TextFieldWithPlaceholder nomeField;
	private TextFieldWithPlaceholder descrizioneField;
	private RoundedButton btnAvanti;
	private NonFoglia categoria;
	
	public ViewAddGerarchiaNonFoglia(JFrame frame, NonFoglia categoria) {
		super(frame);
		this.categoria = categoria;
		aggiornaComponenti(frame.getWidth(), frame.getHeight());
	}

	@Override
	protected void inizializzaComponenti() {
		super.inizializzaComponenti();
		lblRadice = new JLabel();
		nomeField = new TextFieldWithPlaceholder("Nome categoria della prestazione");
		descrizioneField = new TextFieldWithPlaceholder("Descrizione opzionale");
		btnAvanti = new RoundedButton("Avanti", new Color(8, 102, 255));
	}

	@Override
	protected void aggiornaComponenti(int w, int h) {
		super.aggiornaComponenti(w, h);
		contentPanel.removeAll();
	    int contentWidth = contentPanel.getWidth();
        int contentHeight = contentPanel.getHeight();
        if(categoria!=null) {
        	 String  txt = "<html><div align='center'>" 
     				+ "Inserisci i dati della categoria di prestazione<br>"
     				+ "che stai aggiungendo alla categoria <span style='color:#085FFF;'>" + categoria.getNome()  
     				+ "</span></div></html>";
             
             lblRadice.setText(txt);
             lblRadice.setFont(new Font("Tahoma", Font.BOLD, 55));
             Dimension size = lblRadice.getPreferredSize();
             lblRadice.setBounds((contentWidth - size.width) / 2, 70, size.width, size.height);
             contentPanel.add(lblRadice);
        }
       
        
        nomeField.setColumns(10);
        nomeField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        nomeField.setMargin(new Insets(10, 10, 10, 10));
        nomeField.setBounds(contentWidth / 2 - 250, (contentHeight-240)/2, 500, 100);
        contentPanel.add(nomeField); 
	        																																																	
        descrizioneField.setColumns(10);
        descrizioneField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        descrizioneField.setMargin(new Insets(10, 10, 10, 10));
        descrizioneField.setBounds(contentWidth / 2 - 250, (contentHeight+40)/2, 500, 100);
        contentPanel.add(descrizioneField); 
        
        btnAvanti.setBorder(null);
        btnAvanti.setMargin(new Insets(0, 10, 0, 0));
        btnAvanti.setFont(new Font("Tahoma", Font.BOLD, 40));
        btnAvanti.setBounds(contentWidth / 2 - 200, contentHeight-280, 400, 100);
        btnAvanti.setForeground(Color.WHITE);
        contentPanel.add(btnAvanti);
        
        btnBack.setBounds(45, 45, 90, 90);
        contentPanel.add(btnBack);
        
        btnHome.setBounds(140, 45, 90, 90);
        contentPanel.add(btnHome);
        
        contentPanel.revalidate();
	    contentPanel.repaint();
	}
	
	
	
	
	public void setBtnAvantiListener(ActionListener listener) {
		for (ActionListener al : btnAvanti.getActionListeners()) {
			btnAvanti.removeActionListener(al);
		}
		btnAvanti.addActionListener(listener);
	}
	
	public void setNomeNellaGerarchiaNonUnivoco(ViewAddGerarchiaNonFoglia viewAddGerarchiaRadice) {
		lblRadice.setForeground(Color.RED);
		lblRadice.setText("<html><div align='center'>Nome della categoria iniziale é giá in uso,<br> scegline un altro!</div></html>");  
	    Dimension size = lblRadice.getPreferredSize();
	    int contentWidth = contentPanel.getWidth();
		lblRadice.setBounds((contentWidth - size.width) / 2, 70, size.width, size.height);
	    revalidate();
	    repaint();
	}
	
	public String getNomeField() {
		return nomeField.getText();
	}
	public String getDescrizioneField() {
		return descrizioneField.getText();
	}
	public String getNomePlaceholder() {
		return nomeField.getPlaceholder();
	}
	
	
	
}
