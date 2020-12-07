package dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class KreditnaKarticaDto {
	@NotEmpty(message = "Broj kartice ne moze ostati prazan")
	@Size(min = 9, max = 19)
	private Long brojKartice;
	@NotEmpty(message = "Ime vlasnika ne moze ostati prazno")
	private String imeVlasnika;
	@NotEmpty(message = "Prezime vlasnika ne moze ostati prazno")
	private String prezimeVlasnika;
	@NotEmpty(message = "Sigurnosni broj ne moze ostati prazan")
	@Size(min = 3, max = 3)
	private Integer sigurnosniBroj;
	
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
}
