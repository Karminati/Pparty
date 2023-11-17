package br.cefet.pparty.service;

import br.cefet.pparty.dao.FotoFestaDao;
import br.cefet.pparty.model.Comentario;
import br.cefet.pparty.model.FotoFesta;
import java.util.List;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;



@Service
public class FotoFestaService{
    
    private final FotoFestaDao fotoDao;

    public FotoFestaService(Jdbi jdbi)
    {
        this.fotoDao = jdbi.onDemand(FotoFestaDao.class);

    }

    public FotoFesta inserir (FotoFesta foto)
    {
        int idFoto = fotoDao.insert(foto);
        foto.setIdFoto(idFoto);
        return foto;
    }

    public List<FotoFesta> consultarTodos()
    {
        return fotoDao.getAll();
    }

    public FotoFesta consultarporId(int idFoto)
    {
        return fotoDao.get(idFoto);
    }

    public void alterar(FotoFesta foto)
    {
        fotoDao.update(foto);
    }

    public void delete(int idFoto)
    {
        fotoDao.delete(idFoto);
    }

    public List<FotoFesta> consultarporIdFesta (int fkFotoFesta)
    {
       return fotoDao.getAllByFesta(fkFotoFesta);
    }
}