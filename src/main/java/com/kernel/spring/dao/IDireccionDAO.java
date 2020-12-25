package com.kernel.spring.dao;

import com.kernel.spring.dto.ClienteDTO;
import com.kernel.spring.model.Direccion;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IDireccionDAO {

    List<Direccion> ObtenerDirecciones();

    Direccion ObtenerDireccionId(@PathVariable long id);

    Direccion RegistrarDireccion(@RequestBody Direccion direccion);

    Direccion ActualizarDireccion(@RequestBody Direccion direccion);

    void EliminarDireccion(@PathVariable long id);
}
