/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Objetos.TipoCaixa;
import Objetos.Caixa;
import Clarke_Wright.Clarke_Wright;
import GeorgeRobinson.CadastraTiposCx;
import GeorgeRobinson.GeorgeRobinson;
import Objetos.Pedido;
import Objetos.Rota;
import java.util.ArrayList;

/**
 *
 * @author Matheus
 */
public class Main {
    
    public static void main(String[] args) {
        
        CadastraTiposCx cr = new CadastraTiposCx();
        GeorgeRobinson GR = new GeorgeRobinson();
        Clarke_Wright CW = new Clarke_Wright();
        Pedido p;
        ArrayList <Pedido> ped = new ArrayList();        
        
        for(int i=1; i<=7; i++){
            p = new Pedido();
            TipoCaixa tipo = cr.cadastroCaixas(i);
            for(int j=0; j<tipo.getQtdeDisponivel(); j++){
                Caixa cx = new Caixa();
                cx.setAltura(tipo.getAltura());
                cx.setComprimento(tipo.getComprimento());
                cx.setId(p.getCxDisponiveis().size());
                cx.setLargura(tipo.getLargura());
                cx.setTipoCaixa(tipo.getTipoCx());
                p.getCx().add(cx);
            }
            p.setId(i-1);
            if(i==1)p.setEndereco("Avenida Luiz Paulo Franco, 421 - Belvedere, Belo Horizonte - MG");
            else if (i==2) p.setEndereco("Rua Antonio de Padua Pinto, 116 - Santa Helena, Contagem - MG");
            else if (i==3) p.setEndereco("Rua Paraiba, 1465 - Funcionarios, Belo Horizonte - MG");
            else if (i==4) p.setEndereco("Avenida Sinfronio Brochado, 550 - Barreiro, Belo Horizonte - MG");
            else if (i==5) p.setEndereco("Rua Oriente, 609, Serra, Belo Horizonte - MG");
            else if (i==6) p.setEndereco("Avenida do Contorno, 7038 - Lourdes, Belo Horizonte - MG");
            else if (i==7) p.setEndereco("Rua Monsenhor Bicalho, 1129, Eldorado, Contagem - MG");
            else if (i==8) p.setEndereco("Avenida Tiradentes, 2956 - Cidade Industrial - 32230-O20 - Contagem - MG");
            p.getTp().add(tipo);
            p.setTp(p.getTp());
            ped.add(p);
        }
        
        for (int i=0; i<ped.size(); i++){
            System.out.println("Ped("+ped.get(i).getId()+"): " + ped.get(i).getEndereco() + 
                        " qtde caixas: " + ped.get(i).getCx().size());            
        }
        
        ArrayList <Rota> rotaFixa = CW.clarke(ped);
        double distFixa = 0;
        for(int i = 0; i<rotaFixa.size(); i++){
            distFixa =+ rotaFixa.get(i).getDistPercorrida();
        }
        
        for(int i=0; i<20; i++){
            ArrayList <Rota> rotaAux = CW.clarke(ped);
            double distAux = 0;
            for(int j = 0; j<rotaAux.size(); j++){
                distAux =+ rotaAux.get(j).getDistPercorrida();
            }
            if(distAux<distFixa){
                distFixa = distAux;
                rotaFixa = rotaAux;
            }
        }
        System.out.println("Terminou!!");
        //GR.GeorgeRobinson(p, container);
        
    }    
}
