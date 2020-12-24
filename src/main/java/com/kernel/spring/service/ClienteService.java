
package com.kernel.spring.service;

import com.kernel.spring.dao.IClienteDAO;
import com.kernel.spring.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    IClienteDAO IClienteDao;

    public List<Cliente> ObtenerClientes(){
        return IClienteDao.ObtenerClientes();
    }

    public Cliente ObtenerClienteId(long id){
        return IClienteDao.ObtenerClienteId(id);
    }

    public Cliente RegistrarCliente(Cliente cliente){
        return IClienteDao.RegistrarCliente(cliente);
    }

    public Cliente ActualizarCliente(@RequestBody Cliente cliente){
        return IClienteDao.ActualizarCliente(cliente);
    }

    public void EliminarCliente(@PathVariable long id){
        IClienteDao.EliminarCliente(id);
    }
}
