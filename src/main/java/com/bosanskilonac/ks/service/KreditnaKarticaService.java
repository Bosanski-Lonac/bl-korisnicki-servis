package com.bosanskilonac.ks.service;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dto.KreditnaKarticaCUDto;
import dto.KreditnaKarticaDto;
import exceptions.CustomException;
import exceptions.NotFoundException;

@Transactional
public interface KreditnaKarticaService {
	KreditnaKarticaDto add(String authorization, KreditnaKarticaCUDto kreditnaKarticaCreateDto) throws NotFoundException;
	void deleteById(String authorization, Long id) throws CustomException;
	Page<KreditnaKarticaDto> findAll(String authorization, Pageable pageable);
}
