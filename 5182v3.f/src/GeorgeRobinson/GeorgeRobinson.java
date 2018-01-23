/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeorgeRobinson;

import Objetos.Pontos;
import Objetos.Camada;
import Objetos.TipoCaixa;
import Objetos.Torre;
import Objetos.Container;
import Objetos.Pedido;
import java.util.ArrayList;

/**
 *
 * @author Matheus
 */
public class GeorgeRobinson {
    
    CadastraTiposCx cr = new CadastraTiposCx();
    TratamentoCaixas tc = new TratamentoCaixas();
    
    int alturaLim = 2200;
    int larguraLim = 2130;
    int profundidadeLim = 11000;
    int aberto=1;
    int fechado=0;
    int cont=0;
    int conta=0; 
    
    public TipoCaixa Seleciona(ArrayList<TipoCaixa> cx){
        
        tc.ListaPrioridade(cx);
        TipoCaixa obj = null;
        float tmp;
        
        for (TipoCaixa cx1 : cx) {
            //Caso tenha alguma caixa na lista de caixas do tipo aberta ela é selecionada para iniciar a torre
            if (cx1.getTipoAberto() == aberto) {                            
                tmp = cx1.getComprimento();
                //A maior dimensão será aquela que irá definir o comprimento da nova camada
                if(cx1.getMaiorDimen()==cx1.getAltura()){
                    cx1.setComprimento(cx1.getAltura());
                    cx1.setAltura(tmp);
                }else if(cx1.getMaiorDimen()==cx1.getComprimento()){
                }else{
                    cx1.setComprimento(cx1.getLargura());
                    cx1.setLargura(tmp);
                }
                obj = cx1;   
                break;//Para assim que encontra a primeira caixa do tipo aberto. 
            }
        }
        if(obj==null){
            //Caso não tenha nenhuma caixa do tipo aberta, será selecionada a caixa do tipo fechado de maior prioridade
            cx.get(0).setTipoAberto(aberto);
            tmp = cx.get(0).getComprimento();
            
            if(cx.get(0).getMaiorDimen()==cx.get(0).getAltura()){
                //Altura como comprimento da camada
                cx.get(0).setComprimento(cx.get(0).getAltura());
                cx.get(0).setAltura(tmp);
                
            }else if(cx.get(0).getMaiorDimen()==cx.get(0).getComprimento()){ 
            }else{
                //Largura como comprimento da camada
                cx.get(0).setComprimento(cx.get(0).getLargura());
                cx.get(0).setLargura(tmp);
            }
            obj=cx.get(0);
        } 
        return obj;
    }
    
    public boolean PossibilidadeInsercao(TipoCaixa cx1, float altura, float comprimento, float largura){
        
        /*Se a perda de volume for menor do que a perda atual, mas ainda não houver sido testada nenhuma caixa antes
                    ele simplesmente vê se a caixa cabe no espaço selecionado, se couber essa será a caixa inicial do Seleciona2,
                    mas se não couber ela não será a caixa selecionada.
                    
                    Essa verificação é feita da seguinte forma:
                    
                        - Para que uma caixa caiba dentro de um determinado espaço é necessário que:
                            - A maior de suas dimensões seja menor ou igual a maior dimensão do espaço;
                            - A menor de suas dimensões deverá ser menor ou igual a menor das dimensões do espaço;
                            - Idem para a dimensão que não é a menor e nem a maior da caixa e da camada. 
        */
        
        float camMenor, cxMenor,  camMaior, cxMaior, camMeio=0, cxMeio=0;
                     
        camMenor = tc.menorDeTresValores(altura, comprimento, largura);
        camMaior = tc.maiorDeTresValores(altura, comprimento, largura);

        cxMenor = tc.menorDeTresValores(cx1.getAltura(), cx1.getComprimento(), cx1.getLargura());
        cxMaior = tc.maiorDeTresValores(cx1.getAltura(), cx1.getComprimento(), cx1.getLargura());
        

        if(camMenor == altura){
            if(camMaior == comprimento) camMeio = largura;
            else camMeio = comprimento;
        }
        else if(camMenor == comprimento){
            if(camMaior == altura) camMeio = largura;
            else camMeio = altura;
        }
        else if (camMenor == largura){
            if(camMaior == altura) camMeio = comprimento;
            else camMeio = altura;
        }

        if(cxMenor == cx1.getAltura()){
            if(cxMaior == cx1.getComprimento()) cxMeio = cx1.getLargura();
            else cxMeio = cx1.getComprimento(); 
        }
        else if(cxMenor == cx1.getComprimento()){
            if(cxMaior == cx1.getAltura()) cxMeio = cx1.getLargura();
            else cxMeio = cx1.getAltura();
        }
        else if (cxMenor == cx1.getLargura()){
            if(cxMaior == cx1.getAltura()) cxMeio = cx1.getComprimento();
            else cxMeio = cx1.getAltura();
        } 
        if(camMaior>=cxMaior && (camMeio>=cxMeio && camMeio>=0 && cxMeio>=0) && camMenor>=cxMenor){
            return true;
        }
        return false;
    }
    
