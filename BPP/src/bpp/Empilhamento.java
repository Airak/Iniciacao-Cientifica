package bpp;

import java.util.ArrayList;

/**
 *
 * @author Matheus
 */
public class Empilhamento {
    
    ArrayList<Torre> torre = new ArrayList();
    ArrayList<Caixa> cx = new ArrayList();
    
    public void empilha(ArrayList<Caixa> cx, Pedido p){

        //Recebe um ArrayList de caixas de um determinado pedido, o qual será a lista de caixas disponíveis para serem colocadas na torre de caixa
        int [] resistencia = new int[cx.size()];
        
        int cont=0, verificaResistencia=0, verificaFinal=0, guardaFinal=0, guardaFinalResistencia=0;
        Torre t = new Torre();
        //Carregamento c = new Carregamento();
        int id = 0;
        
        while(!cx.isEmpty()){//Quando o tamanho do ArrayList for =0 isso significará que todas as caixas foram colocadas dentro de uma torre
            
            if(t.getTorre().isEmpty()){
                //Insere o primeiro elemento da lista de caixas que se encontra ordenado pela largura em forma decrescente
                t.getTorre().add(cx.get(0));
                
                /*Insere a altura, profundidade e largura na torre, além de inserir a resistência em um vetor auxiliar para que se possa
                realizar as operações sem que seja necessário a alteração dos dados da caixa:*/
                id++;
                t.setId(id);
                t.setAlturaLimite(260);
                t.setAltura(cx.get(0).altura);
                t.setProfundidade(cx.get(0).profundidade);
                t.setLargura(cx.get(0).largura);
                t.setSuperficieDisponivel(cx.get(0).profundidade);//Seta o próximo espaço disponível
                resistencia[0]=cx.get(0).resistencia;
                
                verificaFinal++;    
                guardaFinal=cx.size();  
                cx.remove(cx.get(0));
                guardaFinalResistencia=0;
            }
            else{
                verificaFinal++; 
                if(cx.get(cont).profundidade <= t.profundidade){
                    //Compara a profundidade da caixa com a da última caixa inserida na Torre
                    
                    for(int i=0; i<resistencia.length; i++){
                        //Caso seja menor: Percorre a lista de caixas da torre para ver se a pressão que ela irá exercer sobre a torre é aceitável
                        if(resistencia[i]<cx.get(cont).pressao && resistencia[i]!=0){
                            verificaResistencia=1;
                            break;
                        }
                    }
                    if(verificaResistencia==0){
                        //Resistencia ok
                        //Verifica se com a inserção a altura da torre não será maior que a altura limite
                        if((t.getAltura()+cx.get(cont).altura)<=t.getAlturaLimite()){
                            //Altura ok
                            
                            for(int i=0; i<(guardaFinalResistencia+1);i++){
                                //Ela irá decrementar da resistência de cada caixa já inserida o equivalente a pressão que ela irá exercer.
                                if(resistencia[i]!=0){
                                    resistencia[i]-=cx.get(cont).pressao;
                                }
                            }
                            
                            //Verifica se a caixa será inserida no mesmo andar ou em um novo andar da torre:
                            if(t.getSuperficieDisponivelAtual()<=cx.get(cont).profundidade  /*|| (cx.size()!=1 && t.getTorre().get(cx.size()-1).getAltura()-cx.get(cont).getAltura())<=3*/){//Novo andar
                                t.setAltura((t.getAltura()+cx.get(cont).altura));// Atualizar a altura da torre
                                //Seta a superficie diponivel atual com o valor da próx.
                                t.setSuperficieDisponivelAtual(t.getSuperficieDisponivel());
                                //Seta a próxima superficie disponível com o valor da profundidade da caixa inserida
                                t.setSuperficieDisponivel(cx.get(cont).profundidade);
                            }
                            else{//Mesmo andar
                                //Aumenta a superfície disponível para inserção no próximo andar
                                t.setSuperficieDisponivel(t.getSuperficieDisponivel()+cx.get(cont).profundidade);
                                //Decrementa a profundidade da caixa a ser inserida na superfície disponível atual
                                t.setSuperficieDisponivelAtual(t.getSuperficieDisponivelAtual()-cx.get(cont).profundidade);
                            }
                            t.setProfundidade(cx.get(cont).profundidade);//Atualiza a profundidade da torre                            
                       
                            //t.setLargura(cx.get(cont).largura);//Atualiza a largura de torre
                            
                            t.getTorre().add(cx.get(cont));//Add a caixa na torre
                            
                            guardaFinalResistencia++;
                            resistencia[guardaFinalResistencia]=cx.get(cont).resistencia;
                            cx.remove(cx.get(cont));//Remove a caixa da lista de caixas disponíveis
                        }
                        else
                            cont++;
                    }
                    else
                        cont++;
                        //Sai, não pode ser inserida (pressão)
                    verificaResistencia=0;
                }
                else
                    cont++;
                    //Sai, não pode ser inserida (prof)
            }
            
            if(verificaFinal==guardaFinal || cont == cx.size()){
                //se já chegou na última posição da lista e ainda restam itens disponíveis
                
                torre.add(t);//Add a torre na lista de torres                
                t = new Torre();//Inicializa a nova torre
                cont=0;
                verificaFinal=0;
                for (int i=0; i<resistencia.length; i++)
                    resistencia[i]=0;
            }
            //Se a lista de caixas já foi inteiramente percorrida e ainda existem caixas disponíveis, cria uma nova torre para aquele pedido            

        }
        
        p.setTr(torre);
        
        //System.out.println("Qtde de torres geradas: "+torre.size());
        
        /*for (Torre torre1 : torre) {
            
            //System.out.println("Passa " + i);
            //c.carrega(torre.get(i));
            c.carregaArrayIterativo(torre1);
        }*/            
        
        //imprimeTorre(torre);
        //c.imprimeContainer();
        
    }
    
    public void imprimeTorre(ArrayList<Torre> torre){
        
        System.out.println("Total de torres: " +torre.size()+"\n");
        for(int i=0; i<torre.size(); i++){
            System.out.println("Torre " + torre.get(i).getId() + ":");
            System.out.println("Altura da torre: " + torre.get(i).getAltura());
            System.out.println("Profundidade da torre: " + torre.get(i).getProfundidade());
            System.out.println("Largura da torre: " + torre.get(i).getLargura());
            System.out.println("Total de caixas na torre: "+torre.get(i).caixasDaTorre.size());
            System.out.printf("Caixas (ID): ");
            for(int j=0; j<torre.get(i).caixasDaTorre.size(); j++){
                if(j>0)
                    System.out.printf(", ");
                System.out.printf("%d",torre.get(i).caixasDaTorre.get(j).id);
                
            }
            System.out.println("\n");           
            
        }
    }
}
