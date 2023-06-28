package dtos;


/**
 *
 * @author NgocLinh
 */
public class CD {

    private String name;
    private String type;
    private String title;
    private double price;
    private String id;
    private String publishYear;

    public CD() {
    }

    public CD(String name, String type, String title, double price, String id, String publishYear) {
        this.name = name;
        this.type = type;
        this.title = title;
        this.price = price;
        this.id = id;
        this.publishYear = publishYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!name.isEmpty()) {
            this.name = name;
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (!type.isEmpty()) {
            this.type = type;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (!title.isEmpty()) {
            this.title = title;
        }
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (!id.isEmpty()) {
            this.id = id;
        }
    }

    public String getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(String publishYear) {
        if (!publishYear.isEmpty()) {
            this.publishYear = publishYear;
        }
    }

    @Override
    public String toString() {
        return  name + "," + type + "," + title + "," + price + "," + id + "," + publishYear;
    }

    public void print() {
        System.out.printf(" %-9s| %-13s| %-13s| %-21s| %-14s| %-13s \n", id,name, type, title, price,  publishYear);
    }

}
