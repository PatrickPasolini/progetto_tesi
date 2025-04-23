package it.unibs.view.atomicElements;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class BaseView extends JPanel {
	public JFrame getFrame() {
		return frame;
	}
	private static final long serialVersionUID = 1L;
	protected JFrame frame;
	protected RoundedPanel contentPanel;
    private int maxContentWidth;
    private int maxContentHeight;
    protected static final String ARROWLEFT_PATH = "./Img/arrowLeft.png";
	protected static final String HOME_PATH = "./Img/home.png";
	public CircleHoverIconButton btnBack;
    
	public BaseView(JFrame frame, int maxContentWidth, int maxContentHeight) {
		this.frame=frame;
		this.maxContentWidth=maxContentWidth;
		this.maxContentHeight=maxContentHeight; 
		btnBack = new CircleHoverIconButton(ARROWLEFT_PATH, 50);
		inizializzaComponenti();
		inizializzaBaseView();
	}
	
	private void inizializzaBaseView() {
			frame.getContentPane().removeAll();
			frame.getContentPane().revalidate();
			frame.getContentPane().repaint();
			frame.setResizable(false);
			
			int w = frame.getWidth();
	        int h = frame.getHeight();
	        this.setBounds(0, 0, w, h);
	        this.setBackground(new Color(207, 207, 207));
	        setLayout(null);

	        int contentWidth = Math.min(maxContentWidth, w - 100);
	        int contentHeight = Math.min(maxContentHeight, h - 100);
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
	                int contentWidth = Math.min(maxContentWidth, w - 100);
	                int contentHeight = Math.min(maxContentHeight, h - 100);
	                int x = (w - contentWidth) / 2;
	                int y = (h - contentHeight) / 2;
	                contentPanel.setBounds(x, y, contentWidth, contentHeight);
	                
//	                aggiornaComponenti(w, h); 
	            }
	        });
	}
	protected abstract void inizializzaComponenti();
	protected abstract void aggiornaComponenti(int w,int h);
	
	public void setBtnBackListeners(ActionListener btnListener) {
		for (ActionListener al : btnBack.getActionListeners()) {
			btnBack.removeActionListener(al);
		}
		btnBack.addActionListener(btnListener);
    }
	
}
