package mapper;

import dto.KorisnikDto;
import model.Korisnik;

public class KorisnikMapper {
	public KorisnikDto korisnikToKorisnikDto(Korisnik korisnik) {
		KorisnikDto korisnikDto=new KorisnikDto();
		korisnikDto.setBrojPasosa(korisnik.getBrojPasosa());
		korisnikDto.setIme(korisnik.getIme());
		korisnikDto.setPrezime(korisnik.getPrezime());
		korisnikDto.setEmail(korisnik.getEmail());
		korisnikDto.setSifra(korisnik.getSifra());
		return korisnikDto;
	}
	
	public Korisnik korisnikCreateDtoToKorisnik(KorisnikDto korisnikDto) {
		Korisnik korisnik=new Korisnik();
		korisnik.setBrojPasosa(korisnikDto.getBrojPasosa());
		korisnik.setIme(korisnikDto.getIme());
		korisnik.setPrezime(korisnikDto.getPrezime());
		korisnik.setEmail(korisnikDto.getEmail());
		korisnik.setSifra(korisnikDto.getSifra());
		return korisnik;
	}
}
