package it.unibs.view;

import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import it.unibs.view.atomicElements.RoundedPanel;

public abstract class BaseView extends JPanel {
	protected JFrame frame;
	protected RoundedPanel contentPanel;
    
	public BaseView(JFrame frame) {
		this.frame=frame;
		inizializzaComponenti();
		inizializzaBaseView();
	}
	
	private void inizializzaBaseView() {
			frame.getContentPane().removeAll();
			frame.getContentPane().revalidate();
			frame.getContentPane().repaint();
			
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
	        
	        frame.addComponentListener(new ComponentAdapter() {
	            @Override
	            public void componentResized(ComponentEvent e) {
	                int w = frame.getWidth();
	                int h = frame.getHeight();
	                setBounds(0, 0, w, h);
	                
	                // Aggiorna la posizione e dimensione del pannello interno
	                int contentWidth = Math.min(500, w - 100);
	                int contentHeight = Math.min(550, h - 100);
	                int x = (w - contentWidth) / 2;
	                int y = (h - contentHeight) / 2;
	                contentPanel.setBounds(x, y, contentWidth, contentHeight);
	                
	                aggiornaComponenti(w, h);
	            }
	        });
	}
	protected abstract void inizializzaComponenti();
	protected abstract void aggiornaComponenti(int w,int h);
}
