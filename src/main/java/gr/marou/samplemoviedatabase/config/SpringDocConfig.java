package gr.marou.samplemoviedatabase.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for Springdoc OpenAPI documentation.
 */
@Configuration
public class SpringDocConfig {


    /**
     * Configures a grouped OpenAPI specification for the Sample Movie Database API.
     *
     * @return the grouped OpenAPI specification
     */
    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("sample-movie-database")
                .pathsToMatch("/smdb/**")
                .build();
    }

    /**
     * Configures the OpenAPI metadata for the Sample Movie Database API.
     *
     * @return the OpenAPI metadata
     */
    @Bean
    public OpenAPI smdbAPI() {
        return new OpenAPI()
                .info(new Info().title("SMDB")
                        .description("Sample Movie DataBase")
                        .version("v1.0.0")
                        .license(new License().name("marou").url("https://github.com/MarougkasOrfeas")));
    }

}
