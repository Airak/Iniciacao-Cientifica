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
public class Camada {
    
    ArrayList <Torre> t = new ArrayList();
    
    ArrayList <Pontos> planoInf = new ArrayList();
    ArrayList <Pontos> planoSup = new ArrayList();
    
    float comprimento, largura, altura;
    float x, y; 
    int id;
    
    public Camada(float largura, float profundidade) {
        
        this.comprimento=profundidade;
        this.largura=largura;
        
    }
    public ArrayList<Torre> getT() {
        return t;
    }

    public void setT(ArrayList<Torre> t) {
        this.t = t;
    }

    public ArrayList<Pontos> getPlanoInf() {
        return planoInf;
    }

    public void setPlanoInf(ArrayList<Pontos> planoInf) {
        this.planoInf = planoInf;
    }

    public ArrayList<Pontos> getPlanoSup() {
        return planoSup;
    }

    public void setPlanoSup(ArrayList<Pontos> planoSup) {
        this.planoSup = planoSup;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getComprimento() {
        return comprimento;
    }

    public void setComprimento(float comprimento) {
        this.comprimento = comprimento;
    }

    public float getLargura() {
        return largura;
    }

    public void setLargura(float largura) {
        this.largura = largura;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
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
    
    
}
