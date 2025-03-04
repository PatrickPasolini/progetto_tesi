package it.unibs.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import it.unibs.view.atomicElements.Button;
import it.unibs.view.atomicElements.RoundedPanel;

public class StartView extends JPanel {
    private RoundedPanel contentPanel;
    private Button btnConfiguratore;
    private Button btnFruitore;
    private JFrame frame;
    
    public StartView(JFrame frame) {
    	this.frame=frame;
    	inizializzaStartView();
    }
    
    private void inizializzaStartView() {
        int w = frame.getWidth();
        int h = frame.getHeight();
        this.setBounds(0, 0, w, h);
        this.setBackground(new Color(207, 207, 207));
        setLayout(null);

        int contentWidth = Math.min(600, w - 100);
        int contentHeight = Math.min(650, h - 100);
        int x = (w - contentWidth) / 2;
        int y = (h - contentHeight) / 2;
        
        contentPanel = new RoundedPanel(20, false);
        contentPanel.setLayout(null);
        contentPanel.setBackground(new Color(230, 230, 230));
        contentPanel.setBounds(x, y, contentWidth, contentHeight);

        add(contentPanel);

        aggiornaComponenti(w, h);
    }

    private void aggiornaComponenti(int w, int h) {
        contentPanel.removeAll();
        
        int contentWidth = contentPanel.getWidth();

        JLabel lblAccesso = new JLabel("Accedi come:");
        lblAccesso.setForeground(new Color(43, 43, 43));
        lblAccesso.setFont(new Font("Tahoma", Font.PLAIN, 40));
        Dimension size = lblAccesso.getPreferredSize();
        lblAccesso.setBounds((contentWidth - size.width) / 2, 50, size.width, 70);
        contentPanel.add(lblAccesso);

        btnConfiguratore = new Button("Configuratore", new Color(8, 102, 255));
        btnConfiguratore.setBorder(null);
        btnConfiguratore.setMargin(new Insets(0, 10, 0, 0));
        btnConfiguratore.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnConfiguratore.setBounds(contentWidth / 2 - 170, 150, 340, 100);
        btnConfiguratore.setForeground(Color.WHITE);
        contentPanel.add(btnConfiguratore);

        btnFruitore = new Button("Fruitore", new Color(8, 102, 255));
        btnFruitore.setBorder(null);
        btnFruitore.setMargin(new Insets(0, 10, 0, 0));
        btnFruitore.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnFruitore.setBounds(contentWidth / 2 - 170, 300, 340, 100);
        btnFruitore.setForeground(Color.WHITE);
        contentPanel.add(btnFruitore);
    }

    // Metodo per collegare i bottoni al controller
    public void setButtonListeners(ActionListener configuratoreListener, ActionListener fruitoreListener) {
        btnConfiguratore.addActionListener(configuratoreListener);
        btnFruitore.addActionListener(fruitoreListener);
    }
}
