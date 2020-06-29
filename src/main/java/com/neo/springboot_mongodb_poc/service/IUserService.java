package com.neo.springboot_mongodb_poc.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.neo.springboot_mongodb_poc.Commons.beans.UserDetailsBean;

public interface IUserService {

    public String getAllUsers() throws Exception;

    public String createUser(String dashboardRequest) throws Exception;

    public String deleteUserById(String dashboardRequest) throws Exception;
}
