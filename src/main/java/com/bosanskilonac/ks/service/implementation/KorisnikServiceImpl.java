package com.bosanskilonac.ks.service.implementation;

import org.springframework.stereotype.Service;

import com.bosanskilonac.ks.mapper.KorisnikMapper;
import com.bosanskilonac.ks.model.Korisnik;
import com.bosanskilonac.ks.repository.KorisnikRepository;
import com.bosanskilonac.ks.service.KorisnikService;

import dto.KorisnikCUDto;
import dto.KorisnikDto;
import dto.TokenRequestDto;
import dto.TokenResponseDto;
import enums.Role;
import exceptions.NotFoundException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
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
	public KorisnikDto register(KorisnikCUDto korisnikCreateDto) {
		Korisnik korisnik = korisnikMapper.korisnikCreateDtoToKorisnik(korisnikCreateDto);
		korisnik = korisnikRepository.save(korisnik);
		return korisnikMapper.korisnikToKorisnikDto(korisnik);
	}

	@Override
	public KorisnikDto update(Long id, KorisnikCUDto korisnikUpdateDto) {
		Korisnik korisnik = korisnikRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Korisnik sa id-jem %s ne postoji", id)));
		korisnik = korisnikMapper.korisnikUpdateDtoToKorisnik(korisnikUpdateDto, korisnik);
		korisnik = korisnikRepository.save(korisnik);
		return korisnikMapper.korisnikToKorisnikDto(korisnik);
	}

	@Override
	public void deleteById(Long id) {
		korisnikRepository.deleteById(id);
	}
	
	@Override
	public TokenResponseDto login(TokenRequestDto tokenRequestDto) {
		Korisnik korisnik = korisnikRepository
				.findKorisnikByEmailAndSifra(tokenRequestDto.getUsername(), tokenRequestDto.getPassword())
				.orElseThrow(() -> new NotFoundException("Login information was incorrect, try again."));
		Claims claims = Jwts.claims();
		claims.put("id", korisnik.getId());
		claims.put("role", Role.ROLE_USER.toString());
		return new TokenResponseDto(tokenService.generate(claims));
	}
	
	/*@Override
	public Long getIdKorisnika(String authorization) {
		Claims claims = tokenService.parseToken(authorization);
		Long id = claims.get("id", Long.class);
		return id;
	}*/
}
