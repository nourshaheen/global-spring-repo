package com.global.book.config;

import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;

import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public GroupedOpenApi userManagementApi() {
		String packagesToscan[] = { "com.service.usermanagement" };
		return GroupedOpenApi.builder()
		                     .group("User Management API")
							 .packagesToScan(packagesToscan)
							 .addOperationCustomizer(appTokenHeaderParam())
							 .build();
	}
	
	@Bean
	public GroupedOpenApi setupApi() {
		String packagesToscan[] = { "com.global.book" };
		return GroupedOpenApi.builder()
		                     .group("Book API")
							 .packagesToScan(packagesToscan)
							 .addOperationCustomizer(appTokenHeaderParam())
							 .build();
	}
	
	@Bean
    public OperationCustomizer appTokenHeaderParam() {
        return (Operation operation, HandlerMethod handlerMethod) -> {
            Parameter headerParameter = new Parameter().in(ParameterIn.HEADER.toString()).required(false).
                    schema(new StringSchema()._default("app_token_header_default_value")).name("app_token_header").description("App Token Header");
            operation.addParametersItem(headerParameter);
            return operation;
        };
    }

}
