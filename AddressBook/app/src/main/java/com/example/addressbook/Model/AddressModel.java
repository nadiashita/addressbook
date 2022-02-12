package com.example.addressbook.Model;

public class AddressModel {
    private StreetModel street;
    private String city;
    private String state;
    private String country;
    private String postcode;
    private CoordinateModel coordinates;
    private TimeZoneModel timezone;

    public StreetModel getStreet() {
        return street;
    }

    public void setStreet(StreetModel street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public CoordinateModel getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(CoordinateModel coordinates) {
        this.coordinates = coordinates;
    }

    public TimeZoneModel getTimezone() {
        return timezone;
    }

    public void setTimezone(TimeZoneModel timezone) {
        this.timezone = timezone;
    }
}
