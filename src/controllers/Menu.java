package controllers;

import dtos.CD;
import dtos.I_Menu;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author NgocLinh
 */
public class Menu extends ArrayList<CD> implements I_Menu {

    Scanner sc = new Scanner(System.in);

    @Override
    public int getChoice() {
        int choice = 0;
        do {
            try {
                System.out.print("Your choice: ");
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Please input number!!!");
            }
        } while (choice < 0);
        return choice;
    }

    @Override
    public void showMenu() {
        System.out.println("  CD HOUSE MANAGEMENT");
        System.out.println("1. Add CD to the catalog.");
        System.out.println("2. Search CD by CD title.");
        System.out.println("3. Display the catalog.");
        System.out.println("4. Update CD.");
        System.out.println("5. Save to file.");
        System.out.println("6. Print all list CD from file.");
        System.out.println("Others. Quit.");
    }

}
