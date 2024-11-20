import java.util.ArrayList;
import java.util.Random;

public class Tabuleiro{
    private String tabuleiro [] [];
    private String obstaculos [] [];
    private String sujeiras [] [];
    private int dim_x;
    private int dim_y;
    private int num_obstaculos;
    private int num_sujeiras;
    public Tabuleiro(int dim_x, int dim_y, int num_obstaculos, int num_sujeiras){
        this.tabuleiro = new String[dim_y][dim_x];
        this.obstaculos = new String[dim_y][dim_x];
        this.sujeiras = new String[dim_y][dim_x];
        this.dim_x = dim_x;
        this.dim_y = dim_y;
        this.num_obstaculos = num_obstaculos;
        this.num_sujeiras = num_sujeiras;
        for(int i=0;i<dim_y;i++){
            for(int j=0;j<dim_x;j++){
                this.tabuleiro[i][j]="   ";
                this.obstaculos[i][j]="   ";
                this.sujeiras[i][j]="   ";
            }
        }
    }
    public void criar_obstaculos(){
        Random r = new Random();
        int[] random_eixo_x = new int[dim_x];
        int[] random_eixo_y = new int[dim_y];
        for(int i = 0;i<dim_x;i++){
            random_eixo_x[i] = i;
        }
        for(int i = 0;i<dim_y;i++){
            random_eixo_y[i] = i;
        }
        for(int i = 0; i<num_obstaculos; i++){
            //verificador para evitar que o obstaculo seja criado na pos 00
            boolean verificadorPos00 = false;
            while(verificadorPos00 == false){
            int y = r.nextInt(dim_y);
            int x = r.nextInt(dim_x);
            if(y == 0 && x == 0){
                continue;
            }
            else{
            //O de obstaculo
            //E importante ressaltar que primeiro coloco a coordenada y e depois x por conta do formato que a matriz e gerada
            this.tabuleiro[y][x] = " O ";
            this.obstaculos[y][x] = " O ";
            verificadorPos00 = true;
            }
        }
        }
    }
    public void criar_sujeiras(){
        Random r = new Random();
        int[] random_eixo_x = new int[dim_x];
        int[] random_eixo_y = new int[dim_y];
        for(int i = 0;i<dim_x;i++){
            random_eixo_x[i] = i;
        }
        for(int i = 0;i<dim_y;i++){
            random_eixo_y[i] = i;
        }
        for(int i = 0; i<num_sujeiras; i++){
            //verificador para checar se a pos random da sujeira possui um obstaculo, voce primeira cria os obstaculos, depois as sujeiras
            boolean verificadorObstaculoExistente = false;
            while(verificadorObstaculoExistente == false){
                int y = r.nextInt(dim_y);
                int x = r.nextInt(dim_x);
                if (y==0 && x==0){
                    continue;
                }
                else{
                //S de Sujeira
                //E importante ressaltar que primeiro coloco a coordenada y e depois x por conta do formato que a matriz e gerada
                if(tabuleiro[y][x].strip().length()==0){
                    this.tabuleiro[y][x] = " S ";
                    this.sujeiras[y][x] = " S ";
                    verificadorObstaculoExistente = true;
                }
            }
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
    public String[][] getObstaculos() {
        return obstaculos;
    }
    public void setObstaculos(String[][] obstaculos) {
        this.obstaculos = obstaculos;
    }
    public String[][] getSujeiras() {
        return sujeiras;
    }
    public void setSujeiras(String[][] sujeiras) {
        this.sujeiras = sujeiras;
    }
    
}