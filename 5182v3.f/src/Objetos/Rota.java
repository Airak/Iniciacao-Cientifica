/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import Objetos.Pedido;
import Objetos.Container;
import java.util.ArrayList;

/**
 *
 * @author Matheus
 */
public class Rota {
    
    int alturaLim = 2200;
    int larguraLim = 2130;
    int profundidadeLim = 11000;
    
    ArrayList <Pedido> pedidosAtendidos = new ArrayList();
    double distPercorrida;
    int ID;
    Container container = new Container(larguraLim, profundidadeLim, alturaLim);

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public ArrayList<Pedido> getPedidosAtendidos() {
        return pedidosAtendidos;
    }

    public void setPedidosAtendidos(ArrayList<Pedido> pedidosAtendidos) {
        this.pedidosAtendidos = pedidosAtendidos;
    }

    public double getDistPercorrida() {
        return distPercorrida;
    }

    public void setDistPercorrida(double distPercorrida) {
        this.distPercorrida = distPercorrida;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
}
