package inscaparrella.game;
/*
* Authors:
*   - Navdeep Singh Kaur
*   -
*   -
* */

import java.util.Scanner;

public class CastleDefenseMain {
    private static final Scanner sc = new Scanner(System.in);

    public static int difficultyMenu() {
        Scanner sc = new Scanner(System.in);

        System.out.println
                ("~~~~ MEN Ú ~~~~\n" +
                        "0. Sortir\n" +
                        "1. Jugar en mode fàcil\n" +
                        "2. Jugar en mode normal\n" +
                        "3. Jugar en mode difı́cil"
                );
        System.out.print("Entrada: ");
        return sc.nextInt();
    }

    public static void board() {
        
    }

    public static void main(String[] args) {
        difficultyMenu();
        sc.close();
    }

}