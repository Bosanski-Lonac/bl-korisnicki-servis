package com.bosanskilonac.ks.mapper;

import org.springframework.stereotype.Component;

import com.bosanskilonac.ks.model.Korisnik;

import dto.KorisnikCUDto;
import dto.KorisnikDto;

@Component
public class KorisnikMapper {
	public KorisnikDto korisnikToKorisnikDto(Korisnik korisnik) {
		KorisnikDto korisnikDto=new KorisnikDto();
		korisnikDto.setEmail(korisnik.getEmail());
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
	
	public Korisnik korisnikUpdateDtoToKorisnik(KorisnikCUDto korisnikUpdateDto, Korisnik korisnik) {
		korisnik.setEmail(korisnikUpdateDto.getEmail());
		korisnik.setSifra(korisnikUpdateDto.getSifra());
		korisnik.setIme(korisnikUpdateDto.getIme());
		korisnik.setPrezime(korisnikUpdateDto.getPrezime());
		korisnik.setBrojPasosa(korisnikUpdateDto.getBrojPasosa());
		return korisnik;
	}
}
