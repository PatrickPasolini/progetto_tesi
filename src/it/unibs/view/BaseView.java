package it.unibs.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class BaseView extends JPanel {
	protected JFrame frame;
	
	public BaseView(JFrame frame) {
		this.frame=frame;
		inizializzaBaseView();
	}
	
	private void inizializzaBaseView() {
		
	}

	protected abstract void aggiornaComponenti();
}
