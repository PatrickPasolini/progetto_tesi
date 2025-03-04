package it.unibs.test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.jupiter.api.Test;
import it.unibs.domain.*;
import it.unibs.main.Persistence;
import it.unibs.model.Model;
import it.unibs.model.ModelScambi;

/**
 *	Questa classe rappresenta un text whiteBox 
 *	TEST DEL METODO: cercaScambio(Stack<Proposta> scambio, Proposta newProposta) 
 *	NELLA CLASSE: modelScambi
 *
 */
class TestMatchScambi_WhiteBox {

	/**
	 * Nessuna proposta nel comprensorio, dovrebbe restituire false  
	 * cioe' il seguente array e' vuoto
	 * ArrayList<Proposta> proposteComprensorio = getProposteComprensorio(newProposta);
	 * 
	 * RAMO TESTATO:
	 * if(proposteComprensorio.isEmpty())	
	 * 		return false;
	 */
	  @Test
	    public void testCercaScambio_noProposteComprensorio() {
		  	Model model = new Model(new Persistence());
			ModelScambi modelScambi = model.getModelScambi();
			Stack<Proposta> scambio = new Stack<>();
	        
	        Foglia foglia1 = new Foglia("foglia1", "", "radice");
	        Foglia foglia2 = new Foglia("foglia2", "", "radice");
	        
	        Comprensorio comprensorioVuoto = new Comprensorio("c", new ArrayList<>()); 
	        Fruitore fruitore1 = new Fruitore("Mario", "Rossi", "mario@rossi.it", comprensorioVuoto);
	        
	        Proposta proposta = new Proposta(foglia1, foglia2, 2, 4, fruitore1);
	        boolean result = modelScambi.cercaScambio(scambio, proposta);
	        
	        assertFalse(result);  
	  	}
	 
	  /**
	   * Nessuna proposta che soddisfa la richiesta, dovrebbe restituire false
	   * cioe' il seguente array e' vuoto
	   * ArrayList<Proposta> soddisfaRichiesta = getRichiesteSoddisfatte(proposteComprensorio, newProposta);
	   * 
	   * RAMO TESTATO:
	   * if(soddisfaRichiesta.isEmpty())	
	   * 	return false;
	   */
	  @Test
	    public void testCercaScambio_noSoddisfaRichiesta() {
		  	Model model = new Model(new Persistence());
			ModelScambi modelScambi = model.getModelScambi();
	        Stack<Proposta> scambio = new Stack<>();
	        
	        Foglia foglia1 = new Foglia("foglia1", "", "radice");
	        Foglia foglia2 = new Foglia("foglia2", "", "radice");
	        
	        Comprensorio comprensorioVuoto = new Comprensorio("c", new ArrayList<>()); 
	        Fruitore fruitore1 = new Fruitore("Mario", "Rossi", "mario@rossi.it", comprensorioVuoto);
	        
			Proposta proposta1 = new Proposta(foglia1,foglia2,2,4,fruitore1);
			
			//aggiunta proposta che non soddisfa proposta1
			Proposta propostaNonSoddisfacente = new Proposta(foglia2, foglia1, 10, 5, fruitore1);
			modelScambi.addScambio(propostaNonSoddisfacente);
	        
	        boolean result = modelScambi.cercaScambio(scambio, proposta1);
	        assertFalse(result);
	  }
	  

		
	  /**
	   * proposta che soddisfa la richiesta, dovrebbe restituire true
	   * cioe' il seguente array e' contiene proposta2 che soddisfa proposta1
	   * ArrayList<Proposta> soddisfaRichiesta = getRichiesteSoddisfatte(proposteComprensorio, newProposta);
	   * 
	   * RAMO TESTATO:
	   * if(controllaScambio(soddisfaRichiesta, newProposta, scambio))
	   * 	return true;
	   */
	  @Test
	    public void testCercaScambio_controllaScambioTrue() {
			Model model = new Model(new Persistence());
			ModelScambi modelScambi = model.getModelScambi();
			Stack<Proposta> scambio = new Stack<>();
	        
	        Foglia foglia1 = new Foglia("foglia1", "", "radice");
	        Foglia foglia2 = new Foglia("foglia2", "", "radice");
	        List<String> listaComuni = new ArrayList<>();
	        Comprensorio comprensorio = new Comprensorio("c", listaComuni);
	        Fruitore fruitore1 = new Fruitore("Mario", "Rossi", "mario@rossi.it", comprensorio);
	        Fruitore fruitore2 = new Fruitore("Luigi", "Rossi", "mario@rossi.it", comprensorio);
	        
	        Proposta proposta1 = new Proposta(foglia1, foglia2, 2, 4, fruitore1);
	        Proposta proposta2 = new Proposta(foglia2, foglia1, 4, 2, fruitore2);// soddisfa proposta1
	        modelScambi.addScambio(proposta1);
	        boolean result = modelScambi.cercaScambio(scambio, proposta2);
	        assertTrue(result);
	    }
	  
