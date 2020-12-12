package com.bosanskilonac.ks.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bosanskilonac.ks.service.KreditnaKarticaService;

import dto.KreditnaKarticaCUDto;
import dto.KreditnaKarticaDto;
import enums.Role;
import io.swagger.annotations.ApiOperation;
import security.CheckSecurity;

@RestController
@RequestMapping("/cc")
public class KreditnaKarticaController {
	private KreditnaKarticaService ccService;
	
	public KreditnaKarticaController(KreditnaKarticaService ccService) {
		this.ccService = ccService;
	}
	
	@ApiOperation(value = "Dodavanje kartice")
	@PostMapping
	@CheckSecurity(roles = {Role.ROLE_USER}, checkOwnership = false)
	public ResponseEntity<KreditnaKarticaDto> add(@RequestHeader("Authorization") String authorization, @RequestBody @Valid KreditnaKarticaCUDto kreditnaKarticaCreateDto) {
		return new ResponseEntity<>(ccService.add(authorization, kreditnaKarticaCreateDto), HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Prikazi sve kreditne kartice za trenutnog korisnika")
	@GetMapping
	@CheckSecurity(roles = {Role.ROLE_USER}, checkOwnership = false)
	public ResponseEntity<Page<KreditnaKarticaDto>> getAllCC(@RequestHeader("Authorization") String authorization,
            Pageable pageable) {
		return new ResponseEntity<>(ccService.findAll(authorization, pageable), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Brisanje kreditne kartice")
	@DeleteMapping("/{id}")
	@CheckSecurity(roles = {Role.ROLE_USER}, checkOwnership = false)
	public ResponseEntity<?> delete(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id) {
		ccService.deleteById(authorization, id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
