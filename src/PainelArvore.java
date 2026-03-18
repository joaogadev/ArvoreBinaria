import javax.swing.*;
import java.awt.*;

public class PainelArvore extends JPanel{
    Arvore arvore;

    PainelArvore(Arvore arvore){
        this.arvore = arvore;
    }

    void desenhar(Graphics g, No no, int x, int y, int dist) {
        if (no == null) return;

        g.drawOval(x, y, 30, 30);
        g.drawString(String.valueOf(no.valor), x + 10, y + 20);

        if (no.esquerda != null) {
            g.drawLine(x + 15, y + 30, x - dist + 15, y + 80);
            desenhar(g, no.esquerda, x - dist, y + 80, dist / 2);
        }
        if (no.direita != null) {
            g.drawLine(x + 15, y + 30, x + dist, y + 80);
            desenhar(g, no.direita, x + dist, y + 80, dist / 2);
        }
    }


    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        desenhar(g, arvore.raiz, 300, 30, 150);
    }
}
