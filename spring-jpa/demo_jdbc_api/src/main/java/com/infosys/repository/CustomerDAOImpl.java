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
    private static Logger logger = Logger.getLogger(CustomerDAOImpl.class);
    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    @Override
    public void insert(Customer customer) {
        try(FileInputStream fis = new FileInputStream("spring-jpa\\demo_jdbc_api\\src\\main\\resources\\application.properties")){
            Properties p = new Properties();
            p.load(fis);
            String dname = (String)p.get("JDBC_DRIVER");
            String url = (String)p.get("JDBC_URL");
            String username = (String)p.get("USER");
            String password = (String)p.get("PASSWORD");
            Class.forName(dname);
            // Register the driver
            con = DriverManager.getConnection(url, username, password);
            // Create the connection
            String query = "insert into customer values (?, ?, ?, ?, ?, ?)";
            // Create prepared statement
            stmt = con.prepareStatement(query);
            stmt.setLong(1, customer.getPhoneNumber());
            stmt.setString(2, customer.getName());
            stmt.setInt(3, customer.getAge());
            stmt.setString(4, customer.getGender().toString());
            stmt.setString(5, customer.getAddress());
            stmt.setInt(6, customer.getPlanId());
            // Execute query
            stmt.executeUpdate();
            logger.info("Record Inserted");
        }catch(Exception e){
            logger.error(e.getMessage(), e);
        }finally{
            try {
				// Close the prepared statement
				stmt.close();
				// Close the connection
				con.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
        }
    }

    @Override
    public int remove(Long phoneNo) {
        int result = 1;
		try (FileInputStream fis = new FileInputStream("spring-jpa\\demo_jdbc_api\\src\\main\\resources\\application.properties");) {
			Properties p = new Properties();
			p.load(fis);
			String dname = (String) p.get("JDBC_DRIVER");
			String url = (String) p.get("JDBC_URL");
			String username = (String) p.get("USER");
			String password = (String) p.get("PASSWORD");
			Class.forName(dname);
			// Create connection
			con = DriverManager.getConnection(url, username, password);
			String query = "DELETE FROM Customer WHERE phone_no = ?";
			// Create prepared statement
			stmt = con.prepareStatement(query);
			stmt.setLong(1, phoneNo);
			// Execute query
			result = stmt.executeUpdate();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				// Close the prepared statement
				stmt.close();
				// Close the connection
				con.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		return result;
    }

    @Override
    public List<CustomerDTO> findAll() {
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        try(FileInputStream fis = new FileInputStream("spring-jpa\\demo_jdbc_api\\src\\main\\resources\\application.properties")){
            Properties p = new Properties();
			p.load(fis);
			String dname = (String) p.get("JDBC_DRIVER");
			String url = (String) p.get("JDBC_URL");
			String username = (String) p.get("USER");
			String password = (String) p.get("PASSWORD");
			Class.forName(dname);
			// Create connection
			con = DriverManager.getConnection(url, username, password);
            String query = "select * from customer";
            // Create the prepared statement
            stmt = con.prepareStatement(query);
            // Get the result set
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
                //logger.info(customer);
                customerDTOS.add(Customer.prepareCustomerDTO(customer));
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
        }finally{
            try {
				// Close the prepared statement
				stmt.close();
				// Close the connection
				con.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
        }
        return customerDTOS;
    }

    @Override
    public CustomerDTO findByPhoneNumber(Long phoneNumber) {
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        try(FileInputStream fis = new FileInputStream("spring-jpa\\demo_jdbc_api\\src\\main\\resources\\application.properties")){
            Properties p = new Properties();
			p.load(fis);
			String dname = (String) p.get("JDBC_DRIVER");
			String url = (String) p.get("JDBC_URL");
			String username = (String) p.get("USER");
			String password = (String) p.get("PASSWORD");
			Class.forName(dname);
			// Create connection
			con = DriverManager.getConnection(url, username, password);
            String query = "select * from customer where phone_no = ?";
            // Prepare the query statement
            stmt = con.prepareStatement(query);
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
            logger.error(ex.getMessage(), ex);
        }finally{
            try{
                // close the prepared statement
                stmt.close();
                // close the connection
                con.close();
            }catch(Exception ex){
                logger.error(ex.getMessage(), ex);
            }
        }
        return customerDTOS.get(0);
    }
    
}
