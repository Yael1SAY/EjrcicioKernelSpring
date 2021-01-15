package com.kernel.spring.service;

import com.kernel.spring.dao.IUsuarioDao;
import com.kernel.spring.model.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


//Obtener el usuario y sus roles
@Service
public class UsuarioService implements UserDetailsService, IUsuarioService {

    private Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired//se inyecta el objeto
    private IUsuarioDao usuarioDao;

    @Override
    @Transactional(readOnly = true)//transaccion Solo de lectura
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {

        Usuario usuario = usuarioDao.findByUsername2(nombreUsuario);//obtener el usuario

        //valida si el usuario existe
        if (usuario == null){//se implementa log por si el usuario no existe
            logger.error("Error en el Login: no existe el usuario: " + usuario + "en el sistema");
            throw new UsernameNotFoundException("Error en el Login: no existe el usuario: " + usuario + "en el sistema");
        }

        //Se obtiene los roles del usuario
        List<GrantedAuthority> authorities = usuario.getRoles()//Se obtiene la lista de roles
                .stream()//Transforma a un tipo stream
                .map(role -> new SimpleGrantedAuthority(role.getNombreRol()))//transforma stream a SimpleGrantedAuthority
                .peek(authority -> logger.info("Role: " + authority.getAuthority()))//se imprime el rol en el log
                .collect(Collectors.toList());//Transforma a un tipo list

        //retorna los datos del usuario
        return new User(usuario.getNombreUsuario(),usuario.getPassword(), usuario.isEstatus(), true, true, true, authorities);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findByUsername2(String nombreUsuario) {
        return usuarioDao.findByUsername2(nombreUsuario);
    }
}

