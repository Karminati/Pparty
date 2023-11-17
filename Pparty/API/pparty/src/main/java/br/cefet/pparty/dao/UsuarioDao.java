package br.cefet.pparty.dao;

import br.cefet.pparty.model.Usuario;
import java.util.List;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@RegisterBeanMapper(Usuario.class)
public interface UsuarioDao {
    
    @GetGeneratedKeys
    @SqlUpdate("insert into tbusuario (datanasc, nome, email, senha, endereco) values (:datanasc, :nome, :email, :senha, :endereco)")
    int insert(@BindBean Usuario usuario);

    @SqlQuery ("select * "+
                " from tbusuario "+
                " where idUsuario = :idUsuario;")
    Usuario get(@Bind("idUsuario") int idUsuario);

    @SqlQuery ("select * "+
                " from tbusuario "+
                " order by nome;")
    List<Usuario> getAll();

    @SqlQuery ("select * "+
                " from tbusuario "+
                " where nome like :nome " +
                "order by name;")
    List<Usuario> getAllByName(@Bind("nome") String nome);


    @SqlQuery ("select * "+
                " from tbusuario "+
                " where email like :email and " +
                " senha like :senha ")
    Usuario getLog(@Bind("email") String email, @Bind("senha") String senha);
    
    @SqlUpdate (" update tbusuario " +
                " set nome = :nome, " +
                "     datanasc = :datanasc, "+
                "     email = :email, "+
                "     senha = :senha, "+
                "     endereco = :endereco"+
                " where idUsuario = :idUsuario;")
    int update(@BindBean Usuario usuario);

    @SqlUpdate (" delete "+
                " from tbusuario "+
                " where idUsuario = :idUsuario;"
               )
    int delete (@Bind("idUsuario") int idUsuario);

}