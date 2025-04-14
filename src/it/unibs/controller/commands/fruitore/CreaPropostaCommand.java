package it.unibs.controller.commands.fruitore;

import it.unibs.controller.GestoreScambi;
import it.unibs.controller.commands.CommandUtente;

public class CreaPropostaCommand implements CommandUtente{
	private GestoreScambi gestoreScambi;
	
	public CreaPropostaCommand(GestoreScambi gestoreScambi) {
		this.gestoreScambi = gestoreScambi;
	}
	
	@Override
	public void execute() {
		gestoreScambi.creaProposta();		
	}
}
