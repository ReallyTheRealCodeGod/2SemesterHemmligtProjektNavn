package models;

public class BuiltInFeature {
    private int id;
    private String name;
    private String photo;
    private String description;

    public BuiltInFeature() {}
    public BuiltInFeature(int id, String name, String photo, String description) {
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
