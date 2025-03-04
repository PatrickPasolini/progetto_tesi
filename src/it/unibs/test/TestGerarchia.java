package it.unibs.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.unibs.domain.Foglia;
import it.unibs.domain.Gerarchia;
import it.unibs.domain.NonFoglia;
import it.unibs.exception.FogliaSenzaFigliException;
import it.unibs.main.Persistence;
import it.unibs.model.ModelGerarchie;

class TestGerarchia {

	@Test
	void testAddFogliaToFoglia() throws Exception {
		Foglia f = new Foglia(null, null, null);
		assertThrows(FogliaSenzaFigliException.class, () -> {
			f.addChilds(new Foglia(null,null,null)); });
	}
	
	
	@Test
	void testNewGerarchiaSingolaFoglia() {
		NonFoglia radice = new NonFoglia("radice", "campo", "");
		radice.addDominio("singolaFoglia");
		Gerarchia g = new Gerarchia(radice);
		Foglia foglia = new Foglia("nome foglia","",radice.getNome());
		g.addCategoria(foglia);
		g.addFoglia(foglia);
		radice.addChilds(foglia);
		assertEquals(g.getFoglie().size(), 1);
	}

	@Test
	void testNewGerarchiaSingolaNonFogliaConSingolaFoglia() {
		NonFoglia radice = new NonFoglia("radice", "campo", "");
		radice.addDominio("singolaNonFoglia");
		Gerarchia g = new Gerarchia(radice);
		NonFoglia nonFoglia1 = new NonFoglia("NonFoglia", "campo", "");
		nonFoglia1.addDominio("singolaFoglia");
		g.addCategoria(nonFoglia1);
		radice.addChilds(nonFoglia1);
		
		Foglia foglia = new Foglia("nome foglia","",radice.getNome());
		g.addCategoria(foglia);
		g.addFoglia(foglia);
		nonFoglia1.addChilds(foglia);
		assertEquals(g.getCategorie().size(), 3);
	}
	
	@Test
	void testNewGerarchiaNomeUnivoco() {
		ModelGerarchie model = new ModelGerarchie(new Persistence());
		NonFoglia radice = new NonFoglia("radice", "campo", "");
		radice.addDominio("singolaFoglia");
		//...continuo creazione 
		model.resetNewGerarchia();
		model.addRadice(radice);
		model.addGerarchia();
		
		assertTrue(model.checkNomeRadiceGerarchia("radice"));
	}
	
	@Test
	void testNewGerarchiaCategoriaNomeUnivoco() {
		NonFoglia radice = new NonFoglia("radice", "campo", "");
		radice.addDominio("foglia1");
		Gerarchia g = new Gerarchia(radice);
		
		Foglia foglia1 = new Foglia("foglia1","",radice.getNome());
		g.addCategoria(foglia1);
		g.addFoglia(foglia1);
		radice.addChilds(foglia1);
		
		assertTrue(g.checkNomeCategoria("foglia1"));
	}

	@Test
	void testCalcoloFattoreInverso() {
		ModelGerarchie model = new ModelGerarchie(new Persistence());
		
		NonFoglia radice = new NonFoglia("radice", "campo", "");
		radice.addDominio("foglia1");
		radice.addDominio("foglia2");
		Gerarchia g = new Gerarchia(radice);
		
		Foglia foglia1 = new Foglia("foglia1","",radice.getNome());
		g.addCategoria(foglia1);
		g.addFoglia(foglia1);
		radice.addChilds(foglia1);
		
		Foglia foglia2 = new Foglia("foglia2","",radice.getNome());
		g.addCategoria(foglia2);
		g.addFoglia(foglia2);
		radice.addChilds(foglia2);
		
		double fattore = 2;
		model.calcolaFattoriConversione(foglia1, foglia2, fattore);
		
		assertEquals(model.getMapFattori().get(foglia2, foglia1), 1./fattore);
	}
	@Test
	void testAggiuntaFattoriTreFoglie() {
		ModelGerarchie model = new ModelGerarchie(new Persistence());
		
		NonFoglia radice = new NonFoglia("radice", "campo", "");
		radice.addDominio("foglia1");
		radice.addDominio("foglia2");
		radice.addDominio("foglia3");
		Gerarchia g = new Gerarchia(radice);
		
		Foglia foglia1 = new Foglia("foglia1","",radice.getNome());
		g.addCategoria(foglia1);
		g.addFoglia(foglia1);
		radice.addChilds(foglia1);
		
		Foglia foglia2 = new Foglia("foglia2","",radice.getNome());
		g.addCategoria(foglia2);
		g.addFoglia(foglia2);
		radice.addChilds(foglia2);
		

		double fattore12 = 2;
		model.calcolaFattoriConversione(foglia1, foglia2, fattore12);
		 
		Foglia foglia3 = new Foglia("foglia3","",radice.getNome());
		g.addCategoria(foglia3);
		g.addFoglia(foglia3);
		radice.addChilds(foglia3);
		double fattore32 = 2;
		model.calcolaFattoriConversione(foglia3, foglia2, fattore32);
		
		assertEquals(model.getMapFattori().values().size(), 6);
	}
	
