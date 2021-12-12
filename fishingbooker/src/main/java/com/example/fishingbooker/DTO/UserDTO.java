package com.example.fishingbooker.DTO;

public class UserDTO {
    private String username;
    private String rolename;
    private String name;
    private String surname;
    private String address;
    private String city;
    private String country;
    private String phoneNumer;
    private String email;
    private String password;
    private String registrationReason;

    public UserDTO() {
    }

    public UserDTO(String username, String rolename) {
        this.username = username;
        this.rolename = rolename;
    }

    public UserDTO(String username, String rolename, String name, String surname, String address, String city,
                   String country, String phoneNumer, String email, String password, String registrationReason) {
        this.username = username;
        this.rolename = rolename;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.city = city;
        this.country = country;
        this.phoneNumer = phoneNumer;
        this.email = email;
        this.password = password;
        this.registrationReason = registrationReason;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumer() {
        return phoneNumer;
    }

    public void setPhoneNumer(String phoneNumer) {
        this.phoneNumer = phoneNumer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegistrationReason() {
        return registrationReason;
    }

    public void setRegistrationReason(String registrationReason) {
        this.registrationReason = registrationReason;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
