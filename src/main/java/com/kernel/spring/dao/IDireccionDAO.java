package com.kernel.spring.dao;

import com.kernel.spring.dto.ClienteDTO;
import com.kernel.spring.dto.ClienteDirDTO;
import com.kernel.spring.model.Direccion;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IDireccionDAO {

    List<ClienteDirDTO> obtenerDirecciones();

    Direccion obtenerDireccionId(@PathVariable long id);

    Direccion registrarDireccion(@RequestBody Direccion direccion);

    Direccion actualizarDireccion(@RequestBody Direccion direccion);

    void eliminarDireccion(@PathVariable long id);
}
