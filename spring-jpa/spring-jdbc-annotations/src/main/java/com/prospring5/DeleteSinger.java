package com.prospring5;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class DeleteSinger extends SqlUpdate{
	
	private static final String SQL_DELETE_RECORDS = 
			"delete from singer where id = :id";
	
	public DeleteSinger(DataSource dataSource) {
		super(dataSource, SQL_DELETE_RECORDS);
		super.declareParameter(new SqlParameter("id", Types.INTEGER));
	}
	
}
