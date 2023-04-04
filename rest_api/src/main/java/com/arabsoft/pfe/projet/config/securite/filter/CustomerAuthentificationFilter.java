package com.arabsoft.pfe.projet.config.securite.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.arabsoft.pfe.projet.dto.securite.UtilisateurDto;
import com.arabsoft.pfe.projet.model.framework.AppUser;
import com.arabsoft.pfe.projet.model.framework.ObjetRetour;
import com.arabsoft.pfe.projet.model.securite.Utilisateur;
import com.arabsoft.pfe.projet.util.StaticValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class CustomerAuthentificationFilter extends UsernamePasswordAuthenticationFilter {

        private final AuthenticationManager authenticationManager;

        public CustomerAuthentificationFilter(AuthenticationManager authenticationManager) {
                this.authenticationManager = authenticationManager;
        }

        @Override
        public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
                        throws AuthenticationException {
                Utilisateur utilisateur = getUtilisateurFromRequest(request);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                                utilisateur.getLogin(),
                                utilisateur.getMdp());
                return authenticationManager.authenticate(authenticationToken);

        }

        @Override
        protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                        FilterChain chain,
                        Authentication authResult) throws IOException, ServletException {
                AppUser user = (AppUser) authResult.getPrincipal();
                Algorithm algorithm = Algorithm.HMAC256(StaticValue.secretKey.getBytes());
                String accessToken = JWT.create()
                                .withSubject(user.getUsername())
                                .withExpiresAt(new Date(System.currentTimeMillis() + 1500 * 60 * 1000))
                                .withIssuer(request.getRequestURL().toString())
                                .withClaim("droits",
                                                user.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                                                                .collect(Collectors.toList()))
                                .withClaim("id", user.getUtilisateur().getId())
                                .sign(algorithm);

                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                UtilisateurDto utilisateurDto = new UtilisateurDto(user.getUtilisateur(), accessToken);
                ObjetRetour objetRetour = new ObjetRetour(utilisateurDto);
                new ObjectMapper().writeValue(response.getOutputStream(), objetRetour);
        }

        @Override
        protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                        AuthenticationException failed) throws IOException, ServletException {
                response.setStatus(200);
                ObjetRetour objetRetour = new ObjetRetour("LOGIN_MDP", "Login ou mot de passe incorrect");
                new ObjectMapper().writeValue(response.getOutputStream(), objetRetour);
        }

        private Utilisateur getUtilisateurFromRequest(HttpServletRequest request) {
                BufferedReader reader = null;
                Utilisateur utilisateur = null;
                try {
                        reader = request.getReader();
                        Gson gson = new Gson();
                        utilisateur = gson.fromJson(reader, Utilisateur.class);
                } catch (IOException ex) {

                } finally {
                        try {
                                reader.close();
                        } catch (IOException ex) {

                        }
                }
                return utilisateur;
        }

}
