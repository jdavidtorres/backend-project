package co.com.jdti.businesslogic.configurations;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

	@Bean
	OpenAPI customOpenAPI() {
		return new OpenAPI().components(new Components()).info(new Info()
			.contact(contact())
			.title("Backend service with authentication")
			.version("1.0.0")
			.description("This an API for testing the authentication service")
		);
	}

	private Contact contact() {
		return new Contact()
			.name("jdavidtorres")
			.url("https://github.com/jdavidtorres/backend-project");
	}
}
