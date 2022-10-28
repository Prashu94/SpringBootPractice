package com.prospring5.service;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prospring5.entity.Instrument;
import com.prospring5.entity.Singer;

@Service("jpaSingerService")
@Repository
@Transactional
public class SingerServiceImpl implements SingerService {
    final static String ALL_SINGER_NATIVE_QUERY = 
    "select id, first_name, last_name, birth_date, version from singer";

    private static Logger logger = LoggerFactory.getLogger(SingerServiceImpl.class);

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    public List<Singer> findAll(){
        List<Singer> singers = em.createNamedQuery(Singer.FIND_ALL_WITH_ALBUM, Singer.class).getResultList();
        return singers;
    }

    @Transactional(readOnly = true)
    public Singer findById(Long id){
        TypedQuery<Singer> query = em.createNamedQuery(Singer.FIND_SINGER_BY_ID, Singer.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public Singer save(Singer singer){
        if(singer.getId() == null){
            logger.info("Inserting new Singer");
            em.persist(singer);
        }else{
            em.merge(singer);
            logger.info("Updating existing singer");
        }
        logger.info("Singer saved with id: "+ singer.getId());
        return singer;
    }

    public void delete(Singer singer){
        Singer mergedContact = em.merge(singer);
        em.remove(mergedContact);

        logger.info("Singer with id: "+ singer.getId() + "deleted successfully");
    }

    @Transactional(readOnly = true)
    public List<Singer> findAllByNativeQuery(){
        return em.createNativeQuery(ALL_SINGER_NATIVE_QUERY,
        "singerResult").getResultList();
    }

    @Override
    public List<Singer> findAllWithAlbum() {
        List<Singer> singers = em.createNamedQuery(Singer.FIND_ALL_WITH_ALBUM, Singer.class).getResultList();
        return singers;
    }

    @Override
    public Instrument save(Instrument instrument) {
        em.persist(instrument);
        return instrument;
    }

}
