package com.prospring5.service;

import java.util.List;

import com.prospring5.entity.Instrument;
import com.prospring5.entity.Singer;

public interface SingerService {
    List<Singer> findAll();
    List<Singer> findAllWithAlbum();
    Singer findById(Long id);
    Singer save(Singer singer);
    void delete(Singer singer);
    List<Singer> findAllByNativeQuery();
    Instrument save(Instrument instrument);
}
