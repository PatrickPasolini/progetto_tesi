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
    private RoundedButton btnHome;
	private boolean nomeNonUnivoco=false;
	
	public ViewNuovoComprensorio(JFrame frame) {
		super(frame,frame.getWidth()-200,650);
	}

	@Override
	protected void inizializzaComponenti() {
		lblNuovoComp = new JLabel("Creazione nuovo comprensorio");
		comprensorioField = new TextFieldWithPlaceholder("Nome Comprensorio");
		comuneToAddField = new TextFieldWithPlaceholder("Comune da aggiungere");
		btnPlus = new RoundedButtonPlus(Color.GRAY);
		btnConferma = new RoundedButton("Conferma", new Color(8, 102, 255));	
		listModel = new DefaultListModel<>();
		comuniList = new JList<>(listModel);
		
		btnHome = new RoundedButton("Home", new Color(8, 102, 255));
	}

	@Override
	protected void aggiornaComponenti(int w, int h) {
		contentPanel.removeAll();
        int contentWidth = contentPanel.getWidth();
        int contentHeight = contentPanel.getHeight();
        
//        Color colorTxtAccesso;
//        Color colorTxtPlaceholder;
//        
//        if(nomeNonUnivoco) {
//        	txtNuovoComprensorio="Nome comprensorio gia' presente,riprova:";
//        	colorTxtAccesso=Color.RED;
//        	colorTxtPlaceholder=Color.RED;
//        }
//        else {
//        	txtNuovoComprensorio="Creazione nuovo comprensorio";
//        	colorTxtAccesso=new Color(43, 43, 43);
//            colorTxtPlaceholder=Color.GRAY;
//        }
        
        lblNuovoComp.setFont(new Font("Tahoma", Font.PLAIN, 40));
        Dimension size = lblNuovoComp.getPreferredSize();
        lblNuovoComp.setBounds((contentWidth - size.width) / 2, 20, size.width, 70);
        contentPanel.add(lblNuovoComp);
        
        comprensorioField.setColumns(10);
        comprensorioField.setMargin(new Insets(10, 10, 10, 10));
        comprensorioField.setBounds(contentWidth / 2 - 170, 150, 340, 60);
        contentPanel.add(comprensorioField); 
        
        comuneToAddField.setColumns(10);
        comuneToAddField.setMargin(new Insets(10, 10, 10, 10));
        comuneToAddField.setBounds(contentWidth / 2 - 170, 250, 340, 60);
        contentPanel.add(comuneToAddField); 
        
        btnPlus.setBorder(null);
        btnPlus.setFont(new Font("Tahoma", Font.BOLD, 40));
        btnPlus.setBounds(contentWidth / 2 + 172, 252, 60-4, 60-4);
        btnPlus.setForeground(Color.WHITE);
        contentPanel.add(btnPlus);
        
		comuniList.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comuniList.setBackground(contentPanel.getBackground());
		JScrollPane scrollPane = new JScrollPane(comuniList);
		scrollPane.setBorder(BorderFactory.createCompoundBorder(
			    BorderFactory.createLineBorder(Color.BLACK, 1),
			    BorderFactory.createEmptyBorder(10, 20, 10, 10)
			));
		scrollPane.setBounds(contentWidth / 2 - 170, 350, 340, 180);
		scrollPane.setBackground(contentPanel.getBackground());
		scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		scrollPane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
		contentPanel.add(scrollPane);

        btnConferma.setBorder(null);
        btnConferma.setMargin(new Insets(0, 10, 0, 0));
        btnConferma.setFont(new Font("Tahoma", Font.BOLD, 40));
        btnConferma.setBounds(contentWidth / 2 - 170, 550, 340, 60);
        btnConferma.setForeground(Color.WHITE);
        contentPanel.add(btnConferma);
        
        revalidate();
        repaint();
	}
	
	public List<String> getComuniInseriti() {
	    List<String> comuni = new ArrayList<>();
	    for (int i = 0; i < listModel.getSize(); i++) {
	        comuni.add(listModel.getElementAt(i));
	    }
	    return comuni;
	}
	
	
	public void setBtnPlusListener(ActionListener listener) {
//		if (btnPlusListener != null) 
		btnPlus.addActionListener(listener); // Riaggiungiamo il listener
	}
	public void setBtnCreazioneListener(ActionListener listener) {
//		if (btnCreazioneListener != null)
		btnConferma.addActionListener(listener); // Riaggiungiamo il listener
	}
	public void setCreazioneFallita_NomeNonUnivoco() {
    	lblNuovoComp.setText("Nome comprensorio gia' presente,riprova:");
    	lblNuovoComp.setForeground(Color.RED);
    	comprensorioField.setText("");
    	comprensorioField.setPlaceholderColor(Color.RED);
    	aggiornaComponenti(frame.getWidth(), frame.getHeight());
    	contentPanel.requestFocusInWindow();
    }
	public void setCreazioneFallita_AlmenoUnComune() {
    	lblNuovoComp.setText("Inserisci almeno un comune nel comprensorio:");
    	lblNuovoComp.setForeground(Color.RED);
    	comuneToAddField.setPlaceholderColor(Color.RED);
    	aggiornaComponenti(frame.getWidth(), frame.getHeight());
    	contentPanel.requestFocusInWindow();
    }
	
	public void setCreazioneEseguita(String nomeComp) {
		frame.setResizable(false);
		contentPanel.removeAll();
		int contentWidth = contentPanel.getWidth();
		
		JLabel lblCreazione = new JLabel();
		lblCreazione.setText("CREAZIONE EFFETTUATA CON SUCCESSO");
		lblCreazione.setForeground(new Color(50, 205, 50));
		lblCreazione.setFont(new Font("Tahoma", Font.BOLD, 40));
        Dimension size = lblCreazione.getPreferredSize();
        lblCreazione.setBounds((contentWidth - size.width) / 2, 20, size.width, 70);
        contentPanel.add(lblCreazione);
        
        JLabel lblNomeComp = new JLabel();
        lblNomeComp.setText("Comprensorio "+comprensorioField.getText()+":");
        lblNomeComp.setForeground(Color.BLACK);
        lblNomeComp.setFont(new Font("Tahoma", Font.BOLD, 40));
        lblNomeComp.setBounds((contentWidth - size.width) / 2, 120, size.width, 70);
        contentPanel.add(lblNomeComp);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.setBackground(contentPanel.getBackground());
        for (int i = 0; i < listModel.size(); i++) {
            JLabel lblComune = new JLabel();
            String item = listModel.getElementAt(i);
            lblComune.setForeground(Color.GRAY);
            lblComune.setFont(new Font("Tahoma", Font.PLAIN, 35));
            lblComune.setText(item);
            lblComune.setAlignmentX(JLabel.LEFT_ALIGNMENT);
            panel.add(lblComune);
        }
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBackground(contentPanel.getBackground());
        scrollPane.setBounds((contentWidth - size.width) / 2, 170+50, size.width, 300); 
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		scrollPane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		contentPanel.add(scrollPane);
        
        btnHome.setMargin(new Insets(0, 10, 0, 0));
        btnHome.setFont(new Font("Tahoma", Font.BOLD, 40));
        btnHome.setBounds(contentWidth / 2 - 170, 530, 340, 60);
        btnHome.setForeground(Color.WHITE);
        contentPanel.add(btnHome);
        
        revalidate();
        repaint();
	}
	public void setBtnHomeListener(ActionListener listener) {
		btnHome.addActionListener(listener); // Riaggiungiamo il listener
	}
    
	public String getComuneDaAggiungere() {
	    return comuneToAddField.getText();
	}
	public String getPlaceholderComune() {
	    return comuneToAddField.getPlaceholder();
	}
	public void aggiornaListaComuni(List<String> comuni) {
	    listModel.clear();
    	comuneToAddField.setPlaceholderColor(Color.GRAY);//x togliere il rosso se prima errore
	    for (String comune : comuni) {
	        listModel.addElement("- "+comune);
	    }
	    comuneToAddField.setText("");
	}
	public String getNomeComprensorio() {
		return comprensorioField.getText();
	}

	public String getPlaceholderComp() {
		return comprensorioField.getPlaceholder();
	}
}
