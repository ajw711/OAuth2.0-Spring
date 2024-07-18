package assets.hello_assets.Service;

import assets.hello_assets.JWT.CookieUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuth2HeaderService {

    private final CookieUtil cookieUtil;

    public String oauth2JwtHeaderSet(HttpServletRequest request, HttpServletResponse response){

        Cookie[] cookies=request.getCookies();
        String Authorization=null;

        System.out.println("OAuth2HeaderService +++++++++++");

        if (cookies == null){
           response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
           return " cookies bad";
        }

        for (Cookie cookie : cookies) {
            System.out.println(cookie);
            if (cookie.getName().equals("Authorization")) {
                Authorization = cookie.getValue();
            }
        }

        if(Authorization==null){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "Authorization bad";
        }


        response.addCookie(cookieUtil.createCookie("Authorization",null,0));
        response.addHeader("Authorization",Authorization);
        response.setStatus(HttpServletResponse.SC_OK);

        return "success";


    }




}
