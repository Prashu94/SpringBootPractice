package com.prospring5.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="singer")
@NamedQueries({
    @NamedQuery(name=Singer.FIND_SINGER_BY_ID,
            query="select distinct s from Singer s " +
                    "left join fetch s.albums a " +
                    "left join fetch s.instruments i " +
                    "where s.id = :id"),
    @NamedQuery(name=Singer.FIND_ALL_WITH_ALBUM,
            query="select distinct s from Singer s " +
                    "left join fetch s.albums a " +
                    "left join fetch s.instruments i")
})
public class Singer extends AbstractEntity{
	public static final String FIND_SINGER_BY_ID = "Singer.findById";
	public static final String FIND_ALL_WITH_ALBUM = "Singer.findAllWithAlbum";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
	private long id;
	
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Temporal(TemporalType.DATE)
	@Column(name="BIRTH_DATE")
	private Date birthDate;
	
	@OneToMany(mappedBy = "singer", cascade = CascadeType.ALL
			, orphanRemoval = true)
	private Set<Album> albums = new HashSet<Album>();
	
	@ManyToMany
	@JoinTable(name="singer_instrument",
				joinColumns = @JoinColumn(name="SINGER_ID"),
				inverseJoinColumns = @JoinColumn(name="INSTRUMENT_ID"))
	private Set<Instrument> instruments = new HashSet<>();
	
	
	public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
	public Set<Instrument> getInstruments() {
		return instruments;
	}
	public void setInstruments(Set<Instrument> instruments) {
		this.instruments = instruments;
	}
	
	public boolean addAlbum(Album album) {
		album.setSinger(this);
		return albums.add(album);
	}

	public void removeAlbum(Album album) {
		albums.remove(album);
	}
	
	public boolean addInstrument(Instrument instrument) {
		return instruments.add(instrument);
	}
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return String.format("Singer - id: %d, First Name: %s, Last Name: %s, Birthday: %s",
				id, firstName, lastName, sdf.format(birthDate));
	}
	
	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		if (!super.equals(o))
			return false;
		Singer singer = (Singer) o;
		if (firstName != null ? !firstName.equals(singer.firstName) : singer.firstName != null)
			return false;
		if (lastName != null ? !lastName.equals(singer.lastName) : singer.lastName != null)
			return false;
		return birthDate != null ? birthDate.equals(singer.birthDate) : singer.birthDate == null;
	}

	@Override public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
		result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
		result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
		return result;
	}
}
