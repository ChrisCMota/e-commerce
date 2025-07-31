package com.christian.ecommerce.security;

import com.christian.ecommerce.model.Customer;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

import java.security.Key;
import java.util.Date;

public class ECTokenUtil {

    public static final long ONE_SECOND = 1000;
    public static final long ONE_MINUTE = 60*ONE_SECOND;
    public static final long ONE_HOUR = 60*ONE_MINUTE;
    public static final long ONE_DAY = 24*ONE_HOUR;
    public static final long ONE_WEEK = 7*ONE_DAY;

    public static final String ISSUER = "*EComAPI*";
    public static final String TOKEN_KEY = "01234567890123456789012345678901"; //has to be at least 32bits. I added this number just to be able to use a short password for testing.
    public static final String TOKEN_HEADER = "Bearer";

    public static ECToken generateToken(Customer customer){
        Key scretKey = Keys.hmacShaKeyFor(TOKEN_KEY.getBytes());
        String jwt = Jwts.builder()
                .subject(customer.getEmail())
                .issuer(ISSUER)
                .expiration(new Date(System.currentTimeMillis() + ONE_WEEK))
                .signWith(scretKey, SignatureAlgorithm.HS256)
                .compact();

        ECToken token = new ECToken(TOKEN_HEADER + jwt);

        return token;
    }

    public Authentication decodeToken(HttpServletRequest request){
        return null;
    }
}
