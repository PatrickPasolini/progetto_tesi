package it.unibs.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import it.unibs.view.atomicElements.Button;
import it.unibs.view.atomicElements.RoundedPanel;

public class ViewStart extends BaseView {
    private Button btnConfiguratore;
    private Button btnFruitore;
    private JLabel lblAccesso;
    
    public ViewStart(JFrame frame) {
    	super(frame);
    }
    protected void inizializzaComponenti() {
    	lblAccesso = new JLabel("Accedi come:");
    	btnConfiguratore = new Button("Configuratore", new Color(8, 102, 255));
    	btnFruitore = new Button("Fruitore", new Color(8, 102, 255));
    }
    
    @Override
    protected void aggiornaComponenti(int w, int h) {
        contentPanel.removeAll();
        int contentWidth = contentPanel.getWidth();
        
        lblAccesso.setForeground(new Color(43, 43, 43));
        lblAccesso.setFont(new Font("Tahoma", Font.PLAIN, 40));
        Dimension size = lblAccesso.getPreferredSize();
        lblAccesso.setBounds((contentWidth - size.width) / 2, 50, size.width, 70);
        contentPanel.add(lblAccesso);

        btnConfiguratore.setBorder(null);
        btnConfiguratore.setMargin(new Insets(0, 10, 0, 0));
        btnConfiguratore.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnConfiguratore.setForeground(Color.WHITE);
        btnConfiguratore.setBounds(contentWidth / 2 - 170, 150, 340, 100);
        contentPanel.add(btnConfiguratore);
     
        btnFruitore.setBorder(null);
        btnFruitore.setMargin(new Insets(0, 10, 0, 0));
        btnFruitore.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnFruitore.setForeground(Color.WHITE);
        btnFruitore.setBounds(contentWidth / 2 - 170, 300, 340, 100);
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
