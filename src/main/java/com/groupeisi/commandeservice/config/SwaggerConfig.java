package com.groupeisi.commandeservice.config;
import io.swagger.v3.oas.annotations.*; import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info; import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;
@Configuration
@OpenAPIDefinition(info = @Info(title = "Commande Service API", version = "1.0", description = "Gestion des inscriptions — Thème École"))
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
public class SwaggerConfig {}
