package com.prospring5.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

@Entity
@Table(name="instrument")
@SqlResultSetMapping(
    name = "instrumentResult",
    entities = @EntityResult(entityClass = Instrument.class)
)
public class Instrument implements Serializable{

    @Id
    @Column(name = "INSTRUMENT_ID")
    private String instrumentId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "singer_instrument",
                joinColumns = @JoinColumn(name = "INSTRUMENT_ID"),
                inverseJoinColumns = @JoinColumn(name = "SINGER_ID"))
    private Set<Singer> singers = new HashSet<>();

    public String getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(String instrumentId) {
        this.instrumentId = instrumentId;
    }

    public Set<Singer> getSingers() {
        return singers;
    }

    public void setSingers(Set<Singer> singers) {
        this.singers = singers;
    }

    public void addSingers(Singer singer) {
        if(singers == null) {
            singers = new HashSet<>();
        }
        
        singers.add(singer);
    }
    
    public void removeSingers(Singer singer) {
        getSingers().remove(singer);
    }

    @Override
    public String toString() {
        return "Instrument [instrumentId=" + getInstrumentId() + "]";
    }

    
}
