package com.kernel.spring.service;

import com.kernel.spring.model.Direccion;
import com.kernel.spring.model.Estado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.logging.Logger;

@Service //indica que es un servicio
public class DireccionService {

    private static final Logger LOG = Logger.getLogger(DireccionService.class.getName());

    @Autowired//inyeccion de dependencias de la interface IDireccion
    com.kernel.spring.dao.IDireccionDao IDireccionDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    //ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    //Validator validator = factory.getValidator();

    public List<Direccion> obtenerDirecciones(){
        List<Direccion> list = IDireccionDao.obtenerDirecciones();
        return list;
    }

    public Direccion obtenerDireccionId(long id) {
        //ClienteFullDTO dto = new ClienteFullDTO();
            return IDireccionDao.obtenerDireccionId(id);
            /*if(direccion != null) {
                Usuario cliente = direccion.getUsuario();
                dto.setDatosCliente("Correo: " + cliente.getCorreo() + ", Nombre Completo: " + cliente.getNombre() +
                        " " + cliente.getApellido() + ", edad: " + cliente.getEdad());
                dto.setDireccion(direccion.getCalle() + ", Número " + direccion.getNoExterior() + ", " +
                        direccion.getCodPostal() + ", " + direccion.getMunicipio() + ", " + direccion.getEstado() +
                        ", Referencia: " + direccion.getReferencia());
                LOG.info("Usuario: " + dto);
                return dto;
            }*/
            //LOG.info("No existe cliente con id + " + id);

    }

    public Direccion registrarDireccion(Direccion direccion, BindingResult binding) {
        LOG.info("bindingResult Service: " + binding);//Muestra cuantos errores arojo
        //Set<ConstraintViolation<Direccion>> violations = validator.validate(direccion);
        if(!binding.hasErrors()){//Si no hay errores inserta la direccion
            direccion.getUsuario().setPassword(passwordEncoder.encode(direccion.getUsuario().getPassword()));
            Direccion dir = IDireccionDao.registrarDireccion(direccion);
            LOG.info("Se inserto corectamente el cliente");
            return dir;
        }else {//Si hay errores los imprime y retorna un null
            LOG.warning("Service Error no se inserto Usuario");
            /*for (ConstraintViolation<Direccion> violation : violations) {
                LOG.warning(violation.getMessage());
            }*/
            return null;
        }
    }

    public Direccion actualizarDireccion(@RequestBody Direccion direccion, BindingResult binding) {
        LOG.info("bindingResult Service: " + binding);
        //Set<ConstraintViolation<Direccion>> violations = validator.validate(direccion);
        if(!binding.hasErrors()){
            Direccion dir = IDireccionDao.actualizarDireccion(direccion);
            LOG.info("Se actualizo correctamente el cliente");
            return dir;
        }else{
            LOG.info("Error No se Actualizo cliente");
            /*for (ConstraintViolation<Direccion> violation : violations) {
                LOG.warning(violation.getMessage());
            }*/
            return null;
        }
    }

    public void eliminarDireccion(@PathVariable long id){
        LOG.info("Service id a eliminar" + id);
            IDireccionDao.eliminarDireccion(id);
            LOG.info("Se elimino correctamente el cliente con direccion id: " + id);
    }

    /*public Municipio obtenerDireccion(int codigoPostal){
        return IDireccionDao.obtenerDireccion(codigoPostal);
    }*/

    public List<Estado> obtenerEstados(){
        return IDireccionDao.obtenerEstados();
    }


}
