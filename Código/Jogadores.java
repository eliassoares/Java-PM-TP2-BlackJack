import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

public abstract class Jogadores {

	private String name;
	protected ArrayList<Cartas> cartas = new ArrayList<Cartas>();
	// Para saber se já tem um ás, importante na contagem de pontos.
	private boolean temUmAs;
	// Pontos que o jogador tem com suas cartas.
	private int[] score = new int[2];

	public void Jogadores() {

	};

	// Será implementado em cada subclasse de jogadores. Infelizmente terei que
	// passar o deck com parâmetro.
	// Assim o jogador pode ganhar uma carta dentro da sua própria classe.
	public void opcaoJogador(Baralho deck) {
	};

	protected void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	// Funções a cerca de pontuação:
	private void setScore() {
		// Toda vez que entrarmos aqui teremos que refazer a contagem dos
		// pontos.
		this.score[0] = 0;
		this.score[1] = 0;
                this.temUmAs = false;
		for (Iterator<Cartas> iterator = this.cartas.iterator(); iterator
				.hasNext();) {
			Cartas carta = (Cartas) iterator.next();

			// Meu primeiro ás. Ele pode valer 1 ou 11, vai depender da
			// pontuação.
			if ((!this.temUmAs) && (carta.getValorPontos() == 1)) {
				this.temUmAs = true;
				this.score[0]++;
				// Em score[0] já foi somado 1, para termos uma soma de 11no
				// score[1], somamos mais 10.
				this.score[1] = this.score[0] + 10;
			}
			// Eu já tenho um ás e recebi outro ás.
			else if ((this.temUmAs) && (carta.getValorPontos() == 1)) {
				// Os dois áses não podem valer 11 pontos, pontanto vale um.
				this.score[0]++;
				this.score[1]++;
			}
			// Tenho um ás e recebi outra carta diferente do ás.
			else if (this.temUmAs) {
				this.score[0] += carta.getValorPontos();
				this.score[1] += carta.getValorPontos();
			}
			// Se não tenho ás, considero apenas a primeira posição do vetor.
			else {
				this.score[0] += carta.getValorPontos();
			}

		}
	}

	// Retorna a pontuação atual do jogador.
	public int[] getScore() {
		return this.score;
	}

	public void limparmao() {
		this.cartas.clear();
                this.score[0] = 0;
                this.score[1] = 0;
	}

	// Mostra todas as cartas na mão do jogador.
	public void mostrarCartas() {
		// Vou concatenar as informações numa string e mandar imprimir tudo de
		// uma vez
		// Com o JOptionPane.

		// Vai armazeras várias strings.
		StringBuilder acumuladorDeStrings = new StringBuilder();

		// Quero mostrar o nome do jogador.
		if (this.name.equals("Dealer")) {
			acumuladorDeStrings.append("As cartas do Dealer são:\n");
		} else {
			acumuladorDeStrings.append("Suas cartas são:\n");
		}

		for (Iterator<Cartas> icarta = this.cartas.iterator(); icarta.hasNext();) {
			Cartas carta = (Cartas) icarta.next();
			if (carta.getValor() == 0) {

			} else if (carta.getValor() == 1) {
				acumuladorDeStrings.append("Ás" + " de ").append(
						carta.getNaipe() + ".\n");
			} else if (carta.getValor() == 11) {
				acumuladorDeStrings.append("Valete" + " de ").append(
						carta.getNaipe() + ".\n");
			} else if (carta.getValor() == 12) {
				acumuladorDeStrings.append("Rainha" + " de ").append(
						carta.getNaipe() + ".\n");
			} else {
				acumuladorDeStrings.append(carta.getValor() + " de ").append(
						carta.getNaipe() + ".\n");
			}
		}

		if (this.name.equals("Dealer")) {
			acumuladorDeStrings.append("O Dealer têm " + this.melhorPontuacao()
					+ " pontos.");
		} else {
			acumuladorDeStrings.append("Sua melhor pontuação é "
					+ this.melhorPontuacao() + " pontos.");
		}

		JOptionPane.showConfirmDialog(null, acumuladorDeStrings);
	}

	// Como há duas possíveis pontuações, esse método retorna a melhor pontuação
	// do jogador.
	public int melhorPontuacao() {
		// A pontuação que guarda ás igual a 11 estourou.
		if ((this.score[1] > 21) && (this.temUmAs)) {
			return this.score[0];
		} else if (this.temUmAs) { // Tem um ás e não estourou
			return this.score[1];
		} else { // Não tem ás.
			return this.score[0];
		}
	}

	// Quando o jogador recebe suas cartas, já vamos colocar a pontuação.
	public void setCartas(Cartas carta) {
		this.cartas.add(carta);
		this.setScore();
	}

}