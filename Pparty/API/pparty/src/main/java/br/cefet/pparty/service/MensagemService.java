package br.cefet.pparty.service;

import br.cefet.pparty.dao.MensagemDao;
import br.cefet.pparty.model.Mensagem;

import java.util.List;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;

@Service
public class MensagemService {

    private final MensagemDao mensagemDao;

    public MensagemService(Jdbi jdbi){
        this.mensagemDao = jdbi.onDemand(MensagemDao.class);
    }

    public Mensagem inserir(Mensagem mensagem){
        int idMensagem = mensagemDao.insert(mensagem);
        mensagem.setIdMensagem(idMensagem);
        return mensagem;
    }

    public List<Mensagem> consultarPorIdChat(int idChat){
        return mensagemDao.getAllByIdChat(idChat);
    }

}
