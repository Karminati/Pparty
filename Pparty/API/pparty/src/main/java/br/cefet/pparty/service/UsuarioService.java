package br.cefet.pparty.service;


import br.cefet.pparty.dao.UsuarioDao;
import br.cefet.pparty.model.Usuario;

import java.util.List;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;

@Service


public class UsuarioService {
    
    private final UsuarioDao usuarioDao;

    public UsuarioService(Jdbi jdbi)
    {
        this.usuarioDao = jdbi.onDemand(UsuarioDao.class);

    }

    public Usuario inserir (Usuario usuario)
    {
        int idUsuario = usuarioDao.insert(usuario);
        usuario.setIdUsuario(idUsuario);
        return usuario;
    }

    public List<Usuario> consultarTodos()
    {
        return usuarioDao.getAll();
    }

    public Usuario consultarporId(int id)
    {
        return usuarioDao.get(id);
    }

    public void alterar(Usuario usuario)
    {
        usuarioDao.update(usuario);
    }

    public void delete(int id)
    {
        usuarioDao.delete(id);
    }

    public Usuario logar(String email, String senha)
    {

        return usuarioDao.getLog(email, senha);
    }
}