package com.kernel.spring.controller;

import com.kernel.spring.dto.ClienteDirDTO;
import com.kernel.spring.dto.ClienteFullDTO;
import com.kernel.spring.model.Direccion;
import com.kernel.spring.service.ClienteService;
import com.kernel.spring.service.DireccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.logging.Logger;

@RestController //Para indicar que es un servicio REST
@RequestMapping("direccion") //Se va a consultar el servicio por medio de la direccion cliente
public class DireccionController {
    private static final Logger LOG = Logger.getLogger(ClienteService.class.getName());

    @Autowired
    DireccionService direccionService;

    //Trae Todos los usuarios
    @RequestMapping(value = "/", method = RequestMethod.GET) //Se indica la ruta y el metodo que utiliza (GET)
    List<ClienteDirDTO> ObtenerDirecciones(){
        return direccionService.ObtenerDirecciones();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)//se agrega al path una parametro que se recupera con la anotacion @PathVariable
    ClienteFullDTO ObtenerDireccionId(@PathVariable long id){
        return direccionService.ObtenerDireccionId(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    //RequestBody para traer todo el cuerpo del objeto
    //@Valid para uqe reconosca las validaciones creadas en el Bean
    Direccion RegistrarDireccion(@Valid @RequestBody Direccion direccion, BindingResult binding){
        LOG.info("Binding Controller: " + binding);//BindingResult trae el numero de errores de validacion
        return direccionService.RegistrarDireccion(direccion, binding);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    Direccion ActualizarDireccion(@Valid @RequestBody Direccion direccion, BindingResult binding){
        return direccionService.ActualizarDireccion(direccion, binding);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    void EliminarDireccion(@PathVariable long id){
        direccionService.EliminarDireccion(id);
    }
}
