package com.kernel.spring.dao.Imp;

import com.kernel.spring.dao.IDireccionDao;
import com.kernel.spring.dto.ClienteDirDTO;
import com.kernel.spring.model.Direccion;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Logger;

@Transactional
@Repository
public class DireccionDaoImp implements IDireccionDao {

    private static final Logger LOG = Logger.getLogger(ClienteDaoImp.class.getName());

    @PersistenceContext //Se encarga de administrar las entidades y las transacciones
    EntityManager entityManager; //se encarga de abrir y cerrar las transacciones

    @Transactional//indica que se realizara una accion en la base de datos
    @Override
    public List<ClienteDirDTO> obtenerDirecciones() {
        final String hql = "Select NEW com.kernel.spring.dto.ClienteDirDTO(dir.id, c.correo, c.nombre, c.apellido, c.edad, dir.calle, dir.noExterior, dir.codPostal, dir.estado, dir.municipio, dir.referencia) " +
                "From Direccion as dir INNER JOIN dir.cliente as c";
        return this.entityManager.createQuery(hql, ClienteDirDTO.class).getResultList();
    }

    @Transactional
    @Override
    public ClienteDirDTO obtenerDireccionId(long id) throws NoResultException {
        final String HQL = "Select NEW com.kernel.spring.dto.ClienteDirDTO(dir.id, c.correo, c.nombre, c.apellido, c.edad, dir.calle, dir.noExterior, dir.codPostal, dir.estado, dir.municipio, dir.referencia) " +
                "From Direccion as dir INNER JOIN dir.cliente as c where dir.id = :id";
            return entityManager.createQuery(HQL, ClienteDirDTO.class).setParameter("id",id).getSingleResult();
            //return entityManager.find(Direccion.class, id);
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
    public void eliminarDireccion(long id) {
        LOG.info("Dao id a eliminaar " + id);
            Direccion direccion = entityManager.find(Direccion.class,id);
            entityManager.remove(direccion);
            LOG.info("id Eliminado " + id);
    }
}
