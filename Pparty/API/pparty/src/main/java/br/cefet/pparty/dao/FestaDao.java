package br.cefet.pparty.dao;

import br.cefet.pparty.model.Festa;
import java.util.List;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@RegisterBeanMapper(Festa.class)
public interface FestaDao
{
    @GetGeneratedKeys
    @SqlUpdate("insert into TbFesta (data, titulo, enderecofesta, valorIngresso, cardapio, qingresso, categoria, latitude, longitude, idUsuariof) values (:data, :titulo, :enderecofesta, :valorIngresso, :cardapio, :qingresso, :categoria, latitude, longitude, :idUsuariof )")
    int insert(@BindBean Festa festa);

    @SqlQuery ("select * "+
                " from TbFesta "+
                " where idFesta = :idFesta;")
    Festa get(@Bind("idFesta") int idFesta);

    @SqlQuery ("select * "+
                " from TbFesta ")
    List<Festa> getAll();

    @SqlQuery("select * " +
            " from tbFesta f, tbUsuario us " +
            " where us.idUsuario = f.idUsuariof " +
            "   and us.idUsuario = :idUsuario;")
    List<Festa> getAllByUsuario(@Bind("idUsuario") int idUsuario);

    @SqlUpdate (" update TbFesta " +
                " set data = :data, " +
                "   titulo = :titulo, " +
                "   enderecofesta = :enderecofesta, " +
                "   valorIngresso = :valorIngresso, " +
                "   cardapio = :cardapio, " +
                "   qingresso = :qingresso, "+
                "   categoria = :categoria ,"+
                "   latitude = :latitude ,"+
                "   longitude = :longitude ,"+
                "   idUsuariof = :idUsuariof"+
                "   where idFesta = :idFesta;")

    int update(@BindBean Festa festa);

    @SqlUpdate("delete " +
            " from TbFesta " +
            " where idFesta = :idFesta;")
    int delete (@Bind("idFesta") int idFesta);
    

    @SqlQuery("SELECT * " +
              "FROM TbFesta f " +
              "WHERE f.titulo LIKE CONCAT(:pesquisa , '%') or " +
              "f.enderecofesta LIKE CONCAT(:pesquisa , '%') or " +
              "f.cardapio LIKE CONCAT(:pesquisa , '%');")
              
        List<Festa> getByFilter(@Bind("pesquisa") String pesquisa);
}