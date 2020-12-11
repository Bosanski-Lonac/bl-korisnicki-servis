package com.bosanskilonac.ks.model;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import enums.Rank;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Korisnik {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Email
	@Column(unique=true)
	private String email;
	@Column(unique=true, name="broj_pasosa")
	private String brojPasosa;
	private String ime;
	private String prezime;
	private String sifra;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "korisnik", orphanRemoval = true)
	private List<KreditnaKartica> kartice;
	@Enumerated(EnumType.STRING)
	private Rank rank;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	
	public String getBrojPasosa() {
		return brojPasosa;
	}

	public void setBrojPasosa(String brojPasosa) {
		if(brojPasosa.length()==9) {
			this.brojPasosa = brojPasosa;
		}
	}

	public List<KreditnaKartica> getKartice() {
		return kartice;
	}

	public void setKartice(List<KreditnaKartica> kartice) {
		this.kartice = kartice;
		/*this.kartice.clear();
	    if (kartice != null) {
	        this.kartice.addAll(kartice);
	    }*/
	}

	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}
}
