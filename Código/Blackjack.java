import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Blackjack {

    private ArrayList<Player> players = new ArrayList<Player>();
    private Dealer dealer = new Dealer();
    private Baralho deck = new Baralho();
    private boolean isBlackjackJogador;
    private boolean isBlackjackDealer;

    public Blackjack(int numPlayers) {
        // adiciona novos jogadores
        for (int i = 0; i < numPlayers; i++) {
            String name = JOptionPane.showInputDialog("Por favor, jogador "
                    + (i + 1) + ", informe seu nome.");
            // Tratando entrada de nomes.
            try {
                Player nPlayer = new Player(name);
                players.add(nPlayer);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "Problemas com os nomes inseridos!!!");
                System.exit(8);
            }
        }
    }

	public void jogadas() {
        int i = 0;
        int sair = 0;
        boolean jogadorPerdeu;
        boolean removiJogador;
        while (sair != 1) {
            this.isBlackjackDealer = false;
            this.isBlackjackJogador = false;
            jogadorPerdeu = false;
            removiJogador = false;
            this.dealer.limparmao();
            this.players.get(i).limparmao();
            JOptionPane.showMessageDialog(null,"Bem vindo(a) a uma nova rodada, " + this.players.get(i).getName());
            // Apostando.
            String s = JOptionPane.showInputDialog("Entre com o valor da aposta\nValor máximo: "+ this.players.get(i).getDinheiro());
            // Tratando tomé.
            while ((Integer.parseInt(s) > this.players.get(i).getDinheiro()) || (Integer.parseInt(s) < 0)) {
                s = JOptionPane.showInputDialog(null,"O valor inserido é maior do que o máximo ou menor do que zero!!!\nO máximo permitido é: " + this.players.get(i).getDinheiro() + ".");
            }
            this.players.get(i).setAposta(Integer.parseInt(s));
            JOptionPane.showMessageDialog(null, "Seu saldo é "+ this.players.get(i).getDinheiro() + " R$.");
            // Duas cartas para o jogador i.
            this.players.get(i).setCartas(this.deck.getCarta());
            this.players.get(i).setCartas(this.deck.getCarta());

            // Pegando duas cartas para o dealer.
            this.dealer.setCartas(this.deck.getCarta());
            this.dealer.setCarta(this.deck.getCarta());

            // O jogador fez Blackjack. A posição 1 guardará o BJ.
            if (this.players.get(i).getScore()[1] == 21) {
                this.isBlackjackJogador = true;
            }

            // Dando opção de jogada ao jogador i.
            this.players.get(i).opcaoJogador(this.deck);

            this.dealer.mostrarCartas();

            // Se a primeira posição estourou, a segunda também. Já que a
            // segunda posição terá 10 pontos a mais
            // do que a primeira, se tivermos um ás.
            if ((this.players.get(i).getScore()[0]) > 21) {
                JOptionPane.showMessageDialog(null,"Sua pontuação estourou!!! Você perdeu!!!\nSeus pontos: " + this.players.get(i).getScore()[0]);
                JOptionPane.showMessageDialog(null, "Seu saldo é " + this.players.get(i).getDinheiro() + " R$.");
                this.players.get(i).mostrarCartas();
                // Se o jogador i perdeu, a jogada passa para o jodador i+1
                // O jogador i gastou todo o dinheiro.
                if(this.players.get(i).getDinheiro() <= 0) {
                    JOptionPane.showMessageDialog(null,"Você gastou todo o seu dinheiro!!! Não participará mais do jogo!!! :(");
                    this.players.remove(i);
                    removiJogador = true;
                    // Todos os jogadores foram eliminados
                    if(this.players.size() == 0) {
                       JOptionPane.showMessageDialog(null,"Não há mais jogadores!!! Saindo do jogo!!! :(");
                       System.exit(8);
                    }
                }
                if(! removiJogador){
                    i++;
                // Para ficar dando volta nos jogadores.
                    if (i == this.players.size()) {
                        i = 0;
                    }
                }else{// Removi jogador, diminuo o i.
                    if(i > 0)i--;                
                }
                continue;
            }

            // Se o dealer tem 21 pontos até aqui, ele fez blackjack.
            if (this.dealer.getScore()[1] == 21) {
                this.isBlackjackDealer = true;
            }

            // O dealer tem que fazer no mínimo 17 pontos.
            if ((this.dealer.getScore()[0] < 17)
                    && (this.dealer.getScore()[1] < 17)) {
                this.dealer.opcaoJogador(deck);
            }

            // Olhando as pontuações:
            // Os dois fizeram Blackjack.
            if ((this.isBlackjackJogador) && (this.isBlackjackDealer)) {
                this.players.get(i).mostrarCartas();
                this.dealer.mostrarCartas();
                JOptionPane.showMessageDialog(null, "Você e o Dealer fizeram Blackjack! :|");
            } else if (this.dealer.getScore()[0] > 21) {
                // Dealer Estourou a pontuação.
                this.dealer.mostrarCartas();
                JOptionPane.showMessageDialog(null,"A pontuação do Dealer estourou! Você ganhou! Parabéns!!! :D\n Potuação do Dealer: "+ this.dealer.getScore()[0]);
                this.players.get(i).setDinheiro(2 * this.players.get(i).getAposta());
                JOptionPane.showMessageDialog(null, "Seu saldo é de "+ this.players.get(i).getDinheiro() + " R$.");
            } else if (this.players.get(i).melhorPontuacao() > this.dealer.melhorPontuacao()) {
                // A pontuação do jogador i é maior do que a do Dealer.
                this.players.get(i).mostrarCartas();
                this.dealer.mostrarCartas();
                JOptionPane.showMessageDialog(null,"Você ganhou! Parabéns!!! :D");
                JOptionPane.showMessageDialog(null, "Você tem "+ this.players.get(i).melhorPontuacao()+ " e o Dealer tem " + this.dealer.melhorPontuacao()+ " pontos.");
                this.players.get(i).setDinheiro(this.players.get(i).getDinheiro() + 2 * this.players.get(i).getAposta());
                JOptionPane.showMessageDialog(null, "Seu saldo é de "+ this.players.get(i).getDinheiro() + " R$.");
            } else if (this.players.get(i).melhorPontuacao() == this.dealer.melhorPontuacao()) {
                this.players.get(i).mostrarCartas();
                this.dealer.mostrarCartas();
                if(this.isBlackjackDealer) {
                    JOptionPane.showMessageDialog(null,"O Dealer fez Blackjack e você não! Você perdeu :(");
                    JOptionPane.showMessageDialog(null, "Você tem " + this.players.get(i).melhorPontuacao() + " e o Dealer tem " + this.dealer.melhorPontuacao()+ " pontos.");
                    JOptionPane.showMessageDialog(null, "Seu saldo é de "+ this.players.get(i).getDinheiro() + " R$.");
                    if(this.players.get(i).getDinheiro() <= 0) {
                        JOptionPane.showMessageDialog(null,"Você gastou todo o seu dinheiro!!! Não participará mais do jogo!!! :(");
                        this.players.remove(i);
                        removiJogador = true;
                        // Todos os jogadores foram eliminados
                        if(this.players.size() == 0) {
                            JOptionPane.showMessageDialog(null,"Não há mais jogadores!!! Saindo do jogo!!! :(");
                            System.exit(8);
                        }
                    }
                }else if (this.isBlackjackJogador) {
                    JOptionPane.showMessageDialog(null,"O você fez Blackjack e o Dealer não! Você ganhou :D");
                    JOptionPane.showMessageDialog(null, "Você tem " + this.players.get(i).melhorPontuacao() + " e o Dealer tem " + this.dealer.melhorPontuacao()+ " pontos.");
                    this.players.get(i).setDinheiro(this.players.get(i).getDinheiro() + 2 * this.players.get(i).getAposta());
                    JOptionPane.showMessageDialog(null, "Seu saldo é de "+ this.players.get(i).getDinheiro() + " R$.");
                } else{
                    JOptionPane.showMessageDialog(null,"Você e o Dealer empataram!! :D");
                    JOptionPane.showMessageDialog(null, "Você tem "+ this.players.get(i).melhorPontuacao()+ " e o Dealer tem " + this.dealer.melhorPontuacao()+ " pontos.");
                    this.players.get(i).setDinheiro(this.players.get(i).getDinheiro() + this.players.get(i).getAposta());
                    JOptionPane.showMessageDialog(null, "Seu saldo é de " + this.players.get(i).getDinheiro() + " R$.");
                }
                

            } else {
                // O jogador i perdeu.
                this.players.get(i).mostrarCartas();
                this.dealer.mostrarCartas();
                JOptionPane.showMessageDialog(null, "Você perdeu!!! :(");
                JOptionPane.showMessageDialog(null, "Você tem " + this.players.get(i).melhorPontuacao() + " e o Dealer tem " + this.dealer.melhorPontuacao()+ " pontos.");
                JOptionPane.showMessageDialog(null, "Seu saldo é de "+ this.players.get(i).getDinheiro() + " R$.");
                 // O jogador i gastou todo o dinheiro.
                if(this.players.get(i).getDinheiro() <= 0) {
                    JOptionPane.showMessageDialog(null,"Você gastou todo o seu dinheiro!!! Não participará mais do jogo!!! :(");
                    this.players.remove(i);
                    removiJogador = true;
                    // Todos os jogadores foram eliminados
                    if(this.players.size() == 0) {
                     JOptionPane.showMessageDialog(null,"Não há mais jogadores!!! Saindo do jogo!!! :(");
                     System.exit(8);
                    }
                }
            }
            
            if (this.players.size() > 1) {
                JOptionPane.showMessageDialog(null,"Próximo jogador, por favor.");
            }
            s = JOptionPane.showInputDialog("1 para sair e qualquer outro NÚMERO para continuar\n");
            sair = Integer.parseInt(s);
            // Se eu não removi um jogador. Se removi o i é o mesmo.
            if(! removiJogador){
                i++;
                // Para ficar dando volta nos jogadores.
                if (i == this.players.size()) {
                    i = 0;
                }
            }else{// Removi jogador, diminuo o i.
              if(i > 0) i--;                
            }
            
        }

    }
}
