package it.unibs.controller.commands.configuratore;

import it.unibs.controller.GestoreGerarchieConfiguratore;
import it.unibs.controller.GestoreScambi;
import it.unibs.controller.commands.CommandUtente;
import it.unibs.view.console.ViewConfiguratore;

public class VisualizzaScambiCategoriaCommand implements CommandUtente {
	private ViewConfiguratore view;
	private GestoreScambi gestoreScambi;
	private GestoreGerarchieConfiguratore gestoreGerarchieConfiguratore;

	public VisualizzaScambiCategoriaCommand(ViewConfiguratore view,
											GestoreScambi gestoreScambi,
											GestoreGerarchieConfiguratore gestoreGerarchieConfiguratore) {
		this.view = view;
		this.gestoreScambi = gestoreScambi;
		this.gestoreGerarchieConfiguratore = gestoreGerarchieConfiguratore;
	}

	@Override
	public void execute() {
		gestoreScambi.visualizzaProposteFoglia(view, gestoreGerarchieConfiguratore.sceltaFogliaScambi());
	}
	
}
