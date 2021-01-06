package com.bosanskilonac.ks.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bosanskilonac.ks.service.KorisnikService;

import dto.KorisnikCreateDto;
import dto.KorisnikDto;
import dto.KorisnikUpdateDto;
import dto.TokenRequestDto;
import dto.TokenResponseDto;
import enums.Role;
import io.swagger.annotations.ApiOperation;
import security.CheckSecurity;

@RestController
@RequestMapping("/korisnik")
public class KorisnikController {
	private KorisnikService korisnikService;
	
	public KorisnikController(KorisnikService korisnikService) {
		this.korisnikService = korisnikService;
	}
	
	@ApiOperation(value = "Registracija korisnika")
	@PostMapping
	public ResponseEntity<TokenResponseDto> register(@RequestBody @Valid KorisnikCreateDto korisnikCreateDto) {
		return new ResponseEntity<>(korisnikService.register(korisnikCreateDto), HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Prijava korisnika")
	@PostMapping("/login")
	public ResponseEntity<TokenResponseDto> loginKorisnik(@RequestBody @Valid TokenRequestDto tokenRequestDto) {
		return new ResponseEntity<>(korisnikService.login(tokenRequestDto), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Prikaz detalja korisnika")
	@GetMapping("/{id}")
	@CheckSecurity(roles = {Role.ROLE_USER, Role.ROLE_ADMIN})
	public ResponseEntity<KorisnikDto> get(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id) {
        return new ResponseEntity<>(korisnikService.get(id), HttpStatus.OK);
    }
	
	@ApiOperation(value = "Izmena profila korisnika")
	@PutMapping("/{id}")
	@CheckSecurity(roles = {Role.ROLE_USER, Role.ROLE_ADMIN})
    public ResponseEntity<KorisnikDto> update(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id,
    		@RequestBody @Valid KorisnikUpdateDto korisnikUpdateDto) {
        return new ResponseEntity<>(korisnikService.update(id, korisnikUpdateDto), HttpStatus.OK);
    }
	
	@ApiOperation(value = "Brisanje korisnika")
	@DeleteMapping("/{id}")
	@CheckSecurity(roles = {Role.ROLE_USER, Role.ROLE_ADMIN})
    public ResponseEntity<?> delete(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id) {
        korisnikService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
