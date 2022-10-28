package com.prospring5;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import com.prospring5.config.AppConfig;
import com.prospring5.dao.SingerDao;
import com.prospring5.entities.Album;
import com.prospring5.entities.Singer;

public class AnnotationJdbcTest {
	private GenericApplicationContext ctx;
	private SingerDao singerDao;
	private static final Logger logger = LoggerFactory.getLogger(AnnotationJdbcTest.class);
	
	@Before
	public void setUp() {
		ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		singerDao = ctx.getBean(SingerDao.class);
		assertNotNull(singerDao);
	}

	@Test
	public void testFindAll() {
		List<Singer> singers = singerDao.findAll();
		assertTrue(singers.size() == 3);
		listSingers(singers);
		ctx.close();
	}
	
	@Test
	public void testFindByFirstName() {
		List<Singer> singers = singerDao.findByFirstName("John");
		assertTrue(singers.size() == 1);
		listSingers(singers);
		ctx.close();
	}
	
	@Test
	public void testSingerUpdate() {
		Singer singer = new Singer();
		singer.setId(1L);
		singer.setFirstName("John");
		singer.setLastName("Clayton");
		singer.setBirthDate(new Date(
				(new GregorianCalendar(1977, 9, 16)).getTime().getTime()));
		singerDao.update(singer);
		
		List<Singer> singers = singerDao.findAll();
		listSingers(singers);
	}
	
	@Test
	public void testSingerTest() {
		Singer singer = new Singer();
		singer.setFirstName("Ed");
		singer.setLastName("Sheeran");
		singer.setBirthDate(new Date(
				(new GregorianCalendar(1991, 1, 17)).getTime().getTime()));
		singerDao.insert(singer);
		
		List<Singer> singers = singerDao.findAll();
		listSingers(singers);
		//logger.info(singers.toString());
	}
	
	@Test
	public void testSingerInsertWithAlbum() {
		Singer singer = new Singer();
		singer.setFirstName("BB");
		singer.setLastName("King");
		singer.setBirthDate(new Date(
				(new GregorianCalendar(1940, 8, 16)).getTime().getTime()));
		
		Album album = new Album();
		album.setTitle("My Kind of Blues");
		album.setReleaseDate(new Date(
				(new GregorianCalendar(1961, 7, 18)).getTime().getTime()));
		singer.addAlbum(album);
		
		
		album = new Album();
		album.setTitle("A Heart Full of Blues");
		album.setReleaseDate(new Date(
				(new GregorianCalendar(1962, 3, 20)).getTime().getTime()));
		singer.addAlbum(album);
		
		singerDao.insertWithAlbums(singer);
	}
	
	@Test
	public void testFindByFirstNameById() {
		String firstName = singerDao.findNameById(2L);
		assertEquals("Eric", firstName);
		System.out.println("Retrieved Value: "+ firstName);
	}
	
	@Test
	public void testFindWithAlbums() {
		List<Singer> singers = singerDao.findAllWithAlbums();
		listSingers(singers);
	}
	
	@Test
	public void testFindNameByIdV1() {
		Singer singer = singerDao.findNameByIdV1(1L);
		logger.info(singer.toString());
	}
	
	@Test
	public void testFindLastNameById() {
		String lastName = singerDao.findLastNameById(1L);
		logger.info("Last Name: "+ lastName);
	}
	
	private void listSingers(List<Singer> singers){
		singers.forEach(singer -> {
			System.out.println(singer);
			if (singer.getAlbums() != null) {
				for (Album album : singer.getAlbums()) {
					System.out.println("\t--> " + album);
				}
			}
		});
	}

	@After
	public void tearDown() {
		ctx.close();
	}
	
}
