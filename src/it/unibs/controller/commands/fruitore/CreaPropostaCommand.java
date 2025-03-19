package it.unibs.controller.commands.fruitore;

import it.unibs.controller.GestoreGerarchieFruitore;
import it.unibs.controller.GestoreScambi;
import it.unibs.controller.commands.CommandUtente;
import it.unibs.view.console.ViewFruitore;

public class CreaPropostaCommand implements CommandUtente{
	private GestoreGerarchieFruitore gestoreGerarchieFruitore;
	private GestoreScambi gestoreScambi;
	
	public CreaPropostaCommand(GestoreGerarchieFruitore gestoreGerarchieFruitore, 
								GestoreScambi gestoreScambi) {
		this.gestoreGerarchieFruitore = gestoreGerarchieFruitore;
		this.gestoreScambi = gestoreScambi;
	}
	
	@Override
	public void execute() {
		gestoreScambi.creaProposta(gestoreGerarchieFruitore);		
	}

	
	
}
