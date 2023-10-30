
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
            BufferedReader br = new BufferedReader(new FileReader(file));

            // Declaring a string variable
            String boardLine;
            // Condition holds true till
            // there is character in a string
            while ((boardLine = br.readLine()) != null) {

                // Print the string
                System.out.println(boardLine);
                foundLines.add(boardLine);
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

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