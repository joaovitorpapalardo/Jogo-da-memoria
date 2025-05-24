package v;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representa a janela principal do jogo da memória com interface gráfica.
 * Gerencia a lógica do jogo, interface e interação com o jogador.
 * @author joaovitorpapalardo, joaovitorrodrigues, milenacamposm, nicolasbabo
 * @version 1.0
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

            botao.setFont(new Font("SansSerif", Font.BOLD, 28));
            botao.setPreferredSize(new Dimension(80, 80));
            botao.setBackground(new Color(220, 220, 250));
            botao.setFocusPainted(false);
            botao.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

            botao.addActionListener(e -> aoClicarCarta(botao));
            listaBotoesCarta.add(botao);
            painelCartas.add(botao);
        }


        getContentPane().add(painelCartas, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
    
    /**
     * Gera uma lista de valores duplicados e embaralhados para representar pares de cartas.
     *
     * @param quantidadePares o número de pares de cartas
     * @return lista embaralhada de valores de cartas
     */
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
                cartaSelecionada1 = null;
                cartaSelecionada2 = null;
                verificarFimDeJogo();
            } else {
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
     * Solicita o nome do jogador, salva o placar e exibe o ranking.
     * @since 1.0
     */
    private void verificarFimDeJogo() {
        boolean todasReveladas = listaBotoesCarta.stream().allMatch(BotaoCarta::estaRevelada);
        if (todasReveladas) {
            String nomeJogador = JOptionPane.showInputDialog(this, "Fim de jogo!\nDigite seu nome:");
            if (nomeJogador == null || nomeJogador.isBlank()) {
                nomeJogador = "Jogador";
            }

            Placares.salvarPlacar(nomeJogador, totalTentativas);

            JOptionPane.showMessageDialog(this, "Parabéns " + nomeJogador + "! Você terminou com " + totalTentativas + " tentativas.");

            TelaRanking ranking = new TelaRanking();
            ranking.setVisible(true);
        }
    }

    
    /**
     * Reinicia o jogo removendo os componentes e reconfigurando a interface.
     */
    private void reiniciarJogo() {
        getContentPane().removeAll();
        configurarInterface();
        iniciarJogo();
    }
}
