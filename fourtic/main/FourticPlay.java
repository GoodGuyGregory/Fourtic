import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

public class FourticPlay {

    public char[][] initialPosition;
    public int[] bestMove;

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

    public void makeMove(char[][] position, int rowPos, int colPos, char playerSymbol) {
        this.initialPosition[rowPos][colPos] = playerSymbol;
    }

    public boolean checkMove(int rowPos, int colPos) {

        if (this.initialPosition[rowPos][colPos] == '.') {

            return true;
        } else {
            return false;
        }

    }

    private int[] parseUserMove(String userMove) {
        String[] foundNumbers = userMove.split(",");
        int[] foundCoords = { Integer.parseInt(foundNumbers[0]), Integer.parseInt(foundNumbers[1]) };
        return foundCoords;
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

    public static int determineSinglePlayerScore(char[][] position, char playerSymbol) {
        // printCurrentPosition(position);
        int playerScore = 0;
        // check rows
        playerScore += checkPlayerRowColumnScores(position, playerSymbol);
        playerScore += scorePlayerDiagonals(position, playerSymbol);
        playerScore += scorePlayerSideCells(position, playerSymbol);

        return playerScore;

    }

    // create a getmoves method:
    public static List<int[]> getMoves(char[][] initialBoard) {
        // moves to fill
        List<int[]> foundMoves = new ArrayList<int[]>();
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

    public static char swapPlayer(char playerAtTurn) {
        if (playerAtTurn == 'X') {
            return 'O';
        } else {
            return 'X';
        }
    }

    public int negaMax(char[][] position, int depth, char playerSymbol) {

        if (depth == 0 || gameComplete(position)) {
            return determinePlayerScore(position, playerSymbol);

        }
        int value = Integer.MIN_VALUE;
        List<int[]> availableMoves = getMoves(position);
        for (int[] move : availableMoves) {
            int[] bestMove = { move[0], move[1] };
            position[move[0]][move[1]] = playerSymbol;
            char opponentSymbol = swapPlayer(playerSymbol);
            int oppositePlayerValue = -1 * negaMax(position, depth - 1, opponentSymbol);
            value = Math.max(value, oppositePlayerValue);
            if (playerSymbol == 'O') {
                this.bestMove = bestMove;
            }
            // // undo that previous move made to check decision tree further...
            position[move[0]][move[1]] = '.';
        }
        return value;

    }

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Let's Play Fourtic...");

        Scanner userInput = new Scanner(System.in);

        FourticPlay fourtic = new FourticPlay();
        fourtic.buildIntialBoard();

        while (!gameComplete(fourtic.initialPosition)) {
            System.out.println("===================================");
            System.out.println("Player's Move");
            // prompt the user for the first move as 'X'
            fourtic.printBoard(fourtic.initialPosition);

            System.out.println("Enter your Row Position: ");
            int userRowPos = Integer.parseInt(userInput.nextLine());
            System.out.println("Enter your Column Position: ");
            int userColPos = Integer.parseInt(userInput.nextLine());
            if (fourtic.checkMove(userRowPos, userColPos)) {
                fourtic.makeMove(fourtic.initialPosition, userRowPos, userColPos, 'X');
                fourtic.printBoard(fourtic.initialPosition);
            } else {
                fourtic.printBoard(fourtic.initialPosition);
                System.out.println("Enter your Move: (x,y)");
            }
            System.out.println("===================================");
            System.out.println("Computer's Move");
            Thread.sleep(5000);
            fourtic.negaMax(fourtic.initialPosition, 4, 'O');
            fourtic.makeMove(fourtic.initialPosition, fourtic.bestMove[0], fourtic.bestMove[1], 'O');
        }

        System.out.println("===================================");
        System.out.println("X's Score: " + determinePlayerScore(fourtic.initialPosition, 'X'));
        System.out.println("O's Score: " + determinePlayerScore(fourtic.initialPosition, 'O'));

        System.out.println("===================================");
        System.out.println("Games Over...");
        System.out.println("Tallying Scores...");
        Thread.sleep(5000);
        if (determinePlayerScore(fourtic.initialPosition, 'O') > determinePlayerScore(fourtic.initialPosition, 'X')) {
            System.out.println("O wins!");
        }
        if (determinePlayerScore(fourtic.initialPosition, 'O') < determinePlayerScore(fourtic.initialPosition, 'X')) {
            System.out.println("X wins!");
        } else {
            System.out.println("It's a Tie!");
        }
    }

}