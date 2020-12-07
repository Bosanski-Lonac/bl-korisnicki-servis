package mapper;

import dto.KorisnikCreateDto;
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
	
	public Korisnik korisnikCreateDtoToKorisnik(KorisnikCreateDto korisnikCreateDto) {
		Korisnik korisnik=new Korisnik();
		korisnik.setBrojPasosa(korisnikCreateDto.getBrojPasosa());
		korisnik.setIme(korisnikCreateDto.getIme());
		korisnik.setPrezime(korisnikCreateDto.getPrezime());
		korisnik.setEmail(korisnikCreateDto.getEmail());
		korisnik.setSifra(korisnikCreateDto.getSifra());
		return korisnik;
	}
}
