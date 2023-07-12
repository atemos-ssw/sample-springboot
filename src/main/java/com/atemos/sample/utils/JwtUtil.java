package com.atemos.sample.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import java.util.Date;

import javax.crypto.SecretKey;

@Slf4j
@Component
public class JwtUtil {

    private static final String SECRET_KEY = "atemos_secret_key";
//    private static final long EXPIRATION_TIME = 86400000; // 24 hours
    private static final long EXPIRATION_TIME= 30 * 60 * 1000L;     // 토큰 유효시간 30분

    private SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    
    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);
        
        Claims claims = Jwts.claims()
				.setSubject(username);
        
        return Jwts.builder()
        	    .setClaims(claims) // 정보 저장 
                .setIssuedAt(now)
//                .setExpiration(expiryDate)
                .signWith(secretKey)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(SECRET_KEY)
        		.build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public String validateToken(String token) {
        try {
        	Jwts.parserBuilder().setSigningKey(SECRET_KEY)
    		.build().parseClaimsJws(token);
           return "0000";
        } catch(io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
        	log.error(e.toString());
        	log.info(String.format("%s is wrong token", token));
        	return "9101";
        } catch(ExpiredJwtException e) {
        	log.error(e.toString());
        	log.info(String.format("%s is expired. (토큰이 만료됨)", token));
        	return "9102";
        } catch(UnsupportedJwtException e) {
        	log.error(e.toString());
        	log.info(String.format("%s is not supported(지원되지않는 토큰)", token));
        	return "9103";
        } catch(IllegalArgumentException e) {
        	log.info("JWT token is falult,(JWT 토큰이 잘못 됨)", token);
        	return "9104";
        }         
        catch (Exception e) {
        	log.error(e.toString());
        	return "9004";
        }
    }
}
