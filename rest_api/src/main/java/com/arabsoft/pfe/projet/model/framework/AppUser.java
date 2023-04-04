package com.arabsoft.pfe.projet.model.framework;

import java.util.Collection;

import com.arabsoft.pfe.projet.model.securite.Utilisateur;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AppUser extends User {

    public AppUser(String username, String password, boolean enabled, boolean accountNonExpired,
            boolean credentialsNonExpired, boolean accountNonLocked,
            Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public AppUser(String username, String password, Collection<? extends GrantedAuthority> authorities,
            Utilisateur utilisateur) {
        super(username, password, authorities);
        this.utilisateur = utilisateur;
    }

    private Utilisateur utilisateur;

}
