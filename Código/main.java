import javax.swing.JOptionPane;

public class main {

    public static void main(String[] args) {
        int i;
        String s = JOptionPane.showInputDialog("Por favor, entre com a quantidade de jogadores. Deve ser entre 1 e 4 jogadores!");
        try {
            i = Integer.parseInt(s);
            while ((i < 1) || (i > 4)) {
                s = JOptionPane.showInputDialog("O valor inserido não é válido!!! Entre com um número de 1 a 4!!! :|");
                i = Integer.parseInt(s);
            }
            Blackjack brackjack = new Blackjack(i);
            brackjack.jogadas();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "O valor inserido não é um número ou clicou em \"Não ou Cancelar na caixa de diálogo\"!!! Saindo do programa! :(");
            System.exit(8);
        }
    }

}
