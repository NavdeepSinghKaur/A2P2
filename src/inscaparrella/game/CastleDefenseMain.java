package inscaparrella.game;
import java.util.Random;
import java.util.Scanner;

/*
* Authors:
*   - Navdeep Singh Kaur
*   -
*   -
* */

/*
    LEFT TO DO:
        FIX BUGS
 */

public class CastleDefenseMain {
    private static final Scanner sc = new Scanner(System.in);

    public static final String RESET = "\033[0m";          // Text Reset
    public static final String RED_BOLD = "\033[1;31m";    // RED
    public static final String GREEN_BOLD = "\033[1;32m";  // GREEN
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW

    public static void game(String difficulty) {
        Random random = new Random();
        boolean differentCoodrinates = false;
        boolean reservedSpace = false;
        boolean stopGame = false;
        boolean exitTowerLoop;
        int[][] towerPosition = new int[15][4];
        int[][] enemyTower = {};
        int tableDimensions = random.nextInt(10, 15);
        int[] kingPosition = {tableDimensions / 2, 0};
        int enemiesAtStartup = 0;
        int maxEnemies = 0;
        int enemyTowerLives = 0;
        int iterations = 0; // used inseide main while loop (!stopGame loop)
        int enemiesToShow = 0;

        switch (difficulty) {
            case "easy":
                kingPosition[1] = 10;
                enemyTower = new int[6][3];
                enemyTowerLives = 1;
                enemiesAtStartup = 3;
                maxEnemies = 6;
                break;

            case "normal":
                kingPosition[1] = 7;
                enemyTower = new int[9][3];
                enemiesAtStartup = random.nextInt(4, 7);
                enemyTowerLives = 3;
                maxEnemies = 9;
                break;

            case "hard":
                kingPosition[1] = 5;
                enemyTower = new int[9][3];
                enemiesAtStartup = random.nextInt(4, 7);
                enemyTowerLives = 3;
                maxEnemies = 9;
                break;

        }
        
        for (int i = 0; i < towerPosition.length; i++) { // look if enemy life changes by difficulty
            towerPosition[i][2] = 3;
        }

        while (!differentCoodrinates) {
            for (int n = 0; n < 1; n++) { // Made to check twiice if numbers are repetea
                for (int i = 0; i < enemyTower.length; i++) {
                    for (int j = 0; j < enemyTower.length; j++) {
                        for (int k = 0; k < enemyTower[0].length - 1; k++) {
                            if (enemyTower[i][k] == enemyTower[j][k]) {
                                switch (random.nextInt(4)) { //  Starts from top, clockwise order 0 top, 1 right, 2 bottom, 3 left
                                    case 0:
                                        enemyTower[j] = new int[]{0, random.nextInt(tableDimensions), enemyTowerLives, 0};
                                        break;
                                    case 1:
                                        enemyTower[j] = new int[]{tableDimensions, random.nextInt(tableDimensions), enemyTowerLives, 0};
                                        break;
                                    case 2:
                                        enemyTower[j] = new int[]{random.nextInt(tableDimensions), tableDimensions, enemyTowerLives, 0};
                                        break;
                                    case 3:
                                        enemyTower[j] = new int[]{random.nextInt(tableDimensions), 0, enemyTowerLives, 0};
                                        break;
                                }
                            }
                        }
                    }
                }
            }
            differentCoodrinates = true;
        }

        // TABLE
        while (!stopGame) {
            iterations++;

            // ENEMIES TO SHOW
            if (iterations == 1)
                enemiesToShow = enemiesAtStartup;
            else if (random.nextBoolean() && enemiesToShow < maxEnemies) {
                enemiesToShow++;

                for (int i = 0; i < enemiesToShow; i++) {
                    enemyTower[i][3] = 1;
                }

                // BOARD PRINTING ALGORITTHM
                System.out.print("   ");
                for (int j = 0; j <= 1; j++) {
                    for (int i = 0; i <= tableDimensions; i++) {
                        if (i <= 9)
                            System.out.print(i + "  ");
                        else
                            System.out.print(i + " ");
                    }
                    System.out.print("     ");
                }

                System.out.println();
                for (int i = 0; i <= tableDimensions; i++) {
                    if (i <= 9)
                        System.out.print(" " + i);
                    else
                        System.out.print(i);

                    for (int j = 0; j <= tableDimensions; j++) {
                        for (int k = 0; k < enemiesToShow; k++) {
                            if (enemyTower[k][0] == i && enemyTower[k][1] == j && enemyTower[k][3] == 1 && enemyTower[k][2] > 0) {
                                System.out.print(RED_BOLD + " E " + RESET);
                                reservedSpace = true;
                            }
                        }

                        if (!reservedSpace) {
                            for (int[] k : towerPosition) {
                                if (k[0] == i && k[1] == j && k[3] == 1 && k[2] > 0) {
                                    System.out.print(YELLOW_BOLD + " T " + RESET);
                                    reservedSpace = true;
                                }
                            }
                        }
                        if (!reservedSpace) {
                            if (kingPosition[0] == i && i == j)
                                System.out.print(GREEN_BOLD + " C " + RESET);
                            else
                                System.out.print(" - ");
                        } else {
                            reservedSpace = false;
                        }
                    }
                    System.out.print("     ");
                    for (int j = 0; j <= tableDimensions; j++) {
                        for (int k = 0; k < enemiesToShow; k++) {
                            if (enemyTower[k][0] == i && enemyTower[k][1] == j && enemyTower[k][3] == 1 && enemyTower[k][2] > 0) {
                                System.out.print(RED_BOLD + " " + enemyTower[k][2] + " " + RESET);
                                reservedSpace = true;
                            }
                        }
                        for (int[] l : towerPosition) {
                            if (l[0] == i && l[1] == j && l[3] == 1 && l[2] > 0) {
                                System.out.print(YELLOW_BOLD + " " + l[2] + " " + RESET);
                                reservedSpace = true;
                            }
                        }
                        if (!reservedSpace) {
                            if (kingPosition[0] == j && i == j)
                                System.out.print(GREEN_BOLD + " " + kingPosition[1] + " " + RESET);
                            else
                                System.out.print(" 0 ");
                        }
                        else {
                            reservedSpace = false;
                        }
                    }
                    System.out.println();
                }
                System.out.println();

                // ENEMMY MOVEMENT ALGORITHM

                int kingLife;
                for (int i = 0; i < enemiesToShow; i++) {

                    if (enemyTower[i][2] > 0) {
                        // Enemy will look for the nearest tower, using an iterator and variable, if next iteratino position is lower, enemy will switch tower, finding the nearest
                        int towerToLock = getTowerToLock(towerPosition, enemyTower, i);
                        if (Math.abs(kingPosition[0] * kingPosition[0] - enemyTower[i][0] * enemyTower[i][1]) <= Math.abs(towerPosition[towerToLock][0] * towerPosition[towerToLock][1] - enemyTower[i][0] * enemyTower[i][1])) {

                            if (enemyTower[i][0] == kingPosition[0] && Math.abs(enemyTower[i][1] - kingPosition[0]) == 1 || enemyTower[i][1] == kingPosition[0] && Math.abs(enemyTower[i][0] - kingPosition[0]) == 1) {
                                // ENEMY AND KING LOSE THEIR LIVES
                                kingLife = kingPosition[1];
                                kingPosition[1] = kingLife - enemyTower[i][2];
                                enemyTower[i][2] = enemyTower[i][2] - kingLife;
                            } else if (enemyTower[i][0] > kingPosition[0] && Math.abs(enemyTower[i][0] - kingPosition[0]) > 1)
                                enemyTower[i][0]--;

                            else if (enemyTower[i][0] < kingPosition[0] && Math.abs(enemyTower[1][0] - kingPosition[0]) > 1)
                                enemyTower[i][0]++;

                            else if (enemyTower[i][1] > kingPosition[0])
                                enemyTower[i][1]--;

                            else if (enemyTower[i][1] < kingPosition[0])
                                enemyTower[i][1]++;
                        }

                        if (Math.abs(enemyTower[i][0] - towerPosition[towerToLock][0]) <= 1 && Math.abs(enemyTower[i][1] - towerPosition[towerToLock][1]) <= 1 && iterations > 1) {
                            System.out.println("Les defenses han atacat! -- 3");
                            enemyTower[i][2] = enemyTower[i][2] - towerPosition[i][2];
                        } else if (enemyTower[i][0] > towerPosition[towerToLock][0])
                            enemyTower[i][0]--;

                        else if (enemyTower[i][0] < towerPosition[towerToLock][0])
                            enemyTower[i][0]++;

                        else if (enemyTower[i][1] > towerPosition[towerToLock][0])
                            enemyTower[i][1]--;

                        else if (enemyTower[i][1] < towerPosition[towerToLock][0])
                            enemyTower[i][1]++;
                    }
                    if (kingPosition[1] < 0) {
                        System.out.println("El castell ha caigut. Has perdut.");
                        stopGame = true;
                        difficultyMenu();
                    }
                }

                // TOWER
                exitTowerLoop = false;
                String outOrTower;
                while (!exitTowerLoop) {
                    String[] coordinates;
                    String towerInput;
                    System.out.println("q -- Sortir | t -- Torre: ");
                    outOrTower = sc.nextLine();

                    // BUG (q),  >>>>

                    // Calculate for valid/invalid input when writing tower axis
                    if (outOrTower.equalsIgnoreCase("t")) {
                        System.out.println("Introdueix les coordenades de la torre (fila, columna): ");
                        towerInput = sc.nextLine();
                        coordinates = towerInput.split(", ");
                        int i = 0;
                        boolean iterateOverToverPosition = true;
                        boolean showWarning = false;

                        while (i < towerPosition.length && iterateOverToverPosition) {

                            if (towerPosition[i][3] == 0) {
                                towerPosition[i][0] = Integer.parseInt(coordinates[0].trim());
                                towerPosition[i][1] = Integer.parseInt(coordinates[1].trim());
                                for (int[] j:towerPosition) {
                                    if (towerPosition[i][0] == j[0] && towerPosition[i][1] == j[1] && j[3] > 0) {
                                        System.out.println("La torre està sobre una altra torre");
                                    }
                                }
                                for (int[] j:enemyTower) {
                                    if (towerPosition[i][0] == j[0] && towerPosition[i][1] == j[1]) {
                                        showWarning = true;
                                    }
                                }
                                if (showWarning) {
                                    System.out.println("La torre està sobre una posició ocupada");

                                }
                                if ((towerPosition[i][0] >= tableDimensions || towerPosition[i][0] <= 0) || (towerPosition[i][1] >= tableDimensions || towerPosition[i][1] <= 0)) {
                                    System.out.println("La posició de la torre no pot estar als MARGES ni a l'exterior del taulell de joc, torna a intentar-ho");
                                } else if (towerPosition[i][0] == kingPosition[0] && towerPosition[i][1] == kingPosition[0]) {
                                    System.out.println("La torre està sobre el castell, torna a intentar-ho");
                                } else {
                                    towerPosition[i][3] = 1;
                                    iterateOverToverPosition = false;
                                    exitTowerLoop = true;
                                }
                            }
                            i++;
                        }
                    } else if (outOrTower.equalsIgnoreCase("q")) {
                        stopGame = true;
                        difficultyMenu();
                    } else {
                        System.out.println("Insert a valid command.");
                    }
                }
            }
        }
    }

