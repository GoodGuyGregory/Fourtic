
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

    private char playerAtTurn;

    public int getXScore() {
        return this.xScore;
    }

    public void setXScore(int calculatedScore) {
        this.xScore = calculatedScore;
    }

    public int getOScore() {
        return this.oScore;
    }

    public void setOScore(int calculatedScore) {
        this.oScore = calculatedScore;
    }

    public void setPlayerAtTurn(char playerSymbol) {
        this.playerAtTurn = playerSymbol;
    }

    public char getPlayerAtTurn() {
        return this.playerAtTurn;
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
                // System.out.println(boardLine);
                foundLines.add(boardLine);
            }

            // build the initial board
            this.buildInitialBoard(foundLines);

            // readlines and determine next player.

            // determine currentScore for the maximizing player

        } catch (IOException e) {

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

        this.intialBoard = providedBoard;

        // determine the player whose next at play
        setPlayerAtTurn(this.determinePlayerAtTurn(providedBoard));

        // pass the player at turn as playerOne
        this.determinePlayerScores(providedBoard, 'X', 'O');

    }

    // set's x's and o's initial scores

    private void determinePlayerScores(char[][] providedBoard, char playerOne, char playerTwo) {
        int xScore = 0;
        int oScore = 0;

        xScore += checkPlayerRowColumnScores(providedBoard, playerOne);
        oScore += checkPlayerRowColumnScores(providedBoard, playerTwo);

        xScore += scorePlayerDiagonals(providedBoard, playerOne);
        oScore += scorePlayerDiagonals(providedBoard, playerTwo);

        xScore += scorePlayerSideCells(providedBoard, playerOne);
        oScore += scorePlayerSideCells(providedBoard, playerTwo);

        this.setXScore(xScore);
        this.setOScore(oScore);

    }

    /**
     * @sets who is the maximizer by determining the player who will move next
     *       also determine's each player's score
     */

    public char determinePlayerAtTurn(char[][] providedBoard) {

        int xCount = 0;
        int oCount = 0;
        // determine first player to move.
        for (int i = 0; i < providedBoard.length; i++) {
            for (int j = 0; j < providedBoard[0].length; j++) {
                if (providedBoard[i][j] == 'X') {
                    xCount++;
                } else {
                    if (providedBoard[i][j] == 'O') {
                        oCount++;
                    }
                }
            }
        }

        // X's turn
        if (xCount > oCount) {
            return 'O';
        } else {
            // X's turn
            return 'X';
        }

    }

    /**
     * Scoring:
     * every row of 3 is +3
     * every row of 4 is +6
     * every side or corner is worth +1
     */

    /**
     * number of player's caculated side Cell scores
     * 
     * @param currentBoard
     * @param playerSymbol
     * @return playerSideCellScore
     */
    public int scorePlayerSideCells(char[][] currentBoard, char playerSymbol) {
        int playerScore = 0;
        // check first row
        for (int i = 0; i < currentBoard[0].length; i++) {
            if (currentBoard[0][i] == playerSymbol) {
                playerScore += 1;
            }
        }

        // check first middle column
        if (currentBoard[1][0] == playerSymbol) {
            playerScore++;
        }
        if (currentBoard[2][0] == playerSymbol) {
            playerScore++;
        }

        // check the last middle column
        if (currentBoard[1][3] == playerSymbol) {
            playerScore++;
        }
        if (currentBoard[2][3] == playerSymbol) {
            playerScore++;
        }

        // check last row
        for (int i = 0; i < currentBoard[0].length; i++) {
            if (currentBoard[3][i] == playerSymbol) {
                playerScore++;
            }

        }

        return playerScore;
    }

    /**
     * number of player's calculated diagonals
     * 
     * @param currentBoard
     * @param playerSymbol
     * @return player's diagonal score
     */
    public int scorePlayerDiagonals(char[][] currentBoard, char playerSymbol) {

        int playerScore = 0;
        // check right and left diagonals on the first two rows of the board

        // Right Diagonal:

        // top diagonal:
        if (currentBoard[0][2] == playerSymbol &&
                currentBoard[1][1] == playerSymbol && currentBoard[2][0] == playerSymbol) {
            playerScore += 3;
        }
        // check middle diagonal:
        if (currentBoard[0][3] == playerSymbol && currentBoard[1][2] == playerSymbol
                && currentBoard[2][1] == playerSymbol && currentBoard[3][0] == playerSymbol) {
            playerScore += 6;
        } else if ((currentBoard[0][3] == playerSymbol && currentBoard[1][2] == playerSymbol
                && currentBoard[2][1] == playerSymbol)
                || (currentBoard[3][0] == playerSymbol && currentBoard[2][1] == playerSymbol
                        && currentBoard[1][3] == playerSymbol)) {
            playerScore += 3;
        }
        // bottom diagonal
        if (currentBoard[1][3] == playerSymbol &&
                currentBoard[2][2] == playerSymbol && currentBoard[3][1] == playerSymbol) {
            playerScore += 3;
        }

        // Left Diagonal:

        // bottom diagonal
        if (currentBoard[1][0] == playerSymbol && currentBoard[2][1] == playerSymbol
                && currentBoard[3][2] == playerSymbol) {
            playerScore += 3;
        }
        // middle diagonal:
        if (currentBoard[0][0] == playerSymbol && currentBoard[1][1] == playerSymbol
                && currentBoard[2][2] == playerSymbol && currentBoard[3][3] == playerSymbol) {
            playerScore += 6;
        } else if ((currentBoard[3][3] == playerSymbol && currentBoard[2][2] == playerSymbol
                && currentBoard[1][1] == playerSymbol)
                || (currentBoard[0][0] == playerSymbol && currentBoard[1][1] == playerSymbol
                        && currentBoard[2][2] == playerSymbol)) {
            playerScore += 3;
        }
        //
        // top row diagonal:
        if (currentBoard[0][1] == playerSymbol && currentBoard[1][2] == playerSymbol
                && currentBoard[2][3] == playerSymbol) {
            playerScore += 3;
        }

        return playerScore;

    }

    /**
     * number of player's calculated diagonals
     * 
     * @param currentBoard
     * @param playerSymbol
     * @return player's diagonal score
     */
    public int checkPlayerRowColumnScores(char[][] currentBoard, char playerSymbol) {
        int playerScore = 0;

        // checks all rows for the same character
        for (int i = 0; i < currentBoard.length; i++) {
            int symbolCount = 0;

            for (int j = 0; j < currentBoard[0].length; j++) {
                if (currentBoard[i][j] == playerSymbol) {
                    if (j == 0) {
                        symbolCount++;
                    } else {
                        // checks for consequtive matches within a row iteration
                        if (currentBoard[i][j - 1] == playerSymbol) {
                            symbolCount++;
                        } else {
                            if (symbolCount >= 1) {
                                symbolCount = 0;
                            } else {
                                symbolCount++;
                            }

                        }
                    }
                }
            }
            if (symbolCount > 3) {
                playerScore += 6;
            } else if (symbolCount == 3) {
                playerScore += 3;
            }
        }

        // check columns for the same character
        for (int i = 0; i < currentBoard.length; i++) {
            int symbolCount = 0;
            for (int j = 0; j < currentBoard.length; j++) {
                if (currentBoard[j][i] == playerSymbol) {
                    if (j == 0) {
                        symbolCount++;
                    } else {
                        // checks for consequtive matches within a row iteration
                        if (currentBoard[j - 1][i] == playerSymbol) {
                            symbolCount++;
                        } else {
                            if (symbolCount >= 1) {
                                symbolCount = 0;
                            } else {
                                symbolCount++;
                            }
                        }
                    }
                }
            }
            if (symbolCount > 3) {
                playerScore += 6;
            } else if (symbolCount == 3) {
                playerScore += 3;
            }
        }

        return playerScore;

    }
}