package com.kernel.spring.service;

import com.kernel.spring.dao.IDireccionDAO;
import com.kernel.spring.dto.ClienteDTO;
import com.kernel.spring.model.Direccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class DireccionService {

    @Autowired
    IDireccionDAO IDireccionDao;

    public List<ClienteDTO> ObtenerDirecciones(){
        return IDireccionDao.ObtenerDirecciones();
    }

    public Direccion ObtenerDireccionId(long id){
        return IDireccionDao.ObtenerDireccionId(id);
    }

    public Direccion RegistrarDireccion(Direccion direccion){
        return IDireccionDao.RegistrarDireccion(direccion);
    }

    public Direccion ActualizarDireccion(@RequestBody Direccion direccion){
        return IDireccionDao.ActualizarDireccion(direccion);
    }

    public void EliminarDireccion(@PathVariable long id){
        IDireccionDao.EliminarDireccion(id);
    }
}
