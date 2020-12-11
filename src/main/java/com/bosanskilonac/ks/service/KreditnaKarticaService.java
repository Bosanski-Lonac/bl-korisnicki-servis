package com.bosanskilonac.ks.service;

import dto.KreditnaKarticaCUDto;
import dto.KreditnaKarticaDto;

public interface KreditnaKarticaService {
	KreditnaKarticaDto add(KreditnaKarticaCUDto kreditnaKarticaCreateDto);
	void deleteById(Long id);
}
