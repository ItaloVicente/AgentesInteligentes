import java.util.Random;
import java.util.Scanner;

public class App3 {
    public static void main(String[] args) {
        //Questao 3
        boolean verificadorAlimento=false;
        AgenteMolde robo = new AgenteMolde("Azul");
        AgenteMolde roboInteligente = new AgenteReagenteSimples("Verde");
        Scanner teclado = new Scanner(System.in);
        System.out.println("Informe o tamanho horizontal da sala");
        int dim_x = teclado.nextInt();
        System.out.println("Informe o tamanho vertical da sala");
        int dim_y = teclado.nextInt();
        Tabuleiro tabuleiro = new Tabuleiro(dim_x,dim_y, 3, 3);
        tabuleiro.criar_obstaculos();
        tabuleiro.criar_sujeiras();
        tabuleiro.mostrarMatriz();
        int posicaoAliX = -1;
        int posicaoAliY = -1;
        int movR1=0;
        int movRoboInt=0;
        int movInvR1=0;
        int movInvRoboInt=0;
        boolean verificadorRobo = false;
        boolean verificadorRoboInteligente = false;
        while(verificadorAlimento!=true){
            try{
                System.out.println("Digite a posicao do alimento em x: ");
                System.out.println("Lembre-se que o Alimento precisa estar nas dimensoes da sala");
                posicaoAliX = teclado.nextInt();
                System.out.println("Digite a posicao do alimento em y: ");
                System.out.println("Lembre-se que o Alimento precisa estar nas dimensoes da sala");
                posicaoAliY = teclado.nextInt();
                posicaoAliX-=1;
                posicaoAliY-=1;
                verificadorAlimento=true;
                tabuleiro.atribuir(robo, 0, 0, posicaoAliX, posicaoAliY);
                tabuleiro.atribuir(roboInteligente, 0, 0, posicaoAliX, posicaoAliY);
                tabuleiro.mostrarMatriz();
            }catch(SujeiraInvalida e){
                System.out.println(e);
            }
        }
        while(verificadorRobo!=true||verificadorRoboInteligente!=true){
            try{
                while(verificadorRobo!=true||verificadorRoboInteligente!=true){
                    int[] coordenadasR1 = robo.getCoordenadas();
                    int posAntigaR1_x = coordenadasR1[0];
                    int posAntigaR1_y = coordenadasR1[1];
                    int[] coordenadasR2 = roboInteligente.getCoordenadas();
                    int posAntigaR2_x = coordenadasR2[0];
                    int posAntigaR2_y = coordenadasR2[1];
                    Random numRandom = new Random();
                    try{
                        if(verificadorRobo!=true){
                        robo.mover(numRandom.nextInt(4)+1, tabuleiro);
                        movR1=movR1+1;
                        tabuleiro.atribuir(robo, posAntigaR1_x, posAntigaR1_y, posicaoAliX, posicaoAliY);
                        tabuleiro.mostrarMatriz();
                        Thread.sleep(600);
                        }
                    }catch(MovimentoInvalidoException e){
                        movInvR1++;
                    }
                    boolean win1 = robo.verificar(tabuleiro);
                    if(win1==true){
                        verificadorRobo=true;
                    }
                    try{
                        if(verificadorRoboInteligente!=true){
                        roboInteligente.mover(numRandom.nextInt(4)+1, tabuleiro);
                        movRoboInt=movRoboInt+1;
                        tabuleiro.atribuir(roboInteligente, posAntigaR2_x, posAntigaR2_y, posicaoAliX, posicaoAliY);
                        tabuleiro.mostrarMatriz();
                        Thread.sleep(600);
                        }
                    }catch(MovimentoInvalidoException e){
                        movInvRoboInt++;
                    }
                    boolean win2 = roboInteligente.verificar(tabuleiro);
                    if(win2==true){
                        verificadorRoboInteligente=true;
                    }
                    if(verificadorRobo==true && verificadorRoboInteligente == true){
                        System.out.println("Robo e RoboInteligente Chegaram");
                        System.out.println("Quantidade de Acertos/Erros do Robo 1: " + movR1 + " / " + movInvR1);
                        System.out.println("Quantidade de Acertos/Erros do Robo 2: " + movRoboInt + " / " + movInvRoboInt);
                    }
                }
            }catch(SujeiraInvalida e){
                System.out.println(e);
            }catch(Exception e){
                System.out.println(e);
            }
        }
        teclado.close();
    }
    }
