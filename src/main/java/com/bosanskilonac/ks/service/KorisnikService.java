package com.bosanskilonac.ks.service;

import dto.KorisnikCUDto;
import dto.KorisnikDto;
import dto.TokenRequestDto;
import dto.TokenResponseDto;

public interface KorisnikService {
	KorisnikDto register(KorisnikCUDto korisnikCreateDto);
	
	KorisnikDto update(Long id, KorisnikCUDto korisnikUpdateDto);
	
	void deleteById(Long id);
	
	TokenResponseDto login(TokenRequestDto tokenRequestDto);

	//Long getIdKorisnika(String authorization);
}
