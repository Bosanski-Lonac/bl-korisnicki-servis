package repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Korisnik;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, String> {
	Optional<Korisnik> findUserByEmailAndPassword(String email, String password);
	void deleteById(String id);
}
