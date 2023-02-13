package com.bet.configuration.authent;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bet.model.entity.UtilisateurEntity;
import com.bet.model.repository.IUtilisateurRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    IUtilisateurRepository utilisateurRepo;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final UtilisateurEntity user = this.utilisateurRepo.findUserWithPseudo(username);
        if (user == null) {
            throw new UsernameNotFoundException("user not found for " + username);
        }
        return new User(user.getPseudoUser(), user.getPassword(), Collections.emptyList());
    }

}
