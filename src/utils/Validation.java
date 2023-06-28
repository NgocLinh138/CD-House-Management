package utils;

import java.util.Scanner;

/**
 *
 * @author NgocLinh
 */
public class Validation {

    static Scanner sc = new Scanner(System.in);

    // get string
    public static String getString(String msg, boolean check) {
        String str;
        do {
            System.out.println("Enter " + msg + ": ");
            str = sc.nextLine();
            if (str.isEmpty()) {
                Color.showMessage("Not allow empty!", Color.RED_BOLD);
            }
        } while (str.isEmpty() && check);
        return str;
    }

    // get name
    public static String getName(String msg, boolean check) {
        String str;
        do {
            System.out.println("Enter " + msg + ": ");
            str = sc.nextLine();
            if (str.isEmpty()) {
                Color.showMessage("Not allow empty!", Color.RED_BOLD);
            } else {
                check = true;
            }
            if (!str.equals("game") && !str.equals("movie") && !str.equals("music")) {
                Color.showMessage("Please enter valid name (game/ movie/ music)! ", Color.RED_BOLD);
            }
        } while (check && (!str.equals("game") && !str.equals("movie") && !str.equals("music")));
        return str;
    }

    // get type
    public static String getType(String msg, boolean check) {
        String str;
        do {
            System.out.println("Enter " + msg + ": ");
            str = sc.nextLine();
            if (str.isEmpty()) {
                Color.showMessage("Not allow empty!", Color.RED_BOLD);
            }
            if (!str.equals("audio") && !str.equals("video")) {
                Color.showMessage("Please enter valid name (audio/ video)! ", Color.RED_BOLD);
            }
        } while (check && (!str.equals("audio") && !str.equals("video")));
        return str;
    }

    // get year
    public static String getYear(String msg, boolean check) {
        String str;
        do {
            System.out.println("Enter " + msg + ": ");
            str = sc.nextLine();
            if (str.isEmpty()) {
                Color.showMessage("Not allow empty!", Color.RED_BOLD);
            }
        } while (!str.matches("\\d{4}") && check);
        return str;
    }

    // get double
    public static double getDouble(String msg) {
        double n = 0;
        do {
            try {
                System.out.println("Enter " + msg + ": ");
                n = Double.parseDouble(sc.nextLine());
                if (n <= 0) {
                    System.err.println(n + "must be greater than 0!");
                }
            } catch (NumberFormatException e) {
            }
        } while (n <= 0);
        return n;
    }

    
    // get double (for update)
    public static double getDouble(String msg, double oldData) {
        boolean check = true;
        double n = oldData;
        do {
            try {
                System.out.println("Enter " + msg + ": ");
                String temp = sc.nextLine();
                if (temp.isEmpty()) {
                    check = false;
                    n = oldData;
                } else {
                    n = Double.parseDouble(temp);
                    check = false;
                }
                if (n <= 0) {
                    System.err.println(n + "must be greater than 0!");
                }
            } catch (NumberFormatException e) {
            }
        } while (n <= 0 || check);
        return n;
    }

    // input Update or Delete
    public static String inputUD() {
        String text;

        do {
            System.out.println("\n\n==== UPDATE CD INFORMATION ====");

            utils.Color.showMessage("Do you want to Update or Delete?", Color.PURPLE_UNDERLINED);
            utils.Color.showMessage("    1. Update" + "\n    2. Delete", Color.PURPLE_UNDERLINED);
            text = sc.nextLine();

        } while (!text.equals("1") && !text.equals("2"));
        return text;
    }

    // input Yes or No
    public static String inputYN() {
        String text;
        
        do {

            utils.Color.showMessage("\nGo back to the main menu? ", Color.PURPLE_UNDERLINED);
            utils.Color.showMessage("1. Yes \n2. No", Color.PURPLE_UNDERLINED);
            text = sc.nextLine();
            
        } while (!text.equals("1") && !text.equals("2"));
        return text;
    }

}
