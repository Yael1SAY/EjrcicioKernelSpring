package com.kernel.spring.service;

import com.kernel.spring.dao.IClienteDAO;
import com.kernel.spring.dao.IDireccionDAO;
import com.kernel.spring.dto.ClienteDTO;
import com.kernel.spring.dto.ClienteDirDTO;
import com.kernel.spring.model.Cliente;
import com.kernel.spring.model.Direccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

@Service
public class DireccionService {

    private static final Logger LOG = Logger.getLogger(ClienteService.class.getName());

    @Autowired
    IDireccionDAO IDireccionDao;
    @Autowired
    IClienteDAO iClienteDao;
    Cliente cliente;
    Direccion direccion;

    public List<Direccion> ObtenerDirecciones(){
        List<Direccion> listD = IDireccionDao.ObtenerDirecciones();
        return listD;
    }

    public ClienteDirDTO ObtenerDireccionId(long id){
        ClienteDirDTO clienteDirDto;
        Cliente cliente = iClienteDao.ObtenerClienteId(id);
        Direccion direccion = IDireccionDao.ObtenerDireccionId(id);

        clienteDirDto = new ClienteDirDTO();
        clienteDirDto.setNombre(cliente.getNombre());
        clienteDirDto.setApellido(cliente.getApellido());
        clienteDirDto.setCalle(direccion.getCalle());
        clienteDirDto.setNoExterior(direccion.getNoExterior());
        clienteDirDto.setCodPostal(direccion.getCodPostal());
        clienteDirDto.setEstado(direccion.getEstado());
        clienteDirDto.setReferencia(direccion.getReferencia());
        LOG.info("Service Cliente Direccion: " + clienteDirDto);
        return clienteDirDto;
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
