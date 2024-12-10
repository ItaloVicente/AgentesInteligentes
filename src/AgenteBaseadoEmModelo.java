import java.util.ArrayList;
import java.util.Random;

public class AgenteBaseadoEmModelo extends AgenteMolde {
    boolean errou = false;
    String[][] modelo;
    int dim_y;
    int dim_x;
    public AgenteBaseadoEmModelo(String nome, int dim_x, int dim_y){
        super(nome);
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
    public void mover(String movimento, Ambiente ambiente) throws MovimentoInvalidoException{
        try{
            super.mover(movimento, ambiente);
        }catch(MovimentoInvalidoException e){
            if(!errou){
                errou = true;
                throw e;
            }
            Random r = new Random();
            String[] casos = {"up", "down", "right", "left"};
            while(errou){
                try{
                    super.mover(casos[r.nextInt(4)], ambiente);
                
                    errou = false;
                }catch(MovimentoInvalidoException err)
                { }
            }
        }
    }
    @Override
    public void mover(int movimento, Ambiente ambiente) throws MovimentoInvalidoException{
        Random r = new Random();
        int[] casos = {1, 2, 3, 4};
        int new_movimento = movimento;
        if(this.verificar_modelo(movimento, ambiente)==false){
            while(this.verificar_modelo(new_movimento, ambiente)==false){
                new_movimento=casos[r.nextInt(4)];
            }
            movimento = new_movimento;
        }
        this.mostrarMatriz();
        try{
            super.mover(movimento, ambiente);
        }catch(MovimentoInvalidoException e){
            if(!errou){
                errou = true;
                throw e;
            }
            while(errou){
                try{
                    super.mover(casos[r.nextInt(4)], ambiente);
                
                    errou = false;
                }catch(MovimentoInvalidoException err)
                { }
            }
        }
    }
    public boolean verificar_modelo(int movimento, Ambiente ambiente) {
        int x = coordenadas[0];
        int y = coordenadas[1];
        int novoX = x, novoY = y;
    
        if (movimento == 1) novoX--;        
        else if (movimento == 2) novoX++;   
        else if (movimento == 3) novoY++;   
        else if (movimento == 4) novoY--; 
    
        if (novoX < 0 || novoY < 0 || novoX >= ambiente.getDim_y() || novoY >= ambiente.getDim_x()) {
            return false;
        }
    
        String[][] obstaculos = ambiente.getObstaculos();
    
        if (obstaculos[novoX][novoY].strip().equals("O")) {
            modelo[novoX][novoY] = "O";
            return false;
        }
    
        if (modelo[novoX][novoY].strip().equals("L")) {
            return todasAsOpcoesExploradas(x, y, ambiente);
        }
    
        modelo[x][y] = "L";
        return true; 
    }
    
    private boolean todasAsOpcoesExploradas(int x, int y, Ambiente ambiente) {
        int[][] direcoes = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; 
        String[][] obstaculos = ambiente.getObstaculos();
    
        for (int[] direcao : direcoes) {
            int novoX = x + direcao[0];
            int novoY = y + direcao[1];
    
            if (novoX < 0 || novoY < 0 || novoX >= ambiente.getDim_y() || novoY >= ambiente.getDim_x()) {
                continue;
            }
    
            if (!modelo[novoX][novoY].strip().equals("L") && !obstaculos[novoX][novoY].strip().equals("O")) {
                return false;
            }
        }
        modelo[x][y] = "L";
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
