package com.esliceu.myHbo;

import com.esliceu.myHbo.filter.SessionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@SpringBootApplication
public class MyHboApplication implements WebMvcConfigurer {

    @Autowired
    SessionInterceptor sessionInterceptor;

    public static void main(String[] args) {
        SpringApplication.run(MyHboApplication.class, args);
    }


    @Override
public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(sessionInterceptor)
            .addPathPatterns("/**")  // Proteger TODAS las rutas
            .excludePathPatterns(
                "/login",           // Login
                "/register",        // Registro
                "/movies/search",   // Búsqueda de películas
                "/movies/results",  // Resultados de búsqueda
                "/movies/info",     // Información de película
                "/api/movies/autocomplete"  // API para autocompletado
            );

    }
}
