package Models;

import java.sql.Date;

public class Customer {
    private Integer ssn;
    private String name;
    private String surname;
    private Date birthDate;
    private String gender;
    private String phoneNumber;
    private String customerEmail;
    private String customerAddress;
    private double wallet;

    public Customer(Integer ssn,
                    String name,
                    String surname,
                    Date birthDate,
                    String gender,
                    String phoneNumber,
                    String customerEmail,
                    String address, double wallet) {
        this.ssn = ssn;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.customerEmail = customerEmail;
        this.customerAddress = address;
        this.wallet = wallet;
    }

    public Integer getSsn() {
        return ssn;
    }

    public void setSsn(Integer ssn) {
        this.ssn = ssn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthDate() { return birthDate; }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerAddress() { return customerAddress; }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }
}
