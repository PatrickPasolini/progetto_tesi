package it.unibs.controller.commands.fruitore;

import it.unibs.controller.GestoreGerarchieFruitore;
import it.unibs.controller.GestoreScambi;
import it.unibs.controller.commands.CommandUtente;
import it.unibs.view.console.ViewFruitore;

public class CreaPropostaCommand implements CommandUtente{
	private GestoreGerarchieFruitore gestoreGerarchieFruitore;
	private GestoreScambi gestoreScambi;
	private ViewFruitore view;
	
	public CreaPropostaCommand(GestoreGerarchieFruitore gestoreGerarchieFruitore, 
								GestoreScambi gestoreScambi,
								ViewFruitore view) {
		this.gestoreGerarchieFruitore = gestoreGerarchieFruitore;
		this.gestoreScambi = gestoreScambi;
		this.view = view;
	}
	
	@Override
	public void execute() {
		gestoreScambi.creaProposta(view, gestoreGerarchieFruitore);		
	}

	
	
}
