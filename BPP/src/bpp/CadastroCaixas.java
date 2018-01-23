package bpp;

import java.util.Scanner;

/**
 *
 * @author Matheus
 */
public class CadastroCaixas {
    
    Caixa c = new Caixa();
    
    public Caixa caixasDisponiveis(int id){
        
        if(id==1){ 
            
            /*Fogão 4 bocas coliseum: 
            c.setid=1;
            c.setlargura=48; //cm 
            c.setprofundidade=57;//cm 
            c.setaltura=85;//cm 
            c.setpeso=21;//kg 
            c.setpressao=7520;//7,52E-03 
            c.setresistencia=150;//1,50E-02 
            
            */
            
            c.setId(1);
            c.setLargura(48);
            c.setProfundidade(57);
            c.setAltura(85);
            c.setPeso(21);
            c.setPressao(752);
            c.setResistencia(1500);
            
            return c;
            
            
        }  
        
        else if(id==2){
            
            /*Fogão 4 bocas Ibiza Glass: 
            Largura=50cm 
            Profundidade=57cm 
            Altura=85cm 
            Peso=22kg 
            Pressão=7,78E-03 
            Resistência=1,56E-02*/
            c.setId(2);
            c.setLargura(50);
            c.setProfundidade(57); 
            c.setAltura(85);
            c.setPeso(22);
            c.setPressao(778); 
            c.setResistencia(1560);
            
            return c;
            
        }
        
        else if(id==3){
            
            /*10kg Clean: 
            Largura=67cm 
            Profundidade=71cm 
            Altura=105cm 
            Peso=43kg 
            Pressão=9,07E-03 
            Resistência=1,81E-02*/
            c.setId(3);
            c.setLargura(67);
            c.setProfundidade(71); 
            c.setAltura(105);
            c.setPeso(43);
            c.setPressao(907); 
            c.setResistencia(1810);
            
            return c;
            
        }
        
        else if(id==4){
            
            /*10kg Lava e Seca: 
            Largura=65cm 
            Profundidade=79cm 
            Altura=94cm 
            Peso=88kg 
            Pressão=1,72E-02 
            Resistência=3,45E-02*/
            c.setId(4);
            c.setLargura(65);
            c.setProfundidade(79); 
            c.setAltura(94);
            c.setPeso(88);
            c.setPressao(1720); 
            c.setResistencia(3450);
            
            return c;
            
        }
        
        else if(id==5){
            
            /*439L BRM49: 
            Largura=71cm 
            Profundidade=74cm 
            Altura=188cm 
            Peso=87kg 
            Pressão=1,66E-02 
            Resistência=1,25E-02*/
            c.setId(5);
            c.setLargura(71);
            c.setProfundidade(74); 
            c.setAltura(188);
            c.setPeso(87);
            c.setPressao(1660); 
            c.setResistencia(1250);
            
            return c;
            
        }
        
        else if(id==6){
            
            /*553l DF80: 
            Largura=81cm 
            Profundidade=75cm 
            Altura=190cm 
            Peso=107kg 
            Pressão=1,768E-02 
            Resistência=1,32E-02*/
            c.setId(6);
            c.setLargura(81);
            c.setProfundidade(75); 
            c.setAltura(190);
            c.setPeso(107);
            c.setPressao(1760); 
            c.setResistencia(1320);
            
            return c;
            
        }
        
        else if(id==7){
            
            /*25L NN-ST568: 
            Largura=57cm 
            Profundidade=47cm 
            Altura=37cm 
            Peso=18kg 
            Pressão=6,84E-02 
            Resistência=2,05E-02*/
            c.setId(7);
            c.setLargura(47);
            c.setProfundidade(47); 
            c.setAltura(37);
            c.setPeso(18);
            c.setPressao(684); 
            c.setResistencia(2050);
            
            return c;
            
        }
        
        else if(id==8){
            
            /*7500 BTUs CCN07B: 
            Largura=56cm 
            Profundidade=44cm 
            Altura=36cm 
            Peso=16kg 
            Pressão=6,24E-02 
            Resistência=1,87E-02*/
            c.setId(8);
            c.setLargura(56);
            c.setProfundidade(44); 
            c.setAltura(36);
            c.setPeso(16);
            c.setPressao(624); 
            c.setResistencia(1870);
            
            return c;
            
        }
        
        else if(id==9){
            
            /*7500 BTUs CCN07B*/
            
            c.setId(9);
            c.setLargura(57);
            c.setProfundidade(60); 
            c.setAltura(43);
            c.setPeso(28);
            c.setPressao(823); 
            c.setResistencia(1650);
            
            return c;
            
        }
        
        else if(id==10){
            
            /*Multiclimatizador Q/F*/
            
            c.setId(10);
            c.setLargura(39);
            c.setProfundidade(24); 
            c.setAltura(67);
            c.setPeso(7);
            c.setPressao(7800); 
            c.setResistencia(156);
            
            return c;
            
        }
        
        else if(id==11){
            
            /*Forno 28L Super Cook*/
            
            c.setId(11);
            c.setLargura(56);
            c.setProfundidade(72); 
            c.setAltura(44);
            c.setPeso(6);
            c.setPressao(149); 
            c.setResistencia(372);
            
            return c;
            
        }
        
        else if(id==12){
            
            /*Forno 23L*/
            
            c.setId(12);
            c.setLargura(28);
            c.setProfundidade(35); 
            c.setAltura(46);
            c.setPeso(6);
            c.setPressao(582); 
            c.setResistencia(1450);
            
            return c;
            
        }
        
        else if(id==13){
            
            /*Caso de teste*/
            
            c.setId(13);
            c.setLargura(5);
            c.setProfundidade(44); 
            c.setAltura(36);
            c.setPeso(16);
            c.setPressao(6); 
            c.setResistencia(1870);
            
            return c;
            
        }
        
        else if(id==14){
            
            /*Caso de teste*/
            
            c.setId(14);
            c.setLargura(202);
            c.setProfundidade(2); 
            c.setAltura(36);
            c.setPeso(16);
            c.setPressao(2000); 
            c.setResistencia(1870);
            
            return c;
            
        }
        
        return null;
        
    }
    
    public Caixa novaCaixa(){
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Introduza os dados da nova caixa: ");
        System.out.println("Id:  ");
        int id = sc.nextInt();
        c.setId(id);
        System.out.println("Largura:  ");
        int largura = sc.nextInt();
        c.setLargura(largura);
        System.out.println("Profundidade:  ");
        int profundidade = sc.nextInt();
        c.setProfundidade(profundidade);
        System.out.println("Altura:  ");
        int altura = sc.nextInt();
        c.setAltura(altura);
        System.out.println("Peso:  ");
        int peso = sc.nextInt();
        c.setPeso(peso);
        System.out.println("Pressão:  ");
        int pressao = sc.nextInt();
        c.setPressao(pressao);
        System.out.println("Resistência:  ");
        int resistencia = sc.nextInt();
        c.setResistencia(resistencia);
        
        return c;
    }
}
