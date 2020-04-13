package service.security.jwt;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import service.security.services.UserDetailsImpl;

import java.util.Date;

@Component
public class JwtProvider {
    private static final Logger _logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("jwtSecretKey")
    private String jwtSecret;

    @Value("98706")
    private int jwtExpiration;

    public String generateJwtToken(Authentication auth) {
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        return Jwts.builder()
                .setSubject((userDetails.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpiration*1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            _logger.error("SignatureException -> Message: {} ", e);
        } catch (MalformedJwtException e) {
            _logger.error("MalformedJwtException -> Message: {}", e);
        } catch (ExpiredJwtException e) {
            _logger.error("ExpiredJwtException -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
            _logger.error("UnsupportedJwtException -> Message: {}", e);
        } catch (IllegalArgumentException e) {
            _logger.error("IllegalArgumentException -> Message: {}", e);
        }
        return false;
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }
}