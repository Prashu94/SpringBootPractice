package com.prospring5;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.GenericApplicationContext;

import com.prospring5.dao.SingerDao;
import com.prospring5.entity.Album;
import com.prospring5.entity.Instrument;
import com.prospring5.entity.Singer;

@SpringBootApplication
public class HibernateBaseApplication {
	
	private static Logger logger = LoggerFactory.getLogger(HibernateBaseApplication.class);
	
	public static void main(String[] args) {
		GenericApplicationContext ctx = (GenericApplicationContext) SpringApplication.run(HibernateBaseApplication.class, args);
		SingerDao singerDao = ctx.getBean(SingerDao.class);
		
		// List all singers from the database
		List<Singer> singers = singerDao.findAll();
		listSingers(singers);
		
		// List all singers with albums and instruments
		List<Singer> singersWithAlbums = singerDao.findAllWithAlbum();
		listSingersWithAlbum(singersWithAlbums);
		
		// Get the singer by ID
		Singer singer = singerDao.findById(1L);
		logger.info(singer.toString());
		
		// save the data to the singer, album, instrument
		insertSingersWithAssociation(singerDao);
		
		List<Singer> singersWithAlbums1 = singerDao.findAllWithAlbum();
		listSingersWithAlbum(singersWithAlbums1);
		
		// update the singer data
		updateAllAttributes(singerDao);
		List<Singer> singersWithAlbums2 = singerDao.findAllWithAlbum();
		listSingersWithAlbum(singersWithAlbums2);
		
		// delete a singer
		delete(singerDao);
		List<Singer> singersWithAlbums3 = singerDao.findAllWithAlbum();
		listSingersWithAlbum(singersWithAlbums3);
		
		
		ctx.close();
	}
	
	private static void insertSingersWithAssociation(SingerDao singerDao) {
		Singer singer = new Singer();
		singer.setFirstName("BB");
		singer.setLastName("King");
		singer.setBirthDate(new Date(
				(new GregorianCalendar(1940, 8, 16)).getTime().getTime()));
		
		Album album = new Album();
		album.setTitle("My Kind of Blues");
		album.setReleaseDate(new java.sql.Date(
				(new GregorianCalendar(1961, 7, 18)).getTime().getTime()));
		singer.addAlbum(album);
		
		
		album = new Album();
		album.setTitle("A Heart Fall of Blues");
		album.setReleaseDate(new java.sql.Date(
				(new GregorianCalendar(1962, 3, 20)).getTime().getTime()));
		singer.addAlbum(album);
		
		Instrument instrument = new Instrument();
		instrument.setInstrumentId("Guitar");
		
		singerDao.save(singer);
		
		
	}
	
	public static void updateAllAttributes(SingerDao singerDao) {
		Singer singer = singerDao.findById(1L);
		
		// retrieve the album
		Album album = singer.getAlbums().stream().filter(a -> a.getTitle().equals("Battle Studies")).findFirst().get();
		
		singer.setLastName("Clayton");
		singer.removeAlbum(album);
		Instrument instrument = new Instrument();
		instrument.setInstrumentId("Saxophone");
		
		singer.addInstrument(instrument);
		singerDao.save(instrument);
		
		singerDao.save(singer);
		
	}
	
	private static void delete(SingerDao singerDao) {
		Singer singer = singerDao.findById(2L);
		singerDao.delete(singer);
	}
	
	private static void listSingersWithAlbum(List<Singer> singers) {
		logger.info("--- Listing singers with instruments:");
		singers.forEach(singer -> {
			logger.info(singer.toString());
			if (singer.getAlbums() != null) {
				singer.getAlbums().forEach(album -> logger.info("\t" + album.toString()));
			}
			if (singer.getInstruments() != null) {
				singer.getInstruments().forEach(instrument -> logger.info("\t"+ instrument.getInstrumentId()));
			}
		});
	}
	
	private static void listSingers(List<Singer> singers) {
		logger.info("--- Listing singers:");
		singers.forEach(singer -> logger.info(singer.toString()));
	}
}
