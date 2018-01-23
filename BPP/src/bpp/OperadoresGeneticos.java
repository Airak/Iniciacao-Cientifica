/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bpp;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Matheus
 */
public class OperadoresGeneticos {
    
    Double PC=0.7;
    Double MT=0.08;
    
    public void Reproducao(Geracao g1, Geracao GM){
        //Recebe a geração antiga e a próxima como parâmetro, tem como objetivo clonar a melhor solução para a próxima geração
        
        double menor=g1.getSolucao().get(0).getDistanciaTotal();
        int indiceMenor=0;
        
        
        for(int i=1; i<g1.getSolucao().size(); i++){
            if(menor>g1.getSolucao().get(i).getDistanciaTotal()){
                menor=g1.getSolucao().get(i).getDistanciaTotal();
                indiceMenor=i;
            }
        }
        
        GM.getSolucao().add(g1.getSolucao().get(indiceMenor));
        imprimeS(g1.getSolucao().get(indiceMenor));
        
        
    }
    
    public void CruzamentoOX(Solucao p1, Solucao p2, Geracao GM){
        
        //Recebe os dois pais e a próxima geração como parâmetro
        //Cruzamento OX
        
        Random gerador = new Random();
        int x,y;
        Solucao f1 = new Solucao();
        Solucao f2 = new Solucao();
         //Geracao GM = new Geracao();
        ArrayList <Pedido> temp1 = new ArrayList();
        ArrayList <Pedido> temp2 = new ArrayList();
        
        if(Probabilidade()>=PC){
            //0.7
            
            //System.out.println("entra");
        
            x = y = (gerador.nextInt(p1.getPedido().size()/2)+1);//Gera um número entre 1 e a metade+1 do tamanho da ista de pedidos
            
            while(y <= x){
                //O ponto de segundo corte deve ser maior que o do primeiro                
                y = gerador.nextInt(p1.getPedido().size());
                while(y==p1.getPedido().size()-1)
                    //para melhorar o cruzamento também optei pelo ponto de segundo corte ser diferente do final da lista original
                    y = gerador.nextInt(p1.getPedido().size());
            }
            
            System.out.println("pontos de corte: x: " + x +" y: " + y);
            
            for(int i=y+1; i<=p1.getPedido().size(); i++){
                //Começa a montar as listas temporárias a partir do segundo corte (y)
                          
                if(i==p1.getPedido().size())
                    i=0;//Se chegou ao final da lista de pedidos vá para o começo (fila circular)
                
                //Insira nas listas temporárias até que se chegue ao final do segundo corte (quando sai do laço)
                temp1.add(p1.getPedido().get(i));
                temp2.add(p2.getPedido().get(i));
                
                if(i==y)
                    break;
                
            }
            
            
            //System.out.println(temp1.size());
            //System.out.println(temp2.size());
            
            for(int i=x; i<=y; i++){
                //Percorre a região dos dois cortes para remover os repetidos 
                for(int j=0; j<p1.getPedido().size(); j++){
                    if(p1.getPedido().get(i)==temp2.get(j)){
                        f1.getPedido().add(p1.getPedido().get(i));//Adiciona os elementos da região intermediária de p1 em f1
                        temp2.remove(j);//Rmv os el. da R.I. de p1 de temp2, eliminando assim a possibilidade de pedidos repetidos
                        break;
                    }
                }
            }
            for(int i=x; i<=y; i++){
                for(int j=0; j<p2.getPedido().size(); j++){
                    if(p2.getPedido().get(i)==temp1.get(j)){
                        f2.getPedido().add(p2.getPedido().get(i));
                        temp1.remove(j);
                        break;
                    }
                }
            }
            
            //for(int i=0; i< temp1.size(); i++)
              // System.out.println(temp1.get(i).getId());
            //System.out.println();
            //System.out.println(f1.getPedido().size());
            //System.out.println(f2.getPedido().size());
            
            // ---------------------ok
            
            
            int diferencaDoFim = (p1.getPedido().size() -(y+1));
            int aux=f1.getPedido().size();
            
            //System.out.println("ainda tem: "+diferencaDoFim);
            //System.out.println(p1.getPedido().size());
            
            for(int i=0; i<diferencaDoFim; i++){
                f1.getPedido().add(temp2.get(0));
                temp2.remove(0);
                f2.getPedido().add(temp1.get(0));
                temp1.remove(0);
            }
            if(x!=0){
                //Caso o primeiro corte não comece em 0 então será necessário que se insira pedidos no começo também
                for(int i=0; !temp1.isEmpty() && !temp2.isEmpty() ; i++){
                    f1.getPedido().add(i,temp2.get(0));
                    temp2.remove(0);
                    f2.getPedido().add(i,temp1.get(0));
                    temp1.remove(0);
                }                
            }
            
        }
        
        //System.out.println("chega");
        
        GM.getSolucao().add(f1);
        GM.getSolucao().add(f2);
        
        //System.out.println(GM.getSolucao().size());
        //System.out.println(f1.getPedido().size());
        System.out.println("F1: ");imprimeS(GM.getSolucao().get(0));
        System.out.println("F2: ");imprimeS(GM.getSolucao().get(1));
        
    }
    
