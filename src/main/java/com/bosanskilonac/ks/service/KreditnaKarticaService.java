package com.bosanskilonac.ks.service;

import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dto.KreditnaKarticaCUDto;
import dto.KreditnaKarticaDto;
import exceptions.NotFoundException;

@Transactional
public interface KreditnaKarticaService {
	KreditnaKarticaDto add(Long id, KreditnaKarticaCUDto kreditnaKarticaCreateDto) throws NotFoundException;
	void deleteById(Long ccId) throws EmptyResultDataAccessException;
	Page<KreditnaKarticaDto> findAll(Long id, Pageable pageable) throws EmptyResultDataAccessException;
}
