package com.arabsoft.pfe.projet.util;

import javax.servlet.http.HttpServletRequest;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.arabsoft.pfe.projet.dto.securite.UtilisateurDto;

import org.springframework.http.HttpHeaders;

public class UtilMethod {

    public static final String requeteEtatInvEnCours = getRequeteVocabulaire("", "");

    public static UtilisateurDto getUtilisateurFromToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith(StaticValue.bearerString)) {
            try {
                String token = authorizationHeader.substring(StaticValue.bearerString.length());
                Algorithm algorithm = Algorithm.HMAC256(StaticValue.secretKey.getBytes());
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(token);
                String login = decodedJWT.getSubject();
                Long orgId = decodedJWT.getClaim("orgId").asLong();
                Long id = decodedJWT.getClaim("id").asLong();
                return new UtilisateurDto(id, orgId, login);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    

    public static String getRequeteVocabulaire(String typeCode, String vocCode) {
        return " FROM Vocabulaire v " +
                " INNER JOIN TypeVocabulaire t ON v.typeVocabulaire = t " +
                " INNER JOIN Organisation o ON v.organisation = o " +
                " WHERE t.code = '" + typeCode + "'" +
                " AND   v.code = '" + vocCode + "'";
    }
}
