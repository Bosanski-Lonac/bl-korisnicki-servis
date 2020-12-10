package mapper;

import dto.KreditnaKarticaCUDto;
import dto.KreditnaKarticaDto;
import helpers.NotFoundException;
import model.Korisnik;
import model.KreditnaKartica;
import repository.KorisnikRepository;

public class KreditnaKarticaMapper {
	private KorisnikRepository korisnikRepository;
	
	public KreditnaKarticaDto kreditnaKarticaToKreditnaKarticaDto(KreditnaKartica kreditnaKartica) {
		KreditnaKarticaDto kreditnaKarticaDto=new KreditnaKarticaDto();
		kreditnaKarticaDto.setKrajKartice(kreditnaKartica.getBrojKartice());
		kreditnaKarticaDto.setImeVlasnika(kreditnaKartica.getImeVlasnika());
		kreditnaKarticaDto.setPrezimeVlasnika(kreditnaKartica.getPrezimeVlasnika());
		return kreditnaKarticaDto;
	}
	
	public KreditnaKartica kreditnaKarticaDtoToKreditnaKartica(KreditnaKarticaCUDto kreditnaKarticaCreateDto) {
		KreditnaKartica kreditnaKartica=new KreditnaKartica();
		Korisnik korisnik = korisnikRepository
				.findById(kreditnaKarticaCreateDto.getKorisnikEmail())
				.orElseThrow(() -> new NotFoundException("User to add credit card to was not found."));
		kreditnaKartica.setBrojKartice(kreditnaKarticaCreateDto.getBrojKartice());
		kreditnaKartica.setImeVlasnika(kreditnaKarticaCreateDto.getImeVlasnika());
		kreditnaKartica.setPrezimeVlasnika(kreditnaKarticaCreateDto.getPrezimeVlasnika());
		kreditnaKartica.setSigurnosniBroj(kreditnaKarticaCreateDto.getSigurnosniBroj());
		kreditnaKartica.setKorisnik(korisnik);
		return kreditnaKartica;
	}
}
