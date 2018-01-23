package Objetos;

import java.util.ArrayList;

/**
 *
 * @author Matheus
 */
public class Torre {
    
    float altura, largura, profundidade;
    int id;
    float x, y, z; 
    
    ArrayList <Caixa> caixasDaTorre = new ArrayList();    
    
    public Torre() {
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

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
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

    public ArrayList<Caixa> getCaixasDaTorre() {
        return caixasDaTorre;
    }

    public void setCaixasDaTorre(ArrayList<Caixa> caixasDaTorre) {
        this.caixasDaTorre = caixasDaTorre;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }    

    public ArrayList<Caixa> getTorre() {
        return caixasDaTorre;
    }

    public void setTorre(ArrayList<Caixa> Torre) {
        this.caixasDaTorre = Torre;
    }
    
}
