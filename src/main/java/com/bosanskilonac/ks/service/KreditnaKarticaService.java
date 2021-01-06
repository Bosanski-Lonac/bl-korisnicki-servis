package com.bosanskilonac.ks.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;

import dto.KartaCreateDto;
import dto.KartaKSDto;
import dto.KreditnaKarticaCUDto;
import dto.KreditnaKarticaDto;
import exceptions.NotFoundException;

public interface KreditnaKarticaService {
	KreditnaKarticaDto add(Long id, KreditnaKarticaCUDto kreditnaKarticaCreateDto) throws NotFoundException;
	Page<KreditnaKarticaDto> findAll(Long id, Integer brojStranice) throws EmptyResultDataAccessException;
	KartaCreateDto reserve(KartaKSDto kartaKSDto) throws NotFoundException;
	void deleteById(Long ccId) throws EmptyResultDataAccessException;
}
