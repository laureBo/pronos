package com.bet.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bet.model.entity.UtilisateurEntity;

public interface IUtilisateurRepository extends JpaRepository<UtilisateurEntity, String> {

	UtilisateurEntity findByPseudoUser(String pseudoUser);

	@Query("SELECT u FROM Utilisateur u")
	List<UtilisateurEntity> findAllUtilisateur();

}
