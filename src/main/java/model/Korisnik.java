package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Korisnik {
	@Id
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
	
	public Korisnik() {};
	
	public Korisnik(String brojPasosa, String ime, String prezime, String email, String sifra) {
		this.brojPasosa=brojPasosa;
		this.ime=ime;
		this.prezime=prezime;
		this.email=email;
		this.sifra=sifra;
		kartice=new ArrayList<>();
	}

	public String getBrojPasosa() {
		return brojPasosa;
	}

	public void setBrojPasosa(String brojPasosa) {
		if(brojPasosa.length()==9) {
			this.brojPasosa = brojPasosa;
		}
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

	public List<KreditnaKartica> getKartice() {
		return kartice;
	}

	public void setKartice(List<KreditnaKartica> kartice) {
		this.kartice = kartice;
	}

	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}
}
