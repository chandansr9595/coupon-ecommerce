package com.chandan.rc.repository;

import com.chandan.rc.documents.Deals;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DealsRepository extends MongoRepository<Deals, String> {
    List<Deals> findAllByProductId(Iterable<String> productIds);
}
