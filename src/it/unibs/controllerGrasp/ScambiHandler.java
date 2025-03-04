package it.unibs.controllerGrasp;

import java.util.ArrayList;
import java.util.List;

import it.unibs.domain.Foglia;
import it.unibs.domain.Proposta;
import it.unibs.domain.Scambio;
import it.unibs.domain.Utente;
import it.unibs.model.Model;
import it.unibs.model.ModelScambi;

public class ScambiHandler {
	private ModelScambi model;
	
	public ScambiHandler(Model model) {
		this.model = model.getModelScambi();
	}

	public ArrayList<Proposta> getScambiApertiFoglia(Foglia foglia) {
		return model.getScambiApertiFoglia(foglia);
	}

	public ArrayList<Proposta> getScambiChiusiFoglia(Foglia foglia) {
		return model.getScambiChiusiFoglia(foglia);
	}

	public ArrayList<Proposta> getScambiRitiratiFoglia(Foglia foglia) {
		return model.getScambiRitiratiFoglia(foglia);
	}

	public List<Scambio> getScambiCompleti() {
		return model.getScambiCompleti();
	}

	public ArrayList<String> getNomiScambiCopleti() {
		return model.getNomiScambiCopleti();
	}

	public Scambio getScambioCompleto(int i) {
		return model.getScambioCompleto(i);
	}

	public int calcolaOreDaFattore(Foglia richiesta, Foglia offerta, int oreRichiesta) {
		return model.calcolaOreDaFattore(richiesta, offerta, oreRichiesta);
	}

	public void addScambio(Proposta proposta) {
		model.addScambio(proposta);
	}

	public ArrayList<Proposta> getScambiApertiFruitore() {
		return model.getScambiApertiFruitore();
	}

	public ArrayList<Proposta> getScambiChiusiFruitore() {
		return model.getScambiChiusiFruitore();
	}

	public ArrayList<Proposta> getScambiRitiratiFruitore() {
		return model.getScambiRitiratiFruitore();
	}

	public void ritiraScambioAperto(Proposta p) {
		model.ritiraScambioAperto(p);
	}

	public Utente getUser() {
		return model.getUser();
	}
}
