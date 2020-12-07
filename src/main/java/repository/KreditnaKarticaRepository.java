package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.KreditnaKartica;

public interface KreditnaKarticaRepository extends JpaRepository<KreditnaKartica, String> {

}
