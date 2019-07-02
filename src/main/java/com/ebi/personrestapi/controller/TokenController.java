package com.ebi.personrestapi.controller;

import com.ebi.personrestapi.model.JwtUser;
import com.ebi.personrestapi.security.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
public class TokenController {

    JwtGenerator jwtGenerator;

    public TokenController(JwtGenerator jwtGenerator){
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping
    public String generate(@RequestBody final JwtUser jwtUser){
       return jwtGenerator.generate(jwtUser);
    }
}
