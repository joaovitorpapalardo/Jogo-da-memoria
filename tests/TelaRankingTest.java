package v1;

import org.junit.jupiter.api.*;
import javax.swing.*;
import static org.junit.jupiter.api.Assertions.*;

public class TelaRankingTest {

    @Test
    public void testConstrutorCriaJanelaComTabela() {
        TelaRanking tela = new TelaRanking();
        assertEquals("Top 10 Placares", tela.getTitle());
        assertEquals(300, tela.getWidth());
        assertEquals(300, tela.getHeight());
        assertNotNull(tela.getLocation());

        JScrollPane scrollPane = null;
        for (java.awt.Component comp : tela.getContentPane().getComponents()) {
            if (comp instanceof JScrollPane) {
                scrollPane = (JScrollPane) comp;
                break;
            }
        }
        assertNotNull(scrollPane, "Deve conter um JScrollPane");

        JViewport viewport = scrollPane.getViewport();
        java.awt.Component view = viewport.getView();
        assertTrue(view instanceof JTable, "O JScrollPane deve conter uma JTable");

        JTable tabela = (JTable) view;
        assertEquals(2, tabela.getColumnCount());
        assertEquals("Jogador", tabela.getColumnName(0));
        assertEquals("Tentativas", tabela.getColumnName(1));

        assertTrue(tabela.getRowCount() <= 10);
    }
}
