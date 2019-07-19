package br.com.lucashilles.model;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import jdk.nashorn.internal.objects.Global;

/**
 *
 * @author "Lucas HS"
 */
public class Secao {

    private List<Vertice> vertices = new ArrayList<>();
    private float area = 0f;
    private Vertice centroide = null;

    public Secao() {
    }

    public List<Vertice> getVertices() {
        return vertices;
    }

    public void adicionarVertice(Vertice v) {
        vertices.add(v);
        atualizarArea();
        atualizarCentroide();
    }

    public void removerVertice(int index) {
        vertices.remove(index);
        atualizarArea();
        atualizarCentroide();
    }

    public float getArea() {
        return area;
    }

    public Vertice getCentroide() {
        return centroide;
    }

    /**
     * Retorna um retangulo que engloba os pontos
     */
    public Rectangle getBounds() {
        float xMin = (float) Global.Infinity;
        float xMax = (float) -Global.Infinity;
        float yMin = (float) Global.Infinity;
        float yMax = (float) -Global.Infinity;
        Rectangle rec = null;

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

        rec = new Rectangle(Math.round(xMin), Math.round(yMin), Math.round(xMax - xMin), Math.round(yMax - yMin));

        return rec;
    }

    private void atualizarArea() {
        if (vertices.size() > 2) {
            float soma = 0;
            for (int i = 0; i < vertices.size(); i++) {
                Vertice a, b;
                if (i < vertices.size() - 1) {
                    a = vertices.get(i);
                    b = vertices.get(i + 1);
                } else {
                    a = vertices.get(i);
                    b = vertices.get(0);
                }
                soma += (a.getX() * b.getY() - b.getX() * a.getY());
            }
            area = soma / 2;
        }

    }

    private void atualizarCentroide() {
        if (vertices.size() > 2) {
            float sX = 0;
            float sY = 0;
            for (int i = 0; i < vertices.size(); i++) {
                Vertice a, b;
                if (i < vertices.size() - 1) {
                    a = vertices.get(i);
                    b = vertices.get(i + 1);
                } else {
                    a = vertices.get(i);
                    b = vertices.get(0);
                }

                sX += (a.getX() + b.getX()) * (a.getX() * b.getY() - b.getX() * a.getY());
                sY += (a.getY() + b.getY()) * (a.getX() * b.getY() - b.getX() * a.getY());
            }
            centroide = new Vertice(sX / (6 * area), sY / (6 * area));
        }
    }
}
