package service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dto.TokenRequestDto;
import dto.TokenResponseDto;
import enums.Role;
import helpers.NotFoundException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import model.Admin;
import repository.AdminRepository;
import service.AdminService;
import service.TokenService;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
	private TokenService tokenService;
	private AdminRepository adminRepository;
	
	public AdminServiceImpl(TokenService tokenService, AdminRepository adminRepository) {
		this.tokenService = tokenService;
		this.adminRepository = adminRepository;
	}

	@Override
	public TokenResponseDto login(TokenRequestDto tokenRequestDto) {
		Admin admin = adminRepository
				.findAdminByUsernameAndPassword(tokenRequestDto.getUsername(), tokenRequestDto.getPassword())
				.orElseThrow(() -> new NotFoundException("Login information was incorrect, try again."));
		Claims claims = Jwts.claims();
		claims.put("id", admin.getUsername());
		claims.put("role", Role.ROLE_ADMIN.toString());
		return new TokenResponseDto(tokenService.generate(claims));
	}

}
