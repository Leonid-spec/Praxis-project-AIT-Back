package de.dental_clinic.g_43_praxis.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Dental Clinic API",
                version = "1.0",
                description = "API documentation for the dental clinic application",
                contact = @Contact(
                        name = "Praxis project team",
                        email = "team@ait-tr.de",
                        url = "https://github.com/Leonid-spec/Praxis-project-AIT-Back/tree/dev"
                )
        ),
        servers = @Server(url = "http://localhost:8081", description = "Local Server")
)
public class SwaggerConfig {
}


// Start work with swagger, access - http://localhost:8080/swagger-ui/index.html