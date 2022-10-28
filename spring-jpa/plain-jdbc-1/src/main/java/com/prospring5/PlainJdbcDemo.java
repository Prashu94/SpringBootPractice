package com.prospring5;

import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prospring5.dao.PlainSingerDao;
import com.prospring5.dao.SingerDao;
import com.prospring5.entities.Album;
import com.prospring5.entities.Singer;

public class PlainJdbcDemo {
	private static SingerDao singerDao = new PlainSingerDao();
	private static Logger logger = LoggerFactory.getLogger(PlainJdbcDemo.class);
	
	public static void main(String[] args) {
		logger.info("Listing initial singer data: ");
		
		listAllSingers();
		
		logger.info("Listing Data for name: John");
		
		listSingerByName("John");
		
		logger.info("Listing the data for id: 1");
		
		singerNameById(1L);
		
		logger.info("Listing the data for id: 1");
		
		singerLastNameById(1L);
		
		logger.info("Listing the data with album");
		
		listAllSingersWithAlbum();
		
		logger.info("------------------");
		logger.info("Insert a new singer ");
		
		Singer singer = new Singer();
		singer.setFirstName("Ed");
		singer.setLastName("Sheeran");
		singer.setBirthDate(new Date((new GregorianCalendar(1991, 2, 1991)).getTime().getTime()));
		singerDao.insert(singer);
		logger.info("The singer has ID now: "+ singer.getId());
		
		logger.info("Listing the singer data after the singer is created: ");
		listAllSingers();
		
		
		logger.info("Deleting a Singer with ID: 4");
		singerDao.delete(4L);
		
		logger.info("Listing only the singer data: ");
		listAllSingers();
		
		logger.info("Listing the data with album after inserting the data");
		
		listAllSingersWithAlbum();
		
		logger.info("Get the Singer object with ID value: 1");
		Singer singer_data = singerDao.findNameByIdV1(1L);
		singerDao.update(singer_data);
		logger.info("Listing the singer data after the singer is updated: ");
		listAllSingers();
		
		logger.info("Insert a new Singer with Album");
		Singer singer1 = new Singer();
		Album album1 = new Album();
		album1.setTitle("Pink Venom");
		album1.setReleaseDate(new Date((new GregorianCalendar(2022, 8, 6)).getTime().getTime()));
		Album album2 = new Album();
		album2.setTitle("On The Ground");
		album2.setReleaseDate(new Date((new GregorianCalendar(2022, 9, 10)).getTime().getTime()));
		singer1.setFirstName("Jennie");
		singer1.setLastName("Kim");
		singer1.setBirthDate(new Date((new GregorianCalendar(1996, 1, 16)).getTime().getTime()));
		singer1.addAlbum(album1);
		singer1.addAlbum(album2);
		singerDao.insertWithAlbums(singer1);
		
		logger.info("Listing the data with album after inserting the data");
		
		listAllSingersWithAlbum();
		
		
		
	}
	
	private static void listAllSingers() {
		List<Singer> singers = singerDao.findAll();
		
		for(Singer singer: singers) {
			logger.info(singer.toString());
		}
	}
	
	private static void listSingerByName(String name) {
		List<Singer> singers = singerDao.findByFirstName(name);
		for(Singer singer: singers) {
			logger.info(singer.toString());
		}
	}
	
	private static void singerNameById(Long id) {
		String name = singerDao.findNameById(id);
		logger.info(name);
	}
	
	private static void listAllSingersWithAlbum() {
		List<Singer> singers = singerDao.findAllWithAlbums();
		
		for(Singer singer: singers) {
			logger.info(singer.toString());
		}
	}
	
	private static void singerLastNameById(Long id) {
		String name = singerDao.findLastNameById(id);
		logger.info(name);
	}
}
