package bpp;

import java.util.ArrayList;

/**
 *
 * @author Matheus
 */
public class Pedido {
    
    int id;
    String endereco;
    ArrayList <Torre> tr = new ArrayList();
    ArrayList <Caixa> cx = new ArrayList();

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

    public ArrayList<Caixa> getCx() {
        return cx;
    }

    public void setCx(ArrayList<Caixa> cx) {
        this.cx = cx;
    }
    
    
    
}
