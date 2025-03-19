 package it.unibs.controller;

import java.util.ArrayList;

import javax.swing.JFrame;

import it.unibs.controllerGrasp.GerarchieHandler;
import it.unibs.domain.*;
import it.unibs.model.Model;
import it.unibs.mylib.MyMenu;
import it.unibs.view.console.ViewFruitore;

public class GestoreGerarchieFruitore {
	private  ViewFruitore view; 
	private GerarchieHandler gerarchieHandler;
	private JFrame frame;
	
	public GestoreGerarchieFruitore(Model model, JFrame frame) {
		super();
		this.frame=frame;
		this.gerarchieHandler = new GerarchieHandler(model);
	}

	public void navigazioneGerarchie() {
		
//		Foglia fogliaSelezionata = navigaGerarchia();
//		view.msgFogliaSelezionata(fogliaSelezionata);
	}
	
	// due metodi successivi usati anche da gestoreScambi
	/**
	 * Permette al Fruitore di selezionare una gerarchia scegliendo la radice
	 * E selezione di una foglia {@link #sceltaFogliaDaCampo(Categoria)}
	 * @return Foglia selezionata
	 * @since 3
	 */ 
	public Foglia navigaGerarchia() {
		ArrayList<String> nomiRadici = gerarchieHandler.getNomiRadici();
		MyMenu menuRadice = view.menuSceltaRadice(nomiRadici);
		int scelta = menuRadice.scegli();
		
		Foglia fogliaSelezionata = sceltaFogliaDaCampo(gerarchieHandler.getRadice(scelta-1));
		
		return fogliaSelezionata;
	}
	
	/**
	 * Permette al Fruitore di navigare entro la gerarchia scendendo dalla radice ad una foglia,
	 * impostando  prograssivamente i valori dei campi
	 * @param nf NonFoglia di cui si vuole impostare il valore del campo 
	 * @return Foglia selezionata al termine della ricorsione
	 * @since 2
	 */
	private Foglia sceltaFogliaDaCampo(Categoria nf) {
		if(nf instanceof Foglia)
			return (Foglia) nf;
		
		MyMenu menuCampo = view.menuSceltaDominio(((NonFoglia) nf).getCampo(), ((NonFoglia) nf).getDomini());
		int scelta = menuCampo.scegli();
		
		return sceltaFogliaDaCampo(nf.getChilds().get(scelta-1));
	}
}
