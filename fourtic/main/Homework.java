
public class Homework {

    public static void main(String[] args) {
        try {
            String requestedFile = System.getProperty("user.dir") + args[0];
            FourticReader fourticReader = new FourticReader();
            fourticReader.determineInitialGame(requestedFile);

        } catch (ArrayIndexOutOfBoundsException arrayOutBounds) {
            System.out.println("provide a .txt file as an argument...");
        }

    }
}