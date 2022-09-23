package com.bet.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bet.model.entity.ParticiperEntity;
import com.bet.model.entity.UtilisateurEntity;

public interface IParticiperRepository extends JpaRepository<ParticiperEntity, UtilisateurEntity> {

	/*
	 * @Query("INSERT INTO Participer as p (p.pseudoUser = :pseudo AND p.idSession= :idSession"
	 * ) public ParticiperEntity saveParticipant(@Param("pseudo") String
	 * pseudo, @Param("idSession") int idSession);
	 */

}
