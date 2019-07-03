package com.ebi.personrestapi.security;

import com.ebi.personrestapi.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {
    private String secret = "upendra";

    public JwtUser validate(String token) {
        JwtUser jwtUser = null;
        try {
            Claims currentUserClaims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            if(currentUserClaims == null)
                return null;
            jwtUser = new JwtUser();
            jwtUser.setUserName(currentUserClaims.getSubject());
            // we can extract other claims set and assign to the user
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jwtUser;
    }
}
