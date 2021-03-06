package com.kernel.spring.controller;

import com.kernel.spring.dto.ClienteDTO;
import com.kernel.spring.model.Usuario;
import com.kernel.spring.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController //Para indicar que es un servicio REST
@RequestMapping("cliente") //Se va a consultar el servicio por medio de la direccion cliente
public class ClienteController {

    private static final Logger LOG = Logger.getLogger(ClienteController.class.getName());

    @Autowired
    ClienteService clienteService;

    //Mostrar TODOS los usuarios
    @RequestMapping(value = "/", method = RequestMethod.GET)
    List<ClienteDTO> obtenerClientes(){
        List<ClienteDTO> listCliente = clienteService.obtenerClientes();
        LOG.info("Controller lista Clientes: " + listCliente);
        return listCliente;
    }

    //Mostrar UN usuario
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ClienteDTO obtenerClienteId(@PathVariable long id){
        ClienteDTO dto = clienteService.obtenerClienteId(id);
        LOG.info("Controller ClienteDTO: " + dto);
        return dto;
    }

    //Registrar un usuario
    @RequestMapping(value = "/", method = RequestMethod.POST)
    Usuario registrarCliente(@RequestBody Usuario usuario){
        LOG.info("Usuario a Registrar: " + usuario);
        return clienteService.registrarCliente(usuario);
    }

    //Actualizar un Usuario
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    Usuario actualizarCliente(@RequestBody Usuario usuario){
        return clienteService.actualizarCliente(usuario);
    }

    //Eliminar un Usuario
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    void eliminarCliente(@PathVariable long id){
        clienteService.eliminarCliente(id);
    }

}