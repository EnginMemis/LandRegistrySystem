package Models;

import java.util.Date;

public class LandRegistry {
    private int landRegistryId;
    private int propertyId;
    private Property property;
    private double price;
    private Date date;

    public LandRegistry(int landRegistryId, Property property, double price) {
        this.landRegistryId = landRegistryId;
        this.propertyId = property.getPropertyId();
        this.property = property;
        this.price = price;
        this.date = new Date();
    }

    public int getLandRegistryId() {
        return landRegistryId;
    }

    public void setLandRegistryId(int landRegistryId) {
        this.landRegistryId = landRegistryId;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
