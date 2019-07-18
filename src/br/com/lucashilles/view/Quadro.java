package br.com.lucashilles.view;

import br.com.lucashilles.model.Secao;
import br.com.lucashilles.model.Vertice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author "Lucas HS"
 */
public class Quadro extends javax.swing.JPanel {
    List<Vertice> vert = new ArrayList<>();

    /**
     * Creates new form Quadro
     */
    public Quadro() {
        //initComponents();
        setBackground(Color.black);
    }

    private void drawPoint(Graphics g, float x, float y, int v) {
        g.fillOval(Math.round(x - 2), Math.round(y + 2) * (-1), 4, 4);
        g.drawString("" + v, Math.round(x - 15), Math.round(y - 15) * (-1));
    }

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
    
    private void drawAxis(Graphics g) {
        g.drawLine(-25, 0, getWidth(), 0);
        g.drawLine(0, 25, 0, getHeight() * (-1));
        
    }
    
    public void updateVerticeList(List<Vertice> v) {
        vert = v;
        repaint();
    }
    

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(350, 250);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.translate(10, getHeight() - 10);
        g.setColor(Color.white);
        //g.drawString("This is my custom Panel!", 10, 20  * (-1));
        //g.drawArc(50, 50, 25, 25, 0, 360);
        
        if (vert.size() > 0) {
            drawLines(g);
            int i = 1;
            for (Vertice v : vert) {
                drawPoint(g, v.getX(), v.getY(), i);
                i++;
            }
        }
        
        g.setColor(Color.gray);
        drawAxis(g);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}