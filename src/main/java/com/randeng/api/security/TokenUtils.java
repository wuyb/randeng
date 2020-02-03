package com.randeng.api.security;

import com.randeng.api.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * A utility class for token related methods.
 */
@Component("tokenUtils")
public class TokenUtils {

    /**
     * The secret used to hash the token.
     */
    @Value("${yeah.token.secret}")
    private String secret;

    /**
     * The expiration time of token. It is in millisecond.
     */
    @Value("${yeah.token.expiration}")
    private Long expiration;

    /**
     * Generates a token.
     * @param user the user
     * @return the generated token
     */
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("sub", user.getUsername());
        claims.put("userId", user.getId());
        claims.put("created", new Date(System.currentTimeMillis()));
        return this.generateToken(claims);
    }

    /**
     * Gets username from token.
     * @param token the token
     * @return the username, if the token cannot be parsed, null shall be returned
     */
    public String getUsernameFromToken(String token) {
        String username = null;
        try {
            final Claims claims = getClaimsFromToken(token);
            if (claims != null) {
                username = claims.getSubject();
            }
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + this.expiration * 1000);
    }

    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(this.generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, this.secret)
                .compact();
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

}
