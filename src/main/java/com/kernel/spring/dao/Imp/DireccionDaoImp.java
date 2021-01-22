package com.kernel.spring.dao.Imp;

import com.kernel.spring.dao.IDireccionDao;
import com.kernel.spring.model.Direccion;
import com.kernel.spring.model.Estado;
import com.kernel.spring.model.Rol;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Logger;

@Transactional
@Repository
public class DireccionDaoImp implements IDireccionDao {

    private static final Logger LOG = Logger.getLogger(DireccionDaoImp.class.getName());

    @PersistenceContext //Se encarga de administrar las entidades y las transacciones
    EntityManager entityManager; //se encarga de abrir y cerrar las transacciones

    @Transactional//indica que se realizara una accion en la base de datos
    @Override
    public List<Direccion> obtenerDirecciones() {
        //final String hql = "Select NEW com.kernel.spring.dto.ClienteDirDTO(dir.id, c.correo, c.nombre, c.apellido, c.edad, dir.calle, dir.noExterior, dir.codPostal, dir.estado, dir.municipio, dir.referencia) " +
                //"From Direccion as dir INNER JOIN dir.cliente as c";
        //return this.entityManager.createQuery(hql, ClienteDirDTO.class).getResultList();
        final String listaClientes = "From Direccion d where d.state = 0";
        return entityManager.createQuery(listaClientes,Direccion.class).getResultList();
    }

    @Transactional
    @Override
    public Direccion obtenerDireccionId(long id) {
        //final String HQL = "Select NEW com.kernel.spring.dto.ClienteDirDTO(dir.id, c.correo, c.nombre, c.apellido, c.edad, dir.calle, dir.noExterior, dir.codPostal, dir.estado, dir.municipio, dir.referencia) " +
                //"From Direccion as dir INNER JOIN dir.cliente as c where dir.id = :id";
        //ClienteDirDTO clientResult = entityManager.createQuery(HQL, ClienteDirDTO.class).setParameter("id", id).getSingleResult();
        Direccion direccion = entityManager.find(Direccion.class, id);
        LOG.info("Resultado buscar Cliete id: " + direccion);
        if (direccion.getState()==1){
            direccion = null;
        }
        return direccion;

        //return entityManager.find(Direccion.class, id);
    }

    @Transactional
    @Override
    public Direccion registrarDireccion(Direccion direccion) {
        direccion.getUsuario().setEstatus(true);
        Rol rol = new Rol(1,"ROLE_USER");
        direccion.getUsuario().getRoles().add(rol);
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
        final String DELETEDIRECCION = "UPDATE Direccion d SET d.state = 1 where d.idDireccion = :id";
        final String DELETEUSUARIO = "UPDATE Usuario u SET u.estatus = false where u.idUsuario = :id";
        //sesion.createQuery(HQL).setParameter("id", id)
        //Direccion direccion = entityManager.find(Direccion.class,id);
        LOG.info("Dao id a eliminaar " + id);
        entityManager.createQuery(DELETEDIRECCION).setParameter("id", id).executeUpdate();
        entityManager.createQuery(DELETEUSUARIO).setParameter("id", id).executeUpdate();
        LOG.info("id Eliminado " + id);
    }

    /*@Override
    public Municipio obtenerDireccion(int codigoPostal){
        LOG.info("Codigo Postal " + codigoPostal);
        final String HQL = "From Municipio as m WHERE m.codigoPostal = :codigoPostal";
        Municipio municipio = entityManager.createQuery(HQL, Municipio.class).setParameter("codigoPostal", codigoPostal).getSingleResult();
        LOG.info("Direccion: " + municipio);
        return municipio;
    }*/

    @Override
    public List<Estado> obtenerEstados(){
        final String ESTADOS = "From Estado as e";
        List<Estado> estados = entityManager.createQuery(ESTADOS, Estado.class).getResultList();
        return estados;
    }
}
