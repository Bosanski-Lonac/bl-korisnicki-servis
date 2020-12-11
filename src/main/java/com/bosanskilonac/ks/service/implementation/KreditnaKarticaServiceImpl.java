package com.bosanskilonac.ks.service.implementation;

import com.bosanskilonac.ks.mapper.KreditnaKarticaMapper;
import com.bosanskilonac.ks.model.KreditnaKartica;
import com.bosanskilonac.ks.repository.KreditnaKarticaRepository;
import com.bosanskilonac.ks.service.KreditnaKarticaService;

import dto.KreditnaKarticaCUDto;
import dto.KreditnaKarticaDto;

public class KreditnaKarticaServiceImpl implements KreditnaKarticaService {
	private KreditnaKarticaRepository ccRepository;
	private KreditnaKarticaMapper ccMapper;

	public KreditnaKarticaServiceImpl(KreditnaKarticaRepository ccRepository, KreditnaKarticaMapper ccMapper) {
		this.ccRepository = ccRepository;
		this.ccMapper = ccMapper;
	}

	@Override
	public KreditnaKarticaDto add(KreditnaKarticaCUDto kreditnaKarticaCreateDto) {
		KreditnaKartica kreditnaKartica = ccMapper.kreditnaKarticaDtoToKreditnaKartica(kreditnaKarticaCreateDto);
		kreditnaKartica = ccRepository.save(kreditnaKartica);
		return ccMapper.kreditnaKarticaToKreditnaKarticaDto(kreditnaKartica);
	}

	@Override
	public void deleteById(Long id) {
		ccRepository.deleteById(id);
	}

}
