package it.unibs.controller.commands.configuratore;

import it.unibs.controller.GestoreScambi;
import it.unibs.controller.commands.CommandUtente;
import it.unibs.view.console.ViewConfiguratore;

public class VisualizzaScambiCompleti implements CommandUtente {
	private ViewConfiguratore view;
	private GestoreScambi gestoreScambi;

//	public VisualizzaScambiCompleti(ViewConfiguratore view, GestoreScambi gestoreScambi) {
//		this.view = view;
//		this.gestoreScambi = gestoreScambi;
//	}
	
	public VisualizzaScambiCompleti( GestoreScambi gestoreScambi) {
		this.gestoreScambi = gestoreScambi;
	}
	
	@Override
	public void execute() {
		gestoreScambi.visualizzaScambiCompleti(view);
	}

}
