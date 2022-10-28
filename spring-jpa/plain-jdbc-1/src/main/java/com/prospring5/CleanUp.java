package com.prospring5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import ch.qos.logback.classic.net.LoggingEventPreSerializationTransformer;

public class CleanUp {
	private static Logger logger = LoggerFactory.getLogger(CleanUp.class);
	private JdbcTemplate jdbcTemplate;
	
	public CleanUp(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void destroy() {
		logger.info("... Deleting database files. ");
	}
}
