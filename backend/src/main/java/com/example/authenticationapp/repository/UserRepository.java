package com.example.authenticationapp.repository;

import com.example.authenticationapp.model.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * The repository for our User object
 */
@Repository
public interface UserRepository extends MongoRepository<User, Long> {

    User getUserByEmail(String email);
}
