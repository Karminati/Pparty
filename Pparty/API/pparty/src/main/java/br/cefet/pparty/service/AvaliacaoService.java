package br.cefet.pparty.service;

import br.cefet.pparty.dao.AvaliacaoDao;
import br.cefet.pparty.model.Comentario;
import br.cefet.pparty.model.Avaliacao;
import java.util.List;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;



@Service
public class AvaliacaoService{
    
    private final AvaliacaoDao avaliacaoDao;

    public AvaliacaoService(Jdbi jdbi)
    {
        this.avaliacaoDao = jdbi.onDemand(AvaliacaoDao.class);

    }

    public Avaliacao inserir (Avaliacao avaliacao)
    {
        int idAvaliacao = avaliacaoDao.insert(avaliacao);
        avaliacao.setIdAvaliacao(idAvaliacao);
        return avaliacao;
    }

    public List<Avaliacao> consultarTodos()
    {
        return avaliacaoDao.getAll();
    }

    public Avaliacao consultarporId(int idAvaliacao)
    {
        return avaliacaoDao.get(idAvaliacao);
    }

    public void alterar(Avaliacao avaliacao)
    {
        avaliacaoDao.update(avaliacao);
    }

    public void delete(int idAvaliacao)
    {
        avaliacaoDao.delete(idAvaliacao);
    }

    public List<Avaliacao> consultarporIdFesta (int idUsuarioa)
    {
       return avaliacaoDao.getAllByFesta(idUsuarioa);
    }
}