package it.unibs.controller;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import it.unibs.controller.commands.CommandUtente;
import it.unibs.controller.commands.fruitore.CreaPropostaCommand;
import it.unibs.controller.commands.fruitore.NavigazioneGerarchieCommand;
import it.unibs.controller.commands.fruitore.RitiraPropostaCommand;
import it.unibs.controller.commands.fruitore.VisualizzaProposteUtenteCommand;
import it.unibs.model.Model;
import it.unibs.mylib.MyMenu;
import it.unibs.view.configuratore.ViewMenuConfiguratore;
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
	private JFrame frame;
	
	public ControllerFruitore(Model model,JFrame frame) {
		this.model = model;
		this.frame=frame;
		this.view = new ViewMenuFruitore(frame);
		this.gestoreGerarchieFruitore = new GestoreGerarchieFruitore(model, frame);
		this.gestoreScambi = new GestoreScambi(model,frame);
		
		inizializzaCommandsMenu();
	}
	public void setFrame(JFrame frame) {
        this.frame = frame;
    }
	
	private void inizializzaCommandsMenu() {
		commandMenu.put(1, new NavigazioneGerarchieCommand(gestoreGerarchieFruitore));
		commandMenu.put(2, new CreaPropostaCommand(gestoreGerarchieFruitore, gestoreScambi));
		commandMenu.put(3, new VisualizzaProposteUtenteCommand(gestoreScambi));
		commandMenu.put(4, new RitiraPropostaCommand(gestoreScambi));
	} 
	
	public void run() {
		ViewMenuFruitore menuFruitore = new ViewMenuFruitore(frame);
        frame.getContentPane().add(menuFruitore);
        menuFruitore.setLayout(null);
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
