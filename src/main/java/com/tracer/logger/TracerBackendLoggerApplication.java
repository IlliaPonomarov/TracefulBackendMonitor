package com.tracer.logger;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Trace Backend Logger API",
                description = "Trace Backend Logger API Documentation",
                version = "v1.0",
                contact = @Contact(
                        name = "Illia"
                ),
                license = @License(
                        name = "Apache 2.0"
                )
        )

)
public class TracerBackendLoggerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TracerBackendLoggerApplication.class, args);
    }

}
