package Objetos;

import java.util.ArrayList;

/**
 *
 * @author Matheus
 */
public class Container {
    
    float largura, profundidade, altura;
    ArrayList <Camada> camada = new ArrayList();
    
    
    public Container(float largura, float profundidade, float altura) {
        
        this.profundidade=profundidade;
        this.largura=largura;
    }

    public ArrayList<Camada> getCamada() {
        return camada;
    }

    public void setCamada(ArrayList<Camada> camada) {
        this.camada = camada;
    }

    public float getLargura() {
        return largura;
    }

    public void setLargura(float largura) {
        this.largura = largura;
    }

    public float getProfundidade() {
        return profundidade;
    }

    public void setProfundidade(float profundidade) {
        this.profundidade = profundidade;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }
}
