package Contracts;

import java.sql.Date;

public record UpdateCustomer(
        String fname,
        String lname,
        Date birthDate,
        String gender,
        String phoneNumber,
        String email,
        String address,
        double wallet) {
}
