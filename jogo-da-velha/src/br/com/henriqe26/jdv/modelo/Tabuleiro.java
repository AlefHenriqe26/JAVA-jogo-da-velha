package br.com.henriqe26.jdv.modelo;

import java.util.ArrayList;
import java.util.List;


public class Tabuleiro {
	private int linhas;
	private int colunas;
	
	//JOGADORES
	private final String player1 = "X";
	private final String player2 = "O";
	
	private int rodada = 1;
	
	private boolean vitoriaPlayer1;
	private boolean vitoriaPlayer2;
	private boolean velha;
	
	private final List<Campo> campos = new ArrayList<>();

	public Tabuleiro(int linhas, int colunas) {
		this.linhas = linhas;
		this.colunas = colunas;
		
		gerarCampos();
	}

	private void gerarCampos() {
		for(int linha = 0; linha < linhas; linha++) {
			for(int coluna = 0; coluna < colunas; coluna++) {
				campos.add(new Campo(linha, coluna));
			}
		}
	}
	
	public void jogada(int linha, int coluna) {
		if(linha >= 0 && linha <= 2 && coluna >= 0 && coluna <= 2) {
			if(rodada % 2 == 1) {
				campos.parallelStream()
				.filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
				.findFirst()
				.ifPresent(c -> setRodada(c.jogadaValida(rodada)));
				verificarVencedor(getPlayer1(), getRodada());
			} else {
				campos.parallelStream()
				.filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
				.findFirst()
				.ifPresent(c -> setRodada(c.jogadaValida(rodada)));
				verificarVencedor(getPlayer2(), getRodada());
			}
		} else {
			if(linha < 0 || linha > 2) {
				System.out.println("Linha inválida, linhas válidas: 0, 1 ou 2");
			}
			if(coluna < 0 || coluna > 2) {
				System.out.println("Coluna inválida, colunas válidas: 0, 1 ou 2");
			}
		}
	}
	
	//Se uma das linhas, colunas ou diagonais forem preenchida com o mesmo simbolo, significa que há vencedor 
		/* * POSIÇÕES CAMPO: *
		 * * * * * * * * * * *
		 * * * * 0 1 2 * * * *
		 * * * * 3 4 5 * * * *
		 * * * * 6 7 8 * * * *
		 * * * * * * * * * * */	
	void verificarVencedor(String simbolo, int rodada) {
		if(campos.get(0).getSimbolo().equalsIgnoreCase(simbolo)
				&& campos.get(1).getSimbolo().equalsIgnoreCase(simbolo)
				&& campos.get(2).getSimbolo().equalsIgnoreCase(simbolo)
				|| campos.get(3).getSimbolo().equalsIgnoreCase(simbolo)
				&& campos.get(4).getSimbolo().equalsIgnoreCase(simbolo)
				&& campos.get(5).getSimbolo().equalsIgnoreCase(simbolo)
				|| campos.get(6).getSimbolo().equalsIgnoreCase(simbolo)
				&& campos.get(7).getSimbolo().equalsIgnoreCase(simbolo)
				&& campos.get(8).getSimbolo().equalsIgnoreCase(simbolo)) {
			if(simbolo.equalsIgnoreCase(getPlayer1())) {
				setVitoriaPlayer1(true);
				System.out.println();
				System.out.println("VITORIA PLAYER 1");
			} else {
				setVitoriaPlayer2(true);
				System.out.println();
				System.out.println("VITORIA PLAYER 2");
			}
		}
		else if(campos.get(0).getSimbolo().equalsIgnoreCase(simbolo)
				&& campos.get(3).getSimbolo().equalsIgnoreCase(simbolo)
				&& campos.get(6).getSimbolo().equalsIgnoreCase(simbolo)
				|| campos.get(1).getSimbolo().equalsIgnoreCase(simbolo)
				&& campos.get(4).getSimbolo().equalsIgnoreCase(simbolo)
				&& campos.get(7).getSimbolo().equalsIgnoreCase(simbolo)
				|| campos.get(2).getSimbolo().equalsIgnoreCase(simbolo)
				&& campos.get(5).getSimbolo().equalsIgnoreCase(simbolo)
				&& campos.get(8).getSimbolo().equalsIgnoreCase(simbolo)) {
			if(simbolo.equalsIgnoreCase(getPlayer1())) {
				setVitoriaPlayer1(true);
				System.out.println();
				System.out.println("VITORIA PLAYER 1");
			} else {
				setVitoriaPlayer2(true);
				System.out.println();
				System.out.println("VITORIA PLAYER 2");
			}
		}
		else if(campos.get(0).getSimbolo().equalsIgnoreCase(simbolo)
					&& campos.get(4).getSimbolo().equalsIgnoreCase(simbolo)
					&& campos.get(8).getSimbolo().equalsIgnoreCase(simbolo)
					|| campos.get(2).getSimbolo().equalsIgnoreCase(simbolo)
					&& campos.get(4).getSimbolo().equalsIgnoreCase(simbolo)
					&& campos.get(6).getSimbolo().equalsIgnoreCase(simbolo)) {
			if(simbolo.equalsIgnoreCase(getPlayer1())) {
				setVitoriaPlayer1(true);
				System.out.println();
				System.out.println("VITORIA PLAYER 1");
			} else {
				setVitoriaPlayer2(true);
				System.out.println();
				System.out.println("VITORIA PLAYER 2");
			}
		}
		else if(rodada > 9 && !isVitoriaPlayer1() && !isVitoriaPlayer2()) {
			setVelha(true);
			System.out.println();
			System.out.println("XII, DEU VELHA");
		}
	}
	
	public boolean objetivoAlcancado() {
		return isVelha() || isVitoriaPlayer1() || isVitoriaPlayer2();
	}
	
	public void reiniciar() {
		campos.stream().forEach(c -> c.reiniciar());
		setRodada(1);
		setVelha(false);
		setVitoriaPlayer1(false);
		setVitoriaPlayer2(false);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("   ");
		int i = 0;
		for(int c = 0; c < colunas; c++) {
			//sb.append("");
			sb.append(c);
			sb.append("   ");
		}
		
		sb.append("\n");
		
		for(int linha = 0; linha < linhas; linha++) {
			sb.append(linha);//0
			sb.append("  ");
			for(int coluna = 0; coluna < colunas; coluna++) {
				if(coluna == 1 || coluna == 2) {
					sb.append(" | ");
				}
				sb.append(campos.get(i));//0, 1
				i++;
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public int getLinhas() {
		return linhas;
	}

	public void setLinhas(int linhas) {
		this.linhas = linhas;
	}

	public int getColunas() {
		return colunas;
	}

	public void setColunas(int colunas) {
		this.colunas = colunas;
	}

	public String getPlayer1() {
		return player1;
	}

	public String getPlayer2() {
		return player2;
	}

	public int getRodada() {
		return rodada;
	}

	public void setRodada(int rodada) {
		this.rodada = rodada;
	}

	public boolean isVitoriaPlayer1() {
		return vitoriaPlayer1;
	}

	public void setVitoriaPlayer1(boolean vitoriaPlayer1) {
		this.vitoriaPlayer1 = vitoriaPlayer1;
	}

	public boolean isVitoriaPlayer2() {
		return vitoriaPlayer2;
	}

	public void setVitoriaPlayer2(boolean vitoriaPlayer2) {
		this.vitoriaPlayer2 = vitoriaPlayer2;
	}

	public boolean isVelha() {
		return velha;
	}

	public void setVelha(boolean velha) {
		this.velha = velha;
	}

	public List<Campo> getCampos() {
		return campos;
	}
}