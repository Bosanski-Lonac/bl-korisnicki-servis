package com.bosanskilonac.ks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bosanskilonac.ks.model.KreditnaKartica;

@Repository
public interface KreditnaKarticaRepository extends JpaRepository<KreditnaKartica, String> {

}
