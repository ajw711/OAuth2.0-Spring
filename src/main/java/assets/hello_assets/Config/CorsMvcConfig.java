package assets.hello_assets.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

    @Configuration
    public class CorsMvcConfig implements WebMvcConfigurer {

            @Override
            public void addCorsMappings(CorsRegistry corsRegistry){

                corsRegistry.addMapping("/**")
                        .allowedMethods("*")
                        .allowedHeaders("*")
                        .exposedHeaders("Authorization")
                        .exposedHeaders("Set-Cookie")
                        .allowCredentials(true)
                        .allowedOrigins("http://localhost:3000");
            }

    }
