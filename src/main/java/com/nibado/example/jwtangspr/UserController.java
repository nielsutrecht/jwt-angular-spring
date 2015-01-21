package com.nibado.example.jwtangspr;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {

	private Map<String, List<String>> userDb = new HashMap<>();
	
    public UserController() {
    	userDb.put("tom", Arrays.asList("user"));
    	userDb.put("sally", Arrays.asList("user", "admin"));
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestBody final UserLogin login) throws ServletException {
        if (login.name == null || !userDb.containsKey(login.name)) {
            throw new ServletException("Invalid login");
        }
        return Jwts.builder().setSubject(login.name)
                .claim("roles", userDb.get(login.name))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, SecretKey.BYTES).compact();
    }

    @SuppressWarnings("unused")
    private static class UserLogin {
        public String name;
        public String password;
    }
}
