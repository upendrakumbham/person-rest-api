package com.ebi.personrestapi.security;

import com.ebi.personrestapi.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
public class JwtGenerator {

    public String generate(String username) {
        Claims claims = Jwts.claims()
                .setIssuedAt(Date.from(Instant.now()))
                .setSubject(username);
        // we can set other claims such as roles
        // as per the requirements in actual production applications
        // claims.put("property name", "property value")

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "upendra")
                .compact();

    }
}
