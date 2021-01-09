package com.kernel.spring.dao.Imp;

import com.kernel.spring.dao.IClienteDao;
import com.kernel.spring.dto.ClienteDTO;
import com.kernel.spring.model.Cliente;
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
        //String hql = "From Cliente as c";
        String hql = "Select NEW com.kernel.spring.dto.ClienteDTO(c.correo, c.nombre, c.apellido, c.edad) From Cliente as c";
        List<ClienteDTO> listCliente = entityManager.createQuery(hql, ClienteDTO.class).getResultList();
        return listCliente;
    }

    @Transactional
    @Override
    public Cliente obtenerClienteId(long id) {
        return entityManager.find(Cliente.class, id);
    }

    @Transactional
    @Override
    public Cliente registrarCliente(Cliente cliente) {
        //entityManager.merge(cliente);
        return cliente;
    }

    @Transactional
    @Override
    public Cliente actualizarCliente(Cliente cliente) {
        entityManager.merge(cliente);
        return cliente;
    }

    @Transactional
    @Override
    public void eliminarCliente(long id) {
        Cliente cliente = obtenerClienteId(id);
        //entityManager.remove(cliente);
    }
}
