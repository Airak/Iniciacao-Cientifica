
package bpp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author Matheus
 */
public class CadastroPedidos {
    
    Pedido p;
    Empilhamento emp;
    CadastroCaixas bd = new CadastroCaixas();   
    Caixa caixas = new Caixa();
    ArrayList <Pedido> pedido = new ArrayList();
    
    public Pedido pedidosDisponiveis(int id){
        
        p = new Pedido();
        emp = new Empilhamento();
        
        if(id==1){
            
            //pedido.add(p);
            int x[] = {5,3,4,4,1};
            p.setId(id);
            //Seta string
            for(int i=0; i<x.length; i++){
                
                caixas=bd.caixasDisponiveis(x[i]);
            
                if(caixas!=null){
                    p.getCx().add(caixas);                    
                }
                else{
                    caixas=bd.novaCaixa();
                    if(caixas!=null)
                        p.getCx().add(caixas);
                }                
            }
            
            emp.empilha(p.getCx(), p);
            p.setEndereco("Avenida Luiz Paulo Franco, 421 - Belvedere, Belo Horizonte - MG");
            return p;
        }
        if(id==2){
            int x[] = {5,12,1};
            p.setId(id);
            //Seta string
            for(int i=0; i<x.length; i++){
                
                caixas=bd.caixasDisponiveis(x[i]);
            
                if(caixas!=null)
                    p.getCx().add(caixas);
                else{
                    caixas=bd.novaCaixa();
                    if(caixas!=null)
                        p.getCx().add(caixas);
                }                
            }
            emp.empilha(p.getCx(), p);
            p.setEndereco("Rua Antonio de Padua Pinto, 116 - Santa Helena, Contagem - MG");
            return p;
        }
        if(id==3){
            int x[] = {5,3,4,4,1,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9};
            p.setId(id);
            //Seta string
            for(int i=0; i<x.length; i++){
                
                caixas=bd.caixasDisponiveis(x[i]);
            
                if(caixas!=null)
                    p.getCx().add(caixas);
                else{
                    caixas=bd.novaCaixa();
                    if(caixas!=null)
                        p.getCx().add(caixas);
                }                
            }
            emp.empilha(p.getCx(), p);
            p.setEndereco("Rua Paraiba, 1465 - Funcionarios, Belo Horizonte - MG");
            return p;
        }
        if(id==4){
            int x[] = {5,8,3,3,12,1,12};
            p.setId(id);
            //Seta string
            for(int i=0; i<x.length; i++){
                
                caixas=bd.caixasDisponiveis(x[i]);
            
                if(caixas!=null)
                    p.getCx().add(caixas);
                else{
                    caixas=bd.novaCaixa();
                    if(caixas!=null)
                        p.getCx().add(caixas);
                }                
            }
            emp.empilha(p.getCx(), p);
            p.setEndereco("Avenida Sinfronio Brochado, 550 - Barreiro, Belo Horizonte - MG");
            return p;
        }
        if(id==5){
            int x[] = {14,14};
            p.setId(id);
            //Seta string
            for(int i=0; i<x.length; i++){
                
                caixas=bd.caixasDisponiveis(x[i]);
            
                if(caixas!=null)
                    p.getCx().add(caixas);
                else{
                    caixas=bd.novaCaixa();
                    if(caixas!=null)
                        p.getCx().add(caixas);
                }                
            }
            emp.empilha(p.getCx(), p);
            p.setEndereco("Rua Oriente, 609, Serra, Belo Horizonte - MG");
            return p;
        }
        
        return null;
    }

    public ArrayList<Pedido> getPedido() {
        return pedido;
    }

    public void setPedido(ArrayList<Pedido> pedido) {
        this.pedido = pedido;
    }
    
    public void cadastroPedido(){
        
        p = new Pedido();
        emp = new Empilhamento();
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Introduza os dados da nova caixa: ");
        System.out.println("Id:  ");
        int id = sc.nextInt();
        p.setId(id);
        
        System.out.println("Quantidade de caixas adquiridas:  ");
        int qtde = sc.nextInt();        
        
        while(qtde>0){
            
            System.out.println("Id da caixa:  ");
            int idCx = sc.nextInt();
            caixas=bd.caixasDisponiveis(idCx);
            
            if(caixas!=null)
                p.getCx().add(caixas);
            else{
                caixas=bd.novaCaixa();
                if(caixas!=null)
                    p.getCx().add(caixas);
            }
            
            qtde--;
        }
        
        Collections.sort(p.getCx());
        emp.empilha(p.getCx(),p);
        
        
    }
}
