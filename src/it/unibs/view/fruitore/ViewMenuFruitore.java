package it.unibs.view.fruitore;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

import it.unibs.view.atomicElements.BaseView;
import it.unibs.view.atomicElements.RoundedButton;

public class ViewMenuFruitore extends BaseView{
		private final static String[] SCELTE_FRUITORE = new String[]{
				"Naviga Gerarchie",
				"Formula proposte di scambio di prestazioni",
				"Visualizza proposte",
				"Ritira proposta"};
		private JLabel lblMenuConfiguratore;
		private RoundedButton[] btnAccedi= new RoundedButton[SCELTE_FRUITORE.length];
		private ActionListener[] btnAccediListener;// ??????DIVETA LISTA???????
		
		public ViewMenuFruitore(JFrame frame) {
			super(frame,frame.getWidth()-200,650);
//			btnAccedi = new RoundedButton[SCELTE_CONFIGURATORE.length];	
			inizializzaComponenti();
			aggiornaComponenti(frame.getWidth(), frame.getHeight());
			frame.setMinimumSize(new Dimension(920, 600));
		}

		@Override
		protected void inizializzaComponenti() {
			lblMenuConfiguratore = new JLabel("Menu Fruitore");
			if (btnAccedi!=null) {
				for (int i=0;i<btnAccedi.length;i++) {
					btnAccedi[i] = new RoundedButton(SCELTE_FRUITORE[i], new Color(8, 102, 255));
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
	    	        int widthButton=contentWidth-200;
	    	        int heightButton=contentHeight/6; //100
	    	         
	    	        btnAccedi[i].setBounds(contentWidth/2-widthButton/2, 120+(heightButton+offset)*i, widthButton, heightButton);
	    	        
	    	        btnAccedi[i].setForeground(Color.WHITE);
//	    	        if (btnAccediListener[i] != null) {
//	    	            btnAccedi[i].addActionListener(btnAccediListener[i]);
//	    	        }
	    	        contentPanel.add(btnAccedi[i]);
	    		}
			}
	        
		}
		
	}

