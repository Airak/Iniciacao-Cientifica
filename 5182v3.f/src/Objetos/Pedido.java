/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.util.ArrayList;

/**
 *
 * @author Matheus
 */

public class Pedido {
    
    int id;
    String endereco;
    ArrayList <Torre> tr = new ArrayList();
    ArrayList <Caixa> cxDisponiveis = new ArrayList();//Lista de caixas que auxilia o carregamento
    ArrayList <Caixa> cx = new ArrayList();//Lista de caixas fixa
    ArrayList <TipoCaixa> tipo = new ArrayList(); //Lista de tipos de caixa que auxilia o carregamento
    ArrayList <TipoCaixa> tp = new ArrayList(); //Lista de tipos de caixa fixa
    int idRota;

    public int getIdRota() {
        return idRota;
    }

    public void setIdRota(int idRota) {
        this.idRota = idRota;
    }

    public ArrayList<TipoCaixa> getTipo() {
        return tipo;
    }

    public void setTipo(ArrayList<TipoCaixa> tipo) {
        this.tipo = tipo;
    }

    public Pedido() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public ArrayList<Torre> getTr() {
        return tr;
    }

    public void setTr(ArrayList<Torre> tr) {
        this.tr = tr;
    }

    public ArrayList<Caixa> getCxDisponiveis() {
        return cxDisponiveis;
    }

    public void setCxDisponiveis(ArrayList<Caixa> cxDisponiveis) {
        this.cxDisponiveis = cxDisponiveis;
    }   

    public ArrayList<Caixa> getCx() {
        return cx;
    }

    public void setCx(ArrayList<Caixa> cx) {
        this.cx = cx;
    }

    public ArrayList<TipoCaixa> getTp() {
        return tp;
    }

    public void setTp(ArrayList<TipoCaixa> tp) {
        this.tp = tp;
    }
    
    
}
