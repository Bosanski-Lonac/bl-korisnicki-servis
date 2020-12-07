package dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class KorisnikUpdateDto {
	@NotEmpty(message = "Broj pasosa ne moze ostati prazan")
	@Size(min = 9, max = 9)
	private String brojPasosa;
	@NotEmpty(message = "Ime ne moze ostati prazno")
	private String ime;
	@NotEmpty(message = "Prezime ne moze ostati prazno")
	private String prezime;
	@NotEmpty(message = "Email ne moze ostati prazan")
	private String email;
	@NotEmpty(message = "Sifra ne moze ostati prazna")
	@Size(min = 5)
	private String sifra;
	
	public String getBrojPasosa() {
		return brojPasosa;
	}
	public void setBrojPasosa(String brojPasosa) {
		this.brojPasosa = brojPasosa;
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
}
