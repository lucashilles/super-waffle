/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lucashilles.model;

/**
 *
 * @author "Lucas HS"
 */
public class Vertice {
    private float x;
    private float y;

    public Vertice(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
    
    public float distanciaAte(Vertice v) {
        return (float) Math.sqrt(Math.pow(x - v.getX(),2) + Math.pow(y - v.getY(), 2));
    }
}
