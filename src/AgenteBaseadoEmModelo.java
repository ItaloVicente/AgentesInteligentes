import java.util.ArrayList;
import java.util.Random;

public class AgenteBaseadoEmModelo extends AgenteMolde {
    boolean errou = false;
    String[][] modelo;
    int dim_y;
    int dim_x;
    public AgenteBaseadoEmModelo(String cor, int dim_x, int dim_y){
        super(cor);
        //modelo inicialmente vazio
        modelo = new String[dim_y][dim_x];
        for(int i=0;i<dim_y;i++){
            for(int j=0;j<dim_x;j++){
                this.modelo[i][j]=" ";
            }
        }
        this.dim_x = dim_x;
        this.dim_y = dim_y;
        modelo[0][0] = "L";
    }
    @Override
    public void mover(String movimento, Tabuleiro tabuleiro) throws MovimentoInvalidoException{
        try{
            super.mover(movimento, tabuleiro);
        }catch(MovimentoInvalidoException e){
            if(!errou){
                errou = true;
                throw e;
            }
            Random r = new Random();
            String[] casos = {"up", "down", "right", "left"};
            while(errou){
                try{
                    super.mover(casos[r.nextInt(4)], tabuleiro);
                
                    errou = false;
                }catch(MovimentoInvalidoException err)
                { }
            }
        }
    }
    @Override
    public void mover(int movimento, Tabuleiro tabuleiro) throws MovimentoInvalidoException{
        Random r = new Random();
        int[] casos = {1, 2, 3, 4};
        int new_movimento = movimento;
        if(this.verificar_modelo(movimento, tabuleiro)==false){
            while(this.verificar_modelo(new_movimento, tabuleiro)==false){
                new_movimento=casos[r.nextInt(4)];
            }
            movimento = new_movimento;
        }
        this.mostrarMatriz();
        try{
            super.mover(movimento, tabuleiro);
        }catch(MovimentoInvalidoException e){
            if(!errou){
                errou = true;
                throw e;
            }
            while(errou){
                try{
                    super.mover(casos[r.nextInt(4)], tabuleiro);
                
                    errou = false;
                }catch(MovimentoInvalidoException err)
                { }
            }
        }
    }
    public boolean verificar_modelo(int movimento, Tabuleiro tabuleiro) {
        int x = coordenadas[0];
        int y = coordenadas[1];
        int novoX = x, novoY = y;
    
        // Determina as novas coordenadas com base no movimento
        if (movimento == 1) novoX--;        
        else if (movimento == 2) novoX++;   
        else if (movimento == 3) novoY++;   
        else if (movimento == 4) novoY--; 
    
        // Verifica se as novas coordenadas estão dentro dos limites do tabuleiro
        if (novoX < 0 || novoY < 0 || novoX >= tabuleiro.getDim_y() || novoY >= tabuleiro.getDim_x()) {
            return false;
        }
    
        // Obtém os obstáculos do tabuleiro
        String[][] obstaculos = tabuleiro.getObstaculos();
    
        // Se a posição contém um obstáculo, marca no modelo e rejeita o movimento
        if (obstaculos[novoX][novoY].strip().equals("O")) {
            //Salva o O (obstaculo)
            modelo[novoX][novoY] = "O";
            return false;
        }
    
        // Se a posição já foi limpa, verifica se há outras opções disponíveis
        if (modelo[novoX][novoY].strip().equals("L")) {
            return todasAsOpcoesExploradas(x, y, tabuleiro);
        }
    
        // Atualiza o modelo para marcar a posição atual como limpa
        modelo[x][y] = "L";
        return true; 
    }
    
    private boolean todasAsOpcoesExploradas(int x, int y, Tabuleiro tabuleiro) {
        int[][] direcoes = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Cima, baixo, esquerda, direita
        String[][] obstaculos = tabuleiro.getObstaculos();
    
        for (int[] direcao : direcoes) {
            int novoX = x + direcao[0];
            int novoY = y + direcao[1];
    
            // Ignora posições fora do tabuleiro
            if (novoX < 0 || novoY < 0 || novoX >= tabuleiro.getDim_y() || novoY >= tabuleiro.getDim_x()) {
                continue;
            }
    
            // Se a posição não é obstáculo nem limpa, ainda há algo a explorar
            if (!modelo[novoX][novoY].strip().equals("L") && !obstaculos[novoX][novoY].strip().equals("O")) {
                return false;
            }
        }
        modelo[x][y] = "L";
        // Todas as posições ao redor foram exploradas ou são obstáculos, entao o agente acaba voltando uma casa que ja havia visitado
        return true; 
    }
    public void mostrarMatriz(){
        ArrayList<String> linhaI = new ArrayList<>(); 
        for (int i=0;i<this.dim_y;i++){
            for(int j=0;j<this.dim_x;j++){
                linhaI.add(this.modelo[i][j]);
            }
            System.out.println(linhaI);
            linhaI= new ArrayList<>();
        }
        System.out.println("=====================MODELO=====================");
    }
}
