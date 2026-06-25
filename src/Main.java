import java.util.Scanner;

import Main.MainTerminal;
import interfaces.MenuPrincipal;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("1 - ⌨️Terminal");
        System.out.println("2 - 🖥️Interface");

        int escolha = sc.nextInt();

        if(escolha == 1){
            MainTerminal.iniciar();
        }else{
            new MenuPrincipal();
        }
    }
}