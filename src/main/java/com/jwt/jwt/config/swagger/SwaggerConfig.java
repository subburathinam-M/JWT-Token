package com.jwt.jwt.config.swagger;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
@Configuration
// @OpenAPIDefinition(
//     info = @io.swagger.v3.oas.annotations.info.Info(title = "JWT Auth API", version = "1.0"),
//     security = @SecurityRequirement(name = "bearerAuth") <-- ❌ THIS MAKES JWT REQUIRED GLOBALLY
// )
@SecurityScheme(
    name = "bearerAuth",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "bearer"
)
public class SwaggerConfig {

     @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                    .info(new Info()
                    .title("JWT Auth")
                    .version("1.0")
                    .description("JWT Auth API with Spring Security"))
                    .components(new Components()
                    .addSecuritySchemes("bearerAuth",
                    new io.swagger.v3.oas.models.security.SecurityScheme()
                            .name("bearerAuth")
                            .type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP)
                            .scheme("bearer")
                            .bearerFormat("JWT")));
}


}
