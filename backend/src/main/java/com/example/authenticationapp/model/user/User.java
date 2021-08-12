package com.example.authenticationapp.model.user;


import com.example.authenticationapp.model.Company;
import lombok.Data;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

/**
 * User collection is called "user"
 */
@Data
@Document(collection = "user")
public class User implements UserInterface{

    /**
     * User attributes
     */
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Company company;
    private Set<String> roles;

    @PersistenceConstructor
    public User(){
    }

    @PersistenceConstructor
    public User(String firstName, String lastName, String email, String password, Company company) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.company = company;
        this.roles = new HashSet<>();
    }

    /**
     * A constructor for creating a user by on email
     * @param email user email
     */
    @PersistenceConstructor
    public User(String email){
        this(null, null, email, null, null);
    }

    /**
     * A constructor for creating a user by on email and password
     * @param email user email
     * @param password user password
     */
    @PersistenceConstructor
    public User(String email, String password) {
        this(null, null, email, password, null);
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Company getCompany() {
        return company;
    }

    @Override
    public Set<String> getRoles() {
        return roles;
    }



    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }



    @Override
    public String toString(){
        return "User {"+"\n" +
                "First Name: "+firstName+"\n" +
                "Last Name: "+lastName+"\n" +
                "Email: "+email+"\n" +
                "Company: "+company.toString()+"\n" +
                "Roles: "+ roles.toString() +"\n" +
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

        User user = (User) obj;

        return this.getFirstName().equals(user.getFirstName()) &&
                this.getLastName().equals(user.getLastName()) &&
                this.getEmail().equals(user.getEmail()) &&
                this.getCompany() == user.getCompany() &&
                this.getRoles() == user.getRoles();
    }
}
