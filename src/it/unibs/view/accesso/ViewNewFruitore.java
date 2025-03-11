package it.unibs.view.accesso;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import it.unibs.domain.Comprensorio;
import it.unibs.view.atomicElements.BaseView;
import it.unibs.view.atomicElements.Combobox;
import it.unibs.view.atomicElements.PasswordFieldWithPlaceholder;
import it.unibs.view.atomicElements.RoundedButton;
import it.unibs.view.atomicElements.TextFieldWithPlaceholder;

public class ViewNewFruitore extends BaseView{
	private static final long serialVersionUID = 1L;
	private ActionListener btnCreazioneListener;
	private String typeUser;
	private String txtAccesso;
    private JLabel lblAccesso;
    private TextFieldWithPlaceholder userField;
    private PasswordFieldWithPlaceholder pswField;
    private TextFieldWithPlaceholder emailField;
    private RoundedButton btnCreazioneFruitore;
    private Combobox cmbComprensori;
    private boolean creazioneUtenteFallita=false;
    private String[] nomiComprensori;
    
	public ViewNewFruitore(JFrame frame,String[] nomiComprensori) {
		super(frame);
		this.typeUser = "Fruitore";
		this.nomiComprensori=nomiComprensori;
		inizializzaComponenti();
		aggiornaComponenti(frame.getWidth(), frame.getHeight());
	}

	@Override
	protected void inizializzaComponenti() {
		lblAccesso = new JLabel();
    	userField = new TextFieldWithPlaceholder("Username");
    	pswField = new PasswordFieldWithPlaceholder("Password");
    	emailField = new TextFieldWithPlaceholder("Email");
    	cmbComprensori=new Combobox("Scelta comprensorio");
    	if (nomiComprensori != null) {
    		for (String nome : nomiComprensori) {
				cmbComprensori.addItem(nome);
			}
    		cmbComprensori.setSelectedItem(null);
    	}

    	btnCreazioneFruitore = new RoundedButton("Crea Fruitore", new Color(8, 102, 255));
    }

	@Override
	protected void aggiornaComponenti(int w, int h) {
		contentPanel.removeAll();
        
        // Calcola le dimensioni del pannello interno
        int contentWidth = contentPanel.getWidth();
//        int contentHeight = contentPanel.getHeight();
        
        Color colorTxtAccesso;
        Color colorTxtPlaceholder;
        if(creazioneUtenteFallita) {
        	txtAccesso="Username gia' presente";
        	colorTxtAccesso=Color.RED;
        	colorTxtPlaceholder=Color.RED;
        }
        else {
        	txtAccesso="Creazione "+typeUser;
            colorTxtAccesso=new Color(43, 43, 43);
            colorTxtPlaceholder=Color.GRAY;
        }
        
        lblAccesso.setText(txtAccesso);
        lblAccesso.setForeground(colorTxtAccesso);
        lblAccesso.setFont(new Font("Tahoma", Font.PLAIN, 40));
        Dimension size = lblAccesso.getPreferredSize();
        lblAccesso.setBounds((contentWidth - size.width) / 2, 50, size.width, 70);
        contentPanel.add(lblAccesso);
        
        userField.setColumns(10);
        userField.setMargin(new Insets(10, 10, 10, 10));
        userField.setBounds(contentWidth / 2 - 170, 150, 340, 60);
        userField.setPlaceholderColor(colorTxtPlaceholder);
        contentPanel.add(userField); 
       
        pswField.setColumns(10);
        pswField.setMargin(new Insets(10, 10, 10, 10));
        pswField.setBounds(contentWidth / 2 - 170, 250, 340, 60); 
        pswField.setPlaceholderColor(colorTxtPlaceholder);
        contentPanel.add(pswField); 
        
        emailField.setColumns(10);
        emailField.setMargin(new Insets(10, 10, 10, 10));
        emailField.setBounds(contentWidth / 2 - 170, 350, 340, 60); 
        emailField.setPlaceholderColor(colorTxtPlaceholder);
        contentPanel.add(emailField); 
        
        cmbComprensori.setBounds(contentWidth / 2 - 170, 450, 340, 60);
        contentPanel.add(cmbComprensori);
        
        btnCreazioneFruitore.setBorder(null);
        btnCreazioneFruitore.setMargin(new Insets(0, 10, 0, 0));
        btnCreazioneFruitore.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnCreazioneFruitore.setBounds(contentWidth / 2 - 170, 550, 340, 60);
        btnCreazioneFruitore.setForeground(Color.WHITE);
        if (btnCreazioneListener != null) {
            btnCreazioneFruitore.addActionListener(btnCreazioneListener); // Riaggiungiamo il listener
        }
        contentPanel.add(btnCreazioneFruitore);
        
        revalidate();
        repaint();
	}
	public void setButtonListeners(ActionListener accediListener) {
        this.btnCreazioneListener = accediListener; // Salviamo il listener per il login
        if (btnCreazioneFruitore != null) {
        	btnCreazioneFruitore.addActionListener(accediListener); // Riaggiungiamo il listener
        }
    }
	
	public String getUsername() {
    	return userField.getText();
    }
    @SuppressWarnings("deprecation")
	public String getPassword() {
    	return pswField.getText();
    }
    public String getEmail() {
    	return emailField.getText();
    }
    public int getNomeComprensorioIndex() {
    	return cmbComprensori.getSelectedIndex();
    }
    
    public void setCreazioneFallita() {
        this.creazioneUtenteFallita = true;
        userField.setText("");
        pswField.setText("");
        aggiornaComponenti(frame.getWidth(), frame.getHeight());
        contentPanel.requestFocusInWindow();
    }
    
    //TODO DA ELIMINARE PERCHE SE ESEGUI L'ACCESSO APRI UN ALTRO FRAME 
    public void setCreazioneEseguita() {
    	this.creazioneUtenteFallita=false;
    	aggiornaComponenti(frame.getWidth(), frame.getHeight());
    }

}
