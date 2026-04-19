package platzi.play.util;

import java.util.Scanner;

public class ScannerUtils {

    private static Scanner scanner = new Scanner(System.in);

    public static String captureText(String message) {
        System.out.println(message + ": ");

        return scanner.nextLine();
    }

    public static int captureInt(String message) {
        System.out.println(message + ": ");

        int number = scanner.nextInt();
        scanner.nextLine();

        return number;
    }

    public static double captureDouble(String message) {
        System.out.println(message + ": ");

        double number = scanner.nextDouble();
        scanner.nextLine();

        return number;
    }
}
