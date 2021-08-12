package com.example.authenticationapp.model;



import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "company")
public class Company {

    private String code;
    private String socialReason;
    private String clientType;
    private String label;
    private String countryCode;
    private String countryName;
    private String zipCode;
    private String city;
    private String recipient;

    public Company(String code, String socialReason, String clientType, String label, String countryCode, String countryName, String zipCode, String city, String recipient) {
        this.code = code;
        this.socialReason = socialReason;
        this.clientType = clientType;
        this.label = label;
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.zipCode = zipCode;
        this.city = city;
        this.recipient = recipient;
    }

    public String getCode() {
        return code;
    }

    public String getSocialReason() {
        return socialReason;
    }

    public String getClientType() {
        return clientType;
    }

    public String getLabel() {
        return label;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setSocialReason(String socialReason) {
        this.socialReason = socialReason;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    @Override
    public String toString(){
        return  "Company: {"+"\n"+"Code: "+getCode()+"\n"+
                "Social Reason: "+getSocialReason()+"\n"+
                "Client type : "+getClientType()+"\n"+
                "Label : "+ getLabel()+"\n" +
                "Country Code: "+getCountryCode()+"\n" +
                "Country Name: "+getCountryName()+"\n"+
                "Zip Code : "+ getZipCode()+"\n" +
                "City: "+getCity()+"\n" +
                "Recipient: "+getRecipient()+"\n" +
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

        Company company = (Company) obj;

        return this.getCode().equals(company.getCode()) &&
                this.getSocialReason().equals(company.getSocialReason()) &&
                this.getClientType().equals(company.getClientType()) &&
                this.getLabel().equals(company.getLabel()) &&
                this.getCountryCode().equals(company.getCountryCode()) &&
                this.getCountryName().equals(company.getCountryName()) &&
                this.getZipCode().equals(company.getZipCode()) &&
                this.getCity().equals(company.getCity()) &&
                this.getRecipient().equals(company.getRecipient());
    }
}
