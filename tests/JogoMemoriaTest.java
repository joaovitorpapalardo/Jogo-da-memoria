package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import v1.JogoMemoria;

import static org.junit.jupiter.api.Assertions.*;

public class JogoMemoriaTest {

    private JogoMemoria jogo;

    @BeforeEach
    public void setUp() {
        jogo = new JogoMemoria();
    }

    @Test
    public void testInicializacaoJogo() {
        assertNotNull(jogo);
        assertEquals("Jogo da Memoria", jogo.getTitle());
        assertEquals(400, jogo.getWidth());
        assertEquals(450, jogo.getHeight());
    }

    @Test
    public void testReiniciarJogoNaoLancaExcecao() {
        assertDoesNotThrow(() -> jogo.setVisible(true));
        assertDoesNotThrow(() -> jogo.dispose());
    }
}
