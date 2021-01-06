package com.bosanskilonac.ks.service;

import dto.TokenRequestDto;
import dto.TokenResponseDto;
import exceptions.NotFoundException;

public interface AdminService {
	TokenResponseDto login(TokenRequestDto tokenRequestDto) throws NotFoundException;
}
