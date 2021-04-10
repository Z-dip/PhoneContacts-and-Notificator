import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputChecker {
    Scanner scanner = new Scanner(System.in);
    PhoneBookHandler phoneBookHandler;
    NotificationHandler notificationHandler;
    static int operation = 0;


    void chooseOperation() throws IOException {
        System.out.println("Hello, what would you like to do?");
        System.out.println("Write : \n1 : for browsing or adding / deleting / changing contacts" +
                "\n2 : for scheduling certain notification" +
                "\n3 : Oops, wrong click, close the program right now");
        inputValidator();

        switch (operation) {
            case 1 -> {
                System.out.println("You chose number " + operation);
                System.out.println("Choose what do you want to do with your phone contacts :");
                System.out.println("1 : look up your existing contacts" +
                        "\n2 : adding new contact" +
                        "\n3 : delete your contact");
                inputValidator();
                phoneBookHandler = new PhoneBookHandler();
                switch (operation) {
                    case 1 -> phoneBookHandler.checkContacts();
                    case 2 -> phoneBookHandler.addContacts();
                    case 3 -> phoneBookHandler.removeContact();
                }
            }
            case 2 -> {
                System.out.println("You chose number " + operation);
                notificationHandler = new NotificationHandler(); //
                notificationHandler.timing();
            }
            case 3 -> System.exit(1);
        }
    }

    void inputValidator() {
        while (true) {
            try {
                operation = scanner.nextInt();
                if (operation >= 1 && operation <= 3) {
                    break;
                } else {
                    System.out.println("Your number doesn't suit choice from 1 to 3");
                }
            } catch (InputMismatchException e) {
                System.out.println("You put something else than number, be careful");
                scanner.nextLine();
            }
            scanner.close();
        }
    }
}