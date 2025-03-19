package ro.blz.medical.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import ro.blz.medical.domain.User;

import javax.crypto.SecretKey;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = System.getenv("SECRET_KEY");
    private final long EXPIRATION_TIME = 43200000L; // 12 Hours

    public String extractUsername(String jwt) {
        var username = extractClaim(jwt, Claims::getSubject);
        return username;
    }
    public SimpleGrantedAuthority extractRole(String jwt){
        List roles = extractAllClaims(jwt).get("roles", List.class);
        return (SimpleGrantedAuthority) roles.getFirst();
    }

    public <T> T extractClaim(String jwt, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(jwt);
        return claimsResolver.apply(claims);
    }

    public String generateToken(User userDetails) {
        return Jwts
                .builder()
                .subject(userDetails.getEmail())
                .claim("roles", userDetails.getAuthorities())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) //8minutes
                .signWith(getSignInKey(), Jwts.SIG.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, User userDetails) {
        final String userName = extractUsername(token);
        return userName.equals(userDetails.getEmail()) && !isTokenExpired(token);
    }
//    Validating token for angular on endpoint.
    public boolean validateToken(String token){
        try {
            Jwts.parser()
                    .verifyWith(getSignInKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        }catch (JwtException | IllegalArgumentException e){
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        Date expirationDate = extractClaim(token, Claims::getExpiration);

        return expirationDate;
    }


    private Claims extractAllClaims(String jwt) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
