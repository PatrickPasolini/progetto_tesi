package it.unibs.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import it.unibs.domain.Foglia;
import it.unibs.domain.NestedMap;
import it.unibs.domain.Proposta;
import it.unibs.domain.Scambio;
import it.unibs.domain.Utente;
import it.unibs.main.Persistence;
import it.unibs.mylib.ServizioFile;

public class ModelScambi {
	public static final String PATH_LOG_FILE = "./Data/log.txt";
	
	private List<Proposta> scambiAperti = new ArrayList<Proposta>();
	private List<Proposta> scambiChiusi = new ArrayList<Proposta>();
	private List<Proposta> scambiRitirati = new ArrayList<Proposta>();
	private List<Scambio> scambiCompleti = new ArrayList<Scambio>();
	
	private NestedMap<Foglia, Foglia, Double> mapFattori = new NestedMap<>();
	
	private Utente user;
	private Model model;
	
	public ModelScambi(Persistence persistence, Model model) {
		this.model=model;
		
		this.scambiAperti = persistence.getScambiAperti();
		this.scambiChiusi = persistence.getScambiChiusi();
		this.scambiCompleti = persistence.getScambiCompleti();
		this.scambiRitirati = persistence.getScambiRitirati();
		this.mapFattori = persistence.getMapFattori();
	}

	public Utente getUser() {
		return user;
	}
	
	public void setUser(Utente user) {
		this.user = user;
	}
	
	public List<Scambio> getScambiCompleti() {
		return scambiCompleti;
	}

	/** 
	 * Metodo per l'aggiunta di un nuovo scambio alla lista di scambiAperti
	 * @param newScambio nuovo scambio da aggiungere
	 * @since 3
	 */
	public void addScambio(Proposta newScambio) {
		scambiAperti.add(newScambio);
		
		String log = String.format("scambio aperto: " + newScambio.toString());
		ServizioFile.saveOnTxtFileWithDate(log,PATH_LOG_FILE);
		
		realizzaScambio(newScambio);
		
		model.salvaModifiche();
	}

