package v02;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representa a janela principal do jogo da memória com interface gráfica.
 * Gerencia a lógica do jogo, interface e interação com o jogador.
 * @author joaovitorpapalardo, joaovitorrodrigues, milenacampos, nicolasbabo
 * @version 0.2
 */
public class JogoMemoria extends JFrame {
    private final List<BotaoCarta> listaBotoesCarta = new ArrayList<>();
    private BotaoCarta cartaSelecionada1 = null;
    private BotaoCarta cartaSelecionada2 = null;
    private int totalTentativas = 0;
    private JLabel rotuloTentativas;
    private JButton botaoReiniciar;
    
    /**
     * Construtor da classe. Inicializa a interface e inicia o jogo.
     */
    public JogoMemoria() {
        configurarInterface();
        iniciarJogo();
    }
    
    /**
     * Configura a interface gráfica do jogo, incluindo rótulo de tentativas e botão de reinício.
     */
    private void configurarInterface() {
        setTitle("Jogo da Memoria");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        rotuloTentativas = new JLabel("Tentativas: 0");
        rotuloTentativas.setHorizontalAlignment(SwingConstants.CENTER);
        add(rotuloTentativas, BorderLayout.NORTH);

        botaoReiniciar = new JButton("Reiniciar Jogo");
        botaoReiniciar.addActionListener(e -> reiniciarJogo());
        add(botaoReiniciar, BorderLayout.SOUTH);

        setSize(400, 450);
    }
    
    /**
     * Inicia ou reinicia o jogo, criando e embaralhando as cartas, e resetando o estado.
     */
    private void iniciarJogo() {
        JPanel painelCartas = new JPanel(new GridLayout(4, 4));
        List<Integer> listaValoresCartas = gerarValoresCartas(8); // 8 pares

        listaBotoesCarta.clear();
        cartaSelecionada1 = null;
        cartaSelecionada2 = null;
        totalTentativas = 0;
        rotuloTentativas.setText("Tentativas: 0");

        for (int valorCarta : listaValoresCartas) {
            BotaoCarta botao = new BotaoCarta(valorCarta);
            botao.addActionListener(e -> aoClicarCarta(botao));
            listaBotoesCarta.add(botao);
            painelCartas.add(botao);
        }

        getContentPane().add(painelCartas, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private List<Integer> gerarValoresCartas(int quantidadePares) {
        List<Integer> valores = new ArrayList<>();
        for (int i = 1; i <= quantidadePares; i++) {
            valores.add(i);
            valores.add(i);
        }
        Collections.shuffle(valores);
        return valores;
    }
    
    /**
     * Lida com a lógica ao clicar em uma carta: revela, compara e verifica fim do jogo.
     *
     * @param cartaClicada a carta clicada pelo jogador
     */
    private void aoClicarCarta(BotaoCarta cartaClicada) {
        if (cartaClicada.estaRevelada()) return;
        if (cartaSelecionada1 != null && cartaSelecionada2 != null) return;

        cartaClicada.revelar();

        if (cartaSelecionada1 == null) {
            cartaSelecionada1 = cartaClicada;
        } else {
            cartaSelecionada2 = cartaClicada;
            totalTentativas++;
            rotuloTentativas.setText("Tentativas: " + totalTentativas);

            if (cartaSelecionada1.getValor() == cartaSelecionada2.getValor()) {
                // São iguais → permanecem reveladas
                cartaSelecionada1 = null;
                cartaSelecionada2 = null;
                verificarFimDeJogo();
            } else {
                // Diferentes → esconder após 1 segundo
                Timer temporizador = new Timer(1000, e -> {
                    cartaSelecionada1.esconder();
                    cartaSelecionada2.esconder();
                    cartaSelecionada1 = null;
                    cartaSelecionada2 = null;
                });
                temporizador.setRepeats(false);
                temporizador.start();
            }
        }
    }
    
    /**
     * Verifica se todas as cartas foram reveladas e finaliza o jogo se necessário.
     */
    private void verificarFimDeJogo() {
        boolean todasReveladas = listaBotoesCarta.stream().allMatch(BotaoCarta::estaRevelada);
        if (todasReveladas) {
            JOptionPane.showMessageDialog(this, "Fim de jogo! Total de tentativas: " + totalTentativas);
        }
    }
    
    /**
     * Reinicia o jogo removendo os componentes e reconfigurando a interface.
     * Botao reiniciar
     * @since 0.2
     */
    private void reiniciarJogo() {
        getContentPane().removeAll();
        configurarInterface();
        iniciarJogo();
    }
}
