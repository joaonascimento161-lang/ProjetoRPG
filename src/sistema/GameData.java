package sistema;

public class GameData {
    
    private static boolean deusDesbloqueado = false;

    public static boolean isDeusDesbloqueado(){
        return deusDesbloqueado;
    }

    public static void desbloquearDeus(){
        
        if(!deusDesbloqueado){

            deusDesbloqueado = true;

            System.out.println("\n--------------------");
            System.out.println("CLASSE DEUS DESBLOQUEADA");
            System.out.println("--------------------");
        }
    }

    public static void setDeusDesbloqueado(boolean valor){
        deusDesbloqueado = valor;
    }
}