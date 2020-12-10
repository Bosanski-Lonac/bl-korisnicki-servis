package mapper;

import dto.KorisnikCUDto;
import dto.KorisnikDto;
import model.Korisnik;

public class KorisnikMapper {
	public KorisnikDto korisnikToKorisnikDto(Korisnik korisnik) {
		KorisnikDto korisnikDto=new KorisnikDto();
		korisnikDto.setIme(korisnik.getIme());
		korisnikDto.setPrezime(korisnik.getPrezime());
		korisnikDto.setBrojPasosa(korisnik.getBrojPasosa());
		return korisnikDto;
	}
	
	public Korisnik korisnikCreateDtoToKorisnik(KorisnikCUDto korisnikCreateDto) {
		Korisnik korisnik=new Korisnik();
		korisnik.setEmail(korisnikCreateDto.getEmail());
		korisnik.setSifra(korisnikCreateDto.getSifra());
		korisnik.setIme(korisnikCreateDto.getIme());
		korisnik.setPrezime(korisnikCreateDto.getPrezime());
		korisnik.setBrojPasosa(korisnikCreateDto.getBrojPasosa());
		return korisnik;
	}
}
