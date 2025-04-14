package it.unibs.controller.commands.configuratore;

import it.unibs.controller.GestoreScambi;
import it.unibs.controller.commands.CommandUtente;

public class VisualizzaScambiCompletiCommand implements CommandUtente {
	private GestoreScambi gestoreScambi;
	
	public VisualizzaScambiCompletiCommand( GestoreScambi gestoreScambi) {
		this.gestoreScambi = gestoreScambi;
	}
	
	@Override
	public void execute() {
		gestoreScambi.visualizzaScambiCompleti();
	}

}
