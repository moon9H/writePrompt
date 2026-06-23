package com.ssafy.wp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springdoc.core.customizers.OpenApiCustomizer;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

	private static final String SECURITY_SCHEME_NAME = "bearerAuth";

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("writePrompt API")
						.version("1.0.0")
						.description("writePrompt API 문서"))
				.components(new Components()
						.addSecuritySchemes(SECURITY_SCHEME_NAME,
								new SecurityScheme()
										.name(SECURITY_SCHEME_NAME)
										.type(SecurityScheme.Type.HTTP)
										.scheme("bearer")
										.bearerFormat("JWT")));
	}

	@Bean
	public OpenApiCustomizer securityOpenApiCustomizer() {
		return openApi -> openApi.getPaths().forEach((path, pathItem) ->
				pathItem.readOperationsMap().forEach((method, operation) -> {
					if (requiresAuthentication(method, path)) {
						addBearerAuth(operation);
					}
				})
		);
	}

	private boolean requiresAuthentication(PathItem.HttpMethod method, String path) {
		return !(method == PathItem.HttpMethod.POST && path.equals("/api/auth/login"))
				&& !(method == PathItem.HttpMethod.POST && path.equals("/api/auth/refresh"))
				&& !(method == PathItem.HttpMethod.POST && path.equals("/api/auth/logout"))
				&& !(method == PathItem.HttpMethod.POST && path.equals("/api/members"));
	}

	private void addBearerAuth(Operation operation) {
		operation.addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME));
	}
}