    public boolean testaSeleciona2(float cxA, float cxB, float camA, float camB){
        
        /*Testa se é possível a inserção para as outras duas dimensões, se o menor da caixa for menor ou igual ao menor da camada
        e o maior da cx for menor ou igual ao maior da camada é possível a inserção, caso contrário não*/
        
        if(cxA<=cxB){
            if(camA<=camB){
                if(cxA<=camA && cxB<=camB) return true;
                else if(cxA<=camB && cxB <=camA) return true;
                else return false;
            }
            else if(camA>camB){
                if(cxA<=camB && cxB<=camA) return true;
                else if(cxA<=camA && cxB <=camB) return true;
                else return false;                
            }
        }
        else{
            if(camA<=camB){
                if(cxB<=camA && cxA<=camB) return true;
                else if(cxB<=camB && cxA <=camA) return true;
                else return false;
            }
            else{
                if(cxB<=camB && cxA<=camA) return true;
                else if(cxB<=camA && cxA <=camB) return true;
                else return false;                
            }
        }
        return false;
    }
    
    public TipoCaixa Seleciona2(ArrayList<TipoCaixa> cx, float largura, float comprimento, float altura){
        
        TipoCaixa obj = null;
        int comb=0, flag=0;
        //long perdaBase= largura*comprimento;
        float perdaBase= largura*comprimento;
        
        for (TipoCaixa cx1 : cx) {
                          
            //Calcula qts caixas daquele tipo cabem em uma coluna e depois a perda de altura
                
            int auxCombAl=0, auxCombComp=0, auxCombLarg=0;
            int calcPerdaAl=0, calcPerdaComp=0, calcPerdaLarg=0;

            //Testa usando a largura da caixa como base
            if(cx1.getLargura()<=comprimento && testaSeleciona2(cx1.getAltura(), cx1.getComprimento(), largura, altura)){
                auxCombLarg = (int)(comprimento/cx1.getLargura());
                calcPerdaLarg = (int)(comprimento - cx1.getLargura()*auxCombLarg);
            }
            //Testa usando a altura da caixa como base
            if(cx1.getAltura()<=comprimento && testaSeleciona2(cx1.getLargura(), cx1.getComprimento(), largura, altura)){
                auxCombAl = (int)(comprimento/cx1.getAltura());
                calcPerdaAl = (int)(comprimento - cx1.getAltura()*auxCombAl);
            }
            //Testa usando o comprimento da caixa como base
            if(cx1.getComprimento()<=comprimento && testaSeleciona2(cx1.getAltura(), cx1.getLargura(), largura, altura)){
                auxCombComp = (int)(comprimento/cx1.getComprimento());
                
                calcPerdaComp = (int)(comprimento - cx1.getComprimento()*auxCombComp);
            }

            //Pega a melhor combinação para o comprimento, ou seja, a que causa menor perda:

            float Perda = comprimento;
            flag=0;

            if(calcPerdaComp!=0 && calcPerdaComp<Perda){
                Perda=calcPerdaComp;
                flag=1;
            }
            if(calcPerdaAl!=0 && calcPerdaAl<Perda){
                Perda=calcPerdaAl;
                flag=2;
            }
            if(calcPerdaLarg!=0 && calcPerdaLarg<Perda){
                Perda=calcPerdaLarg;
                flag=3;
            }
            
            if(flag!=0){ 
                if(Perda<perdaBase){
                    /*Acha a caixa que cabe naquele dado intervalo e que com a combinação de uma de suas dimensões tem a menor perda da base 
                        (profundidade) da camada*/                  
                    perdaBase=Perda;
                    obj = cx1;
                }
                float tmp = cx1.getComprimento();
                if (flag==1){
                    //comprimento como base
                }else if(flag==2){
                    //altura como base
                    cx1.setComprimento(cx1.getAltura());
                    cx1.setAltura(tmp);
                }else if(flag==3){
                    //largura como comprimento
                    cx1.setComprimento(cx1.getLargura());
                    cx1.setLargura(tmp);
                }
            }
        } 
        return obj;
    }
    
