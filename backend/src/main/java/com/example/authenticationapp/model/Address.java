package com.example.authenticationapp.model;

import lombok.Data;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "address")
/**
 * This class represents an address
 */
public class Address {


    private String streetNumber;
    private String streetName;
    private String zipCode;
    private String city;
    private String country;

    public Address(String streetNumber, String streetName, String zipCode, String city, String country) {
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
    }
    @PersistenceConstructor
    public Address(){}

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }


    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString(){
        return  "Address: {"+"\n"+"Street Number : "+getStreetNumber()+"\n"+
                "Street Name : "+getStreetName()+"\n"+
                "Postal Code : "+ getZipCode()+"\n"+
                "City : "+ getCity()+"\n" +
                "Country : "+getCountry()+"\n" +
                "}";
    }

    @Override
    public boolean equals(Object obj){
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Address address = (Address) obj;

        return this.getStreetName().equals(address.getStreetName()) &&
                this.getZipCode().equals(address.getZipCode()) &&
                this.getCity().equals(address.getCity()) &&
                this.getCountry().equals(address.getCountry());
    }

}
