import java.util.Random;
public class AgenteReagenteSimples extends AgenteMolde {
    boolean errou = false;
    public AgenteReagenteSimples(String nome){
        super(nome);
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
                }catch(MovimentoInvalidoException err){ 

                }
            }
        }
    }
    @Override
    public void mover(int movimento, Ambiente ambiente) throws MovimentoInvalidoException{
        try{
            super.mover(movimento, ambiente);
        }catch(MovimentoInvalidoException e){
            if(!errou){
                errou = true;
                throw e;
            }
            Random r = new Random();
            int[] casos = {1, 2, 3, 4};
            while(errou){
                try{
                    super.mover(casos[r.nextInt(4)], ambiente);
                
                    errou = false;
                }catch(MovimentoInvalidoException err)
                { }
            }
        }
    }
}
