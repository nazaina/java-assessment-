package com.example.java.assessment.config;

import com.example.java.assessment.dto.CustomerResponseDto;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.Schema;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSchemas("ProductListResponse", new Schema<CustomerResponseDto>())
                        .addResponses("successResponse", new io.swagger.v3.oas.models.responses.ApiResponse()
                                .description("Successful operation")
                                .content(new Content()
                                        .addMediaType("application/json",
                                                new io.swagger.v3.oas.models.media.MediaType()
                                                        .schema(new Schema<>().$ref("#/components/schemas/CustomerResponseDto"))
                                        )
                                )
                        )
                );
    }
}