    public void imprimeS(Solucao sl){
        
        System.out.printf("\n{ ");
        for(int i=0; i<sl.getPedido().size(); i++)
            System.out.printf(sl.getPedido().get(i).getId()+ " ");
        System.out.printf("}\n");
        
    }
    
     public void CruzamentoPMX(Solucao p1, Solucao p2, Geracao GM){
         
        Random gerador = new Random();
        int x,y;
        Solucao f1 = new Solucao();
        Solucao f2 = new Solucao();
         //Geracao GM = new Geracao();
        ArrayList <Pedido> temp1 = new ArrayList();
        ArrayList <Pedido> temp2 = new ArrayList();
        
        if(Probabilidade()>=PC){
            
            x = y = (gerador.nextInt(p1.getPedido().size()/2)+1);//Gera um número entre 1 e a metade+1 do tamanho da ista de pedidos
            
            while(y <= x){
                //O ponto de segundo corte deve ser maior que o do primeiro                
                y = gerador.nextInt(p1.getPedido().size());
                while(y==p1.getPedido().size()-1)
                    //para melhorar o cruzamento também optei pelo ponto de segundo corte ser diferente do final da lista original
                    y = gerador.nextInt(p1.getPedido().size());
            }
            
            System.out.println("pontos de corte: x: " + x +" y: " + y);
            
            for(int i=0; i<p1.getPedido().size(); i++){
                if(i<x || i>y){
                    //Copia as partes fora da região dos dois cortes para os seus respectivos filhos
                    f1.getPedido().add(p1.getPedido().get(i));
                    f2.getPedido().add(p2.getPedido().get(i));
                }
                else if(i>=x && x<=y){
                    //A região entre os dois cortes é copiada para o filho oposto
                    f1.getPedido().add(p2.getPedido().get(i));
                    f2.getPedido().add(p1.getPedido().get(i));
                }
            }
            
            //System.out.println("tam:"+f1.getPedido().size());
            
            for(int j=x; j<=y; j++){
                for(int i=0; i<f1.getPedido().size(); i++){
                    if(i<x || i>y){
                        //Procura por repetições
                        if(f1.getPedido().get(i)==p2.getPedido().get(j)){
                            temp1.add(p1.getPedido().get(i));
                            //Lista de repetidos de f1
                        }
                        if(f2.getPedido().get(i)==p1.getPedido().get(j)){
                            temp2.add(p2.getPedido().get(i));
                        }
                    }
                }
            }
            
            //System.out.println("rep:"+temp1.size());
            
            for(int j=0; j<temp1.size(); j++){
                for(int i=0; i<p1.getPedido().size(); i++){
                    if(i<x || i>y){
                        //Realiza a troca dos pedidos repetidos
                        if(f1.getPedido().get(i)==temp1.get(j)){
                            f1.getPedido().set(i, temp2.get(j));
                        }
                        if(f2.getPedido().get(i)==temp2.get(j)){
                            f2.getPedido().set(i, temp1.get(j));
                        }
                    }
                }
            }
        }
        
        GM.getSolucao().add(f1);
        GM.getSolucao().add(f2);
        
        //System.out.println(GM.getSolucao().size());
        //System.out.println(f1.getPedido().size());
        System.out.println("F1: ");imprimeS(GM.getSolucao().get(0));
        System.out.println("F2: ");imprimeS(GM.getSolucao().get(1));
         
         
         
     }
    
    public void Mutacao(Solucao p1){
        
        Random gerador = new Random();
        int x, y;
        Pedido temp;
        
        if(Probabilidade()>=MT){//Entre 5 e 10%
            
            x = y = gerador.nextInt(p1.getPedido().size());
            
            while(x==y){
                y = gerador.nextInt(p1.getPedido().size());
            }
            
            temp = p1.getPedido().get(x);
            p1.getPedido().set(x, p1.getPedido().get(y));
            p1.getPedido().set(y, temp);
        }
        
    }
    
    public double Probabilidade(){
        
        Random gerador = new Random(); 
        
        return gerador.nextDouble();        
    }
    
    public void selecaoPorTorneio(Geracao GA,Geracao GP, int n){
        
        //Considerando k=2
        int x,y;
        ArrayList <Solucao> temp = new ArrayList();
        
        Random gerador = new Random(); 
        
        while(n>0){
            n--;
            
            x = y = gerador.nextInt(GA.getSolucao().size());
            
            while(x==y){
                y = gerador.nextInt(GA.getSolucao().size());
            }
            
            if(GA.getSolucao().get(x).getDistanciaTotal()<GA.getSolucao().get(y).getDistanciaTotal()){
                temp.add(GA.getSolucao().get(x));
            }
            else{
                temp.add(GA.getSolucao().get(y));
            }            
            
        }
        
        while(!temp.isEmpty()){
            GP.getSolucao().add(temp.get(0));
            temp.remove(0);
        }
    }
    
}
