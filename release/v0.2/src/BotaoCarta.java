package v02;

import javax.swing.JButton;

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
        this.setText(String.valueOf(valor));
        revelada = true;
    }

    public void esconder() {
        this.setText("?");
        revelada = false;
    }
}
