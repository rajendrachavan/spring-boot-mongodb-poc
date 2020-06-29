package com.neo.springboot_mongodb_poc.config;

import com.neo.springboot_mongodb_poc.document.Gender;
import com.neo.springboot_mongodb_poc.document.UserAddress;
import com.neo.springboot_mongodb_poc.document.UserDetails;
import com.neo.springboot_mongodb_poc.document.UserMaster;
import com.neo.springboot_mongodb_poc.repository.UserAddressRepository;
import com.neo.springboot_mongodb_poc.repository.UserDetailsRepository;
import com.neo.springboot_mongodb_poc.repository.UserMasterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.time.LocalDate;
import java.util.UUID;

@EnableMongoRepositories(basePackages = "com.neo.springboot_mongodb_poc.repository")
@Configuration
public class MongoDBConfig {

    /*@Bean
    CommandLineRunner commandLineRunner(UserMasterRepository userMasterRepository, UserAddressRepository userAddressRepository, UserDetailsRepository userDetailsRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                userMasterRepository.save(new UserMaster(UUID.randomUUID().toString(), "alexroman39", "3alexR@9", Boolean.TRUE, LocalDate.now(), null, 1));
                userMasterRepository.save(new UserMaster(UUID.randomUUID().toString(), "domnicverface25", "2domnicV@5", Boolean.TRUE, LocalDate.now(), null, 1));
                userAddressRepository.save(new UserAddress(UUID.randomUUID().toString(), "Home", "302 Mark villa", "North Caroline", "USA", 900091, LocalDate.now(), null, 1));
                userAddressRepository.save(new UserAddress(UUID.randomUUID().toString(), "Home", "51 street green park", "North Caroline", "USA", 900092, LocalDate.now(), null, 1));

            }
        };
    }*/
}
