
package com.kernel.spring.service;

import com.kernel.spring.dao.IClienteDAO;
import com.kernel.spring.dto.ClienteDTO;
import com.kernel.spring.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

/*
* En esta capa se agrega la logica de negocio
* */

@Service
public class ClienteService {

    private static final Logger LOG = Logger.getLogger(ClienteService.class.getName());

    @Autowired
    IClienteDAO IClienteDao;

    public List<ClienteDTO> obtenerClientes(){
        List<ClienteDTO> listCliente = IClienteDao.obtenerClientes();
        LOG.info("Service Lista Clientes: " + listCliente);
        return listCliente;
    }

    public ClienteDTO obtenerClienteId(long id){
        Cliente cliente = IClienteDao.obtenerClienteId(id);
        ClienteDTO clienteDto = new ClienteDTO();
        clienteDto.setNombre(cliente.getNombre());
        clienteDto.setApellido(cliente.getApellido());
        return clienteDto;
    }

    public Cliente registrarCliente(Cliente cliente){
        return IClienteDao.registrarCliente(cliente);
    }

    public Cliente actualizarCliente(Cliente cliente){
        return IClienteDao.actualizarCliente(cliente);
    }

    public void eliminarCliente(long id){
        IClienteDao.eliminarCliente(id);
    }
}
