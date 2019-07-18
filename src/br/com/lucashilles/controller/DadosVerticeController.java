/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lucashilles.controller;

import br.com.lucashilles.model.Vertice;
import br.com.lucashilles.view.DadosVertice;
import java.awt.Dialog;
import java.awt.FlowLayout;
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
    
    private void initController() {
        dv.getjBtnAceitar().addActionListener(e -> aceitar());
        dialog = new JDialog(parent, "Vértice");
        dialog.add(dv);
        dialog.setLayout(new FlowLayout(0,5,5));
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
