package br.cefet.pparty.service;

import br.cefet.pparty.dao.FestaDao;
import br.cefet.pparty.model.Festa;
import java.util.List;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;



@Service
public class FestaService{
    
    private final FestaDao festaDao;

    public FestaService(Jdbi jdbi)
    {
        this.festaDao = jdbi.onDemand(FestaDao.class);

    }

    public Festa inserir (Festa festa)
    {
        int idFesta = festaDao.insert(festa);
        festa.setIdFesta(idFesta);
        return festa;
    }

    public List<Festa> consultarTodos()
    {
        return festaDao.getAll();
    }

    public Festa consultarporId(int idFesta)
    {
        return festaDao.get(idFesta);
    }

    public void alterar(Festa festa)
    {
        festaDao.update(festa);
    }

    public void delete(int idFesta)
    {
        festaDao.delete(idFesta);
    }

    public List<Festa> consultarporIdusuario (int idUsuario)
    {
       return festaDao.getAllByUsuario(idUsuario);
    }

    public List<Festa> buscarPorFiltro (String pesquisa)
    {
        // String pesquisa = pesquisaAux;
        // pesquisa = pesquisa + "%";
        // System.out.println(pesquisa);
        // System.out.println(pesquisaAux);
        List<Festa> f = festaDao.getByFilter(pesquisa);

        System.out.println(pesquisa);
        System.out.println(f);

        return f;
    }
}