
package com.kernel.spring.service;

import com.kernel.spring.dto.ClienteDTO;
import com.kernel.spring.model.Usuario;
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
    com.kernel.spring.dao.IClienteDao IClienteDao;

    public List<ClienteDTO> obtenerClientes(){
        List<ClienteDTO> listCliente = IClienteDao.obtenerClientes();
        LOG.info("Service Lista Clientes: " + listCliente);
        return listCliente;
    }

    public ClienteDTO obtenerClienteId(long id){
        Usuario usuario = IClienteDao.obtenerClienteId(id);
        ClienteDTO clienteDto = new ClienteDTO();
        clienteDto.setCorreo(usuario.getEmail());
        clienteDto.setNombre(usuario.getNombre());
        clienteDto.setApellido(usuario.getApellido());
        clienteDto.setEdad(usuario.getEdad());
        return clienteDto;
    }

    public Usuario registrarCliente(Usuario usuario){
        return IClienteDao.registrarCliente(usuario);
    }

    public Usuario actualizarCliente(Usuario usuario){
        return IClienteDao.actualizarCliente(usuario);
    }

    public void eliminarCliente(long id){
        IClienteDao.eliminarCliente(id);
    }
}
