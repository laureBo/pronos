package com.bet.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bet.model.entity.PariEntity;
import com.bet.model.entity.UtilisateurEntity;

public interface IPariRepository extends JpaRepository<PariEntity, Integer> {

	public List<PariEntity> findAllByUtilisateur(UtilisateurEntity utilisateur);

	@Query("SELECT p FROM Pari as p"
			+ " WHERE p.utilisateur.pseudoUser = :pseudo AND p.match.scoreEquipe1 is not null ORDER BY p.match.dateMatch asc")
	public List<PariEntity> findAllBetsbyPseudo(@Param("pseudo") String pseudo);

	@Query("SELECT p FROM Pari as p"
			+ " WHERE p.utilisateur.pseudoUser = :pseudo AND p.match.session.nomSession= :nomSession AND p.match.scoreEquipe1 is not null ORDER BY p.match.dateMatch asc")
	public List<PariEntity> findAllBetsbyPseudoAndNomSession(@Param("pseudo") String pseudo,
			@Param("nomSession") String nomSession);

	public List<PariEntity> findAllByEquipe1(Integer equipe1);

}
