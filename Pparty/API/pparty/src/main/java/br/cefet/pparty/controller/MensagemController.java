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

import br.cefet.pparty.model.Chat;
import br.cefet.pparty.model.Mensagem;
import br.cefet.pparty.service.MensagemService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/mensagem")
public class MensagemController {

    private final MensagemService mensagemService;

    public MensagemController(MensagemService mensagemService){
        this.mensagemService = mensagemService;
    }
    

    @GetMapping("/{idChat}")
    public List<Mensagem> consultarPorIdChat(@PathVariable("idChat") int idChat){
        List<Mensagem> ret = mensagemService.consultarPorIdChat(idChat);
        return ret;
    }

    @PostMapping({"", "/"})
    public Mensagem inserir (@RequestBody Mensagem mensagem){
        Mensagem ret = mensagemService.inserir(mensagem);
        return ret;
    }
}
