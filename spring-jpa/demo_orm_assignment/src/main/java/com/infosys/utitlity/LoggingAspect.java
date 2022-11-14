package com.infosys.utitlity;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;

public class LoggingAspect {
    private Logger logger = LogManager.getLogger(LoggingAspect.class);

    @AfterThrowing(pointcut = "execution(* com.infosys.repository.*Impl.*(..))", throwing = "exception")
    public void logDaoException(Exception exception){
        logger.error(exception.getMessage(), exception);
    }

    @AfterThrowing(pointcut = "execution(* com.infosys.service.*Impl.*(..))", throwing = "exception")
    public void logServiceException(Exception exception){
        logger.error(exception.getMessage(), exception);
    }

}
