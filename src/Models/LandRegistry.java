package Models;

import java.sql.Date;

public class LandRegistry {
    private Integer id;
    private Integer propertyId;
    private Property property;
    private Integer buyerSsn;
    private User buyer;
    private Integer sellerSsn;
    private User seller;
    private double price;
    private Date issuedAt;
    private boolean isActive;

    public LandRegistry(Integer id,
                        Integer propertyId,
                        Integer buyerSsn,
                        Integer sellerSsn,
                        double price,
                        Date issuedAt,
                        boolean isActive) {
        this.id = id;
        this.propertyId = propertyId;
        this.buyerSsn = buyerSsn;
        this.sellerSsn = sellerSsn;
        this.price = price;
        this.issuedAt = issuedAt;
        this.isActive = isActive;
    }

    public LandRegistry(Integer id,
                        Integer propertyId,
                        Property property,
                        Integer buyerSsn,
                        Integer sellerSsn,
                        double price,
                        Date issuedAt,
                        boolean isActive) {
        this.id = id;
        this.propertyId = propertyId;
        this.property = property;
        this.buyerSsn = buyerSsn;
        this.sellerSsn = sellerSsn;
        this.price = price;
        this.issuedAt = issuedAt;
        this.isActive = isActive;
    }

    public LandRegistry(Integer id,
                        Integer propertyId,
                        Property property,
                        Integer buyerSsn,
                        User buyer,
                        Integer sellerSsn,
                        User seller,
                        double price,
                        Date issuedAt) {
        this.id = id;
        this.propertyId = propertyId;
        this.property = property;
        this.buyerSsn = buyerSsn;
        this.buyer = buyer;
        this.sellerSsn = sellerSsn;
        this.seller = seller;
        this.price = price;
        this.issuedAt = issuedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Integer getBuyerSsn() {
        return buyerSsn;
    }

    public void setBuyerSsn(Integer buyerSsn) {
        this.buyerSsn = buyerSsn;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public Integer getSellerSsn() {
        return sellerSsn;
    }

    public void setSellerSsn(Integer sellerSsn) {
        this.sellerSsn = sellerSsn;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(Date issuedAt) {
        this.issuedAt = issuedAt;
    }
}