    public int Dimensoes(TipoCaixa cx, Camada camada, float tNova, Container con, float larguraEsp, float alturaEsp, float comprimentoEsp, Pedido p){
        
        float aux, aux2;
        int ver1=0, ver2=0;

        //Verifica se é possível de se inserir dos dois jeitos na largura e altura da camada
        if(cx.getAltura()<=(alturaEsp) && cx.getAltura()<=larguraEsp) ver1=1;
        if(cx.getLargura()<=(alturaEsp) && cx.getLargura()<=larguraEsp)ver2=1;

        float tmp;

        if(ver1==1 && ver2==1){

            //Caso em que dos dois jeitos é possível
            aux=alturaEsp/cx.getAltura();
            float dif = alturaEsp - aux*cx.getAltura();

            aux2=alturaEsp/cx.getLargura();
            float dif2 = alturaEsp -aux2*cx.getLargura();

            if(dif<cx.getAltura() && aux<=cx.getQtdeDisponivel() || dif2<cx.getLargura() && aux2<=cx.getQtdeDisponivel()){
                //É possível formar torre completa com pelo menos uma das duas dimensões

                //Pega a que forma coluna completa e tem a menor perda

                if( dif<dif2 && aux<=cx.getQtdeDisponivel() ){
                    //A altura da caixa inserida na torre será igual a altura
                    formaTorre(cx, camada, con, comprimentoEsp, alturaEsp, larguraEsp, tNova, p);
                }
                else if ( dif>dif2 && aux2<=cx.getQtdeDisponivel() ){
                    //A altura da caixa inserida na torre será igual a largura
                    tmp = cx.getLargura();
                    cx.setLargura(cx.getAltura());
                    cx.setAltura(tmp);

                    formaTorre(cx, camada, con, comprimentoEsp, alturaEsp, larguraEsp, tNova, p);
                }else if (dif<cx.getAltura() && aux<=cx.getQtdeDisponivel()){
                    //A altura da torre "será igual" a altura
                    formaTorre(cx, camada, con, comprimentoEsp, alturaEsp, larguraEsp, tNova, p);
                }else{
                    tmp = cx.getLargura();
                    cx.setLargura(cx.getAltura());
                    cx.setAltura(tmp);
                    //A altura da torre "será igual" a largura
                    formaTorre(cx, camada, con, comprimentoEsp, alturaEsp, larguraEsp, tNova, p);
                }

            }                    
            else{

                //Maior dimensão vai para a largura e a menor para a altura
                if (cx.getLargura()>=cx.getAltura()){
                    //Largura como largura e altura como altura
                    formaTorre(cx, camada, con, comprimentoEsp, alturaEsp, larguraEsp, tNova, p);
                }else{
                    tmp = cx.getLargura();
                    cx.setLargura(cx.getAltura());
                    cx.setAltura(tmp);
                    //A altura da torre "será igual" a largura
                    formaTorre(cx, camada, con, comprimentoEsp, alturaEsp, larguraEsp, tNova, p);
                }                        
            }         
        }
        else if(ver1 == 1){
            //Só é possivel utilizar a altura como altura e largura, mas a largura não dá para ser utilizada como altura ou largura
            if(cx.getLargura()<=larguraEsp){
                //A largura será a largura e a altura será altura
                formaTorre(cx, camada, con, comprimentoEsp, alturaEsp, larguraEsp, tNova, p);
            }else if(cx.getLargura()<=alturaEsp){
                tmp = cx.getLargura();
                cx.setLargura(cx.getAltura());
                cx.setAltura(tmp);
                //A altura da torre "será igual" a largura
                formaTorre(cx, camada, con, comprimentoEsp, alturaEsp, larguraEsp, tNova, p);
            }else
                return 0;//Não dá para se utilizar a largura naquela camada portanto não há como inserir aquela caixa

        }
        else if(ver2 == 1){
            //Só é possivel utilizar a largura como altura e largura, mas a altura não dá para ser utilizada como altura ou largura 
            if(cx.getAltura()<=larguraEsp){
                tmp = cx.getLargura();
                cx.setLargura(cx.getAltura());
                cx.setAltura(tmp);
                //A altura da torre "será igual" a largura
                formaTorre(cx, camada, con, comprimentoEsp, alturaEsp, larguraEsp, tNova, p);
            }else if(cx.getAltura()<=alturaEsp){
                //A largura será a largura e a altura será altura
                formaTorre(cx, camada, con, comprimentoEsp, alturaEsp, larguraEsp, tNova, p);
            }else
                return 0;//Não dá para se utilizar a altura naquela camada portanto não há como inserir aquela caixa

        }
        else{
            //Descobre uma forma possível de se inserir a caixa
            if(cx.getAltura()<=alturaEsp && cx.getLargura()<=larguraEsp){
                //A altura será altura e a largura será largura
                formaTorre(cx, camada, con, comprimentoEsp, alturaEsp, larguraEsp, tNova, p);
            }else if(cx.getLargura()<=alturaEsp && cx.getAltura()<=larguraEsp){
                tmp = cx.getLargura();
                cx.setLargura(cx.getAltura());
                cx.setAltura(tmp);
                //A altura da torre "será igual" a largura
                formaTorre(cx, camada, con, comprimentoEsp, alturaEsp, larguraEsp, tNova, p);
            }else
                return 0; //Não há como inserir
        }
        return 1;
    }
    
