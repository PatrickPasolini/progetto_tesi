package it.unibs.view.configuratore;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JLabel;

import it.unibs.view.atomicElements.BaseView;
import it.unibs.view.atomicElements.RoundedButton;

public class ViewMenuConfiguratore extends BaseView {
	private final static String[] SCELTE_CONFIGURATORE = new String[]{
			"Aggiungi comprensorio",
			"Aggiungi gerarchia",
			"Salva Modifiche",
			"Visualizza comprensori",
			"Visualizza gerarchie",
			"Visualizza fattori di una categoria",
			"Visualizza scambi di una categoria",
			"Contatta utenti di uno scambio"};
	private JLabel lblMenuConfiguratore;
	private RoundedButton[] btnAccedi= new RoundedButton[SCELTE_CONFIGURATORE.length];
	private ActionListener[] btnAccediListener;
	
	public ViewMenuConfiguratore(JFrame frame) {
		super(frame,frame.getWidth()-200,650);
//		btnAccedi = new RoundedButton[SCELTE_CONFIGURATORE.length];	
		inizializzaComponenti();
		aggiornaComponenti(frame.getWidth(), frame.getHeight());
		frame.setMinimumSize(new Dimension(920, 600));
	}

	

	@Override
	protected void inizializzaComponenti() {
		lblMenuConfiguratore = new JLabel("Menu Configuratore");
		if (btnAccedi!=null) {
			for (int i=0;i<btnAccedi.length;i++) {
				btnAccedi[i] = new RoundedButton(SCELTE_CONFIGURATORE[i], new Color(8, 102, 255));
			}
		}
	}

	@Override
	protected void aggiornaComponenti(int w, int h) {
		contentPanel.removeAll();
        
        // Calcola le dimensioni del pannello interno
        int contentWidth = contentPanel.getWidth();
        int contentHeight = contentPanel.getHeight();
        
        lblMenuConfiguratore.setForeground(new Color(43, 43, 43));
        lblMenuConfiguratore.setFont(new Font("Tahoma", Font.PLAIN, 40));
        Dimension size = lblMenuConfiguratore.getPreferredSize();
        lblMenuConfiguratore.setBounds((contentWidth - size.width) / 2, 20, size.width, 70);
        contentPanel.add(lblMenuConfiguratore);
        
        if (btnAccedi!=null) {
        	for (int i=0;i<btnAccedi.length;i++) {
    			btnAccedi[i].setBorder(null);
    			btnAccedi[i].setMargin(new Insets(0, 10, 0, 0));
    	        btnAccedi[i].setFont(new Font("Tahoma", Font.BOLD, 20));
    	        int offset=20;
    	        int widthButton=contentWidth/2-50;
    	        int heightButton=contentHeight/6; //100
    	        if (i>3) {
    	        	btnAccedi[i].setBounds(contentWidth/2+offset, 120+(offset+heightButton)*(i-4), widthButton, heightButton);
    	        }
    	        else 
    	        	btnAccedi[i].setBounds(contentWidth/2-widthButton-offset, 120+(heightButton+offset)*i, widthButton, heightButton);
    	        
    	        btnAccedi[i].setForeground(Color.WHITE);
//    	        if (btnAccediListener[i] != null) {
//    	            btnAccedi[i].addActionListener(btnAccediListener[i]);
//    	        }
    	        contentPanel.add(btnAccedi[i]);
    		}
		}
        
	}
	
	public void setButtonListeners(ActionListener btnListener,int i) {
        btnAccedi[i].addActionListener(btnListener);
    }
	
}
