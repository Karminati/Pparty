package br.cefet.pparty.dao;

import br.cefet.pparty.model.Avaliacao;
import br.cefet.pparty.model.FotoFesta;

import java.util.List;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@RegisterBeanMapper(Avaliacao.class)
public interface AvaliacaoDao
{
    @GetGeneratedKeys
    @SqlUpdate("insert into tbAvaliacao (tbFesta_idFesta, idUsuarioa) values (:tbFesta_idFesta, :idUsuarioa)")

    int insert(@BindBean Avaliacao avaliacao);

    @SqlQuery ("select * "+
                " from tbAvaliacao "+
                " where idAvaliacao = :idAvaliacao;")
    Avaliacao get(@Bind("idAvaliacao") int idAvaliacao);

    @SqlQuery ("select * "+
                " from tbAvaliacao ")
    List<Avaliacao> getAll();

    @SqlQuery("select * " +
            " from tbAvaliacao ta, tbusuario u " +
            " where u.idUsuario = ta.idUsuarioa " +
            " and u.idUsuario = :idUsuarioa;")
    List<Avaliacao> getAllByFesta(@Bind("idUsuarioa") int idUsuarioa);

    @SqlQuery("select * " +
            " from tbAvaliacao ta, tbfesta f " +
            " where f.idFesta = ta.tbFesta_idFesta " +
            " and f.idFesta = :idFestaa;")
    List<Avaliacao> getAllBy(@Bind("idFestaa") int idFestaa);


    @SqlUpdate (" update tbAvaliacao SET" +
                " quantAvaliacao = :quantAvaliacao " +
                " where idAvaliacao = :idAvaliacao ")
    int update(@BindBean Avaliacao avaliacao);

    @SqlUpdate("delete " +
            " from tbAvaliacao " +
            " where idAvaliacao = :idAvaliacao;")
    int delete (@Bind("idAvaliacao") int idAvaliacao);
    
}