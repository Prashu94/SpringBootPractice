package com.prospring5.service;

import com.prospring5.entity.Instrument;
import com.prospring5.entity.Singer;

public interface InstrumentService {
    Instrument save(Instrument instrument);
    Instrument findById(String instrumentId);
    Singer save(Singer singer);
}
