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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import javax.persistence.NoResultException;
import javax.validation.*;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200"})//Permite el acceso a este domino para poder enviar y recibir datos
@RestController //Para indicar que es un servicio REST
@RequestMapping("direccion") //Se va a consultar el servicio por medio de la direccion cliente
public class DireccionController {
    private static final Logger LOG = Logger.getLogger(DireccionController.class.getName());
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    @Autowired
    DireccionService direccionService;

    //Trae Todos los usuarios
    @RequestMapping(value = "/", method = RequestMethod.GET) //Se indica la ruta y el metodo que utiliza (GET)
    List<Direccion> obtenerDirecciones(){
        return direccionService.obtenerDirecciones();
    }

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)//se agrega al path una parametro que se recupera con la anotacion @PathVariable
    ResponseEntity<?> obtenerDireccionId(@PathVariable long id){

        Map<String,Object> response = new HashMap<>();
        Direccion cliente = direccionService.obtenerDireccionId(id);
        if (cliente!=null){
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } else {
            response.put("error", "No existe cliente con id " + id );
            return new ResponseEntity<Map>(response, HttpStatus.NOT_FOUND);
        }
    }

    @Secured("ROLE_ADMIN")
    //RequestBody para traer todo el cuerpo del objeto
    //@Valid para uqe reconosca las validaciones creadas en el Bean
    @ResponseStatus(HttpStatus.CREATED)//retorna un estado 201 Created
    @RequestMapping(value = "/", method = RequestMethod.POST)
    ResponseEntity<?> registrarDireccion(@Valid @RequestBody Direccion direccion, BindingResult binding){
        Set<ConstraintViolation<Direccion>> violations = validator.validate(direccion);
        LOG.info("Cliente a registrar: " + direccion);
        Direccion directionsNew = direccionService.registrarDireccion(direccion, binding);
        Map<String,Object> response = new HashMap<>();
        /*if(binding.hasErrors()){//Valida los errores del MODELO
            List<String> errors = binding.getFieldErrors().stream()
                    .map(err -> err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("error",errors);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }*/
        if(binding.hasErrors()){//Valida los errores del MODELO
            List<String> errors = new ArrayList<>();
            for(FieldError err: binding.getFieldErrors()){
                errors.add(err.getDefaultMessage());
            }
            response.put("error",errors);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
        if (directionsNew != null){
            return new ResponseEntity(directionsNew ,HttpStatus.CREATED);
        }else {
                response.put("error", "No es posible agregar cliente");
                LOG.warning("Error No es posible agregar cliente ");
                return new ResponseEntity(response, HttpStatus.NOT_FOUND);
            }
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity<?> actualizarDireccion(@Valid @RequestBody Direccion direccion, BindingResult binding) {
        Set<ConstraintViolation<Direccion>> violations = validator.validate(direccion);
        Map<String,Object> response = new HashMap<>();
        Direccion directionNew = null;
            directionNew = direccionService.actualizarDireccion(direccion, binding);

        if(binding.hasErrors()){//Valida los errores del MODELO
            List<String> errors = new ArrayList<>();
            for(FieldError err: binding.getFieldErrors()){
                errors.add(err.getDefaultMessage());
            }
            response.put("error",errors);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }

        if (directionNew != null){
            return new ResponseEntity(directionNew ,HttpStatus.CREATED);
        }else {
            LOG.warning("No es posible actualizar cliente");
            response.put("error", "No es posible actualizar cliente");
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }


    }

     @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> eliminarDireccion(@PathVariable long id){
        Map<String,Object> response = new HashMap<>();
        try {
            LOG.info("Controller Cliente a eliminar: " + id);
            direccionService.eliminarDireccion(id);
        }catch (Exception e){
            response.put("error", "No es posible eliminar el cliente");
            response.put("error", e.getMessage() + " " + e.getCause().getMessage());
            e.printStackTrace();
            return new ResponseEntity<Map>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El cliente se elimino con exito");
        return new ResponseEntity<Map>(response, HttpStatus.OK);

    }
}
