package assets.hello_assets.JWT;

import assets.hello_assets.domain.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTUtil {

    private SecretKey secretKey;

    public JWTUtil(@Value("${jwt.secret}")String secret){
        secretKey=new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    private Claims getPayload(String token){
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
    }

    public String getUsername(String token){
        return getPayload(token).get("username", String.class);
    }

    public String getRole(String token){
        return getPayload(token).get("role", String.class);
    }

    public String getCategory(String token){
        return getPayload(token).get("category", String.class);
    }

    public Boolean isExpired(String token){
        return getPayload(token).getExpiration().before(new Date());
    }




    public String createJwt(String username, String role,String category, Long expiredMs){
        return Jwts.builder()
                .claim("username",username)
                .claim("role",role)
                .claim("category",category)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+expiredMs))
                .signWith(secretKey)
                .compact();
    }


}
