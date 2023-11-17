package br.cefet.pparty.dao;

import br.cefet.pparty.model.Usuario;
import br.cefet.pparty.model.FotoUsuario;
import java.util.List;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@RegisterBeanMapper(FotoUsuario.class)
public interface FotoUsuarioDao
{
    @GetGeneratedKeys
    @SqlUpdate("insert into TbFotoUsuario (idFoto, url, idUsuario) values (:idFoto , :url, :idUsuario)")

    int insert(@BindBean FotoUsuario foto);

    @SqlQuery ("select * "+
                " from TbFotoUsuario "+
                " where idFoto = :idFoto;")
    FotoUsuario get(@Bind("idFoto") int idFoto);

    @SqlQuery ("select * "+
                " from TbFotoUsuario ")
    List<FotoUsuario> getAll();

    @SqlUpdate (" update TbFotoUsuario " +
                " set url = :url " +
                " where idFoto = :idFoto ")
    int update(@BindBean FotoUsuario foto);

    @SqlUpdate("delete " +
            " from TbFotoUsuario " +
            " where idFoto = :idFoto;")
    int delete (@Bind("idFoto") int idFoto);
    
}