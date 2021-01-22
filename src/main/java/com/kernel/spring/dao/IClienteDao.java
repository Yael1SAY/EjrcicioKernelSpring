package com.kernel.spring.dao;

import com.kernel.spring.dto.ClienteDTO;
import com.kernel.spring.model.Usuario;

import java.util.List;

public interface IClienteDao {

    List<ClienteDTO> obtenerClientes();

    Usuario obtenerClienteId(long id);

    Usuario registrarCliente(Usuario usuario);

    Usuario actualizarCliente(Usuario usuario);

    void eliminarCliente(long id);

}
