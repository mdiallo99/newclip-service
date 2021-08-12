package com.example.authenticationapp.repository;

import com.example.authenticationapp.model.sylobe.Voucher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherRepository extends MongoRepository<Voucher, String> {
}
