package com.example.authenticationapp.model.user;

import com.example.authenticationapp.model.Company;
import com.example.authenticationapp.utils.Constants;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Client extends User{


    public Client(String firstName, String lastName, String email, String password, Company company) {
        super(firstName, lastName, email, password, company);
        this.affectUserRoles();
    }

    public Client(String email, String password) {
        super(email, password);
        this.affectUserRoles();

    }

    public Client(String email){
        super(email);
        this.affectUserRoles();
    }

    public Client(){
        super();
    }

    /**
     * We set roles for all instances of this class to USER
     */
    private void affectUserRoles(){
        this.setRoles(Stream.of(Constants.USER).collect(Collectors.toSet()));
    }
}
