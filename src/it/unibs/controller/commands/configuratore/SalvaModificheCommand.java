package it.unibs.controller.commands.configuratore;

import it.unibs.controller.GestoreSalvataggio;
import it.unibs.controller.commands.CommandUtente;

public class SalvaModificheCommand implements CommandUtente {
	private GestoreSalvataggio gestoreSalvataggio;

	public SalvaModificheCommand(GestoreSalvataggio gestoreSalvataggio) {
		this.gestoreSalvataggio = gestoreSalvataggio;
	}

	@Override
	public void execute() {
		gestoreSalvataggio.visualizzaSalvataggio();		
	}
}
