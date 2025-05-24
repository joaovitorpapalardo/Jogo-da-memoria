package v;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Janela gráfica que exibe o ranking com os 10 melhores placares do jogo da memória.
 * Os dados são carregados a partir do arquivo CSV gerenciado pela classe {@link Placares}.
 * @author joaovitorpapalardo, joaovitorrodrigues, milenacamposm, nicolasbabo
 * @version 1.0
 * @since 1.0
 */
public class TelaRanking extends JFrame {
    /**
     * Construtor da janela de ranking.
     * Inicializa a interface, define o título e exibe os dados dos 10 melhores jogadores
     * em uma tabela com nome e número de tentativas.
     */
    public TelaRanking() {
        setTitle("Top 10 Placares");
        setSize(300, 300);
        setLocationRelativeTo(null);

        List<String[]> top10 = Placares.carregarTop10Placares();
        String[] colunas = {"Jogador", "Tentativas"};
        String[][] dados = new String[top10.size()][2];

        for (int i = 0; i < top10.size(); i++) {
            dados[i][0] = top10.get(i)[0];
            dados[i][1] = top10.get(i)[1];
        }

        JTable tabela = new JTable(dados, colunas);
        add(new JScrollPane(tabela), BorderLayout.CENTER);
    }
}
