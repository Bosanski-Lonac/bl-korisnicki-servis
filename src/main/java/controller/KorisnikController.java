package controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.KorisnikCUDto;
import dto.KorisnikDto;
import dto.TokenRequestDto;
import dto.TokenResponseDto;
import io.swagger.annotations.ApiOperation;
import service.KorisnikService;

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
		return new ResponseEntity<>(korisnikService.register(korisnikCreateDto), HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Logovanje korisnika")
	@PostMapping("/login")
	public ResponseEntity<TokenResponseDto> loginKorisnik(@RequestBody @Valid TokenRequestDto tokenRequestDto) {
		return new ResponseEntity<>(korisnikService.login(tokenRequestDto), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Izmena profila korisnika")
	@PutMapping("/{id}")
    public ResponseEntity<KorisnikDto> update(@PathVariable("id") String id, @RequestBody @Valid KorisnikCUDto korisnikUpdateDto) {
        return new ResponseEntity<>(korisnikService.update(id, korisnikUpdateDto), HttpStatus.OK);
    }
	
	@ApiOperation(value = "Brisanje korisnika")
	@DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        korisnikService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
