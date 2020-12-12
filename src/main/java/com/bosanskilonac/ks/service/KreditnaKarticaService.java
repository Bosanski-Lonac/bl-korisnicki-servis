package com.bosanskilonac.ks.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dto.KreditnaKarticaCUDto;
import dto.KreditnaKarticaDto;

public interface KreditnaKarticaService {
	KreditnaKarticaDto add(String authorization, KreditnaKarticaCUDto kreditnaKarticaCreateDto);
	void deleteById(String authorization, Long id);
	Page<KreditnaKarticaDto> findAll(String authorization, Pageable pageable);
}
