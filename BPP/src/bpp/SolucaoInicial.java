package bpp;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Matheus
 */
public class SolucaoInicial {
    public ArrayList<Pedido> pd;
    public ArrayList <Integer> p;
    
    public void cadastroPedido(){
        
        pd = new ArrayList<Pedido>();        
        p = new ArrayList();
        
        for(int i=1; i<=5; i++)
            p.add(i);
        
        CadastroPedidos cp = new CadastroPedidos();
        Pedido ped = new Pedido();
        
        for (Integer p1 : p) {
            ped = cp.pedidosDisponiveis(p1);
            pd.add(ped);            
        }
        
        solucaoInicial(p, pd);
        
    }
    
    public void solucaoInicial( ArrayList<Integer> aux, ArrayList<Pedido> p){
        
        Random gerador = new Random();
        Geracao geracaoInicial = new Geracao();
        Solucao novaSolucao = new Solucao();
        
        novaSolucao.setId(0);
        
        while(aux.size()>0){
            
            //Gerando a Solução Inicial
            
            int pedidoAleatorio;
            
            if((aux.size()-1)>0)
                pedidoAleatorio = gerador.nextInt(aux.size()-1);//Se houver mais de um pedido disponível ele pega um aleatoriamente
            else
                pedidoAleatorio = 0;//Se só houver um pedido disponível o único que ele pode pegar é o da posição 0           
            
            int p_aux = aux.get(pedidoAleatorio).intValue();
            aux.remove(pedidoAleatorio);
            novaSolucao.getPedido().add(p.get(p_aux-1));
            
        }
        
        System.out.println("\nTamanho da solução Inicial: "+novaSolucao.getPedido().size());
        
        System.out.printf("\nSolução inicial: { ");
        for(int i=0; i<novaSolucao.getPedido().size(); i++)
            System.out.printf(novaSolucao.getPedido().get(i).getId()+ " ");
        System.out.printf("}\n\n");
        
        geracaoInicial.getSolucao().add(novaSolucao);
        
        for(int i=0; i<(geracaoInicial.getSolucao().get(0).getPedido().size()-1); i++){
            
            //Gera a família de soluções iniciais
            
            novaSolucao = new Solucao();
            novaSolucao.setId(i+1);
             
            for(int j=1; j<=geracaoInicial.getSolucao().get(i).getPedido().size(); j++){
                //Gera o novo membro                
                //Começa da posição 1 da solução anterior, visto que o primeiro elemento irá ficar ao final da próxima solução
                //A implementação foi feita como se fosse uma fila circular.
                
                if(j==geracaoInicial.getSolucao().get(i).getPedido().size()){ 
                    //Se já chegou ao final da solução anterior, add o primeiro elemento desta solução na última posição da nova
                     novaSolucao.getPedido().add(geracaoInicial.getSolucao().get(i).getPedido().get(0));
                }else
                    //Caso ainda não tenha chegado a última posição, insere normalmente os elementos
                    novaSolucao.getPedido().add(geracaoInicial.getSolucao().get(i).getPedido().get(j));
                
            }
            geracaoInicial.getSolucao().add(novaSolucao);
        }
        
        //Realiza o carregamento do pedido
            //Calcula a distancia 
        
        carregamentoDasGeracoes(geracaoInicial);
        
        //teste(geracaoInicial);
        //testeMut(geracaoInicial);
        //testeCl(geracaoInicial);
        
        System.out.println("x");
            
    }
    
    public void carregamentoDasGeracoes(Geracao gm){
        
        for(int i=0; i<gm.getSolucao().size(); i++){
            carrega(gm.getSolucao().get(i));   
            System.out.println("Solução ("+i+") ocupou "+gm.getSolucao().get(i).getContainer().size()+" container(s)"+
                    " e a distancia total é "+gm.getSolucao().get(i).getDistanciaTotal()+"km");           
        }        
    }
    public void teste(Geracao gm){
        
        OperadoresGeneticos OG = new OperadoresGeneticos();
        
        //Mutação:
        
        Geracao gp = new Geracao();
        
        //CruzamentoOX:
        
        System.out.println("P1: ");imprimeS(gm.getSolucao().get(0));
        System.out.println("P2: ");imprimeS(gm.getSolucao().get(1));
        //OG.CruzamentoOX(gm.getSolucao().get(0), gm.getSolucao().get(1), gp);
        OG.CruzamentoPMX(gm.getSolucao().get(0), gm.getSolucao().get(1), gp);
        //System.out.println(gp.getSolucao().size());
        //imprimeS(gp.getSolucao().get(0));
    }
    public void testeMut(Geracao gm){
        
        OperadoresGeneticos OG = new OperadoresGeneticos();
        
        //Mutação:
        
        for(int i=0; i<gm.getSolucao().size(); i++){  
            
            imprimeS(gm.getSolucao().get(i));
            OG.Mutacao(gm.getSolucao().get(i));
            imprimeS(gm.getSolucao().get(i));
            System.out.printf("-----");
            //carrega(gm.getSolucao().get(i));            
        }
    }
    
