package it.unibs.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import it.unibs.domain.*;
import it.unibs.main.Persistence;
import it.unibs.model.Model;
import it.unibs.model.ModelGerarchie;
import it.unibs.model.ModelScambi;

class TestProposteDiScambio {

	Model model = new Model(new Persistence());
	ModelScambi modelScambi = model.getModelScambi();
	ModelGerarchie modelGerarchie = model.getModelGerarchie();
	
	@Test
	void testAggiuntaNuovaProposta() {
		Foglia foglia1 = new Foglia("foglia1","","radice");
		Foglia foglia2 = new Foglia("foglia2","","radice");
		double fattore12 = 2;
		modelGerarchie.calcolaFattoriConversione(foglia1, foglia2, fattore12);
		Foglia foglia3 = new Foglia("foglia3","","radice");
		double fattore32 = 2;
		modelGerarchie.calcolaFattoriConversione(foglia3, foglia2, fattore32);
		
		String nome = "mario"; String password = "rossi";
		List<String> listaComuni = new ArrayList<>();
		Fruitore fruitore = new Fruitore(nome, password,"mail@mail", new Comprensorio("c",listaComuni));
		HashMap<String, Utente> listUtenti = new HashMap<>();
		listUtenti.put(nome, fruitore);
		
		Proposta proposta1 = new Proposta(foglia1, foglia2, 2, 
				modelScambi.calcolaOreDaFattore(foglia1, foglia2, 2),
					fruitore);
		
		modelScambi.addScambio(proposta1);
		assertEquals(modelScambi.getScambiApertiFoglia(foglia1).size(), 1);
	}
	
	@Test
	void testCalcoloOreOfferta() {
		Foglia foglia1 = new Foglia("foglia1","","radice");
		Foglia foglia2 = new Foglia("foglia2","","radice");
		double fattore12 = 2;
		modelGerarchie.calcolaFattoriConversione(foglia1, foglia2, fattore12);
		int oreOfferta = modelScambi.calcolaOreDaFattore(foglia1, foglia2, 2);
		assertEquals(oreOfferta, 4);
	}
	
	@Test
	void testCalcoloOreOffertaConArrotondamento() {
		Foglia foglia1 = new Foglia("foglia1","","radice");
		Foglia foglia2 = new Foglia("foglia2","","radice");
		double fattore12 = 1.3;
		modelGerarchie.calcolaFattoriConversione(foglia1, foglia2, fattore12);
		int oreOfferta = modelScambi.calcolaOreDaFattore(foglia1, foglia2, 2);
		assertEquals(oreOfferta, 3);
	}
	
	@Test
	void testPropostaCompletata2Proposte() {
		Foglia foglia1 = new Foglia("foglia1","","radice");
		Foglia foglia2 = new Foglia("foglia2","","radice");
		
		String nome1 = "mario"; String password1 = "rossi";
		String nome2 = "luca"; String password2 = "bianchi";
		List<String> listaComuni = new ArrayList<>();
		Comprensorio c = new Comprensorio("c",listaComuni);
		Fruitore fruitore1 = new Fruitore(nome1, password1,"mail@mail", c);
		Fruitore fruitore2 = new Fruitore(nome2, password2,"mail@mail", c);
		
		Proposta proposta1 = new Proposta(foglia1, foglia2, 2,4,fruitore1);
		Proposta proposta2 = new Proposta(foglia2, foglia1, 4,2,fruitore2);
		
		modelScambi.addScambio(proposta1);
		modelScambi.addScambio(proposta2);
		modelScambi.realizzaScambio(proposta2);
		assertEquals(modelScambi.getScambiCompleti().size(), 1);
	}
	
	
	
	@Test
	void testPropostaCompletata4Proposte() {
		Foglia foglia1 = new Foglia("foglia1","","radice");
		Foglia foglia2 = new Foglia("foglia2","","radice");
		Foglia foglia3 = new Foglia("foglia3","","radice");
		Foglia foglia4 = new Foglia("foglia4","","radice");
		
		List<String> listaComuni = new ArrayList<>();
		Comprensorio c = new Comprensorio("c",listaComuni);
		Fruitore fruitore1 = new Fruitore("nome1", "password","mail@mail", c);
		Fruitore fruitore2 = new Fruitore("nome2", "password","mail@mail", c);
		Fruitore fruitore3 = new Fruitore("nome3", "password","mail@mail", c);
		Fruitore fruitore4 = new Fruitore("nome4", "password","mail@mail", c);
		
		Proposta proposta1 = new Proposta(foglia1, foglia2, 1,2,fruitore1);
		Proposta proposta2 = new Proposta(foglia2, foglia3, 2,3,fruitore2);
		Proposta proposta3 = new Proposta(foglia3, foglia4, 3,4,fruitore3);
		Proposta proposta4 = new Proposta(foglia4, foglia1, 4,1,fruitore4);
		
		modelScambi.addScambio(proposta1);
		modelScambi.addScambio(proposta2);
		modelScambi.addScambio(proposta3);
		modelScambi.addScambio(proposta4);
		modelScambi.realizzaScambio(proposta4);
		assertEquals(modelScambi.getScambiCompleti().size(), 1);
	}
	@Test
	void testProposteChiuse() {
		Foglia foglia1 = new Foglia("foglia1","","radice");
		Foglia foglia2 = new Foglia("foglia2","","radice");
		
		String nome1 = "mario"; String password1 = "rossi";
		String nome2 = "luca"; String password2 = "bianchi";
		List<String> listaComuni = new ArrayList<>();
		Comprensorio c = new Comprensorio("c",listaComuni);
		Fruitore fruitore1 = new Fruitore(nome1, password1,"mail@mail", c);
		Fruitore fruitore2 = new Fruitore(nome2, password2,"mail@mail", c);
		
		Proposta proposta1 = new Proposta(foglia1, foglia2, 2,4,fruitore1);
		Proposta proposta2 = new Proposta(foglia2, foglia1, 4,2,fruitore2);
		
		modelScambi.addScambio(proposta1);
		modelScambi.addScambio(proposta2);
		modelScambi.realizzaScambio(proposta2);
		assertEquals(modelScambi.getScambiChiusiFoglia(foglia2).size(), 2);
	}
	
	@Test
	void testProposteRitirate() {
		Foglia foglia1 = new Foglia("foglia1","","radice");
		Foglia foglia2 = new Foglia("foglia2","","radice");
		
		String nome1 = "mario"; String password1 = "rossi";
		String nome2 = "luca"; String password2 = "bianchi";
		List<String> listaComuni = new ArrayList<>();
		Comprensorio c = new Comprensorio("c",listaComuni);
		Fruitore fruitore1 = new Fruitore(nome1, password1,"mail@mail", c);
		Fruitore fruitore2 = new Fruitore(nome2, password2,"mail@mail", c);
		
		Proposta proposta1 = new Proposta(foglia1, foglia2, 2,4,fruitore1);
		Proposta proposta2 = new Proposta(foglia2, foglia1, 4,2,fruitore2);
		
		modelScambi.addScambio(proposta1);
		modelScambi.addScambio(proposta2);
		modelScambi.realizzaScambio(proposta2);
		modelScambi.ritiraScambioAperto(proposta2);
		assertEquals(modelScambi.getScambiRitiratiFoglia(foglia2).size(), 1);
	}

}
