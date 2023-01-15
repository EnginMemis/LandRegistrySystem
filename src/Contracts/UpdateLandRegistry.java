package Contracts;

import java.sql.Date;

public record UpdateLandRegistry(
        Integer propertyId,
        Integer buyerSsn,
        Integer sellerSsn,
        Integer price,
        Date issuedAt,
        boolean isActive
) {
}
