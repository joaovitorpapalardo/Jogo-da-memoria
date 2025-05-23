package v02;

import javax.swing.JButton;

/**
 * Representa um botão que funciona como uma carta no jogo da memória.
 * Cada carta possui um valor associado e pode estar revelada ou escondida.
 * @author joaovitorpapalardo, joaovitorrodrigues, milenacampos, nicolasbabo
 * @version 0.2
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
        this.setText(String.valueOf(valor));
        revelada = true;
    }
    /**
     * Esconde a carta, exibindo "?" e marcando como não revelada.
     */
    public void esconder() {
        this.setText("?");
        revelada = false;
    }
}
