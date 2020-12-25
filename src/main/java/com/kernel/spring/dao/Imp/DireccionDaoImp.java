package com.kernel.spring.dao.Imp;

import com.kernel.spring.dao.IDireccionDAO;
import com.kernel.spring.dto.ClienteDTO;
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
    public List<Direccion> ObtenerDirecciones() {
        String hql = "From Direccion";
        return (List<Direccion>) entityManager.createQuery(hql).getResultList();
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
