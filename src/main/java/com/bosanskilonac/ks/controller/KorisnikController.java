package com.bosanskilonac.ks.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bosanskilonac.ks.service.KorisnikService;

import dto.KorisnikCUDto;
import dto.KorisnikDto;
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
	public ResponseEntity<KorisnikDto> register(@RequestBody @Valid KorisnikCUDto korisnikCreateDto){
		KorisnikDto korisnikDto = korisnikService.register(korisnikCreateDto);
		if(korisnikDto == null) {
			return new ResponseEntity<>(korisnikDto, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(korisnikDto, HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Logovanje korisnika")
	@PostMapping("/login")
	public ResponseEntity<TokenResponseDto> loginKorisnik(@RequestBody @Valid TokenRequestDto tokenRequestDto) {
		return new ResponseEntity<>(korisnikService.login(tokenRequestDto), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Izmena profila korisnika")
	@PutMapping("/{id}")
	@CheckSecurity(roles = {Role.ROLE_USER, Role.ROLE_ADMIN})
    public ResponseEntity<KorisnikDto> update(@RequestHeader("Authorization") String authorization, @PathVariable("id") String id, @RequestBody @Valid KorisnikCUDto korisnikUpdateDto) {
		KorisnikDto korisnikDto = korisnikService.update(id, korisnikUpdateDto);
		if(korisnikDto == null) {
			return new ResponseEntity<>(korisnikDto, HttpStatus.CONFLICT);
		}
        return new ResponseEntity<>(korisnikDto, HttpStatus.OK);
    }
	
	@ApiOperation(value = "Brisanje korisnika")
	@DeleteMapping("/{id}")
	@CheckSecurity(roles = {Role.ROLE_USER, Role.ROLE_ADMIN})
    public ResponseEntity<?> delete(@RequestHeader("Authorization") String authorization, @PathVariable("id") String id) {
        korisnikService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
