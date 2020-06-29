package com.neo.springboot_mongodb_poc.Commons.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetailsBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonPropertyDescription("User's First Name")
    @JsonProperty("first_name")
    private String firstName;

    @JsonPropertyDescription("User's Last Name")
    @JsonProperty("last_name")
    private String lastName;

    @JsonPropertyDescription("User's Date of Birth.")
    @JsonProperty("dob")
    private String dob;

    @JsonPropertyDescription("User's Email Id")
    @JsonProperty("email_id")
    private String emailId;

    @JsonPropertyDescription("User's Gender")
    @JsonProperty("gender")
    private String gender;

    @JsonPropertyDescription("User's address type")
    @JsonProperty("address_type")
    private String addressType;

    @JsonPropertyDescription("User's address line 1")
    @JsonProperty("address_line_1")
    private String addressLine1;

    @JsonPropertyDescription("User's address line 2")
    @JsonProperty("address_line_2")
    private String addressLine2;

    @JsonPropertyDescription("User's country")
    @JsonProperty("country")
    private String country;

    @JsonPropertyDescription("User's address pincode")
    @JsonProperty("pincode")
    private String pincode;

}
