package service.implementation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dto.KorisnikCreateDto;
import dto.KorisnikDto;
import dto.KorisnikUpdateDto;
import helpers.NotFoundException;
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
	private JmsTemplate jmsTemplate;
	private String orderDestination;
	
	public KorisnikServiceImpl(TokenService tokenService, KorisnikRepository korisnikRepository, KorisnikMapper korisnikMapper, JmsTemplate jmsTemplate, @Value("${destination.createOrder}") String orderDestination) {
		this.tokenService=tokenService;
		this.korisnikRepository=korisnikRepository;
		this.korisnikMapper=korisnikMapper;
		this.jmsTemplate=jmsTemplate;
		this.orderDestination=orderDestination;
	}

	@Override
	public Page<KorisnikDto> findAll(Pageable pageable) {
		return korisnikRepository.findAll(pageable).map(korisnikMapper::korisnikToKorisnikDto);
	}

	@Override
	public KorisnikDto findById(String id) {
		return korisnikRepository.findById(id).map(korisnikMapper::korisnikToKorisnikDto).orElseThrow(() -> new NotFoundException(String.format("Korisnik sa id-jem %s ne postoji", id)));
	}

	@Override
	public KorisnikDto add(KorisnikCreateDto korisnikCreateDto) {
		return korisnikMapper.korisnikToKorisnikDto(korisnikRepository.save(korisnikMapper.korisnikCreateDtoToKorisnik(korisnikCreateDto)));
	}

	@Override
	public KorisnikDto update(String id, KorisnikUpdateDto korisnikUpdateDto) {
		Korisnik korisnik=korisnikRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Korisnik sa id-jem %s ne postoji", id)));
		korisnik.setBrojPasosa(korisnikUpdateDto.getBrojPasosa());
		korisnik.setIme(korisnikUpdateDto.getIme());
		korisnik.setPrezime(korisnikUpdateDto.getPrezime());
		korisnik.setEmail(korisnikUpdateDto.getEmail());
		korisnik.setSifra(korisnikUpdateDto.getSifra());
		return korisnikMapper.korisnikToKorisnikDto(korisnikRepository.save(korisnik));
	}

	@Override
	public void deleteById(String id) {
		korisnikRepository.deleteById(id);
	}
}
