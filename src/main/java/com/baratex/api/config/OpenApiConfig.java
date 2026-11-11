package com.baratex.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Schema;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    static {
        Schema<Object> pointSchema = new Schema<>()
                .type("object")
                .description("Coordenada Geográfica PostGIS (longitude, latitude)");

        SpringDocUtils.getConfig().replaceWithSchema(Point.class, pointSchema);
        SpringDocUtils.getConfig().replaceWithSchema(Geometry.class, pointSchema);
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("BARATEX API")
                        .version("1.0.0")
                        .description("API RESTful do ecossistema BARATEX para agendamento de serviços automotivos.")
                        .contact(new Contact()
                                .name("Suporte BARATEX")
                                .email("suporte@baratex.com")));
    }
}