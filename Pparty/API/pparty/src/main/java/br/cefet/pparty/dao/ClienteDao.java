package br.cefet.pparty.dao;

import br.cefet.pparty.model.Cliente;
import java.util.List;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@RegisterBeanMapper(Cliente.class)
public interface ClienteDao
{
    @GetGeneratedKeys
    @SqlUpdate("insert into TbCliente (idade, nome, email, senha) values (:idade, :nome, :email, :senha)")
    int insert(@BindBean Cliente cliente);

    @SqlQuery ("select * "+
                " from TbCliente "+
                " where id = :id;")
    Cliente get(@Bind("id") int id);

    @SqlQuery ("select * "+
                " from TbCliente "+
                " order by nome;")
    List<Cliente> getAll();

    @SqlQuery ("select * "+
                " from TbCliente "+
                " where nome like :nome " +
                "order by name;")
    List<Cliente> getAllByName(@Bind("nome") String nome);
    
    @SqlUpdate (" update TbCliente " +
                " set nome = :nome, " +
                "     idade = :idade, "+
                "     email = :email, "+
                "     senha = :senha "+
                " where id = :id;")
    int update(@BindBean Cliente cliente);

    //int delete (@Bind("id") int id);

}