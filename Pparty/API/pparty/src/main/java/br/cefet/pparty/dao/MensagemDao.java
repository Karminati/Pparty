package br.cefet.pparty.dao;

import br.cefet.pparty.model.Chat;
import br.cefet.pparty.model.Mensagem;

import java.util.List;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@RegisterBeanMapper(Mensagem.class)
public interface MensagemDao {

    @GetGeneratedKeys
    @SqlUpdate("insert into tbmensagem (conteudo, tbChat_IdChat, udOrigem) values (:conteudo, :tbChat_IdChat, :udOrigem)")
    int insert(@BindBean Mensagem mensagem);
    


    @SqlQuery ("select * "+
               "from tbmensagem tm, tbchat c "+
               "where c.idChat = tm.tbChat_IdChat "+
               "and c.idChat = :idChat")
    List<Mensagem> getAllByIdChat(@Bind("idChat") int idChat);
}
