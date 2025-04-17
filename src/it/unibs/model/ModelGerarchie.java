package it.unibs.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.unibs.domain.Categoria;
import it.unibs.domain.Foglia;
import it.unibs.domain.Gerarchia;
import it.unibs.domain.NestedMap;
import it.unibs.domain.NonFoglia;
import it.unibs.main.Persistence;

public class ModelGerarchie {
	private static final double MIN_FACTOR = 0.5;
	private static final double MAX_FACTOR = 2.0;
	private double fattoreMin = MIN_FACTOR;
	private double fattoreMax = MAX_FACTOR;
	
	private List<Gerarchia> gerarchie = new ArrayList<Gerarchia>();
	private NestedMap<Foglia, Foglia, Double> mapFattori = new NestedMap<>();
	private Gerarchia newGerarchia = new Gerarchia();

	public ModelGerarchie(Persistence persistence) {
		this.gerarchie = persistence.getGerarchie();
		this.mapFattori = persistence.getMapFattori();
	}

	public List<Gerarchia> getGerarchie() {
		return gerarchie;
	}
	
	public NestedMap<Foglia, Foglia, Double> getMapFattori() {
		return mapFattori;
	}
	
	public double getFattoreMin() {
		return fattoreMin;
	}
	
	public double getFattoreMax() {
		return fattoreMax;
	}
	
	public Gerarchia getNewGerarchia() {
		return newGerarchia;
	}
	
	public NestedMap<Foglia, Foglia, Double> getFattori() {
		return mapFattori;
	}
	
	public void setFattori(NestedMap<Foglia, Foglia, Double> fattori) {
		this.mapFattori = fattori;
	}
	
	public Categoria getRadiceNewGerarchia() {
		return newGerarchia.getRadice();
	}
	
	/**
	 * Controllo univocita' del nome della radice della gerarchia
	 * @param nome della nuova gerarchia
	 * @return true se il nome e' gia' presente
	 * @since 1
	 */
	public boolean checkNomeRadiceGerarchia(String name) {
		return gerarchie.stream().anyMatch(g -> g.getNomeRadice().equals(name));
	}

	/**
	 * Inizializzazione della nuova gerarchia vuota per la creazione
	 * @since 1
	 */
	public void resetNewGerarchia() {
		newGerarchia = new Gerarchia();
	}

	/**
	 * Aggiunta della radice a newGerarchia
	 * @param nuova radice
	 * @since 1
	 */
	public void addRadice(NonFoglia radice) {
		newGerarchia.setRadice(radice);
	}
	
	/**
	 * Aggiunta della newGerarchia alla lista delle gerarchie
	 * Controllo che non sia vuota
	 * @since 1
	 */
	public void addGerarchia() {
		if(newGerarchia.hasRadice())
			gerarchie.add(newGerarchia);
	}

	/**
	 * Getter della List di foglie della newGerarchia
	 * @return List di Foglia
	 * @since 1
	 */
	public ArrayList<Foglia> getFoglieNewGerarchia() {
		return this.newGerarchia.getFoglie();
	}	
	
	/**
	 * Metodo per l'aggiunta dei fattori di conversione della Foglia creata con la Foglia scelta e con tutte le foglie presenti nelle gerarchie
	 * Calcolo dei fattori tra le foglie che hanno fattore con con la Foglia scelta e la Foglia nuova
	 * @param fogliaNew nuova foglia creata
	 * @param fogliaOld foglia esistente scelta per inserire il fattore di conversione
	 * @param fattoreNewOld fattore di conversione tra fogliaNew e fogliaOld
	 * @since 1
	 */
	public void calcolaFattoriConversione(Foglia fogliaNew, Foglia fogliaOld, double fattoreNewOld) {
		mapFattori.put(fogliaNew, fogliaOld, fattoreNewOld);
		mapFattori.put(fogliaOld, fogliaNew, 1.0/fattoreNewOld);
		
		Map<Foglia, Double> fattoriOld = mapFattori.get(fogliaOld);
		
		for(Map.Entry<Foglia, Double> entry : fattoriOld.entrySet()) {
			Foglia fogliaTarget = entry.getKey();
            Double fattoreOldTarget = entry.getValue();
            
            if(fogliaTarget == fogliaNew)
            	continue;
            else {
            	double fattoreNewTarget = fattoreNewOld * fattoreOldTarget;
            	mapFattori.put(fogliaNew, fogliaTarget, fattoreNewTarget);
            	mapFattori.put(fogliaTarget, fogliaNew, 1.0/fattoreNewTarget);
            }
		}
	}
	
