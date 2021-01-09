package com.kernel.spring.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.kernel.spring.dto.ClienteDirDTO;
import com.kernel.spring.dto.ClienteFullDTO;
import com.kernel.spring.model.Direccion;
import com.kernel.spring.service.ClienteService;
import com.kernel.spring.service.DireccionService;
import com.sun.xml.internal.ws.handler.HandlerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import javax.persistence.NoResultException;
import javax.validation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

@CrossOrigin(origins = {"http://localhost:4200"})//Permite el acceso a este domino para poder enviar y recibir datos
@RestController //Para indicar que es un servicio REST
@RequestMapping("direccion") //Se va a consultar el servicio por medio de la direccion cliente
public class DireccionController {
    private static final Logger LOG = Logger.getLogger(ClienteService.class.getName());
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();


    @Autowired
    DireccionService direccionService;

    //Trae Todos los usuarios
    @RequestMapping(value = "/", method = RequestMethod.GET) //Se indica la ruta y el metodo que utiliza (GET)
    List<ClienteDirDTO> obtenerDirecciones(){
        return direccionService.obtenerDirecciones();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)//se agrega al path una parametro que se recupera con la anotacion @PathVariable
    ResponseEntity<?> obtenerDireccionId(@PathVariable long id){
        ClienteDirDTO clieteFull = direccionService.obtenerDireccionId(id);
        Map<String,Object> response = new HashMap<>();
        try {
            return new ResponseEntity<>(clieteFull, HttpStatus.OK);
        } catch (Exception e){
            response.put("mensaje", "No existe cliente con id " + id );
            response.put("error", e.getMessage()+ " " + e.getCause().getMessage());
            e.printStackTrace();
            return new ResponseEntity<Map>(response, HttpStatus.NOT_FOUND);
        }
    }

    //RequestBody para traer todo el cuerpo del objeto
    //@Valid para uqe reconosca las validaciones creadas en el Bean
    @ResponseStatus(HttpStatus.CREATED)//retorna un estado 201 Created
    @RequestMapping(value = "/", method = RequestMethod.POST)
    ResponseEntity<?> registrarDireccion(@Valid @RequestBody Direccion direccion, BindingResult binding){
        LOG.info("Entro a metodo registrar del controller");
        Set<ConstraintViolation<Direccion>> violations = validator.validate(direccion);
        LOG.info("Cliente a registrar: " + direccion);
        Direccion directionsNew = direccionService.registrarDireccion(direccion, binding);
        Map<String,Object> response = new HashMap<>();
        if (directionsNew != null){
            return new ResponseEntity(directionsNew ,HttpStatus.CREATED);
        }else {
            for (ConstraintViolation<Direccion> violation : violations) {
                LOG.warning(violation.getMessage());
                response.put("error", violation.getMessage());
            }
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity<?> actualizarDireccion(@Valid @RequestBody Direccion direccion, BindingResult binding) {
        Set<ConstraintViolation<Direccion>> violations = validator.validate(direccion);
        Map<String,Object> response = new HashMap<>();
        Direccion directionNew = null;
            directionNew = direccionService.actualizarDireccion(direccion, binding);
        if (directionNew != null){
            return new ResponseEntity(directionNew ,HttpStatus.CREATED);
        }else {
            for (ConstraintViolation<Direccion> violation : violations) {
                LOG.warning(violation.getMessage());
                response.put("error", violation.getMessage());
            }
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> eliminarDireccion(@PathVariable long id){
        Map<String,Object> response = new HashMap<>();
        try {
            LOG.info("Controller Cliente a eliminar: " + id);
            direccionService.eliminarDireccion(id);
        }catch (Exception e){
            response.put("menasje", "No es posible eliminar el cliente");
            response.put("error", e.getMessage() + " " + e.getCause().getMessage());
            e.printStackTrace();
            return new ResponseEntity<Map>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El cliente se elimino con exito");
        return new ResponseEntity<Map>(response, HttpStatus.OK);

    }
}
