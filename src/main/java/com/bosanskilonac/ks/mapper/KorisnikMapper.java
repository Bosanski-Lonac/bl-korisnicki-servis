package com.bosanskilonac.ks.mapper;

import org.springframework.stereotype.Component;

import com.bosanskilonac.ks.model.Admin;
import com.bosanskilonac.ks.model.Korisnik;

import dto.KorisnikCUDto;
import dto.KorisnikDto;
import enums.Rank;
import enums.Role;

@Component
public class KorisnikMapper {
	public KorisnikDto korisnikToKorisnikDto(Korisnik korisnik) {
		KorisnikDto korisnikDto=new KorisnikDto();
		korisnikDto.setId(korisnik.getId());
		korisnikDto.setUsername(korisnik.getEmail());
		korisnikDto.setRole(Role.ROLE_USER);
		korisnikDto.setIme(korisnik.getIme());
		korisnikDto.setPrezime(korisnik.getPrezime());
		korisnikDto.setBrojPasosa(korisnik.getBrojPasosa());
		return korisnikDto;
	}
	
	public KorisnikDto adminToKorisnikDto(Admin admin) {
		KorisnikDto korisnikDto=new KorisnikDto();
		korisnikDto.setId(admin.getId());
		korisnikDto.setUsername(admin.getUsername());
		korisnikDto.setRole(Role.ROLE_ADMIN);
		return korisnikDto;
	}
	
	public Korisnik korisnikCreateDtoToKorisnik(KorisnikCUDto korisnikCreateDto) {
		Korisnik korisnik=new Korisnik();
		korisnik.setEmail(korisnikCreateDto.getEmail());
		korisnik.setSifra(korisnikCreateDto.getSifra());
		korisnik.setIme(korisnikCreateDto.getIme());
		korisnik.setPrezime(korisnikCreateDto.getPrezime());
		korisnik.setBrojPasosa(korisnikCreateDto.getBrojPasosa());
		korisnik.setRank(Rank.BRONZA);
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
