package Contracts;

import java.sql.Date;

public record UpdateLandRegistry(
        Integer propertyId,
        double price,
        Date date
) {
}
