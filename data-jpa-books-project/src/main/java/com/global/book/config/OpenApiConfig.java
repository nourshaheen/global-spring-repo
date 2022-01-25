package com.global.book.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
@OpenAPIDefinition()
public class OpenApiConfig {
	
	private static final String SECURITY_SCHEME_NAME = "Bearer oAuth Token";
	
	@Bean
	public OpenAPI customOpenAPI(@Value("${application-description}") String appDesciption, 
                                 @Value("${application-version}")  String appVersion) {
		return new OpenAPI()
				.info(new Info()
						.title("sample application API")
				.version(appVersion)
				.contact(getContact())
				.description(appDesciption)
				.termsOfService("http://swagger.io/terms/")
				.license(getLicense()))
						 
				.addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME, Arrays.asList("read", "write")))
				.components(
						new Components()
						.addSecuritySchemes(SECURITY_SCHEME_NAME,
								new SecurityScheme().name(SECURITY_SCHEME_NAME)
								.type(SecurityScheme.Type.HTTP)
								.scheme("bearer")
								.bearerFormat("JWT")
							)) ;
	}
	
	
	
	 private Contact getContact() {
        Contact contact = new Contact();
        contact.setEmail("info@gmail.com");
        contact.setName("book Service");
        contact.setUrl("https://www.book.com");
        contact.setExtensions(Collections.emptyMap());
        return contact;
    }
    
    private License getLicense() {
        License license = new License();
        license.setName("Apache License, Version 2.0");
        license.setUrl("http://www.apache.org/licenses/LICENSE-2.0");
        license.setExtensions(Collections.emptyMap());
        return license;
    }

}
