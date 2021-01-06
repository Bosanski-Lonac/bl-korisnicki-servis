package com.bosanskilonac.ks.mapper;

import org.springframework.stereotype.Component;

import com.bosanskilonac.ks.model.Admin;
import com.bosanskilonac.ks.model.Korisnik;

import dto.KorisnikCreateDto;
import dto.KorisnikDto;
import dto.KorisnikUpdateDto;
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
		korisnikDto.setMilje(korisnik.getMilje());
		return korisnikDto;
	}
	
	public KorisnikDto adminToKorisnikDto(Admin admin) {
		KorisnikDto korisnikDto=new KorisnikDto();
		korisnikDto.setId(admin.getId());
		korisnikDto.setUsername(admin.getUsername());
		korisnikDto.setRole(admin.getRole());
		return korisnikDto;
	}
	
	public Korisnik korisnikCreateDtoToKorisnik(KorisnikCreateDto korisnikCreateDto) {
		Korisnik korisnik=new Korisnik();
		korisnik.setEmail(korisnikCreateDto.getEmail());
		korisnik.setSifra(korisnikCreateDto.getSifra());
		korisnik.setIme(korisnikCreateDto.getIme());
		korisnik.setPrezime(korisnikCreateDto.getPrezime());
		korisnik.setBrojPasosa(korisnikCreateDto.getBrojPasosa());
		korisnik.setMilje(0);
		return korisnik;
	}
	
	public Korisnik korisnikUpdateDtoToKorisnik(KorisnikUpdateDto korisnikUpdateDto, Korisnik korisnik) {
		korisnik.setEmail(korisnikUpdateDto.getEmail());
		if(!korisnikUpdateDto.getNovaSifra().isBlank()) {
			korisnik.setSifra(korisnikUpdateDto.getNovaSifra());
		}
		korisnik.setIme(korisnikUpdateDto.getIme());
		korisnik.setPrezime(korisnikUpdateDto.getPrezime());
		korisnik.setBrojPasosa(korisnikUpdateDto.getBrojPasosa());
		return korisnik;
	}
}
