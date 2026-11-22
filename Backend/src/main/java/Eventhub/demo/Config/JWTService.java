package Eventhub.demo.Config;

import Eventhub.demo.User.Entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.UUID;

@Service
public class JWTService {

    private final SecretKey secretKey;

    public JWTService(@Value("${jwt.secret}") String secret) {
        this.secretKey = Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(secret)
        );
    }

    public String generateToken(UUID userId, User.UserRoles role) {

        Date issuedAt = new Date();
        Date expiration = new Date(
                issuedAt.getTime() + 3600000
        );

        return Jwts.builder()
                .subject(userId.toString())
                .claim("role", role.name())
                .issuedAt(issuedAt)
                .expiration(expiration)
                .signWith(secretKey, Jwts.SIG.HS256)
                .compact();
    }
}