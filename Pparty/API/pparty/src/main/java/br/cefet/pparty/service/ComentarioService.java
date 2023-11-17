package br.cefet.pparty.service;

import br.cefet.pparty.dao.ComentarioDao;
import br.cefet.pparty.model.Comentario;
import br.cefet.pparty.model.Festa;

import java.util.List;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;



@Service
public class ComentarioService{
    
    private final ComentarioDao comentarioDao;

    public ComentarioService(Jdbi jdbi)
    {
        this.comentarioDao = jdbi.onDemand(ComentarioDao.class);

    }

    public Comentario inserir (Comentario comentario)
    {
        int idcomentario = comentarioDao.insert(comentario);
        comentario.setIdComentario(idcomentario);
        return comentario;
    }

    public List<Comentario> consultarTodos()
    {
        return comentarioDao.getAll();
    }

    public Comentario consultarporId(int idcomentario)
    {
        return comentarioDao.get(idcomentario);
    }

    public void alterar(Comentario comentario)
    {
        comentarioDao.update(comentario);
    }

    public void delete(int idcomentario)
    {
        comentarioDao.delete(idcomentario);
    }

    public Comentario consultarporIdAvaliacaoc (int idAvaliacaoc)
    {
       return comentarioDao.getAllByAvaliacao(idAvaliacaoc);
    }
}