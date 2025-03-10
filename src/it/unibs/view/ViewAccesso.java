package it.unibs.view;

import java.awt.*;
import javax.swing.*;
import it.unibs.view.atomicElements.*;
import it.unibs.view.atomicElements.Button;
import java.awt.event.ActionListener;

public class ViewAccesso extends BaseView { 
  
	private String txtAccesso;
    private JLabel lblAccesso;
    private TextFieldWhitPlaceholder userField;
    private PasswordFieldWithPlaceholder pswField;
    private Button btnAccedi;
    private Button btnNuovoUtente;
	
    private String typeUser;
    private boolean accessoFallito=false;
    
    public ViewAccesso(JFrame frame, String typeUser) {
        super(frame);
        this.typeUser = typeUser;
        inizializzaComponenti();
        aggiornaComponenti(frame.getWidth(), frame.getHeight());
    }
    
    protected void inizializzaComponenti() {
    	lblAccesso = new JLabel();
    	userField = new TextFieldWhitPlaceholder("Username");
    	pswField = new PasswordFieldWithPlaceholder("Password");
    	btnAccedi = new Button("Accedi", new Color(8, 102, 255));
    	btnNuovoUtente = new Button("Crea nuovo "+typeUser, new Color(54, 164, 32));
    }
   
    @Override
	protected void aggiornaComponenti(int w, int h) {
        contentPanel.removeAll();
        
        // Calcola le dimensioni del pannello interno
        int contentWidth = contentPanel.getWidth();
//        int contentHeight = contentPanel.getHeight();
        
        Color colorTxtAccesso;
        Color colorTxtPlaceholder;
        if(accessoFallito) {
        	txtAccesso="Accesso fallito,riprova:";
        	colorTxtAccesso=Color.RED;
        	colorTxtPlaceholder=Color.RED;
        }
        else {
        	txtAccesso="Accesso "+typeUser;
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
        
        btnAccedi.setBorder(null);
        btnAccedi.setMargin(new Insets(0, 10, 0, 0));
        btnAccedi.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnAccedi.setBounds(contentWidth / 2 - 170, 350, 340, 60);
        btnAccedi.setForeground(Color.WHITE);
        if (btnAccediListener != null) {
            btnAccedi.addActionListener(btnAccediListener); // Riaggiungiamo il listener
        }
        contentPanel.add(btnAccedi);
      
        JSeparator line = new JSeparator();
        line.setBounds(contentWidth / 2 - 170, 430, 340, 10);
        line.setForeground(Color.DARK_GRAY);
        contentPanel.add(line);
        
        btnNuovoUtente.setBorder(null);
        btnNuovoUtente.setMargin(new Insets(0, 10, 0, 0));
        btnNuovoUtente.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnNuovoUtente.setBounds(contentWidth / 2 - 150, 450, 300, 60);
        btnNuovoUtente.setForeground(Color.white);
        contentPanel.add(btnNuovoUtente);
        
        revalidate();
        repaint();
    }
    
    public void setAccessoFallito() {
        this.accessoFallito = true;
        userField.setText("");
        pswField.setText("");
        aggiornaComponenti(frame.getWidth(), frame.getHeight());

        // Sposta il focus sul pannello per permettere al placeholder di ricomparire
        contentPanel.requestFocusInWindow();
    }
    
    //TODO DA ELIMINARE PERCHE SE ESEGUI L'ACCESSO APRI UN ALTRO FRAME 
    public void setAccessoEseguito() {
    	this.accessoFallito=false;
    	aggiornaComponenti(frame.getWidth(), frame.getHeight());
    }
   
    private ActionListener btnAccediListener;//da salvare fuori dal metodo perche' perderei il riferimento ridisegnando il contentPanel2
    
    public void setButtonListeners(ActionListener accediListener, ActionListener registrazioneListener) {
        this.btnAccediListener = accediListener; // Salviamo il listener per il login
        if (btnAccedi != null) {
            btnAccedi.addActionListener(accediListener); // Riaggiungiamo il listener
        }

        if (btnNuovoUtente != null) {
            btnNuovoUtente.addActionListener(registrazioneListener); // Riaggiungiamo il listener
        }
    }

    public String getUsername() {
    	return userField.getText();
    }
    public String getPassword() {
    	return pswField.getText();
    }
}