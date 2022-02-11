package com.example.myaddressbook.model;

public class EmployeeModel {
    private int employeeId;
    private String gender;
    private NameModel name;
    private LocationModel location;
    private String email;
    private LoginModel login;
    private DOBModel dob;
    private RegisteredModel registered;
    private String phone;
    private String cell;
    private IDModel id;
    private PictureModel picture;
    private String nat;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public NameModel getName() {
        return name;
    }

    public void setName(NameModel name) {
        this.name = name;
    }

    public LocationModel getLocation() {
        return location;
    }

    public void setLocation(LocationModel location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LoginModel getLogin() {
        return login;
    }

    public void setLogin(LoginModel login) {
        this.login = login;
    }

    public DOBModel getDob() {
        return dob;
    }

    public void setDob(DOBModel dob) {
        this.dob = dob;
    }

    public RegisteredModel getRegistered() {
        return registered;
    }

    public void setRegistered(RegisteredModel registered) {
        this.registered = registered;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public IDModel getId() {
        return id;
    }

    public void setId(IDModel id) {
        this.id = id;
    }

    public PictureModel getPicture() {
        return picture;
    }

    public void setPicture(PictureModel picture) {
        this.picture = picture;
    }

    public String getNat() {
        return nat;
    }

    public void setNat(String nat) {
        this.nat = nat;
    }
}
