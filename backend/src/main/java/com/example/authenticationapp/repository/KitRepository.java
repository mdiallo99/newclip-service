package com.example.authenticationapp.repository;

import com.example.authenticationapp.model.sylobe.Kit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface KitRepository extends MongoRepository<Kit, String> {

    @Query("{'label': ?0}")
    Kit findByLabel(String label);
}
