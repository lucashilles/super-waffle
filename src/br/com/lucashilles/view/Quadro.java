package br.com.lucashilles.view;

import br.com.lucashilles.model.Vertice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import jdk.nashorn.internal.objects.Global;

/**
 *
 * @author "Lucas HS"
 */
public class Quadro extends javax.swing.JPanel {

    List<Vertice> vertices = new ArrayList<>();
    Vertice centroide = null;
    Vertice centro = null;

    public Quadro() {
        setBackground(Color.black);
    }

    /**
     * Desenha um ponto com uma etiqueta.
     *
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
     *
     * @param g
     */
    private void drawLines(Graphics g) {
        for (int i = 1; i < vertices.size(); i++) {
            Vertice v1 = vertices.get(i - 1);
            Vertice v2 = vertices.get(i);
            g.drawLine(Math.round(v1.getX()), Math.round(v1.getY()) * (-1), Math.round(v2.getX()), Math.round(v2.getY()) * (-1));
        }

        Vertice fim = vertices.get(vertices.size() - 1);
        Vertice inicio = vertices.get(0);

        g.drawLine(Math.round(fim.getX()), Math.round(fim.getY() * (-1)), Math.round(inicio.getX()), Math.round(inicio.getY()) * (-1));
    }

    /**
     * Desenha os eixos na tela.
     *
     * @param g
     */
    private void drawAxis(Graphics g) {
        g.drawLine(-1000, 0, 1000, 0);
        g.drawLine(0, 1000, 0, -1000);

    }

    public void updateVerticeList(List<Vertice> v) {
        vertices = v;
        if (v.size() > 1) {
            updateCentro();
        }
        repaint();
    }

    public void setCentroide(Vertice c) {
        this.centroide = c;
    }

    private void updateCentro() {
        float xMin = (float) Global.Infinity;
        float xMax = (float) -Global.Infinity;
        float yMin = (float) Global.Infinity;
        float yMax = (float) -Global.Infinity;

        for (Vertice v : vertices) {
            if (v.getX() < xMin) {
                xMin = v.getX();
            }
            if (v.getX() > xMax) {
                xMax = v.getX();
            }
            if (v.getY() < yMin) {
                yMin = v.getY();
            }
            if (v.getY() > yMax) {
                yMax = v.getY();
            }
        }

        centro = new Vertice(xMin + (xMax - xMin) / 2, yMin + (yMax - yMin) / 2);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(450, 250);
    }

    /**
     * Painter customizado para gerar o desenho da seção.
     *
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (centro != null) {
            g.translate(((int) centro.getX() - getWidth() / 2) * (-1), getHeight() - ((int) centro.getY() - getHeight() / 2) * (-1));
        } else {
            g.translate(10, getHeight() - 10);
        }

        g.setColor(Color.gray);
        drawAxis(g);

        g.setColor(Color.white);
        if (vertices.size() > 0) {
            drawLines(g);
            int i = 1;
            for (Vertice v : vertices) {
                drawPoint(g, v.getX(), v.getY(), i);
                i++;
            }
        }

        if (centroide != null) {
            drawPoint(g, centroide.getX(), centroide.getY(), "C");
        }

    }
}