	/**
	 * Calcolo dinamico dei fattori di conversione tra 2 foglie
	 * in modo che durante il calcolo con tutte le altre foglie non si sforino i valori limite
	 * richiama {@link #calcolaFattoreMassimo(Map)} e {@link #calcolaFattoreMinimo(Map)}
	 * @param fogliaNew nuova foglia creata
	 * @param fogliaOld foglia esistente scelta per inserire il fattore di conversione
	 * @since 1
	 */
	public void calcolaFattoriMinMax(Foglia fogliaNew, Foglia fogliaOld) {
		Map<Foglia, Double> fattoriFogliaOld = mapFattori.get(fogliaOld);
		fattoreMax = calcolaFattoreMassimo(fattoriFogliaOld);
		fattoreMin = calcolaFattoreMinimo(fattoriFogliaOld);
	}
	
	/**
	 * Calcolo del fattore minimo accettabile che non causi errori 
	 * @param fattoriFogliaOld Map con chiave le foglie a cui e' associato 
	 * 			il valore del fattore di converisone tra la fogliaOld e le chiavi
	 * @return fattore minimo
	 * @since 1
	 */
	public double calcolaFattoreMinimo(Map<Foglia, Double> fattoriFogliaOld) {
		// Imposto il fattore minimo iniziale al valore minimo accettato
		double min = MIN_FACTOR;
		
		// Se la mappa dei fattori Ã¨ nulla, restituisco il fattore minimo
		if(fattoriFogliaOld == null)
			return min;

		// Itero attraverso tutti i valori dei fattori di conversione
		for(Double d : fattoriFogliaOld.values()) {
			/*
			 * Calcolo il reciproco del fattore moltilicato per il fattore minimo.
			 * Confronto il risultato con il valore minimo attuale e prendo il massimo tra i due.
			 * Assicura che il fattore minimo tenga conto anche dei fattori di conversione presenti,
			 * evitando che il nuovo fattore sia troppo piccolo e causi errori durante la conversione.
			 */
			min = Math.max(min, MIN_FACTOR/d);
		}
		// Restituisco il fattore minimo accettabile
		return min;
	}
	
	/**
	 * Calcolo del fattore massimo accettabile che non causi errori 
	 * @param fattoriFogliaOld Map con chiave le foglie a cui e' associato 
	 * 			il valore del fattore di converisone tra la fogliaOld e le chiavi
	 * @return fattore massimo
	 * @since 1
	 */
	public double calcolaFattoreMassimo(Map<Foglia, Double> fattoriFogliaOld) {
		// Imposto il fattore massimo iniziale al valore massimo accettato
		double max = MAX_FACTOR;
		
		// Se la mappa dei fattori Ã¨ nulla, restituisco il fattore massimo
		if(fattoriFogliaOld == null)
			return max;
		
		// Itero attraverso tutti i valori dei fattori di conversione
		for(Double d : fattoriFogliaOld.values()) {
			/*
			 * Calcolo il rapporto tra il fattore massimo e ciascun fattore di conversione.
			 * Poi confronto questo valore con il valore massimo attuale e prendo il minimo tra i due.
			 * Assicura che il fattore massimo tenga conto anche dei fattori di conversione presenti,
			 * evitando che il nuovo fattore sia troppo grande e causi errori durante la conversione.
			 */
			max = Math.min(max, MAX_FACTOR/d);
		}
		// Restituisco il fattore massimo accettabile
		return max;
	}
	
