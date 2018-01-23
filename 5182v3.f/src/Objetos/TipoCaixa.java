package Objetos;

/**
 *
 * @author Matheus
 */
public class TipoCaixa implements Comparable <TipoCaixa> {
    
    //Tipo será igual a ID futuramente
    
    
    float menorDimen, maiorDimen;
    int qtdeDisponivel;
    float comprimento, largura, altura;
    int tipoCx, tipoAberto;    
    int x, y, z;

    public int getTipoAberto() {
        return tipoAberto;
    }

    public void setTipoAberto(int tipoAberto) {
        this.tipoAberto = tipoAberto;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getTipoCx() {
        return tipoCx;
    }

    public void setTipoCx(int tipo) {
        this.tipoCx = tipo;
    }

    public TipoCaixa() {
    }

    public float getMenorDimen() {
        return menorDimen;
    }

    public void setMenorDimen(float menorDimen) {
        this.menorDimen = menorDimen;
    }

    public float getMaiorDimen() {
        return maiorDimen;
    }

    public void setMaiorDimen(float maiorDimen) {
        this.maiorDimen = maiorDimen;
    }

    public int getQtdeDisponivel() {
        return qtdeDisponivel;
    }

    public void setQtdeDisponivel(int disponibilidade) {
        this.qtdeDisponivel = disponibilidade;
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
    
    public int compareTo(TipoCaixa caixa) {
        
        //Ordena as caixas em ordem decrescente de acordo com a maior das menores dimensões
        if(getMenorDimen()<caixa.getMenorDimen())
            return 1;
        else if(getMenorDimen()>caixa.getMenorDimen())
            return -1;
        //Ordena as caixas em ordem decrescente de acordo com a disponibilidade (caso de empate)
        else if(getQtdeDisponivel()<caixa.getQtdeDisponivel())
            return 1;
        else if(getQtdeDisponivel()>caixa.getQtdeDisponivel())
            return -1;
        //Ordena as caixas em ordem decrescente de acordo com a maior das maiores dimensões (caso de empate)
        else if(getMaiorDimen()<caixa.getMaiorDimen())
            return 1;
        else if(getMaiorDimen()>caixa.getMaiorDimen())
            return -1;
        else 
            return 0;
    }
    
    
}
