package com.bosanskilonac.ks.service;

import javax.transaction.Transactional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import dto.KorisnikCUDto;
import dto.KorisnikDto;
import dto.TokenRequestDto;
import dto.TokenResponseDto;
import exceptions.CustomException;
import exceptions.NotFoundException;

@Transactional
public interface KorisnikService {
	KorisnikDto register(KorisnikCUDto korisnikCreateDto) throws DataIntegrityViolationException;
	
	KorisnikDto update(Long id, KorisnikCUDto korisnikUpdateDto) throws DataIntegrityViolationException, CustomException;
	
	void deleteById(Long id) throws EmptyResultDataAccessException;
	
	TokenResponseDto login(TokenRequestDto tokenRequestDto) throws NotFoundException;
}
