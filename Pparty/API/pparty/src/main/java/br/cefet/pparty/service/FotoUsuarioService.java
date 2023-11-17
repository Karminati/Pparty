package br.cefet.pparty.service;

import br.cefet.pparty.dao.FotoUsuarioDao;
import br.cefet.pparty.model.FotoUsuario;
import java.util.List;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;



@Service
public class FotoUsuarioService{
    
    private final FotoUsuarioDao fotoUsuarioDao;

    public FotoUsuarioService(Jdbi jdbi)
    {
        this.fotoUsuarioDao = jdbi.onDemand(FotoUsuarioDao.class);

    }

    public FotoUsuario inserir (FotoUsuario foto)
    {
        int idFoto = fotoUsuarioDao.insert(foto);
        foto.setIdFoto(idFoto);
        return foto;
    }

    public List<FotoUsuario> consultarTodos()
    {
        return fotoUsuarioDao.getAll();
    }

    public FotoUsuario consultarporId(int idFoto)
    {
        return fotoUsuarioDao.get(idFoto);
    }

    public void alterar(FotoUsuario foto)
    {
        fotoUsuarioDao.update(foto);
    }

    public void delete(int idFoto)
    {
        fotoUsuarioDao.delete(idFoto);
    }
}