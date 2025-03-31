 package it.unibs.controller;

import java.util.ArrayList;

import javax.swing.JFrame;

import it.unibs.controllerGrasp.GerarchieHandler;
import it.unibs.domain.*;
import it.unibs.model.Model;
import it.unibs.mylib.MyMenu;
import it.unibs.view.configuratore.ViewVisualizzaGerarchie;
import it.unibs.view.console.ViewFruitore;
import it.unibs.view.fruitore.ViewNavigaGerarchie;

public class GestoreGerarchieFruitore {
	private  ViewFruitore view; 
	private GerarchieHandler gerarchieHandler;
	private JFrame frame;
	private Model model;
	public GestoreGerarchieFruitore(Model model, JFrame frame) {
		super();
		this.frame=frame;
		this.model=model;
		this.gerarchieHandler = new GerarchieHandler(model);
	}

	public void navigazioneGerarchie() {
		ViewNavigaGerarchie viewGerarchie = new ViewNavigaGerarchie(frame,gerarchieHandler.getGerarchie() );
		frame.getContentPane().add(viewGerarchie);
		viewGerarchie.setLayout(null);
		viewGerarchie.setBtnHomeListener(e-> backHome());
		viewGerarchie.setLeafDoubleClickListener(e -> System.out.println(viewGerarchie.getCategoriaSelezionata().getNome()));


//		Foglia fogliaSelezionata = navigaGerarchia();
//		view.msgFogliaSelezionata(fogliaSelezionata);
	}
	
	private void backHome() {
		ControllerFruitore controllerConfiguratore = new ControllerFruitore(model, frame);
		controllerConfiguratore.run();
	}
	
	public Foglia navigaGerarchia() {
		ArrayList<String> nomiRadici = gerarchieHandler.getNomiRadici();
		MyMenu menuRadice = view.menuSceltaRadice(nomiRadici);
		int scelta = menuRadice.scegli();
		
		Foglia fogliaSelezionata = sceltaFogliaDaCampo(gerarchieHandler.getRadice(scelta-1));
		
		return fogliaSelezionata;
	}
	private Foglia sceltaFogliaDaCampo(Categoria nf) {
		if(nf instanceof Foglia)
			return (Foglia) nf;
		
		MyMenu menuCampo = view.menuSceltaDominio(((NonFoglia) nf).getCampo(), ((NonFoglia) nf).getDomini());
		int scelta = menuCampo.scegli();
		
		return sceltaFogliaDaCampo(nf.getChilds().get(scelta-1));
	}
}
