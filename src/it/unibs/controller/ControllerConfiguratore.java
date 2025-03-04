package it.unibs.controller;

import java.util.HashMap;
import java.util.Map;

import it.unibs.controller.commands.CommandUtente;
import it.unibs.controller.commands.configuratore.AggiungiGerarchiaCommand;
import it.unibs.controller.commands.configuratore.NuovoComprensorioCommand;
import it.unibs.controller.commands.configuratore.SalvaModificheCommand;
import it.unibs.controller.commands.configuratore.StampaComprensoriCommand;
import it.unibs.controller.commands.configuratore.StampaGerachieCommand;
import it.unibs.controller.commands.configuratore.VisualizzaFattoriCategoriaCommand;
import it.unibs.controller.commands.configuratore.VisualizzaScambiCategoriaCommand;
import it.unibs.controller.commands.configuratore.VisualizzaScambiCompleti;
import it.unibs.model.Model;
import it.unibs.mylib.*;
import it.unibs.view.console.ViewConfiguratore;

/**
 * Il ControllerConfiguratore gestisce le operazioni di comunicazione tra il model e la view
 * Consente di aggiungere comprensori e gerarchie, salvare dati e visualizzare informazioni.
 */
public class ControllerConfiguratore implements Controller {
	private Model model;
	private ViewConfiguratore view;
	private GestoreGerarchieConfiguratore gestoreGerarchieConfiguratore;
	private GestoreComprensoriConfiguratore gestoreComprensori;
	private GestoreScambi gestoreScambi;
	private Map<Integer, CommandUtente> commandMenu = new HashMap<>();
	
	public ControllerConfiguratore(Model model, ViewConfiguratore viewConfiguratore) {
		this.model = model;
		this.view = viewConfiguratore;
		this.gestoreGerarchieConfiguratore = new GestoreGerarchieConfiguratore(model, view);
		this.gestoreComprensori = new GestoreComprensoriConfiguratore(model, view);
		this.gestoreScambi = new GestoreScambi(model);
		
		inizializzaCommandsMenu();
	}
	
	private void inizializzaCommandsMenu() {
		commandMenu.put(1, new NuovoComprensorioCommand(gestoreComprensori));
		commandMenu.put(2, new AggiungiGerarchiaCommand(gestoreGerarchieConfiguratore));
		commandMenu.put(3, new SalvaModificheCommand(model));
		commandMenu.put(4, new StampaComprensoriCommand(gestoreComprensori));
		commandMenu.put(5, new StampaGerachieCommand(gestoreGerarchieConfiguratore));
		commandMenu.put(6, new VisualizzaFattoriCategoriaCommand(gestoreGerarchieConfiguratore));
		commandMenu.put(7, new VisualizzaScambiCategoriaCommand(view, gestoreScambi, gestoreGerarchieConfiguratore));
		commandMenu.put(8, new VisualizzaScambiCompleti(view, gestoreScambi));
	}
	
	public void run() {
		MyMenu menuRun = view.getMenuRun();
		
		do {
			view.stampaMenuRun();
		} while(sceltaMenuConfig(menuRun));
	}
	
	public boolean sceltaMenuConfig(MyMenu menu) {
		int scelta = menu.scegli();
		CommandUtente command = commandMenu.get(scelta);
		
		if (command != null) {
	        command.execute();
	        return true;
	    }
	    return false;
	}

}
