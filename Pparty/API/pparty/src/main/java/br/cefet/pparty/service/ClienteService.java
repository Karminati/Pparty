package br.cefet.pparty.service;

import br.cefet.pparty.dao.ClienteDao;
import br.cefet.pparty.model.Cliente;
import java.util.List;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    
    private final ClienteDao clienteDao;

    public ClienteService(Jdbi jdbi)
    {
        this.clienteDao = jdbi.onDemand(ClienteDao.class);

    }

    public Cliente inserir (Cliente cliente)
    {
        int idCliente = clienteDao.insert(cliente);
        cliente.setId(idCliente);
        return cliente;
    }

    public List<Cliente> consultarTodos()
    {
        return clienteDao.getAll();
    }

    public Cliente consultarporId(int id)
    {
        return clienteDao.get(id);
    }

    public void alterar(Cliente cliente)
    {
        clienteDao.update(cliente);
    }

    public void excluir(int id)
    {
        //clienteDao.delete(id);
    }
}