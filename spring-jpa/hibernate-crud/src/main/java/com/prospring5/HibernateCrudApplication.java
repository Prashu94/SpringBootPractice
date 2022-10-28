package com.prospring5;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.GenericApplicationContext;

import com.prospring5.dao.InstrumentDao;
import com.prospring5.dao.SingerDao;
import com.prospring5.entity.Singer;

@SpringBootApplication
public class HibernateCrudApplication {
    private static Logger logger = LoggerFactory.getLogger(HibernateCrudApplication.class);
    
	public static void main(String[] args) {
	    GenericApplicationContext ctx = (GenericApplicationContext)SpringApplication.run(HibernateCrudApplication.class, args);
		SingerDao singerDao = ctx.getBean(SingerDao.class);
		InstrumentDao instrumentDao = ctx.getBean(InstrumentDao.class);
		
		// List all the singers in the database
		List<Singer> singers = singerDao.findAll();
		listSingers(singers);
		
		
		
		
		ctx.close();
		
	}
	
	
	private static void listSingers(List<Singer> singers) {
        logger.info(" ---- Listing singers:");
        for (Singer singer : singers) {
            logger.info(singer.toString());
        }
    }

    private static void listSingersWithAlbum(List<Singer> singers) {
        logger.info(" ---- Listing singers with instruments:");
        singers.forEach(s -> {
            logger.info(s.toString());
            if (s.getAlbums() != null) {
                s.getAlbums().forEach(a -> logger.info("\t" + a.toString()));
            }
            if (s.getInstruments() != null) {
                s.getInstruments().forEach(i -> logger.info("\tInstrument: " + i.getInstrumentId()));
            }
        });
    }

}
