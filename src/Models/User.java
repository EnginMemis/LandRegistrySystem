package Models;

import java.sql.Date;

public class User {
    private Integer ssn;
    private String fname;
    private String lname;
    private Date birthDate;
    private String gender;
    private String phoneNumber;
    private String email;
    private String address;
    private double wallet;
    private String role;

    public User(String fname,
                String lname,
                Date birthDate,
                String gender,
                String phoneNumber,
                String email,
                String address,
                double wallet,
                String role) {
        this.fname = fname;
        this.lname = lname;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.wallet = wallet;
        this.role = role;
    }

    public User(Integer ssn,
                String fname,
                String lname,
                Date birthDate,
                String gender,
                String phoneNumber,
                String email,
                String address,
                double wallet,
                String role) {
        this.ssn = ssn;
        this.fname = fname;
        this.lname = lname;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.wallet = wallet;
        this.role = role;
    }

    public Integer getSsn() {
        return ssn;
    }

    public void setSsn(Integer ssn) {
        this.ssn = ssn;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
