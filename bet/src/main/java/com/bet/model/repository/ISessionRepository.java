package com.bet.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bet.model.entity.ParticiperEntity;
import com.bet.model.entity.SessionEntity;

public interface ISessionRepository extends JpaRepository<SessionEntity, Integer> {

	SessionEntity findByNomSession(String nomSession);

	@Query("SELECT s.nomSession FROM Session as s, Participer as p, Utilisateur as u "
			+ "WHERE u.pseudoUser = :pseudo AND p.session.idSession= s.idSession AND u.pseudoUser= p.utilisateur.pseudoUser ")
	public List<String> findAllNomSessionByUser(@Param("pseudo") String pseudo);

	@Query("SELECT s.participations FROM Session as s WHERE s.idSession= :idSession")
	public List<ParticiperEntity> findAllParticipantsBySession(@Param("idSession") int idSession);
}
