package it.unibs.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

import it.unibs.view.atomicElements.PasswordFieldWithPlaceholder;
import it.unibs.view.atomicElements.RoundedButton;
import it.unibs.view.atomicElements.TextFieldWhitPlaceholder;

public class ViewNewFruitore extends BaseView{
	private static final long serialVersionUID = 1L;
	private String typeUser;
	private ActionListener btnAccediListener;
	private String txtAccesso;
    private JLabel lblAccesso;
    private TextFieldWhitPlaceholder userField;
    private PasswordFieldWithPlaceholder pswField;
    private RoundedButton btnCreazioneFruitore;
    private boolean creazioneUtenteFallita=false;
    
	public ViewNewFruitore(JFrame frame) {
		super(frame);
		this.typeUser = "Fruitore";
		aggiornaComponenti(frame.getWidth(), frame.getHeight());
	}

	@Override
	protected void inizializzaComponenti() {
		lblAccesso = new JLabel();
    	userField = new TextFieldWhitPlaceholder("Username");
    	pswField = new PasswordFieldWithPlaceholder("Password");
    	btnCreazioneFruitore = new RoundedButton("Accedi", new Color(8, 102, 255));
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
        
        btnCreazioneFruitore.setBorder(null);
        btnCreazioneFruitore.setMargin(new Insets(0, 10, 0, 0));
        btnCreazioneFruitore.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnCreazioneFruitore.setBounds(contentWidth / 2 - 170, 350, 340, 60);
        btnCreazioneFruitore.setForeground(Color.WHITE);
        if (btnAccediListener != null) {
            btnCreazioneFruitore.addActionListener(btnAccediListener); // Riaggiungiamo il listener
        }
        contentPanel.add(btnCreazioneFruitore);
        
        revalidate();
        repaint();
	}

}
