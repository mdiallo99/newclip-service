package com.example.authenticationapp.model.user;

import com.example.authenticationapp.model.Company;
import com.example.authenticationapp.utils.Constants;

import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Admin extends User{

    public Admin(String firstName, String lastName, String email, String password, Company company) {
        super(firstName, lastName, email, password, company);
        this.affectAdminRoles();
    }

    public Admin(){
        super();
        this.affectAdminRoles();
    }
    public Admin(String email){
        super(email);
        this.affectAdminRoles();
    }

    public Admin(String email, String password) {
        super(email, password);
        this.affectAdminRoles();
    }

    private void affectAdminRoles(){
        this.setRoles(Stream.of(Constants.ADMIN, Constants.USER).collect(Collectors.toSet()));
    }
}
