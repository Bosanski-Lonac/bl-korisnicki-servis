package com.bosanskilonac.ks.service.implementation;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.bosanskilonac.ks.model.Admin;
import com.bosanskilonac.ks.repository.AdminRepository;
import com.bosanskilonac.ks.service.AdminService;

import dto.TokenRequestDto;
import dto.TokenResponseDto;
import enums.Role;
import exceptions.NotFoundException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import security.TokenService;

@Service
public class AdminServiceImpl implements AdminService {
	private TokenService tokenService;
	private AdminRepository adminRepository;
	
	public AdminServiceImpl(TokenService tokenService, AdminRepository adminRepository) {
		this.tokenService = tokenService;
		this.adminRepository = adminRepository;
	}

	@Override
	public TokenResponseDto login(TokenRequestDto tokenRequestDto) throws NotFoundException {
		Admin admin = adminRepository
				.findAdminByUsernameAndSifra(tokenRequestDto.getUsername(), tokenRequestDto.getPassword())
				.orElseThrow(() -> new NotFoundException("Login information was incorrect, try again."));
		Claims claims = Jwts.claims();
		claims.put("id", admin.getUsername());
		claims.put("role", Role.ROLE_ADMIN.toString());
		return new TokenResponseDto(tokenService.generate(claims));
	}

}
