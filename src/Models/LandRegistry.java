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
    private Integer price;
    private Date issuedAt;
    private boolean isActive;
    private String warning;

    public LandRegistry(Integer id,
                        Integer propertyId,
                        Integer buyerSsn,
                        Integer sellerSsn,
                        Integer price,
                        Date issuedAt,
                        boolean isActive) {
        this.id = id;
        this.propertyId = propertyId;
        this.buyerSsn = buyerSsn;
        this.sellerSsn = sellerSsn;
        this.price = price;
        this.issuedAt = issuedAt;
        this.isActive = isActive;
        this.warning = null;
    }

    public LandRegistry(Integer id,
                        Integer propertyId,
                        Property property,
                        Integer buyerSsn,
                        Integer sellerSsn,
                        Integer price,
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
        this.warning = null;
    }

    public LandRegistry(Integer id,
                        Integer propertyId,
                        Property property,
                        Integer buyerSsn,
                        User buyer,
                        Integer sellerSsn,
                        User seller,
                        Integer price,
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
        this.warning = null;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(Date issuedAt) {
        this.issuedAt = issuedAt;
    }

    public void setWarning(String warning){
        this.warning = warning;
    }
    public String getWarning(){
        return warning;
    }
}
