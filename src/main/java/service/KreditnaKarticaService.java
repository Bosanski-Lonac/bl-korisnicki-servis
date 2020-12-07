package service;

import dto.KreditnaKarticaCreateDto;
import dto.KreditnaKarticaDto;
import dto.KreditnaKarticaUpdateDto;

public interface KreditnaKarticaService {
	KreditnaKarticaDto findById(Long id);
	KreditnaKarticaDto add(KreditnaKarticaCreateDto korisnikCreateDto);
	KreditnaKarticaDto update(Long id, KreditnaKarticaUpdateDto korisnikUpdateDto);
	void deleteById(Long id);
}
