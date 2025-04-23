package it.unibs.view.fruitore;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

import it.unibs.view.atomicElements.BaseView;
import it.unibs.view.atomicElements.CircleHoverIconButton;
import it.unibs.view.atomicElements.RoundedButton;

public class ViewMenuFruitore extends BaseView{
	private static final long serialVersionUID = 1L;
		private final static String[] SCELTE_FRUITORE = new String[]{
				"Naviga Gerarchie",
				"Nuova proposta di scambio",
				"Le mie proposte",
				"Ritira proposta"};
		private JLabel lblMenuFruitore;
		private RoundedButton[] btnMenu= new RoundedButton[SCELTE_FRUITORE.length];
		
		public ViewMenuFruitore(JFrame frame) {
			super(frame,frame.getWidth()-400,frame.getHeight()-200);
			inizializzaComponenti();
			aggiornaComponenti(frame.getWidth(), frame.getHeight());
			frame.setMinimumSize(new Dimension(920, 600));
		}

		@Override
		protected void inizializzaComponenti() {
			lblMenuFruitore = new JLabel("Menù Fruitore");
			if (btnMenu!=null) {
				for (int i=0;i<btnMenu.length;i++) {
					btnMenu[i] = new RoundedButton(SCELTE_FRUITORE[i], new Color(8, 102, 255));
				}
			}
		}

		@Override
		protected void aggiornaComponenti(int w, int h) {
			contentPanel.removeAll();
	        
	        // Calcola le dimensioni del pannello interno
	        int contentWidth = contentPanel.getWidth();
	        int contentHeight = contentPanel.getHeight();
	        
	        lblMenuFruitore.setForeground(new Color(43, 43, 43));
	        lblMenuFruitore.setFont(new Font("Tahoma", Font.BOLD, 55));
	        Dimension size = lblMenuFruitore.getPreferredSize();
	        lblMenuFruitore.setBounds((contentWidth - size.width) / 2, 50, size.width, 70);
	        contentPanel.add(lblMenuFruitore);
	        
	        if (btnMenu!=null) {
	        	for (int i=0;i<btnMenu.length;i++) {
	    			btnMenu[i].setBorder(null);
	    			btnMenu[i].setMargin(new Insets(0, 10, 0, 0));
	    	        btnMenu[i].setFont(new Font("Tahoma", Font.BOLD, 35));
	    	        int offset=20;
	    	        int widthButton=contentWidth-200;
	    	        int heightButton=contentHeight/6; //100
	    	         
	    	        btnMenu[i].setBounds(contentWidth/2-widthButton/2, 180+(heightButton+offset)*i, widthButton, heightButton);
	    	        btnMenu[i].setForeground(Color.WHITE);
	    	        contentPanel.add(btnMenu[i]);
	    		}
			}
	        
	        btnBack.setBounds(45, 45, 90, 90);
	        contentPanel.add(btnBack);
	}
	
	public void setButtonListeners(ActionListener btnListener,int i) {
        btnMenu[i].addActionListener(btnListener);
    }
}

