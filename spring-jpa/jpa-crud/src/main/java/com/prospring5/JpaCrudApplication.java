package com.prospring5;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.GenericApplicationContext;

import com.prospring5.entity.Album;
import com.prospring5.entity.Instrument;
import com.prospring5.entity.Singer;
import com.prospring5.service.InstrumentService;
import com.prospring5.service.SingerService;
import com.prospring5.service.SingerSummaryService;
import com.prospring5.service.SingerSummaryUntypeImpl;
import com.prospring5.view.SingerSummary;

@SpringBootApplication
public class JpaCrudApplication {	
	private static Logger logger = LoggerFactory.getLogger(JpaCrudApplication.class);


	public static void main(String[] args) {
		GenericApplicationContext ctx = (GenericApplicationContext)SpringApplication.run(JpaCrudApplication.class, args);
		SingerService singerService = ctx.getBean(SingerService.class);
		InstrumentService instrumentService = ctx.getBean(InstrumentService.class);
		SingerSummaryService singerSummaryService = ctx.getBean(SingerSummaryService.class);
		SingerSummaryUntypeImpl singerSummaryUntypeService = ctx.getBean(SingerSummaryUntypeImpl.class);

		// List All Singers
		List<Singer> singers = singerService.findAll();
		listSingers(singers);

		// List All singers with album
		List<Singer> singersWithAlbum = singerService.findAllWithAlbum();
		listSingersWithAlbum(singersWithAlbum);

		// Insert along with albums and instruments
		insert(singerService, instrumentService);
		List<Singer> singersWithAlbumAfterInsert = singerService.findAllWithAlbum();
		listSingersWithAlbum(singersWithAlbumAfterInsert);

		// update albums and instruments for Singer with id = 3
		update(singerService, instrumentService);
		List<Singer> singersWithAlbumAfterUpdate = singerService.findAllWithAlbum();
		listSingersWithAlbum(singersWithAlbumAfterUpdate);

		// delete the singer with id 4
		delete(singerService);
		List<Singer> singersWithAlbumAfterDelete = singerService.findAllWithAlbum();
		listSingersWithAlbum(singersWithAlbumAfterDelete);

		// summary service
		findAllSummary(singerSummaryService);
		findAllUntype(singerSummaryUntypeService);
		
	}

	private static void insert(SingerService service, InstrumentService service1){
		Singer singer = new Singer();
		singer.setFirstName("BB");
		singer.setLastName("King");
		singer.setBirthDate(
			new Date(
				new GregorianCalendar(
					1940, 8, 16
				).getTime().getTime()
			)
		);

		Album album = new Album();
		album.setTitle("My Kind of Blues");
		album.setReleaseDate(new Date(
			new GregorianCalendar(
				1961, 7, 18
			).getTime().getTime())
		);
		singer.addAlbum(album);

		album = new Album();
		album.setTitle("A Heart Full of Blues");
		album.setReleaseDate(new Date(
			new GregorianCalendar(
				1962, 3, 20
			).getTime().getTime()
		));
		singer.addAlbum(album);
		//service.save(singer);
		
		Instrument instrument = service1.findById("Synthesizer");
		instrument.getSingers().add(singer);
		singer.getInstruments().add(instrument);
		
		service.save(singer);

	}

	private static void update(SingerService service1, InstrumentService service2){
		Singer singer = service1.findById(3L);
		singer.setFirstName("Lewis");
		Album album = new Album();
		album.setTitle("Lost in Space");
		album.setReleaseDate(new Date(
			new GregorianCalendar(
				1964, 10, 18
			).getTime().getTime())
		);
		singer.addAlbum(album);

		Instrument instrument = service2.findById("Voice");
		instrument.getSingers().add(singer);
		singer.getInstruments().add(instrument);

		service1.save(singer);
	}

	public static void delete(SingerService singerService){
		Singer singer = singerService.findById(4L);
		singerService.delete(singer);
	}

	private static void findAllSummary(SingerSummaryService singerSummaryService){
		List<SingerSummary> singers = singerSummaryService.findAll();
		listSingerSummary(singers);
	}

	private static void findAllUntype(SingerSummaryUntypeImpl singerUntypeService){
		singerUntypeService.displayAllSingerSummary();
	}

	private static void listSingers(List<Singer> singers){
		logger.info(" --- Listing singers: ");
		singers.forEach(s -> logger.info(s.toString()));
	}

	private static void listSingersWithAlbum(List<Singer> singers){
		logger.info(" ---- Listing singers with instruments: ");
		singers.forEach(s -> {
			logger.info(s.toString());
			if(s.getAlbums()!=null){
				s.getAlbums().forEach(a -> logger.info("\t"+ a.toString()));
			}
			if(s.getInstruments()!=null){
				s.getInstruments().forEach(i -> logger.info(i.getInstrumentId()));
			}
		});
	} 

	private static void listSingerSummary(List<SingerSummary> singers){
		logger.info(" ---- Listing singers summary: ");
		for(SingerSummary singer: singers){
			logger.info(singer.toString());
		}
	}
	
}
