package com.techradar.polls.techradar_polls.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Technology Radar API",
                description = "Api для управления технологиями в техрадаре", version = "1.0.0"
        )
)
public class OpenApiConfig {
}
