package com.neo.springboot_mongodb_poc.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class UserDetails {

    @Id
    private String uuid;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String emailId;
    @DBRef()
    private List<UserAddress> userAddress;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private Integer version;

}
