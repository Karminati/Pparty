package br.cefet.pparty.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import br.cefet.pparty.model.Festa;
import br.cefet.pparty.service.FestaService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/festa")
public class FestaController 
{

    private final FestaService festaService;

    public FestaController(FestaService festaservice)
    {
        this.festaService = festaservice;
    }


    @GetMapping("/{idUsuario}/festa")
    public List<Festa> consultarFestabyUsuario(@PathVariable("idUsuario") int idUsuario){
       List<Festa> festaList = festaService.consultarporIdusuario(idUsuario);
        return festaList;
    }

    @GetMapping({"/", ""})
    public List<Festa> consultarTodos(){
        List<Festa> festaList = festaService.consultarTodos();

        return festaList;
    }

    @GetMapping("/{idFesta}")
    public Festa consultarFesta(@PathVariable("idFesta") int idFesta){
        Festa ret = festaService.consultarporId(idFesta);
        return ret;
    }

    @GetMapping("/pesquisa")
    public List<Festa> consultarporfiltro(@RequestParam String pesquisa){
        List<Festa> festaList = festaService.buscarPorFiltro(pesquisa);

        return festaList;
    }
    
    @PostMapping({"", "/"})
    public Festa inserir (@RequestBody Festa festa)
    {
        Festa ret = festaService.inserir(festa);
        return ret;
    }

    @PutMapping({"", "/{idFesta}"})
    public Festa alterar (@RequestBody Festa festa)
    {
        Festa festa2 = festaService.consultarporId(festa.getIdFesta());
        if (festa2 == null)
        {
            throw new RuntimeException( "Nao existe Festa com esse idFesta para ser alteraDa");
        }
        festaService.alterar(festa);
        return festa;
    }

    @DeleteMapping({"" , "/{idFesta}"})
    public Festa alterar(@PathVariable ("idFesta") int idFesta)
    {
        Festa festa = festaService.consultarporId(idFesta);
        if (festa == null)
        {
            throw new RuntimeException( "Nao existe Foto com esse idFesta para ser excluido");
        }
        festaService.delete(idFesta);
        return festa;
    }
}
