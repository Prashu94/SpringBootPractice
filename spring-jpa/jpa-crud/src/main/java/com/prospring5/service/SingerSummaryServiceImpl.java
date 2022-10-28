package com.prospring5.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prospring5.view.SingerSummary;

@Service("singerSummaryServcice")
@Repository
@Transactional
public class SingerSummaryServiceImpl implements SingerSummaryService{

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    @Override
    public List<SingerSummary> findAll() {
        List<SingerSummary> result = em.createQuery(
				"select new com.prospring5.view.SingerSummary("
						+ "s.firstName, s.lastName, a.title) from Singer s "
						+ "left join s.albums a "
						+ "where a.releaseDate=(select max(a2.releaseDate) from Album a2 where a2.singer.id = s.id)",
				SingerSummary.class).getResultList();
        return result;
    }
    
}
