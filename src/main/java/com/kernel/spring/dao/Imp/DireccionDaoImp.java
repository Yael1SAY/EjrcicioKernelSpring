package com.kernel.spring.dao.Imp;

import com.kernel.spring.dao.IDireccionDAO;
import com.kernel.spring.dto.ClienteDirDTO;
import com.kernel.spring.model.Direccion;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Logger;

@Transactional
@Repository
public class DireccionDaoImp implements IDireccionDAO {

    private static final Logger LOG = Logger.getLogger(ClienteDaoImp.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public List<ClienteDirDTO> ObtenerDirecciones() {
        final String hql = "Select NEW com.kernel.spring.dto.ClienteDirDTO(c.nombre, c.apellido, dir.calle, dir.noExterior, dir.codPostal, dir.estado, dir.referencia) " +
                "From Direccion as dir INNER JOIN dir.cliente as c";
        return entityManager.createQuery(hql, ClienteDirDTO.class).getResultList();
    }

    @Transactional
    @Override
    public Direccion ObtenerDireccionId(long id) {
            /*String sql = "Select NEW com.kernel.spring.dto.ClienteDirDTO(c.nombre, c.apellido, " +
                    "dir.calle, dir.noExterior, dir.codPostal, dir.estado, dir.referencia) " +
                    "From Direccion as dir JOIN dir.cliente as c where c.idCliente = :id";
            return entityManager.createQuery(sql, ClienteDirDTO.class).setParameter("id",id).getSingleResult();
            */
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
        //try{
            Direccion direccion = entityManager.find(Direccion.class,id);
            //ClienteDirDTO direccion = ObtenerDireccionId(id);
            entityManager.remove(direccion);
        //}catch (IllegalArgumentException e){
            //LOG.warning("No existe direccion con id: " + id);
            //e.printStackTrace();
        //}
    }
}
