package br.com.henriqe26.jdv.modelo;

import java.util.ArrayList;
import java.util.List;

public class Campo {
	
	/*FORMA DO CAMPO*/
	private final int linha;
	private final int coluna;
	
	//JOGADORES
	private final String player1 = "X";
	private final String player2 = "O";
	
	/*CARACTERÍSTICA DO CAMPO*/
	private String simbolo = "?";
	
	private boolean campoPreenchido;
	
	private List<Campo> campos = new ArrayList<>();
	
	Campo(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}
	
	int jogadaValida(int rodada) {//1
		if(rodada % 2 == 1 && !campoPreenchido) {
			simbolo = getPlayer1();
			campoPreenchido = true;
			rodada++;
			return rodada;
		} else if (rodada % 2 == 0 && !campoPreenchido) {
			simbolo = getPlayer2();
			campoPreenchido = true;
			rodada++;
			return rodada;
		} else {
			System.out.println("\nEste campo já foi preenchido, por favor, escolha um outro: ");
			return rodada;
		}
	}
	
	void reiniciar() {
		simbolo = "?";
		setCampoPreenchido(false);
	}
	
	public String toString() {
		return simbolo;
	}

	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}

	public String getPlayer1() {
		return player1;
	}

	public String getPlayer2() {
		return player2;
	}

	public List<Campo> getCampos() {
		return campos;
	}

	public void setCampos(List<Campo> campos) {
		this.campos = campos;
	}

	public boolean isJogadaFeita() {
		return campoPreenchido;
	}

	public void setJogadaFeita(boolean jogadaFeita) {
		this.campoPreenchido = jogadaFeita;
	}

	public String getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

	public boolean isCampoPreenchido() {
		return campoPreenchido;
	}

	public void setCampoPreenchido(boolean campoPreenchido) {
		this.campoPreenchido = campoPreenchido;
	}
}