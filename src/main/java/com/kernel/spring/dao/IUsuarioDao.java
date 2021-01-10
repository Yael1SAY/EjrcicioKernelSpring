package com.kernel.spring.dao;

import com.kernel.spring.model.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioDao extends CrudRepository<Usuario, Long> {

    //public Usuario findByUsername(String nombreUsuario);

    @Query("select u from Usuario u where u.nombreUsuario = ?1")
    public Usuario findByUsername2(String nombreUsuario);
}
