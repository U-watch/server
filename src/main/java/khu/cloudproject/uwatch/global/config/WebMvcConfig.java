package khu.cloudproject.uwatch.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
//                .allowedOrigins(getAllowOrigins())
                .allowedHeaders("Authorization", "Cache-Control", "Content-Type", "ngrok-skip-browser-warning")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                .allowCredentials(true)
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials") // 응답 헤더 노출
                .maxAge(3600);
    }

    private String[] getAllowOrigins() {
        return Arrays.asList(
                "http://localhost:3000",
                "https://localhost:3000",
                "https://coherent-midge-probably.ngrok-free.app"
        ).toArray(String[]::new);
    }
}
