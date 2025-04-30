package com.jwt.jwt.utils;

import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.logging.Logger;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Component
public class JwtUtils {
    @Value("${jwt.secret}")
    private String secret ;

    @Value("${jwt.expiration}")
    private long expirationTime;

    // Add refresh expiration time field
    @Value("${jwt.refreshExpiration}")
    private long refreshExpiration;

    private SecretKey secretKey;
    private static final Logger logger = Logger.getLogger(JwtUtils.class.getName());
    // @PostConstruct
    // public void init() {
    //     // secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    //     System.out.println("Raw secret from properties: " + secret);
    //     byte[] decodedKey = Base64.getDecoder().decode(secret);
    //     System.out.println("Decoded key length: " + decodedKey.length); // Should be 32
    //     System.out.println("Decoded key: " + new String(decodedKey));
    //     secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "HmacSHA256");
    // }


    @PostConstruct
    public void init() {
        System.out.println("Raw secret from properties: " + secret);
        byte[] decodedKey = Base64.getDecoder().decode(secret.trim()); // ✅ Fix: Trim whitespace
        secretKey = new SecretKeySpec(decodedKey, "HmacSHA256");
        System.out.println("Using secret key: " + Arrays.toString(secretKey.getEncoded()));
    }
    
    
    public String generateToken(String email, String role) {
        System.out.println("Using secret key: " + Arrays.toString(secretKey.getEncoded()));
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(secretKey, SignatureAlgorithm.HS256) // Signature Algorithm
                .compact();
    }
    

    // Add refresh token generation method
    public String generateRefreshToken(String email, String role) {
        return Jwts.builder()
            .setSubject(email)
            .claim("role", role)
            .claim("tokenType", "refresh")
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + refreshExpiration))
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact();
    }
    

    // Extract claims from JWT token
    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Extract username from token
    public String extractEmail(String token) {
        return extractClaims(token).getSubject();
    }

    // Extract role from token
    public String extractRole(String token) {
        return extractClaims(token).get("role", String.class);
    }

    // public boolean validateToken(String token) {
    //     try {
    //         Claims claims = extractClaims(token);  // Extract the claims first
    //         return !claims.getExpiration().before(new Date());  // Check expiration only
    //     } catch (Exception ex) {
    //         logger.warning("Invalid token: " + ex.getMessage());
    //         return false;
    //     }
    // }
    
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException ex) {
            logger.warning("Token expired: " + ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            logger.warning("Unsupported JWT: " + ex.getMessage());
        } catch (MalformedJwtException ex) {
            logger.warning("Malformed JWT: " + ex.getMessage());
        } catch (SignatureException ex) {
            logger.warning("Invalid signature: " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            logger.warning("Invalid token: " + ex.getMessage());
        }
        return false;
    }
    
    // Check if token is expired
    private boolean isTokenExpired(Claims claims) {
        Date expiration = claims.getExpiration();
        return expiration.before(new Date());  // Check if expiration date has passed
    }

    // In your JwtUtils class
public String refreshToken(String token) {
    Claims claims = extractClaims(token);
    return generateToken(claims.getSubject(), claims.get("role").toString());
}


}
