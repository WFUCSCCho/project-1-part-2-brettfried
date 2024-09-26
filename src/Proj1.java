import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Proj1 {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 1) {
            System.err.println("Argument count is invalid: " + args.length);
            System.err.println("Usage: java Proj1 <input_file>");
            System.exit(0);
        }

        // Create parser and process the file
        Parser parser = new Parser(args[0]);

        System.out.println("Printing all player stats:");
        for (PlayerStats playerStat : parser.mybst) {
            System.out.println(playerStat);
        }
    }
}
