package assets.hello_assets.Config;

import assets.hello_assets.JWT.CookieUtil;
import assets.hello_assets.JWT.JWTFilter;
import assets.hello_assets.JWT.JWTUtil;
import assets.hello_assets.Logout.CustomLogoutFilter;
import assets.hello_assets.OAuth2.CustomSuccessHandler;
import assets.hello_assets.Repository.CustomerRepository;
import assets.hello_assets.Repository.RefreshRepository;
import assets.hello_assets.Service.CustomOAuth2UserService;
import assets.hello_assets.Service.RefreshService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomerRepository customerRepository;
    private final CustomSuccessHandler customSuccessHandler;
    private final RefreshService refreshService;
    private final RefreshRepository refreshRepository;
    private final JWTUtil jwtUtil;
    private final CookieUtil cookieUtil;



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http
                .cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {

                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

                        CorsConfiguration configuration = new CorsConfiguration();

                        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000")); //프론트 서버 주소
                       // configuration.setAllowedMethods(Collections.singletonList("*"));
                        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                        configuration.setAllowCredentials(true);
                        configuration.setAllowedHeaders(Collections.singletonList("*"));
                        configuration.setMaxAge(3600L);



                        configuration.setExposedHeaders(Collections.singletonList("Set-Cookie"));
                        configuration.setExposedHeaders(Collections.singletonList("Authorization"));

                        return configuration;
                    }
                }));

        //csrf disable
        http
                .csrf((auth) -> auth.disable());

        //From 로그인 방식 disable
        http
                .formLogin((auth) -> auth.disable());

        //HTTP Basic 인증 방식 disable
        http
                .httpBasic((auth) -> auth.disable());


        //JWTFilter 추가
        http
                .addFilterBefore(new JWTFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);
        http
                .addFilterBefore(new CustomLogoutFilter(jwtUtil,refreshRepository,cookieUtil), LogoutFilter.class);

        //oauth2
        http
                .oauth2Login((oauth2)->oauth2
                        .userInfoEndpoint((userInfoEndpointConfig) -> userInfoEndpointConfig
                                .userService(customOAuth2UserService))
                        .successHandler(new CustomSuccessHandler(jwtUtil,refreshService,cookieUtil, customerRepository))


                );
        //logout
        http
                .logout((auth)->auth
                        .logoutSuccessUrl("/")
                        .permitAll());

        //경로별 인가 작업
        http
                .authorizeHttpRequests((auth) -> auth
//                        .requestMatchers("/", "/login","/logout", "/oauth2-jwt", "/account/create/general").permitAll()
                        .requestMatchers("/**").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .anyRequest().authenticated());

        //세션 설정 : STATELESS
        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }



}
