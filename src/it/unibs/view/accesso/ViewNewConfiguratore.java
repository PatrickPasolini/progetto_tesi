package it.unibs.view.accesso;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

import it.unibs.view.atomicElements.BaseView;
import it.unibs.view.atomicElements.PasswordFieldWithPlaceholder;
import it.unibs.view.atomicElements.RoundedButton;
import it.unibs.view.atomicElements.TextFieldWithPlaceholder;

public class ViewNewConfiguratore extends BaseView {
	private JLabel lblAccesso;
    private TextFieldWithPlaceholder userField;
    private PasswordFieldWithPlaceholder pswField;
    private TextFieldWithPlaceholder emailField;
    private RoundedButton btnCreazioneConfiguratore;
    private boolean creazioneUtenteFallita=false;
    

	private ActionListener btnCreazioneListener;
	    
	public ViewNewConfiguratore(JFrame frame) {
		super(frame,600,650);
	}

	@Override
	protected void inizializzaComponenti() {
		lblAccesso = new JLabel();
    	userField = new TextFieldWithPlaceholder("Username");
    	pswField = new PasswordFieldWithPlaceholder("Password");
    	btnCreazioneConfiguratore = new RoundedButton("Crea Configuratore", new Color(8, 102, 255));
	}

	@Override
	protected void aggiornaComponenti(int w, int h) {
contentPanel.removeAll();
        
        // Calcola le dimensioni del pannello interno
        int contentWidth = contentPanel.getWidth();
        int contentHeight = contentPanel.getHeight();
        
        String txtAccesso;
        Color colorTxtAccesso;
        Color colorTxtPlaceholder;
        if(creazioneUtenteFallita) {
        	txtAccesso="Username gia' presente";
        	colorTxtAccesso=Color.RED;
        	colorTxtPlaceholder=Color.RED;
        }
        else {
        	txtAccesso="Creazione Configuratore";
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
        userField.setBounds(contentWidth / 2 - 170, contentHeight/2 - 30 - 100, 340, 60);
        userField.setPlaceholderColor(colorTxtPlaceholder);
        contentPanel.add(userField); 
       
        pswField.setColumns(10);
        pswField.setMargin(new Insets(10, 10, 10, 10));
        pswField.setBounds(contentWidth / 2 - 170, contentHeight/2 - 30 , 340, 60); 
        pswField.setPlaceholderColor(colorTxtPlaceholder);
        contentPanel.add(pswField); 
        
        btnCreazioneConfiguratore.setBorder(null);
        btnCreazioneConfiguratore.setMargin(new Insets(0, 10, 0, 0));
        btnCreazioneConfiguratore.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnCreazioneConfiguratore.setBounds(contentWidth / 2 - 170, contentHeight/2 - 3 + 100, 340, 60);
        btnCreazioneConfiguratore.setForeground(Color.WHITE);
        if (btnCreazioneListener != null) {
            btnCreazioneConfiguratore.addActionListener(btnCreazioneListener); // Riaggiungiamo il listener
        }
        contentPanel.add(btnCreazioneConfiguratore);
        
        revalidate();
        repaint();
	}
	
	public void setButtonListeners(ActionListener accediListener) {
        this.btnCreazioneListener = accediListener; // Salviamo il listener per il login
        if (btnCreazioneConfiguratore != null) {
        	btnCreazioneConfiguratore.addActionListener(accediListener); // Riaggiungiamo il listener
        }
    }

	public String getUsername() {
    	return userField.getText();
    }
    
    @SuppressWarnings("deprecation")
	public String getPassword() {
    	return pswField.getText();
    }
    public void setCreazioneFallita() {
        this.creazioneUtenteFallita = true;
        userField.setText("");
        pswField.setText("");
        aggiornaComponenti(frame.getWidth(), frame.getHeight());
    }
    
    //TODO DA ELIMINARE PERCHE SE ESEGUI L'ACCESSO APRI UN ALTRO FRAME 
    public void setCreazioneEseguita() {
    	this.creazioneUtenteFallita=false;
    	aggiornaComponenti(frame.getWidth(), frame.getHeight());
    }
}
