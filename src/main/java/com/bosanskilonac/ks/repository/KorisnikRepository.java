package com.bosanskilonac.ks.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bosanskilonac.ks.model.Korisnik;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, String> {
	Optional<Korisnik> findKorisnikByEmailAndSifra(String email, String sifra);
	void deleteById(String id);
}
