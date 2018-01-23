/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clarke_Wright;

import Objetos.Rota;
import Objetos.Nodo;
import Objetos.Container;
import Objetos.Pedido;
import GeorgeRobinson.GeorgeRobinson;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Matheus
 */
public class Clarke_Wright { 
    
    ArrayList <Rota> rota = new ArrayList();
    ArrayList <Nodo> tabEconomia = new ArrayList();
    
    GeorgeRobinson gr = new GeorgeRobinson();
    DistanciaWS dist = new DistanciaWS();
    
    static int alturaLim = 2200;
    static int larguraLim = 2130;
    static int profundidadeLim = 11000;
    static String Central  = "Avenida Amazonas, 7675 - Nova Gameleira, Belo Horizonte - MG";
    
    public Double[][] matrizDist(ArrayList<Pedido> p, Double mDist[][]){
        /*                  -------- Monta a matriz de endereços --------  
        
                A posição [i][i] da matriz corresponde a distância entre a central e o cliente. 
                Todas as outras posições equivalem as distâncias entre clientes.
        */
        Random gerador = new Random();
        for (int i=0; i<p.size(); i++){
            for (int j=0; j<p.size(); j++){   
                if(i==j){             
                    //Pesquisa a distância entre a central e o pedido
                    String d = dist.calcular(Central, p.get(i).getEndereco());
                    //Remove os últimos 3 caracteres da string obtida:
                    d = d.substring(0, d.length()-3);
                    double aux = Double.parseDouble(d);//gerador.nextDouble() * 100 +10;
                    mDist[i][j]=aux;
                }
                else{
                    //Pesquisa a distância entre os pedidos:
                    String d = dist.calcular(p.get(i).getEndereco(), p.get(j).getEndereco());
                    //Remove os últimos 3 caracteres da string obtida:
                    d = d.substring(0, d.length()-3);
                    //System.out.printf("%s ", d);
                    double aux = Double.parseDouble(d); //gerador.nextDouble() * 100 +10;
                    //System.out.println(aux);
                    mDist[i][j]=aux;
                }
            }
        }
        return mDist;
    }
    
