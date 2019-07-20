package br.com.lucashilles.controller;

import br.com.lucashilles.model.Vertice;
import br.com.lucashilles.view.DadosVertice;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author "Lucas HS"
 */
public class DadosVerticeController {

    DadosVertice dv;
    Vertice v = null;
    JFrame parent;
    JDialog dialog;

    public DadosVerticeController(JFrame parent) {
        this.parent = parent;
        dv = new DadosVertice();
        initController();
    }

    /**
     * Inicializa um JDialog com o painel de entrada de dados e adiciona os
     * eventos necessários Esta janela é modal.
     */
    private void initController() {
        dv.getjBtnAceitar().addActionListener(e -> aceitar());
        dv.getjTFY().addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    aceitar();
                }
            }
        });

        dialog = new JDialog(parent, "Vértice");
        dialog.add(dv);
        dialog.setLayout(new FlowLayout(0, 5, 5));
        dialog.pack();
        dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }

    private void aceitar() {
        float x, y;
        x = Float.parseFloat(dv.getjTFX().getText());
        y = Float.parseFloat(dv.getjTFY().getText());

        v = new Vertice(x, y);

        dialog.setVisible(false);
    }

    public Vertice getVertice() {
        return v;
    }

}
