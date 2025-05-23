package v1;

/**
 * Classe principal que inicia a aplicação do jogo da memória.
 * Utiliza a thread de eventos do Swing para garantir que a interface gráfica
 * seja criada de forma segura.
 * @author joaovitorpapalardo, joaovitorrodrigues, milenacamposm, nicolasbabo
 * @version 1.0
 */
public class Main {
    /**
     * Método principal que inicializa o jogo da memória.
     *
     * @param args argumentos da linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            JogoMemoria jogo = new JogoMemoria();
            jogo.setVisible(true);
        });
    }
}
