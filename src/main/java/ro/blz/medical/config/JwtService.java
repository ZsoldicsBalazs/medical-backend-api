package ro.blz.medical.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;

@Service
public class JwtService {
    public String extractUserEmail(String jwt) {
        return null;
    }


    private Claims extractAllClaims(String jwt) {
        return Jwts.parser()
                .decryptWith(getSignInKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }

    private PrivateKey getSignInKey() {
    }

}