	@Test
	void testCalcoloFattoriTreFoglie() {
		ModelGerarchie model = new ModelGerarchie(new Persistence());
		
		NonFoglia radice = new NonFoglia("radice", "campo", "");
		radice.addDominio("foglia1");
		radice.addDominio("foglia2");
		radice.addDominio("foglia3");
		Gerarchia g = new Gerarchia(radice);
		
		Foglia foglia1 = new Foglia("foglia1","",radice.getNome());
		g.addCategoria(foglia1);
		g.addFoglia(foglia1);
		radice.addChilds(foglia1);
		
		Foglia foglia2 = new Foglia("foglia2","",radice.getNome());
		g.addCategoria(foglia2);
		g.addFoglia(foglia2);
		radice.addChilds(foglia2);
		

		double fattore12 = 2;
		model.calcolaFattoriConversione(foglia1, foglia2, fattore12);
		 
		Foglia foglia3 = new Foglia("foglia3","",radice.getNome());
		g.addCategoria(foglia3);
		g.addFoglia(foglia3);
		radice.addChilds(foglia3);
		double fattore32 = 2;
		model.calcolaFattoriConversione(foglia3, foglia2, fattore32);
		
		assertEquals(model.getMapFattori().get(foglia3,foglia1), 1.);
	}
	
	@Test
	void testLimiteMaxFattore() {
		ModelGerarchie model = new ModelGerarchie(new Persistence());
		
		NonFoglia radice = new NonFoglia("radice", "campo", "");
		radice.addDominio("foglia1");
		radice.addDominio("foglia2");
		radice.addDominio("foglia3");
		Gerarchia g = new Gerarchia(radice);
		
		Foglia foglia1 = new Foglia("foglia1","",radice.getNome());
		g.addCategoria(foglia1);
		g.addFoglia(foglia1);
		radice.addChilds(foglia1);
		
		Foglia foglia2 = new Foglia("foglia2","",radice.getNome());
		g.addCategoria(foglia2);
		g.addFoglia(foglia2);
		radice.addChilds(foglia2);
		

		double fattore21 = 2;
		model.calcolaFattoriConversione(foglia2, foglia1, fattore21);
		 
		Foglia foglia3 = new Foglia("foglia3","",radice.getNome());
		g.addCategoria(foglia3);
		g.addFoglia(foglia3);
		radice.addChilds(foglia3);
		double maxfattore32 = 1;
		assertEquals(model.calcolaFattoreMassimo(model.getMapFattori().get(foglia2)), maxfattore32);
	}
	
	@Test
	void testLimiteMinFattore() {
		ModelGerarchie model = new ModelGerarchie(new Persistence());
		
		NonFoglia radice = new NonFoglia("radice", "campo", "");
		radice.addDominio("foglia1");
		radice.addDominio("foglia2");
		radice.addDominio("foglia3");
		Gerarchia g = new Gerarchia(radice);
		
		Foglia foglia1 = new Foglia("foglia1","",radice.getNome());
		g.addCategoria(foglia1);
		g.addFoglia(foglia1);
		radice.addChilds(foglia1);
		
		Foglia foglia2 = new Foglia("foglia2","",radice.getNome());
		g.addCategoria(foglia2);
		g.addFoglia(foglia2);
		radice.addChilds(foglia2);
		

		double fattore21 = 0.5;
		model.calcolaFattoriConversione(foglia2, foglia1, fattore21);
		 
		Foglia foglia3 = new Foglia("foglia3","",radice.getNome());
		g.addCategoria(foglia3);
		g.addFoglia(foglia3);
		radice.addChilds(foglia3);
		double minfattore32 = 1;
		assertEquals(model.calcolaFattoreMinimo(model.getMapFattori().get(foglia2)), minfattore32);
	}
	
	
}
