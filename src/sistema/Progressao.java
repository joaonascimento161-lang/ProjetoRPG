package sistema;

public class Progressao {
    
    private static int AreaLiberada = 1;

    public static int getAreaLiberada(){
        return AreaLiberada;
    }

    public static void setAreaLiberada(int Area){
        AreaLiberada = Area;
    }

    public static void desbloquearProximaArea(){

        if(AreaLiberada < 5){

            AreaLiberada++;

            System.out.println("\n----------");
            System.out.println("NOVA ÁREA LIBERADA");
            System.out.println("----------");
        }
    }
}
