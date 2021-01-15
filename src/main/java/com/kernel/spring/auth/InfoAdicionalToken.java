package com.kernel.spring.auth;

import com.kernel.spring.model.Usuario;
import com.kernel.spring.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InfoAdicionalToken implements TokenEnhancer {

    @Autowired
    private IUsuarioService usuarioService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
    //Envia el id del usuario y nombre de usuario al autenticar
        Usuario usuario = usuarioService.findByUsername2(oAuth2Authentication.getName());
        Map<String, Object> info = new HashMap<>();
        info.put("info_adicional","Hola que tal!: " + usuario.getNombreUsuario());
        info.put("nombre",usuario.getNombre());
        info.put("apellido",usuario.getApellido());
        info.put("email",usuario.getEmail());
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(info);
        return oAuth2AccessToken;
    }
}