	/**
	 * Metodo per il calcolo delle ore da offrire
	 * @param richiesta Foglia di richiesta
	 * @param offerta Foglia di offerta
	 * @param oreRichiesta ore richieste
	 * @return ore da offrire
	 * @since 3
	 */
	public int calcolaOreDaFattore(Foglia richiesta, Foglia offerta, int oreRichiesta) {
		double fattore = getFattore(richiesta,offerta);
		return (int) Math.round(oreRichiesta * fattore);
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
	 * Metodo per la realizzazione dello scambio
	 * @param newProposta nuova proposta da soddisfare
	 * @since 4
	 */
	public void realizzaScambio(Proposta newProposta) {
		Stack<Proposta> scambio = new Stack<>();
		scambio.push(newProposta);
		
		if(cercaScambio(scambio, newProposta)) {
			// per dare il nome allo scambio
			int size = scambiCompleti.size();
			scambiCompleti.add(new Scambio("scambio-" + (size+1), scambio));
			
			spostaScambioApertoChiuso(scambio);
		}
	}

	/**
	 * Metodo per la ricerca dello scambio e l'inizializzazione delle liste per il metodo ricorsivo
	 * @param scambio stack con il cammino dello scambio
	 * @param newProposta nuova proposta da soddisfare
	 * @return true se lo scambio e' soddisfatto, false altrimenti
	 * @since 4
	 */
	public boolean cercaScambio(Stack<Proposta> scambio, Proposta newProposta) {
		ArrayList<Proposta> proposteComprensorio = getProposteComprensorio(newProposta);
		//se non ci sono proposte nelo stesso comprensorio termina
		if(proposteComprensorio.isEmpty())	
			return false;
		
		ArrayList<Proposta> soddisfaRichiesta = getRichiesteSoddisfatte(proposteComprensorio, newProposta);
		//se non ci sono proposte che soddisfano la richiesta termina
		if(soddisfaRichiesta.isEmpty())		
			return false;
		
		//controllo se la proposta viene soddisfatta subito
		if(controllaScambio(soddisfaRichiesta, newProposta, scambio)) {
			return true;
		}
		
		ArrayList<Proposta> soddisfaOfferta = getOfferteSoddisfatte(proposteComprensorio, newProposta);
		//se non ci sono proposte che soddisfano l'offerta termina
		if(soddisfaOfferta.isEmpty())
			return false;
		
		return ricercaScambio(proposteComprensorio, soddisfaRichiesta, scambio, soddisfaOfferta);
	}
	
	
	/**
	 * Metodo ricorsivo per la ricerca di un cammino per completaere lo scambio
	 * la proposta viene aggiunta allo stack, se non si trova un cammino avviene il pop
	 * se non c'e' una corrisponenza che soddisfa lo scambio si procede con le richieste 
	 * che soddisfano l'offerta della proposta p
	 * @param proposteComprensorio le proposte nello stesso comprensorio
	 * @param soddisfaRichiesta le proposte che soddisfano la richiesta della proposta di riferimento
	 * @param scambio stack con il cammino dello scambio
	 * @param soddisfaOfferta lista di proposte che richiedono l'offerta della proposta in esame
	 * @return true se il la richiesta iniziale e' soddisfatta, false altrimenti
	 * @since 4
	 */
	private boolean ricercaScambio(ArrayList<Proposta> proposteComprensorio, 
									ArrayList<Proposta> soddisfaRichiesta, 
									Stack<Proposta> scambio, 
									ArrayList<Proposta> soddisfaOfferta) {
		
		for(Proposta p : soddisfaOfferta) {
			scambio.push(p);
			
			if(controllaScambio(soddisfaRichiesta, p, scambio))
				return true;
						
			ArrayList<Proposta> soddisfaOffertaP = getOfferteSoddisfatte(proposteComprensorio, p);
			if(!soddisfaOffertaP.isEmpty()) {
				return ricercaScambio(proposteComprensorio, soddisfaRichiesta, scambio, soddisfaOffertaP);
			}
			
			scambio.pop();
		}
		
		return false;
	}

	/**
	 * Getter della lista di proposte nello stesso comprensorio della proposta specificata
	 * @param proposta la proposta di riferimento
	 * @return ArrayList con le proposte nello stesso comprensorio
	 * @since 4
	 */
	private ArrayList<Proposta> getProposteComprensorio(Proposta proposta) {
		ArrayList<Proposta> proposteComprensorio = new ArrayList<>();
		for(Proposta p : scambiAperti) {
			if(p.getComprensorio().getName().equals(proposta.getComprensorio().getName()))
				proposteComprensorio.add(p);
		}
		
		return proposteComprensorio;
	}
	
	/**
	 * Getter della lista di proposte nello stesso comprensorio che soddisfano la richiesta della proposta specificata
	 * @param proposteComprensorio lista di proposte nello stesso comprensorio
	 * @param proposta la proposta di riferimento
	 * @return ArrayList con le proposte che soddisfano la richiesta
	 * @since 4
	 */
	private ArrayList<Proposta> getRichiesteSoddisfatte(ArrayList<Proposta> proposteComprensorio, Proposta proposta) {
		ArrayList<Proposta> soddisfaRichiesta = new ArrayList<>();
		for(Proposta p : proposteComprensorio) {
			if(p.getOfferta().equals(proposta.getRichiesta()))
				if(!p.getFruitoreRichiedente().equals(proposta.getFruitoreRichiedente()))
					if(p.getOreOfferta()==proposta.getOreRichiesta())
						soddisfaRichiesta.add(p);
		}
		
		return soddisfaRichiesta;
	}
	
	/**
	 * Getter della lista di proposte nello stesso comprensorio che soddisfano l'offerta della proposta specificata
	 * @param proposteComprensorio lista di proposte nello stesso comprensorio
	 * @param proposta la proposta di riferimento
	 * @return ArrayList con le proposte che soddisfano l'offerta
	 * @since 4
	 */
	private ArrayList<Proposta> getOfferteSoddisfatte(ArrayList<Proposta> proposteComprensorio, Proposta proposta) {
		ArrayList<Proposta> soddisfaOfferta = new ArrayList<>();
		for(Proposta p : proposteComprensorio) {
			if(p.getRichiesta().equals(proposta.getOfferta()))
				if(!p.getFruitoreRichiedente().equals(proposta.getFruitoreRichiedente()))
					if(p.getOreRichiesta()==proposta.getOreOfferta())
						soddisfaOfferta.add(p);
		}
		
		return soddisfaOfferta;
	}
	 
	/**
	 * Controlla se l'offerta della proposta di riferimento soddisfa la richiesta di una delle proposte
	 * che soddisfano la richiesta della proposta inziale
	 * @param soddisfaRichiesta ArrayList con le proposte che soddisfano la richiesta iniziale
	 * @param proposta la proposta di riferimento
	 * @param scambio stack con il cammino dello scambio
	 * @return true se lo scambio e' soddisfatto, false altrimenti
	 * @since 4
	 */
	private boolean controllaScambio(ArrayList<Proposta> soddisfaRichiesta, Proposta proposta, Stack<Proposta> scambio) {
		for(Proposta p : soddisfaRichiesta) {
			if(p.getRichiesta().equals(proposta.getOfferta())) {
				if(!p.getFruitoreRichiedente().getNome().equals(proposta.getFruitoreRichiedente().getNome())) {
					scambio.push(p);
					return true;
				}
			}
		}
		
		return false;
	}

	/**
	 * Metodo per ricavare gli scambi aperti proposti dall'utente
	 * @return lista con gli scambi aperti proposti dall'utente
	 * @since 4
	 */
	public ArrayList<Proposta> getScambiApertiFruitore() {
		return getScambiFruitore(scambiAperti);
	}

	/**
	 * Metodo per ricavare gli scambi chiusi proposti dall'utente
	 * @return lista con gli scambi chiusi proposti dall'utente
	 * @since 4
	 */
	public ArrayList<Proposta> getScambiChiusiFruitore() {
		return getScambiFruitore(scambiChiusi);
	}

	/**
	 * Metodo per ricavare gli scambi ritirati dall'utente
	 * @return lista con gli scambi ritirati dall'utente
	 * @since 4
	 */
	public ArrayList<Proposta> getScambiRitiratiFruitore() {
		return getScambiFruitore(scambiRitirati);
	}
	
	/**
	 * Metodo per ricavare gli scambi proposti dall'utente dalla lista specificata
	 * @param scambi la lista da cui ricavare gli scambi
	 * @return lista con gli scambi proposti dall'utente
	 * @since 4
	 */
	private ArrayList<Proposta> getScambiFruitore(List<Proposta> scambi) {
		ArrayList<Proposta> scambiFruitore = new ArrayList<Proposta>();
		for(Proposta p : scambi) {
			if(p.getFruitoreRichiedente().getNome().equals(user.getNome()))
				scambiFruitore.add(p);
		}
		
		return scambiFruitore;
	}

	/**
	 * Metodo per chiudere le proposte del cammino dello scambio
	 * @param scambio stack con il cammino dello scambio
	 * @since 4
	 */
	private void spostaScambioApertoChiuso(Stack<Proposta> scambio) {
		for(Proposta p : scambio) {
			chiudiScambioAperto(p);
		}
	}
	
	/**
	 * Metodo per chiudere uno scambio aperto
	 * viene rimosso dalla lista scambiAperti e aggiunto a scambiChiusi
	 * @param proposta la proposta da chiudere
	 * @since 4
	 */
	public void chiudiScambioAperto(Proposta proposta) {
		scambiAperti.remove(proposta);
		scambiChiusi.add(proposta);
		
		String log = String.format("scambio chiuso: " + proposta.toString());
		ServizioFile.saveOnTxtFileWithDate(log,PATH_LOG_FILE);
	}
	
	/**
	 * Metodo per ritirare uno scambio aperto
	 * viene rimosso dalla lista scambiAperti e aggiunto a scambiRitirati
	 * @param proposta la proposta da ritirare
	 * @since 4
	 */
	public void ritiraScambioAperto(Proposta proposta) {
		scambiAperti.remove(proposta);
		scambiRitirati.add(proposta);

		model.salvaModifiche();
		
		String log = String.format("scambio ritirato: " + proposta.toString());
		ServizioFile.saveOnTxtFileWithDate(log,PATH_LOG_FILE);
	}
	
	/**
	 * Metodo per ricavare gli scambi aperti che richiedono o offrono
	 * la prestazione di una categoria specificata
	 * @return lista con gli scambi aperti riguardanti una categoria
	 * @since 4
	 */
	public ArrayList<Proposta> getScambiApertiFoglia(Foglia foglia) {
		return getScambiFoglia(scambiAperti, foglia);
	}

	/**
	 * Metodo per ricavare gli scambi chiusi che richiedono o offrono
	 * la prestazione di una categoria specificata
	 * @return lista con gli scambi chiusi riguardanti una categoria
	 * @since 4
	 */
	public ArrayList<Proposta> getScambiChiusiFoglia(Foglia foglia) {
		return getScambiFoglia(scambiChiusi, foglia);
	}

	/**
	 * Metodo per ricavare gli scambi ritirati che richiedono o offrono
	 * la prestazione di una categoria specificata
	 * @return lista con gli scambi ritirati riguardanti una categoria
	 * @since 4
	 */
	public ArrayList<Proposta> getScambiRitiratiFoglia(Foglia foglia) {
		return getScambiFoglia(scambiRitirati, foglia);
	}
	
	/**
	 * Metodo per ricavare gli scambi proposti riguardanti una categoria specificata
	 * dalla lista specificata
	 * @param scambi la lista da cui ricavare gli scambi
	 * @return lista con gli scambi riguardanti una categoria
	 * @since 4
	 */
	private ArrayList<Proposta> getScambiFoglia(List<Proposta> scambi, Foglia foglia) {
		ArrayList<Proposta> scambiFoglia = new ArrayList<Proposta>();
		for(Proposta p : scambi) {
			if(p.getRichiesta().equals(foglia) || p.getOfferta().equals(foglia))
				scambiFoglia.add(p);
		}
		
		return scambiFoglia;
	}
	
	/**
	 * Metodo per ricavare una lista con i nomi degli scambi completi
	 * @return lista con i nomi degli scambi completi
	 * @since 4
	 */
	public ArrayList<String> getNomiScambiCopleti() {
		ArrayList<String> nomiScambiCompleti = new ArrayList<>();
		for(Scambio s : scambiCompleti) {
			nomiScambiCompleti.add(s.getNome());
		}
		
		return nomiScambiCompleti;
	}
	
	/**
	 * Getter di uno scambio completo dato l'inidic nella lista
	 * @param indexScambio indice dello scambio
	 * @return scambio
	 * @since 4
	 */
	public Scambio getScambioCompleto(int indexScambio) {
		return scambiCompleti.get(indexScambio);
	}
}
