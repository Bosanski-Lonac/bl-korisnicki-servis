package mapper;

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
	
	public KreditnaKartica kreditnaKarticaDtoToKreditnaKartica(KreditnaKarticaDto kreditnaKarticaDto) {
		KreditnaKartica kreditnaKartica=new KreditnaKartica();
		kreditnaKartica.setBrojKartice(kreditnaKarticaDto.getBrojKartice());
		kreditnaKartica.setImeVlasnika(kreditnaKarticaDto.getImeVlasnika());
		kreditnaKartica.setPrezimeVlasnika(kreditnaKarticaDto.getPrezimeVlasnika());
		kreditnaKartica.setSigurnosniBroj(kreditnaKarticaDto.getSigurnosniBroj());
		return kreditnaKartica;
	}
}
