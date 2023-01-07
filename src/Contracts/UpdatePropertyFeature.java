package Contracts;

import Models.Property;
public record UpdatePropertyFeature(
        int feature_id,
        int property_id,
        Property property,
        String title,
        String value
) {
}