package com.kernel.spring.dao.Imp;

import com.kernel.spring.dao.IClienteDAO;
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
public class IClienteDaoImp implements IClienteDAO {

    private static final Logger LOG = Logger.getLogger(IClienteDaoImp.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public List<ClienteDTO> ObtenerClientes() {
        String hql = "Select c.nombre, c.apellido From Cliente as c";
        List<ClienteDTO>listDto = (List<ClienteDTO>) entityManager.createQuery(hql).getResultList();
        LOG.info("DAO Lista Cientes: " + listDto);
        return listDto;
    }

    @Transactional
    @Override
    public Cliente ObtenerClienteId(long id) {
        return entityManager.find(Cliente.class, id);
    }

    @Transactional
    @Override
    public Cliente RegistrarCliente(Cliente cliente) {
        entityManager.merge(cliente);
        return cliente;
    }

    @Transactional
    @Override
    public Cliente ActualizarCliente(Cliente cliente) {
        entityManager.merge(cliente);
        return cliente;
    }

    @Transactional
    @Override
    public void EliminarCliente(long id) {
        Cliente cliente = ObtenerClienteId(id);
        entityManager.remove(cliente);
    }
}
