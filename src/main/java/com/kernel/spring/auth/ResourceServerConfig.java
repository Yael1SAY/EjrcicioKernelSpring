package com.kernel.spring.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {//se otorga todos los permisos a esta ruta
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/direccion/").permitAll()
        .anyRequest().authenticated();
        //permite acceder a la ruta de la api a usuarios autenticados
    }
}
