package app.security.jwt;

import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Log4j2
@Service
@PropertySource("classpath:jwt.properties")
public class JwtTokenServiceImpl {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration.normal}")
    private Long expiration_normal;

    @Value("${jwt.expiration.remember}")
    private Long expiration_remember;

    public String generateToken(Long userId, boolean rememberMe) {
        final Date date = new Date();
        final Date expiry = new Date(date.getTime() + (rememberMe ? expiration_remember : expiration_normal));
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(date)
                .setExpiration(expiry)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public Long extractUserIdFromClaims(Jws<Claims> claimsJws) {
        return Long.parseLong(claimsJws.getBody().getSubject());
    }

    public Optional<Jws<Claims>> tokenToClaim(String token){
        try {
            return Optional.of(Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token));
        }catch (SignatureException | MalformedJwtException | IllegalArgumentException | UnsupportedJwtException | ExpiredJwtException ex) {
         //TODO correct this
            System.out.println("ex");
        }
        return Optional.empty();
    }

    public boolean validateToken(String token) {
        return tokenToClaim(token).isPresent();
    }
}
