import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Baralho {

    private ArrayList<Cartas> cartas = new ArrayList<Cartas>();

    public Baralho() {
        this.embaralhar();
    }

    ;

	public Cartas getCarta() {
            // primeiraCarta recebe a 1ª carta do ArrayList, que é removida do
            // baralho.
            if(this.cartas.size() > 0){
                Cartas primeiraCarta = this.cartas.remove(0);
                return primeiraCarta;
            }else{
                this.embaralhar();
            }
            Cartas primeiraCarta = this.cartas.remove(0);
            return primeiraCarta;
    }

    private void embaralhar() {
        // Essa função só é para ser usada se o baralho estiver vazio.
        if (this.cartas.size() == 0) {
             // Armazena a quantidade de elementos no ArrayList numeros, como
            // representa seis baralhos é 312.
            int quantidadeElementosNoArray = 312;

		// Iremos armazenar os números correspondentes as cartas do baralho
            // nesse ArrayList.
            // Faremos um "for" de 1 a 52 e para cada número, será inserido dois
            // elementos desses
            // números no ArrayList. Isso porque teremos dois baralhos de 52 cartas.
            ArrayList<Integer> numeros = new ArrayList<Integer>();

            for (Integer i = 1; i <= 52; i++) {
                numeros.add(i);
                numeros.add(i);
                numeros.add(i);
                numeros.add(i);
                numeros.add(i);
                numeros.add(i);
            }

		// Depois de termos armazenado "dois baralhos" ordenados no ArrayList,
            // iremos selecionar esses números
            // aleatoriamente, escolhendo posições aleatorias dentro do ArrayList,
            // para cada número escolhido
            // retiraremos ele do ArrayList e faremos a conversão dele para sua
            // carta correspondente.
            while (quantidadeElementosNoArray > 0) {
                Random gerador = new Random();
			// Recebe um número aleatório menor do que a quantidade de elementos
                // que têm em numeros,
                // Será usado para acessarmos uma posição aleatória no ArrayList.
                int indiceArray = gerador.nextInt(quantidadeElementosNoArray);
                Integer numeroDaCarta = numeros.get(indiceArray);
			// Apagando o elemento que se encontrava na posição de nosso
                // interesse.
                numeros.remove(indiceArray);
                // Criando uma carta com a numeração que queremos.
                Cartas novaCarta = new Cartas(numeroDaCarta.intValue());
                this.cartas.add(novaCarta);
                quantidadeElementosNoArray--;
            }
        }

    }

    // Apenas para depuração.
    public void mostrarCartas() {

        for (Iterator<Cartas> iCartas = this.cartas.iterator(); iCartas
                .hasNext();) {
            Cartas carta = (Cartas) iCartas.next();
            System.out.print("A carta é: ");
            carta.mostrarCarta();

        }

    }

    // Para inserir apenas uma carta por vez no baralho.
    public void inserirCarta(int numeracaoCarta) {
        this.cartas.add(new Cartas(numeracaoCarta));
    }
}
