import javax.swing.*;
import java.awt.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class NotificationHandler {

    Scanner scanner = new Scanner(System.in);
    static String message = "";
    int howMuchToWait = 0;


    void timing() {

        input();
        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                NotificationHandler notificationHandler = new NotificationHandler();
                notificationHandler.popUp();
            }
        };

        Timer timer = new Timer();
        timer.schedule(timerTask, (howMuchToWait * 1000L) * 60);
    }

    void popUp() {

        JFrame frame = new JFrame();

        frame.pack();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        int x = (int) rect.getMaxX() - frame.getWidth();
        int y = (int) rect.getMaxY() - frame.getHeight();

        frame.setLocation(x - 150, y - 120);
        frame.setVisible(true);
        frame.setSize(300, 125);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx++;
        constraints.weightx = 0f;
        constraints.weighty = 0f;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.gridx = 0;
        constraints.gridy++;
        constraints.weightx = 1.0f;
        constraints.weighty = 1.0f;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.BOTH;

        JLabel messageLabel = new JLabel("<HtMl>" + message);

        frame.add(messageLabel, constraints);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);

        new Thread(() -> {
            try {
                Thread.sleep(10000);
                frame.dispose();
                System.exit(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    void input() {
        System.out.println("Write your text");
        message = scanner.nextLine();
        System.out.println("Write when to come on screen (in minutes)");
        while (howMuchToWait <= 0) {
            try {
                howMuchToWait = scanner.nextInt();
                if (howMuchToWait < 0) {
                    System.out.println("Nice try");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("You put something else than number, be careful");
                scanner.nextLine();
            }
        }
        scanner.close();
    }
}