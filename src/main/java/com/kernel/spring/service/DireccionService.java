package com.kernel.spring.service;

import com.kernel.spring.dao.DireccionDAO;
import com.kernel.spring.model.Direccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class DireccionService {

    @Autowired
    DireccionDAO direccionDao;

    public List<Direccion> ObtenerDirecciones(){
        return direccionDao.ObtenerDirecciones();
    }

    public Direccion ObtenerDireccionId(long id){
        return direccionDao.ObtenerDireccionId(id);
    }

    public Direccion RegistrarDireccion(Direccion direccion){
        return direccionDao.RegistrarDireccion(direccion);
    }

    public Direccion ActualizarDireccion(@RequestBody Direccion direccion){
        return direccionDao.ActualizarDireccion(direccion);
    }

    public void EliminarDireccion(@PathVariable long id){
        direccionDao.EliminarDireccion(id);
    }
}
