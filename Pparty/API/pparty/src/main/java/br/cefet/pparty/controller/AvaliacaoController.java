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

import br.cefet.pparty.model.Avaliacao;
import br.cefet.pparty.service.AvaliacaoService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/avaliacao")
public class AvaliacaoController 
{

    private final AvaliacaoService avaliacaoService;

    public AvaliacaoController(AvaliacaoService avaliacaoservice)
    {
        this.avaliacaoService = avaliacaoservice;
    }

    @GetMapping({"/", ""})
    public List<Avaliacao> consultarTodos(){
        List<Avaliacao> avaliacaoList = avaliacaoService.consultarTodos();

        return avaliacaoList;
    }


    @GetMapping("/{fkAvaliacao}/avaliacaofesta")
    public List<Avaliacao> consultarporIdFesta(@PathVariable("fkAvaliacao") int fkAvaliacao){
       List<Avaliacao> avaliacaoList = avaliacaoService.consultarporIdFesta(fkAvaliacao);
        return avaliacaoList;
    }

    @GetMapping("/{idFestaa}/avafesta")
    public List<Avaliacao> consultarporIdF(@PathVariable("idFestaa") int idFestaa){
       List<Avaliacao> avaliacaoList = avaliacaoService.consultarporIdF(idFestaa);
        return avaliacaoList;
    }


    @GetMapping("/{idAvaliacao}")
    public Avaliacao consultarAvaliacao(@PathVariable("idAvaliacao") int idAvaliacao){
        Avaliacao ret = avaliacaoService.consultarporId(idAvaliacao);
        return ret;
    }
    
    @PostMapping({"", "/"})
    public Avaliacao inserir (@RequestBody Avaliacao avaliacao)
    {
        Avaliacao ret = avaliacaoService.inserir(avaliacao);
        return ret;
    }

    @PutMapping({"", "/{idAvaliacao}"})
    public Avaliacao alterar (@RequestBody Avaliacao avaliacao)
    {
        Avaliacao avaliacao2 = avaliacaoService.consultarporId(avaliacao.getIdAvaliacao());
        if (avaliacao2 == null)
        {
            throw new RuntimeException( "Nao existe Avaliacao com esse idAvaliacao para ser alterar");
        }
        avaliacaoService.alterar(avaliacao);
        return avaliacao;
    }

    @DeleteMapping({"" , "/{idAvaliacao}"})
    public Avaliacao alterar(@PathVariable ("idAvaliacao") int idAvaliacao)
    {
        Avaliacao avaliacao = avaliacaoService.consultarporId(idAvaliacao);
        if (avaliacao == null)
        {
            throw new RuntimeException( "Nao existe Avaliacao com esse idAvaliacao para ser excluido");
        }
        avaliacaoService.delete(idAvaliacao);
        return avaliacao;
    }
}
