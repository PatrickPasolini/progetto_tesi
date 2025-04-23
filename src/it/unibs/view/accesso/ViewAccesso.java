package it.unibs.view.accesso;

import java.awt.*;
import javax.swing.*;
import it.unibs.view.atomicElements.*;
import java.awt.event.ActionListener;

public class ViewAccesso extends BaseView { 
	private static final long serialVersionUID = 1L;
	
	private String txtAccesso;
    private JLabel lblAccesso;
    private TextFieldWithPlaceholder userField;
    private PasswordFieldWithPlaceholder pswField;
    private RoundedButton btnAccedi;
    private RoundedButton btnNuovoUtente;
    private ActionListener btnAccediListener;//da salvare fuori dal metodo perche' perderei il riferimento ridisegnando il contentPanel2
   
    private String typeUser;
    private boolean accessoFallito=false;
    
    public ViewAccesso(JFrame frame, String typeUser) {
    	super(frame,950,750);
		this.typeUser = typeUser;
        aggiornaComponenti(frame.getWidth(), frame.getHeight());
    }
    
    protected void inizializzaComponenti() {
    	lblAccesso = new JLabel();
    	userField = new TextFieldWithPlaceholder("Username");
    	pswField = new PasswordFieldWithPlaceholder("Password");
    	btnAccedi = new RoundedButton("Accedi", new Color(8, 102, 255));
    	btnNuovoUtente = new RoundedButton("Crea nuovo "+typeUser, new Color(54, 164, 32));
    }
   
    @Override
	protected void aggiornaComponenti(int w, int h) {
        contentPanel.removeAll();
        int contentWidth = contentPanel.getWidth();
        
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
        lblAccesso.setFont(new Font("Tahoma",  Font.PLAIN, 50));
        Dimension size = lblAccesso.getPreferredSize();
        lblAccesso.setBounds((contentWidth - size.width) / 2, 50, size.width, 70);
        contentPanel.add(lblAccesso);
        
        userField.setColumns(10);
        userField.setMargin(new Insets(10, 10, 10, 10));
        userField.setBounds(contentWidth / 2 - 200, 190, 400, 80);
        userField.setPlaceholderColor(colorTxtPlaceholder);
        contentPanel.add(userField); 
       
        pswField.setColumns(10);
        pswField.setMargin(new Insets(10, 10, 10, 10));
        pswField.setBounds(contentWidth / 2 - 200, 310, 400, 80); 
        pswField.setPlaceholderColor(colorTxtPlaceholder);
        contentPanel.add(pswField); 
        
        btnAccedi.setBorder(null);
        btnAccedi.setMargin(new Insets(0, 10, 0, 0));
        btnAccedi.setFont(new Font("Tahoma", Font.BOLD, 32));
        btnAccedi.setBounds(contentWidth / 2 - 200, 430, 400, 90);
        btnAccedi.setForeground(Color.WHITE);
        if (btnAccediListener != null) {
            btnAccedi.addActionListener(btnAccediListener); // Riaggiungiamo il listener
        }
        contentPanel.add(btnAccedi);
      
        JSeparator line = new JSeparator();
        line.setBounds(contentWidth / 2 - 200, 540, 400, 10);
        line.setForeground(Color.DARK_GRAY);
        contentPanel.add(line);
        
        btnNuovoUtente.setText("Crea nuovo "+typeUser);
        btnNuovoUtente.setBorder(null);
        btnNuovoUtente.setMargin(new Insets(0, 10, 0, 0));
        btnNuovoUtente.setFont(new Font("Tahoma", Font.BOLD, 26));
        btnNuovoUtente.setBounds(contentWidth / 2 - 180, 560, 360, 90);
        btnNuovoUtente.setForeground(Color.white);
        contentPanel.add(btnNuovoUtente);
        
        btnBack.setBounds(45, 45, 90, 90);
        contentPanel.add(btnBack);
        
        revalidate();
        repaint();
    }
    
    public void setAccessoFallito() {
        this.accessoFallito = true;
        userField.setText("");
        pswField.setText("");
        aggiornaComponenti(frame.getWidth(), frame.getHeight());
        contentPanel.requestFocusInWindow();
    }
    
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
    
    @SuppressWarnings("deprecation")
	public String getPassword() {
    	return pswField.getText();
    }
}