package com.prospring5.dao;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.prospring5.entity.Instrument;

@Transactional
@Repository("instrumentDao")
public class InstrumentDaoImpl implements InstrumentDao{
	
	private static final Log logger = LogFactory.getLog(InstrumentDao.class);
	private SessionFactory sessionFactory;
	
	
	@Override
	public Instrument save(Instrument instrument) {
		sessionFactory.getCurrentSession().saveOrUpdate(instrument);
		logger.info("Instrument saved with id: "+ instrument.getInstrumentId());
		return instrument;
	}


	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Resource(name="sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
}
