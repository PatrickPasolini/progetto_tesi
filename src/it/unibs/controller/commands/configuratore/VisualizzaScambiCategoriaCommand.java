package it.unibs.controller.commands.configuratore;

import it.unibs.controller.GestoreScambi;
import it.unibs.controller.commands.CommandUtente;

public class VisualizzaScambiCategoriaCommand implements CommandUtente {
	private GestoreScambi gestoreScambi;

	public VisualizzaScambiCategoriaCommand(GestoreScambi gestoreScambi) {
		this.gestoreScambi = gestoreScambi;
	}
	
	@Override
	public void execute() {
		gestoreScambi.visualizzaProposteFoglia();
	}
	
}
