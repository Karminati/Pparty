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

import br.cefet.pparty.model.Comentario;
import br.cefet.pparty.service.ComentarioService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/comentario")
public class ComentarioController 
{

    private final ComentarioService comentarioService;

    public ComentarioController(ComentarioService comentarioservice)
    {
        this.comentarioService = comentarioservice;
    }
    

    @GetMapping({"/", ""})
    public List<Comentario> consultarTodos(){
        List<Comentario> comentarioList = comentarioService.consultarTodos();

        return comentarioList;
    }

    @GetMapping("/{idAvaliacaoc}/comentario")
    public Comentario consultarporIdFesta(@PathVariable("idAvaliacaoc") int idAvaliacaoc){
       Comentario comentarioList = comentarioService.consultarporIdAvaliacaoc(idAvaliacaoc);
        return comentarioList;
    }

    @GetMapping("/{idComentario}")
    public Comentario consultarComentario(@PathVariable("idComentario") int idComentario){
        Comentario ret = comentarioService.consultarporId(idComentario);
        return ret;
    }
    
    @PostMapping({"", "/"})
    public Comentario inserir (@RequestBody Comentario comentario)
    {
        Comentario ret = comentarioService.inserir(comentario);
        return ret;
    }

    @PutMapping({"", "/{idComentario}"})
    public Comentario alterar (@RequestBody Comentario comentario)
    {
        Comentario comentario2 = comentarioService.consultarporId(comentario.getIdComentario());
        if (comentario2 == null)
        {
            throw new RuntimeException( "Nao existe Comentario com esse idComentario para ser alterar");
        }
        comentarioService.alterar(comentario);
        return comentario;
    }

    @DeleteMapping({"" , "/{idComentario}"})
    public Comentario alterar(@PathVariable ("idComentario") int idComentario)
    {
        Comentario comentario = comentarioService.consultarporId(idComentario);
        if (comentario == null)
        {
            throw new RuntimeException( "Nao existe Comentario com esse idComentario para ser excluido");
        }
        comentarioService.delete(idComentario);
        return comentario;
    }
}
