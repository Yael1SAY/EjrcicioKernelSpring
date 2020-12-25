
package com.kernel.spring.service;

import com.kernel.spring.dao.IClienteDAO;
import com.kernel.spring.dao.IDireccionDAO;
import com.kernel.spring.dto.ClienteDTO;
import com.kernel.spring.dto.ClienteDirDTO;
import com.kernel.spring.model.Cliente;
import com.kernel.spring.model.Direccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
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
        List<ClienteDTO> listDto = null;
        List<Cliente> listCliente = IClienteDao.ObtenerClientes();
        //Iterator<Cliente> iter = listCliente.iterator();
        LOG.info("Service Lista Clientes: " + listCliente);
        /*while (iter.hasNext()){
            String nombre = (iter.next()).getNombre();
            String apellido = (iter.next()).getApellido();
            LOG.info("nombre: " + nombre);
            ClienteDTO clienteDto = new ClienteDTO(nombre, apellido);
            listDto.add(clienteDto);
            LOG.info("Service Cliente: " + listDto);
        }*/
        for (Cliente c: listCliente) {
            String nombre = c.getNombre();
            String apellido = c.getApellido();
            ClienteDTO clienteDto = new ClienteDTO(nombre, apellido);
            listDto.add(clienteDto);
        }
        return listDto;
    }

    public ClienteDTO ObtenerClienteId(long id){
        Cliente cliente = IClienteDao.ObtenerClienteId(id);
        ClienteDTO clienteDto = new ClienteDTO();
        clienteDto.setNombre(cliente.getNombre());
        clienteDto.setApellido(cliente.getApellido());
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
