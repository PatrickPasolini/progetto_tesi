package it.unibs.controllerGrasp;

import it.unibs.model.Model;

public class SalvaModificheHandler {
	private Model model;
	
	public SalvaModificheHandler(Model model) {
		this.model = model;
	}
	
	public void salvaModifiche() {
		model.salvaModifiche();
	}
}
