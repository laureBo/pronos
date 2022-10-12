package com.bet.configuration.authent;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bet.model.entity.UtilisateurEntity;
import com.bet.model.repository.IUtilisateurRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	IUtilisateurRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Objects.requireNonNull(username);
		UtilisateurEntity user = userRepository.findUserWithPseudo(username);
		// .orElseThrow(() -> new UsernameNotFoundException("User not found"));
		return new MyUserPrincipal(user);
	}
}