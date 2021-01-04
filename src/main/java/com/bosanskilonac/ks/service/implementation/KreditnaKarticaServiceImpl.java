package com.bosanskilonac.ks.service.implementation;

import java.math.BigDecimal;

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

import dto.KartaCreateDto;
import dto.KartaKSDto;
import dto.KreditnaKarticaCUDto;
import dto.KreditnaKarticaDto;
import enums.Rank;
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
	public KartaCreateDto reserve(KartaKSDto kartaKSDto) throws NotFoundException {
		KreditnaKartica kreditnaKartica = ccRepository
				.findById(kartaKSDto.getKreditnaKarticaId())
				.orElseThrow(() -> new NotFoundException("Tražena kreditna kartica ne postoji."));
		KartaCreateDto kartaCreateDto = new KartaCreateDto();
		kartaCreateDto.setKorisnikId(kreditnaKartica.getKorisnik().getId());
		Integer discount = Rank.getRankForMilje(kreditnaKartica.getKorisnik().getMilje()).getPopust();
		kartaCreateDto.setCena(kartaCreateDto.getCena().divide(BigDecimal.valueOf(100)).
				multiply(BigDecimal.valueOf(100 - discount)));
		kreditnaKartica.getKorisnik().addMilje(kartaKSDto.getMilje());
		return kartaCreateDto;
	}

	@Override
	public void deleteById(Long ccId) throws EmptyResultDataAccessException {
		ccRepository.deleteById(ccId);
	}

}
