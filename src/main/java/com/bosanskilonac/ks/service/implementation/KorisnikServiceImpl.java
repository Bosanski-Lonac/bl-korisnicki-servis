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

import dto.KorisnikCreateDto;
import dto.KorisnikDto;
import dto.KorisnikUpdateDto;
import dto.PovracajNovcaDto;
import dto.RezervacijeKorisnikaDto;
import dto.TokenRequestDto;
import dto.TokenResponseDto;
import exceptions.CustomException;
import exceptions.ForbiddenException;
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
	public TokenResponseDto register(KorisnikCreateDto korisnikCreateDto) throws DataIntegrityViolationException {
		Korisnik korisnik = korisnikMapper.korisnikCreateDtoToKorisnik(korisnikCreateDto);
		korisnik = korisnikRepository.save(korisnik);
		final String email = korisnik.getEmail();
		Thread thread = new Thread() {
			public void run() {
				EmailSender.getInstance().sendEmail(email, "Potvrda o registraciji", "Uspešno ste se registrovali!");
			}
		};
		thread.start();
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
	public KorisnikDto update(Long id, KorisnikUpdateDto korisnikUpdateDto) throws DataIntegrityViolationException, CustomException {
		Korisnik korisnik = korisnikRepository
				.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Korisnik sa id-jem %s ne postoji.", id)));
		if(!korisnik.getSifra().equals(korisnikUpdateDto.getSifra())) {
			throw new ForbiddenException("Niste dobro ukucali trenutnu šifru.");
		}
		korisnik = korisnikMapper.korisnikUpdateDtoToKorisnik(korisnikUpdateDto, korisnik);
		korisnik = korisnikRepository.save(korisnik);
		final String email = korisnik.getEmail();
		Thread thread = new Thread() {
			public void run() {
				EmailSender.getInstance().sendEmail(email, "Potvrda o izmeni korisničkih informacija", "Uspešno ste promenili svoje korisničke informacije.");
			}
		};
		thread.start();
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
		for(Entry<Long, RezervacijeKorisnikaDto> rezervacije : povracajNovcaDto.getListaKorisnikCena().entrySet()) {
			Optional<Korisnik> korisnik = korisnikRepository.findById(rezervacije.getKey());
			if(korisnik.isPresent()) {
				korisnik.get().addMilje(-(rezervacije.getValue().getBrojRezervacija()
						* povracajNovcaDto.getLetDto().getMilje()));
				korisnikRepository.save(korisnik.get());
				final String email = korisnik.get().getEmail();
				Thread thread = new Thread() {
					public void run() {
						EmailSender.getInstance().sendEmail(email, "Obaveštenje o Vašem letu" + povracajNovcaDto.getLetDto().getPocetnaDestinacija() + "-" + povracajNovcaDto.getLetDto().getKrajnjaDestinacija(),
								"Vaš let je otkazan, i za vaših " + rezervacije.getValue().getBrojRezervacija().toString() + " rezervacija će se izvršiti povraćaj u iznosu od " + rezervacije.getValue().getCena().toString() + " RSD.");
					}
				};
				thread.start();
			}
		}
	}
}
