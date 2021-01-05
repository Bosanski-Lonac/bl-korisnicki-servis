package com.bosanskilonac.ks.runner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.bosanskilonac.ks.model.Admin;
import com.bosanskilonac.ks.model.Korisnik;
import com.bosanskilonac.ks.model.KreditnaKartica;
import com.bosanskilonac.ks.repository.AdminRepository;
import com.bosanskilonac.ks.repository.KorisnikRepository;
import com.bosanskilonac.ks.repository.KreditnaKarticaRepository;

import enums.Role;
import security.TokenService;
import utility.EmailSender;

@Profile({"default"})
@Component
public class Runner implements CommandLineRunner {
	@Value("${oauth.jwt.secret}")
	private String jwtSecret;
	@Value("${control.email.password}")
	private String emailPassword;
	
	private AdminRepository adminRepository;
	private TokenService tokenService;
	
	private KorisnikRepository korisnikRepository;
	private KreditnaKarticaRepository ccRepository;

	/*public Runner(AdminRepository adminRepository, TokenService tokenService) {
		this.adminRepository = adminRepository;
		this.tokenService = tokenService;
	}*/
	

	public Runner(AdminRepository adminRepository, TokenService tokenService,
			KorisnikRepository korisnikRepository,
			KreditnaKarticaRepository ccRepository) {
		this.adminRepository = adminRepository;
		this.tokenService = tokenService;
		this.korisnikRepository = korisnikRepository;
		this.ccRepository = ccRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		tokenService.setSecret(jwtSecret);
		EmailSender.getInstance().setPassword(emailPassword);
		
		Admin admin1 = new Admin();
		admin1.setUsername("androoideka");
		admin1.setSifra("test123");
		admin1.setRole(Role.ROLE_ADMIN);
		adminRepository.save(admin1);
		
		Admin admin2 = new Admin();
		admin2.setUsername("sbudimac");
		admin2.setSifra("test321");
		admin2.setRole(Role.ROLE_ADMIN);
		adminRepository.save(admin2);
		
		Admin service = new Admin();
		service.setUsername("microservice-access-point");
		service.setSifra("XUhsqdFpmLkh75qwHnSzPpNz");
		service.setRole(Role.ROLE_SERVICE);
		adminRepository.save(service);
		
		Korisnik korisnik = new Korisnik();
		korisnik.setEmail("agasic218rn@raf.rs");
		korisnik.setSifra("testPassword");
		korisnik.setIme("Andrej");
		korisnik.setPrezime("Gasic");
		korisnik.setMilje(0);
		korisnik.setBrojPasosa("bgrsrafzm");
		korisnikRepository.save(korisnik);
		
		KreditnaKartica cc = new KreditnaKartica();
		cc.setBrojKartice(123456789l);
		cc.setImeVlasnika("Andrej");
		cc.setPrezimeVlasnika("Gasic");
		cc.setSigurnosniBroj(869);
		cc.setKorisnik(korisnik);
		ccRepository.save(cc);
	}
}
