package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import v1.BotaoCarta;

public class BotaoCartaTest {

    private BotaoCarta carta;

    @BeforeEach
    public void setUp() {
        carta = new BotaoCarta(7);
    }

    @Test
    public void testValorInicial() {
        assertEquals(7, carta.getValor());
        assertFalse(carta.estaRevelada());
        assertEquals("?", carta.getText());
    }

    @Test
    public void testRevelarCarta() {
        carta.revelar();
        assertTrue(carta.estaRevelada());
        assertEquals("7", carta.getText());
    }

    @Test
    public void testEsconderCarta() {
        carta.revelar();
        carta.esconder();
        assertFalse(carta.estaRevelada());
        assertEquals("?", carta.getText());
    }
}
