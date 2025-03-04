package it.unibs.main;

import java.util.*;

import it.unibs.domain.*;
/**
 * Questa classe rappresenta la persistenza dell'applicazione.
 * Contiene le liste dei comprensori e delle gerarchie,
 * una mappa dei fattori avente come chiave 2 foglie e 
 * value il fattore di conversione dalla foglia1 alla foglia2,
 * e una lista con gli scambi aperti dagli utenti
 *  
 * Fornisce metodi per accedere e modificare queste strutture dati.
 */
public class Persistence {
	private List<Comprensorio> comprensori = new ArrayList<Comprensorio>();
	private List<Gerarchia> gerarchie = new ArrayList<Gerarchia>();
	private NestedMap<Foglia, Foglia, Double> mapFattori = new NestedMap<>();
	private List<Proposta> scambiAperti = new ArrayList<Proposta>();
	private List<Proposta> scambiChiusi = new ArrayList<Proposta>();
	private List<Proposta> scambiRitirati = new ArrayList<Proposta>();
	private List<Scambio> scambiCompleti = new ArrayList<Scambio>();
	
	public Persistence() {
		super(); 
	}
	
	public List<Proposta> getScambiAperti() {
		return scambiAperti;
	}

	public void setScambiAperti(List<Proposta> scambiAperti) {
		this.scambiAperti = scambiAperti;
	}

	public List<Comprensorio> getComprensori() {
		return comprensori;
	}
	
	public void setComprensori(List<Comprensorio> comprensori) {
		this.comprensori = comprensori;
	}
	
	public List<Gerarchia> getGerarchie() {
		return gerarchie;
	}
	
	public void setGerarchie(List<Gerarchia> gerarchie) {
		this.gerarchie = gerarchie;
	}
	
	public NestedMap<Foglia, Foglia, Double> getMapFattori() {
		return mapFattori;
	}
	
	public void setMapFattori(NestedMap<Foglia, Foglia, Double> fattori) {
		this.mapFattori = fattori;
	}

	public List<Proposta> getScambiChiusi() {
		return scambiChiusi;
	}

	public void setScambiChiusi(List<Proposta> scambiChiusi) {
		this.scambiChiusi = scambiChiusi;
	}

	public List<Proposta> getScambiRitirati() {
		return scambiRitirati;
	}

	public void setScambiRitirati(List<Proposta> scambiRitirati) {
		this.scambiRitirati = scambiRitirati;
	}

	public List<Scambio> getScambiCompleti() {
		return scambiCompleti;
	}

	public void setScambiCompleti(List<Scambio> scambiCompleti) {
		this.scambiCompleti = scambiCompleti;
	}

	/**
	 * Verifica che gli attributi sono null e in caso li inizializza
	 * @since 2
	 */
	public void isNull() {
		if(comprensori == null)
			comprensori = new ArrayList<Comprensorio>();
		if(gerarchie == null)
			gerarchie = new ArrayList<Gerarchia>();
		if(mapFattori == null)
			mapFattori = new NestedMap<Foglia, Foglia, Double>();	
		if(scambiAperti == null)
			scambiAperti = new ArrayList<Proposta>();
		if(scambiChiusi == null)
			scambiChiusi = new ArrayList<Proposta>();
		if(scambiRitirati == null)
			scambiRitirati = new ArrayList<Proposta>();
	}
}
