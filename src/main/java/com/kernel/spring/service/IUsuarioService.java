package com.kernel.spring.service;

import com.kernel.spring.model.Usuario;
import org.springframework.data.jpa.repository.Query;

public interface IUsuarioService {

    @Query("select u from Usuario2 u where u.nombreUsuario = ?1")
    public Usuario findByUsername2(String nombreUsuario);
}
