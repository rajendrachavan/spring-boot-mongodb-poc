package com.neo.springboot_mongodb_poc.repository;

import com.neo.springboot_mongodb_poc.document.UserMaster;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMasterRepository extends MongoRepository<UserMaster, String> {
    UserMaster findUserByUsername(String username);
}
