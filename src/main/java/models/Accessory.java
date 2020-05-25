package models;

public class Accessory {
    private int id;
    private int price;
    private String name;
    private String description;
    private int autocamperId;

    public Accessory(int id, int price, String name, String description, int autocamperId) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.description = description;
        this.autocamperId = autocamperId;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public int getAutocamperId() {
        return autocamperId;
    }
    public void setAutocamperId(int autocamperId) {
        this.autocamperId = autocamperId;
    }
}
