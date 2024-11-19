public class MovimentoInvalidoException extends Exception{
    private int posx;
    private int posy;
    public MovimentoInvalidoException(int x,int y){
        this.posx=x;
        this.posy=y;
    }
    public String toString (){
        System.out.println("Movimento Invalido, sua posição em X é: " + posx + " e em Y é: " + posy);
        return ""; 
    }
}
