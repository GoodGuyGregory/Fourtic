
public class Main {

    public static void main(String[] args) {
        try {
            String requestedFile = System.getProperty("user.dir") + args[0];
            FourticReader fourticReader = new FourticReader();

        } catch (ArrayIndexOutOfBoundsException arrayOutBounds) {
            System.out.println("provide a .txt file as an argument...");
        }

    }
}