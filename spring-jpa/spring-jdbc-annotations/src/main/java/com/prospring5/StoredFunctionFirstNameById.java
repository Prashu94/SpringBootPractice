package com.prospring5;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlFunction;

public class StoredFunctionFirstNameById extends SqlFunction<String>{
	private static final String SQL = "select getFirstNameById(?)";
	
	public StoredFunctionFirstNameById(DataSource dataSource) {
		super(dataSource, SQL);
		declareParameter(new SqlParameter(Types.INTEGER));
		compile();
	}
}
