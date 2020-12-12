package com.bosanskilonac.ks.service;

import javax.transaction.Transactional;

import dto.TokenRequestDto;
import dto.TokenResponseDto;
import exceptions.NotFoundException;

@Transactional
public interface AdminService {
	TokenResponseDto login(TokenRequestDto tokenRequestDto) throws NotFoundException;
}