    public int formaTorre(TipoCaixa cx, Camada camada, Container con, float comprimentoEsp, float alturaEsp, float larguraEsp, float tNova, Pedido p){
        
        Torre T;
        float x=0, y=0, z=0;
        Pontos ptInf;
        Pontos ptSup;
        
        float comprimento = cx.getComprimento();
        float altura = cx.getAltura();
        float largura = cx.getLargura();
        
        int qtde = (int)(alturaEsp/altura);//Quantidade de caixas por torres
        
        if(qtde>cx.getQtdeDisponivel())/*Se a qtde de caixas por torre for maior que a quantidade de caixas disponíveis, 
            só será possível fazer uma torre com a quantidade de caixas possíveis.*/
            qtde=cx.getQtdeDisponivel();
        
        int torresP=0, torresL=0;
        
        torresP = (int)(comprimentoEsp/comprimento);//Torres ao longo da profundidade da camada        
        torresL = (int)(larguraEsp/largura);//Torres ao longo da largura da camada
        
        int torres = cx.getQtdeDisponivel()/qtde;//Quantidade de torres possíveis
        
        int menor=torres;
        if(menor>torresL)
            menor=torresL;//Pega a quantidade de torres que dá para ser inserida naquele espaço
        
        for(int i=0; i<menor; i++){
            //Torres
            T=new Torre();            
            ptInf = new Pontos();
            ptSup = new Pontos();
            
            //x da torre na camada
            if(camada.getT().size()==0) x=0;
            else if(tNova==-1)x=camada.getT().get(camada.getT().size()-1).getX()+camada.getT().get(camada.getT().size()-1).getLargura();
            else x = tNova;
                
            if(x>=larguraLim){
                System.out.println("ohhhhhhh n pode meu filho  " + (++conta));                               
            }
            
            
            //Seta as outras dimensões da torre
            T.setProfundidade(comprimento*torresP);
            T.setLargura(largura);            
            if(tNova==-1)
                camada.setLargura(camada.getLargura()-largura);//atualiza largura da camada
            
            if(con.getCamada().isEmpty()){
                //Se é a primeira camada do container então y=0
                y=0;
                T.setY(y);
                
                //Seta um novo ponto na lista de pontos
                ptInf.setPi(x);
                ptInf.setDisponível(comprimentoEsp-(torresP*comprimento));
            }else{
                if(camada.getT().isEmpty()){
                    //Caso seja a primeira torre da camada o y será igual ao y da camada
                    //System.out.println("y da camada "+camada.getId() + ": " + camada.getY());
                    y=camada.getY();
                    T.setY(y);
                    
                    //Seta um novo ponto na lista de pontos
                    ptInf.setPi(x);
                    ptInf.setDisponível(comprimentoEsp-(torresP*comprimento));
                }else{
                    //Verifica largura flexível
                    float aux = (T.getProfundidade()*torresP);
                    
                    for(int j=0; j<con.getCamada().get(con.getCamada().size()-1).getPlanoInf().size(); j++){
                        //Verifica se dentro do intervalo da largura da torre existe uma largura flexível que suporte a inserção da torre
                        float k = con.getCamada().get(con.getCamada().size()-1).getPlanoInf().get(j).getPi();

                        if(k >=x && k <= (T.getLargura()+x) && aux!=0){
                            //Intervalo que cobre a área de ocupação da torre

                            if(con.getCamada().get(con.getCamada().size()-1).getPlanoInf().get(j).getDisponível()<aux)
                                //Acha a menor disponibilidade deste intervalo
                                aux=con.getCamada().get(con.getCamada().size()-1).getPlanoInf().get(j).getDisponível();
                        }
                        else if(aux==0){
                            //Caso no intervalo haja algum ponto que não tenha disponibilidade não há larg flexível
                            break;
                        }
                    }
                    //Linha original:
                    //System.out.println("há largura flexível");
                    
                    //if(aux!=0) 
                    
                        T.setY(camada.getY()-aux);
                        T.setY(camada.getY());
                    //else break;
                    
                    float disp=0;
                    for(int j=0; j<con.getCamada().get(con.getCamada().size()-1).getPlanoInf().size(); j++){
                        if(con.getCamada().get(con.getCamada().size()-1).getPlanoInf().get(j).getPi()>=x ||
                                con.getCamada().get(con.getCamada().size()-1).getPlanoInf().get(j).getPf()>=x){
                            //Procura pelo x na lista de pontos inferior da camada anterior
                            if(con.getCamada().get(con.getCamada().size()-1).getPlanoInf().get(j).getDisponível()>=0){
                                //Caso possua disponilidade o x é incrementado
                                T.setY(T.getY()-con.getCamada().get(con.getCamada().size()-1).getPlanoInf().get(j).getDisponível());
                                disp=con.getCamada().get(con.getCamada().size()-1).getPlanoInf().get(j).getDisponível();
                                //System.out.println("larg");
                            }
                            break;
                        }
                    }
                    
                    //Seta um novo ponto na lista de pontos                    
                    ptInf.setPi(x);
                    ptInf.setDisponível(comprimentoEsp-(torresP*comprimento)-disp);
                }
            }
            
            T.setAltura(altura*qtde);//Seta altura
            
            //Adiciona torre na camada e gera uma nova torre para a próxima iteração
            if(tNova==-1){
                //Caso seja uma nova torre seta um novo ponto
                ptSup.setPi(x);
                ptSup.setDisponível(alturaEsp-(qtde*altura));//Altura limite menos a quantide de caixas por torre multiplicada pela sua altura
                ptSup.setComprimento(torresP*comprimento);
                ptSup.setPf(x+largura);
                camada.getPlanoSup().add(ptSup);
            }
            else{
                //Caso seja uma torre em cima de outra
                float inicio=tNova;
                float fim=tNova+T.getLargura();
                
                for(int k=0; k<camada.getPlanoSup().size(); k++){
                    
                    if(camada.getPlanoSup().get(k).getPi()==inicio){
                        camada.getPlanoSup().get(k).setDisponível(camada.getPlanoSup().get(k).getDisponível()-T.getAltura());
                        camada.getPlanoSup().get(k).setComprimento(torresP*comprimento);
                        camada.getPlanoSup().get(k).setPf(tNova+(torresL*largura));
                    }
                    else if(camada.getPlanoSup().get(k).getPi()>inicio){
                        if(camada.getPlanoSup().get(k).getPi()<fim){
                           camada.getPlanoSup().remove(k);
                        }
                        else if(camada.getPlanoSup().get(k).getPi()==fim){
                            break;
                        }
                        else if (camada.getPlanoSup().get(k).getPi()>fim){
                            //Caso seja necessário inserir um novo ponto na lista
                            Pontos pt = new Pontos();
                            
                            pt.setDisponível(camada.getPlanoSup().get(k).getDisponível());
                            pt.setComprimento(camada.getPlanoSup().get(k).getComprimento());
                            pt.setPi(fim);  
                            pt.setPf(tNova+(torresL*largura));
                            camada.getPlanoSup().add(k, pt);
                            break;
                        }
                    }
                }
                y=inicio;
            }
            
            z=0;
            float yCx;
            for(int j=0; j<qtde; j++){
                //Caixas por torre
                yCx=T.getY(); 
                float yy=yCx;
                for(int k=0; k<torresP; k++){
                    //Caixas por andar                    
                    for(int q=0; q<p.getCxDisponiveis().size(); q++){
                        if(p.getCxDisponiveis().get(q).getTipoCaixa()==cx.getTipoCx()){
                            T.getCaixasDaTorre().add(p.getCxDisponiveis().get(q));
                            T.getCaixasDaTorre().get(T.getCaixasDaTorre().size()-1).setY(yCx);
                            T.getCaixasDaTorre().get(T.getCaixasDaTorre().size()-1).setX(x);
                            T.getCaixasDaTorre().get(T.getCaixasDaTorre().size()-1).setZ(z);
                            T.getCaixasDaTorre().get(T.getCaixasDaTorre().size()-1).setAltura(altura);
                            T.getCaixasDaTorre().get(T.getCaixasDaTorre().size()-1).setComprimento(comprimento);
                            T.getCaixasDaTorre().get(T.getCaixasDaTorre().size()-1).setLargura(largura);
                            //z+=altura;
                            p.getCxDisponiveis().remove(p.getCxDisponiveis().get(q));
                            yCx+=comprimento;
                            break;
                        }
                    }
                    if(torresP>1){
                       /* System.out.println("andar com mais de uma caixa na frente");
                        System.out.println("Comprimento da caixa: " + comprimento + " comprimento do espaço: " + comprimentoEsp + 
                                " cxs por andar: " + torresP);
                        System.out.println("Novo y: " + yy + "  risous: "+ T.getCaixasDaTorre().get(T.getCaixasDaTorre().size()-1).getY());
                        yy += comprimento; */                       
                    }
                    //z=zCx;
                }
                //System.out.println("fim do andar");
                z+=altura;
                T.setAltura(T.getAltura()+altura);                
            }
            
            cx.setQtdeDisponivel(cx.getQtdeDisponivel()-qtde);//Decrementa a quantidade de caixas utlizadas na torre
            
            T.setId(camada.getT().size()+1);
            T.setX(x);
            
            camada.getT().add(T);
            camada.getPlanoInf().add(ptInf);
            //System.out.println("fim da torre");
        }
        //System.out.println("y: " + x);
        return 1;
        
    }
    
