package com.arabsoft.pfe.projet.config.securite.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.arabsoft.pfe.projet.util.StaticValue;

public class CustomerAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (!request.getServletPath().contains("api/v1")) {
            filterChain.doFilter(request, response);
            return;
        }
        if (request.getServletPath().contains(StaticValue.urlAuthentification) ||
                request.getServletPath().contains(StaticValue.urlConnect) ||
                request.getServletPath().contains(StaticValue.urlCrypt)) {
            filterChain.doFilter(request, response);
        } else {
            String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (authorizationHeader != null && authorizationHeader.startsWith(StaticValue.bearerString)) {
                try {
                    String token = authorizationHeader.substring(StaticValue.bearerString.length());
                    Algorithm algorithm = Algorithm.HMAC256(StaticValue.secretKey.getBytes());
                    JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = jwtVerifier.verify(token);
                    String userName = decodedJWT.getSubject();
                    String[] droits = decodedJWT.getClaim("droits").asArray(String.class);
                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    java.util.Arrays.stream(droits).forEach(d -> {
                        authorities.add(new SimpleGrantedAuthority(d));
                    });
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userName, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    filterChain.doFilter(request, response);
                } catch (Exception e) {
                    response.setHeader("error", e.getMessage());
                    response.sendError(HttpStatus.FORBIDDEN.value());
                }
            } else {
                response.sendError(HttpStatus.FORBIDDEN.value());
                // filterChain.doFilter(request, response);
            }
        }

    }

}
