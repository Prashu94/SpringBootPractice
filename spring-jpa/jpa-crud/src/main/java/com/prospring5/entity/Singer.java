package com.prospring5.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="singer")
@NamedQueries({
    @NamedQuery(name=Singer.FIND_ALL, query="select s from Singer s"),
    @NamedQuery(name=Singer.FIND_SINGER_BY_ID,
        query = "select distinct s from Singer s "+
                "left join fetch s.albums a "+
                "left join fetch s.instruments i " +
                "where s.id = :id"),
    @NamedQuery(name=Singer.FIND_ALL_WITH_ALBUM, 
        query = "select distinct s from Singer s "+
                "left join fetch s.albums a "+
                "left join fetch s.instruments i ")
})
@SqlResultSetMapping(
    name = "singerResult",
    entities = @EntityResult(entityClass = Singer.class)
)
public class Singer implements Serializable{
    public static final String FIND_ALL = "Singer.findAll";
    public static final String FIND_SINGER_BY_ID = "Singer.findById";
    public static final String FIND_ALL_WITH_ALBUM = "Singer.findAllWithAlbum";

    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Version
    @Column(name = "VERSION")
    private int version;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTH_DATE")
    private Date birthDate;

    @OneToMany(mappedBy = "singer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Album> albums = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "singer_instrument",
            joinColumns = @JoinColumn(name = "SINGER_ID"),
            inverseJoinColumns = @JoinColumn(name = "INSTRUMENT_ID"))
    private Set<Instrument> instruments = new HashSet<>();
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getVersion() {
        return version;
    }
    public void setVersion(int version) {
        this.version = version;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public Date getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    public Set<Album> getAlbums() {
        return albums;
    }
    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    public boolean addAlbum(Album album){
        album.setSinger(this);
        return getAlbums().add(album);
    }

    public void removeAlbum(Album album){
        getAlbums().remove(album);
    }

    public Set<Instrument> getInstruments() {
        return instruments;
    }
    public void setInstruments(Set<Instrument> instruments) {
        this.instruments = instruments;
    }

    public boolean addInstrument(Instrument instrument) {
		return instruments.add(instrument);
	}
    @Override
    public String toString() {
        return "Singer [id=" + id + ", version=" + version + ", firstName=" + firstName + ", lastName=" + lastName
                + ", birthDate=" + birthDate + "]";
    }

    

}
