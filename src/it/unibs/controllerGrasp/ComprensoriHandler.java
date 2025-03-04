package it.unibs.controllerGrasp;

import java.util.List;

import it.unibs.domain.*;
import it.unibs.model.Model;
import it.unibs.model.ModelComprensori;

public class ComprensoriHandler {
	private ModelComprensori model;
	
	public ComprensoriHandler(Model model) {
		this.model = model.getModelComprensori();
	}
	
	public boolean checkNomeComprensorio(String name) {
		return model.checkNomeComprensorio(name);
	}
	
	public void addComprensorio(Comprensorio comprensorio) {
		model.addComprensorio(comprensorio);
	}

	public List<Comprensorio> getComprensori() {
		return model.getComprensori();
	}
}
