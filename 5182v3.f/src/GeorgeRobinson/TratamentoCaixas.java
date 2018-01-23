/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeorgeRobinson;

import Objetos.TipoCaixa;
import Objetos.Caixa;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Matheus
 */
public class TratamentoCaixas {   

    public TratamentoCaixas() {
    }
    
    Caixa c = new Caixa();
    
    public void menorDim(ArrayList<TipoCaixa> cx){
        
        for (TipoCaixa cx1 : cx) {
            cx1.setMenorDimen(cx1.getComprimento());
            
            if (cx1.getMenorDimen()> cx1.getLargura()) {
                cx1.setMenorDimen(cx1.getLargura());
            }
            
            if (cx1.getMenorDimen() > cx1.getAltura()){
                cx1.setMenorDimen(cx1.getAltura());
            }
        }
    }
    
    public float maiorDeTresValores(float altura, float comprimento, float largura){

        float maior = comprimento;
        
        if (maior < largura) maior = largura;
        if(maior < altura) maior = altura;
        
        return maior;
    }
    public float menorDeTresValores(float altura, float comprimento, float largura){
        
        float menor = comprimento;
        
        if (menor > largura) menor = largura;
        if(menor > altura) menor = altura;
        
        return menor;
    }
    
    public void maiorDim(ArrayList<TipoCaixa> cx){
        for (TipoCaixa cx1 : cx) {
            cx1.setMaiorDimen(cx1.getComprimento());
            
            if (cx1.getMaiorDimen() < cx1.getLargura()) {
                cx1.setMaiorDimen(cx1.getLargura());
            }
            if(cx1.getMaiorDimen()  < cx1.getAltura()){
                cx1.setMaiorDimen(cx1.getAltura());
            }
        }
    }
    
    public void ListaPrioridade(ArrayList<TipoCaixa> cx){
        
        menorDim(cx);
        maiorDim(cx);
        Collections.sort(cx);
        
    }   
    
    
}
