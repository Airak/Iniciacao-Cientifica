/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

/**
 *
 * @author Matheus
 */
public class Nodo implements Comparable <Nodo>{
    
    int A, B;
    Double economia;

    public int getA() {
        return A;
    }

    public void setA(int A) {
        this.A = A;
    }

    public int getB() {
        return B;
    }

    public void setB(int B) {
        this.B = B;
    }

    public Double getEconomia() {
        return economia;
    }

    public void setEconomia(Double economia) {
        this.economia = economia;
    }
    
    public int compareTo(Nodo nodo) {
        
        //Ordena as caixas em ordem decrescente de acordo com a maior das menores dimensões
        if(getEconomia()<nodo.getEconomia())
            return 1;//-1
        else 
            return -1;//1
    }
    
    
}
