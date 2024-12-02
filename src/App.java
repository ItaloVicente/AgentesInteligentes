import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {
        int iteracoes = 30; // Número de execuções
        List<Integer> listaNumSujeiras = new ArrayList<>();
        List<Integer> listaNumObstaculos = new ArrayList<>();
        List<Integer> listaAcertos = new ArrayList<>();
        List<Integer> listaErros = new ArrayList<>();
        List<Integer> listaNumMovimentos = new ArrayList<>();

        for (int iteracao = 0; iteracao < iteracoes; iteracao++) {
            System.out.println("\n--- Iteração " + (iteracao + 1) + " ---");
            //AgenteMolde robo = new AgenteBaseadoEmModelo("Azul", 3, 3);
            AgenteMolde robo = new AgenteReagenteSimples("Azul");
            Ambiente ambiente = new Ambiente(3, 3, 2, 3);
            ambiente.criar_obstaculos();
            ambiente.criar_sujeiras();

            int movR1 = 0;
            int movInvR1 = 0;

            boolean verificadorRobo = false;

            ambiente.atribuir(robo, 0, 0);
            ambiente.mostrarMatriz();

            // Conta o número inicial de sujeiras e obstáculos no ambiente
            int numSujeiras = contarElementos(ambiente.getSujeiras(), "S");
            int numObstaculos = contarElementos(ambiente.getObstaculos(), "O");
            listaNumSujeiras.add(numSujeiras);
            listaNumObstaculos.add(numObstaculos);

            while (!verificadorRobo) {
                try {
                    int[] coordenadasR1 = robo.getCoordenadas();
                    int posAntigaR1_x = coordenadasR1[0];
                    int posAntigaR1_y = coordenadasR1[1];

                    Random numRandom = new Random();

                    try {
                        robo.mover(numRandom.nextInt(4) + 1, ambiente);
                        movR1++;
                        ambiente.atribuir(robo, posAntigaR1_x, posAntigaR1_y);
                        ambiente.mostrarMatriz();
                        System.out.println("Pontuação do Robo " + robo.getCor() + ": acertos " + robo.getAcertos() + ", erros: " + robo.getErros());
                        Thread.sleep(600);
                    } catch (MovimentoInvalidoException e) {
                        movInvR1++;
                    }

                    boolean win1 = robo.verificar(ambiente);
                    if (win1) {
                        verificadorRobo = true;
                        System.out.println("Limpeza finalizada!");
                        System.out.println("Pontuação final robo: " + robo.getCor() + ": " + robo.getAcertos() + " acertos, e " + robo.getErros() + " erros.");
                        System.out.println("Movimentos inválidos: " + movInvR1);
                    }

                } catch (Exception e) {
                    System.out.println(e);
                }
            }

            // Adiciona os dados finais à lista
            listaAcertos.add(robo.getAcertos());
            listaErros.add(robo.getErros());
            listaNumMovimentos.add(movR1);
        }

        // Calcula e exibe as médias
        int totalAcertos = 0;
        int totalErros = 0;

        for (int i = 0; i < iteracoes; i++) {
            totalAcertos += listaAcertos.get(i);
            totalErros += listaErros.get(i);
        }

        float mediaAcertos = (float) totalAcertos / iteracoes;
        float mediaErros = (float) totalErros / iteracoes;

        System.out.println("\nResumo Geral:");
        System.out.println("  Média de acertos: " + mediaAcertos);
        System.out.println("  Média de erros: " + mediaErros);

        // Grava os resultados em um arquivo CSV
        try (FileWriter writer = new FileWriter("robo_burro.csv")) {
            writer.append("Iteração,Acertos,Erros,Movimentos Realizados\n"); // Cabeçalho

            for (int i = 0; i < iteracoes; i++) {
                writer.append((i + 1) + "," + listaAcertos.get(i) + "," + listaErros.get(i) + "," + listaNumMovimentos.get(i) + "\n");
            }

            // Adiciona a linha com as médias
            writer.append("Média," + mediaAcertos + "," + mediaErros + ",\n");

            System.out.println("Resultados salvos no arquivo 'resultados.csv'");

        } catch (IOException e) {
            System.out.println("Erro ao gravar o arquivo CSV: " + e.getMessage());
        }
    }

    public static int contarElementos(String[][] matriz, String elemento) {
        int contador = 0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j].strip().equals(elemento)) {
                    contador++;
                }
            }
        }
        return contador;
    }
}
