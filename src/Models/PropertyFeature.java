package Models;

public class PropertyFeature {
    private int featureId;
    private int propertyId;
    private Property property;
    private String featureTitle;
    private String featureValue;

    public PropertyFeature(int featureId, Property property, String featureTitle, String featureValue) {
        this.featureId = featureId;
        this.propertyId = property.getPropertyId();
        this.property = property;
        this.featureTitle = featureTitle;
        this.featureValue = featureValue;
    }

    public PropertyFeature(int featureId, int propertyId, String featureTitle, String featureValue) {
        this.featureId = featureId;
        this.propertyId = propertyId;
        this.property = null;
        this.featureTitle = featureTitle;
        this.featureValue = featureValue;
    }

    public int getFeatureId() {
        return featureId;
    }

    public void setFeatureId(int featureId) {
        this.featureId = featureId;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public String getFeatureTitle() {
        return featureTitle;
    }

    public void setFeatureTitle(String featureTitle) {
        this.featureTitle = featureTitle;
    }

    public String getFeatureValue() {
        return featureValue;
    }

    public void setFeatureValue(String featureValue) {
        this.featureValue = featureValue;
    }
}
