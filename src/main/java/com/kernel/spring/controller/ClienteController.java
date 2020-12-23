package com.kernel.spring.controller;

import com.kernel.spring.model.Cliente;
import com.kernel.spring.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("client")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    //Trae Todos los usuarios
    @RequestMapping(value = "/", method = RequestMethod.GET)
    List<Cliente> ObtenerClientes(){
        return clienteService.ObtenerClientes();
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Cliente ObtenerClienteId(@PathVariable long id){
        return clienteService.ObtenerClienteId(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    Cliente RegistrarCliente(@RequestBody Cliente cliente){
        return clienteService.RegistrarCliente(cliente);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    Cliente ActualizarCliente(@RequestBody Cliente cliente){
        return clienteService.ActualizarCliente(cliente);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    void EliminarCliente(@PathVariable long id){
        clienteService.EliminarCliente(id);
    }

}
