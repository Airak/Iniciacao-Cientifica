package bpp;

import java.util.ArrayList;

/**
 *
 * @author Matheus
 */
public class Torre {
    
    int altura, largura, profundidade;
    int espacoDisponivelAtual, espacoDisponivel;
    int alturaLimite;
    int id;
    int cx, cy; // coordenadas x e y da inserção

    public int getCx() {
        return cx;
    }

    public void setCx(int cx) {
        this.cx = cx;
    }

    public int getCy() {
        return cy;
    }

    public void setCy(int cy) {
        this.cy = cy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAlturaLimite() {
        return alturaLimite;
    }

    public void setAlturaLimite(int alturaLimite) {
        this.alturaLimite = alturaLimite;
    }

    public int getSuperficieDisponivelAtual() {
        return espacoDisponivelAtual;
    }

    public void setSuperficieDisponivelAtual(int espacoDisponivelAtual) {
        this.espacoDisponivelAtual = espacoDisponivelAtual;
    }

    public int getSuperficieDisponivel() {
        return espacoDisponivel;
    }

    public void setSuperficieDisponivel(int espacoDisponivel) {
        this.espacoDisponivel = espacoDisponivel;
    }
    
    ArrayList <Caixa> caixasDaTorre = new ArrayList();

    public Torre() {
    }

    public int getAltura() {
        return altura;
    }

    public ArrayList<Caixa> getTorre() {
        return caixasDaTorre;
    }

    public void setTorre(ArrayList<Caixa> Torre) {
        this.caixasDaTorre = Torre;
    }

    public void setAltura(int altura) {
        this.altura = altura;
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
    
}
