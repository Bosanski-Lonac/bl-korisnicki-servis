package com.bosanskilonac.ks.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import dto.KorisnikCreateDto;
import dto.KorisnikDto;
import dto.KorisnikUpdateDto;
import dto.PovracajNovcaDto;
import dto.TokenRequestDto;
import dto.TokenResponseDto;
import exceptions.CustomException;
import exceptions.NotFoundException;

public interface KorisnikService {
	TokenResponseDto register(KorisnikCreateDto korisnikCreateDto) throws DataIntegrityViolationException;
	
	KorisnikDto get(Long id) throws NotFoundException;
	
	KorisnikDto update(Long id, KorisnikUpdateDto korisnikUpdateDto) throws DataIntegrityViolationException, CustomException;
	
	void deleteById(Long id) throws EmptyResultDataAccessException;
	
	TokenResponseDto login(TokenRequestDto tokenRequestDto) throws NotFoundException;
	
	void refund(PovracajNovcaDto povracajNovcaDto);
}
