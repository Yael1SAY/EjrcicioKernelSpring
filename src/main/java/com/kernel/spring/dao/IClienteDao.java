package com.kernel.spring.dao;

import com.kernel.spring.dto.ClienteDTO;
import com.kernel.spring.model.Cliente;

import java.util.List;

public interface IClienteDao {

    List<ClienteDTO> obtenerClientes();

    Cliente obtenerClienteId(long id);

    Cliente registrarCliente(Cliente cliente);

    Cliente actualizarCliente(Cliente cliente);

    void eliminarCliente(long id);

}
