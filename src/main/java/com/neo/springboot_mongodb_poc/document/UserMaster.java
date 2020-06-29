package com.neo.springboot_mongodb_poc.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class UserMaster {

    @Id
    private String uuid;
    private String username;
    private String password;
    private Boolean isActive;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private Integer version;
}
