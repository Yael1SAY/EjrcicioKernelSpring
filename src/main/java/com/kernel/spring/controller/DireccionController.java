package com.kernel.spring.controller;

import com.kernel.spring.dto.ClienteDTO;
import com.kernel.spring.dto.ClienteDirDTO;
import com.kernel.spring.model.Direccion;
import com.kernel.spring.service.DireccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("direccion")
public class DireccionController {

    @Autowired
    DireccionService direccionService;

    //Trae Todos los usuarios
    @RequestMapping(value = "/", method = RequestMethod.GET)
    List<ClienteDirDTO> ObtenerDirecciones(){
        return direccionService.ObtenerDirecciones();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ClienteDirDTO ObtenerDireccionId(@PathVariable long id){
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
