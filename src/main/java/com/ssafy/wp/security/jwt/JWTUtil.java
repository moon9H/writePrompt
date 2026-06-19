package com.ssafy.wp.security.jwt;

import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ssafy.wp.model.dto.member.Member;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtil {

    private final long accessExpMin;
    private final long refreshExpMin;
    private final SecretKey key;

    public JWTUtil(
            @Value("${ssafy.jwt.access-expmin}") long accessExpMin,
            @Value("${ssafy.jwt.refresh-expmin}") long refreshExpMin,
            @Value("${ssafy.jwt.secret-string}") String secretKeyString
    ) {
        this.accessExpMin = accessExpMin;
        this.refreshExpMin = refreshExpMin;
        this.key = Keys.hmacShaKeyFor(secretKeyString.getBytes());
    }

    public String createAccessToken(Member member) {
        String role = member.getRole();

        if (role == null) {
            role = "USER";
        }

        return create(
                String.valueOf(member.getId()),
                accessExpMin,
                Map.of(
                        "type", "access",
                        "role", role
                )
        );
    }

    public String createRefreshToken(Member member) {
        return create(
                String.valueOf(member.getId()),
                refreshExpMin,
                Map.of(
                        "type", "refresh"
                )
        );
    }

    private String create(String subject, long expireMin, Map<String, Object> claims) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + 1000 * 60 * expireMin);

        return Jwts.builder()
                .subject(subject)
                .claims(claims)
                .issuedAt(now)
                .expiration(expireDate)
                .signWith(key)
                .compact();
    }

    public Claims getClaims(String jwt) {
        JwtParser parser = Jwts.parser()
                .verifyWith(key)
                .build();

        Jws<Claims> jws = parser.parseSignedClaims(jwt);
        return jws.getPayload();
    }

    public int getMemberId(String jwt) {
        Claims claims = getClaims(jwt);
        return Integer.parseInt(claims.getSubject());
    }

    public String getRole(String jwt) {
        Claims claims = getClaims(jwt);
        return claims.get("role", String.class);
    }

    public String getType(String jwt) {
        Claims claims = getClaims(jwt);
        return claims.get("type", String.class);
    }
}