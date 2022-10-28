package com.prospring5.config;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospring5.dao.InstrumentDao;
import com.prospring5.dao.SingerDao;
import com.prospring5.entity.Album;
import com.prospring5.entity.Instrument;
import com.prospring5.entity.Singer;

@Service
public class DBIntializer {
	private Logger logger = LoggerFactory.getLogger(DBIntializer.class);
	
	@Autowired
	private SingerDao singerDao;
	@Autowired
	private InstrumentDao instrumentDao;

	@PostConstruct
	public void initDB() {
	    logger.info("Starting database initialization...");

        Instrument guitar = new Instrument();
        guitar.setInstrumentId("Guitar");
        instrumentDao.save(guitar);

        Instrument piano = new Instrument();
        piano.setInstrumentId("Piano");
        instrumentDao.save(piano);

        Instrument voice = new Instrument();
        voice.setInstrumentId("Voice");
        instrumentDao.save(voice);

        Singer singer = new Singer();
        singer.setFirstName("John");
        singer.setLastName("Mayer");
        singer.setBirthDate(new Date(
                (new GregorianCalendar(1977, 9, 16)).getTime().getTime()));
        
        Album album1 = new Album();
        album1.setTitle("The Search For Everything");
        album1.setReleaseDate(new java.sql.Date(
                (new GregorianCalendar(2017, 0, 20)).getTime().getTime()));
        singer.addAlbum(album1);

        Album album2 = new Album();
        album2.setTitle("Battle Studies");
        album2.setReleaseDate(new java.sql.Date(
                (new GregorianCalendar(2009, 10, 17)).getTime().getTime()));
        singer.addAlbum(album2);

        singerDao.save(singer);
        
        guitar.addSingers(singer);
        piano.addSingers(singer);
        singer.addInstrument(guitar);
        singer.addInstrument(piano);
        
        singerDao.save(guitar);
        singerDao.save(piano);

        singer = new Singer();
        singer.setFirstName("Eric");
        singer.setLastName("Clapton");
        singer.setBirthDate(new Date(
                (new GregorianCalendar(1945, 2, 30)).getTime().getTime()));
        singer.addInstrument(voice);

        Album album = new Album();
        album.setTitle("From The Cradle");
        album.setReleaseDate(new java.sql.Date(
                (new GregorianCalendar(1994, 8, 13)).getTime().getTime()));
        singer.addAlbum(album);

        singerDao.save(singer);

        singer = new Singer();
        singer.setFirstName("John");
        singer.setLastName("Butler");
        singer.setBirthDate(new Date(
                (new GregorianCalendar(1975, 3, 1)).getTime().getTime()));
        singer.addInstrument(piano);

        singerDao.save(singer);
        logger.info("Database initialization finished.");
		
	}
}
