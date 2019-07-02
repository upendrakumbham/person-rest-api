package com.ebi.personrestapi.model;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Objects;

public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {


    private String token;



    /**
     * This constructor can be safely used by any code that wishes to create a
     * <code>UsernamePasswordAuthenticationToken</code>, as the {@link #isAuthenticated()}
     * will return <code>false</code>.
     *
     */
    public JwtAuthenticationToken(String token) {
        super(null,null);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public Object getCredentials(){
        return null;
    }

    @Override
    public Object getPrincipal(){
       return null;
    }
}
