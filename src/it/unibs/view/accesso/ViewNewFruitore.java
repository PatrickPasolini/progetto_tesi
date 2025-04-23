package it.unibs.view.accesso;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import it.unibs.view.atomicElements.BaseView;
import it.unibs.view.atomicElements.CircleHoverIconButton;
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
    private Combobox<String> cmbComprensori;
    private boolean creazioneUtenteFallita=false;
    private String[] nomiComprensori;
    
	public ViewNewFruitore(JFrame frame,String[] nomiComprensori) {
		super(frame,950,750);
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
    	cmbComprensori=new Combobox<String>("Scelta comprensorio");
    	if (nomiComprensori != null) {
    		for (String nome : nomiComprensori) {
				cmbComprensori.addItem(nome);
			}
    		cmbComprensori.setSelectedItem(null);
    	}

    	btnCreazioneFruitore = new RoundedButton("Crea Fruitore", new Color(8, 102, 255));
    	btnBack = new CircleHoverIconButton(ARROWLEFT_PATH, 50);
	}

	@Override
	protected void aggiornaComponenti(int w, int h) {
		contentPanel.removeAll();
        
        // Calcola le dimensioni del pannello interno
        int contentWidth = contentPanel.getWidth();
        
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
        lblAccesso.setFont(new Font("Tahoma",  Font.PLAIN, 50));
        Dimension size = lblAccesso.getPreferredSize();
        lblAccesso.setBounds((contentWidth - size.width) / 2, 50, size.width, 70);
        contentPanel.add(lblAccesso);
        
        userField.setColumns(10);
        userField.setMargin(new Insets(10, 10, 10, 10));
        userField.setBounds(contentWidth / 2 - 185, 170, 370, 80);
        userField.setPlaceholderColor(colorTxtPlaceholder);
        contentPanel.add(userField); 
       
        pswField.setColumns(10);
        pswField.setMargin(new Insets(10, 10, 10, 10));
        pswField.setBounds(contentWidth / 2 - 185, 270, 370, 80); 
        pswField.setPlaceholderColor(colorTxtPlaceholder);
        contentPanel.add(pswField); 
        
        emailField.setColumns(10);
        emailField.setMargin(new Insets(10, 10, 10, 10));
        emailField.setBounds(contentWidth / 2 - 185, 370, 370, 80); 
        emailField.setPlaceholderColor(colorTxtPlaceholder);
        contentPanel.add(emailField); 
        
        cmbComprensori.setBounds(contentWidth / 2 - 185, 470, 370, 80);
        contentPanel.add(cmbComprensori);
        
        JSeparator line = new JSeparator();
        line.setBounds(contentWidth / 2 - 185, 575, 370, 10);
        line.setForeground(Color.DARK_GRAY);
        contentPanel.add(line);
        
        btnCreazioneFruitore.setBorder(null);
        btnCreazioneFruitore.setMargin(new Insets(0, 10, 0, 0));
        btnCreazioneFruitore.setFont(new Font("Tahoma", Font.BOLD, 30));
        btnCreazioneFruitore.setBounds(contentWidth / 2 - 185, 600, 370, 90);
        btnCreazioneFruitore.setForeground(Color.WHITE);
        if (btnCreazioneListener != null) {
            btnCreazioneFruitore.addActionListener(btnCreazioneListener);
        }
        contentPanel.add(btnCreazioneFruitore);
        
        btnBack.setBounds(45, 45, 90, 90);
        contentPanel.add(btnBack);
        
        revalidate();
        repaint();
	}
	public void setButtonListeners(ActionListener accediListener) {
        this.btnCreazioneListener = accediListener;
        if (btnCreazioneFruitore != null) {
        	btnCreazioneFruitore.addActionListener(accediListener);
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
    
    public void setCreazioneEseguita() {
    	this.creazioneUtenteFallita=false;
    	aggiornaComponenti(frame.getWidth(), frame.getHeight());
    }

}
