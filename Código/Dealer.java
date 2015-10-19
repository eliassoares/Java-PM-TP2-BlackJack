import java.util.Iterator;
import javax.swing.JOptionPane;

public class Dealer extends Jogadores {

	// variável para aplicar singleton

	private static Dealer singDealer;

	private void Dealer() {
		this.setName("Dealer");
	}

	;

	public static Dealer getDealer() {
		if (singDealer == null) {
			singDealer = new Dealer() {
			};
		}
		return singDealer;
	}

	// Usada apenas quando é para imprimir a carta.
	@Override
	public void setCartas(Cartas carta) {
		String s;
		if (carta.getValor() == 0) {
			s = "Rei";
		} else if (carta.getValor() == 1) {
			s = "Ás";
		} else if (carta.getValor() == 11) {
			s = "Valete";
		} else if (carta.getValor() == 12) {
			s = "Rainha";
		} else {
			s = String.valueOf(carta.getValor());
		}
		JOptionPane.showMessageDialog(null, "O Dealer recebeu a carta: " + s
				+ " de " + carta.getNaipe() + ".");
		super.setCartas(carta);
	}

	// Precisa-se de um método que dê carta para o Deale sem mostrar para o
	// jogador qual carta é.
	public void setCarta(Cartas carta) {
		super.setCartas(carta);
	}

	public void opcaoJogador(Baralho deck) {
		while (this.melhorPontuacao() < 17) {
			Cartas carta = deck.getCarta();
			this.setCartas(carta);
		}
	}

	public void mostrarCartas() {
		StringBuilder acumuladorDeStrings = new StringBuilder();
		acumuladorDeStrings.append("As cartas do Dealer são:\n");
		for (Iterator<Cartas> icarta = this.cartas.iterator(); icarta.hasNext();) {
			Cartas carta = (Cartas) icarta.next();
			if (carta.getValor() == 0) {
                            acumuladorDeStrings.append("Rei" + " de ").append(
						carta.getNaipe() + ".\n");
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
                acumuladorDeStrings.append("O Dealer têm " + this.melhorPontuacao()
					+ " pontos.");
		JOptionPane.showConfirmDialog(null, acumuladorDeStrings);
	}
}
