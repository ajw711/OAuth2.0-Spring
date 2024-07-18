package assets.hello_assets.Logout;

import assets.hello_assets.JWT.CookieUtil;
import assets.hello_assets.JWT.JWTUtil;
import assets.hello_assets.Repository.RefreshRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.Arrays;

@RequiredArgsConstructor
public class CustomLogoutFilter extends GenericFilter {

    private final JWTUtil jwtUtil;
    private final RefreshRepository refreshRepository;
    private final CookieUtil cookieUtil;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }

    private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        // uri check
        if (!requestURI.matches("^\\/logout$")) {
            chain.doFilter(request, response);
            return;
        }
        // method check
        String requestMethod = request.getMethod();
        if (!requestMethod.equals("POST")) {
            chain.doFilter(request, response);
            return;
        }

        // refresh token validation
        String refresh = null;
        Cookie[] cookies = request.getCookies();

        refresh = Arrays.stream(cookies).filter(cookie -> cookie.getName().equals("refresh"))
                .findFirst().get().getValue();

        System.out.println("refresh = " + refresh);

        // refresh token null
        if(refresh == null){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        String category = jwtUtil.getCategory(refresh);

        // not refresh token
        if(!category.equals("refresh")){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Boolean isExist = refreshRepository.existsByRefresh(refresh);

        // not exist in DB
        if(!isExist){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        // logout
        refreshRepository.deleteByRefresh(refresh);

        Cookie cookie = cookieUtil.createCookie("refresh", null, 0);
        response.addCookie(cookie);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
