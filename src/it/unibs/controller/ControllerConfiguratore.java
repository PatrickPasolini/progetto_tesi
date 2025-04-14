package it.unibs.controller;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import it.unibs.controller.commands.CommandUtente;
import it.unibs.controller.commands.configuratore.AggiungiGerarchiaCommand;
import it.unibs.controller.commands.configuratore.AggiungiComprensorioCommand;
import it.unibs.controller.commands.configuratore.SalvaModificheCommand;
import it.unibs.controller.commands.configuratore.StampaComprensoriCommand;
import it.unibs.controller.commands.configuratore.StampaGerachieCommand;
import it.unibs.controller.commands.configuratore.VisualizzaFattoriCategoriaCommand;
import it.unibs.controller.commands.configuratore.VisualizzaScambiCategoriaCommand;
import it.unibs.controller.commands.configuratore.VisualizzaScambiCompletiCommand;
import it.unibs.model.Model;
import it.unibs.view.configuratore.ViewMenuConfiguratore;

/**
 * Il ControllerConfiguratore gestisce le operazioni di comunicazione tra il model e la view
 * Consente di aggiungere comprensori e gerarchie, salvare dati e visualizzare informazioni.
 */
public class ControllerConfiguratore implements Controller {
	private Model model;
	private GestoreGerarchieConfiguratore gestoreGerarchieConfiguratore;
	private GestoreComprensoriConfiguratore gestoreComprensori;
	private GestoreScambi gestoreScambi;
	private GestoreSalvataggio gestoreSalvataggio;
	private Map<Integer, CommandUtente> commandMenu = new HashMap<>();
	private JFrame frame;
	
	public ControllerConfiguratore(Model model,JFrame frame) {
		this.model = model;
		this.frame=frame;
		frame.setResizable(false);
		this.gestoreGerarchieConfiguratore = new GestoreGerarchieConfiguratore(model,frame);
		this.gestoreComprensori = new GestoreComprensoriConfiguratore(model,frame);
		this.gestoreScambi = new GestoreScambi(model,frame);
		this.gestoreSalvataggio = new GestoreSalvataggio(model, frame);
		 
		inizializzaCommandsMenu();
	}
	public void setFrame(JFrame frame) {
        this.frame = frame;
    }
	 
	private void inizializzaCommandsMenu() {
		commandMenu.put(1, new AggiungiComprensorioCommand(gestoreComprensori));
		commandMenu.put(2, new AggiungiGerarchiaCommand(gestoreGerarchieConfiguratore));
		commandMenu.put(3, new VisualizzaFattoriCategoriaCommand(gestoreGerarchieConfiguratore));
		commandMenu.put(4, new SalvaModificheCommand(gestoreSalvataggio));
		commandMenu.put(5, new StampaComprensoriCommand(gestoreComprensori));
		commandMenu.put(6, new StampaGerachieCommand(gestoreGerarchieConfiguratore));
		commandMenu.put(7, new VisualizzaScambiCategoriaCommand(gestoreScambi));
		commandMenu.put(8, new VisualizzaScambiCompletiCommand(gestoreScambi));
	}
	
	public void run() {
		ViewMenuConfiguratore menuConfiguratore = new ViewMenuConfiguratore(frame);
        frame.getContentPane().add(menuConfiguratore);
        menuConfiguratore.setLayout(null);
       
        for (Integer key : commandMenu.keySet()) {
            menuConfiguratore.setButtonListeners(e->sceltaMenuConfig(key),key-1);
        }
	}
	
	public boolean sceltaMenuConfig(int scelta) {
		CommandUtente command = commandMenu.get(scelta);
		if (command != null) {
	        command.execute();
	        return true;
	    }
	    return false;
	}

}
