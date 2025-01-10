package inscaparrella.game;
import java.util.Random;
import java.util.Scanner;

/*
* Authors:
*   - Navdeep Singh Kaur
*   -
*   -
* */


public class CastleDefenseMain {
    private static final Scanner sc = new Scanner(System.in);

    public static final String RESET = "\033[0m";          // Text Reset
    public static final String RED_BOLD = "\033[1;31m";    // RED
    public static final String GREEN_BOLD = "\033[1;32m";  // GREEN
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW

    public static int[][] enemyTowerInitializer(int enemyNums, int lives, int boardDimension) {
        int[][] kingPosition = new int[enemyNums][3];

        return kingPosition;
    }

    public static void game(String difficulty) {
        Random random = new Random();
        int tableDimensions = random.nextInt(10, 15);
        int[] kingPosition = {0, 0};
        kingPosition[0] = (tableDimensions/2);
        int[][] enemyTower = {};

        switch (difficulty) {
            case "easy":
                kingPosition[1] = 10;
                enemyTower = enemyTowerInitializer(6, 1, tableDimensions);
                break;

            case "normal":
                kingPosition[1] = 7;
                enemyTower = enemyTowerInitializer(6, 2, tableDimensions);
                break;

            case "hard":
                kingPosition[1] = 5;
                enemyTower = enemyTowerInitializer(6, 2, tableDimensions);
                break;

        }


        int[] towerPosition = new int[2];
        boolean exitTowerLoop = false;

        // KING POSITION

        System.out.println("Dimensions de la taula: " + tableDimensions);

        //Enemies
        boolean attackTower = random.nextBoolean();

        int whichBorder = random.nextInt(4); // Selects a border starting from the top, clockwise order

        for (int i = 0; i < enemyTower[0].length; i++) {
            for (int j = 0; j < enemyTower[1].length; j++) {
                switch (random.nextInt(4)) { //  Starts from the top, clockwise order
                    case 0: // Upper border
                        enemyTower[i] = new int[]{0, random.nextInt(tableDimensions), 1};
                        break;
                    case 1: // Right border
                        enemyTower[i] = new int[]{tableDimensions, random.nextInt(tableDimensions), 1};
                        break;
                    case 2: // Bottom border
                        enemyTower[i] = new int[]{random.nextInt(tableDimensions), tableDimensions, 1};
                        break;
                    case 3: // Left border
                        enemyTower[i] = new int[]{random.nextInt(tableDimensions), 0, 1};
                        break;
                }
            }
        }

        for (int i = 0; i < enemyTower.length; i++) {
            for (int j = 0; j < enemyTower[j].length; j++) {
                System.out.print(enemyTower[i][j] + " ");
            }
            System.out.println();
        }

        /*
        System.out.println("Enemy position (x, y): " + enemyTower[0] + " " + enemyTower[1]);

        // TABLE
        outerloop: while (true) {


            // Board printing into screen
            System.out.print("   ");
            for (int i = 0; i <= tableDimensions; i++) {
                if (i <= 9) {
                    System.out.print(i + "  ");
                } else {
                    System.out.print(i + " ");
                }
            }
            System.out.print("     ");
            for (int i = 0; i <= tableDimensions; i++) {
                if (i <= 9) {
                    System.out.print(i + "  ");
                } else {
                    System.out.print(i + " ");
                }
            }

            System.out.println();

            for (int i = 0; i <= tableDimensions; i++) {
                if (i <= 9) {
                    System.out.print(" " + i);
                } else {
                    System.out.print(i);
                }

                for (int j = 0; j <= tableDimensions; j++) {
                    if (kingPosition[0] == j && kingPosition[0] == i) {
                        System.out.print(GREEN_BOLD + " C " + RESET);
                    } else if (towerPosition[0] == i && towerPosition[1] == j && i>1) {
                        System.out.print(YELLOW_BOLD + " T " + RESET);
                    } else if (enemyTower[0] == i && enemyTower[1] == j) {
                        System.out.print(RED_BOLD + " E " + RESET);
                    } else {
                        System.out.print(" - ");
                    }
                }
                System.out.print("     ");
                for (int j = 0; j <= tableDimensions; j++) {
                    if (kingPosition[0] == j && kingPosition[0] == i) {
                        System.out.print(GREEN_BOLD + " C " + RESET);
                    } else if (towerPosition[0] == i && towerPosition[1] == j && i>1) {
                        System.out.print(YELLOW_BOLD + " T " + RESET);
                    } else if (enemyTower[0] == i && enemyTower[1] == j) {
                        System.out.print(RED_BOLD + " E " + RESET);
                    } else {
                        System.out.print(" 0 ");
                    }
                }
                System.out.println();
            }
            System.out.println();

            // Calculations for enemies to look for king
            boolean moveAxisX = random.nextBoolean(); // Enemy should look first for towers, and then the king, attack the closest CODE TO CHANGE

            if (moveAxisX) {
                if (enemyTower[0] - kingPosition[0] == 0 && Math.abs(enemyTower[1]-kingPosition[0])<= 1) {
                    System.out.println("King has been killed by enemy tower");
                    break outerloop;
                } else if (enemyTower[0] - kingPosition[0] > 0) {
                    enemyTower[0]--;
                } else {
                    enemyTower[0]++;
                }
            }
            else {
                if (enemyTower[1] - kingPosition[0] == 0 && Math.abs(enemyTower[0]-kingPosition[0])<= 1) {
                    System.out.println("King has been killed by enemy tower");
                    break outerloop;
                } else if (enemyTower[1] - kingPosition[0] > 0) {
                    enemyTower[1]--;
                } else {
                    enemyTower[1]++;
                }
            }

            // TOWER
            exitTowerLoop = false;
            while (!exitTowerLoop) {
                String[] coordinates;
                String towerInput;
                System.out.println("Introdueix les coordenades de la torre (fila, columna): ");
                towerInput = sc.nextLine();

                coordinates = towerInput.split(", ");
                towerPosition[0] = Integer.parseInt(coordinates[0].trim());
                towerPosition[1] = Integer.parseInt(coordinates[1].trim());
                if((towerPosition[0] >= tableDimensions || towerPosition[0] <= 0) || (towerPosition[1] >= tableDimensions || towerPosition[1] <= 0)) {
                    System.out.println("La posició de la torre no pot estar als MARGES ni a l'exterior del taulell de joc, torna a intentar-ho");
                }
                else if (towerPosition[0] == kingPosition[0] && towerPosition[1] == kingPosition[0]) {
                    System.out.println("La torre està sobre el castell, torna a intentar-ho");
                }
                else {
                    exitTowerLoop = true;
                }

            }

        }

        */
    }

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
                game("easy");
                exitInitialMenu = true;

            }
            else if (menuOption == 2) {
                System.out.println("Has elegit la opció: " + menuOption + " jugaràs en mode normal.");
                game("normal");

                exitInitialMenu = true;

            }
            else if (menuOption == 3) {
                System.out.println("Has elegit la opció: " + menuOption + " jugaràs en mode difícil.");
                game("hard");
                exitInitialMenu = true;

            }
            else {
                System.out.println("Opció invàlida, torna a intentar-ho.");
                menuOption = difficultyMenu();
            }
        }
    }

}