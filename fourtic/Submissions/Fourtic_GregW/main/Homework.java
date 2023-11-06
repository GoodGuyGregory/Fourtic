import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.*;

public class Homework {

    public static char[][] initialBoard;
    public static List<int[]> potentialMoves;

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
    public static int scorePlayerSideCells(char[][] currentBoard, char playerSymbol) {
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
    public static int scorePlayerDiagonals(char[][] currentBoard, char playerSymbol) {

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
                        && currentBoard[1][2] == playerSymbol)) {
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
     * number of player's calculated rows
     * 
     * @param currentBoard
     * @param playerSymbol
     * @return player's diagonal score
     */
    public static int checkPlayerRowColumnScores(char[][] currentBoard, char playerSymbol) {
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

    // create a getmoves method:
    public static List<int[]> getMoves(char[][] initialBoard) {
        // moves to fill
        List<int[]> foundMoves = new ArrayList();
        for (int i = 0; i < initialBoard.length; i++) {
            for (int j = 0; j < initialBoard[0].length; j++) {
                if (initialBoard[i][j] == '.') {
                    int[] moveCoordinates = { i, j };
                    foundMoves.add(moveCoordinates);
                }
            }
        }
        return foundMoves;
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

    public static void printCurrentPosition(char[][] position) {
        for (int i = 0; i < position.length; i++) {
            for (int j = 0; j < position[0].length; j++) {
                System.out.print(position[i][j]);
            }
            System.out.println();
        }
    }

    public static int determinePlayerScore(char[][] position, char playerSymbol) {
        // printCurrentPosition(position);
        int playerOneScore = 0;
        // check rows
        playerOneScore += checkPlayerRowColumnScores(position, playerSymbol);
        playerOneScore += scorePlayerDiagonals(position, playerSymbol);
        playerOneScore += scorePlayerSideCells(position, playerSymbol);

        int playerTwoScore = 0;
        playerSymbol = swapPlayer(playerSymbol);

        playerTwoScore += checkPlayerRowColumnScores(position, playerSymbol);
        playerTwoScore += scorePlayerDiagonals(position, playerSymbol);
        playerTwoScore += scorePlayerSideCells(position, playerSymbol);

        return playerOneScore - playerTwoScore;

    }

    public static int negaMax(char[][] position, int depth, char playerSymbol) {

        if (depth == 0 || gameComplete(position)) {
            return determinePlayerScore(position, playerSymbol);

        }
        int value = Integer.MIN_VALUE;
        List<int[]> availableMoves = getMoves(position);
        for (int[] move : availableMoves) {
            position[move[0]][move[1]] = playerSymbol;
            char opponentSymbol = swapPlayer(playerSymbol);
            int oppositePlayerValue = -1 * negaMax(position, depth - 1, opponentSymbol);
            value = Math.max(value, oppositePlayerValue);
            // // undo that previous move made to check decision tree further...
            position[move[0]][move[1]] = '.';
        }
        return value;

    }

    public static char swapPlayer(char playerAtTurn) {
        if (playerAtTurn == 'X') {
            return 'O';
        } else {
            return 'X';
        }
    }

    public static void main(String[] args) {
        try {
            String requestedFile = System.getProperty("user.dir") + args[0];
            FourticReader fourticReader = new FourticReader();
            // intial game and scores are held within the fourticReader
            fourticReader.determineInitialGame(requestedFile);

            System.out.println(String.valueOf(fourticReader.getPlayerAtTurn()) + " "
                    + negaMax(fourticReader.intialBoard, 16, fourticReader.getPlayerAtTurn()));

        } catch (ArrayIndexOutOfBoundsException arrayOutBounds) {
            System.out.println("provide a .txt file as an argument...");
        }

    }
}