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

    public Secao() {
    }

    public List<Vertice> getVertices() {
        return vertices;
    }

    public void adicionarVertice(Vertice v) {
        vertices.add(v);
    }
    
    public void removerVertice(int index)  {
        vertices.remove(index);
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
}
