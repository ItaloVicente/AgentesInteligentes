public class AgenteMolde{
    protected String cor;
    protected int[]coordenadas = new int[2];
    protected int pontuacao;

    public AgenteMolde(String cor) {
        this.cor = cor;
        this.coordenadas[0]=0;
        this.coordenadas[1]=0;
        this.pontuacao = 0;
    }
    public void mover (String m, Tabuleiro tabuleiro) throws MovimentoInvalidoException {
        int dim_x = tabuleiro.getDim_x();
        int dim_y = tabuleiro.getDim_y();
        int x = coordenadas[0];
        int y = coordenadas[1];

        if(m.equals("up")){
            coordenadas[0]--;
        }else if (m.equals("down")){
            coordenadas[0]++;
        }else if (m.equals("left")){
            coordenadas[1]--;
        }else if (m.equals("right")){
            coordenadas[1]++;
        }
        String[][] obstaculos = tabuleiro.getObstaculos();
        if (coordenadas[0]<0 || coordenadas[1]<0 || coordenadas[0]>dim_y-1 || coordenadas[1]>dim_x-1){
            coordenadas[0] = x;
            coordenadas[1] = y;
            pontuacao--; // Perde ponto por bater em um obstáculo
            throw new MovimentoInvalidoException(x, y);
        }
        if(coordenadas[0]>0 || coordenadas[1]>0 || coordenadas[0]<dim_y-1 || coordenadas[1]<dim_x-1){
            if(obstaculos[coordenadas[0]][coordenadas[1]].strip().equals("O")){
                coordenadas[0] = x;
                coordenadas[1] = y;
                throw new MovimentoInvalidoException(x, y);
            }

        }
        //pontuacao por alcancar a sujeira
        String[][] sujeiras = tabuleiro.getSujeiras();
        if (sujeiras[coordenadas[0]][coordenadas[1]].strip().equals("S")) {
            pontuacao += 2; // Ganha 2 pontos por alcançar sujeira
        } else {
            pontuacao--; // Perde 1 ponto por não alcançar sujeira
        }
        
        
    }
    public void mover (int m, Tabuleiro tabuleiro) throws MovimentoInvalidoException{
        if(m==1){
            mover("up", tabuleiro);
        }
        if(m==2){
            mover("down", tabuleiro);
        }
        if(m==3){
            mover("right", tabuleiro);
        }
        if(m==4){
            mover("left", tabuleiro);
        }
    }

    public boolean verificar(Tabuleiro tabuleiro){
        String[][] sujeiras = tabuleiro.getSujeiras();
        if(sujeiras[coordenadas[0]][coordenadas[1]].strip().equals("S")){
            return this.aspirar(tabuleiro);
        }
        else{
            return false;
        }
    }
    public boolean aspirar(Tabuleiro tabuleiro){
        return tabuleiro.retirar_sujeira(this.coordenadas[0], this.coordenadas[1]);
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

    public int getPontuacao() {
        return pontuacao;
    }
}