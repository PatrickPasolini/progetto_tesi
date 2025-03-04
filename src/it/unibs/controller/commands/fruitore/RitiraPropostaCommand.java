package it.unibs.controller.commands.fruitore;

import it.unibs.controller.GestoreScambi;
import it.unibs.controller.commands.CommandUtente;
import it.unibs.view.console.ViewFruitore;

public class RitiraPropostaCommand implements CommandUtente{
	private GestoreScambi gestoreScambi;
	private ViewFruitore view;
	
	public RitiraPropostaCommand(GestoreScambi gestoreScambi, ViewFruitore view) {
		this.gestoreScambi = gestoreScambi;
		this.view = view;
	}
	
	@Override
	public void execute() {
		gestoreScambi.ritiraProposta(view);
	}

}
