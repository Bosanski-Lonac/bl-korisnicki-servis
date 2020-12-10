package com.bosanskilonac.ks.service;

import dto.KorisnikCUDto;
import dto.KorisnikDto;
import dto.TokenRequestDto;
import dto.TokenResponseDto;

public interface KorisnikService {
	KorisnikDto register(KorisnikCUDto korisnikCreateDto);
	
	KorisnikDto update(String id, KorisnikCUDto korisnikUpdateDto);
	
	void deleteById(String id);
	
	TokenResponseDto login(TokenRequestDto tokenRequestDto);
}
