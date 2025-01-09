package inscaparrella.game;
import java.sql.SQLOutput;
import java.sql.Time;
import java.util.Random;
import java.util.Scanner;

public class BoardMain {
    //COLOR constants
    public static final String RESET = "\033[0m";          // Text Reset
    public static final String BLACK_BOLD = "\033[1;30m";  // BLACK
    public static final String RED_BOLD = "\033[1;31m";    // RED
    public static final String GREEN_BOLD = "\033[1;32m";  // GREEN
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
    public static final String BLUE_BOLD = "\033[1;34m";   // BLUE
    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    public static final String CYAN_BOLD = "\033[1;36m";   // CYAN
    public static final String WHITE_BOLD = "\033[1;37m";  // WHITE

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        int tableDimensions = random.nextInt(10, 15);
        int kingPosition;
        int towerX = 0;
        int towerY = 0;
        boolean wrongTowerPositionX = true;
        boolean wrongTowerPositionY = true;
        String input;

        // KING POSITION
        kingPosition = (tableDimensions/2);


        // Board
        // WARNING: check if tower in same coordinate as king

        // TOWER

        // CONTITION TO AVOID TOWER TO BE OVER KING
        System.out.println("Dimensions de la taula: " + tableDimensions);
        while (wrongTowerPositionX) {
            System.out.println("Fila torre: ");
            towerX = sc.nextInt();
            if (towerX == tableDimensions || towerX == 0) {
                System.out.println("La posició de la torre no pot estar als marges, torna a intentar-ho");
            } else if (towerX > tableDimensions || towerX < 0) {
                System.out.println("Introdueix un número válid que estigui dins de les dimensions.");
            }
            else {
                wrongTowerPositionX = false;
            }
        }

        while (wrongTowerPositionY) {
            System.out.println("Columna torre: ");
            towerY = sc.nextInt();
            if (towerY == tableDimensions || towerY == 0) {
                System.out.println("La posició de la torre no pot estar als marges, torna a intentar-ho");
            } else if (towerY == kingPosition && towerX == kingPosition) {
                System.out.println("La torre està sobre el castell, torna a intentar-ho");
            } else if (towerY > tableDimensions || towerY < 0) {
                System.out.println("Introdueix un número válid que estigui dins de les dimensions.");
            } else {
                wrongTowerPositionY = false;
            }
        }

        //Enemies
        boolean borderOnX = random.nextBoolean();
        boolean attackTower = random.nextBoolean();
        int whichBorder = random.nextInt(0, 4); // clockwise, starts on top -> output 0: enemy appears on upper wall and so on...
        int[] enemyTower = {0, 0};

        // calculates where enemy will appear
        if (whichBorder == 0) {
            enemyTower[1] = random.nextInt(0, tableDimensions);
        } else if (whichBorder == 1) {
            enemyTower[0] = tableDimensions;
            enemyTower[1] = random.nextInt(0, tableDimensions);
        } else if (whichBorder == 2) {
            enemyTower[0] = random.nextInt(0, tableDimensions);
            enemyTower[1] = tableDimensions;
        } else {
            enemyTower[0] = random.nextInt(0, tableDimensions);
        }

        System.out.println("Enemy position (x, y): " + enemyTower[0] + " " + enemyTower[1]);

        // TABLE
        outerloop: while (true) {
            boolean moveAxisX = random.nextBoolean();

            if (moveAxisX) {
                if (enemyTower[0] - kingPosition == 0 && Math.abs(enemyTower[1]-kingPosition)<= 1) {
                        System.out.println("King has been killed by enemy tower");
                        break outerloop;
                } else if (enemyTower[0] - kingPosition > 0) {
                    enemyTower[0]--;
                } else {
                    enemyTower[0]++;
                }
            } else {
                if (enemyTower[1] - kingPosition == 0 && Math.abs(enemyTower[0]-kingPosition)<= 1) {
                        System.out.println("King has been killed by enemy tower");
                        break outerloop;
                    } else if (enemyTower[1] - kingPosition > 0) {
                        enemyTower[1]--;
                    } else {
                        enemyTower[1]++;
                    }
                }
                if (enemyTower[0] == kingPosition && enemyTower[1] == 0) {
                    System.out.println("King has been killed by enemy");
                    break;
                }
                System.out.print("   ");
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
                        if (kingPosition == j && kingPosition == i) {
                            System.out.print(GREEN_BOLD + " C " + RESET);
                        } else if (towerX == i && towerY == j) {
                            System.out.print(YELLOW_BOLD + " T " + RESET);
                        } else if (enemyTower[0] == i && enemyTower[1] == j) {
                            System.out.print(RED_BOLD + " E " + RESET);
                        } else {
                            System.out.print(" - ");
                        }
                    }
                    System.out.println();
                }
                for (int i = 0; i <= 1e9; i++) {
                }
            }

        /* while (true) {
            boolean borderOnX = random.nextBoolean();
            boolean attackTower = random.nextBoolean();
            int[] enemyTower = {0, 0};
            if (borderOnX) {
                enemyTower[0] = tableDimensions;
                enemyTower[1] = random.nextInt(0, tableDimensions);
            }

            if(!borderOnX){
                enemyTower[0] = random.nextInt(0, tableDimensions);
                enemyTower[1] = tableDimensions;
            }

            System.out.println("Enemy position (x, y): " + enemyTower[0] + " " + enemyTower[1]);

        } */

        }
    }
