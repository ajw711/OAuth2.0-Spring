package assets.hello_assets.Controller;

import assets.hello_assets.Service.OAuth2HeaderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OAuth2LoginController {

    private final OAuth2HeaderService oAuth2HeaderService;


    @PostMapping("/oauth2-jwt")
    public String oauth2JWTHeader(HttpServletRequest request, HttpServletResponse response){
        System.out.println("+++++++++++++++++++++++++++++++++");
        return oAuth2HeaderService.oauth2JwtHeaderSet(request,response);
    }
}
