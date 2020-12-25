package com.kernel.spring.dao;

import com.kernel.spring.dto.ClienteDTO;
import com.kernel.spring.model.Cliente;

import java.util.List;

public interface IClienteDAO {

    List<Cliente> ObtenerClientes();

    Cliente ObtenerClienteId(long id);

    Cliente RegistrarCliente(Cliente cliente);

    Cliente ActualizarCliente(Cliente cliente);

    void EliminarCliente(long id);

}
