package models;

public class Autocamper {
    private int id;
    private int status;
    private String brand;
    private String model;
    private String photo;
    private String[] builtInFeatures;
    private int price;
    private int horsePower;
    private int mileage;
    private int fuelEfficiency;
    private String fuelType;
    private int standingHeight;
    private int maxSpeed;
    private int height;
    private int length;
    private int width;
    private int area;
    private String description;

    public Autocamper(int id, int status, String brand, String model, String photo, String[] builtInFeatures, int price, int horsePower, int mileage, int fuelEfficiency, String fuelType, int standingHeight, int maxSpeed, int height, int length, int width, int area, String description) {
        this.id = id;
        this.status = status;
        this.brand = brand;
        this.model = model;
        this.photo = photo;
        this.builtInFeatures = builtInFeatures;
        this.price = price;
        this.horsePower = horsePower;
        this.mileage = mileage;
        this.fuelEfficiency = fuelEfficiency;
        this.fuelType = fuelType;
        this.standingHeight = standingHeight;
        this.maxSpeed = maxSpeed;
        this.height = height;
        this.length = length;
        this.width = width;
        this.area = area;
        this.description = description;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }

    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String[] getBuiltInFeatures() {
        return builtInFeatures;
    }
    public void setBuiltInFeatures(String[] builtInFeatures) {
        this.builtInFeatures = builtInFeatures;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public int getHorsePower() {
        return horsePower;
    }
    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public int getMileage() {
        return mileage;
    }
    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getFuelEfficiency() {
        return fuelEfficiency;
    }
    public void setFuelEfficiency(int fuelEfficiency) {
        this.fuelEfficiency = fuelEfficiency;
    }

    public String getFuelType() {
        return fuelType;
    }
    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public int getStandingHeight() {
        return standingHeight;
    }
    public void setStandingHeight(int standingHeight) {
        this.standingHeight = standingHeight;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }
    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }

    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }

    public int getArea() {
        return area;
    }
    public void setArea(int area) {
        this.area = area;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
