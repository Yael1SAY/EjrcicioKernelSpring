package com.kernel.spring.service;

import com.kernel.spring.dao.IDireccionDAO;
import com.kernel.spring.dto.ClienteDirDTO;
import com.kernel.spring.dto.ClienteFullDTO;
import com.kernel.spring.model.Cliente;
import com.kernel.spring.model.Direccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.*;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class DireccionService {

    private static final Logger LOG = Logger.getLogger(ClienteService.class.getName());

    @Autowired
    IDireccionDAO IDireccionDao;

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    public List<ClienteDirDTO> ObtenerDirecciones(){
        return IDireccionDao.ObtenerDirecciones();
    }

    public ClienteFullDTO ObtenerDireccionId(long id){
        ClienteFullDTO dto = new ClienteFullDTO();
        Direccion direccion = IDireccionDao.ObtenerDireccionId(id);
        try{
            Cliente cliente = direccion.getCliente();
            dto.setNombreCompleto(cliente.getNombre() + " " + cliente.getApellido());
            dto.setDireccion(direccion.getCalle() + ", " + direccion.getNoExterior() + ", " +
                    direccion.getCodPostal() + ", " + direccion.getEstado() + ", Referencia: " +
                    direccion.getReferencia() );
            LOG.info("Cliente: " + dto);
            return dto;
        }catch (Exception e){
            LOG.warning("No existe cliente con id: " + id);
            e.printStackTrace();
            return null;
        }
    }

    public Direccion RegistrarDireccion(Direccion direccion, BindingResult binding){
        LOG.info("bindingResult Service: " + binding);//Muestra cuantos errores arojo
        Set<ConstraintViolation<Direccion>> violations = validator.validate(direccion);
        if(!binding.hasErrors()){//Si no hay errores inserta la direccion
            Direccion dir = IDireccionDao.RegistrarDireccion(direccion);
            LOG.info("Se inserto corectamente el cliente");
            return dir;
        }else {//Si hay errores los imprime y retorna un null
            LOG.warning("Error no se inserto Cliente");
            for (ConstraintViolation<Direccion> violation : violations) {
                LOG.warning(violation.getMessage());
            }
            return null;
        }
    }

    public Direccion ActualizarDireccion(@RequestBody Direccion direccion, BindingResult binding){
        LOG.info("bindingResult Service: " + binding);
        Set<ConstraintViolation<Direccion>> violations = validator.validate(direccion);
        if(!binding.hasErrors()){
            Direccion dir = IDireccionDao.ActualizarDireccion(direccion);
            LOG.info("Se actualizo correctamente el cliente");
            return dir;
        }else{
            LOG.info("Error No se Actualizo cliente");
            for (ConstraintViolation<Direccion> violation : violations) {
                LOG.warning(violation.getMessage());
            }
            return null;
        }

    }

    public void EliminarDireccion(@PathVariable long id){
        try{
            IDireccionDao.EliminarDireccion(id);
            LOG.info("Se elimino correctamente el cliente con direccion id: " + id);
        }catch (Exception e) {
            LOG.warning("No existe el cliente con id: " + id);
            e.printStackTrace();
        }
    }
}
