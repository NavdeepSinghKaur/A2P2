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
    private static final Random random = new Random();
    public static final String RESET = "\033[0m";          // Text Reset
    public static final String RED_BOLD = "\033[1;31m";    // RED
    public static final String GREEN_BOLD = "\033[1;32m";  // GREEN
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW

    private static void game(int[] kingPosition, int tableDimensions, int[][] enemyTower, int enemyTowerLives, int enemiesAtStartup, int maxEnemies, int towerLives) {
        boolean reservedSpace = false;
        boolean stopGame = false;
        boolean exitTowerLoop;
        int[][] towerPosition = new int[15][4];
        int enemiesToShow = 0;
        int iterations = 0; // used inseide main while loop (!stopGame loop)

        for (int i = 0; i < towerPosition.length; i++) {
            towerPosition[i][2] = towerLives;
        }

        // enemy towers initializetion
        enemyPositionInitalizer(enemyTower, tableDimensions, enemyTowerLives);

        // TABLE
        while (!stopGame) {
            iterations++;

            // ENEMIES TO SHOW
            if (iterations == 1)
                enemiesToShow = enemiesAtStartup;
            else if (random.nextBoolean() && enemiesToShow < maxEnemies)
                enemiesToShow++;

                for (int i = 0; i < enemiesToShow; i++) {
                    enemyTower[i][3] = 1;
                }
            // BOARD PRINTING ALGORITTHM
            boardPrintingAlgorithm(enemyTower, tableDimensions, enemiesToShow, reservedSpace, towerPosition, kingPosition);

            // Enemy movement algorith m
            stopGame = enemyMovementAlgorithm(enemiesToShow, enemyTower, towerPosition, kingPosition, iterations);

            // TOWER
            exitTowerLoop = false;
            String outOrTower;
            while (!exitTowerLoop) {
                String[] coordinates;
                String towerInput;
                System.out.println("q -- Sortir | t -- Torre: ");
                outOrTower = sc.nextLine();

                // Calculate for valid/invalid input when writing tower axis
                if (outOrTower.equalsIgnoreCase("t")) {
                    System.out.println("Introdueix les coordenades de la torre (fila, columna): ");
                    towerInput = sc.nextLine();
                    coordinates = towerInput.split(" ");
                    int i = 0;
                    boolean iterateOverTowerPosition = true;
                    boolean invalidPosition = false;

                    while (i < towerPosition.length && iterateOverTowerPosition) {
                        if (towerPosition[i][3] == 0) {
                            towerPosition[i][0] = Integer.parseInt(coordinates[0].trim());
                            towerPosition[i][1] = Integer.parseInt(coordinates[1].trim());
                            for (int[] j:towerPosition) {
                                if (towerPosition[i][0] == j[0] && towerPosition[i][1] == j[1] && j[3] > 0)
                                    invalidPosition = true;
                            }
                            for (int[] j:enemyTower) {
                                if (towerPosition[i][0] == j[0] && towerPosition[i][1] == j[1])
                                    invalidPosition = true;
                            }
                            if ((towerPosition[i][0] >= tableDimensions || towerPosition[i][0] <= 0) || (towerPosition[i][1] >= tableDimensions || towerPosition[i][1] <= 0)) {
                                System.out.println("ERROR: La posició de la torre no pot estar als MARGES ni a l'exterior del taulell de joc, torna a intentar-ho");
                                invalidPosition = true;
                                iterateOverTowerPosition = false;
                            }
                            else if (towerPosition[i][0] == kingPosition[0] && towerPosition[i][1] == kingPosition[0]) {
                                System.out.println("ERROR: La torre està sobre el castell, torna a intentar-ho.");
                                invalidPosition = true;
                                iterateOverTowerPosition = false;
                            }
                            else if (!invalidPosition){
                                towerPosition[i][3] = 1;
                                iterateOverTowerPosition = false;
                                exitTowerLoop = true;
                            }
                            else {
                                System.out.println("ERROR: La torre està sobre una posició ocupada.");
                                iterateOverTowerPosition = false;
                            }
                        }
                        i++;
                    }
                }
                else if (outOrTower.equalsIgnoreCase("q")) {
                    exitTowerLoop = true;
                    stopGame = true;
                    difficultyMenu();
                }
                else
                    System.out.println("ERROR: Inserta \"q\" o \"t\".");
            }
        }
    }
    
    private static boolean enemyMovementAlgorithm(int enemiesToShow, int[][] enemyTower, int[][] towerPosition, int[] kingPosition, int
                                                   iterations) {
        boolean returnValue = false;
        int kingLife;
        for (int i = 0; i < enemiesToShow; i++) {
            boolean noEnemiesToShow = true;
            for (int[] k:enemyTower) {
                if (k[2] > 0)
                    noEnemiesToShow = false;
            }

            if (noEnemiesToShow) {
                returnValue = true;
                System.out.println("EL CASTELL HA SOBREVISCUT L'ATAC!");
            }
            if (enemyTower[i][2] > 0) {
                // Enemy will look for the nearest tower, using an iterator and variable, if next iteratino position is lower, enemy will switch tower, finding the nearest
                int towerToLock = getTowerToLock(towerPosition, enemyTower, i);

                if (Math.abs(kingPosition[0] * kingPosition[0] - enemyTower[i][0] * enemyTower[i][1]) <= Math.abs(towerPosition[towerToLock][0] * towerPosition[towerToLock][1] - enemyTower[i][0] * enemyTower[i][1])) {
                    if (enemyTower[i][0] == kingPosition[0] && Math.abs(enemyTower[i][1] - kingPosition[0]) == 1 || enemyTower[i][1] == kingPosition[0] && Math.abs(enemyTower[i][0] - kingPosition[0]) == 1) {
                        // ENEMY AND KING LOSE THEIR LIVES
                        kingLife = kingPosition[1];
                        kingPosition[1] = kingLife - enemyTower[i][2];
                        enemyTower[i][2] = enemyTower[i][2] - kingLife;
                    }
                    else if (enemyTower[i][0] > kingPosition[0] && Math.abs(enemyTower[i][0] - kingPosition[0]) > 1)
                        enemyTower[i][0]--;

                    else if (enemyTower[i][0] < kingPosition[0] && Math.abs(enemyTower[1][0] - kingPosition[0]) > 1)
                        enemyTower[i][0]++;

                    else if (enemyTower[i][1] > kingPosition[0])
                        enemyTower[i][1]--;

                    else if (enemyTower[i][1] < kingPosition[0])
                        enemyTower[i][1]++;
                }

                else if (Math.abs(enemyTower[i][0] - towerPosition[towerToLock][0]) <= 1 && Math.abs(enemyTower[i][1] - towerPosition[towerToLock][1]) <= 1 && iterations > 1) {
                    System.out.println("Les defenses han atacat!");
                    enemyTower[i][2] = enemyTower[i][2] - towerPosition[i][2];
                }
                else if (enemyTower[i][0] > towerPosition[towerToLock][0])
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
                returnValue = true;
                difficultyMenu();
            }
        }
        return returnValue;
    }
    private static void boardPrintingAlgorithm(int[][] enemyTower, int tableDimensions, int enemiesToShow, boolean reservedSpace, int[][] towerPosition, int[] kingPosition) {

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
                for (int[] k:enemyTower) {
                    if (k[0] == i && k[1] == j && k[3] == 1 && k[2] > 0) {
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
                }
                else
                    reservedSpace = false;
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
                else
                    reservedSpace = false;
            }
            System.out.println();
        }
        System.out.println();
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

    private static void enemyPositionInitalizer(int[][] enemyTower, int tableDimensions, int enemyTowerLives) {
        boolean differentCoodrinates = false;
        while (!differentCoodrinates) {
            for (int j = 0; j < enemyTower.length; j++) {
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
                for (int i = 0; i < enemyTower.length; i++) {
                    if (enemyTower[j][0] == enemyTower[i][0] && enemyTower[i][1] == enemyTower[j][1]) {
                        switch (random.nextInt(4)) { //  Starts from top, clockwise order 0 top, 1 right, 2 bottom, 3 left
                            case 0:
                                enemyTower[i] = new int[]{0, random.nextInt(tableDimensions), enemyTowerLives, 0};
                                break;
                            case 1:
                                enemyTower[i] = new int[]{tableDimensions, random.nextInt(tableDimensions), enemyTowerLives, 0};
                                break;
                            case 2:
                                enemyTower[i] = new int[]{random.nextInt(tableDimensions), tableDimensions, enemyTowerLives, 0};
                                break;
                            case 3:
                                enemyTower[i] = new int[]{random.nextInt(tableDimensions), 0, enemyTowerLives, 0};
                                break;
                        }
                    }
                }
            }
            differentCoodrinates = true;
        }
    }

    public static void difficultyMenu() {
        Random random = new Random();
        int tableDimensions = random.nextInt(10, 15);
        int[] kingPosition = {tableDimensions / 2, 0};
        boolean exitInitialMenu = false;
        int menuOption;

        while(!exitInitialMenu) {
            System.out.println("""
                                    ~~~~ MENÚ ~~~~
                                    0. Sortir
                                    1. Jugar en mode fàcil
                                    2. Jugar en mode normal
                                    3. Jugar en mode difı́cil""");

            System.out.print("Entrada: ");
            menuOption = sc.nextInt();
            sc.nextLine();

            switch (menuOption) {
                case 0:
                    System.out.println("Has elegit la opció: " + menuOption + " El joc es tancarà inmediatament.");
                    sc.close();
                    exitInitialMenu = true;
                    break;

                case 1:
                    System.out.println("Has elegit la opció: " + menuOption + " jugaràs en mode fàcil.");
                    exitInitialMenu = true;
                    kingPosition[1] = 10;
                    game(
                        kingPosition,
                        tableDimensions,
                        new int[6][3], // enemyTower
                        1, // enemyTowerLives
                        3, // enemiesAtStartup
                        6, // maxEnemies
                        3 // towerLives
                    );
                    break;

                case 2:
                    System.out.println("Has elegit la opció: " + menuOption + " jugaràs en mode normal.");
                    exitInitialMenu = true;
                    kingPosition[1] = 7;
                    game(
                        kingPosition,
                        tableDimensions,
                        new int[9][3], // enemyTower
                        2, // enemyTowerLives
                        random.nextInt(4, 7), // enemiesAtStartup
                        9, // maxEnemies
                        2 // towerLives
                    );
                    break;

                case 3:
                    System.out.println("Has elegit la opció: " + menuOption + " jugaràs en mode difícil.");
                    exitInitialMenu = true;
                    kingPosition[1] = 5;
                    game(
                        kingPosition,
                        tableDimensions,
                        new int[9][3], // enemyTower
                        3, // enemyTowerLives
                        random.nextInt(4, 7), // enemiesAtStartup
                        9, // maxEnemies
                        1 // towerLives
                    );
                    break;

                default:
                    System.out.println("Opció invàlida, torna a intentar-ho.");
            }
        }
    }

    public static void main(String[] args) {


        difficultyMenu();
    }
}