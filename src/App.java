import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {
        // tamanho de 5 por 5 com 3 obstáculos e 3 sujeiras
        AgenteMolde robo = new AgenteBaseadoEmModelo("Azul", 4, 8);
        AgenteMolde roboInteligente = new AgenteReagenteSimples("Verde");
        Tabuleiro tabuleiro = new Tabuleiro(4, 8, 3, 3);
        tabuleiro.criar_obstaculos();
        tabuleiro.criar_sujeiras();

        int movR1 = 0;
        int movRoboInt = 0;
        int movInvR1 = 0;
        int movInvRoboInt = 0;

        boolean verificadorRobo = false;
        boolean verificadorRoboInteligente = false;

        tabuleiro.atribuir(robo, 0, 0);
        tabuleiro.atribuir(roboInteligente, 0, 0);
        tabuleiro.mostrarMatriz();

        while (!verificadorRobo && !verificadorRoboInteligente) {
            try {
                while (!verificadorRobo && !verificadorRoboInteligente) {
                    int[] coordenadasR1 = robo.getCoordenadas();
                    int posAntigaR1_x = coordenadasR1[0];
                    int posAntigaR1_y = coordenadasR1[1];
                    int[] coordenadasR2 = roboInteligente.getCoordenadas();
                    int posAntigaR2_x = coordenadasR2[0];
                    int posAntigaR2_y = coordenadasR2[1];

                    Random numRandom = new Random();

                    try {
                        if (!verificadorRobo) {
                            robo.mover(numRandom.nextInt(4) + 1, tabuleiro);
                            movR1++;
                            tabuleiro.atribuir(robo, posAntigaR1_x, posAntigaR1_y);
                            tabuleiro.mostrarMatriz();
                            System.out.println("Pontuação do Robo " + robo.cor + ": "+ robo.getPontuacao());
                            Thread.sleep(600);
                        }
                    } catch (MovimentoInvalidoException e) {
                        movInvR1++;
                    }

                    boolean win1 = robo.verificar(tabuleiro);
                    if (win1) {
                        verificadorRobo = true;
                    }

                    try {
                        if (!verificadorRoboInteligente) {
                            roboInteligente.mover(numRandom.nextInt(4) + 1, tabuleiro);
                            movRoboInt++;
                            tabuleiro.atribuir(roboInteligente, posAntigaR2_x, posAntigaR2_y);
                            tabuleiro.mostrarMatriz();
                            System.out.println("Pontuação do Robo Inteligente: " +roboInteligente.cor + ": "+ roboInteligente.getPontuacao());
                            Thread.sleep(600);
                        }
                    } catch (MovimentoInvalidoException e) {
                        movInvRoboInt++;
                    }

                    boolean win2 = roboInteligente.verificar(tabuleiro);
                    if (win2) {
                        verificadorRoboInteligente = true;
                    }

                    if (verificadorRobo || verificadorRoboInteligente) {
                        System.out.println("Robo e RoboInteligente Chegaram");
                        System.out.println("Quantidade de Acertos/Erros do Robo 1: " + movR1 + " / " + movInvR1);
                        System.out.println("Pontuação final do Robo 1: " + robo.getPontuacao());
                        System.out.println("Quantidade de Acertos/Erros do Robo 2: " + movRoboInt + " / " + movInvRoboInt);
                        System.out.println("Pontuação final do Robo Inteligente: " + roboInteligente.getPontuacao());
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
