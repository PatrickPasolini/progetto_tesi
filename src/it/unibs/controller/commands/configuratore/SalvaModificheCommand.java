package it.unibs.controller.commands.configuratore;

import it.unibs.controller.commands.CommandUtente;
import it.unibs.controllerGrasp.SalvaModificheHandler;
import it.unibs.model.Model;

public class SalvaModificheCommand implements CommandUtente {
	private SalvaModificheHandler salvaHandler;

	public SalvaModificheCommand(Model model) {
		this.salvaHandler = new SalvaModificheHandler(model);
	}

	@Override
	public void execute() {
		salvaHandler.salvaModifiche();		
	}
}
