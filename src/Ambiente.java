import java.util.ArrayList;
import java.util.Random;

public class Ambiente{
    private String ambiente [] [];
    private String obstaculos [] [];
    private String sujeiras [] [];
    private int dim_x;
    private int dim_y;
    private int num_obstaculos;
    private int num_sujeiras;
    public Ambiente(int dim_x, int dim_y, int num_obstaculos, int num_sujeiras){
        this.ambiente = new String[dim_y][dim_x];
        this.obstaculos = new String[dim_y][dim_x];
        this.sujeiras = new String[dim_y][dim_x];
        this.dim_x = dim_x;
        this.dim_y = dim_y;
        this.num_obstaculos = num_obstaculos;
        this.num_sujeiras = num_sujeiras;
        for(int i=0;i<dim_y;i++){
            for(int j=0;j<dim_x;j++){
                this.ambiente[i][j]="   ";
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
            this.ambiente[y][x] = " O ";
            this.obstaculos[y][x] = " O ";
            verificadorPos00 = true;
            }
        }
        }
    }
    public boolean retirar_sujeira(int coordenada_x, int coordenada_y){
        this.sujeiras[coordenada_x][coordenada_y] = "   ";
        String cor = this.ambiente[coordenada_x][coordenada_y];
        cor = cor.trim();
        cor = cor.replace('S', ' ');
        cor = cor.trim();

        this.ambiente[coordenada_x][coordenada_y] = " " + cor + " ";
        for(int i = 0; i<dim_y; i++){
            for(int j = 0; j<dim_x; j++){
                if(sujeiras[i][j].strip().equals("S")){
                    return false;
                }
            }
        }
        return true;
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
                if(ambiente[y][x].strip().length()==0){
                    this.ambiente[y][x] = " S ";
                    this.sujeiras[y][x] = " S ";
                    verificadorObstaculoExistente = true;
                }
            }
            }
        }
    }
    public void atribuir(AgenteMolde robo,int posAntiga_x,int posAntiga_y){
        String posicaoAntigaString= ambiente[posAntiga_x][posAntiga_y].strip();
        if(posicaoAntigaString.length()==2){
            char corRobo = robo.getCor().charAt(0);
            posicaoAntigaString =  posicaoAntigaString.replace(corRobo, ' ');
            ambiente[posAntiga_x][posAntiga_y]=posicaoAntigaString+" ";
        }
        else if(posicaoAntigaString.length()==1){
            char corRobo = robo.getCor().charAt(0);
            posicaoAntigaString =  posicaoAntigaString.replace(corRobo, ' ');
            ambiente[posAntiga_x][posAntiga_y]=posicaoAntigaString+"  ";
        }
        else{
        ambiente[posAntiga_x][posAntiga_y]="   ";
        }
        int [] coordenadas = robo.getCoordenadas();
        int x = coordenadas[0];
        int y = coordenadas[1];
        String espacoVazio = ambiente[x][y];
        if(espacoVazio.equals("   ")==true){
            ambiente[x][y]=robo.getCor().charAt(0) + "  ";
        }   
        if(espacoVazio.equals("   ")==false){
            String cor = ambiente[x][y].strip();
            ambiente[x][y]= cor + robo.getCor().charAt(0)+ " ";
        }
    }
    public void mostrarMatriz(){
        ArrayList<String> linhaI = new ArrayList<>(); 
        for (int i=0;i<this.dim_y;i++){
            for(int j=0;j<dim_x;j++){
                linhaI.add(this.ambiente[i][j]);
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