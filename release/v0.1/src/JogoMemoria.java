package v01;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JogoMemoria extends JFrame {
    private final List<BotaoCarta> listaBotoesCarta = new ArrayList<>();
    private BotaoCarta cartaSelecionada1 = null;
    private BotaoCarta cartaSelecionada2 = null;
    private int totalTentativas = 0;
    private JLabel rotuloTentativas;

    public JogoMemoria() {
        setTitle("Jogo da Memoria");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel painelCartas = new JPanel(new GridLayout(4, 4));
        List<Integer> listaValoresCartas = gerarValoresCartas(8); // 8 pares

        for (int valorCarta : listaValoresCartas) {
            BotaoCarta botao = new BotaoCarta(valorCarta);
            botao.addActionListener(e -> aoClicarCarta(botao));
            listaBotoesCarta.add(botao);
            painelCartas.add(botao);
        }

        rotuloTentativas = new JLabel("Tentativas: 0");
        add(rotuloTentativas, BorderLayout.NORTH);
        add(painelCartas, BorderLayout.CENTER);

        setSize(400, 400);
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
