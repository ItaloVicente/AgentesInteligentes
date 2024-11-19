import java.util.ArrayList;

public class Tabuleiro{
    private String tabuleiro [] [];
    private int dim_x;
    private int dim_y;
    public Tabuleiro(int dim_x, int dim_y){
        this.tabuleiro = new String[dim_y][dim_x];
        this.dim_x = dim_x;
        this.dim_y = dim_y;
        for(int i=0;i<dim_y;i++){
            for(int j=0;j<dim_x;j++){
                tabuleiro[i][j]="   ";
            }
        }
    }
    public void atribuir(AgenteMolde robo,int posAntiga_x,int posAntiga_y,int posicaoAliX, int posicaoAliY) throws SujeiraInvalida{
        if(posicaoAliX<0||posicaoAliX>this.dim_x-1||posicaoAliY<0||posicaoAliY>this.dim_y-1){
            throw new SujeiraInvalida(posicaoAliX, posicaoAliY);
        }
        tabuleiro[posicaoAliX][posicaoAliY]= "0  ";
        String posicaoAntigaString= tabuleiro[posAntiga_x][posAntiga_y].strip();
        if(posicaoAntigaString.length()==2){
            char corRobo = robo.getCor().charAt(0);
            posicaoAntigaString =  posicaoAntigaString.replace(corRobo, ' ');
            tabuleiro[posAntiga_x][posAntiga_y]=posicaoAntigaString+" ";
        }
        else if(posicaoAntigaString.length()==1){
            char corRobo = robo.getCor().charAt(0);
            posicaoAntigaString =  posicaoAntigaString.replace(corRobo, ' ');
            tabuleiro[posAntiga_x][posAntiga_y]=posicaoAntigaString+"  ";
        }
        else{
        tabuleiro[posAntiga_x][posAntiga_y]="   ";
        }
        int [] coordenadas = robo.getCoordenadas();
        int x = coordenadas[0];
        int y = coordenadas[1];
        String espacoVazio = tabuleiro[x][y];
        if(espacoVazio.equals("   ")==true){
            tabuleiro[x][y]=robo.getCor().charAt(0) + "  ";
        }   
        if(espacoVazio.equals("   ")==false){
            String cor = tabuleiro[x][y].strip();
            tabuleiro[x][y]= cor + robo.getCor().charAt(0)+ " ";
        }
    }
    public void mostrarMatriz(){
        ArrayList<String> linhaI = new ArrayList<>(); 
        for (int i=0;i<this.dim_y;i++){
            for(int j=0;j<dim_x;j++){
                linhaI.add(this.tabuleiro[i][j]);
            }
            System.out.println(linhaI);
            linhaI= new ArrayList<>();
        }
        System.out.println("=================================");
    }
    public int getDim_x() {
        return dim_x;
    }
    public void setDim_x(int dim_x) {
        this.dim_x = dim_x;
    }
    public int getDim_y() {
        return dim_y;
    }
    public void setDim_y(int dim_y) {
        this.dim_y = dim_y;
    }
    
}