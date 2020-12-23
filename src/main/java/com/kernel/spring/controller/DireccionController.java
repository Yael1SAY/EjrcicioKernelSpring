package com.kernel.spring.controller;

import com.kernel.spring.model.Cliente;
import com.kernel.spring.model.Direccion;
import com.kernel.spring.service.DireccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("direc")
public class DireccionController {

    @Autowired
    DireccionService direccionService;

    //Trae Todos los usuarios
    @RequestMapping(value = "/", method = RequestMethod.GET)
    List<Direccion> ObtenerDirecciones(){
        return direccionService.ObtenerDirecciones();
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Direccion ObtenerDireccionId(@PathVariable long id){
        return direccionService.ObtenerDireccionId(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    Direccion RegistrarDireccion(@RequestBody Direccion direccion){
        return direccionService.RegistrarDireccion(direccion);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    Direccion ActualizarDireccion(@RequestBody Direccion direccion){
        return direccionService.ActualizarDireccion(direccion);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    void EliminarDireccion(@PathVariable long id){
        direccionService.EliminarDireccion(id);
    }
}
