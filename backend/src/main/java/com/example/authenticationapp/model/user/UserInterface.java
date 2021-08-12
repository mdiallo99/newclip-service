package com.example.authenticationapp.model.user;

import com.example.authenticationapp.model.Address;
import com.example.authenticationapp.model.Company;

import java.util.Set;

public interface UserInterface {
    String getFirstName();
    String getLastName();
    String  getEmail();
    String getPassword();
    Company getCompany();
    Set<String> getRoles();
//    Address getAddress();

    void setFirstName(String newFirstName);
    void setLastName(String newLastName);
    void setEmail(String newEmail);
    void setPassword(String newPassword);
    void setCompany(Company newCompany);
    void setRoles(Set<String> newRole);
//    void setAddress(Address address);
}
