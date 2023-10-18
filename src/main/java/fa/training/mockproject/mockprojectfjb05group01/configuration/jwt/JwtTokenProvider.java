package fa.training.mockproject.mockprojectfjb05group01.configuration.jwt;

import fa.training.mockproject.mockprojectfjb05group01.configuration.security.CustomClientDetails;
import io.jsonwebtoken.*;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {
    @Value("${ra.jwt.secret}")
    private  String JWT_SECRET;
    @Value("${ra.jwt.expiration}")
    private int JWT_EXPIRATION;


    //tao jwt tu thong tin user
    public  String generateToken(CustomClientDetails customClientDetails) {


        Date now = new Date();
        Date expiry = new Date(now.getTime() + JWT_EXPIRATION);
//        create the jwt token from user information
        return Jwts.builder()
                .setSubject(customClientDetails.getUsername())
                .claim("isEnable", customClientDetails.isEnabled())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(SignatureAlgorithm.HS512,JWT_SECRET)
                .compact();
    }

//    get email from the token of JWT
    public  String getUserNameFromJwt(String token) {
        Claims claims = Jwts.parser().setSigningKey(JWT_SECRET)
                .parseClaimsJws(token).getBody();
//        return username as the email of the client
        return claims.getSubject();
    }
    //validate khi lay thong tin tu jwt
    public  boolean validateToken(String authToken) {
        try{
            Jwts.parser().setSigningKey(JWT_SECRET)
                    .parseClaimsJws(authToken);
            return true;
        }catch (MalformedJwtException ex) {
            log.error("Invalid Jwt token");
        }catch (ExpiredJwtException ex) {
            log.error("Expired Jwt token");
        }catch (UnsupportedJwtException ex) {
            log.error("Unsupported Jwt Token");
        }catch (IllegalArgumentException ex) {
            log.error("Jwt claims String is empty");
        }
        return  false;
    }
}
