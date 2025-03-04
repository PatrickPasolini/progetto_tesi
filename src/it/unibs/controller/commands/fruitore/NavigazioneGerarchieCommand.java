package it.unibs.controller.commands.fruitore;

import it.unibs.controller.GestoreGerarchieFruitore;
import it.unibs.controller.commands.CommandUtente;

public class NavigazioneGerarchieCommand implements CommandUtente {
	private GestoreGerarchieFruitore gestoreGerarchieFruitore;

	public NavigazioneGerarchieCommand(GestoreGerarchieFruitore gestoreGerarchieFruitore) {
		this.gestoreGerarchieFruitore = gestoreGerarchieFruitore;
	}

	@Override
	public void execute() {
		gestoreGerarchieFruitore.navigazioneGerarchie();		
	}
}
