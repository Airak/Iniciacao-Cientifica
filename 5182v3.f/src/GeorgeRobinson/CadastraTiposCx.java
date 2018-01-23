/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeorgeRobinson;

import Objetos.TipoCaixa;
import Objetos.Pedido;
import java.util.ArrayList;

/**
 *
 * @author Matheus
 */
public class CadastraTiposCx {
    
    
    TratamentoCaixas tc = new TratamentoCaixas();
    Pedido p = new Pedido();
    
    public TipoCaixa cadastroCaixas(int n){
        
        TipoCaixa tipo = new TipoCaixa();
        tipo.setTipoCx(n);
        
        if(n==1){   
            
            tipo.setComprimento(785);
            tipo.setLargura(139);
            tipo.setAltura(273);
            tipo.setQtdeDisponivel(400);//400
            return tipo;
        }else if(n==2){
            
           tipo.setComprimento(901);
           tipo.setLargura(185);
           tipo.setAltura(195);
           tipo.setQtdeDisponivel(160);//160
           return tipo; 
        }else if(n==3){  
            
            tipo.setComprimento(901);
            tipo.setLargura(195);
            tipo.setAltura(265);
            tipo.setQtdeDisponivel(40);//40
            return tipo;  
            
        }else if(n==4){  
            
           tipo.setComprimento(1477);
           tipo.setLargura(135);
           tipo.setAltura(195);
           tipo.setQtdeDisponivel(40);//40
           return tipo;
        }else if(n==5){ 
            
            tipo.setComprimento(614);
            tipo.setLargura(480);
            tipo.setAltura(185);
            tipo.setQtdeDisponivel(8);//8
            return tipo;
        }else if(n==6){ 
            
            tipo.setComprimento(400);
            tipo.setLargura(400);
            tipo.setAltura(135);
            tipo.setQtdeDisponivel(16);//16
            return tipo;
        }else if(n==7){ 
            
            tipo.setComprimento(264);
            tipo.setLargura(400);
            tipo.setAltura(400);
            tipo.setQtdeDisponivel(80);//80
            return tipo;
        }        
        else if(n==8){ 
            
            tipo.setComprimento(385);
            tipo.setLargura(365);
            tipo.setAltura(290);
            tipo.setQtdeDisponivel(40);//40
            return tipo; 
        }      
        return null;
    }
    
}
