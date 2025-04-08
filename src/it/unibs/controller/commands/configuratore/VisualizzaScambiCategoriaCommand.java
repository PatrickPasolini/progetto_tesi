package it.unibs.controller.commands.configuratore;

import it.unibs.controller.GestoreGerarchieConfiguratore;
import it.unibs.controller.GestoreScambi;
import it.unibs.controller.commands.CommandUtente;

public class VisualizzaScambiCategoriaCommand implements CommandUtente {
	private GestoreScambi gestoreScambi;
	private GestoreGerarchieConfiguratore gestoreGerarchieConfiguratore;

	public VisualizzaScambiCategoriaCommand(GestoreScambi gestoreScambi,
											GestoreGerarchieConfiguratore gestoreGerarchieConfiguratore) {
		this.gestoreScambi = gestoreScambi;
		this.gestoreGerarchieConfiguratore = gestoreGerarchieConfiguratore;
	}
	
	@Override
	public void execute() {
//		gestoreScambi.visualizzaProposteFoglia(gestoreGerarchieConfiguratore.sceltaFogliaScambi());
		gestoreScambi.visualizzaProposteFoglia();
	}
	
}
