package bpp;

/**
 *
 * @author Matheus
 */
public class Caixa implements Comparable <Caixa> {
    
    int largura, profundidade, peso, altura, resistencia, id, pressao;

    Caixa() {
    }

    public int getPressao() {
        return pressao;
    }

    public void setPressao(int pressao) {
        this.pressao = pressao;
    }

    public Caixa(int id) {
        this.id = id;
    }

    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public int getProfundidade() {
        return profundidade;
    }

    public void setProfundidade(int profundidade) {
        this.profundidade = profundidade;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int compareTo(Caixa caixa) {
        
        //Ordena as caixas em ordem decrescente de largura, utilizando a Profundidade como critério de desempate
        if(getLargura()<caixa.getLargura())
            return 1;
        else if(getLargura()>caixa.getLargura())
            return -1;
        else if(getProfundidade() < caixa.getProfundidade())
            return 1;
        else if(getProfundidade() > caixa.getProfundidade())
            return -1;
        else if(getPressao() < caixa.getPressao())
            return 1;
        else if(getPressao() > caixa.getPressao())
            return -1;
        else 
            return 0;
        //return 0;
    }
    
    
}