    public int novoEspaco(Camada cam, ArrayList <TipoCaixa> C, Container con, Pedido p){
        /*Retorna:
            0 -> Nova camada
            1 -> Insere em cima de uma torre
            2 -> Insere ao lado da última torre
        */
        
        //--------------------------------------------------------------------
        //Procura por um espaço superior para realizar a inserção
        
        float ptI=0, ptF=0;//Ponto de começo e término do intervalo
        float intervalo=0;
        float altDisp = cam.getPlanoSup().get(0).getDisponível();//Varíavel que armazena a altura disponível
        float comp = cam.getPlanoSup().get(0).getComprimento();
        /*System.out.println(altDisp);
        System.out.println(cam.getT().get(0).getProfundidade());*/
        
        for(int i=1; i<cam.getPlanoSup().size(); i++){
            //Percorre a lista de pontos superior procurando intervalos onde a altura disponível seja equivalente
            if(cam.getPlanoSup().get(i).getDisponível()==altDisp){
                //Além disso deve verificar se a base das caixas é a mesma
                ptF=cam.getPlanoSup().get(i).getPi();
            }                
            else if(!(cam.getPlanoSup().get(i).getDisponível()==altDisp) || (i+1)==cam.getPlanoSup().size()){
                //Termina o intervalo verificando se é possível de se inserir uma caixa neste dado intervalo e reinicializa os atributos
                
                ptF=cam.getPlanoSup().get(i-1).getPf();
                intervalo=ptF-ptI; 
                
                //System.out.println("xxxxxx");
                if(verifica(C, intervalo, altDisp, comp)){
                    //System.out.println("entrou");
                    insereSup(cam, C, ptF-ptI, ptI, ptF, cam.getPlanoSup().get(cam.getPlanoSup().size()-1).getDisponível(), con, p);
                    return 1;
                }
                ptI=cam.getPlanoSup().get(i).getPi();                    
                altDisp=cam.getPlanoSup().get(i).getDisponível();
                comp = cam.getPlanoSup().get(i).getComprimento();
                ptF=cam.getPlanoSup().get(i).getPf();
                intervalo=ptF-ptI;
            }
        }        
        if(verifica(C, intervalo, altDisp, comp)){
            insereSup(cam, C, ptF-ptI, ptI, ptF, cam.getPlanoSup().get(cam.getPlanoSup().size()-1).getDisponível(), con, p);
            return 1;
        }
        
        //Fim de verificação na superfície das torres já inseridas
        //--------------------------------------------------------------------
        
        //Caso não tenha encontrado nenhum espaço superior para inserir as caixas deve-se procurar na lateral
        
        if(verifica(C, cam.getLargura(), cam.getAltura(), cam.getComprimento())){
            return 2;
        }
        
        //Fim da verificação nas laterais
        //--------------------------------------------------------------------
        return 0;
    }
    
