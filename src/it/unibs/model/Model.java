package it.unibs.model;

import it.unibs.domain.Comprensorio;
import it.unibs.domain.Gerarchia;
import it.unibs.domain.NestedMap;
import it.unibs.domain.Proposta;
import it.unibs.domain.Utente;
import it.unibs.main.*;

/**
 * Model del programma
 * Questa classe rappresenta il modello del programma e gestisce i dati principali
 * utilizzati dall'applicazione. Fornisce metodi per l'accesso e la manipolazione
 * dei dati relativi ai comprensori, alle gerarchie e ai fattori di conversione e agli scambi di ore.
 *
 * @see Comprensorio
 * @see Gerarchia
 * @see Proposta
 * @see NestedMap
 * @see Persistence
 * @see JSONParser
 */
public class Model{
	private Persistence persistence;
	private ModelComprensori modelComprensori;
	private ModelGerarchie modelGerarchie ;
	private ModelScambi modelScambi ;
	
	/**
	 * Costruttore della classe Model.
	 * @param persistence l'oggetto Persistence utilizzato per caricare i dati persistenti nei vari model
     */
	public Model(Persistence persistence) {
		this.persistence = persistence;
		this.modelComprensori = new ModelComprensori(persistence);
		this.modelGerarchie = new ModelGerarchie(persistence);
		this.modelScambi = new ModelScambi(persistence, this);
	}
	
	public Persistence getPersistence() { 
		return persistence;
	}
	
	public ModelComprensori getModelComprensori() {
		return modelComprensori;
	}
	
	public ModelGerarchie getModelGerarchie() { 
		return modelGerarchie;
	}
	
	public ModelScambi getModelScambi() { 
		return modelScambi;
	}
	
	/**
	 * Salvataggio di persistence su file
	 * richiama {@link JSONParser#saveDataToJson(Object, String)}
	 * @since 1
	 */
	public void salvaModifiche() {
		JSONParser.saveDataToJson(persistence, Main.PERSISTENCE);
	}

	/**
	 * Set dell'utente che sta usando l'applicativo 
	 * richiama {@link JSONParser#saveDataToJson(Object, String)}
	 * @since 1
	 */
	public void setUser(Utente user) {
		modelScambi.setUser(user);
	}
}