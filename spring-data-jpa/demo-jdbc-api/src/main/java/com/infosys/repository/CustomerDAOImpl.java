package com.infosys.repository;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.infosys.dto.CustomerDTO;
import com.infosys.entity.Customer;

public class CustomerDAOImpl implements CustomerDAO{
	public static Logger LOGGER = Logger.getLogger(CustomerDAOImpl.class);
	
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	
	
	@Override
	public void insert(Customer customer) {
		try(FileInputStream fis = new FileInputStream("src\\main\\resources\\application.properties")){
			Properties p = new Properties();
			p.load(fis);
			String dname = (String)p.get("JDBC_DRIVER");
			String url = (String)p.get("JDBC_URL");
			String username = (String)p.get("USER");
			String password = (String)p.get("PASSWORD");
			
			Class.forName(dname);
			// Register a driver
			conn = DriverManager.getConnection(url, username, password);
			// Create a prepared statement
			String query = "insert into customer values(?, ?, ?, ?, ?, ?)";
			// Create a prepared statement
			stmt = conn.prepareStatement(query);
			stmt.setLong(1, customer.getPhoneNumber());
			stmt.setString(2, customer.getName());
			stmt.setInt(3, customer.getAge());
			stmt.setString(4, customer.getGender().toString());
			stmt.setString(5, customer.getAddress());
			stmt.setInt(6, customer.getPlanId());
			// Execute query
			stmt.executeUpdate();
			LOGGER.info("Record Inserted");
		}catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			try {
				// Close the prepared statement
				stmt.close();
				// Close the connection
				conn.close();
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
		
	}

	@Override
	public int remove(Long phoneNo) {
		int result = 1;
		try(FileInputStream fis = new FileInputStream("src\\main\\resources\\application.properties")){
			Properties p = new Properties();
			p.load(fis);
			String dname = (String)p.get("JDBC_DRIVER");
			String url = (String)p.get("JDBC_URL");
			String username = (String)p.get("USER");
			String password = (String)p.get("PASSWORD");
			Class.forName(dname);
			// Create connection
			conn = DriverManager.getConnection(url, username, password);
			String query = "DELETE FROM customer where phone_no = ?";
			// Create a prepared statement
			stmt = conn.prepareStatement(query);
			stmt.setLong(1, phoneNo);
			// Execute Query
			result = stmt.executeUpdate();
		}catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			try {
				// Close the prepared statement
				stmt.close();
				// Close the connection
				conn.close();
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
		return result;
	}

	@Override
	public List<CustomerDTO> findAll() {
		List<CustomerDTO> customerDTOs = new ArrayList<CustomerDTO>();
		try(FileInputStream fis = new FileInputStream("src\\main\\resources\\application.properties")){
			Properties p = new Properties();
			p.load(fis);
			String dname = (String)p.get("JDBC_DRIVER");
			String url = (String)p.get("JDBC_URL");
			String username = (String)p.get("USER");
			String password = (String)p.get("PASSWORD");
			Class.forName(dname);
			// Create connection
			conn = DriverManager.getConnection(url, username, password);
			String query = "select * from customer";
			// Create the prepared statement
			stmt = conn.prepareStatement(query);
			// Get the result set
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Customer customer = new Customer(
						 	rs.getLong(1),
		                    rs.getString(2),
		                    rs.getInt(3),
		                    rs.getString(4).charAt(0),
		                    rs.getString(5),
		                    rs.getInt(6)
						);
				customerDTOs.add(Customer.prepareCustomerDTO(customer));
			}
		}catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			try {
				// Close the prepared statement
				stmt.close();
				// Close the connection
				conn.close();
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
		return customerDTOs;
	}

	@Override
	public CustomerDTO findByPhoneNumber(Long phoneNumber) {
		List<CustomerDTO> customerDTOS = new ArrayList<>();
        try(FileInputStream fis = new FileInputStream("src\\main\\resources\\application.properties")){
            Properties p = new Properties();
			p.load(fis);
			String dname = (String) p.get("JDBC_DRIVER");
			String url = (String) p.get("JDBC_URL");
			String username = (String) p.get("USER");
			String password = (String) p.get("PASSWORD");
			Class.forName(dname);
			// Create connection
			conn = DriverManager.getConnection(url, username, password);
            String query = "select * from customer where phone_no = ?";
            // Prepare the query statement
            stmt = conn.prepareStatement(query);
            stmt.setLong(1, phoneNumber);
            rs = stmt.executeQuery();
            while(rs.next()){
                Customer customer = new Customer(
                    rs.getLong(1),
                    rs.getString(2),
                    rs.getInt(3),
                    rs.getString(4).charAt(0),
                    rs.getString(5),
                    rs.getInt(6)
                );
                customerDTOS.add(Customer.prepareCustomerDTO(customer));
            }
        }catch(Exception ex){   
            LOGGER.error(ex.getMessage(), ex);
        }finally{
            try{
                // close the prepared statement
                stmt.close();
                // close the connection
                conn.close();
            }catch(Exception ex){
            	LOGGER.error(ex.getMessage(), ex);
            }
        }
        return customerDTOS.get(0);
	}

}