    public boolean verifica(ArrayList<TipoCaixa> C, float largura, float altura, float comprimento){
        
        /*Quando o verifica é utilizado para verificar a inserção na superfície:
            altura <= disponibilidade no ponto;
            largura <= intervalo
            comprimento <=comprimento no ponto
        */
        //System.out.println("intervalo: " + largura + " altDisp: " + altura + " comp: " + comprimento);
        
        for (TipoCaixa C1 : C) {
            if (PossibilidadeInsercao(C1, altura, comprimento, largura)) {
                /*System.out.println("Caixa permitida: " + C1.getTipoCx());
                System.out.println("largura: " + largura + " altura: " + altura + " comprimento: " + comprimento);
                System.out.println("cxLargura: " + C1.getLargura() + " cxAltura: " + C1.getAltura() + " cxComprimento: " + C1.getComprimento());
                System.out.println("p");
                System.out.println("intervalo: " + largura + " altDisp: " + altura + " comp: " + comprimento);
                System.out.println("cxLargura: " + C1.getLargura() + " cxAltura: " + C1.getAltura() + " cxComprimento: " + C1.getComprimento());
                */
                return true;
            }
        }
        
        return false;   
    }    
    
    public int insereSup(Camada cam, ArrayList <TipoCaixa> C, float intervalo, float ptI, float ptF, float alturaDisp, Container con, Pedido p){
        
        /*Método responsável por intermediar a inserção de torres em cima de outras torres*/
        
        /*System.out.println("Começo da inserção da 1");
        System.out.println(ptI);
        System.out.println(ptF);*/
        float largura=0, comprimento=0, altura=0;     
        
        for(int i=0; i<cam.getPlanoSup().size();i++){
            //Encontra o ponto de começo do intervalo e com base nele pega as dimensões do espaço
            if(cam.getPlanoSup().get(i).getPi()==ptI){
                altura=cam.getPlanoSup().get(i).getDisponível();
                largura=intervalo;
                comprimento=cam.getPlanoSup().get(i).getComprimento();
                break;
            }
        }
        //System.out.println();
        TipoCaixa Cx  = Seleciona2(C, largura, comprimento, altura);
        //System.out.println(Cx.getTipoCx());
        if(Cx==null) return 0;  
        
        if(Dimensoes(Cx, cam, ptI, con,  largura, altura, comprimento, p)==0) return 0;
        else return 1;
    }
    
