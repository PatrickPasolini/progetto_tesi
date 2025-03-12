package it.unibs.controller;

import java.util.HashMap;
import java.util.Map;

import it.unibs.controller.commands.CommandUtente;
import it.unibs.controller.commands.fruitore.CreaPropostaCommand;
import it.unibs.controller.commands.fruitore.NavigazioneGerarchieCommand;
import it.unibs.controller.commands.fruitore.RitiraPropostaCommand;
import it.unibs.controller.commands.fruitore.VisualizzaProposteUtenteCommand;
import it.unibs.model.Model;
import it.unibs.mylib.MyMenu;
import it.unibs.view.console.ViewFruitore;
import it.unibs.view.fruitore.ViewMenuFruitore;
/**
 * Il ControllerFruitore gestisce le operazioni di comunicazione tra il model e la view
 * Consente di selezionare una Foglia tramite l'impostazione progressiva dei valori dei campi
 */
public class ControllerFruitore implements Controller{
	private Model model;
	private ViewMenuFruitore view;
	private GestoreGerarchieFruitore gestoreGerarchieFruitore;
	private GestoreScambi gestoreScambi;
	private Map<Integer, CommandUtente> commandMenu = new HashMap<>();
	
	public ControllerFruitore(Model model, ViewMenuFruitore viewFruitore) {
		this.model = model;
		this.view = viewFruitore;
//		this.gestoreGerarchieFruitore = new GestoreGerarchieFruitore(model, view);
		this.gestoreScambi = new GestoreScambi(model);
		
		inizializzaCommandsMenu();
	}
	
	private void inizializzaCommandsMenu() {
		commandMenu.put(1, new NavigazioneGerarchieCommand(gestoreGerarchieFruitore));
//		commandMenu.put(2, new CreaPropostaCommand(gestoreGerarchieFruitore, gestoreScambi, view));
//		commandMenu.put(3, new VisualizzaProposteUtenteCommand(gestoreScambi, view));
//		commandMenu.put(4, new RitiraPropostaCommand(gestoreScambi, view));
	}
	
	public void run() {
//		MyMenu menuRun = view.getMenuRun();
//		
//		do {
//			view.stampaMenuRun();
//		} while(sceltaMenuFruitore(menuRun));
	}

	public boolean sceltaMenuFruitore(MyMenu menu) {	
		int scelta = menu.scegli();
		CommandUtente command = commandMenu.get(scelta);
		
		if (command != null) {
	        command.execute();
	        return true;
	    }
	    return false;
	}
	
}
