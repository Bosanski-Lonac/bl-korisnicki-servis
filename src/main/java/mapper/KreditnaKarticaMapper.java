package mapper;

import dto.KreditnaKarticaCreateDto;
import dto.KreditnaKarticaDto;
import model.KreditnaKartica;

public class KreditnaKarticaMapper {
	public KreditnaKarticaDto kreditnaKarticaToKreditnaKarticaDto(KreditnaKartica kreditnaKartica) {
		KreditnaKarticaDto kreditnaKarticaDto=new KreditnaKarticaDto();
		kreditnaKarticaDto.setBrojKartice(kreditnaKartica.getBrojKartice());
		kreditnaKarticaDto.setImeVlasnika(kreditnaKartica.getImeVlasnika());
		kreditnaKarticaDto.setPrezimeVlasnika(kreditnaKartica.getPrezimeVlasnika());
		kreditnaKarticaDto.setSigurnosniBroj(kreditnaKartica.getSigurnosniBroj());
		return kreditnaKarticaDto;
	}
	
	public KreditnaKartica kreditnaKarticaDtoToKreditnaKartica(KreditnaKarticaCreateDto kreditnaKarticaCreateDto) {
		KreditnaKartica kreditnaKartica=new KreditnaKartica();
		kreditnaKartica.setBrojKartice(kreditnaKarticaCreateDto.getBrojKartice());
		kreditnaKartica.setImeVlasnika(kreditnaKarticaCreateDto.getImeVlasnika());
		kreditnaKartica.setPrezimeVlasnika(kreditnaKarticaCreateDto.getPrezimeVlasnika());
		kreditnaKartica.setSigurnosniBroj(kreditnaKarticaCreateDto.getSigurnosniBroj());
		return kreditnaKartica;
	}
}
