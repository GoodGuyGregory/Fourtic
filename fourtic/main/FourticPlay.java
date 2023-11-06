import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

public class FourticPlay {

    public char[][] initialPosition;

    public FourticPlay() {

    }

    public void buildIntialBoard() {
        char[][] initialPosition = new char[4][4];

        for (int i = 0; i < initialPosition.length; i++) {
            for (int j = 0; j < initialPosition[0].length; j++) {
                initialPosition[i][j] = '.';
            }
        }

        this.initialPosition = initialPosition;

    }

    public void printBoard(char[][] position) {
        for (int i = 0; i < position.length; i++) {
            for (int j = 0; j < position[0].length; j++) {

                if (j != 3) {
                    System.out.print(" " + position[i][j] + " " + "|");
                } else {
                    System.out.print(position[i][j]);
                }
            }
            if (i != 3) {
                System.out.println();
            }
        }
        System.out.println();
    }

    public void makeMove(char[][] position, int[] chosenMove, char playerSymbol) {
        position[chosenMove[0]][chosenMove[1]] = playerSymbol;
    }

    public boolean checkMove(String providedMove) {

        Pattern digitChecker = Pattern.compile("\\d");
        Matcher matcher = digitChecker.matcher(providedMove);

        if (matcher.matches()) {
            // split to coordinates into an array.
            String[] foundNumbers = providedMove.split(",");
            int[] foundCoords = { Integer.parseInt(foundNumbers[0]), Integer.parseInt(foundNumbers[1]) };

            if (this.initialPosition[foundCoords[0]][foundCoords[1]] == '.') {
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }

    }

    public void createMoveForUser(int[] providedMove) {
        this.initialPosition[providedMove[0]][providedMove[1]] = 'X';

    }

    public static boolean gameComplete(char[][] providedPos) {
        for (int i = 0; i < providedPos.length; i++) {
            for (int j = 0; j < providedPos[0].length; j++) {
                if (providedPos[i][j] == '.') {
                    return false;
                }
            }
        }

        return true;

    }

    public static void main(String[] args) {

        System.out.println("Let's Play Fourtic...");

        Scanner userInput = new Scanner(System.in);

        FourticPlay fourtic = new FourticPlay();
        fourtic.buildIntialBoard();

        while (!gameComplete(fourtic.initialPosition)) {
            // prompt the user for the first move as 'X'
            fourtic.printBoard(fourtic.initialPosition);

            System.out.println("Enter your Move: (x,y)");
            String userMove = userInput.nextLine();
            if (fourtic.checkMove(userMove)) {

                fourtic.printBoard(fourtic.initialPosition);
            } else {

                fourtic.printBoard(fourtic.initialPosition);
                System.out.println("Enter your Move: (x,y)");
            }

        }
    }
}