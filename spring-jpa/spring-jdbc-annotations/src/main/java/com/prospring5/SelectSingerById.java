package com.prospring5;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import com.prospring5.entities.Singer;

public class SelectSingerById extends MappingSqlQuery<Singer>{
	private static final String SQL_FIND_BY_FIRST_ID = 
			"select id, first_name, last_name, birth_date from Singer where id = :id";
	
	public SelectSingerById(DataSource dataSource) {
		super(dataSource, SQL_FIND_BY_FIRST_ID);
		super.declareParameter(new SqlParameter("id", Types.VARCHAR));
	}
	
	@Override
	protected Singer mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Singer singer = new Singer();
		singer.setId(rs.getLong("id"));
		singer.setFirstName(rs.getString("first_name"));
		singer.setLastName(rs.getString("last_name"));
		singer.setBirthDate(rs.getDate("birth_date"));
		return singer;
	}

}
