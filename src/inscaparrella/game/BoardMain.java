package inscaparrella.game;
import java.sql.SQLOutput;
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
        int towerY;
        boolean wrongTowerPositionX = true;
        boolean wrongTowerPositionY = true;
        String input;

        // KING
        kingPosition = (tableDimensions/2);

        // Board
        // WARNING: check if tower in same coordinate as king

        // TOWER
        System.out.println("Dimensions de la taula: " + tableDimensions);
        while (wrongTowerPositionX) {
            System.out.println("Fila torre: ");
            towerX = sc.nextInt();
            if (towerX == tableDimensions) {
                System.out.println("La posició de la torre no pot estar als marges, torna a intentar-ho");
            }
            else if (towerX > tableDimensions || towerX < 0) {
                System.out.println("Introdueix un número válid que estigui dins de les dimensions.");
            }

            else {
                wrongTowerPositionX = false;
            }
        }

        while (wrongTowerPositionY) {
            System.out.println("Columna torre: ");
            towerY = sc.nextInt();
            if (towerY == tableDimensions) {
                System.out.println("La posició de la torre no pot estar als marges, torna a intentar-ho");
            }
            else if (towerY > tableDimensions || towerY < 0) {
                System.out.println("Introdueix un número válid que estigui dins de les dimensions.");
            }
            else {
                wrongTowerPositionY = false;
            }
        }

        System.out.println("Columna torre: ");
        towerY = sc.nextInt();

        // TABLE
        System.out.print("   ");
        for(int i = 0; i < tableDimensions; i++) {
            if (i <= 9) {
                System.out.print(i + "  ");
            } else {
                System.out.print(i + " ");
            }
        }
        System.out.println();

        for(int i = 0; i < tableDimensions; i++) {
            if (i <= 9) {
                System.out.print(" " + i);
            } else {
                System.out.print(i);
            }

            for(int j = 0; j < tableDimensions; j++) {
                if (kingPosition-1 == j && kingPosition-1 == i) {
                    System.out.print(GREEN_BOLD + " C " + RESET);
                }
                else if (towerX == i && towerY == j){
                    System.out.print(YELLOW_BOLD + " T " + RESET);
                }
                else {
                    System.out.print(" - ");
                }
            }
            System.out.println();
        }

        while (true) {
            boolean borderOnX = random.nextBoolean();
            boolean attackTower = random.nextBoolean();

            int enemy

            if(borderOnX){

            }

            System.out.println("Enemies");

        }

    }
}