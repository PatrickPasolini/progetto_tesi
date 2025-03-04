package it.unibs.domain;

import java.util.Stack;

/**
 * Classe per gestire la visualizzazione di uno scambio completato,
 * in cui tutte le richieste sono soddisfatte dalle offerte
 */
public class Scambio {
	private String nome;
	private Stack<Proposta> scambio = new Stack<>();

	public Scambio(String nome, Stack<Proposta> scambio) {
		this.nome = nome;
		this.scambio = scambio;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Stack<Proposta> getScambio() {
		return scambio;
	}
	
	public void setScambio(Stack<Proposta> scambio) {
		this.scambio = scambio;
	}
	
}
