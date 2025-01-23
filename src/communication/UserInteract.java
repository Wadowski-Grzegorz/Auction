package communication;

import java.util.Scanner;

public class UserInteract {
    public static boolean getBool(String message) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        System.out.print(message);

        while (choice != 1 || choice != 2) {
            System.out.print("Type in: 1 - yes, 2 - no");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice == 1) {
                    return true;
                } else if (choice == 2) {
                    return false;
                } else {
                    System.out.println("Incorrect value.");
                }
            } else {
                System.out.println("Incorrect value.");
                scanner.next();
            }
        }

        scanner.close();
        return false;
    }

    public static double getPositiveDouble(String message) {
        Scanner scanner = new Scanner(System.in);
        double value = -1;

        while (value < 0) {
            System.out.print(message);
            if (scanner.hasNextDouble()) {
                value = scanner.nextDouble();
                if (value < 0) {
                    System.out.println("Incorrect value.");
                }
            } else {
                System.out.println("Incorrect value.");
                scanner.next();
            }
        }

        return value;
    }

    public static void tellId(int id){
        System.out.println("Your id is " + id + ".");
    }

    public static void mess(String string){
        System.out.println(string);
    }
}
