package com.infosys.validator;

import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.infosys.dto.EmployeeDTO;
import com.infosys.exception.EmployeeException;

public class Validator {
    public static final Logger logger = LogManager.getLogger(Validator.class);

    public static void validate(EmployeeDTO employeeDTO) throws EmployeeException{
        if(Boolean.TRUE.equals(validateEmailId(employeeDTO.getEmailId()))){
            logger.info("Validator.INVALID_EMAILID");        
        }
    }

    public static Boolean validateEmailId(String emailId){
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return Pattern.compile(regexPattern)
        .matcher(emailId)
        .matches();
    }
}
