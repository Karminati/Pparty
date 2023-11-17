package br.cefet.pparty.dao;

import br.cefet.pparty.model.Comentario;
import br.cefet.pparty.model.Festa;
import br.cefet.pparty.model.Comentario;
import java.util.List;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@RegisterBeanMapper(Comentario.class)

public interface ComentarioDao {

    @GetGeneratedKeys
    @SqlUpdate("insert into tbcomentario (coment, idAvaliacaoc) values (:coment, :idAvaliacaoc)")

    int insert(@BindBean Comentario comentario);

    @SqlQuery ("select * "+
                " from tbcomentario "+
                " where idComentario = :idComentario;")
    Comentario get(@Bind("idComentario") int idComentario);

    @SqlQuery ("select * "+
                " from tbcomentario ")
    List<Comentario> getAll();

    @SqlQuery("select * " +
            " from tbcomentario ca, tbAvaliacao a " +
            " where a.idAvaliacao = ca.idAvaliacaoc " +
            "   and ca.idAvaliacaoc = :idAvaliacaoc;")
    Comentario getAllByAvaliacao(@Bind("idAvaliacaoc") int idAvaliacaoc);


    @SqlUpdate (" update tbcomentario " +
                " SET coment = :coment, " +
                " idAvaliacaoc = :idAvaliacaoc" +
                " where idComentario = :idComentario;")
    int update(@BindBean Comentario comentario);

    @SqlUpdate("delete " +
            " from tbcomentario " +
            " where idComentario = :idComentario;")
    int delete (@Bind("idComentario") int idComentario);
    
}
