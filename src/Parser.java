import java.io.*;
import java.util.Scanner;

public class Parser {
    BST<PlayerStats> mybst = new BST<>();

    public Parser(String filename) throws FileNotFoundException {
        process(new File(filename));
    }

    public void process(File input) throws FileNotFoundException {
        Scanner scanner = new Scanner(input);

        // Skip the header row
        if (scanner.hasNextLine()) {
            scanner.nextLine(); // Skip header
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                String[] attributes = line.split(";");

                // Extract PlayerStats attributes from CSV
                String player = attributes[1];
                String position = attributes[2];
                String team = attributes[3];
                int gamesPlayed = Integer.parseInt(attributes[6]); // Adjust based on CSV structure
                double points = Double.parseDouble(attributes[attributes.length - 1]);

                // Create PlayerStats object and insert into BST
                PlayerStats newPlayer = new PlayerStats(player, position, team, gamesPlayed, points);
                mybst.insert(newPlayer);

                // Print inserted player for debugging
                System.out.println("Inserted: " + newPlayer);
            }
        }

        scanner.close();
    }

    public void operate_BST(String[] command) throws IOException {
        switch (command[0]) {
            case "insert":
                String player = command[1];
                String position = command[2];
                String team = command[3];
                int gamesPlayed = Integer.parseInt(command[4]);
                double points = Double.parseDouble(command[5]);

                PlayerStats newPlayer = new PlayerStats(player, position, team, gamesPlayed, points);
                mybst.insert(newPlayer);
                writeToFile("insert " + player, "./result.txt");
                break;

            case "search":
                String searchPlayer = command[1];
                PlayerStats searchResult = mybst.search(new PlayerStats(searchPlayer, "", "", 0, 0.0));
                if (searchResult != null) {
                    writeToFile("found " + searchPlayer, "./result.txt");
                } else {
                    writeToFile("search failed", "./result.txt");
                }
                break;

            case "remove":
                String removePlayer = command[1];
                mybst.remove(new PlayerStats(removePlayer, "", "", 0, 0.0));
                writeToFile("removed " + removePlayer, "./result.txt");
                break;

            case "print":
                for (PlayerStats playerStat : mybst) {
                    writeToFile(playerStat.toString(), "./result.txt");
                }
                break;

            default:
                writeToFile("Invalid Command", "./result.txt");
                break;
        }
    }

    public void writeToFile(String content, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            //System.out.println("Attempting to write to file: " + content); // Testing line
            writer.write(content);
            writer.newLine();
            //System.out.println("Successfully wrote to file: " + content);  // Testing line
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
