package com.bosanskilonac.ks.mapper;

import org.springframework.stereotype.Component;

import com.bosanskilonac.ks.model.Korisnik;
import com.bosanskilonac.ks.model.KreditnaKartica;

import dto.KreditnaKarticaCUDto;
import dto.KreditnaKarticaDto;

@Component
public class KreditnaKarticaMapper {
	
	public KreditnaKarticaDto kreditnaKarticaToKreditnaKarticaDto(KreditnaKartica kreditnaKartica) {
		KreditnaKarticaDto kreditnaKarticaDto=new KreditnaKarticaDto();
		kreditnaKarticaDto.setId(kreditnaKartica.getId());
		kreditnaKarticaDto.odrediKrajKartice(kreditnaKartica.getBrojKartice());
		kreditnaKarticaDto.setImeVlasnika(kreditnaKartica.getImeVlasnika());
		kreditnaKarticaDto.setPrezimeVlasnika(kreditnaKartica.getPrezimeVlasnika());
		return kreditnaKarticaDto;
	}
	
	public KreditnaKartica kreditnaKarticaDtoToKreditnaKartica(KreditnaKarticaCUDto kreditnaKarticaCreateDto, Korisnik korisnik) {
		KreditnaKartica kreditnaKartica=new KreditnaKartica();
		kreditnaKartica.setBrojKartice(kreditnaKarticaCreateDto.getBrojKartice());
		kreditnaKartica.setImeVlasnika(kreditnaKarticaCreateDto.getImeVlasnika());
		kreditnaKartica.setPrezimeVlasnika(kreditnaKarticaCreateDto.getPrezimeVlasnika());
		kreditnaKartica.setSigurnosniBroj(kreditnaKarticaCreateDto.getSigurnosniBroj());
		kreditnaKartica.setKorisnik(korisnik);
		return kreditnaKartica;
	}
}
