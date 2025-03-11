package it.unibs.view.accesso;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import it.unibs.view.atomicElements.RoundedButton;

public class ViewStart extends BaseView {
	private static final long serialVersionUID = 1L;
	private RoundedButton btnConfiguratore;
    private RoundedButton btnFruitore;
    private JLabel lblAccesso;
    
    public ViewStart(JFrame frame) {
    	super(frame);
    }
    protected void inizializzaComponenti() {
    	lblAccesso = new JLabel("Accedi come:");
    	btnConfiguratore = new RoundedButton("Configuratore", new Color(8, 102, 255));
    	btnFruitore = new RoundedButton("Fruitore", new Color(8, 102, 255));
    }
    
    @Override
    protected void aggiornaComponenti(int w, int h) {
        contentPanel.removeAll();
        int contentWidth = contentPanel.getWidth();
        int contentHeight = contentPanel.getHeight();
        
        lblAccesso.setForeground(new Color(43, 43, 43));
        lblAccesso.setFont(new Font("Tahoma", Font.PLAIN, 40));
        Dimension size = lblAccesso.getPreferredSize();
        lblAccesso.setBounds((contentWidth - size.width) / 2, 50, size.width, 70);
        contentPanel.add(lblAccesso);

        btnConfiguratore.setBorder(null);
        btnConfiguratore.setMargin(new Insets(0, 10, 0, 0));
        btnConfiguratore.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnConfiguratore.setForeground(Color.WHITE);
        btnConfiguratore.setBounds(contentWidth / 2 - 170, contentHeight/2-100 - 50, 340, 150);
        contentPanel.add(btnConfiguratore);
     
        btnFruitore.setBorder(null);
        btnFruitore.setMargin(new Insets(0, 10, 0, 0));
        btnFruitore.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnFruitore.setForeground(Color.WHITE);
        btnFruitore.setBounds(contentWidth / 2 - 170, contentHeight/2 + 50 , 340, 150);
        contentPanel.add(btnFruitore);

        contentPanel.revalidate();
        contentPanel.repaint();
    }


    // Metodo per collegare i bottoni al controller
    public void setButtonListeners(ActionListener configuratoreListener, ActionListener fruitoreListener) {
        btnConfiguratore.addActionListener(configuratoreListener);
        btnFruitore.addActionListener(fruitoreListener);
    }

}
