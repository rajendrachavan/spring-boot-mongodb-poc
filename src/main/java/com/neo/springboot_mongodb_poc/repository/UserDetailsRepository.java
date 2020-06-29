package com.neo.springboot_mongodb_poc.repository;

import com.neo.springboot_mongodb_poc.document.UserDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserDetailsRepository extends MongoRepository<UserDetails, String> {

}
