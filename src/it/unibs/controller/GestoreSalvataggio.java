package it.unibs.controller;

import javax.swing.JFrame;

import it.unibs.controllerGrasp.SalvaModificheHandler;
import it.unibs.model.Model;
import it.unibs.view.configuratore.ViewSalva;

public class GestoreSalvataggio {
	private JFrame frame;
	private SalvaModificheHandler salvaHandler;
	ControllerConfiguratore controllerConfiguratore;
	
	public GestoreSalvataggio(Model model,JFrame frame,ControllerConfiguratore controllerConfiguratore) {
		super();
		this.frame=frame;
		this.salvaHandler = new SalvaModificheHandler(model);
		this.controllerConfiguratore = controllerConfiguratore;
	}
	
	public void visualizzaSalvataggio() {
		ViewSalva viewSalva = new ViewSalva(frame);
		frame.getContentPane().add(viewSalva);
		viewSalva.setLayout(null);
		viewSalva.setBtnBackListeners(e -> backHomeConfiguratore());
		
		salvaHandler.salvaModifiche();
	}
	
	private void backHomeConfiguratore() { 
		controllerConfiguratore.run();
	}
}
