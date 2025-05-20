package v1;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            JogoMemoria jogo = new JogoMemoria();
            jogo.setVisible(true);
        });
    }
}
