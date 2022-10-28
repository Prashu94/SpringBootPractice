package com.prospring5;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import com.prospring5.entities.Singer;

public class SelectSingerLastNameById extends MappingSqlQuery<Singer>{
	private static final String SQL_FIND_LAST_NAME_BY_ID = 
			"select last_name from Singer where id = :id";
	
	public SelectSingerLastNameById(DataSource dataSource) {
		super(dataSource, SQL_FIND_LAST_NAME_BY_ID);
		super.declareParameter(new SqlParameter("id", Types.VARCHAR));
	}
	
	@Override
	protected Singer mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Singer singer = new Singer();
		singer.setLastName(rs.getString("last_name"));
		return singer;
	}
	
	
}
