package model;

import javax.persistence.*;

public class KreditnaKartica {
	@Id
	@Column(name="broj_kartice")
	private Long brojKartice;
	@Column(name="ime_vlasnika")
	private String imeVlasnika;
	@Column(name="prezime_vlasnika")
	private String prezimeVlasnika;
	@Column(name="sigurnosni_broj")
	private Integer sigurnosniBroj;
	@ManyToOne
	private Korisnik korisnik;
	
	public KreditnaKartica(Long brojKartice, String imeVlasnika, String prezimeVlasnika, Integer sigurnosniBroj) {
		super();
		this.brojKartice = brojKartice;
		this.imeVlasnika = imeVlasnika;
		this.prezimeVlasnika = prezimeVlasnika;
		this.sigurnosniBroj = sigurnosniBroj;
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
		if(sigurnosniBroj.toString().length()==3) {
			this.sigurnosniBroj = sigurnosniBroj;
		}
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
}
