import java.util.HashSet;
import java.util.Set;

public class AgenteMolde{
    protected String cor;
    protected int[]coordenadas = new int[2];
    protected int acertos;
    protected int erros;
    private Set<String> celulasVisitadas = new HashSet<>();

    public AgenteMolde(String cor) {
        this.cor = cor;
        this.coordenadas[0]=0;
        this.coordenadas[1]=0;
        this.acertos=0;
        this.erros=0;
        String posicaoInicial = "0,0";
        celulasVisitadas.add(posicaoInicial);

        
    }
    public void mover(String m, Ambiente ambiente) throws MovimentoInvalidoException {
        int dim_x = ambiente.getDim_x();
        int dim_y = ambiente.getDim_y();
        int x = coordenadas[0];
        int y = coordenadas[1];
    
        if (m.equals("up")) {
            coordenadas[0]--;
        } else if (m.equals("down")) {
            coordenadas[0]++;
        } else if (m.equals("left")) {
            coordenadas[1]--;
        } else if (m.equals("right")) {
            coordenadas[1]++;
        }
    
    if (coordenadas[0] < 0 || coordenadas[1] < 0 || coordenadas[0] >= dim_y || coordenadas[1] >= dim_x) {
    coordenadas[0] = x;
    coordenadas[1] = y;
    throw new MovimentoInvalidoException(x, y);
    }
    String[][] obstaculos = ambiente.getObstaculos();
    String[][] sujeiras = ambiente.getSujeiras();
    String posicaoAtual = coordenadas[0] + "," + coordenadas[1];
    
    if (obstaculos[coordenadas[0]][coordenadas[1]].strip().equals("O")) {
        coordenadas[0] = x;
        coordenadas[1] = y;
        // Penalidade apenas se for a primeira vez
        if (!celulasVisitadas.contains(posicaoAtual)) {
            erros += 3; // Perde pontos por bater no obstáculo
            celulasVisitadas.add(posicaoAtual);
            System.out.println("robo perdeu ponto por bater em um obstaculo");
            
        }
        throw new MovimentoInvalidoException(x, y);
    }
    
    if (sujeiras[coordenadas[0]][coordenadas[1]].strip().equals("S")) {
        acertos += 10; // Ganha pontos por alcançar sujeira
        celulasVisitadas.add(posicaoAtual);
    } else {
        // Penalidade por revisitar células (não-obstáculo)
        if (!celulasVisitadas.contains(posicaoAtual)) {
            celulasVisitadas.add(posicaoAtual); // Adiciona como visitada
            erros += 1; // Perde 1 ponto por célula vazia
            System.out.println("robo perdeu pontos por irpara celula vazia");
        } else {
            erros += 3; // Penaliza revisita
            System.out.println("robo perdeu pontos por revisitar um local ja visitado");
        }
    }
    //tratar aqui os casos em que não há solução - sujeira inalcansave ou agente preso






    }


    public void mover (int m, Ambiente ambiente) throws MovimentoInvalidoException{
        if(m==1){
            mover("up", ambiente);
        }
        if(m==2){
            mover("down", ambiente);
        }
        if(m==3){
            mover("right", ambiente);
        }
        if(m==4){
            mover("left", ambiente);
        }
    }

    public boolean verificar(Ambiente ambiente){
        String[][] sujeiras = ambiente.getSujeiras();
        if(sujeiras[coordenadas[0]][coordenadas[1]].strip().equals("S")){
            return this.aspirar(ambiente);
        }
        else{
            return false;
        }
    }
    public boolean aspirar(Ambiente ambiente){
        return ambiente.retirar_sujeira(this.coordenadas[0], this.coordenadas[1]);
    }
    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int[] getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(int[] coordenadas) {
        this.coordenadas = coordenadas;
    }

    public int getAcertos() {
        return acertos;
    }
    public int getErros() {
        return erros;
    }
}