package assets.hello_assets.Service;

import assets.hello_assets.JWT.CookieUtil;
import assets.hello_assets.JWT.JWTUtil;
import assets.hello_assets.Repository.RefreshRepository;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class ReissueService {


    private final JWTUtil jwtUtil;
    private final RefreshRepository refreshRepository;
    private final RefreshService refreshService;
    private final CookieUtil cookieUtil;

    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response){

        String refresh=null;
        Cookie[] cookies=request.getCookies();

        refresh= Arrays.stream(cookies).filter((cookie)-> cookie.getName().equals("refresh")).findFirst().get().getValue();

        if(refresh == null){
                return new ResponseEntity<>("refresh Token null", HttpStatus.BAD_REQUEST);
        }

        try{
            jwtUtil.isExpired(refresh);

        }catch (ExpiredJwtException e){
            return new ResponseEntity<>("refresh token expired", HttpStatus.BAD_REQUEST);
        }

        String category=jwtUtil.getCategory(refresh);
        String customerName=jwtUtil.getUsername(refresh);
        String role=jwtUtil.getRole(refresh);
        if (!category.equals("refresh")){
            return new ResponseEntity<>("invalid refresh token",HttpStatus.BAD_REQUEST);

        }



        String newAuthorization=jwtUtil.createJwt(customerName,role,"Authorization",60*10*1000L);
        String newRefresh=jwtUtil.createJwt(customerName,role,"refresh",24*60*60*1000L);

        refreshRepository.deleteByRefresh(refresh);
        refreshService.saveRefresh(customerName,"newRefresh",24*60*60);

        response.setHeader("Authorization",newAuthorization);
        response.addCookie(cookieUtil.createCookie("refresh",newRefresh,24*60*60));

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
