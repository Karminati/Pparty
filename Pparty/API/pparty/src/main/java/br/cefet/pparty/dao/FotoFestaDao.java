package br.cefet.pparty.dao;

import br.cefet.pparty.model.Comentario;
import br.cefet.pparty.model.Festa;
import br.cefet.pparty.model.FotoFesta;
import java.util.List;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@RegisterBeanMapper(FotoFesta.class)
public interface FotoFestaDao
{
    @GetGeneratedKeys
    @SqlUpdate("insert into TbFotoFesta (idFoto, url, fkFotoFesta) values (:idFoto , :url, :fkFotoFesta)")

    int insert(@BindBean FotoFesta foto);

    @SqlQuery ("select * "+
                " from TbFotoFesta "+
                " where idFoto = :idFoto;")
    FotoFesta get(@Bind("idFoto") int idFoto);

    @SqlQuery ("select * "+
                " from TbFotoFesta ")
    List<FotoFesta> getAll();

    @SqlQuery("select * " +
            " from tbfotoFesta ff, tbFesta f " +
            " where f.idFesta = ff.fkFotoFesta " +
            "   and ff.fkFotoFesta = :fkFotoFesta;")
    List<FotoFesta> getAllByFesta(@Bind("fkFotoFesta") int fkFotoFesta);

    @SqlUpdate (" update TbFotoFesta " +
                " set url = :url " +
                " where idFoto = :idFoto ")
    int update(@BindBean FotoFesta foto);

    @SqlUpdate("delete " +
            " from TbFotoFesta " +
            " where idFoto = :idFoto;")
    int delete (@Bind("idFoto") int idFoto);
    
}