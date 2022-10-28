package com.prospring5.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.prospring5.DeleteSinger;
import com.prospring5.InsertSinger;
import com.prospring5.InsertSingerWithAlbum;
import com.prospring5.SelectAllSingers;
import com.prospring5.SelectSingerByFirstName;
import com.prospring5.SelectSingerById;
import com.prospring5.SelectSingerLastNameById;
import com.prospring5.StoredFunctionFirstNameById;
import com.prospring5.UpdateSinger;
import com.prospring5.entities.Album;
import com.prospring5.entities.Singer;

@Repository("singerDao")
public class JdbcSingerDao implements SingerDao {
	
	private static Logger logger = LoggerFactory.getLogger(JdbcSingerDao.class);
	
	private DataSource dataSource;
	private SelectAllSingers selectAllSingers;
	private SelectSingerByFirstName selectSingerByFirstName;
	private UpdateSinger updateSinger;
	private InsertSinger insertSinger;
	private InsertSingerWithAlbum insertSingerWithAlbum;
	private DeleteSinger deleteSinger;
	private StoredFunctionFirstNameById storedFunctionById;
	private SelectSingerById selectSingerById;
	private SelectSingerLastNameById selectSingerLastNameById;
	
	
	@Override
	public List<Singer> findAll() {
		// TODO Auto-generated method stub
		return selectAllSingers.execute();
	}

	@Override
	public List<Singer> findByFirstName(String firstName) {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("first_name", firstName);
		return selectSingerByFirstName.executeByNamedParam(paramMap);
	}

	@Override
	public String findNameById(Long id) {
		// TODO Auto-generated method stub
		List<String> result = storedFunctionById.execute(id);
		logger.info(result.get(0));
		return result.get(0);
	}

	@Override
	public String findLastNameById(Long id) {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", id);
		return selectSingerLastNameById.executeByNamedParam(paramMap).get(0).toString();
	}

	@Override
	public void insert(Singer singer) {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("first_name", singer.getFirstName());
		paramMap.put("last_name", singer.getLastName());
		paramMap.put("birth_date", singer.getBirthDate());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		insertSinger.updateByNamedParam(paramMap, keyHolder);
		singer.setId(keyHolder.getKey().longValue());
		logger.info("New Singer inserted with id: "+ singer.getId() );
	}

	@Override
	public void update(Singer singer) {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("first_name", singer.getFirstName());
		paramMap.put("last_name", singer.getLastName());
		paramMap.put("birth_date", singer.getBirthDate());
		paramMap.put("id", singer.getId());
		updateSinger.updateByNamedParam(paramMap);
		logger.info("Existing singer updated with id: "+ singer.getId());
	}

	@Override
	public void delete(Long singerId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", singerId);
		deleteSinger.updateByNamedParam(paramMap);
		logger.info("Deleting singer with id: "+ singerId);
	}

	@Override
	public List<Singer> findAllWithAlbums() {
		JdbcTemplate jdbcTemplate  = new JdbcTemplate(getDataSource());
		String sql = "select s.id, s.first_name, s.last_name, s.birth_date"
				+ ", a.id as album_id, a.title, a.release_date from singer s "
				+ "LEFT JOIN album a ON s.id = a.singer_id";
		return jdbcTemplate.query(sql, rs -> {
			Map<Long, Singer> map = new HashMap<>();
			Singer singer;
			while(rs.next()) {
				Long id = rs.getLong("id");
				singer = map.get(id);
				if(singer == null) {
					singer = new Singer();
					singer.setId(id);
					singer.setFirstName(rs.getString("first_name"));
					singer.setLastName(rs.getString("last_name"));
					singer.setBirthDate(rs.getDate("birth_date"));
					singer.setAlbums(new ArrayList<>());
					map.put(id, singer);
				}
				Long albumId = rs.getLong("album_id");
				if(albumId > 0) {
					Album album = new Album();
					album.setId(albumId);
					album.setSingerId(id);
					album.setTitle(rs.getString("title"));
					album.setReleaseDate(rs.getDate("release_date"));
					singer.getAlbums().add(album);
				}
			}
			return new ArrayList<>(map.values());
		});
	}

	@Override
	public void insertWithAlbums(Singer singer) {
		insertSingerWithAlbum = new InsertSingerWithAlbum(dataSource);
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("first_name", singer.getFirstName());
		paramMap.put("last_name", singer.getLastName());
		paramMap.put("birth_date", singer.getBirthDate());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		insertSinger.updateByNamedParam(paramMap, keyHolder);
		singer.setId(keyHolder.getKey().longValue());
		logger.info("New singer inserted with id: " + singer.getId());
		List<Album> albums =
				singer.getAlbums();
		if (albums != null) {
			for (Album album : albums) {
				paramMap = new HashMap<>();
				paramMap.put("singer_id", singer.getId());
				paramMap.put("title", album.getTitle());
				paramMap.put("release_date", album.getReleaseDate());
				insertSingerWithAlbum.updateByNamedParam(paramMap);
			}
		}
		insertSingerWithAlbum.flush();
	}

	@Override
	public Singer findNameByIdV1(Long id) {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", id);
		return selectSingerById.executeByNamedParam(paramMap).get(0);
	}
	
	@Resource(name="dataSource")
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.selectAllSingers = new SelectAllSingers(dataSource);
		this.selectSingerByFirstName = new SelectSingerByFirstName(dataSource);
		this.updateSinger = new UpdateSinger(dataSource);
		this.insertSinger = new  InsertSinger(dataSource);
		this.selectSingerById = new SelectSingerById(dataSource);
		this.selectSingerLastNameById = new SelectSingerLastNameById(dataSource);
		this.storedFunctionById = new StoredFunctionFirstNameById(dataSource);
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}
}
