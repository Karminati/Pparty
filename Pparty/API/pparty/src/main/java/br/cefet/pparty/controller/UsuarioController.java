package br.cefet.pparty.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;


import br.cefet.pparty.model.Usuario;
import br.cefet.pparty.service.UsuarioService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService)
    {
        this.usuarioService = usuarioService;
    }
    

    @GetMapping({"/", ""})
    public List<Usuario> consultarTodos(){
        List<Usuario> usuarioList = usuarioService.consultarTodos();

        return usuarioList;
    }


    @GetMapping("/{id}")
    public Usuario consultarUsuario(@PathVariable("id") int id){
        Usuario ret = usuarioService.consultarporId(id);
        return ret;
    }

    @GetMapping("/{email}/{senha}/logar")
    public Usuario logarUsuario(@PathVariable("email") String email, @PathVariable("senha") String senha){
        Usuario ret = usuarioService.logar(email, senha);
        return ret;
    }
    
    @PostMapping({"", "/"})
    public Usuario inserir (@RequestBody Usuario usuario)
    {
        Usuario ret = usuarioService.inserir(usuario);
        return ret;
    }

    @PutMapping({"", "/{id}"})
    public Usuario alterar (@RequestBody Usuario usuario)
    {
        Usuario usuario2 = usuarioService.consultarporId(usuario.getIdUsuario());
        if (usuario2 == null)
        {
            throw new RuntimeException( "Nao existe aluno com esse id para ser alterado");
        }
        usuarioService.alterar(usuario);
        return usuario;
    }

    @DeleteMapping({"/{id}"})
    public void delete(@PathVariable("id") int id)
    {
        Usuario usuario2 = usuarioService.consultarporId(id);
        if (usuario2 == null)
        {
            throw new RuntimeException( "Nao existe aluno com esse id para ser excluido");
        }
        usuarioService.delete(id);
    }

    @GetMapping({"/{id}/usuario"})
    public Usuario consultarPorId(@PathVariable("id")int id){
        Usuario ret = usuarioService.consultarporId(id);
        return ret;
    }
}
