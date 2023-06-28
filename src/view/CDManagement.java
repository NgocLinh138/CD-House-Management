package view;

import controllers.CDList;
import controllers.Menu;
import dtos.CD;
import java.io.IOException;
import java.util.ArrayList;
import utils.Color;

/**
 *
 * @author NgocLinh
 */
public class CDManagement extends ArrayList<CD> {

    public static void main(String[] args) throws IOException {
        
        CDList list = new CDList();
        Menu menu = new Menu();
        int choice;

        list.loadFromFile();

        utils.Color.showMessage("\n\n========== WELCOME TO THE CD HOUSE ==========", Color.YELLOW_BACKGROUND);

        do {
            System.out.println("\n\n----------------------------------");
            menu.showMenu();
            System.out.println("----------------------------------\n");

            choice = menu.getChoice();

            System.out.println();

            switch (choice) {
                case 1:
                    list.addCD();
                    break;

                case 2:
                    list.searchCD();
                    break;

                case 3:
                    list.displayCD();
                    break;

                case 4:
                    do {
                        list.updateCD();
                    } while (utils.Validation.inputYN().equals("2"));

                    break;

                case 5:
                    do {
                        list.saveToFile();
                    } while (utils.Validation.inputYN().equals("2"));
                    break;

                case 6:
                    do {
                        list.printList();
                    } while (utils.Validation.inputYN().equals("2"));
                    break;

                default:
                    System.out.println("Good bye!");
                    break;
            }
        } while (choice > 0 && choice < 7);

    }
}
