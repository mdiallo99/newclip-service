package com.example.authenticationapp.repository;

import com.example.authenticationapp.model.Address;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The repository for our Address object
 */
@Repository
public interface AddressRepository extends MongoRepository<Address, String> {

}
