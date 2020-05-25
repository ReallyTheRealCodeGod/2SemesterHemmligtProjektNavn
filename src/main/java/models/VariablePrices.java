package models;

public class VariablePrices {
    private int excessKilometerPrice;
    private int fuelPrice;
    private int dropOffKilometerPrice;
    private int pickUpKilometerPrice;
    private Season[] seasons;

    public VariablePrices(int excessKilometerPrice, int fuelPrice, int dropOffKilometerPrice, int pickUpKilometerPrice, Season[] seasons) {
        this.excessKilometerPrice = excessKilometerPrice;
        this.fuelPrice = fuelPrice;
        this.dropOffKilometerPrice = dropOffKilometerPrice;
        this.pickUpKilometerPrice = pickUpKilometerPrice;
        this.seasons = seasons;
    }

    public int getExcessKilometerPrice() {
        return excessKilometerPrice;
    }

    public void setExcessKilometerPrice(int excessKilometerPrice) {
        this.excessKilometerPrice = excessKilometerPrice;
    }

    public int getFuelPrice() {
        return fuelPrice;
    }

    public void setFuelPrice(int fuelPrice) {
        this.fuelPrice = fuelPrice;
    }

    public int getDropOffKilometerPrice() {
        return dropOffKilometerPrice;
    }

    public void setDropOffKilometerPrice(int dropOffKilometerPrice) {
        this.dropOffKilometerPrice = dropOffKilometerPrice;
    }

    public int getPickUpKilometerPrice() {
        return pickUpKilometerPrice;
    }

    public void setPickUpKilometerPrice(int pickUpKilometerPrice) {
        this.pickUpKilometerPrice = pickUpKilometerPrice;
    }

    public Season[] getSeasons() {
        return seasons;
    }

    public void setSeasons(Season[] seasons) {
        this.seasons = seasons;
    }
}
