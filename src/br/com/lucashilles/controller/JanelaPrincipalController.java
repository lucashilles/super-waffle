package br.com.lucashilles.controller;

import br.com.lucashilles.model.Secao;
import br.com.lucashilles.model.Vertice;
import br.com.lucashilles.view.JanelaPrincipal;
import br.com.lucashilles.view.Quadro;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author "Lucas HS"
 */
public class JanelaPrincipalController {

    private JanelaPrincipal janela;
    private Quadro quadro;
    private Secao secao = new Secao();

    public JanelaPrincipalController() {
        quadro = new Quadro();
        janela = new JanelaPrincipal();
        initController();
    }

    private void initController() {
        janela.getjBtnAceitar().addActionListener(e -> aceitar());
        janela.getjBtnAdicionar().addActionListener(e -> adicionarVertice());
        janela.getjBtnLimpar().addActionListener(e -> limpar());
        janela.getjBtnRemover().addActionListener(e -> removerVertice());

        janela.getjPQuadro().setLayout(new GridBagLayout());
        janela.getjPQuadro().add(quadro);
        janela.pack();
        janela.setLocationRelativeTo(null);
    }

    private void aceitar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void adicionarVertice() {
//        float x, y;
//        x = Float.parseFloat(JOptionPane.showInputDialog("Coordenada X"));
//        y = Float.parseFloat(JOptionPane.showInputDialog("Coordenada y"));
//        Vertice v = new Vertice(x, y);
        DadosVerticeController dvc = new DadosVerticeController(janela);
        Vertice v = dvc.getVertice();
        if (v != null) {
            secao.adicionarVertice(v);
            quadro.updateVerticeList(secao.getVertices());

            DefaultListModel model = (DefaultListModel) janela.getjLListaDeVertices().getModel();
            model.addElement("x: " + v.getX() + " y: " + v.getY());
        }
    }

    private void limpar() {
        secao = new Secao();
        quadro.updateVerticeList(new ArrayList<>());
        DefaultListModel model = (DefaultListModel) janela.getjLListaDeVertices().getModel();
        model.clear();
    }

    private void removerVertice() {
        int removeIndex;
        removeIndex = janela.getjLListaDeVertices().getSelectedIndex();
        secao.removerVertice(removeIndex);
        quadro.updateVerticeList(secao.getVertices());
        DefaultListModel model = (DefaultListModel) janela.getjLListaDeVertices().getModel();
        model.remove(removeIndex);
    }

}
