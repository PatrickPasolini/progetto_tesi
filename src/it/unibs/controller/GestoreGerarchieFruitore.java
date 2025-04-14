 package it.unibs.controller;

import javax.swing.JFrame;
import it.unibs.controllerGrasp.GerarchieHandler;
import it.unibs.model.Model;
import it.unibs.view.fruitore.ViewNavigaGerarchie;

public class GestoreGerarchieFruitore {
	private GerarchieHandler gerarchieHandler;
	private JFrame frame;
	private Model model;
	public GestoreGerarchieFruitore(Model model, JFrame frame) {
		super();
		this.frame=frame;
		this.model=model;
		this.gerarchieHandler = new GerarchieHandler(model);
	}

	public void navigazioneGerarchie() {
		ViewNavigaGerarchie viewGerarchie = new ViewNavigaGerarchie(frame,gerarchieHandler.getGerarchie() );
		frame.getContentPane().add(viewGerarchie);
		viewGerarchie.setLayout(null);
		viewGerarchie.setBtnHomeListener(e-> backHome());
		viewGerarchie.setLeafDoubleClickListener(e -> System.out.println(viewGerarchie.getCategoriaSelezionata().getNome()));
	}
	
	private void backHome() {
		ControllerFruitore controllerConfiguratore = new ControllerFruitore(model, frame);
		controllerConfiguratore.run();
	}
}
