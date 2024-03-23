package gr.marou.samplemoviedatabase.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Collections;

/**
 * Configuration class for customizing Spring Web MVC behavior.
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${spring.data.rest.base-path}")
    private String basePath;

    /**
     * Configures path matching for Spring MVC controllers to include a base path.
     *
     * @param configurer the path match configurer
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix(basePath, HandlerTypePredicate.forAnnotation(RestController.class));
    }

    /**
     * Defines an ErrorViewResolver bean to handle path-based location strategy.
     * Redirects to index.html for HTTP status NOT_FOUND.
     *
     * @return the ErrorViewResolver bean
     */
    @Bean
    ErrorViewResolver supportPathBasedLocationStrategy(){
        return (request, status, model) -> status == HttpStatus.NOT_FOUND
                ? new ModelAndView("forward:/index.html", Collections.emptyMap(), HttpStatus.OK)
                : null;
    }
}
