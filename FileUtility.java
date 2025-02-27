import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

public class FileUtility {
    private static final String FILE_PATH = "myfile.txt"; // File name

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n==== File Utility Menu ====");
            System.out.println("1. Write to File");
            System.out.println("2. Read from File");
            System.out.println("3. Modify File");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter text to write: ");
                    String textToWrite = scanner.nextLine();
                    writeToFile(textToWrite);
                    break;
                case 2:
                    readFromFile();
                    break;
                case 3:
                    System.out.print("Enter word to replace: ");
                    String oldWord = scanner.next();
                    System.out.print("Enter new word: ");
                    String newWord = scanner.next();
                    modifyFile(oldWord, newWord);
                    break;
                case 4:
                    System.out.println("Exiting program. Bye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    // Method to write text to file
    private static void writeToFile(String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(content);
            writer.newLine();
            System.out.println("✅ Text successfully added to the file.");
        } catch (IOException e) {
            System.out.println("❌ Error writing to file: " + e.getMessage());
        }
    }

    // Method to read file content
    private static void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            System.out.println("\n📄 File Content:");
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("❌ Error reading file: " + e.getMessage());
        }
    }

    // Method to modify content in the file
    private static void modifyFile(String oldWord, String newWord) {
        try {
            Path path = Paths.get(FILE_PATH);
            String content = Files.readString(path);
            if (!content.contains(oldWord)) {
                System.out.println("⚠️ Word not found in file.");
                return;
            }
            content = content.replace(oldWord, newWord);
            Files.writeString(path, content);
            System.out.println("✅ File updated successfully.");
        } catch (IOException e) {
            System.out.println("❌ Error modifying file: " + e.getMessage());
        }
    }
}