	  /**
	   * cercaScambio() dovrebbe restituire false perche' le due proposte 
	   * non combaciano solo per una foglia
	   */
	  @Test
	    public void testCercaScambio_noSoddisfaOfferta_foglieNonCombaciano() {
		  Model model = new Model(new Persistence());
			ModelScambi modelScambi = model.getModelScambi();
			Stack<Proposta> scambio = new Stack<>();
	        
	        Foglia foglia1 = new Foglia("foglia1", "", "radice");
	        Foglia foglia2 = new Foglia("foglia2", "", "radice");
	        Foglia foglia3 = new Foglia("foglia3", "", "radice");
	        List<String> listaComuni = new ArrayList<>();
	        Comprensorio comprensorio = new Comprensorio("c", listaComuni);
	        Fruitore fruitore1 = new Fruitore("Mario", "Rossi", "mario@rossi.it", comprensorio);
	        Fruitore fruitore2 = new Fruitore("Luigi", "Rossi", "mario@rossi.it", comprensorio);
	        
	        Proposta proposta1 = new Proposta(foglia1, foglia2, 2, 4, fruitore1);
	        Proposta proposta2 = new Proposta(foglia3, foglia1, 4, 2, fruitore2);// soddisfa proposta1
	        modelScambi.addScambio(proposta1);
	        boolean result = modelScambi.cercaScambio(scambio, proposta2);
	        assertFalse(result);
	        
	  	}
	  
	  /**
	   * cercaScambio() dovrebbe restituire false perche' le due proposte 
	   * non combaciano solo perche' oreRichieste di proposta2  != oreOfferte di proposta1
	   */
	  @Test
	    public void testCercaScambio_noSoddisfaOfferta_foglieCombaciano_oreNonCombaciano() {
		  	Model model = new Model(new Persistence());
			ModelScambi modelScambi = model.getModelScambi();
			Stack<Proposta> scambio = new Stack<>();
	        
	        Foglia foglia1 = new Foglia("foglia1", "", "radice");
	        Foglia foglia2 = new Foglia("foglia2", "", "radice");
	        List<String> listaComuni = new ArrayList<>();
	        Comprensorio comprensorio = new Comprensorio("c", listaComuni);
	        Fruitore fruitore1 = new Fruitore("Mario", "Rossi", "mario@rossi.it", comprensorio);
	        Fruitore fruitore2 = new Fruitore("Luigi", "Rossi", "mario@rossi.it", comprensorio);
	        
	        Proposta proposta1 = new Proposta(foglia1, foglia2, 2, 4, fruitore1);
	        Proposta proposta2 = new Proposta(foglia2, foglia1, 10, 2, fruitore2);//NON soddisfa proposta1
	        modelScambi.addScambio(proposta1);
	        boolean result = modelScambi.cercaScambio(scambio, proposta2);
	        assertFalse(result);
	        
	  	}
	  
