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
import br.cefet.pparty.service.ChatService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService){
        this.chatService = chatService;
    }

    @GetMapping("/{idChat}")
    public Chat consultarChat(@PathVariable("idChat") int idChat){
        Chat ret = chatService.consultarPorId(idChat);
        return ret;
    }
    
    @GetMapping("/{idUsuario}/usuario")
    public List<Chat> consultarPorIdUsuario(@PathVariable("idUsuario") int idUsuario){
        List<Chat> ret = chatService.consultarPorIdUsuario(idUsuario);
        return ret;
    }

    @PostMapping({"", "/"})
    public Chat inserir (@RequestBody Chat chat){
        Chat ret = chatService.inserir(chat);
        return ret;
    }
}
