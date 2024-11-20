import java.util.Random;

public class AgenteBaseadoEmModelo extends AgenteMolde {
    boolean errou = false;
    String[][] modelo;
    public AgenteBaseadoEmModelo(String cor, int dim_y, int dim_x){
        super(cor);
        //modelo inicialmente vazio
        modelo = new String[dim_y][dim_x];
        for(int i=0;i<dim_y;i++){
            for(int j=0;j<dim_x;j++){
                this.modelo[i][j]="   ";
            }
        }
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
        try{
            super.mover(movimento, tabuleiro);
        }catch(MovimentoInvalidoException e){
            if(!errou){
                errou = true;
                throw e;
            }
            Random r = new Random();
            int[] casos = {1, 2, 3, 4};
            while(errou){
                try{
                    super.mover(casos[r.nextInt(4)], tabuleiro);
                
                    errou = false;
                }catch(MovimentoInvalidoException err)
                { }
            }
        }
    }
}
