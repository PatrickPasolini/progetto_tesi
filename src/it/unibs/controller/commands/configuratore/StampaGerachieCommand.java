package it.unibs.controller.commands.configuratore;

import it.unibs.controller.GestoreGerarchieConfiguratore;
import it.unibs.controller.commands.CommandUtente;

public class StampaGerachieCommand implements CommandUtente {
	private GestoreGerarchieConfiguratore gestoreGerarchieConfiguratore;

	public StampaGerachieCommand(GestoreGerarchieConfiguratore gestoreGerarchieConfiguratore) {
		this.gestoreGerarchieConfiguratore = gestoreGerarchieConfiguratore;
	}

	@Override
	public void execute() {
		gestoreGerarchieConfiguratore.stampaGerarchie();		
	}
}
