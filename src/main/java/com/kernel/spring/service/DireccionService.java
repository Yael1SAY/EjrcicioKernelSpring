package com.kernel.spring.service;

import com.kernel.spring.dao.IDireccionDAO;
import com.kernel.spring.dto.ClienteDirDTO;
import com.kernel.spring.dto.ClienteFullDTO;
import com.kernel.spring.model.Cliente;
import com.kernel.spring.model.Direccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.logging.Logger;

@Service
public class DireccionService {

    private static final Logger LOG = Logger.getLogger(ClienteService.class.getName());

    @Autowired
    IDireccionDAO IDireccionDao;

    public List<ClienteDirDTO> ObtenerDirecciones(){
        List<ClienteDirDTO> listD = IDireccionDao.ObtenerDirecciones();
        return listD;
    }

    public ClienteFullDTO ObtenerDireccionId(long id){
        ClienteFullDTO dto = new ClienteFullDTO();
        Direccion direccion = IDireccionDao.ObtenerDireccionId(id);
        Cliente cliente = direccion.getCliente();
        dto.setNombreCompleto(cliente.getNombre() + " " + cliente.getApellido());
        dto.setDireccion(direccion.getCalle() + ", " + direccion.getNoExterior() + ", " +
                direccion.getCodPostal() + ", " + direccion.getEstado() + ", Referencia: " +
                direccion.getReferencia() );
        return dto;
    }

    public Direccion RegistrarDireccion(Direccion direccion){
        return IDireccionDao.RegistrarDireccion(direccion);
    }

    public Direccion ActualizarDireccion(@RequestBody Direccion direccion){
        return IDireccionDao.ActualizarDireccion(direccion);
    }

    public void EliminarDireccion(@PathVariable long id){
        //try {
            IDireccionDao.EliminarDireccion(id);
        //}catch (UnexpectedRollbackException e){
            //e.printStackTrace();
        //}
    }
}
