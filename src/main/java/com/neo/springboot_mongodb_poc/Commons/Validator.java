package com.neo.springboot_mongodb_poc.Commons;

import com.neo.springboot_mongodb_poc.Commons.beans.UserDetailsBean;
import com.neo.springboot_mongodb_poc.document.UserDetails;
import com.neo.springboot_mongodb_poc.document.UserMaster;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class Validator {

    public ResponseEntity<Object> responseBuilder(Object user) throws Exception {
        DashboardResponse dashboardResponse = new DashboardResponse();
        dashboardResponse.setStatusCode(CommonConstants.SUCCESS_STATUS);

        return new ResponseEntity<>(dashboardResponse, HttpStatus.OK);
    }

    public ResponseEntity<Object> responseBuilder(Exception exc) {
        DashboardResponse dashboardResponse = new DashboardResponse();
        dashboardResponse.setStatusCode(CommonConstants.FAIL_STATUS);

        return new ResponseEntity<>(dashboardResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public boolean validateUserMaster(UserMaster userMaster) {
        if(userMaster.getUsername() != null && userMaster.getPassword() != null)
            return Boolean.TRUE;
        else
            return Boolean.FALSE;
    }

    public boolean validateUserDetails(UserDetailsBean userDetails) {
        if(userDetails.getFirstName() != null && userDetails.getLastName() != null
                && userDetails.getDob() != null && userDetails.getGender() != null
                && userDetails.getEmailId() != null)
            return Boolean.TRUE;
        else
            return Boolean.FALSE;
    }
}
