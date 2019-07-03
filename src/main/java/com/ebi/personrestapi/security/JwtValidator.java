package com.ebi.personrestapi.security;

import com.ebi.personrestapi.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {
    private String secret = "upendra";
    public JwtUser validate(String token) {
        JwtUser jwtUser = new JwtUser();
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            jwtUser.setUserName(body.getSubject());
            jwtUser.setId(Long.parseLong((String) body.get("userId")));
            jwtUser.setRole((String) body.get("role"));

        } catch (Exception e) {
            System.out.println("error" + e.toString());
        }
        return jwtUser;
    }
}
