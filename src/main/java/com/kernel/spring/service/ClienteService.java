
package com.kernel.spring.service;

import com.kernel.spring.dao.IClienteDAO;
import com.kernel.spring.dao.IDireccionDAO;
import com.kernel.spring.dto.ClienteDTO;
import com.kernel.spring.model.Cliente;
import com.kernel.spring.model.Direccion;
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
    @Autowired
    IDireccionDAO IDireccionDao;

    public List<ClienteDTO> ObtenerClientes(){
        List<ClienteDTO> listDto = IClienteDao.ObtenerClientes();
        LOG.info("Service Lista Clientes: " + listDto);
        return listDto;
    }

    public ClienteDTO ObtenerClienteId(long id){
        ClienteDTO clienteDto;
        Cliente cliente = IClienteDao.ObtenerClienteId(id);
        LOG.info("Service Cliente: " + cliente);
        Direccion direccion = IDireccionDao.ObtenerDireccionId(id);

        clienteDto = new ClienteDTO();
        clienteDto.setNombre(cliente.getNombre());
        clienteDto.setApellido(cliente.getApellido());
        LOG.info("Service Cliente DTO: " + clienteDto);
        return clienteDto;
    }

    public Cliente RegistrarCliente(Cliente cliente){
        return IClienteDao.RegistrarCliente(cliente);
    }

    public Cliente ActualizarCliente(Cliente cliente){
        return IClienteDao.ActualizarCliente(cliente);
    }

    public void EliminarCliente(long id){
        IClienteDao.EliminarCliente(id);
    }
}
