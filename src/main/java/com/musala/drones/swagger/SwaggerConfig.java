package com.musala.drones.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;


@OpenAPIDefinition(
        info = @Info(
                title = "Drone Dispatcher",
                description = "REST API service that allows Drone dispatch medications",
                contact = @Contact(
                        name = "Ikechukwu Ezugworie",
                        url = "https://www.linkedin.com/in/ezugworie/",
                        email = "i.ezugworie@gmail.com"
                )
        ),
        servers = @Server(url = "http://localhost:8080")
)
public class SwaggerConfig {}