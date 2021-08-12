package com.example.authenticationapp.repository;

import com.example.authenticationapp.model.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


/**
 * The repository for our Company object
 */
@Repository
public interface CompanyRepository extends MongoRepository<Company, Long> {

    @Query("{'code': ?0}")
    Company findCompanyCode(String code);

    @Query("{'socialReason':?1}")
    Company findCompanyBySocialReason(String socialReason);
}
