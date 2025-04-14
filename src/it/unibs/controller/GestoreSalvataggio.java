package it.unibs.controller;

import javax.swing.JFrame;

import it.unibs.controllerGrasp.SalvaModificheHandler;
import it.unibs.model.Model;
import it.unibs.view.configuratore.ViewSalva;

public class GestoreSalvataggio {
	private Model model;
	private JFrame frame;
	private SalvaModificheHandler salvaHandler;
	
	public GestoreSalvataggio(Model model,JFrame frame) {
		super();
		this.model=model; 
		this.frame=frame;
		this.salvaHandler = new SalvaModificheHandler(model);
	}
	
	public void visualizzaSalvataggio() {
		ViewSalva viewSalva = new ViewSalva(frame);
		frame.getContentPane().add(viewSalva);
		viewSalva.setLayout(null);
		viewSalva.setBtnHomeListener(e -> backHomeConfiguratore());
		
		salvaHandler.salvaModifiche();
	}
	
	private void backHomeConfiguratore() {
		ControllerConfiguratore controllerConfiguratore = new ControllerConfiguratore(model, frame);
		controllerConfiguratore.run();
	}
}
