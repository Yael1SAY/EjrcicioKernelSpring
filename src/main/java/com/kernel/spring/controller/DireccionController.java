package com.kernel.spring.controller;

import com.kernel.spring.model.Direccion;
import com.kernel.spring.service.DireccionService;
import com.kernel.spring.service.ValidaCampos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.*;
import java.util.logging.Logger;

@CrossOrigin(origins = {"http://localhost:4200"})//Permite el acceso a este domino para poder enviar y recibir datos
@RestController //Para indicar que es un servicio REST
@RequestMapping("direccion") //Se va a consultar el servicio por medio de la direccion cliente
public class DireccionController {
    private static final Logger LOG = Logger.getLogger(DireccionController.class.getName());
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    @Autowired
    DireccionService direccionService;

    @Autowired
    ValidaCampos validaCampos;

    //Trae Todos los usuarios
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @RequestMapping(value = "/", method = RequestMethod.GET) //Se indica la ruta y el metodo que utiliza (GET)
    List<Direccion> obtenerDirecciones(){
        return direccionService.obtenerDirecciones();
    }

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)//se agrega al path una parametro que se recupera con la anotacion @PathVariable
    ResponseEntity<?> obtenerDireccionId(@PathVariable long id){

        Map<String,Object> response = new HashMap<>();
        Direccion cliente;

        try{
            cliente = direccionService.obtenerDireccionId(id);
        }catch (Exception e){
            e.printStackTrace();
            response.put("mensaje","Error al buscar el cliente en la base de datos");
            response.put("error", e.getMessage() + ": " +e.getCause().getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (cliente!=null){
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } else {
            response.put("error", "No existe cliente con id " + id );
            return new ResponseEntity<Map>(response, HttpStatus.NOT_FOUND);
        }
    }

    /*@Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.CREATED)//retorna un estado 201 Created
    @RequestMapping(value = "/", method = RequestMethod.POST)
    ResponseEntity<?> registrarDireccion(@Valid @RequestBody Direccion direccion, BindingResult binding){
        //Set<ConstraintViolation<Direccion>> violations = validator.validate(direccion);
        LOG.info("Usuario a registrar: " + direccion);
        Direccion directionsNew = direccionService.registrarDireccion(direccion, binding);
        Map<String,Object> response = new HashMap<>();
        //if(binding.hasErrors()){//Valida los errores del MODELO
          //  List<String> errors = binding.getFieldErrors().stream()
            //        .map(err -> err.getDefaultMessage())
              //      .collect(Collectors.toList());
            //response.put("error",errors);
            //return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        //}
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
    }*/

    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.CREATED)//retorna un estado 201 Created
    @RequestMapping(value = "/", method = RequestMethod.POST)
    ResponseEntity<?> registrarDireccion(@Valid @RequestBody Direccion direccion, BindingResult binding){
        LOG.info("Usuario a registrar: " + direccion);
        Map<String,Object> response = new HashMap<>();
        List<String> error = validaCampos.validaCorreo(direccion);
        Direccion directionsNew;
        if(error.isEmpty()){
            try{
                directionsNew = direccionService.registrarDireccion(direccion, binding);
            }catch (DataAccessException e){
                e.printStackTrace();
                response.put("error","Error al realizar el insert en la base de datos");
                response.put("error", e.getMostSpecificCause().getMessage());
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            //Errores del Bean
            if(binding.hasErrors()){
                List<String> errors = new ArrayList<>();
                for(FieldError err: binding.getFieldErrors()){
                    errors.add(err.getDefaultMessage());
                }
                response.put("error",errors);
                return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            }

        }else {
            response.put("error", error);
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(directionsNew ,HttpStatus.CREATED);
    }


    /*@Secured("ROLE_ADMIN")
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


    }*/

    /*@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity<?> actualizarDireccion(@Valid @RequestBody Direccion direccion, BindingResult binding) {
        Map<String,Object> response = new HashMap<>();
        Direccion directionNew = null;
        List<String> error = validaCampos.validaCorreo(direccion);
        LOG.info("Error de validacion: " + error);
        if (error.isEmpty()) {
            try{
                directionNew = direccionService.actualizarDireccion(direccion, binding);
            }catch (DataAccessException e){
                e.printStackTrace();
                response.put("mensaje","Error al realizar la actualizacion en la base de datos");
                response.put("error", e.getMostSpecificCause().getMessage());
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else {
            response.put("error",error);
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(directionNew, HttpStatus.CREATED);
    }*/


    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity<?> actualizarDireccion(@Valid @RequestBody Direccion direccion, BindingResult binding) {
        Map<String,Object> response = new HashMap<>();
        LOG.info("direccion recibida: " + direccion);
        Direccion direccionActual = direccionService.obtenerDireccionId(direccion.getIdDireccion());
        Direccion directionNew;

        List<String> error = validaCampos.validaCorreo(direccion);

        if (error.isEmpty()) {
            try{
                direccionActual.getUsuario().setEmail(direccion.getUsuario().getEmail());
                direccionActual.getUsuario().setUsername(direccion.getUsuario().getUsername());
                direccionActual.getUsuario().setNombre(direccion.getUsuario().getNombre());
                direccionActual.getUsuario().setApellido(direccion.getUsuario().getApellido());
                direccionActual.getUsuario().setCurp(direccion.getUsuario().getCurp());
                direccionActual.getUsuario().setEdad(direccion.getUsuario().getEdad());
                direccionActual.getUsuario().setPassword(direccion.getUsuario().getPassword());
                direccionActual.setCalle(direccion.getCalle());
                direccionActual.setNoExterior(direccion.getNoExterior());
                direccionActual.setCodPostal(direccion.getCodPostal());
                direccionActual.setMunicipio(direccion.getMunicipio());
                direccionActual.setReferencia(direccion.getReferencia());
                direccionActual.getEstado().setNombre(direccion.getEstado().getNombre());

                directionNew = direccionService.actualizarDireccion(direccionActual, binding);
            }catch (DataAccessException e){
                e.printStackTrace();
                response.put("mensaje","Error al realizar la actualizacion en la base de datos");
                response.put("error", e.getMostSpecificCause().getMessage());
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else {
            LOG.info("Error de validacion: " + error);
            response.put("error",error);
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(directionNew, HttpStatus.CREATED);
    }


    //@Secured({"ROLE_ADMIN","ROLE_USER"})
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.PUT)
    ResponseEntity<?> eliminarDireccion(@PathVariable long id){
        Map<String,Object> response = new HashMap<>();
        try {
            if (direccionService.obtenerDireccionId(id) != null){
                if (direccionService.obtenerDireccionId(id).getState() == 0){
                    LOG.info("Controller Usuario a eliminar: " + id);
                    direccionService.eliminarDireccion(id);
                }else {
                    response.put("error", "El cliente con id: " + id + " ya se encuentra en estado eliminado");
                    return new ResponseEntity<Map>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }else {
                response.put("error", "No existe el cliente con id: " + id);
                return new ResponseEntity<Map>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (DataAccessException e){
            response.put("error", "No es posible eliminar el cliente");
            response.put("error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            e.printStackTrace();
            return new ResponseEntity<Map>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El cliente se elimino con exito");
        return new ResponseEntity<Map>(response, HttpStatus.OK);

    }

    /*@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/municipio/{codigoPostal}", method = RequestMethod.GET)
    ResponseEntity<?> obtenerDireccion(@PathVariable int codigoPostal){
        Municipio municipio = direccionService.obtenerDireccion(codigoPostal);
        return new ResponseEntity<>(municipio, HttpStatus.OK);
    }*/

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/estados")
    ResponseEntity<?> ontenerEstados(){
        return new ResponseEntity<>(direccionService.obtenerEstados(), HttpStatus.OK);
    }
}
