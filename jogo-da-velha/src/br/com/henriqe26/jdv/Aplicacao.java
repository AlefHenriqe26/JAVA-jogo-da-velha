package br.com.henriqe26.jdv;

import br.com.henriqe26.jdv.modelo.Tabuleiro;
import br.com.henriqe26.jdv.visao.TabuleiroConsole;

public class Aplicacao {
	public static void main(String[] args) {
		
		Tabuleiro tabuleiro = new Tabuleiro(3, 3);
		new TabuleiroConsole(tabuleiro);
	}
}