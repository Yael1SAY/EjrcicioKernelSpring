package com.kernel.spring.dao;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.kernel.spring.dto.ClienteDTO;
import com.kernel.spring.dto.ClienteDirDTO;
import com.kernel.spring.model.Direccion;
import com.kernel.spring.model.Estado;
//import com.kernel.spring.model.Municipio;
import com.sun.xml.internal.ws.handler.HandlerException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface IDireccionDao {

    List<Direccion> obtenerDirecciones();

    Direccion obtenerDireccionId(@PathVariable long id);

    Direccion registrarDireccion(@RequestBody Direccion direccion);

    Direccion actualizarDireccion(@RequestBody Direccion direccion);

    void eliminarDireccion(@PathVariable long id);

    //Municipio obtenerDireccion(int codigoPostal);

    public List<Estado> obtenerEstados();
}
