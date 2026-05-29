package com.example.week8.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        // Swagger UI에서 JWT 토큰을 입력할 수 있도록 보안 스키마 설정
        String securitySchemeName = "Bearer Auth";
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(securitySchemeName);
        Components components = new Components()
                .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                        .name(securitySchemeName)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT"));

        return new OpenAPI()
                .info(new Info()
                        .title("Likelion Week 8 Member System API")
                        .description("회원 관리 및 인증 시스템 API 명세서")
                        .version("1.0.0"))
                .addSecurityItem(securityRequirement)
                .components(components);
    }
}