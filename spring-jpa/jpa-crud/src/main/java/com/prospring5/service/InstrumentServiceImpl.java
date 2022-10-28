package com.prospring5.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prospring5.entity.Instrument;
import com.prospring5.entity.Singer;

@Service("jpaInstrumentService")
@Repository
@Transactional
public class InstrumentServiceImpl implements InstrumentService{

    private static final Logger logger = LoggerFactory.getLogger(InstrumentServiceImpl.class);
    final static String INSTRUMENT_BY_ID = "select instrument_id from instrument where instrument_id = :instrument_id";

    @PersistenceContext
    private EntityManager em;

    @Override
    public Instrument save(Instrument instrument) {
        if(instrument.getInstrumentId() == null){
            logger.info("Insert new Instrument Id");
            em.persist(instrument);
        }else{
            em.merge(instrument);
            logger.info("Insturment Id is updated");
        }
        return instrument;
    }

    @Override
    public Instrument findById(String instrumentId) {
        Query instruments = em.createNativeQuery(INSTRUMENT_BY_ID, "instrumentResult");
        /*Query instruments = em.createQuery("select i from instrument i JOIN FETCH i.singer where i.instrument_id = :instrumentId", Instrument.class);*/
        instruments.setParameter("instrument_id", instrumentId);
        return (Instrument)instruments.getSingleResult();
    }

    @Override
    public Singer save(Singer singer) {
        em.persist(singer);
        return singer;
    }
    
}
