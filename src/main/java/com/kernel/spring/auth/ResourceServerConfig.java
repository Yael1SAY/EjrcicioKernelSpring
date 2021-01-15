package com.kernel.spring.auth;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.util.Arrays;


//se encarga de Administrar los permisos y accesos
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

//Se otorgan los permisos a las rutas
    @Override
    public void configure(HttpSecurity http) throws Exception {//se otorga todos los permisos a esta ruta
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/direccion/").permitAll()
        //.antMatchers(HttpMethod.GET, "/direccion/{id}").permitAll()//.hasAnyRole("USER", "ADMIN")//Automaticamente reconoce ROL_USER Y ROLE_ADMIN
        //.antMatchers(HttpMethod.POST, "/direccion/").permitAll()//.hasRole("ADMIN")
        //.antMatchers("/direccion/**").permitAll()//.hasRole("ADMIN")
        //.anyRequest().authenticated()
        //permite acceder a la ruta de la api a usuarios autenticados
        .and().cors().configurationSource(corsConfigurationSource());
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));//PERMITE EL DOMINIO
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
        configuration.setAllowCredentials(true);//permitir credenciales
        configuration.setAllowedHeaders(Arrays.asList("Content-type","Authorization"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter(){

        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

}


