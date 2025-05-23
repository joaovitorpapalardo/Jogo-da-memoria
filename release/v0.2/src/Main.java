package v02;

/**
 * Classe principal que inicia a aplicação do jogo da memória.
 * Utiliza a thread de eventos do Swing para garantir que a interface gráfica
 * seja criada de forma segura.
 * @author joaovitorpapalardo, joaovitorrodrigues, milenacampos, nicolasbabo
 * @version 0.2
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
