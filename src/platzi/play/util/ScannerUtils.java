package platzi.play.util;

import platzi.play.content.Genre;

import java.util.Scanner;

public class ScannerUtils {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static String captureText(String message) {
        System.out.println(message + ": ");

        return SCANNER.nextLine();
    }

    public static int captureInt(String message) {
        System.out.println(message + ": ");

        while (!SCANNER.hasNextInt()) {
            System.out.println("Invalid data!");
            SCANNER.next();
        }

        int number = SCANNER.nextInt();
        SCANNER.nextLine();

        return number;
    }

    public static double captureDouble(String message) {
        System.out.println(message + ": ");

        double number = SCANNER.nextDouble();
        SCANNER.nextLine();

        return number;
    }

    public static Genre captureGenre(String message) {
        while (true) {
            System.out.println("Available genres we have in the platform are the following ones:");
            for (Genre genre : Genre.values()) {
                System.out.println("- " +genre.name());
            }
            String entryText = captureText(message);

            try {
                return Genre.valueOf(entryText.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Data not accepted. " + message);
            }
        }
    }
}
