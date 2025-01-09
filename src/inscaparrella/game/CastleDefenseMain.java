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

    public static void main(String[] args) {
        int menuOption = difficultyMenu();
        boolean exitInitialMenu = false;

        while(!exitInitialMenu) {
            if (menuOption == 0) {
                System.out.println("Has elegit la opció: " + menuOption + " El joc es tancarà inmediatament.");
                sc.close();
                exitInitialMenu = true;
            }
            else if (menuOption == 1) {
                System.out.println("Has elegit la opció: " + menuOption + " jugaràs en mode fàcil.");
                exitInitialMenu = true;

            }
            else if (menuOption == 2) {
                System.out.println("Has elegit la opció: " + menuOption + " jugaràs en mode normal.");
                exitInitialMenu = true;

            }
            else if (menuOption == 3) {
                System.out.println("Has elegit la opció: " + menuOption + " jugaràs en mode difícil.");
                exitInitialMenu = true;

            }
            else {
                System.out.println("Opció invàlida, torna a intentar-ho.");
                menuOption = difficultyMenu();
            }
        }
    }

}