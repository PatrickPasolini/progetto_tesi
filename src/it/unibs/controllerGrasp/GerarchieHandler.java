package it.unibs.controllerGrasp;

import java.util.ArrayList;
import java.util.List;

import it.unibs.domain.Categoria;
import it.unibs.domain.Foglia;
import it.unibs.domain.Gerarchia;
import it.unibs.domain.NestedMap;
import it.unibs.domain.NonFoglia;
import it.unibs.model.Model;
import it.unibs.model.ModelGerarchie;

public class GerarchieHandler {
	private ModelGerarchie model;

	public GerarchieHandler(Model model) {
		this.model = model.getModelGerarchie();
	}

	public void resetNewGerarchia() {
		model.resetNewGerarchia();
	}

	public void addRadice(NonFoglia radice) {
		model.addRadice(radice);
	}

	public Categoria getRadiceNewGerarchia() {
		return model.getRadiceNewGerarchia();
	}

	public void addGerarchia() {
		model.addGerarchia();
	}

	public boolean checkNomeRadiceGerarchia(String nome) {
		return model.checkNomeRadiceGerarchia(nome);
	}

	public Gerarchia getNewGerarchia() {
		return model.getNewGerarchia();
	}
	
	public void calcolaFattoriMinMax(Foglia fogliaNew, Foglia fogliaOld) {
		model.calcolaFattoriMinMax(fogliaNew, fogliaOld);
	}

	public double getFattoreMin() {
		return model.getFattoreMin();
	}

	public double getFattoreMax() {
		return model.getFattoreMax();
	}

	public void calcolaFattoriConversione(Foglia fogliaNew, Foglia fogliaOld, double fattore) {
		model.calcolaFattoriConversione(fogliaNew, fogliaOld, fattore);
	}

	public ArrayList<String> getNomiRadici() {
		return model.getNomiRadici();
	}

	public ArrayList<String> getNomiFoglieGerarchia(int i) {
		return model.getNomiFoglieGerarchia(i);
	}

	public Foglia getFogliaDaRadice(int i, int j) {
		return model.getFogliaDaRadice(i, j);
	}

	public ArrayList<String> getNomiFoglieGerarchia(Gerarchia newGerarchia) {
		return model.getNomiFoglieGerarchia(newGerarchia);
	}

	public NestedMap<Foglia, Foglia, Double> getMapFattori() {
		return model.getMapFattori();
	}

	public List<Gerarchia> getGerarchie() {
		return model.getGerarchie();
	}

	public Categoria getRadice(int i) {
		return model.getRadice(i);
	}

	public boolean isTerminabile() {
		return model.isTerminabile();
	}

}