    public Container GeorgeRobinson(Pedido p, Container container){
        
        //Container container = new Container(larguraLim, profundidadeLim, alturaLim);
        //System.out.println("Pedido: " + p.getId());
        
        for(int i=0; i<p.getCx().size(); i++)
            p.getCxDisponiveis().add(p.getCx().get(i));
        for(int i=0; i<p.getTp().size(); i++){
            
            TipoCaixa tpo = cr.cadastroCaixas(p.getTp().get(i).getTipoCx());
            p.getTp().get(i).setQtdeDisponivel(tpo.getQtdeDisponivel());
            p.getTp().get(i).setTipoAberto(fechado);           
            p.getTipo().add(p.getTp().get(i));
        }
        
        //Seleciona a caixa que irá criar a primeira camada
        TipoCaixa tipoSelecionado = Seleciona(p.getTipo()); 
        
        Camada camada = new Camada(larguraLim, tipoSelecionado.getMaiorDimen());
        camada.setAltura(alturaLim); 
        
        //Seta as coordenadas da primeira camada:
        
        if(container.getCamada().isEmpty()){
            camada.setX(0);
            camada.setY(0);
        }
        else {
            //Caso o container não esteja vazio o x da primeira camada corresponde ao último ocupado
            
            int k = container.getCamada().size()-1;
            float in = container.getCamada().get(k).getX();
            float f = in + container.getCamada().get(k).getComprimento();
            camada.setY(f);
            //camada.setX(0);
            cont = container.getCamada().get(k).getId();
        }
        
        camada.setId(++cont);
        
        while(!p.getTipo().isEmpty()){
            
            /*---------------------------------------------------------------------------------------------------------------------------
            Inserção da primeira caixa na camada*/
            int controle=0;
            for(TipoCaixa tipoCx : p.getTipo()){
                //Procura por um tipo de caixa aberto para começar a camada
                if(tipoCx.getTipoAberto()==aberto){                    
                    if(Dimensoes(tipoCx, camada, -1, container, camada.getLargura(), camada.getAltura(), camada.getComprimento(), p)==1){
                        //Se a caixa foi inserida devidamente o loop acaba.
                        tipoSelecionado=tipoCx;
                        conta+=tipoSelecionado.getComprimento();
                        controle=1;
                        break;
                    }
                }
            }
            if(controle==0){
                //Não foi inserida nenhuma caixa ou simplesmente nenhuma caixa do tipo aberta disponível foi encontrada
            }
            
            for(int i=0; i<p.getTipo().size(); i++){
                if(p.getTipo().get(i).getQtdeDisponivel()==0){
                    //Caso encontre algum tipo de caixa indisponível ele a remove da lista, refazendo a lista de prioridade em seguida. 
                    p.getTipo().remove(i);
                    tc.ListaPrioridade(p.getTipo());
                }
            } 
            
            if(p.getTipo().isEmpty()){
                container.getCamada().add(camada);
                System.out.println((camada.getX()+camada.getComprimento()));
                break;
            }
            
            /*Fim de inserção das primeiras caixas na camada            
            ---------------------------------------------------------------------------------------------------------------------------            
            Começa a inserir as caixas restantes até que a camada já não suporte novas caixas*/
            while(!p.getTipo().isEmpty()){          
                int nv = novoEspaco(camada, p.getTipo(), container, p);
                if(nv==0)//Quando for igual a 0 uma nova camada deve ser feita
                    break;                
                else if(nv==1){
                    //Inserção já acontece dentro do novoEspaco
                }
                else if (nv==2){
                    //Inserção nas laterais n==2
                    tc.ListaPrioridade(p.getTipo());                    
                    tipoSelecionado = Seleciona2(p.getTipo(), camada.getLargura(), camada.getComprimento(), camada.getAltura());
                    
                    if(tipoSelecionado==null){
                        /*Nunca deve acontecer, visto que se entrou aqui é por que o novoEspaco achou uma caixa que pode ser inserida na 
                        camada, mas o Seleciona 2, não a selecionou. =( */
                        System.out.println("> Deu ruim");
                        return null;
                    }
                    else if(Dimensoes(tipoSelecionado, camada, -1, container, camada.getLargura(), camada.getAltura(), 
                                camada.getComprimento(), p)==1);
                    else{
                        /*Nunca deve acontecer, se o seleciona 2 selecionou uma caixa que dá para ser inserida, e o novoEspaço diz que existe 
                        uma que dá então se o seleciona2 a selecionou corretamente deveria haver a inserção.*/
                        System.out.println(">> Deu ruim");
                        return null;
                    }
                }
                else{
                    //Não há como o novoEspaço retornar qualquer valor que não seja 0, 1 ou 2. 
                    System.out.println(">>> Deu ruim");
                    return null;
                }
                
                for(int i=0; i<p.getTipo().size(); i++){
                    if(p.getTipo().get(i).getQtdeDisponivel()==0){
                        //Caso encontre algum tipo de caixa indisponível ele a remove da lista, refazendo a lista de prioridade em seguida.
                        p.getTipo().remove(i);
                        tc.ListaPrioridade(p.getTipo());
                    }
                }
                //imprimePontos(camada);
            }
            
            container.getCamada().add(camada);//Salva a camada
            
            if(p.getTipo().isEmpty()){//Caso não haja mais nenhum tipo de caixa a ser inserido para de rodar
                System.out.println((camada.getX()+camada.getComprimento()));
                break;
            }
            /*---------------------------------------------------------------------------------------------------------------------------    
            Gera a próxima camada*/
            
            tipoSelecionado = Seleciona(p.getTipo()); 
            
            camada = new Camada(larguraLim,tipoSelecionado.getMaiorDimen());
            
            camada.setAltura(alturaLim);
            camada.setId(++cont);
            camada.setY(container.getCamada().get(container.getCamada().size()-1).getY()+container.getCamada().get(container.getCamada().size()-1).getComprimento());
            camada.setX(0);
            
            /*---------------------------------------------------------------------------------------------------------------------------    
            Recomeça o loop*/
        
        }     
        
        return container;
    }
    
    public void imprimePontos(Camada camada){
        for(int i=0; i<camada.getPlanoSup().size(); i++)
            System.out.println("pt: " + camada.getPlanoSup().get(i).getPi() + "\tdisponibilidade: " + camada.getPlanoSup().get(i).getDisponível() + "\tPf: " + camada.getPlanoSup().get(i).getPf());        
    }
            
}
