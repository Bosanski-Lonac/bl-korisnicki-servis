package service;

import dto.TokenRequestDto;
import dto.TokenResponseDto;

public interface AdminService {
	TokenResponseDto login(TokenRequestDto tokenRequestDto);
}
