/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bpp;

import java.util.ArrayList;

/**
 *
 * @author Matheus
 */
public class Solucao {
    
    ArrayList<Pedido> pedido = new ArrayList<Pedido>();
    ArrayList<Container> container = new ArrayList<Container>();;
    
    int id;
    Double distanciaTotal;

    public ArrayList<Container> getContainer() {
        return container;
    }

    public void setContainer(ArrayList<Container> container) {
        this.container = container;
    }

    public ArrayList<Pedido> getPedido() {
        return pedido;
    }

    public void setPedido(ArrayList<Pedido> pedido) {
        this.pedido = pedido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getDistanciaTotal() {
        return distanciaTotal;
    }

    public void setDistanciaTotal(Double distanciaTotal) {
        this.distanciaTotal = distanciaTotal;
    }
    
}
