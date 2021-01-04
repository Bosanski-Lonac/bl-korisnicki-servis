package com.bosanskilonac.ks.service.implementation;

import java.util.Map.Entry;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.bosanskilonac.ks.mapper.KorisnikMapper;
import com.bosanskilonac.ks.model.Korisnik;
import com.bosanskilonac.ks.repository.KorisnikRepository;
import com.bosanskilonac.ks.service.KorisnikService;

import dto.KorisnikCUDto;
import dto.KorisnikDto;
import dto.PovracajNovcaDto;
import dto.RezervacijeDto;
import dto.TokenRequestDto;
import dto.TokenResponseDto;
import exceptions.CustomException;
import exceptions.NotFoundException;
import security.TokenService;
import utility.EmailSender;

@Service
public class KorisnikServiceImpl implements KorisnikService {
	private TokenService tokenService;
	private KorisnikRepository korisnikRepository;
	private KorisnikMapper korisnikMapper;
	
	public KorisnikServiceImpl(TokenService tokenService, KorisnikRepository korisnikRepository, KorisnikMapper korisnikMapper) {
		this.tokenService = tokenService;
		this.korisnikRepository = korisnikRepository;
		this.korisnikMapper = korisnikMapper;
	}

	@Override
	@Transactional(rollbackOn = DataIntegrityViolationException.class)
	public TokenResponseDto register(KorisnikCUDto korisnikCreateDto) throws DataIntegrityViolationException {
		Korisnik korisnik = korisnikMapper.korisnikCreateDtoToKorisnik(korisnikCreateDto);
		korisnik = korisnikRepository.save(korisnik);
		//EmailSender.getInstance().sendEmail(korisnik.getEmail(), "Potvrda o registraciji", "Uspešno ste se registrovali!");
		return tokenService.createToken(korisnikMapper.korisnikToKorisnikDto(korisnik));
	}
	
	@Override
	public KorisnikDto get(Long id) throws NotFoundException {
		Korisnik korisnik = korisnikRepository
				.findById(id)
				.orElseThrow(() -> new NotFoundException("Korisnik nije nađen."));
		KorisnikDto korisnikDto = korisnikMapper.korisnikToKorisnikDto(korisnik);
		return korisnikDto;
	}

	@Override
	@Transactional(rollbackOn = DataIntegrityViolationException.class)
	public KorisnikDto update(Long id, KorisnikCUDto korisnikUpdateDto) throws DataIntegrityViolationException, CustomException {
		Korisnik korisnik = korisnikRepository
				.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Korisnik sa id-jem %s ne postoji.", id)));
		korisnik = korisnikMapper.korisnikUpdateDtoToKorisnik(korisnikUpdateDto, korisnik);
		korisnik = korisnikRepository.save(korisnik);
		//EmailSender.getInstance().sendEmail(korisnik.getEmail(), "Potvrda o promeni email adrese", "Uspešno ste promenili svoju email adresu.");
		return korisnikMapper.korisnikToKorisnikDto(korisnik);
	}

	@Override
	public void deleteById(Long id) {
		korisnikRepository.deleteById(id);
	}
	
	@Override
	public TokenResponseDto login(TokenRequestDto tokenRequestDto) throws NotFoundException {
		Korisnik korisnik = korisnikRepository
				.findKorisnikByEmailAndSifra(tokenRequestDto.getUsername(), tokenRequestDto.getPassword())
				.orElseThrow(() -> new NotFoundException("Prosleđene informacije za prijavu nisu tačne. Pokušajte ponovo."));
		return tokenService.createToken(korisnikMapper.korisnikToKorisnikDto(korisnik));
	}

	@Override
	public void refund(PovracajNovcaDto povracajNovcaDto) {
		for(Entry<Long, RezervacijeDto> rezervacije : povracajNovcaDto.getListaKorisnikCena().entrySet()) {
			Optional<Korisnik> korisnik = korisnikRepository.findById(rezervacije.getKey());
			if(korisnik.isPresent()) {
				korisnik.get().addMilje(-(rezervacije.getValue().getBrojRezervacija()
						* povracajNovcaDto.getLetDto().getMilje()));
				korisnikRepository.save(korisnik.get());
				EmailSender.getInstance().sendEmail(korisnik.get().getEmail(), "Obaveštenje o Vašem letu" + povracajNovcaDto.getLetDto().getPocetnaDestinacija() + "-" + povracajNovcaDto.getLetDto().getKrajnjaDestinacija(),
						"Vaš let je otkazan, i za vaših " + rezervacije.getValue().getBrojRezervacija().toString() + " rezervacija će se izvršiti povraćaj u iznosu od " + rezervacije.getValue().getCena().toString() + " RSD.");
			}
		}
	}
}
