package it.unibs.controller;

import java.util.ArrayList;

import it.unibs.domain.Comprensorio;
import it.unibs.exception.ListaComprensoriVuotaException;
import it.unibs.model.ModelAccesso;
import it.unibs.mylib.MyMenu;
import it.unibs.view.console.ViewAccesso;

public class GestoreComprensoriAccesso {
	private ViewAccesso viewAccesso;
	private ModelAccesso modelAccesso;
	
	public GestoreComprensoriAccesso(ModelAccesso modelAccesso, ViewAccesso viewAccesso ) {
		this.viewAccesso = viewAccesso;
		this.modelAccesso = modelAccesso;
	}
	
	/**
	 * Permette la scelta di un comprensorio 
	 * @param modelAccesso
	 * @return
	 * @throws ListaComprensoriVuotaException 
	 */
	public Comprensorio sceltaComprensorio() {
		ArrayList<String> nomiComp = new ArrayList<>();
		
		for (Comprensorio c : modelAccesso.getComprensori()) {
			nomiComp.add(c.stampaComprensorio());
		}
		String[] nomiComprenosori = nomiComp.toArray(new String[0]);
		
		MyMenu menuSceltaComp =viewAccesso.menuSceltaComprensorio(nomiComprenosori);
		int scelta = menuSceltaComp.scegliNoExit();
		
		return modelAccesso.getComprensorio(scelta);
	}
}
