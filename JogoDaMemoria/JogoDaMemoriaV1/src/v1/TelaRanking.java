package v1;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaRanking extends JFrame {
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
