package com.neo.springboot_mongodb_poc.repository;

import com.neo.springboot_mongodb_poc.document.UserAddress;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserAddressRepository extends MongoRepository<UserAddress, String> {
}
