package models;

public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNr;
    private int cprNr;
    private int postalCode;
    private String streetName;
    private String houseNr;
    private String floor;
    private int cardNr;
    private int cardCVV;

    public Customer(int id, String firstName, String lastName, String email, String phoneNr, int cprNr, int postalCode, String streetName, String houseNr, String floor, int cardNr, int cardCVV) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNr = phoneNr;
        this.cprNr = cprNr;
        this.postalCode = postalCode;
        this.streetName = streetName;
        this.houseNr = houseNr;
        this.floor = floor;
        this.cardNr = cardNr;
        this.cardCVV = cardCVV;
    }

    public Customer(){};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }

    public int getCprNr() {
        return cprNr;
    }

    public void setCprNr(int cprNr) {
        this.cprNr = cprNr;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getHouseNr() {
        return houseNr;
    }

    public void setHouseNr(String houseNr) {
        this.houseNr = houseNr;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public int getCardNr() {
        return cardNr;
    }

    public void setCardNr(int cardNr) {
        this.cardNr = cardNr;
    }

    public int getCardCVV() {
        return cardCVV;
    }

    public void setCardCVV(int cardCVV) {
        this.cardCVV = cardCVV;
    }
}
