package com.kernel.spring.controller;

import com.kernel.spring.dto.ClienteDTO;
import com.kernel.spring.model.Cliente;
import com.kernel.spring.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("cliente")
public class ClienteController {

    private static final Logger LOG = Logger.getLogger(ClienteController.class.getName());

    @Autowired
    ClienteService clienteService;

    //Mostrar TODOS los usuarios
    @RequestMapping(value = "/", method = RequestMethod.GET)
    List<ClienteDTO> ObtenerClientes(){
        List<ClienteDTO> listDto = clienteService.ObtenerClientes();
        LOG.info("Controller lista Clientes: " + listDto);
        return listDto;
    }

    //Mostrar UN usuario
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ClienteDTO ObtenerClienteId(@PathVariable long id){
        ClienteDTO dto = clienteService.ObtenerClienteId(id);
        LOG.info("Controller ClienteDTO: " + dto);
        return dto;
    }

    //Registrar un usuario
    @RequestMapping(value = "/", method = RequestMethod.POST)
    Cliente RegistrarCliente(@RequestBody Cliente cliente){
        return clienteService.RegistrarCliente(cliente);
    }

    //Actualizar un Cliente
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    Cliente ActualizarCliente(@RequestBody Cliente cliente){
        return clienteService.ActualizarCliente(cliente);
    }

    //Eliminar un Cliente
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    void EliminarCliente(@PathVariable long id){
        clienteService.EliminarCliente(id);
    }

}
