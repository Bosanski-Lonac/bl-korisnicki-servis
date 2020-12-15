package com.bosanskilonac.ks.service.implementation;

import javax.transaction.Transactional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.bosanskilonac.ks.mapper.KorisnikMapper;
import com.bosanskilonac.ks.model.Korisnik;
import com.bosanskilonac.ks.repository.KorisnikRepository;
import com.bosanskilonac.ks.service.KorisnikService;

import dto.KorisnikCUDto;
import dto.KorisnikDto;
import dto.TokenRequestDto;
import dto.TokenResponseDto;
import exceptions.CustomException;
import exceptions.NotFoundException;
import security.EmailSender;
import security.TokenService;

@Service
public class KorisnikServiceImpl implements KorisnikService {
	private TokenService tokenService;
	private KorisnikRepository korisnikRepository;
	private KorisnikMapper korisnikMapper;
	
	public KorisnikServiceImpl(TokenService tokenService, KorisnikRepository korisnikRepository, KorisnikMapper korisnikMapper) {
		this.tokenService = tokenService;
		this.korisnikRepository = korisnikRepository;
		this.korisnikMapper = korisnikMapper;
	}

	@Override
	@Transactional(rollbackOn = DataIntegrityViolationException.class)
	public TokenResponseDto register(KorisnikCUDto korisnikCreateDto) throws DataIntegrityViolationException {
		Korisnik korisnik = korisnikMapper.korisnikCreateDtoToKorisnik(korisnikCreateDto);
		korisnik = korisnikRepository.save(korisnik);
		//EmailSender.getInstance().sendEmail(korisnikDto.getEmail(), "Potvrda o registraciji", "Uspešno ste se registrovali!");
		return tokenService.createToken(korisnikMapper.korisnikToKorisnikDto(korisnik));
	}
	
	@Override
	public KorisnikDto get(Long id) throws NotFoundException {
		Korisnik korisnik = korisnikRepository
				.findById(id)
				.orElseThrow(() -> new NotFoundException("Korisnik nije nađen."));
		KorisnikDto korisnikDto = korisnikMapper.korisnikToKorisnikDto(korisnik);
		return korisnikDto;
	}

	@Override
	@Transactional(rollbackOn = DataIntegrityViolationException.class)
	public KorisnikDto update(Long id, KorisnikCUDto korisnikUpdateDto) throws DataIntegrityViolationException, CustomException {
		Korisnik korisnik = korisnikRepository
				.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Korisnik sa id-jem %s ne postoji.", id)));
		korisnik = korisnikMapper.korisnikUpdateDtoToKorisnik(korisnikUpdateDto, korisnik);
		korisnik = korisnikRepository.save(korisnik);
		return korisnikMapper.korisnikToKorisnikDto(korisnik);
	}

	@Override
	public void deleteById(Long id) {
		korisnikRepository.deleteById(id);
	}
	
	@Override
	public TokenResponseDto login(TokenRequestDto tokenRequestDto) throws NotFoundException {
		Korisnik korisnik = korisnikRepository
				.findKorisnikByEmailAndSifra(tokenRequestDto.getUsername(), tokenRequestDto.getPassword())
				.orElseThrow(() -> new NotFoundException("Prosleđene informacije za prijavu nisu tačne. Pokušajte ponovo."));
		return tokenService.createToken(korisnikMapper.korisnikToKorisnikDto(korisnik));
	}
}
