
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FourticReader {

    public char[][] intialBoard;
    public static final int WIDTH;
    public static final int HEIGHT;

    public FourticReader() {
        this.WIDTH = 4;
        this.HEIGHT = 4;
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
        File fourticState = new File(fileName);
        try (// read the file's content with the scanner
                Scanner fileReader = new Scanner(fourticState)) {
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        List<String> foundLines = new ArrayList<>();

    }

    public void buildInitialBoard(List<String> foundLines) {
        char[][] providedBoard = new char[this.HEIGHT][this.WIDTH];

    }

    /**
     * @sets who is the maximizer by determining the player who will move next
     *       also determine's each player's score
     */
    public void determineInitalScores() {

    }

}