	/**
	 * Metodo per ritornare un ArrayList con tutti i nomi delle radici
	 * @return List con i nomi delle radici
	 * @since 1
	 */
	public ArrayList<String> getNomiRadici() {
		ArrayList<String> nomiRadici = new ArrayList<>();
		for(Gerarchia g : gerarchie) {
			nomiRadici.add(g.getNomeRadice());
		}
		
		return nomiRadici;
	}
	
	/**
	 * Metodo per ritornare un ArrayList con tutti i nomi delle foglie di una gerarchia
	 * richiama {@link #getNomiFoglieGerarchia(Gerarchia)} e {@link #getGerarchia(int)}
	 * @param inidice della gerarchia nell'ArrayList gerachie
	 * @return List con i nomi
	 * @since 1
	 */
	public ArrayList<String> getNomiFoglieGerarchia(int indexGerarchia) {
		Gerarchia g = getGerarchia(indexGerarchia);
		return getNomiFoglieGerarchia(g);
	}

	/**
	 * Metodo per ritornare un ArrayList con tutti i nomi delle foglie di una gerarchia
	 * @param Gerarchia della quale servono i nomi delle foglie
	 * @return List con i nomi
	 * @since 1
	 */
	public ArrayList<String> getNomiFoglieGerarchia(Gerarchia g) {
		ArrayList<String> nomiFoglie = new ArrayList<>();

		for(Foglia f : g.getFoglie()) {
			nomiFoglie.add(f.getNome());
		}
		
		return nomiFoglie;
	}
	
	/**
	 * Getter di una gerarchia di gerachie con indice
	 * @param indice della gerachia
	 * @return Gerarchia corrispondente
	 * @since 1
	 */
	private Gerarchia getGerarchia(int index) {
		return gerarchie.get(index);
	}
	
	/**
	 * Getter di una foglia di una gerarchia dato indice della gerarchia
	 * e indice della foglia nell'arraylist delle Foglie di Gerarchia {@link Gerarchia#getFoglie()}
	 * @param indexRadice indice della gerachia in gerarchie
	 * @param indexFoglia indice della foglia nell'ArrayList delle foglie di gerarchia
	 * @return Foglia corrispondente
	 * @since 1
	 */
	public Foglia getFogliaDaRadice(int indexRadice, int indexFoglia) {
		return getGerarchia(indexRadice).getFoglia(indexFoglia);
	}
	
	/**
	 * Getter di radice dato indice della gerarchia
	 * @param indexRadice indice della gerachia in gerarchie
	 * @return radice della gerarchia
	 * @since 2
	 */
	public NonFoglia getRadice(int indexRadice) {
		return (NonFoglia) getGerarchia(indexRadice).getRadice();
	}
	
	/**
	 * Getter del fattore di conversione date le due Foglie
	 * @param richiesta Foglia di partenza
	 * @param offerta Foglia destinazione
	 * @return il fattore tra le 2 foglie
	 * @since 3
	 */
	public double getFattore(Foglia richiesta, Foglia offerta) {
		return mapFattori.get(richiesta, offerta);
	}

	/**
	 * Verifica se la gerarchia è “terminabile”:
	 * ogni nodo NonFoglia ha almeno un figlio (Categoria o Foglia).
	 * 
	 * @return true se ogni NonFoglia ha almeno un figlio, false altrimenti
	 * @since 1X
	 */
	public boolean isTerminabile() {
	    // Non ha radice  non è terminabile
	    if (newGerarchia.getRadice() == null) {
	        return false;
	    }

	    // Scorri tutte le categorie: consideriamo solo le NonFoglia
	    for (Categoria c : newGerarchia.getCategorie()) {
	        if (c instanceof NonFoglia) {
	            NonFoglia nf = (NonFoglia) c;
	            // qui presupponiamo che NonFoglia esponga i suoi figli:
	            // List<Categoria> getFigli();
	            if (nf.getChilds().isEmpty()) {
	                return false;
	            }
	        }
	    }
	    return true;
	}

}
