package br.com.lucashilles.view;

import br.com.lucashilles.model.Vertice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author "Lucas HS"
 * 
 * TODO: Adicionar função para centralizar a seção na tela usando translate no custom painter e getBounds da seção.
 */
public class Quadro extends javax.swing.JPanel {

    List<Vertice> vert = new ArrayList<>();
    Vertice c = null;

    public Quadro() {
        setBackground(Color.black);
    }

    /**
     * Desenha um ponto com uma etiqueta.
     * @param g
     * @param x - Coordenada X.
     * @param y - Coordenada Y.
     * @param t - Elemento que será colocado na etiqueta.
     */
    private void drawPoint(Graphics g, float x, float y, Object t) {
        g.fillOval(Math.round(x - 2), Math.round(y + 2) * (-1), 4, 4);
        g.drawString("" + t, Math.round(x - 15), Math.round(y - 15) * (-1));
    }

    /**
     * Desenha linhas ligando todos os pontos na ordem em que foram adicionados.
     * @param g 
     */
    private void drawLines(Graphics g) {
        for (int i = 1; i < vert.size(); i++) {
            Vertice v1 = vert.get(i - 1);
            Vertice v2 = vert.get(i);
            g.drawLine(Math.round(v1.getX()), Math.round(v1.getY()) * (-1), Math.round(v2.getX()), Math.round(v2.getY()) * (-1));
        }

        Vertice fim = vert.get(vert.size() - 1);
        Vertice inicio = vert.get(0);

        g.drawLine(Math.round(fim.getX()), Math.round(fim.getY() * (-1)), Math.round(inicio.getX()), Math.round(inicio.getY()) * (-1));
    }

    
    /**
     * Desenha os eixos na tela.
     * @param g 
     */
    private void drawAxis(Graphics g) {
        g.drawLine(-25, 0, getWidth(), 0);
        g.drawLine(0, 25, 0, getHeight() * (-1));

    }

    public void updateVerticeList(List<Vertice> v) {
        vert = v;
        repaint();
    }

    public void setCentroide(Vertice c) {
        this.c = c;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(350, 250);
    }

    /**
     * Painter customizado para gerar o desenho da seção.
     * @param g 
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.translate(10, getHeight() - 10);
        g.setColor(Color.white);

        if (vert.size() > 0) {
            drawLines(g);
            int i = 1;
            for (Vertice v : vert) {
                drawPoint(g, v.getX(), v.getY(), i);
                i++;
            }
        }

        if (c != null) {
            drawPoint(g, c.getX(), c.getY(), "C");
        }

        g.setColor(Color.gray);
        drawAxis(g);
    }
}
