package v1;

import javax.swing.JButton;
import java.awt.Font;

/**
 * Representa um botão que funciona como uma carta no jogo da memória.
 * Cada carta possui um valor associado e pode estar revelada ou escondida.
 * @author joaovitorpapalardo, joaovitorrodrigues, milenacamposm, nicolasbabo
 * @version 1.0
 */
public class BotaoCarta extends JButton {
    private final int valor;
    private boolean revelada = false;
    
    /**
     * Cria um novo botão de carta com o valor especificado.
     * Inicialmente, a carta está escondida.
     * @param valor o valor associado à carta
     */
    public BotaoCarta(int valor) {
        this.valor = valor;
        this.setText("?");
    }
    
    /**
     * Retorna o valor da carta.
     * @return o valor da carta
     */
    public int getValor() {
        return valor;
    }
    
    /**
     * Verifica se a carta está revelada.
     * @return {@code true} se a carta estiver revelada, {@code false} caso contrário
     */
    public boolean estaRevelada() {
        return revelada;
    }
    
    /**
     * Revela a carta, exibindo seu valor e marcando como revelada.
     */
    public void revelar() {
        setText(String.valueOf(valor));
        setFont(new Font("SansSerif", Font.BOLD, 28));
        revelada = true;
    }
    
    /**
     * Esconde a carta, exibindo "?" e marcando como não revelada.
     */
    public void esconder() {
        setText("?");
        setFont(new Font("SansSerif", Font.BOLD, 28));
        revelada = false;
    }
}
