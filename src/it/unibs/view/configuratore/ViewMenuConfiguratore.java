package it.unibs.view.configuratore;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import it.unibs.view.atomicElements.BaseView;
import it.unibs.view.atomicElements.RoundedButton;

public class ViewMenuConfiguratore extends BaseView {
	private static final long serialVersionUID = 1L;
	private final static String[] SCELTE_CONFIGURATORE = new String[]{
			"Aggiungi comprensorio",
			"Aggiungi gerarchia",
			"Visualizza fattori di una prestazione",
			"Salva Modifiche",
			"Visualizza comprensori",
			"Visualizza gerarchie",
			"Visualizza scambi di una prestazione",
			"Contatta utenti di uno scambio"};
	private JLabel lblMenuConfiguratore;
	private RoundedButton[] btnMenu= new RoundedButton[SCELTE_CONFIGURATORE.length];
	
	public ViewMenuConfiguratore(JFrame frame) {
		super(frame,frame.getWidth()-200,frame.getHeight()-200);
		inizializzaComponenti();
		aggiornaComponenti(frame.getWidth(), frame.getHeight());
		frame.setMinimumSize(new Dimension(920, 600));
	}

	@Override
	protected void inizializzaComponenti() {
		lblMenuConfiguratore = new JLabel("Menù Configuratore");
		if (btnMenu!=null) {
			for (int i=0;i<btnMenu.length;i++) {
				btnMenu[i] = new RoundedButton(SCELTE_CONFIGURATORE[i], new Color(8, 102, 255));
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
        lblMenuConfiguratore.setFont(new Font("Tahoma", Font.BOLD, 55));
        Dimension size = lblMenuConfiguratore.getPreferredSize();
        lblMenuConfiguratore.setBounds((contentWidth - size.width) / 2, 20, size.width, 70);
        contentPanel.add(lblMenuConfiguratore);
        
        if (btnMenu!=null) {
        	for (int i=0;i<btnMenu.length;i++) {
    			btnMenu[i].setBorder(null);
    			btnMenu[i].setMargin(new Insets(0, 10, 0, 0));
    	        btnMenu[i].setFont(new Font("Tahoma", Font.BOLD, 35));
    	        int offset=20;
    	        int widthButton=contentWidth/2-50;
    	        int heightButton=contentHeight/6; //100
    	        if (i>3) {
    	        	btnMenu[i].setBounds(contentWidth/2+offset, 120+(offset+heightButton)*(i-4), widthButton, heightButton);
    	        }
    	        else 
    	        	btnMenu[i].setBounds(contentWidth/2-widthButton-offset, 120+(heightButton+offset)*i, widthButton, heightButton);
    	        
    	        btnMenu[i].setForeground(Color.WHITE);
    	        contentPanel.add(btnMenu[i]);
    		}
		}
	}
	
	public void setButtonListeners(ActionListener btnListener,int i) {
        btnMenu[i].addActionListener(btnListener);
    }
}