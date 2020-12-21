package com.bosanskilonac.ks.service.implementation;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bosanskilonac.ks.mapper.KreditnaKarticaMapper;
import com.bosanskilonac.ks.model.Korisnik;
import com.bosanskilonac.ks.model.KreditnaKartica;
import com.bosanskilonac.ks.repository.KorisnikRepository;
import com.bosanskilonac.ks.repository.KreditnaKarticaRepository;
import com.bosanskilonac.ks.service.KreditnaKarticaService;

import dto.KreditnaKarticaCUDto;
import dto.KreditnaKarticaDto;
import exceptions.NotFoundException;

@Service
public class KreditnaKarticaServiceImpl implements KreditnaKarticaService {
	private final int velicinaStranice = 20;
	
	private KreditnaKarticaRepository ccRepository;
	private KreditnaKarticaMapper ccMapper;
	private KorisnikRepository korisnikRepository;

	public KreditnaKarticaServiceImpl(KreditnaKarticaRepository ccRepository, KreditnaKarticaMapper ccMapper, KorisnikRepository korisnikRepository) {
		this.ccRepository = ccRepository;
		this.ccMapper = ccMapper;
		this.korisnikRepository = korisnikRepository;
	}

	@Override
	public KreditnaKarticaDto add(Long id, KreditnaKarticaCUDto kreditnaKarticaCreateDto) throws NotFoundException {
		Korisnik korisnik = korisnikRepository
				.findById(id)
				.orElseThrow(() -> new NotFoundException("Trenutni korisnik ne postoji."));
		KreditnaKartica kreditnaKartica = ccMapper.kreditnaKarticaDtoToKreditnaKartica(kreditnaKarticaCreateDto, korisnik);
		kreditnaKartica = ccRepository.save(kreditnaKartica);
		return ccMapper.kreditnaKarticaToKreditnaKarticaDto(kreditnaKartica);
	}
	
	@Override
	public Page<KreditnaKarticaDto> findAll(Long id, Integer brojStranice) throws EmptyResultDataAccessException {
		return ccRepository.findByKorisnikId(id, PageRequest.of(brojStranice, velicinaStranice))
				.map(ccMapper::kreditnaKarticaToKreditnaKarticaDto);
	}

	@Override
	public void deleteById(Long ccId) throws EmptyResultDataAccessException {
		ccRepository.deleteById(ccId);
	}

}
