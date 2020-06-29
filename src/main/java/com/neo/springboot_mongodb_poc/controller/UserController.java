package com.neo.springboot_mongodb_poc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neo.springboot_mongodb_poc.Commons.Validator;
import com.neo.springboot_mongodb_poc.Commons.beans.UserDetailsBean;
import com.neo.springboot_mongodb_poc.document.UserDetails;
import com.neo.springboot_mongodb_poc.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users/v1")
public class UserController extends Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllUsers() throws Exception {
        LOGGER.trace("Starting getAllUsers() from UserController");
        ResponseEntity<?> responseEntity = null;
        String jsonString = userService.getAllUsers();
        if(jsonString != null){
            responseEntity = ResponseEntity.ok(jsonString);
        } else
            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        LOGGER.trace("Exiting getAllUsers() from UserController with return:: responseEntity: "+responseEntity);
        return responseEntity;
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerUser(@RequestBody String dashboardRequest) throws Exception {
        LOGGER.trace("Starting registerUser() from UserController with arguments:: dashboardRequest: "+dashboardRequest);
        ResponseEntity<?> responseEntity = null;
        String jsonString = userService.createUser(dashboardRequest);
        if(jsonString != null){
            responseEntity = ResponseEntity.ok(jsonString);
        } else
            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        LOGGER.trace("Exiting registerUser() from UserController with return:: responseEntity: "+responseEntity);
        return responseEntity;
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteUser(@RequestBody String dashboardRequest) throws Exception {
        LOGGER.trace("Starting deleteUser() from UserController with arguments:: dashboardRequest: "+dashboardRequest);
        ResponseEntity<?> responseEntity = null;
        String jsonString = userService.deleteUserById(dashboardRequest);
        if(jsonString != null){
            responseEntity = ResponseEntity.ok(jsonString);
        } else
            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        LOGGER.trace("Exiting deleteUser() from UserController with return:: responseEntity: "+responseEntity);
        return responseEntity;
    }

}