	  /**
	   * cercaScambio() dovrebbe restituire false perche' le due proposte 
	   * non combaciano solo perche' i due Fruitori appartengono a Comprensori diversi
	   */
	  @Test
	    public void testCercaScambio_noSoddisfaOfferta_FruitoriComprensoriDiversi() {
		  	Model model = new Model(new Persistence());
			ModelScambi modelScambi = model.getModelScambi();
			Stack<Proposta> scambio = new Stack<>();
	        
	        Foglia foglia1 = new Foglia("foglia1", "", "radice");
	        Foglia foglia2 = new Foglia("foglia2", "", "radice");
	        List<String> listaComuni = new ArrayList<>();
	        Comprensorio comprensorio1 = new Comprensorio("c1", listaComuni);
	        Comprensorio comprensorio2 = new Comprensorio("c2", listaComuni);
	        Fruitore fruitore1 = new Fruitore("Mario", "Rossi", "mario@rossi.it", comprensorio1);
	        Fruitore fruitore2 = new Fruitore("Luigi", "Rossi", "mario@rossi.it", comprensorio2);
	        
	        Proposta proposta1 = new Proposta(foglia1, foglia2, 2, 4, fruitore1);
	        Proposta proposta2 = new Proposta(foglia2, foglia1, 4, 2, fruitore2);//NON soddisfa proposta1
	        modelScambi.addScambio(proposta1);
	        boolean result = modelScambi.cercaScambio(scambio, proposta2);
	        assertFalse(result);
	        
	  	}
	  
	  
	  @Test
	    public void testCercaScambio_ScambioTrueConTreProposte() {
		  Model model = new Model(new Persistence());
			ModelScambi modelScambi = model.getModelScambi();
			Stack<Proposta> scambio = new Stack<>();
	        
	        Foglia foglia1 = new Foglia("foglia1", "", "radice");
	        Foglia foglia2 = new Foglia("foglia2", "", "radice");
	        Foglia foglia3 = new Foglia("foglia3", "", "radice");
	        
	        List<String> listaComuni = new ArrayList<>();
	        Comprensorio comprensorio = new Comprensorio("c", listaComuni);
	        
	        Fruitore fruitore1 = new Fruitore("Mario", "Rossi", "mario@rossi.it", comprensorio);
	        Fruitore fruitore2 = new Fruitore("Luigi", "Rossi", "mario@rossi.it", comprensorio);
	        Fruitore fruitore3 = new Fruitore("Anna", "Blue", "mario@rossi.it", comprensorio);
	        
	        Proposta proposta1 = new Proposta(foglia1, foglia2, 2, 4, fruitore1);
	        Proposta proposta2 = new Proposta(foglia2, foglia3, 4, 2, fruitore2);
	        Proposta proposta3 = new Proposta(foglia3, foglia1, 2, 2, fruitore3);
	        
	        modelScambi.addScambio(proposta1);
	        modelScambi.addScambio(proposta2);
	        boolean result = modelScambi.cercaScambio(scambio, proposta3);
	        assertTrue(result);
	    }
	  
	  @Test
	    public void testCercaScambio_ScambioTrueConQuattroProposte() {
		  Model model = new Model(new Persistence());
			ModelScambi modelScambi = model.getModelScambi();
			Stack<Proposta> scambio = new Stack<>();
	        
	        Foglia foglia1 = new Foglia("foglia1", "", "radice");
	        Foglia foglia2 = new Foglia("foglia2", "", "radice");
	        Foglia foglia3 = new Foglia("foglia3", "", "radice");
	        Foglia foglia4 = new Foglia("foglia4", "", "radice");
	        
	        List<String> listaComuni = new ArrayList<>();
	        Comprensorio comprensorio = new Comprensorio("c", listaComuni);
	        
	        Fruitore fruitore1 = new Fruitore("Mario", "Rossi", "mario@rossi.it", comprensorio);
	        Fruitore fruitore2 = new Fruitore("Luigi", "Rossi", "mario@rossi.it", comprensorio);
	        Fruitore fruitore3 = new Fruitore("Anna", "Blue", "mario@rossi.it", comprensorio);
	        Fruitore fruitore4 = new Fruitore("Beatrice", "Bianchi", "mario@rossi.it", comprensorio);
	        
	        Proposta proposta1 = new Proposta(foglia1, foglia2, 2, 4, fruitore1);
	        Proposta proposta2 = new Proposta(foglia2, foglia3, 4, 2, fruitore2);
	        Proposta proposta3 = new Proposta(foglia3, foglia4, 2, 4, fruitore3);
	        Proposta proposta4 = new Proposta(foglia4, foglia1, 4, 2, fruitore4);
	        
	        modelScambi.addScambio(proposta1);
	        modelScambi.addScambio(proposta2);
	        modelScambi.addScambio(proposta3);
	        boolean result = modelScambi.cercaScambio(scambio, proposta4);
	        assertTrue(result);
	    }
	  
}
