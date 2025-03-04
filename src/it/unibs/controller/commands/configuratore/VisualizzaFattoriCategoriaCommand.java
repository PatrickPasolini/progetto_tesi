package it.unibs.controller.commands.configuratore;

import it.unibs.controller.GestoreGerarchieConfiguratore;
import it.unibs.controller.commands.CommandUtente;

public class VisualizzaFattoriCategoriaCommand implements CommandUtente {
	private GestoreGerarchieConfiguratore gestoreGerarchieConfiguratore;

	public VisualizzaFattoriCategoriaCommand(GestoreGerarchieConfiguratore gestoreGerarchieConfiguratore) {
		this.gestoreGerarchieConfiguratore = gestoreGerarchieConfiguratore;
	}

	@Override
	public void execute() {
		gestoreGerarchieConfiguratore.sceltaFogliaFattori();	
	}

}
