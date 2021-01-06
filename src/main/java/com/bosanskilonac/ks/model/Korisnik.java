package com.bosanskilonac.ks.model;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Korisnik {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Email
	@Column(unique=true)
	private String email;
	@NotBlank
	@Size(min = 9, max = 9)
	@Column(unique=true, name="broj_pasosa")
	private String brojPasosa;
	@NotBlank
	private String ime;
	@NotBlank
	private String prezime;
	@NotBlank
	@Size(min = 5)
	private String sifra;
	@PositiveOrZero
	private Integer milje;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "korisnik", orphanRemoval = true)
	private List<KreditnaKartica> kartice;
	
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

	public void setSifra(@NotBlank @Size(min = 5) String sifra) {
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

	public Integer getMilje() {
		return milje;
	}

	public void setMilje(Integer milje) {
		this.milje = milje;
	}
	
	public void addMilje(Integer milje) {
		this.milje += milje;
	}

	public List<KreditnaKartica> getKartice() {
		return kartice;
	}

	public void setKartice(List<KreditnaKartica> kartice) {
		this.kartice = kartice;
	}
}
