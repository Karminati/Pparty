package br.cefet.pparty.dao;

import br.cefet.pparty.model.Avaliacao;
import br.cefet.pparty.model.Chat;

import java.util.List;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@RegisterBeanMapper(Chat.class)
public interface ChatDao 
{
    
    @GetGeneratedKeys
    @SqlUpdate("insert into tbchat (idUsuario1, idUsuario2) values (:idUsuario1, :idUsuario2)")
    int insert(@BindBean Chat chat);

    @SqlQuery ("select * "+
            " from tbchat "+
            " where idChat = :idChat;")
    Chat get(@Bind("idChat") int idChat);

    @SqlQuery ("select * "+
               "from tbchat tc, tbusuario u "+
               "where (u.idUsuario = tc.idUsuario1 "+
               "or u.idUsuario = tc.idUsuario2) "+
               "and u.idUsuario = :idUsuarioch")
    List<Chat> getAllByIdUsuario(@Bind("idUsuarioch") int idUsuarioch);
}
