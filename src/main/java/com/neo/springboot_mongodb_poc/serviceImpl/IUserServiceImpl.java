package com.neo.springboot_mongodb_poc.serviceImpl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neo.springboot_mongodb_poc.Commons.CommonConstants;
import com.neo.springboot_mongodb_poc.Commons.CommonUtils;
import com.neo.springboot_mongodb_poc.Commons.beans.UserDetailsBean;
import com.neo.springboot_mongodb_poc.document.Gender;
import com.neo.springboot_mongodb_poc.document.UserAddress;
import com.neo.springboot_mongodb_poc.document.UserDetails;
import com.neo.springboot_mongodb_poc.document.UserMaster;
import com.neo.springboot_mongodb_poc.repository.UserDetailsRepository;
import com.neo.springboot_mongodb_poc.repository.UserMasterRepository;
import com.neo.springboot_mongodb_poc.service.IUserService;
import com.neo.springboot_mongodb_poc.Commons.DashboardResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class IUserServiceImpl implements IUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IUserServiceImpl.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String USER_DETAILS = "user_details";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String DOB = "dob";
    private static final String GENDER = "gender";
    private static final String EMAIL_ID = "email_id";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String USER_ID = "user_id";

    private final UserDetailsRepository userDetailsRepository;
    private final UserMasterRepository userMasterRepository;

    public IUserServiceImpl(UserDetailsRepository userDetailsRepository, UserMasterRepository userMasterRepository) {
        this.userDetailsRepository = userDetailsRepository;
        this.userMasterRepository = userMasterRepository;
    }

    @Override
    public String getAllUsers() throws Exception {
        LOGGER.trace("Starting getAllUsers() from UserServiceImpl");
        String returnValue = null;
        String errorMsg = null;
        DashboardResponse dashboardResponse = new DashboardResponse();
        try {
            List<UserDetails> userDetailsList = this.userDetailsRepository.findAll();
            LOGGER.trace("USER_DETAILS_LIST:: "+userDetailsList);
            if(!userDetailsList.isEmpty()) {
                List<UserDetailsBean> userDetailsBeanList = userDetailsList.stream().map(userDetails -> {
                    UserDetailsBean uDetailsBean = new UserDetailsBean();
                    uDetailsBean.setFirstName(userDetails.getFirstName());
                    uDetailsBean.setLastName(userDetails.getLastName());
                    uDetailsBean.setDob(userDetails.getDateOfBirth().format(CommonConstants.DTF_dd_MM_yyyy));
                    uDetailsBean.setGender(userDetails.getGender().name());
                    uDetailsBean.setEmailId(userDetails.getEmailId());
                    return uDetailsBean;
                }).collect(Collectors.toList());

                dashboardResponse.setStatusCode(CommonConstants.SUCCESS_STATUS);
                dashboardResponse.setResponseData(USER_DETAILS, userDetailsBeanList);
            } else
                errorMsg = "No Records found for requested input.";

        } catch (Exception e) {
            errorMsg = "Following exception occur while fetching User Details.";
            LOGGER.error(errorMsg + "\n\r : "+ CommonUtils.INSTANCE.getStringStackTrace(e));
        }
        if(errorMsg != null){
            dashboardResponse.setStatusCode(CommonConstants.FAIL_STATUS);
            dashboardResponse.setErrorMsg(errorMsg);
        }
        returnValue = MAPPER.writeValueAsString(dashboardResponse);
        LOGGER.trace("Exiting getAllUsers() from UserServiceImpl with return:: returnValue: "+returnValue);
        return returnValue;
    }

    @Override
    public String createUser(String dashboardRequest) throws Exception {
        LOGGER.trace("Starting createUser() from UserServiceImpl with arguments:: dashboardRequest: "+dashboardRequest);
        String returnValue = null;
        String errorMsg = null;
        DashboardResponse dashboardResponse = new DashboardResponse();
        try {
            JsonNode requestJsonNode = MAPPER.readTree(dashboardRequest);
            String firstName = requestJsonNode.get(FIRST_NAME).asText();
            String lastName = requestJsonNode.get(LAST_NAME).asText();
            String dateOfBirth = requestJsonNode.get(DOB).asText();
            String gender = requestJsonNode.get(GENDER).asText();
            String emailId = requestJsonNode.get(EMAIL_ID).asText();
            String username = requestJsonNode.get(USERNAME).asText();
            String password = requestJsonNode.get(PASSWORD).asText();

            UserMaster userMaster = this.userMasterRepository.findUserByUsername(username);
            if(userMaster == null) {
                userMaster = new UserMaster();
                userMaster.setUuid(UUID.randomUUID().toString());
                userMaster.setUsername(username);
                userMaster.setPassword(password);
                userMaster.setIsActive(Boolean.TRUE);
                userMaster.setCreatedAt(LocalDate.now());
                userMaster = this.userMasterRepository.save(userMaster);

                UserDetails userDetails = new UserDetails();
                userDetails.setUuid(UUID.randomUUID().toString());
                userDetails.setFirstName(firstName);
                userDetails.setLastName(lastName);
                userDetails.setDateOfBirth(LocalDate.parse(dateOfBirth, CommonConstants.DTF_dd_MM_yyyy));
                userDetails.setGender(Gender.valueOf(gender));
                userDetails.setEmailId(emailId);
                userDetails.setCreatedAt(LocalDate.now());
                userDetails = this.userDetailsRepository.save(userDetails);

                dashboardResponse.setStatusCode(CommonConstants.SUCCESS_STATUS);
                dashboardResponse.setResponseData(USER_DETAILS, "User Registered Successfully.");
            } else
                errorMsg = "user already exists.";

        } catch (Exception e) {
            errorMsg = "Following exception occur while saving User Details.";
            LOGGER.error(errorMsg + "\n\r : "+ CommonUtils.INSTANCE.getStringStackTrace(e));
        }
        if(errorMsg != null){
            dashboardResponse.setStatusCode(CommonConstants.FAIL_STATUS);
            dashboardResponse.setErrorMsg(errorMsg);
        }
        returnValue = MAPPER.writeValueAsString(dashboardResponse);
        LOGGER.trace("Exiting createUser() from UserServiceImpl with return:: returnValue: "+returnValue);
        return returnValue;
    }

    @Override
    public String deleteUserById(String dashboardRequest) throws Exception {
        LOGGER.trace("Starting deleteUserById() from UserServiceImpl with arguments:: dashboardRequest: "+dashboardRequest);
        String returnValue = null;
        String errorMsg = null;
        DashboardResponse dashboardResponse = new DashboardResponse();
        try {
            JsonNode requestJsonNode = MAPPER.readTree(dashboardRequest);
            String userId = requestJsonNode.get(USER_ID).asText();

            UserDetails userDetails = this.userDetailsRepository.findById(userId).orElse(null);
            if(userDetails != null) {
                this.userDetailsRepository.deleteById(userId);

                dashboardResponse.setStatusCode(CommonConstants.SUCCESS_STATUS);
                dashboardResponse.setResponseData(USER_DETAILS, "User deleted successfully from database.");
            } else
                errorMsg = "No Records found for requested user";

        } catch (Exception e) {
            errorMsg = "Following exception occur while fetching User Details.";
            LOGGER.error(errorMsg + "\n\r : "+ CommonUtils.INSTANCE.getStringStackTrace(e));
        }
        if(errorMsg != null){
            dashboardResponse.setStatusCode(CommonConstants.FAIL_STATUS);
            dashboardResponse.setErrorMsg(errorMsg);
        }
        returnValue = MAPPER.writeValueAsString(dashboardResponse);
        LOGGER.trace("Exiting deleteUserById() from UserServiceImpl with return:: returnValue: "+returnValue);
        return returnValue;
    }
}