    public void tabelaEconomia(ArrayList<Pedido> p, Double[][] mDist/*, ArrayList<Rota> rota*/){
        /*                  -------- Monta a tabela de economias --------  
        
                    A tabela de economia é montada com base nas rotas, quando o número de rotas for igual ao número de pedidos, 
        isso significa que é o primeiro loop do algoritmo. Essa lista deve ser montada novamente toda vez que uma rota for 
        unificada com outra. Visto que isso influencia no cálculo da economia. 
                    Somente os clientes que estão nas extremidades das rotas podem influenciar no cálculo, uma vez que é a 
        partir deles que a rota será unida com outra.        
                Sentido da viagem: O->A->B->O sendo O a central (origem)
        */
        
        //Limpa a lista de economias para que ela possa ser refeita:
        for(int i=0; i<tabEconomia.size(); i++) tabEconomia.remove(i); 
           
        if(rota.isEmpty()){//Qd a rota está vazia
            
            //Quando ainda nao foi iniciada nenhuma rota
            for(int i=0; i<p.size(); i++){
                for (int j=0; j<p.size(); j++){
                    
                    if(j==i) continue;//Caso seja o mesmo endereço de origem e destino, ele ignora.
                    
                    Nodo n = new Nodo();
                    
                    int id_i = p.get(i).getId();
                    int id_j = p.get(j).getId();
                    
                    n.setA(id_i);
                    n.setB(id_j);
                    double dist_pI = mDist[id_i][id_i];
                    double dist_pJ = mDist[id_j][id_j];
                    double dist_pIpJ = mDist[id_i][id_j];
                    
                    double economia = (dist_pI + dist_pJ) - dist_pIpJ;
                    n.setEconomia(economia);
                    tabEconomia.add(n);
                }
            }
        }
        else{
            //Quando já há alguma rota iniciada
            for(int i=0; i<p.size(); i++){
                
                int id_rotaI = p.get(i).getIdRota();
                
                if(id_rotaI!=-1){//Se i estiver no meio de uma rota, ele ignora
                    int primeiro_Ped = rota.get(id_rotaI).getPedidosAtendidos().get(0).getId();
                    int n = rota.get(id_rotaI).getPedidosAtendidos().size()-1;
                    int ultimo_Ped = rota.get(id_rotaI).getPedidosAtendidos().get(n).getId();
                    if (!(primeiro_Ped == id_rotaI || ultimo_Ped == id_rotaI)) continue;
                }
                
                for(int j=0; j<p.size(); j++){
                    //id do pedido
                    int id_i = p.get(i).getId();
                    int id_j = p.get(j).getId();
                    
                    int id_rotaJ = p.get(j).getIdRota();
                                        
                    if(i==j) continue;
                    else if(id_rotaJ!=-1){//Se j estiver no meio de uma rota, ele ignora
                        int primeiro_Ped = rota.get(id_rotaJ).getPedidosAtendidos().get(0).getId();
                        int n = rota.get(id_rotaJ).getPedidosAtendidos().size()-1;
                        int ultimo_Ped = rota.get(id_rotaJ).getPedidosAtendidos().get(n).getId();
                        if (!(primeiro_Ped == id_rotaJ || ultimo_Ped == id_rotaJ)) continue;
                    }
                    
                    Nodo n = new Nodo();
                    n.setA(id_i);
                    n.setB(id_j);
                    
                    if(id_rotaI==-1 && id_rotaJ==-1){
                        //Caso ambos não estejam presentes em uma rota
                        double dist_pI = mDist[id_i][id_i];
                        double dist_pJ = mDist[id_j][id_j];
                        double dist_pIpJ = mDist[id_i][id_j];

                        double economia = (dist_pI + dist_pJ) - dist_pIpJ;
                        n.setEconomia(economia);
                        
                    }else if(id_rotaI!=-1 && id_rotaJ!=-1){   
                        if(id_rotaI==id_rotaJ) continue; //não podem estar na mesma rota
                        //Caso ambos estejam na extremidade de uma rota
                        int n_I = rota.get(id_rotaI).getPedidosAtendidos().size()-1;
                        int n_J = rota.get(id_rotaJ).getPedidosAtendidos().size()-1;
                        
                        int inI = rota.get(id_rotaI).getPedidosAtendidos().get(0).getId();
                        int outI = rota.get(id_rotaI).getPedidosAtendidos().get(n_I).getId();
                        int inJ = rota.get(id_rotaJ).getPedidosAtendidos().get(0).getId();
                        int outJ = rota.get(id_rotaJ).getPedidosAtendidos().get(n_J).getId();
                        
                        double dist_pIout_pJin = mDist[outI][inJ];
                        double dist_pJout = mDist[outJ][outJ];
                        double dist_pIin = mDist[inI][inI];
                        double distPer_RotaJ = rota.get(id_rotaJ).getDistPercorrida();
                        double distPer_RotaI = rota.get(id_rotaI).getDistPercorrida();
                        
                        double aux_A = dist_pIin + dist_pJout;
                        double aux_B = dist_pIout_pJin + distPer_RotaJ + distPer_RotaI;                        
                        double economia = aux_A - aux_B;
                        
                        n.setEconomia(economia);
                        
                    }else if(id_rotaI!=-1){                        
                        //Apenas i está presente na extremidade de uma rota
                        int n_I = rota.get(id_rotaI).getPedidosAtendidos().size()-1;
                        int inI = rota.get(id_rotaI).getPedidosAtendidos().get(0).getId();
                        int outI = rota.get(id_rotaI).getPedidosAtendidos().get(n_I).getId();
                        
                        double dist_pIin = mDist[inI][inI];
                        double dist_pJ = mDist[id_j][id_j];
                        double dist_pIout_pJ = mDist[outI][j];
                        double distPer_RotaI = rota.get(id_rotaI).getDistPercorrida();
                        
                        double aux_A = dist_pIin + dist_pJ;
                        double aux_B = dist_pIout_pJ + distPer_RotaI;
                        double economia = aux_A - aux_B;
                        
                        n.setEconomia(economia);
                        
                    }else{                        
                        //Apenas j está presente na extremidade de uma rota
                        int n_J = rota.get(id_rotaJ).getPedidosAtendidos().size()-1;
                        int outJ = rota.get(id_rotaJ).getPedidosAtendidos().get(n_J).getId();
                        
                        double dist_pI = mDist[id_i][id_i];
                        double dist_pJout = mDist[outJ][outJ];
                        double dist_pIpJ = mDist[id_i][id_j];
                        double distPer_RotaJ = rota.get(id_rotaJ).getDistPercorrida();
                        
                        double aux_A = dist_pI + dist_pJout;
                        double aux_B = dist_pIpJ + distPer_RotaJ;
                        double economia = aux_A - aux_B;                        
                        n.setEconomia(economia);
                        
                    }                                       
                    tabEconomia.add(n);
                }
            }
        }
    }
    
