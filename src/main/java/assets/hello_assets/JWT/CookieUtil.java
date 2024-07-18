package assets.hello_assets.JWT;

import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CookieUtil {

    public Cookie createCookie(String key, String value, Integer expiredMs){
        Cookie cookie=new Cookie(key,value);
        cookie.setMaxAge(expiredMs);
        //cookie.setSecure(true); HTTPS
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }
}
