package com.bosanskilonac.ks.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bosanskilonac.ks.service.AdminService;

import dto.TokenRequestDto;
import dto.TokenResponseDto;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/admin")
public class AdminController {
	private AdminService adminService;

	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}

	@ApiOperation(value = "Logovanje admina")
	@PostMapping
	public ResponseEntity<TokenResponseDto> loginKorisnik(@RequestBody @Valid TokenRequestDto tokenRequestDto) {
		return new ResponseEntity<>(adminService.login(tokenRequestDto), HttpStatus.OK);
	}
}
