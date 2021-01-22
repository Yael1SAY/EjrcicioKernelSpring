package com.kernel.spring.dao;

import com.kernel.spring.model.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository//Para trabajar con CRUD Repository
public interface IUsuarioDao extends CrudRepository<Usuario, Long> {

    //public Usuario2 findByUsername(String nombreUsuario);

    @Query("select u from Usuario u where u.username = ?1")//consulta hql
    public Usuario findByUsername2(String username);//para buscar el usuario
}
