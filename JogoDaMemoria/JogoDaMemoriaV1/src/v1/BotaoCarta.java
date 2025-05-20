package v1;

import javax.swing.JButton;
import java.awt.Font;


public class BotaoCarta extends JButton {
    private final int valor;
    private boolean revelada = false;

    public BotaoCarta(int valor) {
        this.valor = valor;
        this.setText("?");
    }

    public int getValor() {
        return valor;
    }

    public boolean estaRevelada() {
        return revelada;
    }

    public void revelar() {
        setText(String.valueOf(valor));
        setFont(new Font("SansSerif", Font.BOLD, 28));
        revelada = true;
    }

    public void esconder() {
        setText("?");
        setFont(new Font("SansSerif", Font.BOLD, 28));
        revelada = false;
    }
}
