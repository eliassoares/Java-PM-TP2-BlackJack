public class Cartas {

	/*
	 * Numeração conrrespondente das cartas: São 52 cartas, cada carta terá uma
	 * numeração de 1 a 52, tirando o resto da divisão por 13, teremos o valor
	 * da carta indo de 0 a 12, segue abaixo a sequência: 0: Rei, 1: As, 2 a 10:
	 * valores correspondentes, 11: Valete, 12: Rainha. Quanto aos naipes, basta
	 * pegar o resto do número da carta por 4, os valores do restos
	 * correspondendo com os naipes seguem abaixo: 0: Paus, 1: Copas, 2: Ouros,
	 * 3: Espadas. Portanto, uma carta com numeração 51 será: 51 mod13 = 12,
	 * onde 12 corresponde a rainha, 51 mod4 = 3, correnpondendo ao naipe
	 * Espadas, então a carta com valor 51 será uma Rainha de Espadas.
	 */

	private String naipe;

	// Esse valor se refere se é ás, 2, 3 ... 13
	private int valor;
	// Esse valor se refere a quantos pontos a carta dar.
	private int valorPontos;

	// Recebe a numeração da carta e faz a conversão para o naipe e valor
	// correspondente
	// conforme as regras explicadas no cabeçalho da classe.
	public Cartas(int numeracaoDaCarta) {
		setNaipe(numeracaoDaCarta);
		setValor(numeracaoDaCarta);
		setValorPontos(this.valor);
	};

	private void setNaipe(int numeracaoDaCarta) {
		int numeroNaipe = numeracaoDaCarta % 4;
		switch (numeroNaipe) {
		case 0:
			this.naipe = "♣";
			break;
		case 1:
			this.naipe = "♥";
			break;
		case 2:
			this.naipe = "♦";
			break;
		case 3:
			this.naipe = "♠";
			break;
		default: {
			System.out.println("Valor não existe para o naipe!!!");
			System.exit(0);
		}
		}
	}

	String getNaipe() {
		return this.naipe;
	}

	private void setValor(int numeracaoDaCarta) {
		// Fazendo o tratamento para que só seja inserido uma numeração
		// adequada,
		// pois, apartir daqui vamos supor que valor da carta será sempre
		// correto.
		if ((numeracaoDaCarta > 52) || (numeracaoDaCarta < 1)) {
			System.out.println("A numeração inserida não está entre 1 e 52!!!");
			System.exit(8);
		}
		this.valor = numeracaoDaCarta % 13;
	}

	int getValor() {
		return this.valor;
	}

	public void setValorPontos(int valor) {

		// Cartas de Ás ao 10, o Ás pode ter dois valores distintos, isso será
		// tratado posteriormente.
		if ((valor > 0) && (valor < 11)) {
			this.valorPontos = valor;
		} else {
			// Para cartas 0, 11 e 12. Que são respectivamente:
			// Rei, Valete e Rainha.
			this.valorPontos = 10;
		}
	}

	public int getValorPontos() {
		return this.valorPontos;
	}

	// Retorna o valor de uma carta no formato "valor +" de "+naipe"
	public void writeCard() {
		if (this.valor > 1 && this.valor < 11) {
			System.out.println(this.valor + " de " + this.naipe);
		} else {
			System.out.println("As de " + this.naipe);
		}
	}
	
	// Para debugação.
	public void mostrarCarta(){
		if(this.valor == 0) {
			System.out.println("Rei de "+this.naipe+".");
		} else if(this.valor == 11) {
			System.out.println("Valete de "+this.naipe+".");
		} else if(this.valor == 12) {
			System.out.println("Rainha de "+this.naipe+".");
		}else if(this.valor == 1) {
			System.out.println("Ás de "+this.naipe+".");
		} else {
			System.out.println(this.valor+" de "+this.naipe+".");
		}
	}
}
