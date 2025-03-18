package it.unibs.view.configuratore;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import it.unibs.view.atomicElements.BaseView;
import it.unibs.view.atomicElements.CustomScrollBarUI;
import it.unibs.view.atomicElements.RoundedButton;
import it.unibs.view.atomicElements.RoundedButtonPlus;
import it.unibs.view.atomicElements.TextFieldWithPlaceholder;

public class ViewNuovoComprensorio extends BaseView {
	private String txtNuovoComprensorio;
	private JLabel lblNuovoComp;
	private TextFieldWithPlaceholder comprensorioField;
	private TextFieldWithPlaceholder comuneToAddField;
	private RoundedButtonPlus btnPlus;
	private RoundedButton btnConferma;
	private DefaultListModel<String> listModel;
    private JList<String> comuniList;
    
    private ActionListener btnPlusListener;
    private ActionListener btnCreazioneListener;
	private boolean nomeNonUnivoco=false;
	
	public ViewNuovoComprensorio(JFrame frame) {
		super(frame,frame.getWidth()-200,650);
	}

	@Override
	protected void inizializzaComponenti() {
		lblNuovoComp = new JLabel();
		comprensorioField = new TextFieldWithPlaceholder("Nome Comprensorio");
		comuneToAddField = new TextFieldWithPlaceholder("Comune da aggiungere");
		btnPlus = new RoundedButtonPlus(Color.GRAY);
		
		btnConferma = new RoundedButton("Conferma", new Color(8, 102, 255));
		listModel = new DefaultListModel<>();
		comuniList = new JList<>(listModel);
	}

	@Override
	protected void aggiornaComponenti(int w, int h) {
		contentPanel.removeAll();
        
        // Calcola le dimensioni del pannello interno
        int contentWidth = contentPanel.getWidth();
        int contentHeight = contentPanel.getHeight();
        
        Color colorTxtAccesso;
        Color colorTxtPlaceholder;
        
        if(nomeNonUnivoco) { //nome non univoco
        	txtNuovoComprensorio="Nome comprensorio gia' presente,riprova:";
        	colorTxtAccesso=Color.RED;
        	colorTxtPlaceholder=Color.RED;
        }
        else {
        	txtNuovoComprensorio="Creazione nuovo comprensorio";
        	colorTxtAccesso=new Color(43, 43, 43);
            colorTxtPlaceholder=Color.GRAY;
        }
        lblNuovoComp.setText(txtNuovoComprensorio);
        lblNuovoComp.setForeground(colorTxtAccesso);
        lblNuovoComp.setFont(new Font("Tahoma", Font.PLAIN, 40));
        Dimension size = lblNuovoComp.getPreferredSize();
        lblNuovoComp.setBounds((contentWidth - size.width) / 2, 20, size.width, 70);
        contentPanel.add(lblNuovoComp);
        
        comprensorioField.setColumns(10);
        comprensorioField.setMargin(new Insets(10, 10, 10, 10));
        comprensorioField.setBounds(contentWidth / 2 - 170, 150, 340, 60);
        comprensorioField.setPlaceholderColor(colorTxtPlaceholder);
        contentPanel.add(comprensorioField); 
        
        comuneToAddField.setColumns(10);
        comuneToAddField.setMargin(new Insets(10, 10, 10, 10));
        comuneToAddField.setBounds(contentWidth / 2 - 170, 250, 340, 60);
        contentPanel.add(comuneToAddField); 
        
        btnPlus.setBorder(null);
        btnPlus.setFont(new Font("Tahoma", Font.BOLD, 40));
        btnPlus.setBounds(contentWidth / 2 + 171, 251, 60-2, 60-2);
        btnPlus.setForeground(Color.WHITE);
        contentPanel.add(btnPlus);
	    if (btnPlusListener != null) {
	    	btnPlus.addActionListener(btnPlusListener); // Riaggiungiamo il listener
	    }
	    
	    
		comuniList.setFont(new Font("Tahoma", Font.PLAIN, 20));
		JScrollPane scrollPane = new JScrollPane(comuniList);
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		scrollPane.setBounds(contentWidth / 2 - 170, 350, 340, 180);

		// Personalizzazione della scrollbar
		scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		scrollPane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());

		contentPanel.add(scrollPane);

        
        btnConferma.setBorder(null);
        btnConferma.setMargin(new Insets(0, 10, 0, 0));
        btnConferma.setFont(new Font("Tahoma", Font.BOLD, 40));
        btnConferma.setBounds(contentWidth / 2 - 170, 550, 340, 60);
        btnConferma.setForeground(Color.WHITE);
        if (btnCreazioneListener != null) {
        	btnConferma.addActionListener(btnCreazioneListener); // Riaggiungiamo il listener
        }
        contentPanel.add(btnConferma);
        
        revalidate();
        repaint();
        System.out.println(getComuniInseriti());
	}
	
	public List<String> getComuniInseriti() {
	    List<String> comuni = new ArrayList<>();
	    for (int i = 0; i < listModel.getSize(); i++) {
	        comuni.add(listModel.getElementAt(i));
	    }
	    return comuni;
	}
	
	
	public void setBtnPlusListener(ActionListener listener) {
		this.btnPlusListener=listener;
		if (btnPlusListener != null) {
	    	btnPlus.addActionListener(btnPlusListener); // Riaggiungiamo il listener
	    }
	}
	public void setBtnCreazioneListener(ActionListener listener) {
		this.btnCreazioneListener=listener;
		if (btnCreazioneListener != null) {
	    	btnConferma.addActionListener(btnCreazioneListener); // Riaggiungiamo il listener
	    }
	}
	
	public void setAccessoFallito() {
    	this.nomeNonUnivoco=true;
    	comprensorioField.setText(txtNuovoComprensorio);
    	aggiornaComponenti(frame.getWidth(), frame.getHeight());
    	contentPanel.requestFocusInWindow();
    }
	public void setAccessoEseguito() {
		contentPanel.removeAll();
		lblNuovoComp.setText("CREAZIONE EFFETTUATA CON SUCCESSO");
        lblNuovoComp.setForeground(Color.GRAY);
        lblNuovoComp.setFont(new Font("Tahoma", Font.PLAIN, 40));
        Dimension size = lblNuovoComp.getPreferredSize();
        lblNuovoComp.setBounds((contentPanel.getWidth() - size.width) / 2, 20, size.width, 70);
        contentPanel.add(lblNuovoComp);
        revalidate();
        repaint();
	}
    
	
	public String getComuneDaAggiungere() {
	    return comuneToAddField.getText();
	}
	public String getPlaceholderComune() {
	    return comuneToAddField.getPlaceholder();
	}
	public void aggiornaListaComuni(List<String> comuni) {
	    listModel.clear();
	    for (String comune : comuni) {
	        listModel.addElement(comune);
	    }
	    comuneToAddField.setText("");
	}
	public String getNomeComprensorio() {
		return comprensorioField.getText();
	}
}
