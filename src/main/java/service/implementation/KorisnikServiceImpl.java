package service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dto.KorisnikCUDto;
import dto.KorisnikDto;
import dto.TokenRequestDto;
import dto.TokenResponseDto;
import enums.Role;
import helpers.NotFoundException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import mapper.KorisnikMapper;
import model.Korisnik;
import repository.KorisnikRepository;
import service.KorisnikService;
import service.TokenService;

@Service
@Transactional
public class KorisnikServiceImpl implements KorisnikService {
	private TokenService tokenService;
	private KorisnikRepository korisnikRepository;
	private KorisnikMapper korisnikMapper;
	
	public KorisnikServiceImpl(TokenService tokenService, KorisnikRepository korisnikRepository, KorisnikMapper korisnikMapper) {
		this.tokenService=tokenService;
		this.korisnikRepository=korisnikRepository;
		this.korisnikMapper=korisnikMapper;
	}

	@Override
	public KorisnikDto register(KorisnikCUDto korisnikCreateDto) {
		Korisnik korisnik = korisnikMapper.korisnikCreateDtoToKorisnik(korisnikCreateDto);
		korisnikRepository.save(korisnik);
		return korisnikMapper.korisnikToKorisnikDto(korisnik);
	}

	@Override
	public KorisnikDto update(String id, KorisnikCUDto korisnikUpdateDto) {
		Korisnik korisnik=korisnikRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Korisnik sa id-jem %s ne postoji", id)));
		korisnik.setEmail(korisnikUpdateDto.getEmail());
		korisnik.setSifra(korisnikUpdateDto.getSifra());
		korisnik.setIme(korisnikUpdateDto.getIme());
		korisnik.setPrezime(korisnikUpdateDto.getPrezime());
		korisnik.setBrojPasosa(korisnikUpdateDto.getBrojPasosa());
		return korisnikMapper.korisnikToKorisnikDto(korisnikRepository.save(korisnik));
	}

	@Override
	public void deleteById(String id) {
		korisnikRepository.deleteById(id);
	}
	
	@Override
	public TokenResponseDto login(TokenRequestDto tokenRequestDto) {
		Korisnik korisnik = korisnikRepository
				.findUserByEmailAndPassword(tokenRequestDto.getUsername(), tokenRequestDto.getPassword())
				.orElseThrow(() -> new NotFoundException("Login information was incorrect, try again."));
		Claims claims = Jwts.claims();
		claims.put("id", korisnik.getEmail());
		claims.put("role", Role.ROLE_USER.toString());
		return new TokenResponseDto(tokenService.generate(claims));
	}
}
