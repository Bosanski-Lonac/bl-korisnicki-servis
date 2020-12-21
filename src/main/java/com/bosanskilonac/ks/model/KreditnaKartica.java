package com.bosanskilonac.ks.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class KreditnaKartica {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Min(value = 100000000L)
	@Column(name="broj_kartice")
	private Long brojKartice;
	@NotBlank
	@Column(name="ime_vlasnika")
	private String imeVlasnika;
	@NotBlank
	@Column(name="prezime_vlasnika")
	private String prezimeVlasnika;
	@Min(value = 100)
	@Max(value = 999)
	@Column(name="sigurnosni_broj")
	private Integer sigurnosniBroj;
	@ManyToOne
	private Korisnik korisnik;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBrojKartice() {
		return brojKartice;
	}

	public void setBrojKartice(Long brojKartice) {
		this.brojKartice = brojKartice;
	}

	public String getImeVlasnika() {
		return imeVlasnika;
	}

	public void setImeVlasnika(String imeVlasnika) {
		this.imeVlasnika = imeVlasnika;
	}

	public String getPrezimeVlasnika() {
		return prezimeVlasnika;
	}

	public void setPrezimeVlasnika(String prezimeVlasnika) {
		this.prezimeVlasnika = prezimeVlasnika;
	}

	public Integer getSigurnosniBroj() {
		return sigurnosniBroj;
	}

	public void setSigurnosniBroj(Integer sigurnosniBroj) {
		this.sigurnosniBroj = sigurnosniBroj;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
}
