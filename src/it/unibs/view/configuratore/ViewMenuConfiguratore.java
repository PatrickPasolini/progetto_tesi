package it.unibs.view.configuratore;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JFrame;

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
	private RoundedButton[] btnAccedi;
	private ActionListener[] btnAccediListener;// ??????DIVETA LISTA???????
	
	public ViewMenuConfiguratore(JFrame frame) {
		super(frame);
		btnAccedi = new RoundedButton[SCELTE_CONFIGURATORE.length];
//		inizializzaComponenti();
//		aggiornaComponenti(frame.getWidth(), frame.getHeight());
	}

	

	@Override
	protected void inizializzaComponenti() {
		for (int i=0;i<btnAccedi.length;i++) {
			btnAccedi[i] = new RoundedButton(SCELTE_CONFIGURATORE[i], Color.CYAN);
		}
	}

	@Override
	protected void aggiornaComponenti(int w, int h) {
		contentPanel.removeAll();
        
        // Calcola le dimensioni del pannello interno
        int contentWidth = contentPanel.getWidth();
        int contentHeight = contentPanel.getHeight();
        
        for (int i=0;i<btnAccedi.length;i++) {
			btnAccedi[i].setBorder(null);
			btnAccedi[i].setMargin(new Insets(0, 10, 0, 0));
	        btnAccedi[i].setFont(new Font("Tahoma", Font.BOLD, 20));
	        btnAccedi[i].setBounds(contentWidth / 2 - 200 -20, 200*i, 200, 60);
	        btnAccedi[i].setForeground(Color.WHITE);
	        if (btnAccediListener[i] != null) {
	            btnAccedi[i].addActionListener(btnAccediListener[i]);
	        }
	        contentPanel.add(btnAccedi[i]);
		}
        
	}
	
}
