package Models;

import java.sql.Date;

public class LandRegistry {
    private Integer landRegistryId;
    private Integer propertyId;
    private Property property;
    private double price;
    private Date date;

    public LandRegistry(Integer landRegistryId, Integer propertyId, double price, Date date) {
        this.landRegistryId = landRegistryId;
        this.propertyId = propertyId;
        this.property = null;
        this.price = price;
        this.date = date;
    }

    public LandRegistry(Integer landRegistryId, Integer propertyId, Property property, double price, Date date) {
        this.landRegistryId = landRegistryId;
        this.propertyId = propertyId;
        this.property = property;
        this.price = price;
        this.date = date;
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
