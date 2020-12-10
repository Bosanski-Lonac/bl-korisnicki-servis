package service;

import dto.KreditnaKarticaCUDto;
import dto.KreditnaKarticaDto;

public interface KreditnaKarticaService {
	KreditnaKarticaDto add(KreditnaKarticaCUDto korisnikCreateDto);
	void deleteById(Long id);
}