    private static int getTowerToLock(int[][] towerPosition, int[][] enemyTower, int i) {
        int towerToLock = 0;
        for (int j = 0; j < towerPosition.length; j++) {
            if (towerPosition[j][3] == 1 && towerPosition[j][2] > 0) {
                if (Math.abs(towerPosition[j][0] - enemyTower[i][0]) < Math.abs(towerPosition[towerToLock][0] - enemyTower[i][0])) {
                    if (Math.abs(towerPosition[j][1] - enemyTower[i][1]) < Math.abs(towerPosition[towerToLock][1] - enemyTower[i][1])) {
                        towerToLock = j;
                    }
                }
            }
        }
        return towerToLock;
    }

    public static int difficultyMenu() {

        System.out.println("""
                ~~~~ MEN Ú ~~~~
                0. Sortir
                1. Jugar en mode fàcil
                2. Jugar en mode normal
                3. Jugar en mode difı́cil""");

        System.out.print("Entrada: ");
        return sc.nextInt();
    }

    public static void main(String[] args) {
        int menuOption = difficultyMenu();
        boolean exitInitialMenu = false;

        while(!exitInitialMenu) {
            switch (menuOption) {
                case 0:
                    System.out.println("Has elegit la opció: " + menuOption + " El joc es tancarà inmediatament.");
                    sc.close();
                    exitInitialMenu = true;
                    break;
                case 1:
                    System.out.println("Has elegit la opció: " + menuOption + " jugaràs en mode fàcil.");
                    game("easy");
                    exitInitialMenu = true;
                    break;
                case 2:
                    System.out.println("Has elegit la opció: " + menuOption + " jugaràs en mode normal.");
                    game("normal");
                    exitInitialMenu = true;
                    break;
                case 3:
                    System.out.println("Has elegit la opció: " + menuOption + " jugaràs en mode difícil.");
                    game("hard");
                    exitInitialMenu = true;
                    break;
                default:
                    System.out.println("Opció invàlida, torna a intentar-ho.");
                    menuOption = difficultyMenu();
            }
        }
    }
}