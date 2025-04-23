 package it.unibs.controller;

import javax.swing.JFrame;
import it.unibs.controllerGrasp.GerarchieHandler;
import it.unibs.model.Model;
import it.unibs.view.fruitore.ViewNavigaGerarchie;

public class GestoreGerarchieFruitore {
	private GerarchieHandler gerarchieHandler;
	private JFrame frame;
	ControllerFruitore controllerFruitore;
	public GestoreGerarchieFruitore(Model model, JFrame frame,ControllerFruitore controllerFruitore) {
		super();
		this.frame=frame;
		this.gerarchieHandler = new GerarchieHandler(model);
		this.controllerFruitore = controllerFruitore;
	}

	public void navigazioneGerarchie() {
		ViewNavigaGerarchie viewGerarchie = new ViewNavigaGerarchie(frame,gerarchieHandler.getGerarchie() );
		frame.getContentPane().add(viewGerarchie);
		viewGerarchie.setLayout(null);
		viewGerarchie.setBtnBackListeners(e-> backHome());
	}
	
	private void backHome() {
		controllerFruitore.run();
	}
}
