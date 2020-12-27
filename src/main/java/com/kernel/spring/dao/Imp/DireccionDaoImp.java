package com.kernel.spring.dao.Imp;

import com.kernel.spring.dao.IDireccionDAO;
import com.kernel.spring.dto.ClienteDirDTO;
import com.kernel.spring.model.Direccion;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class DireccionDaoImp implements IDireccionDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public List<ClienteDirDTO> ObtenerDirecciones() {
        String hql = "Select NEW com.kernel.spring.dto.ClienteDirDTO(c.nombre, c.apellido, dir.calle, " +
                        "dir.noExterior, dir.codPostal, dir.estado, dir.referencia) " +
                        "From Direccion as dir INNER JOIN dir.cliente as c";

        return entityManager.createQuery(hql, ClienteDirDTO.class).getResultList();
    }

    @Transactional
    @Override
    public Direccion ObtenerDireccionId(long id) {
        return entityManager.find(Direccion.class, id);
    }

    @Transactional
    @Override
    public Direccion RegistrarDireccion(Direccion direccion) {
        entityManager.merge(direccion);
        return direccion;
    }

    @Transactional
    @Override
    public Direccion ActualizarDireccion(Direccion direccion) {
        entityManager.merge(direccion);
        return direccion;
    }

    @Transactional
    @Override
    public void EliminarDireccion(long id) {
        Direccion direccion = ObtenerDireccionId(id);
        entityManager.remove(direccion);

    }
}
