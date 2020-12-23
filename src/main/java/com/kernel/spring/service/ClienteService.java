
package com.kernel.spring.service;

import com.kernel.spring.dao.ClienteDAO;
import com.kernel.spring.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    ClienteDAO clienteDao;

    public List<Cliente> ObtenerClientes(){
        return clienteDao.ObtenerClientes();
    }

    public Cliente ObtenerClienteId(long id){
        return clienteDao.ObtenerClienteId(id);
    }

    public Cliente RegistrarCliente(Cliente cliente){
        return clienteDao.RegistrarCliente(cliente);
    }

    public Cliente ActualizarCliente(@RequestBody Cliente cliente){
        return clienteDao.ActualizarCliente(cliente);
    }

    public void EliminarCliente(@PathVariable long id){
        clienteDao.EliminarCliente(id);
    }
}
