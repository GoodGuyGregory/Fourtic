
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FourticReader {

    public char[][] intialBoard;
    public static final int WIDTH = 4;
    public static final int HEIGHT = 4;

    private int xScore;
    private int oScore;

    public int getXScore() {
        return this.xScore;
    }

    public void setXscore(int calculatedScore) {
        this.xScore = calculatedScore;
    }

    public int getOScore() {
        return this.oScore;
    }

    public void setOScore(int calculatedScore) {
        this.xScore = calculatedScore;
    }

    public FourticReader() {
    }

    /**
     * Reads The file to determine who's turn it is
     * 
     * @params: takes a file string
     * @void: sets the current state of the board for the decision tree.
     */

    // Handles the reading process of the problems...
    // https://www.geeksforgeeks.org/different-ways-reading-text-file-java/

    public void determineInitialGame(String fileName) {
        try {// read the file's content with the scanner

            List<String> foundLines = new ArrayList<>();

            // File path is passed as parameter
            File file = new File(fileName);

            // Note: Double backquote is to avoid compiler
            // interpret words
            // like \test as \t (ie. as a escape sequence)

            // Creating an object of BufferedReader class
            BufferedReader buffReader = new BufferedReader(new FileReader(file));

            // Declaring a string variable
            String boardLine;
            // Condition holds true till
            // there is character in a string
            while ((boardLine = buffReader.readLine()) != null) {
                System.out.println(boardLine);
                foundLines.add(boardLine);
            }

            // build the initial board
            this.buildInitialBoard(foundLines);

            // readlines and determine next player.

            // determine currentScore for the maximizing player

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void buildInitialBoard(List<String> foundLines) {
        char[][] providedBoard = new char[this.HEIGHT][this.WIDTH];

        for (int i = 0; i < providedBoard.length; i++) {
            for (int j = 0; j < providedBoard[0].length; j++) {
                foundLines.get(i).charAt(j);
                providedBoard[i][j] = foundLines.get(i).charAt(j);
            }
        }

        // determine the player whose next at play
        this.determinePlayerAtTurn(providedBoard);

        this.determineEachPlayerInitialScore(providedBoard);

    }

    private void determineEachPlayerInitialScore(char[][] providedBoard, char player) {

    }

    /**
     * @sets who is the maximizer by determining the player who will move next
     *       also determine's each player's score
     */

    public char determinePlayerAtTurn(char[][] providedBoard) {

        int xCount = 0;
        // determine first player to move.
        for (int i = 0; i < providedBoard.length; i++) {
            for (int j = 0; j < providedBoard[0].length; j++) {
                if (providedBoard[i][j] == 'X') {
                    xCount++;
                }
            }
        }

        // X's turn
        if (xCount % 2 == 0) {
            return 'X';
        } else {
            // O's turn
            return 'O';
        }

    }

    /**
     * Scoring:
     * every Row of 3 is +3
     * every row of 4 is +6
     * every side or corner is worth +1
     */

    /**
     * number of player's caculated cide Cell scores
     * 
     * @param currentBoard
     * @param playerSymbol
     * @return playerSideCellScore
     */
    public int scorePlayerEnds(char[][] currentBoard, char playerSymbol) {
        int playerScore = 0;
        // check first row
        for (int i = 0; i < currentBoard[0].length; i++) {
            int foundCount = 0;
            if (currentBoard[0][i] == playerSymbol) {
                foundCount++;
            }
            if (foundCount > 3) {
                playerScore += 6;
            } else {
                playerScore += foundCount;
            }
        }

        // check first column
        for (int i = 0; i < currentBoard.length; i++) {
            int foundCount = 0;
            if (currentBoard[i][0] == playerSymbol) {
                foundCount++;
            }

            if (foundCount > 3) {
                playerScore += 6;
            } else {
                playerScore += foundCount;
            }
        }

        // check the last columm
        for (int i = 0; i < currentBoard.length; i++) {
            if (currentBoard[i][0] == playerSymbol) {
                playerScore++;
            }
        }

        return playerScore;
    }

}