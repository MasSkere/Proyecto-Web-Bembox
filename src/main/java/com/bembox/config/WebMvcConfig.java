package com.bembox.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuración MVC para redireccionar la ruta raíz a index.html en templates.
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Redirige la ruta raíz a "index" (Thymeleaf buscará templates/index.html)
        registry.addViewController("/").setViewName("index");
    }
}
