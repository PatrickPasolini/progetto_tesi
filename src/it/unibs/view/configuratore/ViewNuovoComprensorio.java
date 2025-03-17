package it.unibs.view.configuratore;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.*;

import it.unibs.view.atomicElements.BaseView;
import it.unibs.view.atomicElements.RoundedButton;
import it.unibs.view.atomicElements.TextFieldWithPlaceholder;

public class ViewNuovoComprensorio extends BaseView {
	private JLabel lblNuovoComp;
	private TextFieldWithPlaceholder comprensorioField;
	private TextFieldWithPlaceholder comuneToAddField;
	private RoundedButton btnPlus;
	private RoundedButton btnConferma;
	
	private DefaultListModel<String> listModel;
    private JList<String> comuniList;
	
	public ViewNuovoComprensorio(JFrame frame) {
		super(frame,frame.getWidth()-200,650);
	}

	@Override
	protected void inizializzaComponenti() {
		lblNuovoComp = new JLabel("Creazione nuovo comprensorio");
		comprensorioField = new TextFieldWithPlaceholder("Nome Comprensorio");
		comuneToAddField = new TextFieldWithPlaceholder("Comune da aggiungere");
		btnPlus = new RoundedButton("+",Color.GRAY);
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
        
        lblNuovoComp.setForeground(new Color(43, 43, 43));
        lblNuovoComp.setFont(new Font("Tahoma", Font.PLAIN, 40));
        Dimension size = lblNuovoComp.getPreferredSize();
        lblNuovoComp.setBounds((contentWidth - size.width) / 2, 20, size.width, 70);
        contentPanel.add(lblNuovoComp);
        
        Color colorTxtPlaceholder;
        if(false) { //nome non univoco
//        	txtAccesso="Accesso fallito,riprova:";
//        	colorTxtAccesso=Color.RED;
        	colorTxtPlaceholder=Color.RED;
        }
        else {
//        	txtAccesso="Accesso "+typeUser;
//          colorTxtAccesso=new Color(43, 43, 43);
            colorTxtPlaceholder=Color.GRAY;
        }
        
        comprensorioField.setColumns(10);
        comprensorioField.setMargin(new Insets(10, 10, 10, 10));
        comprensorioField.setBounds(contentWidth / 2 - 170, 150, 340, 60);
        comprensorioField.setPlaceholderColor(colorTxtPlaceholder);
        contentPanel.add(comprensorioField); 
        
        comuneToAddField.setColumns(10);
        comuneToAddField.setMargin(new Insets(10, 10, 10, 10));
        comuneToAddField.setBounds(contentWidth / 2 - 170, 250, 340, 60); 
        comuneToAddField.setPlaceholderColor(colorTxtPlaceholder);
        contentPanel.add(comuneToAddField); 
        
        btnPlus.setBorder(null);
        btnPlus.setMargin(new Insets(0, 10, 0, 0));
        btnPlus.setFont(new Font("Tahoma", Font.BOLD, 40));
        btnPlus.setBounds(contentWidth / 2 + 170, 250, 60, 60);
        btnPlus.setForeground(Color.WHITE);
        btnPlus.addActionListener(e -> {
            String comune = comuneToAddField.getText().trim();
            if (!comune.isEmpty() && !listModel.contains(comune)) {
                listModel.addElement(comune);
                comuneToAddField.setText("");
            }
        });

        contentPanel.add(btnPlus);
        
		JScrollPane scrollPane = new JScrollPane(comuniList);
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		scrollPane.setBounds(contentWidth / 2 - 170, 350, 340, 180); 
		contentPanel.add(scrollPane);
        
        btnConferma.setBorder(null);
        btnConferma.setMargin(new Insets(0, 10, 0, 0));
        btnConferma.setFont(new Font("Tahoma", Font.BOLD, 40));
        btnConferma.setBounds(contentWidth / 2 - 170, 550, 340, 60);
        btnConferma.setForeground(Color.WHITE);
//        if (btnAccediListener != null) {
//            btnPlus.addActionListener(btnAccediListener); // Riaggiungiamo il listener
//        }
        contentPanel.add(btnConferma);
        
	}

}
