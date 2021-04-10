import java.io.*;
import java.util.Scanner;

public class PhoneBookHandler {

    Scanner scanner = new Scanner(System.in);
    File file = new File("phoneNumbers.txt").getAbsoluteFile();

    void checkContacts() throws IOException {
        FileReader fileReader = new FileReader(file);
        int reader = fileReader.read();
        while (reader != -1) {
            System.out.print((char) reader);
            reader = fileReader.read();
        }
        fileReader.close();
    }

    void addContacts() throws IOException {
        FileWriter fileWriter = new FileWriter(file, true);
        System.out.println("Write name and phone number (ex. Andrew Hawkins 98751216)");
        System.out.println("Your format can look however you wish. (Beware, to delete certain contact," +
                " you need to rewrite the whole contact info");
        fileWriter.append(scanner.nextLine()).append("\n");
        fileWriter.close();
        System.out.println("Now your phone book looks like this :");
        checkContacts();

    }

    void removeContact() throws IOException {
        FileReader fileReader = new FileReader(file);
        FileWriter fileWriter = new FileWriter(file, true);
        StringBuilder stringBuilder = new StringBuilder();
        String test;
        String replacement;

        System.out.println("Write name of the person you want to remove and their phone number");
        checkContacts();
        System.out.println("\n" + "If you want to remove whole phone book, just write ALL (Destroys all)");
        replacement = scanner.nextLine();
        if (replacement.equals("ALL")) {
            removeAllContacts();
            System.exit(1);
        }
        int reader = fileReader.read();
        while (reader != -1) {
            stringBuilder.append((char) reader);
            reader = fileReader.read();
        }
        test = stringBuilder.toString().replace(replacement, "");
        test = test.replaceAll("\n+", "\n");
        fileWriter.append(test);
        FileWriter fw = new FileWriter(file);
        fileReader.close();
        fileWriter.close();
        fw.close();
        System.out.println("Now your phone book looks like this :");
        checkContacts();
        scanner.close();
    }

    void removeAllContacts() throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.close();
        System.out.println("Your phone book got obliterated");
        checkContacts();
    }
}