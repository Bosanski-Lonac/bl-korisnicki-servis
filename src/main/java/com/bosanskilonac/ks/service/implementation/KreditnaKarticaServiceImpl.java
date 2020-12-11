package com.bosanskilonac.ks.service.implementation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import security.TokenService;

@Service
public class KreditnaKarticaServiceImpl implements KreditnaKarticaService {
	private TokenService tokenService;
	private KreditnaKarticaRepository ccRepository;
	private KreditnaKarticaMapper ccMapper;
	private KorisnikRepository korisnikRepository;

	public KreditnaKarticaServiceImpl(TokenService tokenService, KreditnaKarticaRepository ccRepository, KreditnaKarticaMapper ccMapper, KorisnikRepository korisnikRepository) {
		this.tokenService = tokenService;
		this.ccRepository = ccRepository;
		this.ccMapper = ccMapper;
		this.korisnikRepository = korisnikRepository;
	}

	@Override
	public KreditnaKarticaDto add(String authorization, KreditnaKarticaCUDto kreditnaKarticaCreateDto) {
		Long korisnikId = tokenService.getIdFromToken(authorization);
		Korisnik korisnik = korisnikRepository
				.findById(korisnikId)
				.orElseThrow(() -> new NotFoundException("Couldn't find user attempting to add credit card"));
		KreditnaKartica kreditnaKartica = ccMapper.kreditnaKarticaDtoToKreditnaKartica(kreditnaKarticaCreateDto, korisnik);
		kreditnaKartica = ccRepository.save(kreditnaKartica);
		return ccMapper.kreditnaKarticaToKreditnaKarticaDto(kreditnaKartica);
	}

	@Override
	public void deleteById(Long id) {
		ccRepository.deleteById(id);
	}

	@Override
	public Page<KreditnaKarticaDto> findAll(String authorization, Pageable pageable) {
		Long korisnikId = tokenService.getIdFromToken(authorization);
		return ccRepository.findByKorisnikId(korisnikId, pageable)
				.map(ccMapper::kreditnaKarticaToKreditnaKarticaDto);
	}

}