    public ArrayList<Rota> clarke(ArrayList<Pedido> p){
        
        for (int i=0; i<p.size(); i++) p.get(i).setIdRota(-1); //Inicialmente não há rotas com pedidos
        
        Double mDist[][] = new Double[p.size()][p.size()];
        
        mDist = matrizDist(p, mDist);//Gera a matriz de distâncias 
        boolean carregamento;
        int i=0;
        System.out.println("chega");
        int a = 0;
        while(true){
            tabEconomia.clear();
            tabelaEconomia(p, mDist);//Calcula a tabela de economias a cada união de rotas
            Random random = new Random();
            
            Collections.sort(tabEconomia);//Ordena
            carregamento = true;//Controle do carregamento
            if (a == 1) break;
            //for(i=0; i<tabEconomia.size(); i++){
            int cont = 1, div = 0; 
            while(!tabEconomia.isEmpty()){
                System.out.println("testando");
                if (cont == 4){
                    div++;
                    int f = i + (4*div);
                    if(f>tabEconomia.size()) break;
                }
                i = random.nextInt(4) + (4*div);
                System.out.println("i: "+i);
                
                //Id das duas rotas
                int id_rotaA = p.get(tabEconomia.get(i).getA()).getIdRota();
                int id_rotaB = p.get(tabEconomia.get(i).getB()).getIdRota();
                if (a == 1) break;
                //Id dos objetos                        
                int id_A = p.get(tabEconomia.get(i).getA()).getId();
                int id_B = p.get(tabEconomia.get(i).getB()).getId();
                
                if(id_rotaA==-1 && id_rotaB==-1){
                    //Os dois não possuem rota
                    System.out.println("não possui");
                    Container container = new Container(larguraLim, profundidadeLim, alturaLim);
                    container = gr.GeorgeRobinson(p.get(tabEconomia.get(i).getA()), container);
                    
                    if(container!=null){
                        container = gr.GeorgeRobinson(p.get(tabEconomia.get(i).getB()), container);
                        if(container!=null) carregamento = true;
                        else carregamento = false;
                    }
                    else carregamento = false;
                    
                    if(carregamento){//Se conseguiu fazer o carregamento
                        cont = 1;
                        Rota rt = new Rota();
                        rt.setID(rota.size());//Id da rota
                        System.out.println("> "+ container.getCamada().size());
                        //Adiciona os pedidos na rota:
                        rt.getPedidosAtendidos().add(p.get(tabEconomia.get(i).getA()));
                        rt.getPedidosAtendidos().add(p.get(tabEconomia.get(i).getB()));
                        
                        //Adiciona a id da rota nos pedidos
                        p.get(tabEconomia.get(i).getA()).setIdRota(rt.getID());
                        p.get(tabEconomia.get(i).getB()).setIdRota(rt.getID());
                        
                        //Seta a distância percorrida pela rota
                        rt.setDistPercorrida(mDist[id_A][id_B]);
                        
                        //Adiciona o container gerado na rota
                        rt.setContainer(container);
                        
                        rota.add(rt);//Adiciona a rota na lista de rotas
                        tabEconomia.remove(i);
                        System.out.println("saiu1");
                        break;
                    }else{
                        cont++;
                        System.out.println("Não carregou");
                    }
                    
                }else if(id_rotaA!=-1 && id_rotaB!=-1){
                    //Os dois possuem rota
                    System.out.println("possuem");
                    Container container = rota.get(id_rotaA).getContainer();
                    
                    for(int j=0; j<rota.get(id_rotaB).getPedidosAtendidos().size() /*&& carregamento*/; j++){
                        container = gr.GeorgeRobinson(rota.get(id_rotaB).getPedidosAtendidos().get(j), container);
                        if(container==null){
                            //Caso não consiga fazer o carregamento do pedido
                            carregamento = false;
                            break;
                        }
                    }
                    
                    if(carregamento){
                        cont = 1;
                        //Se conseguiu fazer o carregamento, faz a junção da rota B em A
                        int ultimo_elRotaA = rota.get(id_rotaA).getPedidosAtendidos().size()-1;
                        int id_ultimo_elRotaA = rota.get(id_rotaA).getPedidosAtendidos().get(ultimo_elRotaA).getId();
                        int id_primeiro_elRotaB = rota.get(id_rotaB).getPedidosAtendidos().get(0).getId();
                        System.out.println(">> "+ container.getCamada().size());
                        
                        Rota r = rota.get(id_rotaA);
                        int size = rota.get(id_rotaB).getPedidosAtendidos().size();
                        
                        for(int x=0; x<r.getPedidosAtendidos().size(); x++)
                            System.out.println(r.getPedidosAtendidos().get(x).getId());
                        
                        for(int j=0; j<size; j++){
                            //Adiciona a Id da rota de A, nos pedidos da rota de B (que agora estarão em A)
                            Pedido pB = rota.get(id_rotaB).getPedidosAtendidos().get(j);//Id do ped da rota B
                            pB.setIdRota(id_rotaA);
                            
                            //Adiciona os pedidos de B em A
                            r.getPedidosAtendidos().add(pB);
                        }
                        
                        double dist_AB = mDist[id_ultimo_elRotaA][id_primeiro_elRotaB];
                         
                        /*Incrementa a distancia percorrida em A (que é a soma da distância das duas rotas, mais a distância 
                                entre elas)*/
                        double distPer_RotaA = r.getDistPercorrida();
                        double distPer_RotaB = rota.get(id_rotaB).getDistPercorrida();
                        
                        double novaDist_RtA = distPer_RotaA + distPer_RotaB + dist_AB;
                        r.setDistPercorrida(novaDist_RtA);                        
                        
                        r.setContainer(container);//Atualiza o container de A
                        
                        /* Alterando ID das rotas posteriores a B */
                        for(int x=id_rotaB+1; x<rota.size(); x++){                            
                            for(int k=0; k<rota.get(x).getPedidosAtendidos().size(); k++){
                                rota.get(x).getPedidosAtendidos().get(k).setIdRota(x-1);
                            }
                            rota.get(x).setID(x-1);
                        }                      
                        
                        rota.remove(rota.get(id_rotaB));//Remove a rota B da lista de rotas
                        if(id_rotaA>id_rotaB)
                            rota.remove(rota.get(id_rotaA-1));
                        else {
                            if(rota.size()==1) rota.remove(0);
                            else if (rota.size()>1) rota.remove(rota.get(id_rotaA));
                        }
                        r.setID(id_rotaA);
                        rota.add(r);
                        tabEconomia.remove(i);
                        System.out.println("saiu2");
                        break;
                    }else{
                        cont++;
                        System.out.println("Não carregou 1");
                    }
                    
                }else if(id_rotaA!=-1){
                    System.out.println("a possui");
                    //A possui rota  
                    
                    Container container = rota.get(id_rotaA).getContainer();
                    container = gr.GeorgeRobinson(p.get(tabEconomia.get(i).getB()), container);
                    
                    if(container!=null){
                        cont = 1;
                        //Caso tenha conseguido fazer o carregamento, B fará parte da rota de A. 
                        System.out.println(">>> "+ container.getCamada().size());
                        //Adiciona os pedidos na rota:
                        rota.get(id_rotaA).getPedidosAtendidos().add(p.get(tabEconomia.get(i).getB()));
                        
                        //Seta a id da rota de A em B. 
                        p.get(tabEconomia.get(i).getB()).setIdRota(id_rotaA);
                        
                        int ultimo_elRotaA = rota.get(id_rotaA).getPedidosAtendidos().size()-1;
                        int id_ultimo_elRotaA = rota.get(id_rotaA).getPedidosAtendidos().get(ultimo_elRotaA).getId();
                        
                        double distPer_RotaA = rota.get(id_rotaA).getDistPercorrida();                        
                        //Incrementa a distância total percorrida pela rota
                        double dist_AB = mDist[id_ultimo_elRotaA][id_B];
                        double novaDist_RtA = distPer_RotaA + dist_AB;
                        rota.get(id_rotaA).setDistPercorrida(novaDist_RtA);
                        
                        //Atualiza o container gerado na rota
                        rota.get(id_rotaA).setContainer(container);
                        tabEconomia.remove(i);
                        System.out.println("saiu3");
                        break;
                    }else{
                        cont++;
                        System.out.println("Não carregou 2");
                    }
                    
                }else if(id_rotaB!=-1){
                    //B possui rota
                    System.out.println("b possui");
                    Container container = new Container(larguraLim, profundidadeLim, alturaLim);
                    container = gr.GeorgeRobinson(p.get(tabEconomia.get(i).getA()), container);
                    
                    if(container!=null){
                        //Caso consiga inserir A no container, tenta inserir todos os pedidos de B no mesmo container.
                        for(int j=0; j<rota.get(id_rotaB).getPedidosAtendidos().size(); j++){
                            //Adiciona os pedidos da rota B no container da nova rota
                            container = gr.GeorgeRobinson(rota.get(id_rotaB).getPedidosAtendidos().get(j), container);
                            if(container==null){
                                //Caso não consiga fazer o carregamento do pedido
                                carregamento = false;
                                break;
                            }
                        }
                    }
                    else carregamento = false;
                    
                    if(carregamento){//Caso seja possível o carregamento
                        cont = 1;
                        System.out.println(">>>> "+ container.getCamada().size());
                        //Atualiza a rota, colocando A na primeira posição
                        rota.get(id_rotaB).getPedidosAtendidos().add(0, p.get(tabEconomia.get(i).getA()));
                        
                        //Seta a ID da rota de B em A
                        p.get(tabEconomia.get(i).getA()).setIdRota(id_rotaB);
                        
                        //Incrementa a distância total percorrida pela rota
                        int id_primeiro_elRotaB = rota.get(id_rotaB).getPedidosAtendidos().get(0).getId();
                        /*Como A agora é o primeiro elemento da rota, precisamos acrescentar a soma da distância deste 
                            para o antigo primeiro elemento da rota*/
                        double dist_AB = mDist[id_A][id_primeiro_elRotaB];
                        double distPer_RotaB = rota.get(id_rotaB).getDistPercorrida();
                        
                        rota.get(id_rotaB).setDistPercorrida(distPer_RotaB + dist_AB);
                        
                        //Atualiza o container gerado na rota
                        rota.get(id_rotaB).setContainer(container);
                        tabEconomia.remove(i);
                        System.out.println("saiu4");
                        break;
                    }else{
                        cont++;
                        System.out.println("Não carregou 3");
                    }
                    
                }else {
                    tabEconomia.remove(i);
                    return null;
                    //return false;
                }
                tabEconomia.remove(i);
            }
            
            System.out.println("loop");
            
            /*if(i==tabEconomia.size())/*Quando não conseguimos fazer mais nenhuma união de rotas, é por que já 
                chegamos na solução*/
                //break;*/
            if(tabEconomia.isEmpty()) break;
        }
        System.out.println();
        for(i=0; i<rota.size(); i++){
            System.out.println("A rota (" + i + ") percorre " + rota.get(i).getDistPercorrida() + "km e"
                    + " atende aos seguintes pedidos:");
            for(int j=0; j<rota.get(i).getPedidosAtendidos().size(); j++)
                System.out.println("\t-- Pedido (" + rota.get(i).getPedidosAtendidos().get(j).getId() + ")");
            int u = rota.get(i).getContainer().getCamada().size()-1;
            float in = rota.get(i).getContainer().getCamada().get(u).getY();
            float f = in + rota.get(i).getContainer().getCamada().get(u).getComprimento();
            float livre = rota.get(i).getContainer().getProfundidade()-f;
            System.out.println("\t\t**Tem " + f + " de ocupação e " + livre + " livres");
        }
        System.out.println();
        return rota;        
        //return true;
        
    }   
}
