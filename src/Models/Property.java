package Models;

public class Property {
    private int propertyId;
    private String propertyAddress;
    private String propertyType;
    private double propertyValue;
    private double propertyArea;

    public Property(int propertyId, String propertyAddress, String propertyType, double propertyValue, double propertyArea) {
        this.propertyId = propertyId;
        this.propertyAddress = propertyAddress;
        this.propertyType = propertyType;
        this.propertyValue = propertyValue;
        this.propertyArea = propertyArea;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyAddress() {
        return propertyAddress;
    }

    public void setPropertyAddress(String propertyAddress) {
        this.propertyAddress = propertyAddress;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public double getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(double propertyValue) {
        this.propertyValue = propertyValue;
    }

    public double getPropertyArea() {
        return propertyArea;
    }

    public void setPropertyArea(double propertyArea) {
        this.propertyArea = propertyArea;
    }
}
