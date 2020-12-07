package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Korisnik;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, String> {
	void deleteById(String id);
}
