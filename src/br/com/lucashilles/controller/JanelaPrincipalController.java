package br.com.lucashilles.controller;

import br.com.lucashilles.model.Secao;
import br.com.lucashilles.model.Vertice;
import br.com.lucashilles.view.JanelaPrincipal;
import br.com.lucashilles.view.Quadro;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

/**
 *
 * @author "Lucas HS"
 */
public class JanelaPrincipalController {

    private JanelaPrincipal janela;
    private Quadro quadro;
    private Secao secao = new Secao();

    /**
     * Construtor que inicializa as variaveis.
     */
    public JanelaPrincipalController() {
        quadro = new Quadro();
        janela = new JanelaPrincipal();
        initController();
    }

    /**
     * Adiciona os eventos necessários aos botões.
     */
    private void initController() {
        janela.getjBtnAceitar().addActionListener(e -> aceitar());
        janela.getjBtnAdicionar().addActionListener(e -> adicionarVertice());
        janela.getjBtnLimpar().addActionListener(e -> limpar());
        janela.getjBtnRemover().addActionListener(e -> removerVertice());

        janela.getjPQuadro().setLayout(new GridBagLayout());
        janela.getjPQuadro().add(quadro);
        janela.pack();
        janela.setLocationRelativeTo(null);

        janela.getjLInfo().setText("");

        janela.setVisible(true);
    }

    private void aceitar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Invoca a janela para criar um novo vertice, captura o vértice criado e se
     * ele for válido é adicionado à seção, à lista e ao desenho.
     */
    private void adicionarVertice() {
        DadosVerticeController dvc = new DadosVerticeController(janela);
        Vertice v = dvc.getVertice();
        String text = "";
        if (v != null) {
            secao.adicionarVertice(v);
            if (secao.getCentroide() != null) {
                quadro.setCentroide(secao.getCentroide());
                text = "C: (" + String.format("%.2f", secao.getCentroide().getX()) + ", " + String.format("%.2f", secao.getCentroide().getY()) + ") - " ; 
            }
            quadro.updateVerticeList(secao.getVertices());

            DefaultListModel model = (DefaultListModel) janela.getjLListaDeVertices().getModel();
            model.addElement("x: " + v.getX() + " y: " + v.getY());
        }
        
        janela.getjLInfo().setText(text + String.format("A: %.2f", secao.getArea()));
    }

    /**
     * Reinicia a entrada da seção.
     */
    private void limpar() {
        secao = new Secao();
        quadro.setCentroide(null);
        quadro.updateVerticeList(new ArrayList<>());
        DefaultListModel model = (DefaultListModel) janela.getjLListaDeVertices().getModel();
        model.clear();
    }

    /**
     * Remove o vértice selecionado seguindo os mesmos passo da adição de
     * vértices.
     */
    private void removerVertice() {
        int removeIndex;
        removeIndex = janela.getjLListaDeVertices().getSelectedIndex();
        secao.removerVertice(removeIndex);
        quadro.updateVerticeList(secao.getVertices());
        DefaultListModel model = (DefaultListModel) janela.getjLListaDeVertices().getModel();
        model.remove(removeIndex);
    }

}
