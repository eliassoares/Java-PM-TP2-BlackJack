import java.util.Iterator;

import javax.swing.JOptionPane;

public class Player extends Jogadores {

    private int valorAposta;
    private int money;

    public Player(String name) {
        this.setName(name);
        // 1000 é o valor inicial de aposta.
        this.money = 1000;
    }

    ;

	// Funções a cerca de aposta:

	// Se a pessoa ganhar, valor será acrescentado ao seu dinheiro.
	public void setDinheiro(int valor) {
        this.money += valor;
    }

    public int getDinheiro() {
        return this.money;
    }

    // Quando o jogador entra com a aposta, o valor da aposta será decrementado
    // do dinheiro.
    public void setAposta(int aposta) {
        this.valorAposta = aposta;
        this.setDinheiro(-aposta);
    }

    public int getAposta() {
        return this.valorAposta;
    }

    // Foi criado apenas para mostrar a mensagem abaixo para o jogador humano.
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
        JOptionPane.showMessageDialog(
                null,
                "Você recebeu a carta: " + s + " de "
                + carta.getNaipe() + ".");
        super.setCartas(carta);
    }

    // É o método que dar as opções de jogada para o jogador.
    public void opcaoJogador(Baralho deck) {
        boolean isDobrouAposta = false;
        boolean podeDobrar = (this.getDinheiro() >= this.getAposta());
        String s;
        if (this.getScore()[1] == 0) {
            JOptionPane.showMessageDialog(null, "Você tem " + this.getScore()[0] + " pontos, " + this.getName() + ".");
        } else {
            JOptionPane.showMessageDialog(null, "Você tem " + this.getScore()[0] + "/" + this.getScore()[1] + " pontos, " + this.getName() + ".");
        }

        // Se fez 21 com apenas duas cartas, fez blackjack.
        if (this.getScore()[1] == 21) {
            this.mostrarCartas();
            JOptionPane.showMessageDialog(null,
                    "Parabéns!!! Você fez um Blackjack");
            // Se o jogador fez Blackjack, ele não precisa escolher nenhuma opção abaixo..
            this.setDinheiro(this.valorAposta);
            return;
        }

        int opcao = 0;
        while ((opcao != 3) && (this.melhorPontuacao() < 22)) {
            if((isDobrouAposta) || (! podeDobrar) || (this.melhorPontuacao() == 21)){
                s = JOptionPane.showInputDialog("Digite:\n1 pegar outra carta.\n3 deixar o Dealer jogar.\n4 mostrar suas cartas\nQualquer outra coisa para sair do jogo.");
            }else {
                s = JOptionPane.showInputDialog("Digite:\n1 pegar outra carta.\n2 dobrar aposta, deve-se ter menos que 21 pontos.\n3 deixar o Dealer jogar.\n4 mostrar suas cartas\nQualquer outra coisa para sair do jogo.");
            }
            opcao = Integer.parseInt(s);
            if (opcao == 1) {
                Cartas carta = deck.getCarta();
                this.setCartas(carta);
                // Se nenhuma das duas pontuções tenha estourado, mostro as duas.
                if ((this.getScore()[0] < 21) && (this.getScore()[1] < 21) && (this.getScore()[1] != 0)) {
                    JOptionPane.showMessageDialog(null, "Sua nova pontuação é "
                            + this.getScore()[0] + "/" + this.getScore()[1] + " pontos.");
                } else { // Mostro a melhor.
                    JOptionPane.showMessageDialog(null, "Sua nova pontuação é "
                            + this.melhorPontuacao() + " pontos.");
                }

                this.mostrarCartas();
            } else if ((((opcao == 2) && (this.melhorPontuacao() < 21)) && (! isDobrouAposta)) && (podeDobrar)) {
                this.setAposta(2 * this.valorAposta);
                // Já tirei 3 * this.aposta, quero tirar apenas 2 * this.aposta, então somo.
                this.setDinheiro(this.valorAposta/2);
                JOptionPane.showMessageDialog(null,"Você dobrou a aposta!!! Seu novo saldo de dinheiro é " + this.money + ".");
                isDobrouAposta = true;
            } else if ((opcao == 3) || ((opcao == 2) && (isDobrouAposta))) {
                JOptionPane.showMessageDialog(null, "Passando jogada para o Dealer!");
                opcao = 3;
            }else if (opcao == 4) {
              this.mostrarCartas();
            } else {
                JOptionPane.showMessageDialog(null, "Saindo!!! :(");
                System.exit(8);
            }
        }

    }
    
    public void mostrarCartas() {
        // Vai armazeras várias strings.
        StringBuilder acumuladorDeStrings = new StringBuilder();
        acumuladorDeStrings.append("Suas cartas são:\n");
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
        acumuladorDeStrings.append("Sua melhor pontuação é " + this.melhorPontuacao()
                + " pontos.");
        JOptionPane.showConfirmDialog(null, acumuladorDeStrings);
    }


}