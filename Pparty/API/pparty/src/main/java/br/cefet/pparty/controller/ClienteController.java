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

import br.cefet.pparty.model.Cliente;
import br.cefet.pparty.service.ClienteService;



@RestController
@RequestMapping("/api/v1/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteservice)
    {
        this.clienteService = clienteservice;
    }
    

    @GetMapping({"/", ""})
    public List<Cliente> consultarTodos(){
        List<Cliente> clienteList = clienteService.consultarTodos();

        return clienteList;
    }


    @GetMapping("/{id}")
    public Cliente consultarCliente(@PathVariable("id") int id){
        Cliente ret = clienteService.consultarporId(id);
        return ret;
    }
    
    @PostMapping({"", "/"})
    public Cliente inserir (@RequestBody Cliente cliente)
    {
        Cliente ret = clienteService.inserir(cliente);
        return ret;
    }

    @PutMapping({"", "/{id}"})
    public Cliente alterar (@RequestBody Cliente cliente)
    {
        Cliente cliente2 = clienteService.consultarporId(cliente.getId());
        if (cliente2 == null)
        {
            throw new RuntimeException( "Nao existe aluno com esse id para ser excluido");
        }
        clienteService.alterar(cliente);
        return cliente;
    }
}
