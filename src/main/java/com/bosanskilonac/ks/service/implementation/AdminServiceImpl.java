package com.bosanskilonac.ks.service.implementation;

import org.springframework.stereotype.Service;

import com.bosanskilonac.ks.mapper.KorisnikMapper;
import com.bosanskilonac.ks.model.Admin;
import com.bosanskilonac.ks.repository.AdminRepository;
import com.bosanskilonac.ks.service.AdminService;

import dto.TokenRequestDto;
import dto.TokenResponseDto;
import exceptions.NotFoundException;
import security.TokenService;

@Service
public class AdminServiceImpl implements AdminService {
	private TokenService tokenService;
	private AdminRepository adminRepository;
	private KorisnikMapper korisnikMapper;
	
	public AdminServiceImpl(TokenService tokenService, AdminRepository adminRepository, KorisnikMapper korisnikMapper) {
		this.tokenService = tokenService;
		this.adminRepository = adminRepository;
		this.korisnikMapper = korisnikMapper;
	}

	@Override
	public TokenResponseDto login(TokenRequestDto tokenRequestDto) throws NotFoundException {
		Admin admin = adminRepository
				.findAdminByUsernameAndSifra(tokenRequestDto.getUsername(), tokenRequestDto.getPassword())
				.orElseThrow(() -> new NotFoundException("Prosleđene informacije za prijavu nisu tačne. Pokušajte ponovo."));
		return tokenService.createToken(korisnikMapper.adminToKorisnikDto(admin));
	}

}
