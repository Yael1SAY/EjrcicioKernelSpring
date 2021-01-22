package com.kernel.spring.dao.Imp;

import com.kernel.spring.dao.IClienteDao;
import com.kernel.spring.dto.ClienteDTO;
import com.kernel.spring.model.Usuario;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Logger;

@Transactional
@Repository
public class ClienteDaoImp implements IClienteDao {

    private static final Logger LOG = Logger.getLogger(ClienteDaoImp.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public List<ClienteDTO> obtenerClientes() {
        //String hql = "From Usuario as c";
        String hql = "Select NEW com.kernel.spring.dto.ClienteDTO(c.correo, c.nombre, c.apellido, c.edad) From Usuario as c";
        List<ClienteDTO> listCliente = entityManager.createQuery(hql, ClienteDTO.class).getResultList();
        return listCliente;
    }

    @Transactional
    @Override
    public Usuario obtenerClienteId(long id) {
        return entityManager.find(Usuario.class, id);
    }

    @Transactional
    @Override
    public Usuario registrarCliente(Usuario usuario) {
        //entityManager.merge(usuario);
        return usuario;
    }

    @Transactional
    @Override
    public Usuario actualizarCliente(Usuario usuario) {
        entityManager.merge(usuario);
        return usuario;
    }

    @Transactional
    @Override
    public void eliminarCliente(long id) {
        Usuario usuario = obtenerClienteId(id);
        //entityManager.remove(usuario);
    }
}
