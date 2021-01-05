package com.kernel.spring.dao.Imp;

import com.kernel.spring.dao.IDireccionDAO;
import com.kernel.spring.dto.ClienteDirDTO;
import com.kernel.spring.model.Direccion;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Logger;

@Transactional
@Repository
public class DireccionDaoImp implements IDireccionDAO {

    private static final Logger LOG = Logger.getLogger(ClienteDaoImp.class.getName());

    @PersistenceContext //Se encarga de administrar las entidades y las transacciones
    EntityManager entityManager; //se encarga de abrir y cerrar las transacciones

    @Transactional//indica que se realizara una accion en la base de datos
    @Override
    public List<ClienteDirDTO> obtenerDirecciones() {
        final String hql = "Select NEW com.kernel.spring.dto.ClienteDirDTO(c.nombre, c.apellido, dir.calle, dir.noExterior, dir.codPostal, dir.estado, dir.referencia) " +
                "From Direccion as dir INNER JOIN dir.cliente as c";
        return this.entityManager.createQuery(hql, ClienteDirDTO.class).getResultList();
    }

    @Transactional
    @Override
    public Direccion obtenerDireccionId(long id) {
            //return entityManager.createQuery(sql, ClienteDirDTO.class).setParameter("id",id).getSingleResult();
            return entityManager.find(Direccion.class, id);
    }

    @Transactional
    @Override
    public Direccion registrarDireccion(Direccion direccion) {
            entityManager.merge(direccion);
            return direccion;
    }

    @Transactional
    @Override
    public Direccion actualizarDireccion(Direccion direccion) {
            entityManager.merge(direccion);
            return direccion;
    }

    @Transactional
    @Override
    public void eliminarDireccion(long id) throws IllegalArgumentException{
            Direccion direccion = entityManager.find(Direccion.class,id);
            entityManager.remove(direccion);
    }
}
