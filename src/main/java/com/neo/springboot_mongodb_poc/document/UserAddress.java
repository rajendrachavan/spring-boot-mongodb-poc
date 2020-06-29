package com.neo.springboot_mongodb_poc.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class UserAddress {

    @Id
    private String uuid;
    private String type;
    private String addressLine1;
    private String addressLine2;
    private String country;
    private Integer pincode;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private Integer version;

}
