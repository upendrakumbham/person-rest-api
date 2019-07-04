package com.ebi.personrestapi.security;

import com.ebi.personrestapi.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
public class JwtGenerator {

    @Value("${jwt.secret}")
    private String secret; //we can make secret more robust in real time, here I am using UUID as a secret for quick solution

    public String generate(String username) {
        long validityInMilliseconds = 8 * 60 * 60 * 1000; // 8h
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);
        Claims claims = Jwts.claims()
                .setIssuedAt(Date.from(Instant.now()))
                .setSubject(username)
                .setExpiration(validity);
        // we can set other claims such as roles
        // as per the requirements in actual production applications
        // claims.put("property name", "property value")

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();

    }
}
