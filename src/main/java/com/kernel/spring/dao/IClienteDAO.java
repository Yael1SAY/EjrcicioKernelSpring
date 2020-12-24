package com.kernel.spring.dao;

import com.kernel.spring.model.Cliente;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

public interface IClienteDAO {

    List<Cliente> ObtenerClientes();

    Cliente ObtenerClienteId(@PathVariable long id);

    Cliente RegistrarCliente(@RequestBody Cliente cliente);

    Cliente ActualizarCliente(@RequestBody Cliente cliente);

    void EliminarCliente(@PathVariable long id);

}
