package vn.edu.iuh.fit.jwt.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {
    public static SecretKey SECRET = Keys.hmacShaKeyFor(Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded());
    public static long EXPIRATION_TIME = 864000000;
    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(SECRET)
                .build()
                .parseSignedClaims(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().verifyWith(SECRET)
                    .build()
                    .parseSignedClaims(authToken);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}
