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

import com.prospring5.entities.Album;
import com.prospring5.entities.Singer;

public class PlainSingerDao implements SingerDao{
	
	private static Logger logger = LoggerFactory.getLogger(PlainSingerDao.class);

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			logger.error("Problem loading DB Driver", e);
		}
	}
	
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/spring_demo?useSSL=false",
				"prashant", "admin123@");
	}
	
	private void closeConnection(Connection connection) {
		if(connection==null) {
			return;
		}
		try {
			connection.close();
		}catch (SQLException e) {
			logger.error("Problem closing connection to the database", e);
		}
	}
	//@Override
	public List<Singer> findAll() {
		List<Singer> result = new ArrayList<>();
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement("select * from SINGER");
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Singer singer = new Singer();
				singer.setId(resultSet.getLong("id"));
				singer.setFirstName(resultSet.getString("first_name"));
				singer.setLastName(resultSet.getString("last_name"));
				singer.setBirthDate(resultSet.getDate("birth_date"));
				result.add(singer);
			}
			statement.close();
		}catch(SQLException ex) {
			logger.error("Problem when execuing SELECT!", ex);
		}finally {
			closeConnection(connection);
		}
		
		return result;
	}

	//@Override
	public List<Singer> findByFirstName(String firstName) {
		List<Singer> result = new ArrayList<>();
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = 
					connection.prepareStatement("SELECT * FROM SINGER WHERE first_name = ?");
			statement.setString(1, firstName);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Singer singer = new Singer();
				singer.setId(resultSet.getLong("id"));
				singer.setFirstName(resultSet.getString("first_name"));
				singer.setLastName(resultSet.getString("last_name"));
				singer.setBirthDate(resultSet.getDate("birth_date"));
				result.add(singer);
			}
			statement.close();
		}catch(SQLException e) {
			logger.error("Problem when executing SELECT!", e);
		}finally {
			closeConnection(connection);
		}
		return result;
	}

	@Override
	public String findNameById(Long id) {
		String name = new String();
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = 
					connection.prepareStatement("select * from SINGER where id = ?");
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Singer singer = new Singer();
				singer.setFirstName(resultSet.getString("first_name"));
				singer.setLastName(resultSet.getString("last_name"));
				name = singer.getFirstName() +" "+singer.getLastName();
			}
			statement.close();
		}catch(SQLException e) {
			logger.error("Problem when executing SELECT!", e);
		}finally {
			closeConnection(connection);
		}
		return name;
	}
	
	public Singer findNameByIdV1(Long id) {
		Singer singer = new Singer();
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = 
					connection.prepareStatement("select * from SINGER where id = ?");
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				singer.setId(resultSet.getLong("id"));
				singer.setFirstName(resultSet.getString("first_name"));
				singer.setLastName(resultSet.getString("last_name"));
				singer.setBirthDate(resultSet.getDate("birth_date"));
			}
			statement.close();
		}catch(SQLException e) {
			logger.error("Problem when executing SELECT!", e);
		}finally {
			closeConnection(connection);
		}
		return singer;
	}

	@Override
	public String findLastNameById(Long id) {
		String name = new String();
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = 
					connection.prepareStatement("select * from SINGER where id = ?");
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Singer singer = new Singer();
				singer.setLastName(resultSet.getString("last_name"));
				name = singer.getLastName();
			}
			statement.close();
		}catch(SQLException e) {
			logger.error("Problem when executing SELECT!", e);
		}finally {
			closeConnection(connection);
		}
		return name;
	}

	@Override
	public void insert(Singer singer) {
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"insert into SINGER(first_name, last_name, birth_date) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, singer.getFirstName());
			statement.setString(2, singer.getLastName());
			statement.setDate(3, singer.getBirthDate());
			statement.execute();
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if(generatedKeys.next()) {
				singer.setId(generatedKeys.getLong(1));
			}
		}catch(SQLException ex) {
			logger.info("Problem executing INSERT!",ex);
		}finally {
			closeConnection(connection);
		}
	}

	@Override
	public void update(Singer singer) {
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = 
					connection.prepareStatement("update singer set first_name = ? where id = ?");
			statement.setString(1, "Oliver");
			statement.setLong(2, singer.getId());
			statement.execute();
		}catch(SQLException ex) {
			logger.error("Problem when excuting UPDATE!", ex);
		}finally {
			closeConnection(connection);
		}
	}

	@Override
	public void delete(Long singerId) {
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement("delete from SINGER where id = ?");
			statement.setLong(1, singerId);
			statement.execute();
		}catch(SQLException ex) {
			logger.error("Problem executing DELETE", ex);
		}finally {
			closeConnection(connection);
		}
	}

	@Override
	public List<Singer> findAllWithAlbums() {
		List<Singer> result = new ArrayList();
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection
					.prepareStatement("SELECT * FROM SINGER S INNER JOIN ALBUM A ON S.ID = A.SINGER_ID");
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Singer singer = new Singer();
				singer.setId(resultSet.getLong("id"));
				singer.setFirstName(resultSet.getString("first_name"));
				singer.setLastName(resultSet.getString("last_name"));
				singer.setBirthDate(resultSet.getDate("birth_date"));
				Album album = new Album();
				album.setId(resultSet.getLong("id"));
				album.setSingerId(resultSet.getLong("singer_id"));
				album.setTitle(resultSet.getString("title"));
				album.setReleaseDate(resultSet.getDate("release_date"));
				singer.addAlbum(album);
				result.add(singer);
			}
			statement.close();
		}catch(SQLException ex) {
			logger.error("Problem when execuing SELECT!", ex);
		}finally {
			closeConnection(connection);
		}
		return result;
	}

	@Override
	public void insertWithAlbums(Singer singer) {
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"insert into SINGER(first_name, last_name, birth_date) values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, singer.getFirstName());
			statement.setString(2, singer.getLastName());
			statement.setDate(3, singer.getBirthDate());
			statement.execute();
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if(generatedKeys.next()) {
				singer.setId(generatedKeys.getLong(1));
			}
			List<Album> albums = singer.getAlbums();
			for(Album album: albums) {
				album.setSingerId(singer.getId());
				statement = connection.prepareStatement(
						"insert into ALBUM(singer_id, title, release_date) values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
				statement.setLong(1, album.getSingerId());
				statement.setString(2, album.getTitle());
				statement.setDate(3, album.getReleaseDate());
				statement.execute();
				ResultSet generatedKeysAlbum = statement.getGeneratedKeys();
				if(generatedKeysAlbum.next()) {
					album.setId(generatedKeysAlbum.getLong(1));
				}
			}
		}catch(SQLException e) {
			logger.error("Problem executing INSERT!", e);
		}finally {
			closeConnection(connection);
		}
		
	}
	
}
