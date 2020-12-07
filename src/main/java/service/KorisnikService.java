package service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dto.KorisnikCreateDto;
import dto.KorisnikDto;
import dto.KorisnikUpdateDto;

public interface KorisnikService {
	Page<KorisnikDto> findAll(Pageable pageable);
	KorisnikDto findById(String id);
	KorisnikDto register(KorisnikCreateDto korisnikCreateDto);
	KorisnikDto update(String id, KorisnikUpdateDto korisnikUpdateDto);
	void deleteById(String id);
}
