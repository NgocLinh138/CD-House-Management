package controllers;

import dtos.CD;
import dtos.I_List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import utils.Color;

/**
 *
 * @author NgocLinh
 */
public class CDList extends ArrayList<CD> implements I_List {

    Scanner sc = new Scanner(System.in);
    private int cdCounter;
    String name, type, title, id, publishYear;
    double price;

    public CDList() {
        cdCounter = 0;
    }

    // 1. Add CD to the catalog
    @Override
    public void addCD() {
        int pos;

        do {
            if (cdCounter > 800) {
                System.err.println("Unable to add CD!");
            } else {
                name = utils.Validation.getName("CD collection name (game/movie/music)", true);
                type = utils.Validation.getType("CD type (audio/video)", true);
                title = utils.Validation.getString("title", true);
                price = utils.Validation.getDouble("price");

                do {
                    id = utils.Validation.getString("id", true);
                    pos = searchID(id);
                    if (pos != - 1) {
                        System.err.println("ID must not be duplicated!");
                    }
                } while (pos != -1);

                publishYear = utils.Validation.getYear("Publishing year", true);

                this.add(new CD(name, type, title, price, id, publishYear));
                cdCounter++;

                utils.Color.showMessage("Adding successfully!", Color.GREEN);

            }
        } while (utils.Validation.inputYN().equals("2"));
    }

    // Search ID (if duplicate return i, else return -1)
    public int searchID(String id) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    // Search Title
    public int searchTitle(String title) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getTitle().equals(title)) {
                return i;
            }
        }
        return -1;
    }

    // 2. Search CD by CD title
    @Override
    public void searchCD() {
        String text;

        utils.Color.showMessage("The number of CDs in the archive list: " + this.size(), Color.BLUE_UNDERLINED);

        do {
            System.out.print("\nEnter CD title you want to search: ");
            text = sc.nextLine();

            System.out.println("\nList CD information by CD title: ");

            for (CD i : this) {
                if (i.getTitle().contains(text)) {
                    i.print();
                }
            }

            if (searchTitle(text) == -1) {
                System.err.println("Not found!");
            }

        } while (text.isEmpty());
    }

    // 3. Display the catalog
    @Override
    public void displayCD() {
        utils.Color.showMessage("The number of CDs in the archive list: " + cdCounter, Color.BLUE_UNDERLINED);

        if (this.isEmpty()) {
            System.err.println("Empty list!");
        } else {
            System.out.println("\nCD List: ".toUpperCase());
            System.out.println("\n    id    |     name     |     type     |         title        |     price     |  publishing year ".toUpperCase());
            System.out.println("----------|--------------|--------------|----------------------|---------------|-------------------");

            for (CD i : this) {
                i.print();
            }

            System.out.println("----------|--------------|--------------|----------------------|---------------|-------------------");

            utils.Color.showMessage("Total: ".toUpperCase() + this.size() + " CD(s).", Color.GREEN);
        }
    }

    // 4. Update CD
    @Override
    public void updateCD() {
        String str = utils.Validation.inputUD();
        int pos;

        switch (str) {
            // 4.1. Update CD information
            case "1":
                id = utils.Validation.getString("id to be updated", true);

                pos = searchID(id);

                if (pos < 0) {
                    System.err.println("Not found!");
                    System.err.println("Failed!");
                } else {
                    name = utils.Validation.getName("updated name", false);
                    type = utils.Validation.getType("updated type", false);
                    title = utils.Validation.getString("updated title", false);
                    price = utils.Validation.getDouble("updated price", this.get(pos).getPrice());
                    publishYear = utils.Validation.getYear("updated publish year", false);

                    this.get(pos).setName(name);
                    this.get(pos).setType(type);
                    this.get(pos).setTitle(title);
                    this.get(pos).setPrice(price);
                    this.get(pos).setPublishYear(publishYear);

                    saveToFile();

                    utils.Color.showMessage("Updating successfully!", Color.GREEN);
                }
                break;

            // 4.2. Delete CD
            case "2":
                id = utils.Validation.getString("id to be deleted", true);

                pos = searchID(id.trim());

                if (pos < 0) {
                    System.err.println("Not found!");
                    System.err.println("Failed!");
                } else {
                    this.remove(pos);
                    cdCounter--;

                    saveToFile();

                    utils.Color.showMessage("Deleting successfully!", Color.GREEN);
                }
                break;
        }
    }

    // 5. Save to file
    @Override
    public void saveToFile() {
        try {
            File f = new File("CD.txt");
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < this.size(); i++) {
                String str = "";
                str += this.get(i) + "\n";
//                bw.write(md5(str));
                bw.write(str);

            }

            bw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        utils.Color.showMessage("Saving successfully!", Color.GREEN);
    }

    // md5
//    public String md5(String str) {
//        MessageDigest md;
//        String result = "";
//        try {
//            md = MessageDigest.getInstance("MD5");
//            md.update(str.getBytes());
//            BigInteger bi = new BigInteger(1, md.digest());
//
//            result = bi.toString(16);
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }

    // 6. Print all list CD from file
    @Override
    public void printList() {
        ArrayList<CD> tmp = new ArrayList<>();

        try {
            FileReader fr = new FileReader("CD.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                String[] item = line.split(",");
                name = item[0].trim();
                type = item[1].trim();
                title = item[2].trim();
                price = Double.parseDouble(item[3].trim());
                id = item[4].trim();
                publishYear = item[5].trim();

                tmp.add(new CD(name, type, title, price, id, publishYear));

            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("\nAll CD list from file: ".toUpperCase());
        System.out.println("\n    id    |     name     |     type     |         title        |     price     |  publishing year ".toUpperCase());
        System.out.println("----------|--------------|--------------|----------------------|---------------|-------------------");

        tmp.sort(Comparator.comparing(CD::getTitle));

        for (CD i : tmp) {
            i.print();
        }

        System.out.println("----------|--------------|--------------|----------------------|---------------|-------------------");
        utils.Color.showMessage("Total: ".toUpperCase() + this.size() + " CD(s).", Color.GREEN);
    }

    // Load from file
    public void loadFromFile() throws FileNotFoundException, IOException {
        File f = new File("CD.txt");

        if (!f.exists()) {
            System.out.println("File does not exist");
        }

        try {
            FileReader fr = new FileReader("CD.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                String[] item = line.split(",");
                name = item[0].trim();
                type = item[1].trim();
                title = item[2].trim();
                price = Double.parseDouble(item[3].trim());
                id = item[4].trim();
                publishYear = item[5].trim();

                int pos = searchID(id);

                if (pos == -1) {
                    this.add(new CD(name, type, title, price, id, publishYear));
                }
            }
            br.close();
            fr.close();
        } catch (FileNotFoundException | NumberFormatException e) {
        }

    }

}