    public void testeCl(Geracao gm){
        
        OperadoresGeneticos OG = new OperadoresGeneticos();
        Geracao gp = new Geracao();
        
        //Mutação:
        Double cont=100.00;
        for(int i=0; i<gm.getSolucao().size(); i++){  
            
            gm.getSolucao().get(i).setDistanciaTotal(cont);
            cont+=200;
            //carrega(gm.getSolucao().get(i));            
        }
        gm.getSolucao().get(0).setDistanciaTotal(1050.00);
        
        OG.Reproducao(gm, gp);
    }
    
    
    
    public void imprimeS(Solucao sl){
        
        System.out.printf("\n{ ");
        for(int i=0; i<sl.getPedido().size(); i++)
            System.out.printf(sl.getPedido().get(i).getId()+ " ");
        System.out.printf("}\n");
        
    }
    
    public void carrega(Solucao sl){
        
        //System.out.println("entra");
        
        Carregamento c = new Carregamento();        
        Container cont = new Container(404, 300);
        Container cAux = new Container(404, 300);
        //System.out.println("gera container");

        //Ao final da execução calcula a distancia que cada container irá percorrer e pega a distancia total no final. 

        for(int i=0, aux=0; i<sl.getPedido().size(); i++){
            //System.out.println("pedidos");
            //Loop dos pedidos
            int j;
            for(j=0; j<sl.getPedido().get(i).getTr().size(); j++){
                //Loop das torres
                //System.out.println("torres");
                if(c.carregaArrayIterativo(sl.getPedido().get(i).getTr().get(j), cAux))
                    //Caso tenha inserido a torre com sucesso
                    //System.out.println("inseriu");
                    continue;
                else {
                    aux++;
                    break;
                }
            }
            //System.out.println("sai");
            if(j==sl.getPedido().get(i).getTr().size() && aux==0){
                //Se inseriu todas as torres de um pedido no container salva as modificações no container original
                //System.out.println("salva-----------");
                cAux.getPedido().add(sl.getPedido().get(i));
                cont=cAux;
            }else if (aux>0 || j!=sl.getPedido().get(i).getTr().size()){
                //Caso não seja possível inserir uma torre de um determinado pedido em um container
                //...para e começa a inserção em um novo a partir deste pedido.
                //System.out.println("xD---");
                sl.getContainer().add(cont);
                i--;
                cont = new Container(404, 300);
                cAux = new Container(404, 300);
            }
            aux=0;               
        }
        sl.getContainer().add(cont);//Adiciona o último container
        
        //System.out.println("Termina----------------");
        
        calculaDistancia(sl);
    }
    
    public void calculaDistancia(Solucao sl){
        
        String Origem = "Avenida Amazonas, 7675 - Nova Gameleira, Belo Horizonte - MG";
        DistanciaWS dist = new DistanciaWS();
        Double distancia, dt=0.0;
        
        for(int i=0; i<sl.getContainer().size(); i++){
            distancia=0.0;
            for(int j=sl.getContainer().get(i).getPedido().size()-1; j>0;j--){
                if(j==sl.getContainer().get(i).getPedido().size()-1){
                    System.out.println("\nOrigem: "+Origem);
                    System.out.println("Destino: "+sl.getContainer().get(i).getPedido().get(j).getEndereco());
                    String d = dist.calcular(Origem, sl.getContainer().get(i).getPedido().get(j).getEndereco());
                    
                    d = d.substring(0, d.length()-3);
                    
                    distancia=Double.parseDouble(d);
                    sl.getContainer().get(i).setDistanciaPercorrida(distancia);
                }
                else{
                    System.out.println("\nOrigem: "+sl.getContainer().get(i).getPedido().get(j+1).getEndereco());
                    System.out.println("Destino: "+sl.getContainer().get(i).getPedido().get(j).getEndereco());
                    String d = dist.calcular(sl.getContainer().get(i).getPedido().get(j+1).getEndereco(), sl.getContainer().get(i).getPedido().get(j).getEndereco());
                    
                    d = d.substring(0, d.length()-3);
                    distancia+=Double.parseDouble(d);
                    sl.getContainer().get(i).setDistanciaPercorrida(distancia);                    
                    
                }
            }
            dt+=distancia;
        }
        sl.setDistanciaTotal(dt);
        
    }
    
}
