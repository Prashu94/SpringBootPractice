package com.prospring5.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.prospring5.entity.Instrument;
import com.prospring5.entity.Singer;

@Transactional
@Repository("singerDao")
public class SingerDaoImpl implements SingerDao{
	
	private static final Log logger = LogFactory.getLog(SingerDaoImpl.class);
	private SessionFactory sessionFactory;
	
	
	@Transactional(readOnly = true)
	public List<Singer> findAll() {
		return sessionFactory.getCurrentSession().createQuery("from Singer s").list();
	}

	@Transactional(readOnly = true)
	public List<Singer> findAllWithAlbum() {
		return sessionFactory.getCurrentSession()
				.getNamedQuery("Singer.findAllWithAlbum").list();
	}

	@Transactional(readOnly = true)
	public Singer findById(Long id) {
		return (Singer) sessionFactory.getCurrentSession()
				.getNamedQuery("Singer.findById")
				.setParameter("id", id)
				.uniqueResult();
	}

	@Override
	public Singer save(Singer singer) {
		sessionFactory.getCurrentSession().saveOrUpdate(singer);
		logger.info("Singer saved with id: "+ singer.getId());
		return singer;
	}
	
	@Override
    public Instrument save(Instrument instrument) {
        sessionFactory.getCurrentSession().saveOrUpdate(instrument);
        logger.info("Instrument saved with id: "+ instrument.getInstrumentId());
        return instrument;
    }

	@Override
	public void delete(Singer singer) {
		sessionFactory.getCurrentSession().delete(singer);
		logger.info("Singer deleted with id: "+ singer.getId());
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
