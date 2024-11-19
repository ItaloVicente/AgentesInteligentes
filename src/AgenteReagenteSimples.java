import java.util.Random;
public class AgenteReagenteSimples extends AgenteMolde {
    boolean errou = false;
    public AgenteReagenteSimples(String cor){
        super(cor);
    }
    @Override
    public void mover(String movimento, int dim_x, int dim_y) throws MovimentoInvalidoException{
        try{
            super.mover(movimento, dim_x, dim_y);
        }catch(MovimentoInvalidoException e){
            if(!errou){
                errou = true;
                throw e;
            }
            Random r = new Random();
            String[] casos = {"up", "down", "right", "left"};
            while(errou){
                try{
                    super.mover(casos[r.nextInt(4)], dim_x, dim_y);
                
                    errou = false;
                }catch(MovimentoInvalidoException err)
                { }
            }
        }
    }
    @Override
    public void mover(int movimento, int dim_x, int dim_y) throws MovimentoInvalidoException{
        try{
            super.mover(movimento, dim_x, dim_y);
        }catch(MovimentoInvalidoException e){
            if(!errou){
                errou = true;
                throw e;
            }
            Random r = new Random();
            int[] casos = {1, 2, 3, 4};
            while(errou){
                try{
                    super.mover(casos[r.nextInt(4)], dim_x, dim_y);
                
                    errou = false;
                }catch(MovimentoInvalidoException err)
                { }
            }
        }
    }
}
