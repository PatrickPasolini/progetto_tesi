package it.unibs.controller.commands.fruitore;

import it.unibs.controller.GestoreScambi;
import it.unibs.controller.commands.CommandUtente;

public class VisualizzaProposteUtenteCommand implements CommandUtente{
	private GestoreScambi gestoreScambi;
	
	public VisualizzaProposteUtenteCommand(GestoreScambi gestoreScambi) {
		this.gestoreScambi = gestoreScambi;
	}
	
	@Override
	public void execute() {
		gestoreScambi.visualizzaProposteUtente();	
	}

}
