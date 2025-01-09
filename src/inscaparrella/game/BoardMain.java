package inscaparrella.game;
import java.util.Random;
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
        Random random = new Random();
        int tableDimensions = random.nextInt(10, 15);

        System.out.print("   ");
        for(int i = 0; i <= 14; i++) {
            if (i <= 9) {
                System.out.print(i + " ");
            } else {
                System.out.print(i + " ");
            }
        }
        System.out.println();

        for(int i = 0; i <= 14; i++) {
            if (i <= 9) {
                System.out.print(" " + i);
            } else {
                System.out.print(i);
            }

            // Print board contents
            for(int j = 0; j <= 14; j++) {
                System.out.print(" -");
                
            }
            System.out.println();
        }
    }
}