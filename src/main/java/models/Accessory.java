package models;

public class Accessory {
    private int id;
    private int price;
    private String name;
    private String description;
    private int rentalId;

    public Accessory(){};
    public Accessory(int id, int price, String name, String description, int rentalId) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.description = description;
        this.rentalId = rentalId;
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

    public int getRentalId() {
        return rentalId;
    }
    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    @Override
    public String toString() {
        return "Accessory{" +
                "id=" + id +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", rentalId=" + rentalId +
                '}';
    }
}

