package it.unibs.controller.commands.fruitore;

import it.unibs.controller.GestoreScambi;
import it.unibs.controller.commands.CommandUtente;
import it.unibs.view.console.ViewFruitore;

public class VisualizzaProposteUtenteCommand implements CommandUtente{
	private GestoreScambi gestoreScambi;
	private ViewFruitore view;
	
	public VisualizzaProposteUtenteCommand(GestoreScambi gestoreScambi, ViewFruitore view) {
		this.gestoreScambi = gestoreScambi;
		this.view = view;
	}
	
	@Override
	public void execute() {
		gestoreScambi.visualizzaProposteUtente(view);	
	}

}
