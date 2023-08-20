 package br.com.henriqe26.jdv.visao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import br.com.henriqe26.jdv.modelo.Tabuleiro;

public class TabuleiroConsole {
	
	private Tabuleiro tabuleiro;
	private Scanner entrada = new Scanner(System.in);
	
	public TabuleiroConsole(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		
		executarJogo();
	}

	private void executarJogo() {
		try {
			boolean continuar = true;
			
			while(continuar) {
				cicloDoJogo();
				System.out.println("Quer iniciar outra partida? (S/n)");
				String resposta = entrada.nextLine();
			
				if("n".equalsIgnoreCase(resposta)) {
					continuar = false;
				} else {
					tabuleiro.reiniciar();
				}
			}
		} catch (Exception e) {
			System.out.println("Tchau!");
		} finally {
			entrada.close();
		}
		
	}

	private void cicloDoJogo() {
		try {
			while (!tabuleiro.objetivoAlcancado()) {
				System.out.println("\n***********************************");
				System.out.println("Rodada " + tabuleiro.getRodada());
				System.out.println();
				
				if(tabuleiro.getRodada() % 2 == 1) {
					System.out.println("É a vez do jogador 1");
				} else {
					System.out.println("É a vez do jogador 2");
				}
				
				System.out.println(tabuleiro);
				
				String digitado = capturarValorDigitado("Digite a (linha), (coluna): ");
				Iterator<Integer> lc = Arrays.stream(digitado.split(","))
						.map(e -> Integer.parseInt(e.trim()))
						.iterator();
				//GERAR O VALOR DIGITADO INTEIRO
				tabuleiro.jogada(lc.next(), lc.next());
			}
			System.out.println(tabuleiro);
		} catch (Exception e2) {
			System.out.println();
			System.out.println("OCORREU UM ERRO: VALOR DIGITADO INVÁLIDO!");
			System.out.println();
		}
	}

	private String capturarValorDigitado(String texto) throws Exception {
		System.out.println(texto);
		String digitado = entrada.nextLine();
		
		return digitado;
	}
}