package assets.hello_assets.OAuth2;

import assets.hello_assets.DTO.CustomOAuth2User;
import assets.hello_assets.JWT.CookieUtil;
import assets.hello_assets.JWT.JWTUtil;
import assets.hello_assets.Repository.CustomerRepository;
import assets.hello_assets.Service.RefreshService;
import assets.hello_assets.domain.Customer;
import assets.hello_assets.domain.Role;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Iterator;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler { //jwt발급해주는 메서드

    private final JWTUtil jwtUtil;
    private final RefreshService refreshService;

    private final CustomerRepository customerRepository;

    private final CookieUtil cookieUtil;

    public CustomSuccessHandler(JWTUtil jwtUtil, RefreshService refreshService,CookieUtil cookieUtil, CustomerRepository customerRepository){
        this.jwtUtil=jwtUtil;
        this.refreshService=refreshService;
        this.cookieUtil=cookieUtil;
        this.customerRepository=customerRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();

        String CustomerName = customUserDetails.getUsername();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();

        String username=customUserDetails.getUsername();
        String name=customUserDetails.getName();
        String role = auth.getAuthority();

        System.out.println(username);
        System.out.println(name);
        System.out.println(role);

        Customer customer=customerRepository.findByCustomerName(username);


        String token=jwtUtil.createJwt(CustomerName,role,"Authorization",60*60*60L);
        String refreshToken=jwtUtil.createJwt("refresh",username,"refresh", 24*60*60*1000L);

        refreshService.saveRefresh(username,refreshToken,24*60*60);

        String encodeName="";

        if(customer.getRole().toString().equals("ADMIN")) {

            encodeName = URLEncoder.encode("ROLE", StandardCharsets.UTF_8);
        }else {
            encodeName = URLEncoder.encode(name, StandardCharsets.UTF_8);
        }
        response.addCookie(cookieUtil.createCookie("Authorization",token,60*10));
        response.addCookie(cookieUtil.createCookie("refresh",refreshToken,24*60*60));
        response.sendRedirect("http://localhost:3000/oauth2-jwt?name=" + encodeName);
    }


}
