package com.bet.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bet.model.entity.MatchEntity;

public interface IMatchRepository extends JpaRepository<MatchEntity, Integer> {

	@Query("SELECT m FROM Match as m WHERE m.idMatch = :idMatch")
	public MatchEntity findMatchByIdMatch(@Param("idMatch") int idMatch);

	@Query("SELECT m FROM Match as m WHERE idSession = :idSession")
	public List<MatchEntity> findAllMatchsForOneSession(@Param("idSession") int idSession);

}
