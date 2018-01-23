package bpp;
/**
 *
 * @author Matheus
 */
public class Carregamento {
    
    /*Container c;

    public Carregamento() {
        this.c = new Container(404, 300);
    }*/
    
    public boolean carregaArrayIterativo (Torre t, Container c){
        
        //Coordenadas de inserção:
        int x=c.getLargura();
        int y=c.getProfundidade(); 
        
        //Auxiliar do 1º passo:
        int MenorTam=c.getLargura()+1;
        
        //Auxiliar do 1º e 2º passo:
        int confere=1;
        
        for(int i=c.getLargura()-1; i>=0; i--){
            //1º passo da inserção
            if((c.matrix.get(i).size()-1)<MenorTam && (c.matrix.get(i).size()-1)>t.getProfundidade()){
                //Procura pela menor coluna que dê para fazer a inserção da torre tendo como critério a profundidade
                for(int j=i; j>(i-t.getLargura()); j--){
                    //Verifica se no intervalo todas as linhas tem tamanho maior ou igual a selecionada
                    if(c.matrix.get(j).size()<c.matrix.get(i).size()){
                        confere=0;
                    }
                }
                if(confere==1){
                    x=i;
                    y=c.matrix.get(i).size()-1;
                    confere=1;
                    break;
                }
                else
                    confere=1;
            }
            //Ainda temos de verificas se é possível realizar inserção no primeiro passo
            return false;
        }
        if((x-t.largura)>=t.largura){
            //Só será possível a movimentação da caixa se houver espaço para isto
            for(int i=(x-t.largura); /* i>=0 && */(i-t.largura)>=0; i--){
                //2º passo da inserção
                //Percorre enquanto i for maior que 0 e houver possibilidade de movimentação
                if(c.matrix.get(i).size()-1>y){
                    //Procura por uma linha que tenha tamanho maior que o achado no primeiro passo
                    //Caso encontre ela percorre o intervalo para ver se é possível inserilo ali
                    //Para que seja todas as linhas dentro do intervalo devem ser maior ou igual a achada
                    if(c.matrix.get(i).contains(-1)==false){
                        //Verifica se existe algum espaço ocupado nesta linha
                        //Se não tem
                        for(int j=i; j>(i-t.getLargura()); j--){                        
                            if(c.matrix.get(j).size()<c.matrix.get(i).size()){
                                confere=0;
                            }
                        }
                        if(confere==1){
                            //Atualiza os valores de x e y caso tenha encontrado um intervalo melhor para inserção
                            x=i;
                            y=c.matrix.get(i).size()-1;
                            confere=1;
                        }
                        else
                            confere=1;
                    }
                    else{
                        //Se tem espaço ocupado
                        //Dá prioridade a verificação do espaço mais ao fundo por ser este o objetivo do algoritmo
                        if((c.matrix.get(i).size()-(1+frente(i,c)))>=t.profundidade){
                            //verifica o novo intervalo
                            for(int j=i; j>(i-t.getLargura()); j--){                        
                                    if(c.matrix.get(j).size()<c.matrix.get(i).size()){
                                        confere=0;
                                    }
                                }
                                if(confere==1){
                                    //Atualiza os valores de x e y caso tenha encontrado um intervalo melhor para inserção
                                    x=i;
                                    y=c.matrix.get(i).size()-1;
                                    confere=1;
                                }
                                else
                                    confere=1;
                        }
                        else 
                            if(atras(i,c)>y){
                                //verifica o novo intervalo
                                for(int j=i; j>(i-t.getLargura()); j--){                        
                                    if(c.matrix.get(j).size()<c.matrix.get(i).size()){
                                        confere=0;
                                    }
                                }
                                if(confere==1){
                                    //Atualiza os valores de x e y caso tenha encontrado um intervalo melhor para inserção
                                    x=i;
                                    y=c.matrix.get(i).size()-1;
                                    confere=1;
                                }
                                else
                                    confere=1;
                            }
                    }
                }
            }
        }
        
        insereTorreArray(x,y,t,c);
        return true;
    }
    
    public int frente (int linha, Container c){
        
        //Caso tenha espaço ocupado na linha ele pega a última posição do espaço ocupado
        int i;        
        for(i=c.matrix.get(linha).size()-1; i>=0; i--)
            if(c.matrix.get(linha).get(i)==-1){
                return i;
            }        
        return i;
    }
    
    public int atras (int linha, Container c){
        
        //Caso tenha espaço ocupado na linha ele pega a primeira posição do espaço ocupado
        int i;        
        for(i=0; i<=c.matrix.get(linha).size()-1; i++)
            if(c.matrix.get(linha).get(i)==-1){
                return i;
            }        
        return i;
    }
    
    
    public void insereTorreArray(int linha, int coluna, Torre t, Container c){
        
        for(int i=linha; i>(linha-t.getLargura()); i--){//Largura()
            for(int j=coluna; j>(coluna-t.getProfundidade()); j--){
                         
                if(coluna<c.matrix.get(i).size()-1)
                    //Caso gere algum espaço livre a frente, ele não remove o espaço ocupado pela torre, mas seta os seus campos com -1
                    c.matrix.get(i).set(j, -1);
                else    
                    c.matrix.get(i).remove(j);
            }
        }
        
        //Seta as coordenadas:
        t.setCx(linha);
        t.setCy(coluna);
        
        //Add na fila de torres:
        c.getTorre().add(t);;
        
    }
    
    public void imprimeContainer(Container c){
        
        System.out.println("Torres inseridas no container: \n");
        for(int i=0; i<c.getTorre().size(); i++){
            System.out.println("Torre (" + c.getTorre().get(i).getId()+"):" + "\tx(linha): "+ c.getTorre().get(i).getCx() +"\ty(coluna): "+ c.getTorre().get(i).getCy());
            
        }
        
    }
}