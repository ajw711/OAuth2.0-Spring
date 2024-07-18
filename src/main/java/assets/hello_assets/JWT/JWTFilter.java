package assets.hello_assets.JWT;

import assets.hello_assets.DTO.CustomOAuth2User;
import assets.hello_assets.DTO.UserDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil){
        this.jwtUtil=jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{


        String Authorization=null;
        Authorization= request.getHeader("Authorization");
        System.out.println("Authorization 값입니다: "+Authorization);
        Cookie[] cookies=request.getCookies();
        //System.out.println("과연????"+Authorization);

        ////Authorization 헤더 검증

        if(Authorization == null){
          //  System.out.println("token null");
            filterChain.doFilter(request,response);

            //조건이 해당되면 메소드 종료 (필수)
            return;
        }

        for(Cookie cookie:cookies){
            System.out.println(cookie.getName());

            if(cookie.getName().equals("Authorization")){
                Authorization=cookie.getValue();
            }
        }

        String category= jwtUtil.getCategory(Authorization);
        String token=Authorization;



        //토큰 소멸 시간 검증
        if (jwtUtil.isExpired(Authorization)) {

            System.out.println("token expired");
            filterChain.doFilter(request, response);

            //조건이 해당되면 메소드 종료 (필수)
            return;
        }

        if(!category.equals("Authorization")){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }


        //토큰에서 username과 role 획득
        String username = jwtUtil.getUsername(Authorization);
        String role = jwtUtil.getRole(Authorization);

        System.out.println("jwfilter 입니다 확인합니다: "+username+" "+role);

        //userDTO를 생성하여 값 set
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setRole(role);

        //UserDetails에 회원 정보 객체 담기
        CustomOAuth2User customOAuth2User = new CustomOAuth2User(userDTO);

        //스프링 시큐리티 인증 토큰 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(customOAuth2User, null, customOAuth2User.getAuthorities());
        //세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }

}
