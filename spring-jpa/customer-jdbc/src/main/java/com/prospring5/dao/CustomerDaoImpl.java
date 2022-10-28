package com.prospring5.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prospring5.entities.Customer;

public class CustomerDaoImpl implements CustomerDao{
	
	private static Logger logger = LoggerFactory.getLogger(CustomerDaoImpl.class);
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException ex) {
			logger.error("Problem loading in the server", ex);
		}
	}
	
	private Connection getConnection(Connection connection) throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/spring_demo?useSSL=false",
				"prashant", "admin123@");
	}
	
	private void closeConnection(Connection connection) {
		if(connection == null) {
			return;
		}
		try {
			connection.close();
		}catch(SQLException ex) {
			logger.error("Problem closing connection to the database", ex);
		}
	}
	
	public List<Customer> findAll() {
		List<Customer> result = new ArrayList<Customer>();
		Connection connection = null;
		try {
			connection = getConnection(connection);
			PreparedStatement statement = connection
					.prepareStatement("select * from customer");
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Customer customer = new Customer();
				customer.setPhoneNumber(resultSet.getLong("phone_no"));
				customer.setPlanId(resultSet.getInt("plan_id"));
				customer.setName(resultSet.getString("name"));
				customer.setAge(resultSet.getInt("age"));
				customer.setGender(resultSet.getString("gender").charAt(0));
				customer.setAddress(resultSet.getString("address"));
				result.add(customer);
			}
			statement.close();
		}catch(SQLException e) {
			logger.error("Problem when execuing SELECT!", e);
		}finally {
			closeConnection(connection);
		}
		return result;
	}

	public void insert(Customer customer) {
		Connection connection = null;
		try {
			connection = getConnection(connection);
			PreparedStatement statement = connection.prepareStatement(
					"insert into customer(phone_no, name, age, gender, address, plan_id) values (?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			statement.setLong(1, customer.getPhoneNumber());
			statement.setString(2, customer.getName());
			statement.setInt(3, customer.getAge());
			statement.setString(4, customer.getGender().toString());
			statement.setString(5, customer.getAddress());
			statement.setInt(6, customer.getPlanId());
			statement.execute();
		}catch(SQLException e) {
			logger.error("Problem when executing the INSERT!", e);
		}finally {
			closeConnection(connection);
		}
		
	}

	public void update(Customer customer) {
		Connection connection = null;
		try {
			connection = getConnection(connection);
			PreparedStatement statement = 
					connection.prepareStatement("update customer set name= ? where phone_no = ?");
			statement.setString(1, "Joe");
			statement.setLong(2, customer.getPhoneNumber());
			statement.execute();
		}catch(SQLException e) {
			logger.error("Problem when executing the UPDATE", e);
		}finally {
			closeConnection(connection);
		}
	}

	public void delete(Long phoneNumber) {
		Connection connection = null;
		try {
			connection = getConnection(connection);
			PreparedStatement statement = 
					connection.prepareStatement("delete from customer where phone_no = ?");
			statement.setLong(1, phoneNumber);
			statement.execute();
		}catch(SQLException e) {
			logger.error("Problem when executing the DELETE", e);
		}finally {
			closeConnection(connection);
		}
	}

}
