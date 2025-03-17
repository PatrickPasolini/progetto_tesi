package it.unibs.controller;

import java.util.ArrayList;

import it.unibs.controllerGrasp.ComprensoriHandler;
import it.unibs.domain.*;
import it.unibs.model.Model;
import it.unibs.mylib.InputDati;
import it.unibs.view.accesso.*;
import it.unibs.view.console.ViewConfiguratore;

public class GestoreComprensoriConfiguratore {
	private ViewConfiguratore view;  
	private ComprensoriHandler comprensoriHandler; 
	
//	public GestoreComprensoriConfiguratore(Model model, ViewConfiguratore view) {
//		this.view = view;
//		this.comprensoriHandler = new ComprensoriHandler(model);
//	}
	
	public GestoreComprensoriConfiguratore(Model model) {
		this.comprensoriHandler = new ComprensoriHandler(model);
	}
	
	
	/** 
	 * Metodo per la creazione di un nuovo comprensorio
	 * Richiede: nome univoco, lista dei comuni (@ per terminare l'inserimento)
	 * @since 1
	 */
	public void nuovoComprensorio() {
		
		System.out.println("gay");

//		String name;
//		do {
//			view.msgNuovoComprensorioNome();
//			name = InputDati.leggiStringaNonVuota("");
//		} while (comprensoriHandler.checkNomeComprensorio(name));
//		 
//		view.msgNuovoComprensorioComuni();
// 
//		ArrayList<String> comuni = new ArrayList<String>();
//		String comune;
//		
//		do {
//			comune = InputDati.leggiStringaNonVuota("");
//			if(!comune.equals("@")) 
//				comuni.add(comune);
//		} while(!comune.equals("@") || comuni.size() == 0);
//		
//		comprensoriHandler.addComprensorio(new Comprensorio(name,comuni));
	}
	
	/**
	 * Metodo per visualizzare tutti i comprensori presenti
	 * @since 4
	 */
	public void stampaComprensori() {
		System.out.println(comprensoriHandler.getComprensori());
//		view.stampaComprensori(comprensoriHandler.getComprensori());
	}
}