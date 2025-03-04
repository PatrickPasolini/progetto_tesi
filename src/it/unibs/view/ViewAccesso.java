package it.unibs.view;
import java.awt.*;
import javax.swing.*;

import it.unibs.view.atomicElements.*;
import it.unibs.view.atomicElements.Button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ViewAccesso extends JPanel { 
    private RoundedPanel contentPanel;
    private JFrame frame;
    private String typeUser;
    private TextFieldWhitPlaceholder userField;
    private PasswordFieldWithPlaceholder pswField;
    private Button btnAccedi;
    private boolean accessoFallito=false;
    public ViewAccesso(JFrame frame, String typeUser) {
        this.frame = frame;
        this.typeUser = typeUser;
        
        frame.getContentPane().removeAll();// Rimuove il contenuto attuale del frame 
        
        setBackground(SystemColor.windowBorder);
        int w = frame.getWidth();
        int h = frame.getHeight();
        this.setBounds(0, 0, w, h);
        this.setBackground(new Color(207, 207, 207)); //43,43,43
        setLayout(null);
        
        contentPanel = new RoundedPanel(20,false);
        contentPanel.setLayout(null);
        contentPanel.setBackground(new Color(230, 230, 230));
        
        // Calcola le dimensioni e la posizione del pannello interno
        int contentWidth = Math.min(600, w - 100);
        int contentHeight = Math.min(650, h - 100);
        int x = (w - contentWidth) / 2;
        int y = (h - contentHeight) / 2-20;
        contentPanel.setBounds(x, y, contentWidth, contentHeight);
        
        add(contentPanel);
        
        aggiornaComponenti(w, h);
        
        // Aggiungi un listener per il ridimensionamento
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int w = frame.getWidth();
                int h = frame.getHeight();
                setBounds(0, 0, w, h);
                
                // Aggiorna la posizione e dimensione del pannello interno
                int contentWidth = Math.min(500, w - 100);
                int contentHeight = Math.min(550, h - 100);
                int x = (w - contentWidth) / 2;
                int y = (h - contentHeight) / 2;
                contentPanel.setBounds(x, y, contentWidth, contentHeight);
                
                aggiornaComponenti(w, h);
            }
        });
        
        frame.getContentPane().add(this); // Aggiunge la nuova schermata
        frame.revalidate();
        frame.repaint();
    }
   
    	
    private void aggiornaComponenti(int w, int h) {
        contentPanel.removeAll();
        
        // Calcola le dimensioni del pannello interno
        int contentWidth = contentPanel.getWidth();
        int contentHeight = contentPanel.getHeight();
        
        String txtAccesso;
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
        
        JLabel lblAccesso = new JLabel(txtAccesso);
        lblAccesso.setForeground(colorTxtAccesso);
        lblAccesso.setFont(new Font("Tahoma", Font.PLAIN, 40));
        Dimension size = lblAccesso.getPreferredSize();
        lblAccesso.setBounds((contentWidth - size.width) / 2, 50, size.width, 70);
        contentPanel.add(lblAccesso);
        
        userField = new TextFieldWhitPlaceholder("Username");
        userField.setColumns(10);
        userField.setMargin(new Insets(10, 10, 10, 10));
        userField.setBounds(contentWidth / 2 - 170, 150, 340, 60);
        userField.setPlaceholderColor(colorTxtPlaceholder);
        contentPanel.add(userField); 
 
        pswField = new PasswordFieldWithPlaceholder("Password");
        pswField.setColumns(10);
        pswField.setMargin(new Insets(10, 10, 10, 10));
        pswField.setBounds(contentWidth / 2 - 170, 250, 340, 60); 
        pswField.setPlaceholderColor(colorTxtPlaceholder);
        contentPanel.add(pswField); 
        
        btnAccedi = new Button("Accedi", new Color(8, 102, 255));
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
        
        Button btnNuovoUtente = new Button("Crea nuovo "+typeUser, new Color(54, 164, 32));
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
    	this.accessoFallito=true;
    	aggiornaComponenti(frame.getWidth(), frame.getHeight());
    }
    //TODO DA ELIMINARE PERCHE SE ESEGUI L'ACCESSO APRI UN ALTRO FRAME 
    public void setAccessoEseguito() {
    	this.accessoFallito=false;
    	aggiornaComponenti(frame.getWidth(), frame.getHeight());
    }
   
    private ActionListener btnAccediListener;//da salvare fuori dal metodo perche' perderei il riferimento ridisegnando il contentPanel
    
    public void setButtonListeners(ActionListener accediListener) {
        this.btnAccediListener = accediListener; // Salviamo il listener
        if (btnAccedi != null) {
            btnAccedi.addActionListener(accediListener); // Se il pulsante esiste già, lo riaggiungiamo
        }
    }

    public String getUsername() {
    	return userField.getText();
    }
    public String getPassword() {
    	return pswField.getText();
    }
}