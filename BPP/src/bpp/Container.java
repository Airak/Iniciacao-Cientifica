package bpp;

import java.util.ArrayList;

/**
 *
 * @author Matheus
 */
public class Container {
    
    int largura, profundidade, altura;
    Double distanciaPercorrida;
    ArrayList<Torre> torre = new ArrayList();
    ArrayList<Pedido> pedido = new ArrayList();
    ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();

    public Double getDistanciaPercorrida() {
        return distanciaPercorrida;
    }

    public void setDistanciaPercorrida(Double distanciaPercorrida) {
        this.distanciaPercorrida = distanciaPercorrida;
    }

    public ArrayList<Pedido> getPedido() {
        return pedido;
    }

    public void setPedido(ArrayList<Pedido> pedido) {
        this.pedido = pedido;
    }

    public Container(int largura, int profundidade) {
        
        this.profundidade=profundidade;
        this.largura=largura;
        initArray(matrix, profundidade, largura);
        
    }
    
    public static void initArray(ArrayList<ArrayList<Integer>> matrix, int prof, int larg){
        
        for(int linha=0; linha<larg; linha++){
            matrix.add(linha, new ArrayList<Integer>());
            for(int coluna=0; coluna<prof; coluna++){
                matrix.get(linha).add(coluna, 0);                
            }
        }       
        
    }

    public ArrayList<ArrayList<Integer>> getMatrix() {
        return matrix;
    }

    public void setMatrix(ArrayList<ArrayList<Integer>> matrix) {
        this.matrix = matrix;
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

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public ArrayList<Torre> getTorre() {
        return torre;
    }

    public void setTorre(ArrayList<Torre> torre) {
        this.torre = torre;
    }
    
}
