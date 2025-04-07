package it.unibs.view.configuratore;

import java.util.List;

import javax.swing.JFrame;

import it.unibs.domain.Gerarchia;
import it.unibs.view.atomicElements.BaseView;

public class ViewScambiCategoria extends BaseView {

	public ViewScambiCategoria(JFrame frame, List<Gerarchia> gerarchie) {
		super(frame,frame.getWidth()-200,frame.getHeight()-200);
		ViewSceltaFoglia viewSceltaFoglia = new ViewSceltaFoglia(frame, gerarchie);
		frame.getContentPane().add(viewSceltaFoglia);
		viewSceltaFoglia.setLayout(null);
	}

	@Override
	protected void inizializzaComponenti() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void aggiornaComponenti(int w, int h) {
		// TODO Auto-generated method stub
		
	}

}
