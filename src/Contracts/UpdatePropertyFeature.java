package Contracts;

import Models.Property;
public record UpdatePropertyFeature(
        int property_id,
        String title,
        String value
) {
}